package io.github.itokagimaru.itokagimaru_daw.gui.listener;

import io.github.itokagimaru.itokagimaru_daw.gui.menu.BaseGuiHolder;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class DawClickInventoryListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof BaseGuiHolder baseGuiHolder)) return;
        ItemStack clicked = event.getCurrentItem();
        if (clicked == null || clicked.getType() == Material.AIR) {
            return;
        }

        event.setCancelled(true);
        baseGuiHolder.onClick(event);
    }
}
