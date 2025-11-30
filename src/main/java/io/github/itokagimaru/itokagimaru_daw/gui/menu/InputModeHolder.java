package io.github.itokagimaru.itokagimaru_daw.gui.menu;

import io.github.itokagimaru.itokagimaru_daw.*;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import io.github.itokagimaru.itokagimaru_daw.PdcManager;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Objects;

public class InputModeHolder extends BaseGuiHolder {
    Inventory playerInventory = Bukkit.createInventory(null, 36);
    public InputModeHolder() {
        this.inv = Bukkit.createInventory(this, 54, Component.text("InputMode"));
        setup();
    }
    public void setup() {
        MakeItem makeItem = new MakeItem();
        ItemStack paper = new ItemStack(Material.PAPER);
        for (int i = 0 ; i <= 53 ; i++) {
            makeItem.setItemMeta(paper,"",null, "note_def",null,null);
            this.inv.setItem(i, paper);
        }
        for (int i = 0 ; i <= 5 ; i++) {
            makeItem.setItemMeta(paper,"",null, "key_board_" + String.valueOf(i),null,null);
            this.inv.setItem(i * 9, paper);
        }
        ItemStack glay = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        makeItem.setItemMeta(glay,"",null, null,null,null);
        for (int i = 0 ; i < 36 ; i++) {
            playerInventory.setItem(i, glay);
        }
        makeItem.setItemMeta(paper,"選択中",null, "select", PdcManager.BUTTONID,"SELECTED");
        playerInventory.setItem(10, paper);
        for( int i = 1;i <= 7;i++){
            makeItem.setItemMeta(paper,"カーソルを移動",null, "next_b_up", PdcManager.BUTTONID,"SELECT");
            playerInventory.setItem(10 + i, paper);
        }
        makeItem.setItemMeta(paper,"1ページへ",null, "next_b_left", PdcManager.BUTTONID,"GO FIRST");
        playerInventory.setItem(0, paper);
        makeItem.setItemMeta(paper,"前のページへ",null, "next_b_left", PdcManager.BUTTONID,"BACK PAGE");
        playerInventory.setItem(1, paper);

        makeItem.setItemMeta(paper,"次のページへ",null, "next_b_right", PdcManager.BUTTONID,"NEXT PAGE");
        playerInventory.setItem(3, paper);
        makeItem.setItemMeta(paper,"最後ののページへ",null, "next_b_right", PdcManager.BUTTONID,"GO END");
        playerInventory.setItem(4, paper);
        makeItem.setItemMeta(paper,"上へスクロール",null, "next_b_up", PdcManager.BUTTONID,"UP NOTE");
        playerInventory.setItem(18, paper);
        makeItem.setItemMeta(paper,"下へスクロール",null, "next_b_down", PdcManager.BUTTONID,"DOWN NOTE");
        playerInventory.setItem(27, paper);

        ItemStack sign = new ItemStack(Material.OAK_HANGING_SIGN);
        makeItem.setItemMeta(sign,"現在1ページ目",null, null, PdcManager.PAGE,"1");
        playerInventory.setItem(2, sign);

        ItemStack bar = new ItemStack(Material.BARRIER);
        makeItem.setItemMeta(bar,"§4しゅうりょう",null, null, PdcManager.BUTTONID,"CLOSE");
        playerInventory.setItem(8, bar);

        String[] whiteName = {"休符","null","ド/C","レ/D","ミ/E","ファ/F","ソ/G","ラ/A","シ/B"};
        for (int i = 0; i < whiteName.length ; i++) {
            if (!whiteName[i].equals("null")) {
                ItemStack white = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
                makeItem.setItemMeta(white, whiteName[i],null, null, PdcManager.BUTTONID,whiteName[i]);
                playerInventory.setItem(i + 26, white);
            }
        }

        String[] blackName = {"ド#/C#","レ#/D#","null","ファ#/F#","ソ#/G#","ラ#/A#"};
        for (int i = 0; i < blackName.length ; i++) {
            if (!blackName[i].equals("null")) {
                ItemStack black = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                makeItem.setItemMeta(black, blackName[i],null, null, PdcManager.BUTTONID,blackName[i]);
                playerInventory.setItem(i + 19, black);
            }
        }

        ItemStack structure = new ItemStack(Material.STRUCTURE_VOID);
        makeItem.setItemMeta(structure,"全削除",null, null, PdcManager.BUTTONID,"ALL DELETE");
        playerInventory.setItem(6, structure);
    }
    public void open(Player player){
        InventoryManager inventoryManager = new InventoryManager();
        inventoryManager.seaveInventory(player);
        player.getInventory().clear();
        inputGuiUpdate(player, this.inv,0,1);
        player.getInventory().setContents(playerInventory.getContents());
        player.openInventory(this.inv);
    }
    public void inputGuiUpdate(Player player , Inventory plInv, Integer topNote, Integer page) {
        MakeItem makeItem = new MakeItem();
        MusicManager music = new MusicManager();
        int[] musicList = music.loadMusic(player);
        int noteId;

        if ( topNote == null || musicList == null) {
            return;
        }
        if (plInv.getHolder() == getInventory().getHolder()) {
            ItemStack paper = new ItemStack(Material.PAPER);

            for (int i = 0 ; i <= 53 ; i++) {
                makeItem.setItemMeta(paper,"",null, "note_def",null,null);
                this.inv.setItem(i, paper);
            }
            for (int i = 0 ; i <= 5 ; i++) {
                noteId = topNote + i;
                paper.setAmount((int) (7 - (noteId + 2)/6));
                while (noteId>=7)noteId -= 6;
                makeItem.setItemMeta(paper,String.valueOf(topNote) + "/" + String.valueOf(noteId),null, "key_board_" + String.valueOf(noteId), PdcManager.TOPNOTE,String.valueOf(topNote));
                this.inv.setItem(i * 9, paper);
            }
            paper.setAmount(1);
            for(int i = 0 ; i < 8 ; i++){
                int note = musicList[i+((page -1)*8)];
                if (note != 0){
                    if (note % 2 == 1){
                        makeItem.setItemMeta(paper,"",null, "note_up",null,null);
                    } else {
                        makeItem.setItemMeta(paper,"",null, "note_dw",null,null);
                    }
                    if(note > topNote *2 && note <= (topNote + 6)*2){

                        note -= topNote * 2;
                        if (note == 25)note -= 24;
                        else if (note >= 13)note -= 12;
                        if(note % 2 == 1){
                            note += 1;
                        }
                        this.inv.setItem(i +1+ (9 * note / 2 - 9), paper);
                    }
                }
            }
        }
    }
    public void jumpPage(int page,int topNote,Player player){
        ItemStack pageItem = player.getInventory().getItem(2);
        if (page < 1 || page > Itokagimaru_daw.MAXPAGE) return;
        MakeItem makeItem = new MakeItem();
        makeItem.setItemMeta(pageItem,"現在" + String.valueOf(page) + "ページ目",null, null, PdcManager.PAGE,String.valueOf(page));
        inputGuiUpdate(player, inv, topNote, page);
    }
    public void setMusicEndpoint(Player player) {
        MusicManager music = new MusicManager();
        int[] loded_music = music.loadMusic(player);
        for (int i = 0;i < loded_music.length;i++) {//エンドポイントの削除
            if  (loded_music[i] == -1) loded_music[i] = 0;
        }
        int music_end_point = 0;
        for (int i = loded_music.length; i > 0 ; i--) {//エンドポイントの追加
            if (loded_music[i -1] != 0) {
                music_end_point = i;
                break;
            }
        }
        if(!(music_end_point>= loded_music.length)) loded_music[music_end_point] = -1;
        music.saveMusic(player, loded_music);
    }
    public int getMusicEndPage(Player player) {
        MusicManager music = new MusicManager();
        int[] loded_music = music.loadMusic(player);
        int endPoint = 0;
        for (int i = 0;i < loded_music.length;i++) {//エンドポイントの削除
            if  (loded_music[i] == -1) endPoint = i;
        }
        int endPage = endPoint/8 + 1;
        return endPage;
    }
    @Override
    public void onClick(InventoryClickEvent event) {
        event.setCancelled(true);
        ItemStack clicked = event.getCurrentItem();
        if (clicked == null || clicked.getType() == Material.AIR) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        ItemMeta meta = clicked.getItemMeta();
        Inventory clicked_inv = event.getClickedInventory();
        Inventory pl_inv = player.getInventory();
        PdcManager.GetPDC getPdc = new PdcManager.GetPDC();
        if (Objects.equals(getPdc.buttonId(clicked),"CLOSE")) {
            player.closeInventory();
        } else if (clicked.getType() == Material.WHITE_STAINED_GLASS_PANE) {
            MusicManager musicManager = new MusicManager();
            int[] lodedMusic = musicManager.loadMusic(player);
            ItemStack page_item = clicked_inv.getItem(2);
            int page = getPdc.page(page_item);
            ItemStack topnote_item = player.getOpenInventory().getTopInventory().getItem(0);
            int topnote = getPdc.topnote(topnote_item);
            int select = 1;
            int add = 0;
            for (int i = 1; i <= 8; i++) {
                ItemStack select_item = clicked_inv.getItem(i + 9);
                if (Objects.equals(getPdc.buttonId(select_item),"SELECTED")) {
                    select = i;
                }
            }
            if (Objects.equals(getPdc.buttonId(clicked),"ド/C")) {
                add = Math.min((topnote + 2) / 6, 5);
                lodedMusic[(page - 1) * 8 + select - 1] = 8 + (12 * add);
            }
            if (Objects.equals(getPdc.buttonId(clicked),"レ/D")) {
                add = Math.min((topnote + 3) / 6, 5);
                lodedMusic[(page - 1) * 8 + select - 1] = 6 + (12 * add);
            }
            if (Objects.equals(getPdc.buttonId(clicked),"ミ/E")) {
                add = Math.min((topnote + 4) / 6, 5);
                lodedMusic[(page - 1) * 8 + select - 1] = 4 + (12 * add);
            }
            if (Objects.equals(getPdc.buttonId(clicked),"ファ/F")) {
                add = Math.min((topnote + 4) / 6, 5);
                lodedMusic[(page - 1) * 8 + select - 1] = 3 + (12 * add);

            }
            if (Objects.equals(getPdc.buttonId(clicked),"ソ/G")) {
                if (topnote != 0) {
                    add = Math.min((topnote - 1) / 6, 5);
                    lodedMusic[(page - 1) * 8 + select - 1] = 13 + add * 12;
                }
            }
            if (Objects.equals(getPdc.buttonId(clicked),"ラ/A")) {
                add = Math.min((topnote) / 6, 5);
                lodedMusic[(page - 1) * 8 + select - 1] = 11 + (12 * add);
            }
            if (Objects.equals(getPdc.buttonId(clicked),"シ/B")) {
                add = Math.min((topnote + 1) / 6, 5);
                lodedMusic[(page - 1) * 8 + select - 1] = 9 + (12 * add);
            }
            if (Objects.equals(getPdc.buttonId(clicked),"休符"))
                lodedMusic[(page - 1) * 8 + select - 1] = 0;
            //"ド/C","レ/D","ミ/E","ファ/F","ソ/G","ラ/A","シ/B"


            musicManager.saveMusic(player, lodedMusic.clone());
            inputGuiUpdate(player, event.getInventory(), topnote, page);

            MakeItem makeItem = new MakeItem();
            ItemStack update_select = new ItemStack(Material.PAPER);
            makeItem.setItemMeta(update_select, "カーソルを移動", null, "next_b_up", PdcManager.BUTTONID, "SELECT");
            player.getInventory().setItem(select + 9, update_select);

            if (select >= 8) select = 0;
            select++;
            makeItem.setItemMeta(update_select, "選択中", null, "select", PdcManager.BUTTONID, "SELECTED");
            player.getInventory().setItem(select + 9, update_select);
        } else if (clicked.getType() == Material.BLACK_STAINED_GLASS_PANE) {
                MusicManager musicManager = new MusicManager();
                int[] lodedmusic = musicManager.loadMusic(player);
                ItemStack page_item = clicked_inv.getItem(2);
                int page = getPdc.page(page_item);
                ItemStack topnote_item = player.getOpenInventory().getTopInventory().getItem(0);
                int topnote = getPdc.topnote(topnote_item);
                int add = 0;
                int select = 1;
                for (int i = 1; i <= 8; i++) {
                    ItemStack select_item = clicked_inv.getItem(i + 9);
                    if (Objects.requireNonNull(select_item).getType() == Material.PAPER && Objects.requireNonNull(select_item.getItemMeta().getItemModel()).equals(NamespacedKey.minecraft("select"))) {
                        select = i;
                    }
                }
                if (Objects.equals(getPdc.buttonId(clicked),"ド#/C#")){
                    add = Math.min((topnote + 2) / 6, 5);
                    lodedmusic[(page - 1) * 8 + select - 1] = 7 + (12*add);
                }

                if (Objects.equals(getPdc.buttonId(clicked),"レ#/D#")){
                    add = Math.min((topnote + 3) / 6, 5);
                    lodedmusic[(page - 1) * 8 + select - 1] = 5 + (12*add);
                }
                if (Objects.equals(getPdc.buttonId(clicked),"ファ#/F#")){
                    add = Math.min((topnote + 5) / 6, 6);
                    if (topnote == 0) add = 0;

                    lodedmusic[(page - 1) * 8 + select - 1] = 2 + (12*add);
                }
                if (Objects.equals(getPdc.buttonId(clicked),"ソ#/G#")){
                    add = Math.min((topnote) / 6, 5);
                    lodedmusic[(page - 1) * 8 + select - 1] = 12 + (12*add);

                }
                if (Objects.equals(getPdc.buttonId(clicked),"ラ#/A#")){
                    add = Math.min((topnote + 1) / 6, 5);
                    lodedmusic[(page - 1) * 8 + select - 1] = 10 + (12*add);
                }
                musicManager.saveMusic(player, lodedmusic.clone());
                inputGuiUpdate(player, event.getInventory(), topnote, page);
                MakeItem makeItem = new MakeItem();
                ItemStack update_select = new ItemStack(Material.PAPER);
                makeItem.setItemMeta(update_select, "カーソルを移動", null, "next_b_up", PdcManager.BUTTONID, "SELECT");
                player.getInventory().setItem(select + 9, update_select);
                if (select >= 8) select = 0;
                select++;
                makeItem.setItemMeta(update_select, "選択中", null, "select", PdcManager.BUTTONID, "SELECTED");
                player.getInventory().setItem(select + 9, update_select);


            } else if (clicked.getType() == Material.PAPER) {
                if (Objects.equals(getPdc.buttonId(clicked),"UP NOTE")) {

                    ItemStack topnote_item = player.getOpenInventory().getTopInventory().getItem(0);

                    int topnote = getPdc.topnote(topnote_item);

                    ItemStack page_item = clicked_inv.getItem(2);
                    int tag_int = getPdc.page(page_item);
                    if (topnote <=0) topnote += 1;
                    inputGuiUpdate(player, event.getInventory(), topnote-1, tag_int);
                } else if (Objects.equals(getPdc.buttonId(clicked),"DOWN NOTE")) {
                    ItemStack page_item = clicked_inv.getItem(2);
                    int tag_int = getPdc.page(page_item);

                    ItemStack topnote_item = player.getOpenInventory().getTopInventory().getItem(0);
                    int topnote = getPdc.topnote(topnote_item);
                    if (topnote >=31) topnote -= 1;
                    inputGuiUpdate(player, event.getInventory(), topnote+1, tag_int);

                } else if (Objects.equals(getPdc.buttonId(clicked),"SELECT")) {
                    MakeItem makeItem = new MakeItem();
                    makeItem.setItemMeta(clicked,"選択中",null, "select", PdcManager.BUTTONID,"SELECTED");
                    ItemStack paper = new ItemStack(Material.PAPER);
                    int slot = event.getRawSlot() - 55;
                    for (int i = 0; i <= 7; i++) {
                        if (i != slot) {
                            makeItem.setItemMeta(paper,"カーソルを移動",null, "next_b_up", PdcManager.BUTTONID,"SELECT");
                            player.getInventory().setItem(10 + i, paper);
                        }
                    }
                } else if (Objects.equals(getPdc.buttonId(clicked),"NEXT PAGE")) {

                    ItemStack page_item = Objects.requireNonNull(clicked_inv).getItem(2);
                    if (clicked_inv == pl_inv) {
                        ItemStack topnote_item = player.getOpenInventory().getTopInventory().getItem(0);
                        int topnote = getPdc.topnote(topnote_item);
                        int page = getPdc.page(Objects.requireNonNull(page_item));
                        jumpPage(page + 1,topnote,player);

                        ItemStack paper = new ItemStack(Material.PAPER);
                        MakeItem makeItem = new MakeItem();
                        makeItem.setItemMeta(paper, "選択中", null, "select", PdcManager.BUTTONID, "SELECTED");
                        player.getInventory().setItem(10, paper);
                        for (int i = 1; i <= 7; i++) {
                            makeItem.setItemMeta(paper, "カーソルを移動", null, "next_b_up", PdcManager.BUTTONID, "SELECT");
                            player.getInventory().setItem(10 + i, paper);
                        }
                    }

                } else if (Objects.equals(getPdc.buttonId(clicked),"BACK PAGE")) {

                    ItemStack page_item = clicked_inv.getItem(2);
                    ItemStack topnote_item = player.getOpenInventory().getTopInventory().getItem(0);
                    int topnote = getPdc.topnote(topnote_item);
                    int page = getPdc.page(Objects.requireNonNull(page_item));
                    jumpPage(page - 1,topnote,player);
                    ItemStack paper = new ItemStack(Material.PAPER);
                    MakeItem makeItem = new MakeItem();
                    makeItem.setItemMeta(paper, "選択中", null, "select", PdcManager.BUTTONID, "SELECTED");
                    player.getInventory().setItem(10, paper);
                    for (int i = 1; i <= 7; i++) {
                        makeItem.setItemMeta(paper, "カーソルを移動", null, "next_b_up", PdcManager.BUTTONID, "SELECT");
                        player.getInventory().setItem(10 + i, paper);
                    }

                } else if (Objects.equals(getPdc.buttonId(clicked),"GO FIRST")) {
                    ItemStack topnote_item = player.getOpenInventory().getTopInventory().getItem(0);
                    int topnote = getPdc.topnote(topnote_item);
                    jumpPage(1,topnote,player);
                    ItemStack paper = new ItemStack(Material.PAPER);
                    MakeItem makeItem = new MakeItem();
                    makeItem.setItemMeta(paper, "選択中", null, "select", PdcManager.BUTTONID, "SELECTED");
                    player.getInventory().setItem(10, paper);
                    for (int i = 1; i <= 7; i++) {
                        makeItem.setItemMeta(paper, "カーソルを移動", null, "next_b_up", PdcManager.BUTTONID, "SELECT");
                        player.getInventory().setItem(10 + i, paper);
                    }
                } else if (Objects.equals(getPdc.buttonId(clicked),"GO END")) {
                    ItemStack topnote_item = player.getOpenInventory().getTopInventory().getItem(0);
                    int topnote = getPdc.topnote(topnote_item);
                    setMusicEndpoint(player);
                    int endPage = getMusicEndPage(player);
                    jumpPage(endPage,topnote,player);
                    ItemStack paper = new ItemStack(Material.PAPER);
                    MakeItem makeItem = new MakeItem();
                    makeItem.setItemMeta(paper, "選択中", null, "select", PdcManager.BUTTONID, "SELECTED");
                    player.getInventory().setItem(10, paper);
                    for (int i = 1; i <= 7; i++) {
                        makeItem.setItemMeta(paper, "カーソルを移動", null, "next_b_up", PdcManager.BUTTONID, "SELECT");
                        player.getInventory().setItem(10 + i, paper);
                    }
                }
            } else if (clicked.getType() == Material.STRUCTURE_VOID && Objects.requireNonNull(meta.displayName()).equals(Component.text("全削除"))) {
                int[] reset = new int[256];
                Arrays.fill(reset, 0);
                MusicManager musicManager = new MusicManager();
                musicManager.saveMusic(player,reset);

                ItemStack page_item = clicked_inv.getItem(2);
                Integer tag_int = getPdc.page(page_item);

                ItemStack topnote_item = player.getOpenInventory().getTopInventory().getItem(0);
                int topnote = getPdc.topnote(topnote_item);
                inputGuiUpdate(player, event.getInventory(), topnote, tag_int);
            } else{
                return;
            }
    }
    @Override
    public void onClose(Player player) {
        setMusicEndpoint(player);
        InventoryManager inventory_lode = new InventoryManager();
        inventory_lode.loadInventory(player);
    }
}
