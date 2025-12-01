package io.github.itokagimaru.itokagimaru_daw.data;

import io.papermc.paper.persistence.PersistentDataContainerView;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class IntKey extends UsefulKey<Integer> {
    public IntKey(NamespacedKey key, Integer defaultValue) {
        super(key, PersistentDataType.INTEGER, defaultValue);
    }

    @Override
    public Integer get(PersistentDataContainerView dataContainerView) {
        if(dataContainerView.has(key, PersistentDataType.STRING)) {
            try {
                String rawInt = dataContainerView.get(key, PersistentDataType.STRING);
                if(rawInt == null) return defaultValue;
                return Integer.parseInt(rawInt);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return super.get(dataContainerView);
    }
}
