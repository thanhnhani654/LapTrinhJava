/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component;

import Utility.*;
import laptrinhjava.ImageDraw;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
/**
 *
 * @author Nhan
 */
public class Tile {
    IntRect tile;
    Tag.eTag tag;
    ImageDraw image;
    int objectId = -1;
    
    //static List<Tile> listButton = new ArrayList<GButton>();
    
    public Tile(int x, int y, int w, int h)
    {
        image = new ImageDraw("src/Resources/Tile.png",x,y,100, 100);
        this.tile = new IntRect(x,y,w,h);
        tag = Tag.eTag.T_none;
    }
    
    public void setTag(Tag.eTag tag)
    {
        this.tag = tag;
    }
    
    public Tag.eTag getTag()
    {
        return this.tag;
    }
    
    public boolean isHasTag(Tag.eTag tag)
    {
        if (this.tag == tag)
        {
            return true;
        }
        return false;
    }
    
    public void setObjectId(int id)
    {
        objectId = id;
    }
    
    public int getObjectId()
    {
        return objectId;
    }
    
    public void Draw(Graphics g)
    {
        image.Draw(g);
    }
}
