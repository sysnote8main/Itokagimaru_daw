package io.github.itokagimaru.itokagimaru_daw.listeners;
import io.github.itokagimaru.itokagimaru_daw.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
public class PlayerJpinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        Location loc = new Location(Bukkit.getWorld("world"), 8,-59,8);
        p.teleport(loc);
        p.setGameMode(GameMode.ADVENTURE);
    }
}
