package io.github.itokagimaru.itokagimaru_daw.listeners;

import io.github.itokagimaru.itokagimaru_daw.PlayMusic;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.entity.Player;

import io.github.itokagimaru.itokagimaru_daw.Itokagimaru_daw;
import io.github.itokagimaru.itokagimaru_daw.MusicManager;
import io.github.itokagimaru.itokagimaru_daw.InventoryManager;

public class Daw_close_inventory_listeners implements Listener {
    @EventHandler
    public void Daw_close_inventory(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        Inventory inv = e.getInventory();
        if (e.getView().title().equals(Component.text("§b打ち込みモード"))) {//打ち込みモードを閉じたことを検知
            MusicManager music = new MusicManager();
            InventoryManager inventory_lode = new InventoryManager();
            int[] loded_music = music.loadMusic(p);
            for (int i = 0;i < loded_music.length;i++) {//エンドポイントの削除
                if  (loded_music[i] == -1) loded_music[i] = 0;
            }
            int music_end_point = 0;
            for (int i = loded_music.length; i > 0 ; i--) {//エンドポイントの追加
                if (loded_music[i -1] != 0) {
                    music_end_point = i;
                    break;
                }
            }
            if(!(music_end_point>= loded_music.length)) loded_music[music_end_point] = -1;
            music.saveMusic(p, loded_music);
            inventory_lode.loadInventory(p);
        }else if (e.getView().title().equals(Component.text("§b再生モード"))){
            Itokagimaru_daw.operation_playing playing = new Itokagimaru_daw.operation_playing();
            PlayMusic play = playing.get_playing(p);
            if (play == null) return;
            play.stop_task();
        }
    }
}
