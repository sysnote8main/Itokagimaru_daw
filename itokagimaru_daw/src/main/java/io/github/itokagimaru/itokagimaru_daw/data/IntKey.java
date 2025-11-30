package io.github.itokagimaru.itokagimaru_daw.data;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class IntKey extends UsefulKey<Integer> {
    public IntKey(NamespacedKey key, Integer defaultValue) {
        super(key, PersistentDataType.INTEGER, defaultValue);
    }
}
