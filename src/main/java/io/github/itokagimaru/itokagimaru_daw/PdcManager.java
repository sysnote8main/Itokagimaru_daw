package io.github.itokagimaru.itokagimaru_daw;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class PdcManager {
    private static final String NAME_KEY = "itokagimaru_daw";
    private PdcManager() {};
    public static NamespacedKey PAGE = new NamespacedKey(NAME_KEY, "page");
    public static NamespacedKey BPM = new NamespacedKey(NAME_KEY, "bpm");
    public static NamespacedKey TOPNOTE = new NamespacedKey(NAME_KEY, "topnote");
    public static NamespacedKey BYTELIST = new NamespacedKey(NAME_KEY, "bytelist");
    public static NamespacedKey BUTTONID = new NamespacedKey(NAME_KEY, "buttonid");
    public static NamespacedKey MUSICSAVESRED = new NamespacedKey(NAME_KEY, "misdeliveredred");
    public static NamespacedKey MUSICSAVESBULUE = new NamespacedKey(NAME_KEY, "musicsavedbulue");
    public static NamespacedKey MUSICSAVESYELLOW = new NamespacedKey(NAME_KEY, "musicsavedsyellow");


    public static class GetPDC {
        public int bpm(ItemStack item) {
            ItemMeta meta = item.getItemMeta();
            String pdc = meta.getPersistentDataContainer().get(BPM, PersistentDataType.STRING);
            if (pdc == null) return -1;
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
        public byte[] bytelist(ItemStack item) {
            if (!item.hasItemMeta()) {
                ByteArrayManager byteArrayManager = new ByteArrayManager();
                byte[] data =byteArrayManager.encode(new int[] {0});
                return data;
            }
            ItemMeta meta = item.getItemMeta();
            byte[] pdc = meta.getPersistentDataContainer().get(BYTELIST, PersistentDataType.BYTE_ARRAY);
            return pdc;
        }
        public String buttonId(ItemStack item) {
            if (!item.hasItemMeta()) return "";
            ItemMeta meta = item.getItemMeta();
            String pdc = meta.getPersistentDataContainer().get(BUTTONID, PersistentDataType.STRING);
            return pdc;
        }
        public int[] musicsavedbulue(ItemStack item) {
            if (!item.hasItemMeta()) return new int[0];
            ItemMeta meta = item.getItemMeta();
            int[] pdc = meta.getPersistentDataContainer().get(MUSICSAVESBULUE, PersistentDataType.INTEGER_ARRAY);
            return pdc;
        }public int[] musicsavedred(ItemStack item) {
            if (!item.hasItemMeta()) return new int[0];
            ItemMeta meta = item.getItemMeta();
            int[] pdc = meta.getPersistentDataContainer().get(MUSICSAVESRED, PersistentDataType.INTEGER_ARRAY);
            return pdc;
        }public int[] musicsavedyellow(ItemStack item) {
            if (!item.hasItemMeta()) return new int[0];
            ItemMeta meta = item.getItemMeta();
            int[] pdc = meta.getPersistentDataContainer().get(MUSICSAVESYELLOW, PersistentDataType.INTEGER_ARRAY);
            return pdc;
        }

    }
    public static class SetPdc {
        public ItemMeta addStr(ItemStack item, NamespacedKey key, String val) {
            ItemMeta meta = item.getItemMeta();
            meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, val);
            return meta;
        }
        public ItemMeta addIntarray(ItemStack item,NamespacedKey key,int[] val) {
            ItemMeta meta = item.getItemMeta();
            meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER_ARRAY, val);
            return meta;
        }
    }


}
