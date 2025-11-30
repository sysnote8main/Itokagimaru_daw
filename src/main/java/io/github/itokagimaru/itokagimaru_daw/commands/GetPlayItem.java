package io.github.itokagimaru.itokagimaru_daw.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jspecify.annotations.NullMarked;

import java.util.List;

@NullMarked
public class GetPlayItem implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can execute this command");
            return false;
        }
        ItemStack stack = new ItemStack(Material.WOODEN_HOE);
        stack.editMeta(meta -> {
            meta.setItemModel(NamespacedKey.minecraft("walkman"));
            meta.customName(Component.text("レトロなカセットプレイヤー"));
            meta.lore(List.of(
                    Component.text("レトロでかわいいカセットプレイヤー"),
                    Component.text("**注意**"),
                    Component.text("イヤホンジャックがぬけた状態で"),
                    Component.text("再生してしまいますと"),
                    Component.text("音漏れいたしますのでご注意ください")
            ));
        });
        player.give(stack);
        return true;
    }
}
