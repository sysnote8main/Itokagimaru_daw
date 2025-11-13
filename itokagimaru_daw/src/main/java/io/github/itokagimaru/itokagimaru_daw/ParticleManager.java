package io.github.itokagimaru.itokagimaru_daw;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class ParticleManager {
    public void playNote(Player player){
        Location location = player.getLocation().add(0,2,0);
        player.getWorld().spawnParticle(Particle.NOTE,location,1);
    }
}
