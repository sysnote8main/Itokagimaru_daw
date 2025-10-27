package io.github.itokagimaru.itokagimaru_daw;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


import java.util.Objects;

public class GuiMenuManager {
    MakeItem makeItem = new MakeItem();
    //give @a diamond[custom_name=[{"text":"daw","italic":false,"color":"#95e5f9"}],item_model="id:sample",custom_model_data={floats:[1]}]
    public void openMenu(Player player) {
        Inventory menu = Bukkit.createInventory(null,9, Component.text("§bだう"));
        ItemStack writable = new ItemStack(Material.WRITABLE_BOOK);
        writable.setItemMeta(makeItem.makeItemMeta(writable,"§e打ち込みモード",null,null,null,null));
        menu.setItem(3, writable);

        ItemStack disc = new ItemStack(Material.MUSIC_DISC_13);
        disc.setItemMeta(makeItem.makeItemMeta(disc,"§e再生モード",null,null,null,null));
        menu.setItem(5, disc);

        ItemStack bar = new ItemStack(Material.BARRIER);
        bar.setItemMeta(makeItem.makeItemMeta(bar,"§4しゅうりょう",null,null,null,null));
        menu.setItem(8, bar);

        player.openInventory(menu);
    }
    public void openInputMode(Player player) {
        InventoryManager invManager = new InventoryManager();
        invManager.seaveInventory(player);
        player.getInventory().clear();
        Inventory inputMode = Bukkit.createInventory(null,54,Component.text("§b打ち込みモード"));

        ItemStack paper = new ItemStack(Material.PAPER);
        for (int i = 0 ; i <= 53 ; i++) {
            paper.setItemMeta(makeItem.makeItemMeta(paper,"",null,"note_def",null,null));
            inputMode.setItem(i, paper);
        }
        for (int i = 0 ; i <= 5 ; i++) {
            paper.setItemMeta(makeItem.makeItemMeta(paper,"",null,"key_board_" + String.valueOf(i),null,null));
            inputMode.setItem(i * 9, paper);
        }
        paper.setItemMeta(makeItem.makeItemMeta(paper,"選択中",null,"select",null,null));
        player.getInventory().setItem(10, paper);
        for( int i = 1;i <= 7;i++){
            paper.setItemMeta(makeItem.makeItemMeta(paper,"カーソルを移動",null,"next_b_up",null,null));
            player.getInventory().setItem(10 + i, paper);
        }
        paper.setItemMeta(makeItem.makeItemMeta(paper,"前のページへ",null,"next_b_left",null,null));
        player.getInventory().setItem(1, paper);
        paper.setItemMeta(makeItem.makeItemMeta(paper,"次のページへ",null,"next_b_right",null,null));
        player.getInventory().setItem(3, paper);
        paper.setItemMeta(makeItem.makeItemMeta(paper,"上へスクロール",null,"next_b_up",null,null));
        player.getInventory().setItem(18, paper);
        paper.setItemMeta(makeItem.makeItemMeta(paper,"下へスクロール",null,"next_b_down",null,null));
        player.getInventory().setItem(27, paper);

        ItemStack sign = new ItemStack(Material.OAK_HANGING_SIGN);
        sign.setItemMeta(makeItem.makeItemMeta(sign,"現在1ページ目",null,null, NameKeyManager.PAGE,"1"));
        player.getInventory().setItem(2, sign);

        ItemStack bar = new ItemStack(Material.BARRIER);
        bar.setItemMeta(makeItem.makeItemMeta(bar,"§4しゅうりょう",null,null,null,null));
        player.getInventory().setItem(8, bar);

        String[] whiteName = {"休符","null","ド/C","レ/D","ミ/E","ファ/F","ソ/G","ラ/A","シ/B"};
        for (int i = 0; i < whiteName.length ; i++) {
            if (!whiteName[i].equals("null")) {
                ItemStack white = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
                white.setItemMeta(makeItem.makeItemMeta(white, whiteName[i],null,null,null,null));
                player.getInventory().setItem(i + 26, white);
            }
        }

        String[] blackName = {"ド#/C#","レ#/D#","null","ファ#/F#","ソ#/G#","ラ#/A#"};
        for (int i = 0; i < blackName.length ; i++) {
            if (!blackName[i].equals("null")) {
                ItemStack black = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                black.setItemMeta(makeItem.makeItemMeta(black, blackName[i],null,null,null,null));
                player.getInventory().setItem(i + 19, black);
            }
        }
        ItemStack structure = new ItemStack(Material.STRUCTURE_VOID);
        structure.setItemMeta(makeItem.makeItemMeta(structure,"全削除",null,null,null,null));
        player.getInventory().setItem(6, structure);

        inputGuiUpdate(player, inputMode,Component.text("§b打ち込みモード"),0,1);
        player.openInventory(inputMode);
    }
    public void inputGuiUpdate(Player player , Inventory inv , Component invName, Integer topNote, Integer page) {
        MusicManager music = new MusicManager();
        int[] musicList = music.loadMusic(player);
        int noteInt;

        if (invName == null || topNote == null || musicList == null) {
            return;
        }
        if (Objects.requireNonNull(invName).equals(Component.text("§b打ち込みモード"))){
            ItemStack paper = new ItemStack(Material.PAPER);

            for (int i = 0 ; i <= 53 ; i++) {
                paper.setItemMeta(makeItem.makeItemMeta(paper,"",null,"note_def",null,null));
                inv.setItem(i, paper);
            }
            for (int i = 0 ; i <= 5 ; i++) {
                noteInt = topNote + i;
                if (noteInt >= 7)noteInt -= 6;
                paper.setItemMeta(makeItem.makeItemMeta(paper,String.valueOf(topNote) + "/" + String.valueOf(noteInt),null,"key_board_" + String.valueOf(noteInt),NameKeyManager.TOPNOTE,String.valueOf(topNote)));
                inv.setItem(i * 9, paper);
            }

            for(int i = 0 ; i < 8 ; i++){

                int note = musicList[i+((page -1)*8)];
                if (note != 0){
                    if (note % 2 == 1){
                        paper.setItemMeta(makeItem.makeItemMeta(paper,"",null,"note_up",null,null));
                    } else {
                        paper.setItemMeta(makeItem.makeItemMeta(paper,"",null,"note_dw",null,null));
                    }
                    if(note > topNote *2 && note <= (topNote + 6)*2){

                        note -= topNote * 2;
                        if (note == 25)note -= 24;
                        else if (note >= 13)note -= 12;
                        if(note % 2 == 1){
                            note += 1;
                        }
                        inv.setItem(i +1+ (9 * note / 2 - 9), paper);
                    }
                }
            }
        }
    }
    public void openPlayMode(Player player,Integer bpm) {
        Inventory menu = Bukkit.createInventory(null,9, Component.text("§b再生モード"));
        ItemStack clock = new ItemStack(Material.PAPER);
        if (bpm == null)bpm = 60;
        clock.setItemMeta(makeItem.makeItemMeta(clock,"現在のBPM:" + String.valueOf(bpm),null,"clock",NameKeyManager.BPM,String.valueOf(bpm)));
        menu.setItem(2, clock);
        ItemStack play = new ItemStack(Material.PAPER);
        play.setItemMeta(makeItem.makeItemMeta(play,"再生",null,"next_b_right",null,null));
        menu.setItem(4, play);

        player.openInventory(menu);
    }
    public void openSetBPM(Player player, int bpm) {
        int[] bpmList = {1,2,3,4,5,6,8,10,12,15,16,20,24,25,30,40,48,50,60,75,80,100,120,150,200,240,300,400,600,1200};
        int selectedBpm = 0;
        for (int i = 0; i < bpmList.length; i++) {
            if (bpm == bpmList[i]){
                selectedBpm = i;
            }
        }
        if (selectedBpm > bpmList.length-7 ) selectedBpm = bpmList.length-7;
        Inventory menu = Bukkit.createInventory(null, 9, Component.text("§b設定/BPM"));
        ItemStack left = new ItemStack(Material.PAPER);
        ItemStack right = new ItemStack(Material.PAPER);
        left.setItemMeta(makeItem.makeItemMeta(left,"",null, "next_b_left",null,null));
        right.setItemMeta(makeItem.makeItemMeta(right,"",null, "next_b_right",null,null));
        menu.setItem(0, left);
        menu.setItem(8, right);
        ItemStack green = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        for(int i=0; i<7;i++){
            green.setItemMeta(makeItem.makeItemMeta(green,"set:" + String.valueOf(bpmList[selectedBpm + i]),null, null,NameKeyManager.ITEMTAG,String.valueOf(bpmList[selectedBpm + i])));
            menu.setItem(i+1, green);
        }
        player.openInventory(menu);
    }
}
