/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import Component.GGrid;
import java.awt.Graphics;
import laptrinhjava.ImageDraw;
import Utility.Tag;
import Utility.Int2;
import Component.Movement;
import Component.Interaction;
import Component.HpComponent;
import Component.MpComponent;
/**
 *
 * @author Nhan
 */
public class Player extends GameObject {
    
    static Player instance;
    
    Interaction interaction;
    public boolean gameStart = false;
    public boolean bActive = true;
    
    //ImageDraw image;
    //Movement movement;
    //HpComponent hp;
    //MpComponent mp;
    public MeleeHero hero;
    
    public enum eRole
    {
        R_melee,
        R_range,
        R_mage
    }
    public static eRole role;
    
    
    public Player ()
    {
        //id = 0;
        hero = new MeleeHero();
        hero.setId(0);
        hero.setTag(Tag.eTag.T_player);
        //image = new ImageDraw("src/Resources/Player.png",pos.x,pos.y,100,100);
        //tag = Tag.eTag.T_player;
        //movement = new Movement(this);
        //movement.setSpeed(1);
        interaction = new Interaction(hero);
//        hp = new HpComponent(this);
//        hp.setMaxHp(100);
//        hp.setHp(100);
//        mp = new MpComponent();
//        mp.setMaxMp(100);
//        mp.setMp(100);
    }
    
    public boolean isActive()
    {
        return bActive;
    }
    
    public void active()
    {
        bActive = true;
    }
    
    public void deActive()
    {
        bActive = false;
    }
    
    
    public static Player getInst()
    {
        if (instance == null)
        {
            instance = new Player();
        }
        return instance;
    }
    
    public HpComponent getHp()
    {
        return hero.getHpComponent();
    }
    
    public MpComponent getMp()
    {
        return hero.getMpComponent();
    }
    
    public Character getCharacter()
    {
        return hero;
    }
    
    public void Update()
    {
        //image.setPosition(pos.x, pos.y);
        hero.Update();
        interaction.Update();
    }
    
    public void getMousePress(int x, int y)
    {
        if (!gameStart || !bActive)
            return;
        //movement.move(GGrid.convertToGridPos(new Int2(x,y)));
        else
        {
            
            System.out.println("HERO KEY " + hero.key);
            if (hero.turnbase.getKeyValue(hero.key))
            {
                if (hero.getMovement().move(GGrid.convertToGridPos(new Int2(x,y))))
                    hero.turnbase.nextKey();
                interaction.action(GGrid.convertToGridPos(new Int2(x,y)));
                //hero.turnbase.nextKey();
            }
        }
        
    }
    
    public void Draw (Graphics g)
    {
        if (!gameStart || !bActive)
            return;
        hero.Draw(g);
        //image.Draw(g);
    }
}
