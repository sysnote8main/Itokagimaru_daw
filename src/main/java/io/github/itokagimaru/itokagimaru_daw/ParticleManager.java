package io.github.itokagimaru.itokagimaru_daw;

import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class ParticleManager {
    public void playNote(Player player) {
        player.getWorld().spawnParticle(Particle.NOTE, player.getLocation().add(0, 2, 0), 1);
    }
}
