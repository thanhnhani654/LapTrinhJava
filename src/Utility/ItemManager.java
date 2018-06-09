/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.util.ArrayList;
import java.util.List;
import Component.ItemComponent;
import Component.StatsComponent;

/**
 *
 * @author Nhan
 */
public class ItemManager {
    public static ItemManager inst;
    public static ItemManager getInstance()
    {
        if (inst == null)
            inst = new ItemManager();
        return inst;
    }
    
    public ItemManager()
    {}
    
    public static List<ItemData> listItem = new ArrayList<ItemData>();
    
    public ItemComponent.eItemTag getTagFromData(String tag)
    {
        ItemComponent.eItemTag tempTag;
        switch(tag)
        {
            case "0":
                return ItemComponent.eItemTag.IT_none;
            case "1":
                return ItemComponent.eItemTag.IT_weapon;
            case "2":
                return ItemComponent.eItemTag.IT_shield;
            case "3":
                return ItemComponent.eItemTag.IT_armor;
            case "4":
                return ItemComponent.eItemTag.IT_amulet;
            case "5":
                return ItemComponent.eItemTag.IT_consumable;
            case "6":
                return ItemComponent.eItemTag.IT_spell;
            case "7":
                return ItemComponent.eItemTag.IT_all;
        }
        System.out.println("error: ItemManager.getTagFromData() wrong tag");
        return null;
    }
    public void getStatsFromData(StatsComponent stat, ItemData data)
    {
       stat.setHp(data.hp);
       stat.setDamage(data.attack);
       stat.setArmor(data.def);
       stat.setCriticalChance(data.critical);
       stat.setBreakArmor(data.breakArmor);
       stat.setBlockChance(data.blockChance);
    }
    
    public ItemComponent createItem(String name)
    {
        ItemComponent temp = null;
        for (ItemData l : listItem)
        {
            if (l.name.equals(name))
            {                
                temp = new ItemComponent(l.name, l.path, getTagFromData(l.tag));                
                getStatsFromData(temp.getStat(),l);
                
            }
            System.out.println("error ItemManager.CreateItem Wrong ItemName");
        }
        
        return temp;
    }
    
}
