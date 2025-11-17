package io.github.itokagimaru.itokagimaru_daw;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.UUID;

public class PlayMusic {
    MusicManager music = new MusicManager();
    HashMap<UUID, BukkitTask> tasks = new HashMap<>();
    BukkitTask task;
    public void play_music (Player player, long interval){
        task = new BukkitRunnable() {
            final int[] loded_music = music.loadMusic(player);
            int count = 0;
            MakeItem makeitem = new MakeItem();
            PlaySound playSound = new PlaySound();
            @Override
            public void run() {
                if (loded_music[count] == -1 || count >= loded_music.length) {
                    ItemStack play = new ItemStack(Material.PAPER);
                    play.setItemMeta(makeitem.makeItemMeta(play,"再生",null,"next_b_right",null,null));
                    player.getOpenInventory().getTopInventory().setItem(4,play);
                    cancel();
                }else if(loded_music[count] != 0){
                    playSound.playNote(player,loded_music[count]);

                    ParticleManager particlemanager = new ParticleManager();
                    particlemanager.playNote(player);
                }
                count++;
            }
        }.runTaskTimer(Itokagimaru_daw.getInstance(),0,(long) interval);
    }
    public void stop_task(){
        task.cancel();
    }
}
