package io.github.itokagimaru.itokagimaru_daw.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class MakeItem {
    public static void setItemMeta(ItemStack itemstack, String name, int[] nameColor, String model, NamespacedKey key, String val) {
        setItemMetaByColor(itemstack, name, TextColor.color(nameColor[0], nameColor[2], nameColor[3]), model, key, val);
    }

    public static void setItemMetaByColor(ItemStack itemstack, String name, TextColor nameColor, String model, NamespacedKey key, String val) {
        ItemMeta meta = itemstack.getItemMeta();
        if (name != null) {
            var nameComponent = Component.text(name);
            if (nameColor != null) {
                nameComponent = nameComponent.color(nameColor);
            }
            meta.displayName(nameComponent);

            if (model != null) {
                meta.setItemModel(NamespacedKey.minecraft(model));
            }
            if (key != null && val != null) {
                meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, val);
            }
            itemstack.setItemMeta(meta);
        }
    }
}

