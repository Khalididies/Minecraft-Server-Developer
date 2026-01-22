package org.example;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.ArrayList;

public class SnowballListener implements Listener {

    private final GameManager gameManager;

    public SnowballListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onSnowballHit(ProjectileHitEvent event) {
        if (!(event.getEntity() instanceof Snowball)) return;
        Entity hit = event.getHitEntity();
        if (hit == null) return;

        for (Letter letter : new ArrayList<>(gameManager.getLetters())) {
            if (letter.getArmorStand() != null && letter.getArmorStand().equals(hit)) {
                Player shooter = (Player) ((Snowball) event.getEntity()).getShooter();
                gameManager.letterHit(letter, shooter);
                break;
            }
        }
    }
}
