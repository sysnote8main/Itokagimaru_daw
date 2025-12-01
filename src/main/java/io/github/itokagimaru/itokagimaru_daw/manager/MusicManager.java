package io.github.itokagimaru.itokagimaru_daw.manager;

import io.github.itokagimaru.itokagimaru_daw.Itokagimaru_daw;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MusicManager {
    private final Map<UUID, int[]> savedMusicList = Itokagimaru_daw.savedMusicList;

    public void saveMusic(Player player, int[] music) {
        if (music.length != Itokagimaru_daw.MUSICLENGTH) music = Arrays.copyOf(music, Itokagimaru_daw.MUSICLENGTH);
        Itokagimaru_daw.savedMusicList.put(player.getUniqueId(), music);
    }

    public int[] loadMusic(Player player) {
        if (!savedMusicList.containsKey(player.getUniqueId())) {
            int[] music = new int[Itokagimaru_daw.MUSICLENGTH];
            Arrays.fill(music, 0);
            saveMusic(player, music);
            return music;
        }
        return savedMusicList.get(player.getUniqueId()).clone();
    }

    public Map<UUID, int[]> getSavedMusicList() {
        return savedMusicList;
    }

    public void setSavedMusicList(Map<UUID, int[]> music) {
        Itokagimaru_daw.savedMusicList = music;
    }

    public void makeMapFile(JavaPlugin plugin, Map<UUID, int[]> data) {
        File file = new File(plugin.getDataFolder(), "music.yml");
        YamlConfiguration config = new YamlConfiguration();
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        for (Map.Entry<UUID, int[]> entry : data.entrySet()) {
            String uuid = entry.getKey().toString();
            int[] value = entry.getValue();
            config.set(uuid, value);
        }
        try {
            config.save(file);
            plugin.getLogger().info("music save success.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<UUID, int[]> loadMapFile(JavaPlugin plugin) {
        File file = new File(plugin.getDataFolder(), "music.yml");
        Map<UUID, int[]> data = new HashMap<>();
        if (!file.exists()) {
            plugin.getLogger().warning("music save failed.");
            return data;
        }
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        for (String key : config.getKeys(false)) {
            try {
                UUID uuid = UUID.fromString(key);
                List<Integer> music = config.getIntegerList(key);
                int[] value = music.stream().mapToInt(Integer::intValue).toArray();
                data.put(uuid, value);
            } catch (IllegalArgumentException e) {
                plugin.getLogger().warning("UUIDの変換に失敗:" + key);
            }
        }
        plugin.getLogger().info("music save success: " + data);
        return data;
    }
}
