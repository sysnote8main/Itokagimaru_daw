package io.github.itokagimaru.itokagimaru_daw.gui.menu;

import io.github.itokagimaru.itokagimaru_daw.data.ItemData;
import io.github.itokagimaru.itokagimaru_daw.manager.ByteArrayManager;
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
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;

public class ItemsPlayModeHolder extends BaseGuiHolder {
    public ItemsPlayModeHolder() {
        inv = Bukkit.createInventory(this, 9, Component.text("PlayMode"));
        setup();
    }

    public void setup() {
        ItemStack play = new ItemStack(Material.PAPER);
        MakeItem.setItemMeta(play, "再生", null, "next_b_right", ItemData.BUTTON_ID, "PLAY");
        inv.setItem(4, play);
        ItemStack bar = new ItemStack(Material.BARRIER);
        MakeItem.setItemMeta(bar, "未選択", null, null, null, null);
        inv.setItem(7, bar);
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        ItemStack clickedItem = event.getCurrentItem();
        if (Objects.equals(ItemData.BUTTON_ID.get(clickedItem), "RECORD ITEM")) {
            ItemStack recordButton = clickedItem.clone();
            ItemData.BUTTON_ID.set(recordButton, "RECORD BUTTON");
            inv.setItem(7, recordButton);
            ItemStack clock = new ItemStack(Material.CLOCK);
            int bpm = ItemData.BPM.get(clickedItem);
            MakeItem.setItemMeta(clock, "BPM", null, null, null, null);
            ItemMeta meta = clock.getItemMeta();
            meta.lore(List.of(Component.text("現在のBPM設定:" + bpm)));
            clock.setItemMeta(meta);
            inv.setItem(1, clock);
        } else if (Objects.equals(ItemData.BUTTON_ID.get(clickedItem), "RECORD BUTTON")) {
            ItemStack bar = new ItemStack(Material.BARRIER);
            MakeItem.setItemMeta(bar, "未選択", null, null, null, null);
            inv.setItem(7, bar);
            inv.setItem(1, null);
        } else if (Objects.equals(ItemData.BUTTON_ID.get(clickedItem), "PLAY")) {
            Inventory clicked_inv = event.getClickedInventory();
            double bpm = ItemData.BPM.get(Objects.requireNonNull(clicked_inv.getItem(7)));
            if (bpm == -1) return;
            MakeItem.setItemMeta(clickedItem, "再生停止", null, "elytra", ItemData.BUTTON_ID, "STOP");
            PlayMusic play = new PlayMusic();
            Player player = (Player) event.getWhoClicked();
            PlayerMusicManager.setPlayingMusic(player, play);
            ByteArrayManager byteArrayManager = new ByteArrayManager();
            int[] music = byteArrayManager.decode(ItemData.BYTE_LIST.get(clicked_inv.getItem(7)));
            play.playMusic(player, music, (long) (1200 / bpm));
        } else if (Objects.equals(ItemData.BUTTON_ID.get(clickedItem), "STOP")) {
            Player player = (Player) event.getWhoClicked();
            PlayMusic play = PlayerMusicManager.getMusic(player);
            MakeItem.setItemMeta(clickedItem, "再生", null, "next_b_right", ItemData.BUTTON_ID, "PLAY");
            play.stopTask();
        }
    }

    @Override
    public void onClose(Player player) {
        PlayMusic play = PlayerMusicManager.getMusic(player);
        if (play == null) return;
        play.stopTask();
    }
}
