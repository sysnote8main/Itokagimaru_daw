package io.github.itokagimaru.itokagimaru_daw.data;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class ByteArrayKey extends UsefulKey<byte[]> {
    public ByteArrayKey(NamespacedKey key, byte[] defaultValue) {
        super(key, PersistentDataType.BYTE_ARRAY, defaultValue);
    }
}
