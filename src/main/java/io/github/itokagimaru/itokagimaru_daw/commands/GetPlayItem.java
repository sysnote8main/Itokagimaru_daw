package io.github.itokagimaru.itokagimaru_daw.commands;

import io.github.itokagimaru.itokagimaru_daw.FakeEnchant;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetPlayItem implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(!(sender instanceof Player player)){
            sender.sendMessage("Only players can execute this command");
            return false;
        }
        ((Player) sender).performCommand("give @s minecraft:wooden_hoe[item_model=\"minecraft:walkman\",custom_name=[{\"text\":\"レトロなカセットプレイヤー\"}],lore=[{\"text\":\"レトロでかわいいカセットプレイヤー\"},{\"text\":\"**注意**\"},{\"text\":\"イヤホンジャックがぬけた状態で\"},{\"text\":\"再生してしまいますと\"},{\"text\":\"音漏れいたしますのでご注意ください\"}]]");
        return true;
    }
}
