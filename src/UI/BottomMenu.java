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
import laptrinhjava.Scene.InventoryScene;
/**
 *
 * @author Nhan
 */
public class BottomMenu {
    GButton spellSlot1;
    GButton spellSlot2;
    GButton spellSlot3;
    GButton itemSlot1;
    GButton itemSlot2;
    GButton itemSlot3;
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
            if (SceneManager.getInstance().getScene(Scene.eSceneId.S_inventory) != null)
            {
                SceneManager.getInstance().setCurrentScene(Scene.eSceneId.S_inventory);
                InventoryScene temp = (InventoryScene)SceneManager.getInstance().getScene(Scene.eSceneId.S_inventory);
                temp.active();
                System.out.println("invetory Button Pressed1");
                GGrid.getInst().deActive();
                this.deActive();
                return;
            }
            SceneManager.getInstance().createScene(Scene.eSceneId.S_inventory);
            SceneManager.getInstance().setCurrentScene(Scene.eSceneId.S_inventory);
            System.out.println("invetory Button Pressed");
            GGrid.getInst().deActive();
            this.deActive();
        }
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
}
