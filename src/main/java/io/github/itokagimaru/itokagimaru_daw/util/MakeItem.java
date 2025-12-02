package io.github.itokagimaru.itokagimaru_daw.util;

import io.github.itokagimaru.itokagimaru_daw.data.UsefulKey;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jspecify.annotations.NonNull;

public class MakeItem {
    public static <T> void setItemMeta(ItemStack itemstack, String name, int[] nameColor, String model, UsefulKey<@NonNull T> key, T val) {
        TextColor textColor = null;
        if(nameColor != null) {
            textColor = TextColor.color(nameColor[0], nameColor[2], nameColor[3]);
        }
        setItemMetaByColor(itemstack, name, textColor, model, key, val);
    }

    public static <T> void setItemMetaByColor(ItemStack itemstack, String name, TextColor nameColor, String model, UsefulKey<@NonNull T> key, T val) {
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
                key.set(meta.getPersistentDataContainer(), val);
            }
            itemstack.setItemMeta(meta);
        }
    }
}

