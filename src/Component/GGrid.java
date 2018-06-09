/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component;

import java.util.ArrayList;
import java.util.List;
import Utility.*;
import java.awt.Graphics;
import GameObject.Player;
/**
 *
 * @author Nhan
 */
public class GGrid {
    Tile[][] tiles;
    
    public static GGrid inst;
    boolean bActive = true;
    
    public GGrid ()
    {
        tiles = new Tile[8][4];
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 4; j++)
            {
                tiles[i][j] = new Tile(i*100,j*100+100,100,100);
            }
    }
    
    public boolean isActive()
    {
        return bActive;
    }
    
    public void active()
    {
        bActive= true;
        Player.getInst().active();
    }
    
    public void deActive()
    {
        bActive = false;
        Player.getInst().deActive();
    }
    
    public static GGrid getInst()
    {
        if (inst == null)
        {
            inst = new GGrid();
        }
        return inst;
    }
    
    public void attachTile(Int2 pos, Tag.eTag tag, int id)
    {
        tiles[pos.x][pos.y].setTag(tag);
        tiles[pos.x][pos.y].setObjectId(id);
    }
    
    public void deAttachTile(Int2 pos)
    {
        tiles[pos.x][pos.y].setObjectId(-1);
        tiles[pos.x][pos.y].setTag(Tag.eTag.T_none);
    }
    
    public Tile getTile(int x, int y)
    {
        return tiles[x][y];
    }
    
    //GameObject chi su dung GridPos
    public static Int2 convertToGridPos(Int2 pos)
    {
        Int2 gridpos = new Int2 (0,0);
        if (pos.x > 800 || pos.y > 500 || pos.y < 100)
        {
            gridpos.x = -1;
            gridpos.y = -1;
            return gridpos;
        }
        
        gridpos.x = pos.x / 100;
        gridpos.y = (pos.y-100) / 100;
            
        System.out.printf("gridpos.x = %d\ngridpos.y = %d\n", gridpos.x,gridpos.y);
        return gridpos;
    }
    
    //GameObject chi su dung GridPos
    public static Int2 convertToNormalPos (Int2 gridpos)
    {
        Int2 pos = new Int2 (0,0);
        
        if (gridpos.x > 8 || gridpos.x < 0)
        {
           pos.x = -1;
           pos.y = -1;
           return pos;
        }
        if (gridpos.y > 4 || gridpos.y < 0)
        {
            pos.x = -1;
            pos.y = -1;
            return pos;
        }
        
        pos.x = gridpos.x * 100;
        pos.y = (gridpos.y + 1) *100;
        
        return pos;
    }
    
    public void Draw (Graphics g)
    {
        if (!bActive)
            return;
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 4; j++)
            {
                if (tiles[i][j] != null)
                    tiles[i][j].Draw(g);
            }
    }
    
    public void clearGrid()
    {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 4; j++)
            {
                tiles[i][j].setTag(Tag.eTag.T_none);
            }
    }
    
}
