package io.github.itokagimaru.itokagimaru_daw;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class GuiClickManager {
    public static void menu(Player player, ItemStack clicked) {
        GuiMenuManager menuManager = new GuiMenuManager();
        ItemMeta meta = clicked.getItemMeta();
        if (clicked.getType() == Material.WRITABLE_BOOK && Objects.requireNonNull(meta.displayName()).equals(Component.text("§e打ち込みモード"))) {
            player.closeInventory();
            menuManager.openInputMode(player);
        } else if (clicked.getType() == Material.MUSIC_DISC_13 && Objects.requireNonNull(meta.displayName()).equals(Component.text("§e再生モード"))) {
            player.closeInventory();
            menuManager.openPlayMode(player,null);
        } else if (clicked.getType() == Material.BARRIER && Objects.requireNonNull(meta.displayName()).equals(Component.text("§4しゅうりょう"))) {
            player.closeInventory();
        }
    }
}
