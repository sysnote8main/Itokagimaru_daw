package io.github.itokagimaru.itokagimaru_daw;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;


public class PlayMusic {
    //HashMap<UUID, BukkitTask> tasks = new HashMap<>();
    BukkitTask task;
    public void play_music (Player player,int[] lodedMusic, long interval){
        task = new BukkitRunnable() {
            int count = 0;
            MakeItem makeitem = new MakeItem();
            PlaySound playSound = new PlaySound();
            @Override
            public void run() {
                if (lodedMusic[count] == -1 || count >= lodedMusic.length) {
                    ItemStack play = new ItemStack(Material.PAPER);
                    play.setItemMeta(makeitem.makeItemMeta(play,"再生",null, "next_b_right",PdcManager.BUTTONID,"PLAY"));
                    player.getOpenInventory().getTopInventory().setItem(4,play);
                    cancel();
                }else if(lodedMusic[count] != 0){
                    playSound.playNote(player,lodedMusic[count]);

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
