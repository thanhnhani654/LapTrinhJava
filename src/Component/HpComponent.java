/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component;
import GameObject.*;

/**
 *
 * @author Nhan
 */
public class HpComponent {
    GameObject gameObject;
    private int maxHp;
    private int hp;
    
    public HpComponent (GameObject gameObject)
    {
        this.gameObject = gameObject;
    }
    
    public void setMaxHp(int maxhp)
    {
        this.maxHp = maxhp;
    }
    
    public void setHp (int hp)
    {
        this.hp = hp;
    }
    
    public int getMaxHp()
    {
        return maxHp;
    }
    
    public int getHp()
    {
        return hp;
    }
    
    public void doDelta(int delta)
    {
        hp += delta;
        if (hp > maxHp)
            hp = maxHp;
        if (hp < 0)
            hp = 0;
    }
}
