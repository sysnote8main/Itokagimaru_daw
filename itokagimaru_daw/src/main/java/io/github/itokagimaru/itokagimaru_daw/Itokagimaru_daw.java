package io.github.itokagimaru.itokagimaru_daw;

import io.github.itokagimaru.itokagimaru_daw.listeners.PlayerJpinListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.event.Listener;

import java.util.*;

import io.github.itokagimaru.itokagimaru_daw.listeners.Daw_menu_listener;
import io.github.itokagimaru.itokagimaru_daw.listeners.Daw_Item_use_listener;
import io.github.itokagimaru.itokagimaru_daw.listeners.Daw_close_inventory_listeners;
import io.github.itokagimaru.itokagimaru_daw.commands.GetDawItem;
import io.github.itokagimaru.itokagimaru_daw.commands.GetSheetMusicItem;


public final class Itokagimaru_daw extends JavaPlugin implements Listener {
    public static Itokagimaru_daw instance;
    public static HashMap<UUID,PlayMusic> playing = new HashMap<>();
    public static class operation_playing{
        public void set_playing(Player player,PlayMusic play){
            playing.put(player.getUniqueId(),play);
        }
        public PlayMusic get_playing(Player player){
            return playing.get(player.getUniqueId());
        }
    }
    public static Map<UUID, int[]> savedMusicList = new HashMap<>();
    public static HashMap<UUID, ItemStack[]> inv= new HashMap<>();
    public static int MUSICLENGTH = 2048;
    public static int MAXPAGE = MUSICLENGTH/8;

    @Override
    public void onEnable() {
        // Plugin startup logic
        MusicManager music = new MusicManager();
        music.setSavedMusicList(music.loadMapFile(this));
        Bukkit.getPluginManager().registerEvents(this,this);
        getServer().getPluginManager().registerEvents(new Daw_menu_listener(),this);
        getServer().getPluginManager().registerEvents(new Daw_Item_use_listener(),this);
        getServer().getPluginManager().registerEvents(new Daw_close_inventory_listeners(),this);
        getServer().getPluginManager().registerEvents(new PlayerJpinListener(),this);
        getCommand("getDawItem").setExecutor(new GetDawItem());
        getCommand("getSheetMusic").setExecutor(new GetSheetMusicItem());
        instance = this;
    }

    @Override
    public void onDisable() {
        MusicManager music = new MusicManager();
        music.makeMapFile(this, music.getSavedMusicList());
    }

    public static Itokagimaru_daw getInstance() {return instance;}
}
