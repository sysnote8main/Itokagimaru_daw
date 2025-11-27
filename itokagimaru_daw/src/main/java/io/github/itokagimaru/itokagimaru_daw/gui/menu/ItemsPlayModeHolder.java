package io.github.itokagimaru.itokagimaru_daw.gui.menu;

import io.github.itokagimaru.itokagimaru_daw.*;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;

public class ItemsPlayModeHolder extends BaseGuiHolder {
    public ItemsPlayModeHolder() {
        inv = Bukkit.createInventory(this, 9, Component.text("PlayMode"));
        setup();
    }
    public void setup() {
        MakeItem makeItem = new MakeItem();
        ItemStack play = new ItemStack(Material.PAPER);
        play.setItemMeta(makeItem.makeItemMeta(play,"再生",null, "next_b_right", PdcManager.BUTTONID,"PLAY"));
        inv.setItem(4, play);
        ItemStack bar = new ItemStack(Material.BARRIER);
        bar.setItemMeta(makeItem.makeItemMeta(bar,"未選択",null, null,null,null));
        inv.setItem(7, bar);
    }
    @Override
    public void onClick(InventoryClickEvent event) {
        ItemStack clickedItem = event.getCurrentItem();
        PdcManager.GetPDC getPdc = new PdcManager.GetPDC();
        PdcManager.SetPdc setPdc = new PdcManager.SetPdc();
        if(Objects.equals(getPdc.buttonId(clickedItem), "RECORD ITEM")) {
            MakeItem makeItem = new MakeItem();
            ItemStack recordButton = clickedItem.clone();
            recordButton.setItemMeta(setPdc.addStr(recordButton, PdcManager.BUTTONID, "RECORD BUTTON"));
            inv.setItem(7, recordButton);
            ItemStack clock = new ItemStack(Material.CLOCK);
            int bpm = getPdc.bpm(clickedItem);
            clock.setItemMeta(makeItem.makeItemMeta(clock, "BPM", null, null, null, null));
            ItemMeta meta = clock.getItemMeta();
            meta.lore(List.of(Component.text("現在のBPM設定:"+ bpm)));
            clock.setItemMeta(meta);
            inv.setItem(1, clock);
        }else if(Objects.equals(getPdc.buttonId(clickedItem), "RECORD BUTTON")) {
            MakeItem makeItem = new MakeItem();
            ItemStack bar = new ItemStack(Material.BARRIER);
            bar.setItemMeta(makeItem.makeItemMeta(bar,"未選択",null, null,null,null));
            inv.setItem(7, bar);
            inv.setItem(1,null);
        } else if (Objects.equals(getPdc.buttonId(clickedItem), "PLAY")) {
            Inventory clicked_inv = event.getClickedInventory();
            double bpm = getPdc.bpm(Objects.requireNonNull(clicked_inv.getItem(7)));
            if (bpm == -1)return;
            MakeItem makeItem = new MakeItem();
            clickedItem.setItemMeta(makeItem.makeItemMeta(clickedItem,"再生停止",null, "elytra", PdcManager.BUTTONID, "STOP"));
            Itokagimaru_daw.operation_playing playing = new Itokagimaru_daw.operation_playing();
            PlayMusic play = new PlayMusic();
            Player player = (Player) event.getWhoClicked();
            playing.set_playing(player,play);
            ByteArrayManager byteArrayManager = new ByteArrayManager();
            int[] music = byteArrayManager.decode(getPdc.bytelist(clicked_inv.getItem(7)));
            play.play_music(player, music, (long) (1200/bpm));
        } else if (Objects.equals(getPdc.buttonId(clickedItem), "STOP")) {
            Itokagimaru_daw.operation_playing playing = new Itokagimaru_daw.operation_playing();
            Player player = (Player) event.getWhoClicked();
            PlayMusic play = playing.get_playing(player);
            MakeItem makeItem = new MakeItem();
            clickedItem.setItemMeta(makeItem.makeItemMeta(clickedItem, "再生", null, "next_b_right", PdcManager.BUTTONID, "PLAY"));
            play.stop_task();
        }
    }
    @Override
    public void onClose(Player player) {
        Itokagimaru_daw.operation_playing playing = new Itokagimaru_daw.operation_playing();
        PlayMusic play = playing.get_playing(player);
        if (play == null) return;
        play.stop_task();
    }
}
