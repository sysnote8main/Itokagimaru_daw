package io.github.itokagimaru.itokagimaru_daw.gui.menu;

import io.github.itokagimaru.itokagimaru_daw.manager.MusicManager;
import io.github.itokagimaru.itokagimaru_daw.manager.PdcManager;
import io.github.itokagimaru.itokagimaru_daw.manager.PlayerMusicManager;
import io.github.itokagimaru.itokagimaru_daw.task.PlayMusic;
import io.github.itokagimaru.itokagimaru_daw.util.MakeItem;
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
        inv = Bukkit.createInventory(this, 9, Component.text("PlayMode"));
        setup(bpm);
    }

    public void setup(int bpm) {
        ItemStack clock = new ItemStack(Material.PAPER);
        MakeItem.setItemMeta(clock, "現在のBPM:" + bpm, null, "clock", PdcManager.BPM, String.valueOf(bpm));
        PdcManager.SetPdc setPdc = new PdcManager.SetPdc();
        clock.setItemMeta(setPdc.addStr(clock, PdcManager.BUTTONID, "OPTION BPM"));
        inv.setItem(2, clock);
        ItemStack play = new ItemStack(Material.PAPER);
        MakeItem.setItemMeta(play, "再生", null, "next_b_right", PdcManager.BUTTONID, "PLAY");
        inv.setItem(4, play);
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();
        Inventory clicked_inv = event.getClickedInventory();
        PdcManager.GetPDC getPdc = new PdcManager.GetPDC();
        if (Objects.equals(getPdc.buttonId(clicked), "OPTION BPM")) {
            int bpm = getPdc.bpm(clicked);
            player.closeInventory();
            DawsOptionBpmHolder dawsOptionBpmHolder = new DawsOptionBpmHolder();
            dawsOptionBpmHolder.updateBpmIcons(bpm);
            player.openInventory(dawsOptionBpmHolder.getInventory());
        } else if (Objects.equals(getPdc.buttonId(clicked), "PLAY")) {
            double bpm = getPdc.bpm(Objects.requireNonNull(clicked_inv.getItem(2)));
            MakeItem.setItemMeta(clicked, "再生停止", null, "elytra", PdcManager.BUTTONID, "STOP");
            PlayMusic play = new PlayMusic();
            PlayerMusicManager.setPlayingMusic(player, play);
            MusicManager music = new MusicManager();
            play.play_music(player, music.loadMusic(player), (long) (1200 / bpm));
        } else if (Objects.equals(getPdc.buttonId(clicked), "STOP")) {
            PlayMusic play = PlayerMusicManager.getMusic(player);
            MakeItem.setItemMeta(clicked, "再生", null, "next_b_right", PdcManager.BUTTONID, "PLAY");
            play.stop_task();
        }
    }

    @Override
    public void onClose(Player player) {
        PlayMusic play = PlayerMusicManager.getMusic(player);
        if (play == null) return;
        play.stop_task();
    }
}
