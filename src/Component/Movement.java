/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component;
import Utility.Int2;
import GameObject.*;
import Utility.Tag;
import java.lang.Math;
/**
 *
 * @author Nhan
 */
public class Movement {
    GGrid grid;
    int speed = 0;          // speed = 100 <=> di chuyen khong gioi han
    GameObject gameObject;
    
    public Movement(GameObject gameObject)
    {
        grid = GGrid.getInst();
        this.gameObject = gameObject;
    }
    
    public boolean move(Int2 pos)
    { 
        if (pos.x > 8 || pos.x < 0)
        {
            return false;
        }
        if (pos.y > 4 || pos.y < 0)
        {
            
            return false;
        }
        if (!grid.getTile(pos.x, pos.y).isHasTag(Tag.eTag.T_none))
        {
            return false;
        }
        Int2 objectPos = gameObject.getPosition();
        if (pos.x == objectPos.x && pos.y == objectPos.y)
        {
            return false;
        }
        
        if (Math.abs(pos.x - objectPos.x) > speed)
            return false;
        if (Math.abs(pos.y - objectPos.y) > speed)
            return false;
        if (Math.abs(pos.x - objectPos.x) + Math.abs(pos.y - objectPos.y) > speed)
            return false;
        gameObject.setPosition(pos);
        return true;
    }
    
    public boolean canMove(Int2 pos)
    {
         if (pos.x > 8 || pos.x < 0)
        {
            return false;
        }
        if (pos.y > 4 || pos.y < 0)
        {
            
            return false;
        }
        if (!grid.getTile(pos.x, pos.y).isHasTag(Tag.eTag.T_none))
        {
            return false;
        }
        Int2 objectPos = gameObject.getPosition();
        if (pos.x == objectPos.x && pos.y == objectPos.y)
        {
            return false;
        }
        
        if (Math.abs(pos.x - objectPos.x) > speed)
            return false;
        if (Math.abs(pos.y - objectPos.y) > speed)
            return false;
        if (Math.abs(pos.x - objectPos.x) + Math.abs(pos.y - objectPos.y) > speed)
            return false;
        return true;
    }
    
    public void setSpeed(int speed)
    {
        this.speed = speed;
    }
}
