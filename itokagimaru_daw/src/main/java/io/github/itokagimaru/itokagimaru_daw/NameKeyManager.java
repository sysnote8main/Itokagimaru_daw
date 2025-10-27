package io.github.itokagimaru.itokagimaru_daw;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class NameKeyManager {
    private static final String NAME_KEY = "itokagimaru_daw";
    private NameKeyManager() {};
    public static NamespacedKey PAGE = new NamespacedKey(NAME_KEY, "page");
    public static NamespacedKey BPM = new NamespacedKey(NAME_KEY, "bpm");
    public static NamespacedKey ITEMTAG = new NamespacedKey(NAME_KEY, "itemtag");
    public static NamespacedKey TEMPO = new NamespacedKey(NAME_KEY, "tempo");
    public static NamespacedKey TOPNOTE = new NamespacedKey(NAME_KEY, "topnote");
    public static class getPDC {
        public int bpm(ItemStack item) {
            ItemMeta meta = item.getItemMeta();
            String pdc = meta.getPersistentDataContainer().get(BPM, PersistentDataType.STRING);
            return Integer.parseInt(Objects.requireNonNull(pdc));
        }
        public int topnote(ItemStack item) {
            if (!item.hasItemMeta()) return 0;
            ItemMeta meta = item.getItemMeta();
            String pdc = meta.getPersistentDataContainer().get(TOPNOTE, PersistentDataType.STRING);
            return Integer.parseInt(Objects.requireNonNull(pdc));
        }
        public int page(ItemStack item) {
            if (!item.hasItemMeta()) return 0;
            ItemMeta meta = item.getItemMeta();
            String pdc = meta.getPersistentDataContainer().get(PAGE, PersistentDataType.STRING);
            return Integer.parseInt(Objects.requireNonNull(pdc));
        }

    }
}
