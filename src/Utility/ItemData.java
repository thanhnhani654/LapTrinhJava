/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

/**
 *
 * @author Nhan
 */
public class ItemData {
    public String name;
    public String path;
    // {0: none, 1:weapon, 2: Shield, 3: armor, 4: amulet, 5: consumable, 6: Spell, 7: all}
    public String tag;
    // {0:common, 1:rare, 2: epic, 3:lengendary}
    public String level;
    public int hp;
    public float attack;
    public float def;
    public float critical;
    public float breakArmor;
    public float blockChance;
    
    ItemData(String name,String path, String tag, String level,String hp, String attack, String def, String critical, String breakArmor, String blockChance)
    {
        this.name = name;
        this.path = path;
        this.tag = tag;
        this.level = level;
        this.attack = Float.parseFloat(attack);
        this.def = Float.parseFloat(def);
        this.critical = Float.parseFloat(critical);
        this.breakArmor = Float.parseFloat(breakArmor);
        this.blockChance = Float.parseFloat(blockChance);
    }
}
