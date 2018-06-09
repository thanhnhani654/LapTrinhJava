/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import java.awt.Graphics;
import Component.HpComponent;
import Component.MpComponent;
import Component.Movement;
import laptrinhjava.ImageDraw;
import Component.StatsComponent;
import Component.AttackComponent;
import Component.TurnBaseSystem;
import Component.KeyTurnBase;
/**
 *
 * @author Nhan
 */
public abstract class Character extends GameObject {
    protected ImageDraw image;
    protected HpComponent hp;
    protected MpComponent mp;
    public Movement movement;
    protected StatsComponent stats;
    boolean bEnemy;
    AttackComponent.eAttStyle attStyle;
    public int key;
    public TurnBaseSystem turnbase;
    
    public Character()
    {
        movement = new Movement(this);
        movement.setSpeed(1);
        hp = new HpComponent(this);
        hp.setMaxHp(100);
        hp.setHp(100);
        mp = new MpComponent();
        mp.setMaxMp(100);
        mp.setMp(100);
        stats = new StatsComponent();
        turnbase = TurnBaseSystem.getInstance();
        key = TurnBaseSystem.getInstance().getKey();
    }
    
    public Movement getMovement()
    {
        return movement;
    }
    
    public HpComponent getHpComponent()
    {
        return hp;
    }
    
    public MpComponent getMpComponent()
    { 
        return mp;
    }
    
    public StatsComponent getStatsComponent()
    {
        return stats;
    }
    
    //public abstract void childUpdate();
    
    public void Update()
    {
        if (delete)
            return;
        if (image == null)
            return;
        image.setPosition(pos.x, pos.y);
        if (this.id != 0)
        {
            if (hp.getHp() == 0)
            {
                turnbase.freeKey(key);
                this.destroy();
                //System.out.printf("id: %d\n", this.id);
            }
        }
        
        //childUpdate();
    }
    
    public void Draw(Graphics g)
    {
        if (delete)
            return;
        if (image == null)
            return;
        image.Draw(g);
    }
}
