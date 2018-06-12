/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component;

import Utility.Int2;
import GameObject.Character;
import GameObject.GameObject;
import GameObject.Gate;
import GameObject.Enemy;
import Component.AttackComponent;
import java.awt.Graphics;
import GameObject.Player;
import UI.BottomMenu;

import Utility.Tag;
import laptrinhjava.Scene.Scene;
import laptrinhjava.Scene.SceneManager;
/**
 *
 * @author Nhan
 */
public class Interaction {
    GGrid grid;
    Character character;
    AttackComponent attackComp;
    long last_time;
    double delta_time;
    
    public Interaction (Character character)
    {
        grid = GGrid.getInst();
        this.character = character;
        attackComp = new AttackComponent(character);
        last_time = System.nanoTime();
        delta_time = 0;
    }
    
    public void Update()
    {
        long time = System.nanoTime();
        delta_time += (double) ((time - last_time) / (1000000/60.0));
        last_time = time;
        
        if (delta_time > 1.0)
        {
            attackComp.DoMeleeAttack();
            if (attackComp.attackEnd)
                TurnBaseSystem.getInstance().nextKey();
            delta_time --;
        }
    }
    
    public boolean action(Int2 pos)
    {
        
        if (pos.x < 0 || pos.y < 0)
            return false;
        switch (grid.getTile(pos.x, pos.y).getTag())
        {
        case T_gate:
            Gate temp = (Gate)GameObject.getObject(grid.getTile(pos.x, pos.y).getObjectId());
            if (temp.isDownGate())
            {
                temp.goDown();
            }
            else
            {
                temp.exit();
            }
            return true;
        case T_enemy:
            Enemy tempEnemy = (Enemy)GameObject.getObject(grid.getTile(pos.x, pos.y).getObjectId());
            attackComp.getTarget(tempEnemy);
            attackComp.meleeAttack();
            System.out.println("doAttackkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
            System.out.println("EnemyHP " + tempEnemy.getHpComponent().getHp());
            return true;
        case T_statNPC:
            SceneManager.getInstance().changeToSubScene(Scene.eSceneId.S_Stats);
            BottomMenu.getInst().deActive();
            //System.out.println("Call Stat NPC");
            return true;
        case T_shopNPC:
            SceneManager.getInstance().changeToSubScene(Scene.eSceneId.S_Shop);
            BottomMenu.getInst().deActive();
            System.out.println("OPEN YOUR SHOP");
        }
        return false;
    }
}
