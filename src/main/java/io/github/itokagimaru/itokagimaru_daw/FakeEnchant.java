package io.github.itokagimaru.itokagimaru_daw;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class FakeEnchant {
    public static void addFakeEnchant(ItemStack item) {
        item.editMeta(meta -> {
            meta.addEnchant(Enchantment.UNBREAKING, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        });
    }
}
