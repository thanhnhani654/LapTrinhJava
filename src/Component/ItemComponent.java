/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component;

import laptrinhjava.ImageDraw;
import java.lang.Cloneable;
import GameObject.Character;
/**
 *
 * @author Nhan
 */
public class ItemComponent implements Cloneable {
    static public enum eItemTag
    {IT_none,   IT_weapon,   IT_shield,  IT_armor,    IT_amulet, IT_consumable, IT_spell, IT_all   }
    static public enum eItemLevel
    {IL_common, IL_rare, IL_epic, IL_lengendary}
    
    String name;
    eItemTag tag;
    //int amount;
    StatsComponent stat;
    ImageDraw image;

    public String getName() {
        return name;
    }

    public StatsComponent getStat() {
        return stat;
    }
    
    
    public ItemComponent(String name, String imagePath, eItemTag tag)//, int amount)
    {
        this.name = name;
        this.image = new ImageDraw(imagePath, 0,0,80,80);
        this.tag = tag;
        stat = new StatsComponent();
        //stat.hp = 100;
        //stat.criticalChance = 10;
        //stat.blockChance = 10;
        //stat.damage = 10;
//        if (tag == eItemTag.IT_consumable)
//        {
//            this.amount = amount;
//        }
//        this.amount = 1;
    }

    public void setName(String name) {
        this.name = name;
    }

    public eItemTag getTag() {
        return tag;
    }
    
    public ImageDraw getImage()
    {
        return image;
    }
    
    
    public boolean isHasTag(eItemTag tag)
    {
        if (this.tag != tag)
            return false;
        return true;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    public void use(Character target)
    {
        if (tag == eItemTag.IT_consumable)
        {
            target.getHpComponent().doDelta(stat.hp);
            target.getMpComponent().doDelta(stat.mp);
        }
        else
        {
            target.getHpComponent().setMaxHp(target.getHpComponent().getMaxHp() + stat.hp);
            target.getMpComponent().setMaxMp(target.getMpComponent().getMaxMp() + stat.mp);
            target.getStatsComponent().addStat(stat);
        }
    }
    
    public void unUse(Character target)
    {
        target.getHpComponent().setMaxHp(target.getHpComponent().getMaxHp() - stat.hp);
        target.getMpComponent().setMaxMp(target.getMpComponent().getMaxMp() - stat.mp);
        target.getStatsComponent().subtractStat(stat);
    }
    
}
