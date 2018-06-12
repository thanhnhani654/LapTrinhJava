/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import Component.GGrid;
import Utility.Int2;
import laptrinhjava.ImageDraw;
import Utility.Tag;
import Component.MeleeAI;
/**
 *
 * @author Nhan
 */
public class Enemy extends Character {
   
    protected MeleeAI ai;
    public Enemy(int x, int y, int id)
    {
        level.setCurrentExp(80);
        this.id = id;
        bEnemy= true;
        tag = Tag.eTag.T_enemy;
        image = new ImageDraw("src/Resources/Enemy.png", 0,0,100,100);
        setPosition(new Int2(x,y));
        ai = new MeleeAI(this);
        InitializeStat();
    }
    
    public void Update ()
    {        
        super.Update();
        ai.Update();
    }
    
    public void InitializeStat()
    {
        stats.setHp(100);
        stats.setDamage(10);
        stats.setArmor(0);
        stats.setCriticalChance(0);
        stats.setBreakArmor(0);
        stats.setBlockChance(0);  
    }
 
}
