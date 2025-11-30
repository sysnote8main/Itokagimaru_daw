package io.github.itokagimaru.itokagimaru_daw.gui.menu;

import io.github.itokagimaru.itokagimaru_daw.MakeItem;
import io.github.itokagimaru.itokagimaru_daw.PdcManager;
import net.kyori.adventure.text.Component;
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
        MakeItem makeItem = new MakeItem();
        MakeItem.setItemMeta(writable, "§e打ち込みモード", null, null, PdcManager.BUTTONID, "INPUT MODE");
        this.inv.setItem(3, writable);

        ItemStack disc = new ItemStack(Material.MUSIC_DISC_13);
        MakeItem.setItemMeta(disc, "§e再生モード", null, null, PdcManager.BUTTONID, "PLAY MODE");
        this.inv.setItem(5, disc);

        ItemStack bar = new ItemStack(Material.BARRIER);
        MakeItem.setItemMeta(bar, "§4しゅうりょう", null, null, PdcManager.BUTTONID, "CLOSE");
        this.inv.setItem(8, bar);
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        Player clickedPlayer = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();
        PdcManager.GetPDC getPDC = new PdcManager.GetPDC();
        if (Objects.equals(getPDC.buttonId(clickedItem), "INPUT MODE")) {
            InputModeHolder inputModeHolder = new InputModeHolder();
            inputModeHolder.open(clickedPlayer);
        } else if (Objects.equals(getPDC.buttonId(clickedItem), "PLAY MODE")) {
            clickedPlayer.closeInventory();
            DawsPlayModeHolder dawsPlayModeHolder = new DawsPlayModeHolder(60);
            clickedPlayer.openInventory(dawsPlayModeHolder.getInventory());
        } else if (Objects.equals(getPDC.buttonId(clickedItem), "CLOSE")) {
            clickedPlayer.closeInventory();
        }
    }

    @Override
    public void onClose(Player player) {
    }

}
