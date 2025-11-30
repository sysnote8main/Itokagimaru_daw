package io.github.itokagimaru.itokagimaru_daw;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class SheetMusicManager {
    public static ItemMeta makeSheetMusic(Player player) {
        MusicManager musicManager = new MusicManager();
        int[] musicList = musicManager.loadMusic(player);
        ByteArrayManager byteArrayManager = new ByteArrayManager();
        byte[] data = byteArrayManager.encode(musicList);
        ItemStack item = new ItemStack(Material.WOODEN_HOE);
        MakeItem.setItemMeta(item, "記述済みの楽譜", null, "written_sheet_music", null, null);
        ItemMeta meta = item.getItemMeta();
        PlaySound playSound = new PlaySound();
        playSound.playPageTurn(player);
        meta.getPersistentDataContainer().set(PdcManager.BYTELIST, PersistentDataType.BYTE_ARRAY, data);
        meta.lore(List.of(Component.text("written by " + player.getName())));
        return meta;
    }

    public void lodeSheetMusic(Player player, ItemStack item) {
        ByteArrayManager byteArrayManager = new ByteArrayManager();
        PdcManager.GetPDC getPdc = new PdcManager.GetPDC();
        int[] music = byteArrayManager.decode(getPdc.bytelist(item));
        MusicManager musicManager = new MusicManager();
        musicManager.saveMusic(player, music);
        PlaySound playSound = new PlaySound();
        playSound.playLavelup(player);
    }
}
