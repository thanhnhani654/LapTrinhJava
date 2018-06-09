/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Component.ItemComponent;
import Utility.IntRect;
import Utility.Int2;
import java.util.ArrayList;
import java.util.List;
import laptrinhjava.GButton;
import java.awt.Graphics;
import laptrinhjava.ImageDraw;
import Component.ItemCarrier;
import GameObject.Player;
/**
 *
 * @author Nhan
 */
public class ItemSlot {
    ItemComponent.eItemTag tag;
    ItemComponent.eItemTag specialTag;
    public ItemComponent item;
    int amount;

    public GButton getButton() {
        return button;
    }
    GButton button; 
    ImageDraw image;

    public ImageDraw getImage() {
        return image;
    }
    boolean bActive = true;
    
    
    public static List<ItemSlot> listItemSlot = new ArrayList<ItemSlot>();
    
    public ItemSlot()
    {
        image = new ImageDraw("src/Resources/ItemSlot_none.png",0,0,80,80);
        button = new GButton(image,0,0);
        tag = ItemComponent.eItemTag.IT_none;
        specialTag = ItemComponent.eItemTag.IT_none;
        item = null;
        amount = 0;
        listItemSlot.add(this);
    }
    
    public void setSpecialTag(ItemComponent.eItemTag tag)
    {
        specialTag = tag;
    }
    
    public ItemComponent.eItemTag getSpecialTag()
    {
        return specialTag;
    }
    
    public boolean isActive()
    {
        return bActive;
    }
    
    public void active()
    {
        button.active();
        bActive = true;
    }
    
    public void deActive()
    {
        button.deActive();
        bActive = false;
    }
    
    public ItemComponent getItem ()
    {
        return item;
    }
    
    //Dua Item vao trong Slot thong qua ItemCarrier
    public boolean pushItem(ItemCarrier itemCarrier)
    {
        if (this.item != null && this.item.isHasTag(ItemComponent.eItemTag.IT_consumable))
        {
            return false;
        }
        
        if (this.item == null)
        {
            //this.item = item;
            if (this.specialTag == ItemComponent.eItemTag.IT_none)
            {
                this.item = itemCarrier.getOutItem();
                amount = 1;
                button.setImage(item.getImage());
            }
            else
            {
                switch(this.specialTag)
                {
                    case IT_weapon:
                        if (itemCarrier.getItem().isHasTag(ItemComponent.eItemTag.IT_weapon))
                        {
                            this.item = itemCarrier.getOutItem();
                            amount = 1;
                            button.setImage(item.getImage());
                        }
                        break;
                    case IT_shield:
                        if (itemCarrier.getItem().isHasTag(ItemComponent.eItemTag.IT_shield))
                        {
                            this.item = itemCarrier.getOutItem();
                            amount = 1;
                            button.setImage(item.getImage());
                        }
                        break;
                    case IT_armor:
                        if (itemCarrier.getItem().isHasTag(ItemComponent.eItemTag.IT_armor))
                        {
                            this.item = itemCarrier.getOutItem();
                            amount = 1;
                            button.setImage(item.getImage());
                        }
                        break;
                    case IT_amulet:
                        if (itemCarrier.getItem().isHasTag(ItemComponent.eItemTag.IT_amulet))
                        {
                            this.item = itemCarrier.getOutItem();
                            amount = 1;
                            button.setImage(item.getImage());
                        }
                        break;
                }
                if (item != null)
                item.use(Player.getInst().getCharacter());
                
            }
        }
        else
        {
            amount ++;
        }
        return true;
    }
    
    //Dua Item vao trong Slot khong thong qua ItemCarrier   ----> dung de Debug
    public boolean pushItem(ItemComponent item)
    {
        if (this.item != null && this.item.isHasTag(ItemComponent.eItemTag.IT_consumable))
        {
            return false;
        }
        
        if (this.item == null)
        {
            //this.item = item;
            this.item = item;
            amount = 1;
            button.setImage(item.getImage());
        }
        else
        {
            amount ++;
        }
        return true;
    }
    
    //bien item dc truyen vao de lay item trong Slot ra
    public boolean popItem(ItemCarrier itemCarrier)
    {
        if (this.item == null)
            return false;
        
        try 
        {
            ItemComponent tempItem = (ItemComponent)this.item.clone();
            itemCarrier.putItem(tempItem, this);
        } catch (CloneNotSupportedException e)
        {}
        if (this.specialTag == ItemComponent.eItemTag.IT_weapon ||
                this.specialTag == ItemComponent.eItemTag.IT_shield ||
                this.specialTag == ItemComponent.eItemTag.IT_armor ||
                this.specialTag == ItemComponent.eItemTag.IT_amulet)
        {
            this.item.unUse(Player.getInst().getCharacter());
        }
        this.item = null;
        button.setImage(image);
        tag = ItemComponent.eItemTag.IT_none;
        
        
        return true;
    }  
    
    public void setPosition(Int2 pos)
    {
        button.setPosition(pos);
    }
    
    public Int2 getPosition()
    {
        return button.getPosition();
    }
    
    public IntRect getSize()
    {
        return button.getRect();
    }
    
    public boolean isHasItem()
    {
        if (item != null)
            return true;
        return false;
    }
    
    public void Update()
    {
        
        if (!bActive)
            return;
//        if (button.IsClicked())
//        {
//           
//        }
        if (button.IsReleased())
        {
            if (ItemCarrier.getInst().isHastItem())
            {
                pushItem(ItemCarrier.getInst());
            }
            System.out.println("ItemSlot Released");
        }
    }
    
    public void Draw(Graphics g)
    {
        if (!bActive)
            return;
        if (item == null)
        {
            image.Draw(g);
        }
        else
        {
            button.drawButton(g);
        }
    }
    
    
}
