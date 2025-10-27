package io.github.itokagimaru.itokagimaru_daw;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class MakeItem {
    public ItemMeta makeItemMeta(ItemStack itemstack, String name, int[] color, String model, NamespacedKey key, String tag) {
        ItemMeta meta = itemstack.getItemMeta();
        if (!(name == null)) {
            if (color == null) {
                meta.displayName(Component.text(name));
            } else {
                meta.displayName(Component.text(name).color(TextColor.color(color[0], color[2], color[3])));
            }
            if (!(model == null)) {
                meta.setItemModel(NamespacedKey.minecraft(model));
            }
            if (key == null) {
                tag = null;
            }
            if (!(tag == null)) {
                meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, tag);
            }
            return meta;
        }
        return null;
    }
}

