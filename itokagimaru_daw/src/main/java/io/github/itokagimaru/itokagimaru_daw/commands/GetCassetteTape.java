package io.github.itokagimaru.itokagimaru_daw.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetCassetteTape implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(!(sender instanceof Player player)){
            sender.sendMessage("Only players can execute this command");
            return false;
        }
        ((Player) sender).performCommand("give @s minecraft:wooden_hoe[item_model=\"minecraft:cassette_tape\",custom_name=[{\"text\":\"カセットテープ\"}]]");
        return true;
    }
}
