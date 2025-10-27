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
            float pitch = 0;
            MakeItem makeitem = new MakeItem();
            @Override
            public void run() {
                if (loded_music[count] == -1 || count >= loded_music.length) {
                    ItemStack play = new ItemStack(Material.PAPER);
                    play.setItemMeta(makeitem.makeItemMeta(play,"再生",null,"next_b_right",null,null));
                    player.getOpenInventory().getTopInventory().setItem(4,play);
                    cancel();
                }else if(loded_music[count] != 0){
                    pitch = (float) Math.pow(2.0 , (double) (14 - loded_music[count]) / 12);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1.9f,pitch);
                }
                count++;
            }
        }.runTaskTimer(Itokagimaru_daw.getInstance(),0,(long) interval);
    }
    public void stop_task(){
        task.cancel();
    }
}
