package io.github.itokagimaru.itokagimaru_daw.data;

import org.bukkit.NamespacedKey;

public class ItemData {
    private static final String NAMESPACE = "itokagimaru_daw";

    /**
     * Get key instance for pdc container id
     *
     * @param key key
     * @return New instance of NamespacedKey
     */
    private static NamespacedKey getKey(String key) {
        return new NamespacedKey(NAMESPACE, key);
    }

    public static final IntKey BPM = new IntKey(getKey("bpm"), -1);
    public static final IntKey TOP_NOTE = new IntKey(getKey("topnote"), 0);
    public static final IntKey PAGE = new IntKey(getKey("page"), 0);
    public static final ByteArrayKey BYTE_LIST = new ByteArrayKey(getKey("bytelist"), new byte[]{});
    public static final StringKey BUTTON_ID = new StringKey(getKey("buttonid"), "");

    // Todo: implement these values as key
//    public int[] musicsavedbulue(ItemStack item) {
//        if (!item.hasItemMeta()) return new int[0];
//        ItemMeta meta = item.getItemMeta();
//        int[] pdc = meta.getPersistentDataContainer().get(MUSICSAVESBULUE, PersistentDataType.INTEGER_ARRAY);
//        return pdc;
//    }public int[] musicsavedred(ItemStack item) {
//        if (!item.hasItemMeta()) return new int[0];
//        ItemMeta meta = item.getItemMeta();
//        int[] pdc = meta.getPersistentDataContainer().get(MUSICSAVESRED, PersistentDataType.INTEGER_ARRAY);
//        return pdc;
//    }public int[] musicsavedyellow(ItemStack item) {
//        if (!item.hasItemMeta()) return new int[0];
//        ItemMeta meta = item.getItemMeta();
//        int[] pdc = meta.getPersistentDataContainer().get(MUSICSAVESYELLOW, PersistentDataType.INTEGER_ARRAY);
//        return pdc;
//    }
}
