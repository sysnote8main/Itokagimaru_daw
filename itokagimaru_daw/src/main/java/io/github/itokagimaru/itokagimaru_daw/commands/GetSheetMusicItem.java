package io.github.itokagimaru.itokagimaru_daw.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetSheetMusicItem implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(!(sender instanceof Player player)){
            sender.sendMessage("Only players can execute this command");
            return false;
        }
        ((Player) sender).performCommand("give @s minecraft:wooden_hoe[item_model=\"minecraft:blank_sheet_music\",custom_name=[{\"text\":\"白紙の楽譜\"}]]");
        return true;
    }
}
