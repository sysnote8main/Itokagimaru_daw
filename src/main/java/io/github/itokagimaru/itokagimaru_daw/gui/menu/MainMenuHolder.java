package io.github.itokagimaru.itokagimaru_daw.gui.menu;

import io.github.itokagimaru.itokagimaru_daw.data.ItemData;
import io.github.itokagimaru.itokagimaru_daw.util.MakeItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class MainMenuHolder extends BaseGuiHolder {
    public MainMenuHolder() {
        this.inv = Bukkit.createInventory(this, 9, Component.text("MainMenu"));
        setup();
    }

    public void setup() {
        ItemStack writable = new ItemStack(Material.WRITABLE_BOOK);
        MakeItem.setItemMetaByColor(writable, "打ち込みモード", NamedTextColor.YELLOW, null, ItemData.BUTTON_ID.key, "INPUT MODE");
        this.inv.setItem(3, writable);

        ItemStack disc = new ItemStack(Material.MUSIC_DISC_13);
        MakeItem.setItemMetaByColor(disc, "再生モード", NamedTextColor.YELLOW, null, ItemData.BUTTON_ID.key, "PLAY MODE");
        this.inv.setItem(5, disc);

        ItemStack bar = new ItemStack(Material.BARRIER);
        MakeItem.setItemMetaByColor(bar, "しゅうりょう", NamedTextColor.DARK_RED, null, ItemData.BUTTON_ID.key, "CLOSE");
        this.inv.setItem(8, bar);
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        Player clickedPlayer = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();
        if (Objects.equals(ItemData.BUTTON_ID.get(clickedItem), "INPUT MODE")) {
            InputModeHolder inputModeHolder = new InputModeHolder();
            inputModeHolder.open(clickedPlayer);
        } else if (Objects.equals(ItemData.BUTTON_ID.get(clickedItem), "PLAY MODE")) {
            clickedPlayer.closeInventory();
            DawsPlayModeHolder dawsPlayModeHolder = new DawsPlayModeHolder(60);
            clickedPlayer.openInventory(dawsPlayModeHolder.getInventory());
        } else if (Objects.equals(ItemData.BUTTON_ID.get(clickedItem), "CLOSE")) {
            clickedPlayer.closeInventory();
        }
    }

    @Override
    public void onClose(Player player) {
    }

}
