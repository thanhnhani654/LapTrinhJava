/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import laptrinhjava.GButton;
import java.awt.Graphics;
import laptrinhjava.Scene.Scene;
import laptrinhjava.Scene.SceneManager;
import Component.GGrid;
import Component.ItemComponent;
import GameObject.Player;
import laptrinhjava.Scene.InventoryScene;
import laptrinhjava.ImageDraw;
import Component.TurnBaseSystem;
/**
 *
 * @author Nhan
 */
public class BottomMenu {
    GButton spellSlot1;
    GButton spellSlot2;
    GButton spellSlot3;
    GButton itemSlot1;
    boolean bSlot1HasItem;
    GButton itemSlot2;
    boolean bSlot2HasItem;
    GButton itemSlot3;
    boolean bSlot3HasItem;
    GButton inventory;
    
    public static BottomMenu inst;
    
    boolean bActive = true;
    
    public static BottomMenu getInst()
    {
        if (inst == null)
        {
            inst = new BottomMenu();
        }
        return inst;
    }
    
    public BottomMenu()
    {
        spellSlot1 = new GButton("src/Resources/SkillSlot.png",30,510, 80,80);
        spellSlot2 = new GButton("src/Resources/SkillSlot.png",140,510, 80,80);
        spellSlot3 = new GButton("src/Resources/SkillSlot.png",250,510, 80,80);
        itemSlot1 = new GButton("src/Resources/ItemSlot.png",360,510, 80,80);
        itemSlot2 = new GButton("src/Resources/ItemSlot.png",470,510, 80,80);
        itemSlot3 = new GButton("src/Resources/ItemSlot.png",580,510, 80,80);
        inventory = new GButton("src/Resources/Inventory.png",690,510, 80,80);
    }
    
    public void Update()
    {
        if (inventory.IsClicked())
        {
            SceneManager.getInstance().changeToSubScene(Scene.eSceneId.S_inventory);
            this.deActive();
//            if (SceneManager.getInstance().getScene(Scene.eSceneId.S_inventory) != null)
//            {
//                SceneManager.getInstance().setCurrentScene(Scene.eSceneId.S_inventory);
//                InventoryScene temp = (InventoryScene)SceneManager.getInstance().getScene(Scene.eSceneId.S_inventory);
//                temp.active();
//                System.out.println("invetory Button Pressed1");
//                GGrid.getInst().deActive();
//                this.deActive();
//                return;
//            }
//            SceneManager.getInstance().createScene(Scene.eSceneId.S_inventory);
//            SceneManager.getInstance().setCurrentScene(Scene.eSceneId.S_inventory);
//            System.out.println("invetory Button Pressed");
//            GGrid.getInst().deActive();
//            this.deActive();
        }
        
        UpdateSpellSlot1();
        UpdateSpellSlot2();
        UpdateSpellSlot3();
        
        if (InventoryScene.listItemInvenSlot.isEmpty())
            return;
        
        UpdateSlot1();
        UpdateEventSlot1();
        UpdateSlot2();
        UpdateEventSlot2();
        UpdateSlot3();
        UpdateEventSlot3();
        
        
    }
    
    public void active()
    {
        spellSlot1.active();
        spellSlot2.active();
        spellSlot3.active();
        itemSlot1.active();
        itemSlot2.active();
        itemSlot3.active();
        inventory.active();
        bActive = true;
    }
    
    public void deActive()
    {
        spellSlot1.deActive();
        spellSlot2.deActive();
        spellSlot3.deActive();
        itemSlot1.deActive();
        itemSlot2.deActive();
        itemSlot3.deActive();
        inventory.deActive();
        bActive = false;
    }
    
    public boolean isActive()
    {
        return bActive;
    }
    
    public void Draw (Graphics g)
    {
        spellSlot1.drawButton(g);
        spellSlot2.drawButton(g);
        spellSlot3.drawButton(g);
        itemSlot1.drawButton(g);
        itemSlot2.drawButton(g);
        itemSlot3.drawButton(g);
        inventory.drawButton(g);
    }
    
    private void UpdateSlot1()
    {
        if (InventoryScene.listItemInvenSlot.get(0).item == null)
        {
            if (bSlot1HasItem)
            {
                bSlot1HasItem = false;
                itemSlot1.setImage(new ImageDraw("src/Resources/ItemSlot.png",360,510, 80,80));
            }
        }
        else
        {
            if (!InventoryScene.listItemInvenSlot.get(0).item.isHasTag(ItemComponent.eItemTag.IT_consumable))
            {
                if (bSlot1HasItem)
                {
                    bSlot1HasItem = false;
                    itemSlot1.setImage(new ImageDraw("src/Resources/ItemSlot.png",360,510, 80,80));
                }
                return;
            }
            if (!bSlot1HasItem)
            {
                bSlot1HasItem = true;
                itemSlot1.setImage(new ImageDraw(InventoryScene.listItemInvenSlot.get(0).item.getImage()));
            }
        }
    }
    private void UpdateSlot2()
    {
        if (InventoryScene.listItemInvenSlot.get(1).item == null)
        {
            if (bSlot2HasItem)
            {
                bSlot2HasItem = false;
                itemSlot2.setImage(new ImageDraw("src/Resources/ItemSlot.png",360,510, 80,80));
            }
        }
        else
        {
            if (!InventoryScene.listItemInvenSlot.get(1).item.isHasTag(ItemComponent.eItemTag.IT_consumable))
            {
                if (bSlot2HasItem)
                {
                    bSlot2HasItem = false;
                    itemSlot2.setImage(new ImageDraw("src/Resources/ItemSlot.png",360,510, 80,80));
                }
                return;
            }
            if (!bSlot2HasItem)
            {
                bSlot2HasItem = true;
                itemSlot2.setImage(new ImageDraw(InventoryScene.listItemInvenSlot.get(1).item.getImage()));
            }
        }
    }
    private void UpdateSlot3()
    {
        if (InventoryScene.listItemInvenSlot.get(2).item == null)
        {
            if (bSlot3HasItem)
            {
                bSlot3HasItem = false;
                itemSlot3.setImage(new ImageDraw("src/Resources/ItemSlot.png",360,510, 80,80));
            }
        }
        else
        {
            if (!InventoryScene.listItemInvenSlot.get(2).item.isHasTag(ItemComponent.eItemTag.IT_consumable))
            {
                if (bSlot3HasItem)
                {
                    bSlot3HasItem = false;
                    itemSlot3.setImage(new ImageDraw("src/Resources/ItemSlot.png",360,510, 80,80));
                }
                return;
            }
            if (!bSlot3HasItem)
            {
                bSlot3HasItem = true;
                itemSlot3.setImage(new ImageDraw(InventoryScene.listItemInvenSlot.get(2).item.getImage()));
            }
        }
    }
    private void UpdateEventSlot1()
    {
        if (!bSlot1HasItem)
            return;
        if (itemSlot1.IsClicked())
        {
            InventoryScene.listItemInvenSlot.get(0).item.use(Player.getInst().getCharacter());
            InventoryScene.listItemInvenSlot.get(0).amount -=1;
            if (InventoryScene.listItemInvenSlot.get(0).amount == 0)
                InventoryScene.listItemInvenSlot.get(0).item = null;
        }
    }
    private void UpdateEventSlot2()
    {
        if (!bSlot2HasItem)
            return;
        if (itemSlot2.IsClicked())
        {
            InventoryScene.listItemInvenSlot.get(1).item.use(Player.getInst().getCharacter());
            InventoryScene.listItemInvenSlot.get(1).amount -=1;
            if (InventoryScene.listItemInvenSlot.get(1).amount == 0)
                InventoryScene.listItemInvenSlot.get(1).item = null;
        }
    }
    private void UpdateEventSlot3()
    {
        if (!bSlot3HasItem)
            return;
        if (itemSlot3.IsClicked())
        {
            InventoryScene.listItemInvenSlot.get(2).item.use(Player.getInst().getCharacter());
            InventoryScene.listItemInvenSlot.get(2).amount -=1;
            if (InventoryScene.listItemInvenSlot.get(2).amount == 0)
                InventoryScene.listItemInvenSlot.get(2).item = null;
        }
    }
    private void UpdateSpellSlot1()
    {
        if (!Player.getInst().hero.turnbase.getKeyValue(Player.getInst().hero.key))
            return;
        if (spellSlot1.IsClicked() && !Player.getInst().hero.attackBuff)
        {
            Player.getInst().hero.getMpComponent().doDelta(-25);
            Player.getInst().hero.attackBuff = true;
        }
    }   
    private void UpdateSpellSlot2()
    {
        if (!Player.getInst().hero.turnbase.getKeyValue(Player.getInst().hero.key))
            return;
        if (spellSlot2.IsClicked() && !Player.getInst().hero.defBuff)
        {
            Player.getInst().hero.getMpComponent().doDelta(-25);
            Player.getInst().hero.defBuff = true;
        }
    }
    private void UpdateSpellSlot3()
    {
        if (!Player.getInst().hero.turnbase.getKeyValue(Player.getInst().hero.key))
            return;
        if (spellSlot3.IsClicked() && !Player.getInst().hero.criticalbuff)
        {
            Player.getInst().hero.getMpComponent().doDelta(-25);
            Player.getInst().hero.criticalbuff = true;
        }
    }
}
