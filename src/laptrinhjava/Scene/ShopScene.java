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
import java.util.ArrayList;
import java.util.List;
import Component.ItemCarrier;
import Component.ItemComponent;
import laptrinhjava.Game;
import GameObject.Player;
import Component.NumberComponent;
import Component.StatsComponent;
import Utility.ItemManager;
import Utility.ItemData;
import java.util.Random;

/**
 *
 * @author Nhan
 */
public class ShopScene extends Scene {
    
    private static ShopScene inst;
    public static ShopScene getInstance()
    {
        if (inst == null)
            inst = new ShopScene();
        return inst;
    }
    
    Random rd;
    GButton exitButton;
    public static List<ItemSlot> listItemInvenSlot = new ArrayList<ItemSlot>();
    private ItemCarrier itemCarrier;
    Player player;
    InventoryScene inven;
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
    
    //Shop
    public ItemSlot case1;
    public ItemSlot case2;
    public ItemSlot case3;
    public ItemSlot case4;
    int caseCount = 1;
    boolean stopGenerate = false;
    
    
    public ShopScene()
    {
        player = Player.getInst();
        rd = new Random();
        inven = InventoryScene.getInstance();
        inven.deActive();
        backGround = new ImageDraw("src/Resources/InventoryBackground.png",0,0,WidthSize,HeightSize);
        id = Scene.eSceneId.S_Shop;
        exitButton = new GButton("src/Resources/ExitInventory.png",750,551, 50,50);
        listItemInvenSlot = InventoryScene.listItemInvenSlot;
        inven.ItemSlotActive();
        itemCarrier = ItemCarrier.getInst();
        
        case1 = new ItemSlot();
        case1.setPosition(new Int2(94,108));
        case1.setSpecialTag(ItemComponent.eItemTag.IT_none);
        case2 = new ItemSlot();
        case2.setPosition(new Int2(270,108));
        case2.setSpecialTag(ItemComponent.eItemTag.IT_none);
        case3 = new ItemSlot();
        case3.setPosition(new Int2(446, 108));
        case3.setSpecialTag(ItemComponent.eItemTag.IT_none);
        case4 = new ItemSlot();
        case4.setPosition(new Int2(622,108));
        case4.setSpecialTag(ItemComponent.eItemTag.IT_none);
        
        //Stat Display
        InitializeStatDisplay();
        InitializeItemStatsDisplay();
        
        GenerateItem(1);
        if (inst == null)
            inst = this;
    }
    
    public boolean isActive()
    {
        
        return bActive;
    }
    
    public void active()
    {        
        GenerateItem(1);
        bActive = true;
        activeStat();
        activeItemStat();
        exitButton.active();
        System.out.println("Inventory Scene Actived");
        ItemSlotActive();
        case1.active();
        case2.active();
        case3.active();
        case4.active();
    }
    
    public void deActive()
    {
        bActive = false;
        deActiveStat();
        deActiveItemStat();
        exitButton.deActive();
        System.out.println(exitButton.isActive());
        ItemSlotDeActive();
        case1.deActive();
        case2.deActive();
        case3.deActive();
        case4.deActive();
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
        
        UpdateCaseItem();
        
        ItemCarrierUpdating();
        
        
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
        ItemSlotDrawing(g);
        case1.Draw(g);
        case2.Draw(g);
        case3.Draw(g);
        case4.Draw(g);
        
        ItemCarrierDrawing(g);
        exitButton.drawButton(g);
    }
    
    public void ItemCarrierDrawing(Graphics g)
    {
        itemCarrier.Draw(g);
    }
    
    public void ItemCarrierUpdating()
    {
        itemCarrier.getEventReleased(Game.bMouseReleased);
        itemCarrier.getMousePos(Game.mousePos);
        itemCarrier.Update();
    }
    
    public void ItemSlotDrawing(Graphics g)
    {
        for (ItemSlot l : listItemInvenSlot)
        {
            l.Draw(g);
        }
    }
    
    public void ItemSlotActive()
    {
        for (ItemSlot l : listItemInvenSlot)
        {
             l.active();
        }
    }
    public void ItemSlotDeActive()
    {
        for (ItemSlot l : listItemInvenSlot)
        {
             l.deActive();
        }
    }
    
    public void InitializeStatDisplay()
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
    
    public void activeStat()
    {
        Att.bActive = true;
        Def.bActive = true;
        Crit.bActive = true;
        BreArmor.bActive = true;
        Block.bActive = true;
    }
    
    public void deActiveStat()
    {
        Att.bActive = false;
        Def.bActive = false;
        Crit.bActive = false;
        BreArmor.bActive = false;
        Block.bActive = false;
    }
    
    public void IconStatsDrawing(Graphics g)
    {
        icoAttack.Draw(g);
        icoDef.Draw(g);
        icoCrit.Draw(g);
        icoBreak.Draw(g);
        icoBlock.Draw(g);
    }
    
    public void InitializeItemStatsDisplay()
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
    
    public void activeItemStat()
    {
        IHp.bActive = true;
        IAtt.bActive = true;
        IDef.bActive = true;
        ICrit.bActive = true;
        IBreArmor.bActive = true;
        IBlock.bActive = true;
    }
    
    public void deActiveItemStat()
    {
        IHp.bActive = false;
        IAtt.bActive = false;
        IDef.bActive = false;
        ICrit.bActive = false;
        IBreArmor.bActive = false;
        IBlock.bActive = false;
    }
    
    public void UpdateItemStatsDisplay()
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
    
    public void ItemIconStatsDrawing(Graphics g)
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
    
    public void UpdateCaseItem()
    {
//        case1.Update();
//        case2.Update();
//        case3.Update();
//        case4.Update();
        
        if (case1.getButton().IsClicked())
            {        
                if (case1.isHasItem())
                {
                    ItemComponent temp = new ItemComponent(case1.item.getName(),case1.item.getImage().getPath(),case1.item.getTag());
    
                    selectItemImage = temp.getImage();   
                    selectItemImage.setPosition(20, 220);
                    selectItemStats = case1.item.getStat();
                    selectItemName = case1.item.getName();
                }
                if (!ItemCarrier.getInst().isHastItem())
                {
                    System.out.println("ItemSlot Clicked");               
                    case1.popItem(ItemCarrier.getInst());
                }
            }
        
        if (case2.getButton().IsClicked())
            {        
                if (case2.isHasItem())
                {
                    ItemComponent temp = new ItemComponent(case2.item.getName(),case2.item.getImage().getPath(),case2.item.getTag());
    
                    selectItemImage = temp.getImage();   
                    selectItemImage.setPosition(20, 220);
                    selectItemStats = case2.item.getStat();
                    selectItemName = case2.item.getName();
                }
                if (!ItemCarrier.getInst().isHastItem())
                {
                    System.out.println("ItemSlot Clicked");               
                    case2.popItem(ItemCarrier.getInst());
                }
            }
        
        if (case3.getButton().IsClicked())
            {        
                if (case3.isHasItem())
                {
                    ItemComponent temp = new ItemComponent(case3.item.getName(),case3.item.getImage().getPath(),case3.item.getTag());
    
                    selectItemImage = temp.getImage();   
                    selectItemImage.setPosition(20, 220);
                    selectItemStats = case3.item.getStat();
                    selectItemName = case3.item.getName();
                }
                if (!ItemCarrier.getInst().isHastItem())
                {
                    System.out.println("ItemSlot Clicked");               
                    case3.popItem(ItemCarrier.getInst());
                }
            }
        
        if (case4.getButton().IsClicked())
            {        
                if (case4.isHasItem())
                {
                    ItemComponent temp = new ItemComponent(case4.item.getName(),case4.item.getImage().getPath(),case4.item.getTag());
    
                    selectItemImage = temp.getImage();   
                    selectItemImage.setPosition(20, 220);
                    selectItemStats = case4.item.getStat();
                    selectItemName = case4.item.getName();
                }
                if (!ItemCarrier.getInst().isHastItem())
                {
                    System.out.println("ItemSlot Clicked");               
                    case4.popItem(ItemCarrier.getInst());
                }
            }
    }
    
    public void GenerateItem(int seed)
    {
        stopGenerate = false;
        clearCaseItem();
        while (!stopGenerate)
        {
            switch (seed)
            {
                case 1:
                    RandomItem("sword",25);
                    RandomItem("shield",25);
                    RandomItem("sword 2",25);
                    RandomItem("shield 2",25);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7: 
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
            }
        }
        
    }
    
    public boolean RandomItem(String name, int percent)
    {
        int temp = rd.nextInt(101);
        if (temp > percent)
            return false;
        if (caseCount > 4)
        {
            stopGenerate = true;
            caseCount = 1;
            return true;
        }
        ItemComponent item;
        switch(caseCount)
        {
            case 1:
                item = ItemManager.getInstance().createItem(name);
                if (item == null)
                {
                    System.out.println("Wrong name Item     call From: ShopScene.RandomItem");
                    stopGenerate = true;
                }
               
                case1.pushItem(item);                  
                break;
            case 2:
                item = ItemManager.getInstance().createItem(name);
                if (item == null)
                {
                    System.out.println("Wrong name Item     call From: ShopScene.RandomItem");
                    stopGenerate = true;
                }
                case2.pushItem(item);
                break;
            case 3:
                item = ItemManager.getInstance().createItem(name);
                if (item == null)
                {
                    System.out.println("Wrong name Item     call From: ShopScene.RandomItem");
                    stopGenerate = true;
                }
                case3.pushItem(item);
                break;
            case 4:
                item = ItemManager.getInstance().createItem(name);
                if (item == null)
                {
                    System.out.println("Wrong name Item     call From: ShopScene.RandomItem");
                    stopGenerate = true;
                }
                case4.pushItem(item);
                break;
        }
        caseCount++;
        return false;
    }
    
    public void clearCaseItem()
    {
        case1.RemoveItem();
        case2.RemoveItem();
        case3.RemoveItem();
        case4.RemoveItem();
    }
    
    
    
    
    
}
