package io.github.itokagimaru.itokagimaru_daw.data;

import io.papermc.paper.persistence.PersistentDataContainerView;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jspecify.annotations.NullMarked;

import java.util.function.Function;

@NullMarked
public abstract class UsefulKey<C> {
    public final NamespacedKey key;
    public final PersistentDataType<?, C> dataType;
    public final C defaultValue;

    public UsefulKey(NamespacedKey key, PersistentDataType<?, C> dataType, C defaultValue) {
        this.key = key;
        this.dataType = dataType;
        this.defaultValue = defaultValue;
    }

    public C get(PersistentDataContainerView dataContainerView) {
        return dataContainerView.getOrDefault(this.key, this.dataType, this.defaultValue);
    }

    public C get(ItemStack stack) {
        return get(stack.getPersistentDataContainer());
    }

    public void set(PersistentDataContainer dataContainer, C newValue) {
        dataContainer.set(this.key, this.dataType, newValue);
    }

    public void set(ItemStack stack, C newValue) {
        stack.editPersistentDataContainer(p -> set(p, newValue));
    }

    void compute(PersistentDataContainer dataContainer, Function<C, C> applyFunc) {
        set(dataContainer, applyFunc.apply(get(dataContainer)));
    }
}
