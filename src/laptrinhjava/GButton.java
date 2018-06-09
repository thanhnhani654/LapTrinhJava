/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laptrinhjava;

import Utility.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
/**
 *
 * @author Nhan
 */
public class GButton {
    
    protected ImageDraw image;
    public IntRect rect;
    boolean currentState = false;
    boolean bRelease = false;
    boolean deleted = false;
    static List<GButton> listButton = new ArrayList<GButton>();
    
    boolean bActive = true;
    //public IntRect rect;
    
    public int debugid;
    
    public GButton (String imagepath, int x, int y, int w, int h)
    {
        //rect = new IntRect(x,y,w,h);
        image = new ImageDraw(imagepath, x,y,w,h);
        rect = new IntRect(x,y,w,h);
        listButton.add(this);
    }
    
    public GButton (ImageDraw image, int x, int y)
    {
        this.image = image;
        this.rect = new IntRect(x,y,image.getPosition().width,image.getPosition().height);
        listButton.add(this);
    }
    
    public void setPosition(Int2 pos)
    {
        rect.x = pos.x;
        rect.y = pos.y;
        image.setPosition(pos.x, pos.y);
    }
    
    public Int2 getPosition()
    {
        return new Int2(rect.x,rect.y);
    }
    
    public IntRect getRect()
    {
        return rect;
    }
    
    public void setImage(ImageDraw image)
    {
        this.image = image;
        image.setPosition(rect.x, rect.y);
    }
    
    public void drawButton(Graphics g)
    {
        
        if (!bActive)
            return;
        
        image.Draw(g);
    }
    
    public void getEventClicked()
    {
        if (!bActive)
            return;
        currentState = true;
    }
    
    public void getEventReleased()
    {
        if (!bActive)
            return;
        bRelease = true;
    }
    
    public boolean IsClicked()
    {
        if (currentState == true)
        {
            currentState = false;
            return true;
        }
        return false;
    }          
    
    public boolean IsReleased()
    {
        if (bRelease == true)
        {
            bRelease = false;
            return true;
        }
        return false;
    }
    
    public void deleteButton()
    {
        deleted = true;
    }
    
    public void active()
    {
        bActive = true;
        if (debugid == 2)
        {
            System.out.println("Debug X BUtton");
            System.out.println(bActive);
            
        }
    }
    
    public void deActive()
    {
        bActive = false;
    }
    
    public boolean isActive()
    {
        return bActive;
    }
    
    static public void draw(Graphics g)
    {
        if (!listButton.isEmpty())
        for (GButton l : listButton)
        {
            l.drawButton(g);
            
        }
        //else
            //System.out.println("listButton Empty");
    }
    
    static public void updateEventClicked(int x, int y)
    {
        if (!listButton.isEmpty())
        for (GButton l : listButton)
        {
            if (x < l.rect.x || 
                y < l.rect.y ||
                x > l.rect.x + l.rect.width ||
                y > l.rect.y + l.rect.height)
                continue;
            
            l.getEventClicked();
        } 
    }
    
    static public void updateEventReleased(int x, int y)
    {
        if (!listButton.isEmpty())
            for (GButton l : listButton)
            {
                if (x < l.rect.x || 
                y < l.rect.y ||
                x > l.rect.x + l.rect.width ||
                y > l.rect.y + l.rect.height)
                continue;
                l.getEventReleased();
            }
        
    }
    
    static public void update()
    {
        GButton delete = null;
        for (GButton l : listButton)
        {
                if (l.deleted)
                {
                    delete = l;
                    break;
                }
        }
        if (delete != null)
            listButton.remove(delete);
    }
}
