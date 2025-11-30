package io.github.itokagimaru.itokagimaru_daw.gui.menu;

import io.github.itokagimaru.itokagimaru_daw.*;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class DawsPlayModeHolder extends BaseGuiHolder {
    public DawsPlayModeHolder(int bpm) {
        inv =  Bukkit.createInventory(this, 9, Component.text("PlayMode"));
        setup(bpm);
    }
    public void setup(int bpm) {
        ItemStack clock = new ItemStack(Material.PAPER);
        MakeItem makeItem = new MakeItem();
        makeItem.setItemMeta(clock,"現在のBPM:" + bpm,null, "clock", PdcManager.BPM,String.valueOf(bpm));
        PdcManager.SetPdc setPdc = new PdcManager.SetPdc();
        clock.setItemMeta(setPdc.addStr(clock,PdcManager.BUTTONID,"OPTION BPM"));
        inv.setItem(2, clock);
        ItemStack play = new ItemStack(Material.PAPER);
        makeItem.setItemMeta(play,"再生",null, "next_b_right", PdcManager.BUTTONID,"PLAY");
        inv.setItem(4, play);
    }
    @Override
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();
        Inventory clicked_inv = event.getClickedInventory();
        PdcManager.GetPDC getPdc = new PdcManager.GetPDC();
        if (Objects.equals(getPdc.buttonId(clicked), "OPTION BPM")) {
            int bpm =  getPdc.bpm(clicked);
            player.closeInventory();
            DawsOptionBpmHolder dawsOptionBpmHolder = new DawsOptionBpmHolder();
            dawsOptionBpmHolder.updateBpmIcons(bpm);
            player.openInventory(dawsOptionBpmHolder.getInventory());
        }else if (Objects.equals(getPdc.buttonId(clicked), "PLAY")) {
            double bpm = (double) getPdc.bpm(Objects.requireNonNull(clicked_inv.getItem(2)));
            MakeItem makeItem = new MakeItem();
            makeItem.setItemMeta(clicked,"再生停止",null, "elytra", PdcManager.BUTTONID, "STOP");
            Itokagimaru_daw.operation_playing playing = new Itokagimaru_daw.operation_playing();
            PlayMusic play = new PlayMusic();
            playing.set_playing(player,play);
            MusicManager music = new MusicManager();
            play.play_music(player, music.loadMusic(player), (long) (1200/bpm));
        }else if (Objects.equals(getPdc.buttonId(clicked), "STOP")) {
            Itokagimaru_daw.operation_playing playing = new Itokagimaru_daw.operation_playing();
            PlayMusic play = playing.get_playing(player);
            MakeItem makeItem = new MakeItem();
            makeItem.setItemMeta(clicked, "再生", null, "next_b_right", PdcManager.BUTTONID, "PLAY");
            play.stop_task();
        }
    }
    @Override
    public void onClose(Player player){
        Itokagimaru_daw.operation_playing playing = new Itokagimaru_daw.operation_playing();
        PlayMusic play = playing.get_playing(player);
        if (play == null) return;
        play.stop_task();
    }
}
