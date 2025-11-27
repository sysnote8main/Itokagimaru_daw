package io.github.itokagimaru.itokagimaru_daw.gui.listener;

import io.github.itokagimaru.itokagimaru_daw.gui.menu.InputModeHolder;
import io.github.itokagimaru.itokagimaru_daw.gui.menu.ItemsPlayModeHolder;
import io.github.itokagimaru.itokagimaru_daw.gui.menu.MainMenuHolder;
import io.github.itokagimaru.itokagimaru_daw.gui.menu.DawsPlayModeHolder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.entity.Player;

public class DawCloseInventoryListeners implements Listener {
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        Inventory inv = e.getInventory();
        if (inv.getHolder() instanceof InputModeHolder holder) {//打ち込みモードを閉じたことを検知
            holder.onClose(p);
        }else if (inv.getHolder() instanceof DawsPlayModeHolder holder) {
            holder.onClose(p);
        }else if (inv.getHolder() instanceof ItemsPlayModeHolder holder) {
            holder.onClose(p);
        }
    }
}
