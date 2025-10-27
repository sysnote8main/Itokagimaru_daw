package io.github.itokagimaru.itokagimaru_daw;


import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;

import org.bukkit.event.Listener;

import java.util.*;

import io.github.itokagimaru.itokagimaru_daw.listeners.Daw_menu_listener;
import io.github.itokagimaru.itokagimaru_daw.listeners.Daw_Item_use_listener;
import io.github.itokagimaru.itokagimaru_daw.listeners.Daw_close_inventory_listeners;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;


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

    @Override
    public void onEnable() {
        // Plugin startup logic
        MusicManager music = new MusicManager();
        music.setSavedMusicList(music.loadMapFile(this));
        Bukkit.getPluginManager().registerEvents(this,this);
        getServer().getPluginManager().registerEvents(new Daw_menu_listener(),this);
        getServer().getPluginManager().registerEvents(new Daw_Item_use_listener(),this);
        getServer().getPluginManager().registerEvents(new Daw_close_inventory_listeners(),this);
        instance = this;
    }

    @Override
    public void onDisable() {
        MusicManager music = new MusicManager();
        music.makeMapFile(this, music.getSavedMusicList());
    }

    public static Itokagimaru_daw getInstance() {return instance;}


}
