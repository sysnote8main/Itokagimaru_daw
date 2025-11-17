package io.github.itokagimaru.itokagimaru_daw.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetDawItem implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(!(sender instanceof Player player)){
            sender.sendMessage("Only players can execute this command");
            return false;
        }
        ((Player) sender).performCommand("give @s minecraft:wooden_sword[custom_name=[{\"text\":\"daw\",\"italic\":false,\"color\":\"#95e5f9\"}],item_model=\"minecraft:itokagimaru_daw\"]");
        return true;
    }

}
