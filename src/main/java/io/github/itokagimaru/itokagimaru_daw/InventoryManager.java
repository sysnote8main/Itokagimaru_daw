package io.github.itokagimaru.itokagimaru_daw;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class InventoryManager {
    private final HashMap<UUID, ItemStack[]> inv= Itokagimaru_daw.inv;
    public void seaveInventory(Player player){
        inv.put(player.getUniqueId(),player.getInventory().getContents().clone());
    }
    public void loadInventory(Player player){
        if(!inv.containsKey(player.getUniqueId())){
            return;
        }
        player.getInventory().setContents(inv.get(player.getUniqueId()).clone());
        inv.remove(player.getUniqueId());
    }
}
