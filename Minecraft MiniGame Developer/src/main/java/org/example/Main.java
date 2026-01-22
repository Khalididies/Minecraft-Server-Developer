package org.example;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private GameManager gameManager;

    @Override
    public void onEnable() {
        this.gameManager = new GameManager(this);
        Bukkit.getPluginManager().registerEvents(new SnowballListener(gameManager), this);

        this.getCommand("startgame").setExecutor((sender, cmd, label, args) -> {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can start the game!");
                return true;
            }
            gameManager.startGame();
            return true;
        });

        getLogger().info("AlphabetGame Enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("AlphabetGame Disabled!");
    }
}
