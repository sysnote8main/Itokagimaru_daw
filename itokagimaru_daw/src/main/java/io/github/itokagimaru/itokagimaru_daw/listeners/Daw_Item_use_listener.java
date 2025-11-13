package io.github.itokagimaru.itokagimaru_daw.listeners;

import io.github.itokagimaru.itokagimaru_daw.GuiMenuManager;
import io.github.itokagimaru.itokagimaru_daw.ParticleManager;
import io.github.itokagimaru.itokagimaru_daw.SheetMusicManager;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.entity.Player;


import java.util.Objects;

public class Daw_Item_use_listener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        ItemStack item = e.getItem();
        if(!(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)){
            return;
        }
        if(item ==  null) return;
        if (item.getItemMeta().hasItemModel()) {
            NamespacedKey data = item.getItemMeta().getItemModel();
            if (item.getType() == Material.WOODEN_SWORD && Objects.equals(data, NamespacedKey.minecraft("itokagimaru_daw")) && item.getItemMeta().getDisplayName().equals("§x§9§5§E§5§F§9daw")) {
                GuiMenuManager DAW_menu = new GuiMenuManager();
                DAW_menu.openMenu(p);
            }
            if (item.getType() == Material.WOODEN_HOE && Objects.equals(data, NamespacedKey.minecraft("blank_sheet_music"))) {
                SheetMusicManager sheetMusicManage = new SheetMusicManager();
                item.setItemMeta(sheetMusicManage.makeSheetMusic(p));

            }if (item.getType() == Material.WOODEN_HOE && Objects.equals(data, NamespacedKey.minecraft("written_sheet_music"))) {
                SheetMusicManager sheetMusicManage = new SheetMusicManager();
                sheetMusicManage.lodeSheetMusic(p,item);

            }
        }
    }
}
