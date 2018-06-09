/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laptrinhjava.Scene;

import java.awt.Graphics;
import laptrinhjava.ImageDraw;
import laptrinhjava.GButton;
import UI.BottomMenu;
import UI.ItemSlot;
import Utility.Int2;
import Component.GGrid;
import Component.ItemCarrier;
import java.util.ArrayList;
import java.util.List;
import Component.ItemCarrier;
import Component.ItemComponent;
import laptrinhjava.Game;
import GameObject.Player;
import Component.NumberComponent;
import Component.StatsComponent;
import Utility.ItemManager;
/**
 *
 * @author Nhan
 */
public class InventoryScene extends Scene {
    
    GButton exitButton;
    public static List<ItemSlot> listItemInvenSlot = new ArrayList<ItemSlot>();
    private ItemCarrier itemCarrier;
    ItemComponent colorPoition;
    ItemComponent colorPoition2;
    ItemComponent testItem;
    public ItemSlot weapon;
    public ItemSlot shield;
    public ItemSlot armor;
    public ItemSlot amulet;
    Player player;
    //Character Stat Display
    NumberComponent Att;
    NumberComponent Def;
    NumberComponent Crit;
    NumberComponent BreArmor;
    NumberComponent Block;
    ImageDraw icoAttack;
    ImageDraw icoDef;
    ImageDraw icoCrit;
    ImageDraw icoBreak;
    ImageDraw icoBlock;
    //ItemStatDisplay
    ImageDraw selectItemImage;
    StatsComponent selectItemStats;
    String selectItemName;
    NumberComponent IHp;
    NumberComponent IAtt;
    NumberComponent IDef;
    NumberComponent ICrit;
    NumberComponent IBreArmor;
    NumberComponent IBlock;
    ImageDraw IicoHp;
    ImageDraw IicoAttack;
    ImageDraw IicoDef;
    ImageDraw IicoCrit;
    ImageDraw IicoBreak;
    ImageDraw IicoBlock;
    
    
    public InventoryScene()
    {
        player = Player.getInst();
        backGround = new ImageDraw("src/Resources/InventoryBackground.png",0,0,WidthSize,HeightSize);
        id = Scene.eSceneId.S_inventory;
        exitButton = new GButton("src/Resources/ExitInventory.png",750,551, 50,50);
        exitButton.debugid = 2;
        for (int i = 0 ;  i < 6; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                ItemSlot item = new ItemSlot();
                item.setPosition(new Int2(160 + i * 106, 206 + j*86));
                item.setSpecialTag(ItemComponent.eItemTag.IT_none);
                listItemInvenSlot.add(item);
            }
        }
        itemCarrier = ItemCarrier.getInst();
        colorPoition = new ItemComponent("Color Poition", "src/Resources/ColorPoition.png", ItemComponent.eItemTag.IT_amulet);
        colorPoition2 = new ItemComponent("Color Poition2", "src/Resources/ColorPoition2.png", ItemComponent.eItemTag.IT_shield);
        listItemInvenSlot.get(0).pushItem(colorPoition);
        listItemInvenSlot.get(1).pushItem(colorPoition2);
        //TestItem De Xem chi so o day///////////////////////////////////////////////////////
        //TestItem De Xem chi so o day///////////////////////////////////////////////////////
        //TestItem De Xem chi so o day///////////////////////////////////////////////////////
        testItem = ItemManager.getInstance().createItem("sword");
        listItemInvenSlot.get(2).pushItem(testItem);
        //Equipment Slot
        weapon = new ItemSlot();
        weapon.setPosition(new Int2(94,108));
        weapon.setSpecialTag(ItemComponent.eItemTag.IT_weapon);
        shield = new ItemSlot();
        shield.setPosition(new Int2(270,108));
        shield.setSpecialTag(ItemComponent.eItemTag.IT_shield);
        armor = new ItemSlot();
        armor.setPosition(new Int2(446, 108));
        armor.setSpecialTag(ItemComponent.eItemTag.IT_armor);
        amulet = new ItemSlot();
        amulet.setPosition(new Int2(622,108));
        amulet.setSpecialTag(ItemComponent.eItemTag.IT_amulet);
        //Stat Display
        InitializeStatDisplay();
        InitializeItemStatsDisplay();
    }
    
    public boolean isActive()
    {
        
        return bActive;
    }
    
    public void active()
    {        
        bActive = true;
        Att.bActive = true;
        Def.bActive = true;
        Crit.bActive = true;
        BreArmor.bActive = true;
        Block.bActive = true;
        exitButton.active();
        System.out.println("Inventory Scene Actived");
        for (ItemSlot l : listItemInvenSlot)
        {
             l.active();
        }
        //Equipment Slot
        weapon.active();
        armor.active();
        shield.active();
        amulet.active();
    }
    
    public void deActive()
    {
        bActive = false;
        Att.bActive = false;
        Def.bActive = false;
        Crit.bActive = false;
        BreArmor.bActive = false;
        Block.bActive = false;
        exitButton.deActive();
        System.out.println(exitButton.isActive());
        for (ItemSlot l : listItemInvenSlot)
        {
            l.deActive();
        }
        weapon.deActive();
        armor.deActive();
        shield.deActive();
        amulet.deActive();
    }
    
    public void destroy()
    {
        exitButton.deleteButton();
    }
    
    
    public void Update (float dt)
    {
        if (!bActive)
            return;
        
        Att.updateText(Integer.toString((int)player.getCharacter().getStatsComponent().getDamage()));
        Def.updateText(Integer.toString((int)player.getCharacter().getStatsComponent().getArmor()));
        Crit.updateText(Integer.toString((int)player.getCharacter().getStatsComponent().getCriticalChance()));
        BreArmor.updateText(Integer.toString((int)player.getCharacter().getStatsComponent().getBreakArmor()));
        Block.updateText(Integer.toString((int)player.getCharacter().getStatsComponent().getBlockChance()));
        
        UpdateItemStatsDisplay();
        
        for (ItemSlot l : listItemInvenSlot)
        {
            l.Update();
            
            if (l.getButton().IsClicked())
            {        
                if (l.isHasItem())
                {
                    ItemComponent temp = new ItemComponent(l.item.getName(),l.item.getImage().getPath(),l.item.getTag());
    
                    selectItemImage = temp.getImage();   
                    selectItemImage.setPosition(20, 220);
                    selectItemStats = l.item.getStat();
                    selectItemName = l.item.getName();
                }
                if (!ItemCarrier.getInst().isHastItem())
                {
                    System.out.println("ItemSlot Clicked");               
                    l.popItem(ItemCarrier.getInst());
                }
            }
        }
        weapon.Update();
        armor.Update();
        shield.Update();
        amulet.Update();
        
        itemCarrier.getEventReleased(Game.bMouseReleased);
        itemCarrier.getMousePos(Game.mousePos);
        itemCarrier.Update();
        
        
        if (exitButton.IsClicked())
        {
            //SceneManager.getInstance().setCurrentScene(Scene.eSceneId.S_village);
            SceneManager.getInstance().setCurrentScene(SceneManager.getInstance().previousScene);
            System.out.println("Exit Inventory Button Pressed");
            BottomMenu.getInst().active();
            GGrid.getInst().active();
            System.out.println("X button deactive");
            this.deActive();
        }
    }
    
    public void Draw (Graphics g)
    {
        if (!bActive)
            return;
        backGround.Draw(g);
        IconStatsDrawing(g);
        ItemIconStatsDrawing(g);
        for (ItemSlot l : listItemInvenSlot)
        {
            l.Draw(g);
        }
        weapon.Draw(g);
        armor.Draw(g);
        shield.Draw(g);
        amulet.Draw(g);
        
        itemCarrier.Draw(g);
        exitButton.drawButton(g);
    }
    
    private void InitializeStatDisplay()
    {
        Att = NumberComponent.createNumber( Integer.toString((int)player.getCharacter().getStatsComponent().getDamage()), 450, 20, 1,1);
        Def = NumberComponent.createNumber( Integer.toString((int)player.getCharacter().getStatsComponent().getArmor()), 450, 60);
        Crit = NumberComponent.createNumber( Integer.toString((int)player.getCharacter().getStatsComponent().getCriticalChance()), 550, 20);
        BreArmor = NumberComponent.createNumber( Integer.toString((int)player.getCharacter().getStatsComponent().getBreakArmor()), 550, 60);
        Block = NumberComponent.createNumber( Integer.toString((int)player.getCharacter().getStatsComponent().getBlockChance()), 650, 20);
        icoAttack = new ImageDraw("src/Resources/icoAttack.png", 420,20,25,25);
        icoDef = new ImageDraw("src/Resources/icoDef.png", 420,60,25,25);
        icoCrit = new ImageDraw("src/Resources/icoCrit.png", 520,20,25,25);
        icoBreak = new ImageDraw("src/Resources/icoBreak.png", 520,60,25,25);
        icoBlock = new ImageDraw("src/Resources/icoBlock.png", 620,20,25,25);
    }
    
    private void IconStatsDrawing(Graphics g)
    {
        icoAttack.Draw(g);
        icoDef.Draw(g);
        icoCrit.Draw(g);
        icoBreak.Draw(g);
        icoBlock.Draw(g);
    }
    
    private void InitializeItemStatsDisplay()
    {                   
        IHp = NumberComponent.createNumber("0", 60, 310, 1,1);
        IAtt = NumberComponent.createNumber("0", 60, 350, 1,1);
        IDef = NumberComponent.createNumber("0", 60, 390);
        ICrit = NumberComponent.createNumber("0", 60, 430);
        IBreArmor = NumberComponent.createNumber("0", 60, 470);
        IBlock = NumberComponent.createNumber("0", 60, 510);
        IicoHp = new ImageDraw("src/Resources/icoHp.png", 30,310,25,25);
        IicoAttack = new ImageDraw("src/Resources/icoAttack.png", 30,350,25,25);
        IicoDef = new ImageDraw("src/Resources/icoDef.png", 30,390,25,25);
        IicoCrit = new ImageDraw("src/Resources/icoCrit.png", 30,430,25,25);
        IicoBreak = new ImageDraw("src/Resources/icoBreak.png", 30,470,25,25);
        IicoBlock = new ImageDraw("src/Resources/icoBlock.png", 30,510,25,25);
    }
    
    private void UpdateItemStatsDisplay()
    {
        if (selectItemStats == null)
            return;
        IHp.updateText(Integer.toString((int)selectItemStats.getHp()));
        IAtt.updateText(Integer.toString((int)selectItemStats.getDamage()));
        IDef.updateText(Integer.toString((int)selectItemStats.getArmor()));
        ICrit.updateText(Integer.toString((int)selectItemStats.getCriticalChance()));
        IBreArmor.updateText(Integer.toString((int)selectItemStats.getBreakArmor()));
        IBlock.updateText(Integer.toString((int)selectItemStats.getBlockChance()));
        
    }
    
    private void ItemIconStatsDrawing(Graphics g)
    {
        if (selectItemImage != null)
        {
            selectItemImage.Draw(g);
        }
        IicoHp.Draw(g);
        IicoAttack.Draw(g);
        IicoDef.Draw(g);
        IicoCrit.Draw(g);
        IicoBreak.Draw(g);
        IicoBlock.Draw(g);
    }
}

