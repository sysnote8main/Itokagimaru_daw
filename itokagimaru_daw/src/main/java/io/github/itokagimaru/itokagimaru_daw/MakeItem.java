package io.github.itokagimaru.itokagimaru_daw;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class MakeItem {
    public ItemMeta makeItemMeta(ItemStack itemstack, String name, int[] nameColor, String model, NamespacedKey key, String val) {
        ItemMeta meta = itemstack.getItemMeta();
        if (!(name == null)) {
            if (nameColor == null) {
                meta.displayName(Component.text(name));
            } else {
                meta.displayName(Component.text(name).color(TextColor.color(nameColor[0], nameColor[2], nameColor[3])));
            }
            if (!(model == null)) {
                meta.setItemModel(NamespacedKey.minecraft(model));
            }
            if (key == null) {
                val = null;
            }
            if (!(val == null)) {
                meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, val);
            }
            return meta;
        }
        return null;
    }
}

