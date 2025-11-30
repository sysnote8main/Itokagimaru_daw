package io.github.itokagimaru.itokagimaru_daw.data;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class StringKey extends UsefulKey<String> {
    public StringKey(NamespacedKey key, String defaultValue) {
        super(key, PersistentDataType.STRING, defaultValue);
    }
}
