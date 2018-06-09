/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import Utility.Int2;
import Utility.Tag;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import Component.GGrid;
/**
 *
 * @author Nhan
 */
public abstract class GameObject {
    protected int id = -1;
    protected Int2 pos = new Int2(0,0);
    protected Int2 gridpos = new Int2(0,0);
    protected Tag.eTag tag;
    protected boolean delete = false;
    
    static List<GameObject> listObject = new ArrayList<GameObject>();
    
    public GameObject ()
    {
        pos = new Int2(0,0);
        listObject.add(this);
    }
    
    public void setPosition(Int2 pos)
    {
        if (pos.x > 8 || pos.x < 0)
        {
            System.out.println("Position incompatible");
            return;
        }
        if (pos.y > 4 || pos.y < 0)
        {
            System.out.println("Position incompatible");
            return;
        }
        
        GGrid.getInst().deAttachTile(gridpos);
        Int2 temp = new Int2(pos.x * 100, pos.y * 100 + 100);
        gridpos = pos;
        this.pos = temp;
        GGrid.getInst().attachTile(pos, tag, id);
        System.out.println("GAMEOBJECT DEBUG " +temp.y);
    }
    
    public void setRealPosition(Int2 pos)
    {
        this.pos = pos;
    }
    
    //Duoc chay trong Game
    public static void Destroyer()
    {
        GameObject temp = null;
       
        for (GameObject l : listObject)
        {
            if (l.delete == true)
            {                
                temp = l;
                GGrid.getInst().deAttachTile(l.gridpos);
                break;
            }
        }
        if (temp != null)
        {
            listObject.remove(temp);
            temp = null;
        }
    }
    
    public void destroy()
    {
        delete = true;
    }
            
    public Int2 getPosition()
    {
        return this.gridpos;
    }
    
    public Int2 getRealPosition()
    {
        return this.pos;
    }
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public void setTag(Tag.eTag tag)
    {
        this.tag = tag;
    }
    
    public Tag.eTag getTag()
    {
        return this.tag;
    }
    
    public static GameObject getObject(int id)
    {
        for (GameObject l : listObject)
        {
            if (l.getId() == id)
                return l;
        }
        
        return null;
    }
    
    public abstract void Update();
    //public abstract void UpdateTurn(TurnBaseSystem turnbase);
    public abstract void Draw(Graphics g);
}
