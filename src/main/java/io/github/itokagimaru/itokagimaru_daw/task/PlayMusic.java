package io.github.itokagimaru.itokagimaru_daw.task;

import io.github.itokagimaru.itokagimaru_daw.Itokagimaru_daw;
import io.github.itokagimaru.itokagimaru_daw.data.ItemData;
import io.github.itokagimaru.itokagimaru_daw.manager.ParticleManager;
import io.github.itokagimaru.itokagimaru_daw.util.MakeItem;
import io.github.itokagimaru.itokagimaru_daw.util.PlaySound;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;


public class PlayMusic {
    //HashMap<UUID, BukkitTask> tasks = new HashMap<>();
    BukkitTask task;

    public void play_music(Player player, int[] lodedMusic, long interval) {
        task = new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                if (lodedMusic[count] == -1) {
                    ItemStack play = new ItemStack(Material.PAPER);
                    MakeItem.setItemMeta(play, "再生", null, "next_b_right", ItemData.BUTTON_ID.key, "PLAY");
                    player.getOpenInventory().getTopInventory().setItem(4, play);
                    cancel();
                } else if (lodedMusic[count] != 0) {
                    PlaySound.playNote(player, lodedMusic[count]);

                    ParticleManager particlemanager = new ParticleManager();
                    particlemanager.playNote(player);
                }
                count++;
            }
        }.runTaskTimer(Itokagimaru_daw.getInstance(), 0, (long) interval);
    }

    public void stop_task() {
        task.cancel();

    }
}
