package io.github.itokagimaru.itokagimaru_daw;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class SheetMusicManager {
    public ItemMeta makeSheetMusic(Player player){
        MusicManager musicManager = new MusicManager();
        int[] musicList = musicManager.loadMusic(player);
        ByteArrayManager byteArrayManager = new ByteArrayManager();
        byte[] data = byteArrayManager.encode(musicList);
        ItemStack item = new ItemStack(Material.WOODEN_HOE);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("記述済みの楽譜"));
        meta.lore(List.of(Component.text("written by:" + player.getName())));
        meta.setItemModel(NamespacedKey.minecraft("written_sheet_music"));
        PlaySound playSound = new PlaySound();
        playSound.playPageTurn(player);
        meta.getPersistentDataContainer().set(NameKeyManager.BYTELIST, PersistentDataType.BYTE_ARRAY, data);
        return  meta;
    }
    public void lodeSheetMusic(Player player,ItemStack item){
        ByteArrayManager byteArrayManager = new ByteArrayManager();
        NameKeyManager.getPDC getPdc = new NameKeyManager.getPDC();
        int[] music = byteArrayManager.decode(getPdc.bytelist(item));
        MusicManager musicManager = new MusicManager();
        musicManager.saveMusic(player,music);
        PlaySound playSound = new PlaySound();
        playSound.playLavelup(player);
    }
}
