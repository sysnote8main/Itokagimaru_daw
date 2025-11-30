package io.github.itokagimaru.itokagimaru_daw;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SwapItems {
    public void mainAndHead(Player player) {
        ItemStack main = player.getInventory().getItemInMainHand().clone();
        ItemStack head = player.getInventory().getHelmet().clone();
        player.getInventory().setHelmet(main);
        player.getInventory().setItemInMainHand(head);
    }
}
