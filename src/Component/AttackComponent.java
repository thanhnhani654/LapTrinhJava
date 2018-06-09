/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component;

import java.util.Random;
import GameObject.Character;
import Component.StatsComponent;
import java.awt.Graphics;
import Utility.Int2;
/**
 *
 * @author Nhan
 */

//Use in Interation with user is Player
public class AttackComponent {
    
    public static enum eAttStyle
    {
        AS_melee,
        AS_range,
        AS_magic
    }
    
    Character character = null;
    StatsComponent stats;
    Random rd;
    boolean doCritical = false;
    boolean doBlock = false;
    boolean doMeleeAttacking = false;
    Character target = null;
   
    eAttStyle attstyle;
    
    // For Melee Attack
    Int2 endPos;
    Int2 preparePos;
    Int2 attackPos;
    boolean attackStart;
    public boolean attackEnd;
    private Int2 prepareVelocity = new Int2(0,0);
    private Int2 attackVelocity = new Int2(0,0);
    private Int2 endVelocity = new Int2(0,0);
    
    public enum eMAttState
    {
        MA_prepare,
        MA_attack,
        MA_end,
        MA_calculate,
    }
   eMAttState mAttState;
   
    
    
    public AttackComponent(Character character)
    {
        rd = new Random();
        this.character = character;
        stats = character.getStatsComponent();
        mAttState = eMAttState.MA_calculate;
        endPos = new Int2(0,0);
        preparePos = new Int2(0,0);
        attackPos = new Int2(0,0);
        
    }
    
    public void getTarget(Character target) 
    {
        this.target = target;
    }
    
    public void meleeAttack()
    {
        doMeleeAttacking = true;
    }
    
    public void DoMeleeAttack()
    {
        attackEnd = false;
        if (target == null)
            return;
        if (character == null)
            return;
        if (Math.abs(target.getPosition().x - character.getPosition().x) > 1)
            doMeleeAttacking = false;
        else if (Math.abs(target.getPosition().y - character.getPosition().y) > 1)
            doMeleeAttacking = false;
        else if (Math.abs(target.getPosition().x - character.getPosition().x) + Math.abs(target.getPosition().y - character.getPosition().y) > 1)
            doMeleeAttacking = false;
        if (!doMeleeAttacking)
            return;    
        
        
        switch (mAttState)
        {
        case MA_calculate:
            endPos = new Int2(character.getRealPosition());
            preparePos = new Int2(character.getRealPosition());
            attackPos = new Int2(character.getRealPosition());
            if ( target.getRealPosition().x - character.getRealPosition().x != 0)
            {      
                System.out.println(character.getRealPosition().x);
                preparePos.x = character.getRealPosition().x - (int)((target.getRealPosition().x - character.getRealPosition().x) * 0.2f);
                attackPos.x = character.getRealPosition().x + (int)((target.getRealPosition().x - character.getRealPosition().x) * 0.2f);
                prepareVelocity.x = (character.getRealPosition().x - target.getRealPosition().x) / 100 * 2;
                attackVelocity.x = (target.getRealPosition().x - character.getRealPosition().x) / 100 * 5;
                endVelocity.x = (character.getRealPosition().x - target.getRealPosition().x) / 100 ;  
            }
            if ( target.getRealPosition().y - character.getRealPosition().y != 0)
            {
                preparePos.y = character.getRealPosition().y - (int)((target.getRealPosition().y - character.getRealPosition().y) * 0.2f);
                attackPos.y = character.getRealPosition().y + (int)((target.getRealPosition().y - character.getRealPosition().y) * 0.2f);
                prepareVelocity.y = (character.getRealPosition().y - target.getRealPosition().y) / 100 * 2;
                attackVelocity.y = (target.getRealPosition().y - character.getRealPosition().y) / 100 * 5;
                endVelocity.y = (character.getRealPosition().y - target.getRealPosition().y) / 100 ;
            }
            
            mAttState = eMAttState.MA_prepare;
            
        break;
            case MA_prepare:
                if (character.getRealPosition().x != preparePos.x)
                    character.setRealPosition(new Int2(character.getRealPosition().x + prepareVelocity.x,character.getRealPosition().y ));
                if (character.getRealPosition().y != preparePos.y)
                    character.setRealPosition(new Int2(character.getRealPosition().x,character.getRealPosition().y + prepareVelocity.y ));
                if (character.getRealPosition().x == preparePos.x && character.getRealPosition().y == preparePos.y)
                    mAttState = eMAttState.MA_attack;
                break;
            case MA_attack:
                if (character.getRealPosition().x != attackPos.x)
                    character.setRealPosition(new Int2(character.getRealPosition().x + attackVelocity.x,character.getRealPosition().y ));
                if (character.getRealPosition().y != attackPos.y)
                    character.setRealPosition(new Int2(character.getRealPosition().x,character.getRealPosition().y + attackVelocity.y));
                if (character.getRealPosition().x == attackPos.x && character.getRealPosition().y == attackPos.y)
                {
                    mAttState = eMAttState.MA_end;
                    doMeleeDamage();
                }
                break;
            case MA_end:
                if (character.getRealPosition().x != endPos.x)
                    character.setRealPosition(new Int2(character.getRealPosition().x + endVelocity.x,character.getRealPosition().y ));
                if (character.getRealPosition().y != endPos.y)
                    character.setRealPosition(new Int2(character.getRealPosition().x,character.getRealPosition().y + endVelocity.y));
                if (character.getRealPosition().x == endPos.x && character.getRealPosition().y == endPos.y)
                {
                    mAttState = eMAttState.MA_calculate;
                    doMeleeAttacking = false;
                    attackEnd = true;
                }
                
                break;
        }
    }
    
    public void doMeleeDamage()
    {
        if (target == null)
            return;
        StatsComponent tarStats = target.getStatsComponent();
        
        if (stats.criticalChance > 0)
        {
            if (rd.nextFloat() < stats.criticalChance / 100.0f)
                doCritical = true;
        }
        
        if (tarStats.blockChance > 0)
        {
            if (rd.nextFloat() < tarStats.blockChance / 100.0f)
                doBlock = true;
        }
        float damage = stats.damage;
        if (doCritical)
        {
            damage *= 2;
        }
        
        damage = damage - (0.5f * (tarStats.armor - stats.breakArmor));
        if (damage < 0 || doBlock)
            damage = 0;
        target.getHpComponent().doDelta((int)(-damage));
        doCritical = false;
        doBlock = false;
    }
}
