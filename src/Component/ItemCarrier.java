/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component;

import java.awt.Graphics;
import laptrinhjava.ImageDraw;
import UI.ItemSlot;
import Utility.Int2;
/**
 *
 * @author Nhan
 */
public class ItemCarrier {
    public static ItemCarrier inst;
    
    public static ItemCarrier getInst()
    {
        if (inst == null)
        {
            inst = new ItemCarrier();
        }
        return inst;
    }
    
    public ItemComponent item;
    ImageDraw image;
    ItemSlot itemSlot;
    boolean bClicked = false;
    boolean bReleased = false;
    Int2 mousePos;
    public int amount;
    
    public ItemCarrier ()
    {}
    //Lay Item ra khoi Carrier
    public ItemComponent getOutItem()
    {
        ItemComponent tempItem = null;
        if (this.item == null)
            return null;
        
        try 
        {
            tempItem = (ItemComponent)this.item.clone();
        } catch (CloneNotSupportedException e)
        {}
        
        this.item = null;
        this.image = null;
        this.itemSlot = null;
        
        return tempItem;
    }
    
    public boolean isHastItem()
    {
        if (item != null)
            return true;
        return false;
    }
    
    public ItemComponent getItem()
    {
        return this.item;
    }
    
    public void putItem(ItemComponent item, ItemSlot itemSlot, int amount)
    {
        if (this.item != null)
        {
            System.out.println("'Loi Item chua duoc xoa khi tha chuot.' said: ItemCarrier");
            return;
        }
        this.item = item;
        image = this.item.getImage();
        this.itemSlot = itemSlot;
        this.amount = amount;
    }
    
    public void getEventReleased(boolean iEvent)
    {
        bReleased = iEvent;
    }
    
    public void getEventClicked(boolean iEvent)
    {
        bClicked = iEvent;
    }
    
    public void getMousePos(Int2 iMousePos)
    {
        this.mousePos = iMousePos;
    }
    
    public boolean isReleased()
    {
        if (bReleased)
        {
            bReleased = false;
            return true;
        }
        return false;
    }
    
    public void Update()
    {
        if (this.item != null)
        {            
            image.setPosition(mousePos.x - 40, mousePos.y - 40);
        }
        
        if (isReleased() && this.item != null)
        {
            if (itemSlot != null)
            {
                itemSlot.pushItem(this);
            }
            else
                System.out.println("Loi: ItemSlot bang NULL ma van co Item trong ItemCarrier");
        }
    }
    
    public void Draw (Graphics g)
    {
        if (image != null)
        {
            image.Draw(g);
        }
    }
        
    
    
    
}
