package org.example;

import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class GameManager {

    private final Main plugin;
    private final List<Letter> letters = new ArrayList<>();
    private final Map<Player, Integer> scores = new HashMap<>();
    private final Random random = new Random();
    private boolean gameRunning = false;

    public GameManager(Main plugin) {
        this.plugin = plugin;
    }

    public void startGame() {
        if (gameRunning) return;
        gameRunning = true;

        Bukkit.broadcastMessage(ChatColor.GOLD + "Alphabet Snowball Game Started!");

        // Give players snowballs and initialize scores
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.getInventory().clear();
            player.getInventory().addItem(new ItemStack(Material.SNOWBALL, 10));
            scores.put(player, 0);
        }

        // Spawn letters for all players
        spawnLetters();

        // Start scoreboard updater
        startScoreboardTask();

        // Start game timer
        new BukkitRunnable() {
            @Override
            public void run() {
                boolean anySnowballsLeft = false;
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.getInventory().contains(Material.SNOWBALL)) {
                        anySnowballsLeft = true;
                        break;
                    }
                }
                if (!anySnowballsLeft) {
                    endGame();
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 20L, 20L);
    }

    private void spawnLetters() {
        // Remove old letters
        letters.forEach(Letter::remove);
        letters.clear();

        for (Player player : Bukkit.getOnlinePlayers()) {
            Location base = player.getLocation().clone();

            for (int i = 0; i < 4; i++) {
                char c = (char) ('A' + random.nextInt(26));
                Location loc = base.clone().add(i - 1.5, 2, 3); // in front of player
                Letter letter = new Letter(c, loc);
                letter.spawn();

                // Firework effect when letter spawns
                player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, loc, 20, 0.5, 0.5, 0.5, 0.01);

                letters.add(letter);
            }
        }
    }

    public void letterHit(Letter letter, Player shooter) {
        shooter.sendMessage(ChatColor.GREEN + "You hit letter: " + letter.getLetter());
        scores.put(shooter, scores.getOrDefault(shooter, 0) + 1);
        letter.remove();
        letters.remove(letter);

        // Respawn letter after 3 seconds
        new BukkitRunnable() {
            @Override
            public void run() {
                char c = (char) ('A' + random.nextInt(26));
                Location loc = shooter.getLocation().clone().add(random.nextInt(4) - 2, 2, 3);
                Letter newLetter = new Letter(c, loc);
                newLetter.spawn();
                shooter.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, loc, 20, 0.5, 0.5, 0.5, 0.01);
                letters.add(newLetter);
            }
        }.runTaskLater(plugin, 60L); // 3 seconds later
    }

    private void startScoreboardTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
                    org.bukkit.scoreboard.Scoreboard sb = player.getScoreboard();
                    org.bukkit.scoreboard.Objective obj = sb.registerNewObjective("Scores", "dummy", ChatColor.AQUA + "Alphabet Game");
                    obj.setDisplaySlot(org.bukkit.scoreboard.DisplaySlot.SIDEBAR);
                    int i = 1;
                    for (Map.Entry<Player, Integer> entry : scores.entrySet()) {
                        obj.getScore(entry.getKey().getName()).setScore(entry.getValue());
                        i++;
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 20L); // Update every second
    }

    private void endGame() {
        Bukkit.broadcastMessage(ChatColor.GOLD + "Game Over!");
        Player winner = scores.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
        if (winner != null) {
            Bukkit.broadcastMessage(ChatColor.GREEN + "Winner: " + winner.getName() + " with " + scores.get(winner) + " points!");
        }
        letters.forEach(Letter::remove);
        letters.clear();
        scores.clear();
        gameRunning = false;
    }

    public List<Letter> getLetters() {
        return letters;
    }
}
