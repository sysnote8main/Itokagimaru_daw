package io.github.itokagimaru.itokagimaru_daw.gui.menu;

import io.github.itokagimaru.itokagimaru_daw.ByteArrayManager;
import io.github.itokagimaru.itokagimaru_daw.FakeEnchant;
import io.github.itokagimaru.itokagimaru_daw.MakeItem;
import io.github.itokagimaru.itokagimaru_daw.MusicManager;
import io.github.itokagimaru.itokagimaru_daw.PdcManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;
import java.util.Objects;

public class ItemsOptionBpmHolder extends DawsOptionBpmHolder {
    @Override
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        player.sendMessage("testes");
        ItemStack clicked = event.getCurrentItem();
        PdcManager.GetPDC getPdc = new PdcManager.GetPDC();
        String buttonId = getPdc.buttonId(clicked);
        if (Objects.equals(buttonId, "SET BPM")) {
            int bpm = getPdc.bpm(clicked);
            MakeItem makeItem = new MakeItem();
            ItemStack item = new ItemStack(Material.WOODEN_HOE);
            MakeItem.setItemMeta(item,"記録済みのカセットテープ",null, "cassette_tape",PdcManager.BPM,String.valueOf(bpm));
            PdcManager.SetPdc setPdc = new PdcManager.SetPdc();
            item.setItemMeta(setPdc.addStr(item, PdcManager.BUTTONID, "RECORD ITEM"));
            ItemMeta meta = item.getItemMeta();
            MusicManager musicManager = new MusicManager();
            int[] musicList = musicManager.loadMusic(player);
            ByteArrayManager byteArrayManager = new ByteArrayManager();
            byte[] data = byteArrayManager.encode(musicList);
            meta.getPersistentDataContainer().set(PdcManager.BYTELIST, PersistentDataType.BYTE_ARRAY, data);
            meta.lore(List.of(Component.text("BPM:" + bpm),Component.text("recorded by " + player.getName())));
            item.setItemMeta(meta);
            FakeEnchant.addFakeEnchant(item);
            player.getInventory().setItemInMainHand(item);
            player.closeInventory();
        }else if (Objects.equals(buttonId, "SHIFT RIGHT")) {
            int selectBpmId = getSelectBpmId(getPdc.bpm(inv.getItem(1)));
            selectBpmId += 1;
            if (selectBpmId > bpmList.length - 7 ) selectBpmId = bpmList.length - 7;
            int bpm = bpmList[selectBpmId];
            updateBpmIcons(bpm);
        }else if (Objects.equals(buttonId, "SHIFT LEFT")) {
            int selectBpmId = getSelectBpmId(getPdc.bpm(inv.getItem(1)));
            selectBpmId -= 1;
            if (selectBpmId < 0 ) selectBpmId = 0;
            int bpm = bpmList[selectBpmId];
            updateBpmIcons(bpm);
        }
    }
}
