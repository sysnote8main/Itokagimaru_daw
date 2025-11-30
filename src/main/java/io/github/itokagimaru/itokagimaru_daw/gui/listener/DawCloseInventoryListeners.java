package io.github.itokagimaru.itokagimaru_daw.gui.listener;

import io.github.itokagimaru.itokagimaru_daw.gui.menu.BaseGuiHolder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class DawCloseInventoryListeners implements Listener {
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        // is player
        if(!(e.getPlayer() instanceof Player player)) return;

        // get inventory and check
        Inventory inv = e.getInventory();
        if(!(inv.getHolder() instanceof BaseGuiHolder guiHolder)) return;

        // call onClose
        guiHolder.onClose(player);
    }
}
