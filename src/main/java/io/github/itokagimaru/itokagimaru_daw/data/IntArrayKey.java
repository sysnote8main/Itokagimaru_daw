package io.github.itokagimaru.itokagimaru_daw.data;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class IntArrayKey extends UsefulKey<int[]>{
    public IntArrayKey(NamespacedKey key, int[] defaultValue) {
        super(key, PersistentDataType.INTEGER_ARRAY, defaultValue);
    }
}
