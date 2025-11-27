package io.github.itokagimaru.itokagimaru_daw.gui.listener;

import io.github.itokagimaru.itokagimaru_daw.gui.menu.*;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class DawClickInventoryListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() instanceof MainMenuHolder mainHolder) {
            ItemStack clicked = event.getCurrentItem();
            if (clicked == null || clicked.getType() == Material.AIR) {
                return;
            }
            event.setCancelled(true);
            mainHolder.onClick(event);
        } else if (event.getInventory().getHolder() instanceof InputModeHolder inputHolder) {
            ItemStack clicked = event.getCurrentItem();
            if (clicked == null || clicked.getType() == Material.AIR) {
                return;
            }
            event.setCancelled(true);
            inputHolder.onClick(event);
        } else if (event.getInventory().getHolder() instanceof DawsPlayModeHolder dawPlayHolder) {
            event.setCancelled(true);
            ItemStack clicked = event.getCurrentItem();
            if (clicked == null || clicked.getType() == Material.AIR) {
                return;
            }
            dawPlayHolder.onClick(event);
        } else if (event.getInventory().getHolder() instanceof DawsOptionBpmHolder dawBpmHolder) {
            event.setCancelled(true);
            ItemStack clicked = event.getCurrentItem();
            if (clicked == null || clicked.getType() == Material.AIR) {
                return;
            }
            dawBpmHolder.onClick(event);
        } else if (event.getInventory().getHolder() instanceof ItemsOptionBpmHolder itemBpmHolder) {
            event.setCancelled(true);
            ItemStack clicked = event.getCurrentItem();
            if (clicked == null || clicked.getType() == Material.AIR) {
                return;
            }
            itemBpmHolder.onClick(event);
        } else if (event.getInventory().getHolder() instanceof ItemsPlayModeHolder itemPlayHolder) {
            event.setCancelled(true);
            ItemStack clicked = event.getCurrentItem();
            if (clicked == null || clicked.getType() == Material.AIR) {
                return;
            }
            itemPlayHolder.onClick(event);
        }
    }

}
