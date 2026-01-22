package org.example;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

public class Letter {

    private final char letter;
    private final Location location;
    private ArmorStand armorStand;

    public Letter(char letter, Location location) {
        this.letter = letter;
        this.location = location;
    }

    public void spawn() {
        armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setVisible(false);
        armorStand.setCustomNameVisible(true);
        armorStand.setCustomName(String.valueOf(letter));
        armorStand.setGravity(false);
        armorStand.setInvulnerable(true);
        armorStand.setSmall(false); // Big letters
        armorStand.setCustomNameVisible(true);
    }

    public void remove() {
        if (armorStand != null) armorStand.remove();
    }

    public ArmorStand getArmorStand() {
        return armorStand;
    }

    public char getLetter() {
        return letter;
    }
}
