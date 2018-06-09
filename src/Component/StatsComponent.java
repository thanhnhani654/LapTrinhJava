/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component;

/**
 *
 * @author Nhan
 */
public class StatsComponent {
    float damage;
    float magicDamage;

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public void setMagicDamage(float magicDamage) {
        this.magicDamage = magicDamage;
    }

    public void setArmor(float armor) {
        this.armor = armor;
    }

    public void setMagicArmor(float magicArmor) {
        this.magicArmor = magicArmor;
    }

    public void setBreakArmor(float breakArmor) {
        this.breakArmor = breakArmor;
    }

    public void setPoision(float poision) {
        this.poision = poision;
    }

    public void setBlockChance(float blockChance) {
        this.blockChance = blockChance;
    }

    public void setCriticalChance(float criticalChance) {
        this.criticalChance = criticalChance;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }
    float armor;
    float magicArmor;
    float breakArmor;
    float poision;
    float blockChance;

    public float getDamage() {
        return damage;
    }

    public float getMagicDamage() {
        return magicDamage;
    }

    public float getArmor() {
        return armor;
    }

    public float getMagicArmor() {
        return magicArmor;
    }

    public float getBreakArmor() {
        return breakArmor;
    }

    public float getPoision() {
        return poision;
    }

    public float getBlockChance() {
        return blockChance;
    }

    public float getCriticalChance() {
        return criticalChance;
    }

    public int getHp() {
        return hp;
    }

    public int getMp() {
        return mp;
    }
    float criticalChance;
    int hp;
    int mp;
    
    
    public StatsComponent()
    {
        damage = 10;
        magicDamage = 10;
        armor = 10;
        magicArmor = 10;
        breakArmor = 0;
        poision = 0;
        blockChance = 0;
        criticalChance =0;
        hp = 0;
        mp = 0;
    }
    
    public void addStat(StatsComponent stat)
    {
        damage += stat.damage;
        magicDamage += stat.magicDamage;
        armor += stat.armor;
        magicArmor += stat.magicArmor;
        breakArmor += stat.breakArmor;
        poision += stat.poision;
        blockChance += stat.blockChance;
        criticalChance += stat.criticalChance;
    }
    
    public void subtractStat(StatsComponent stat)
    {
        damage -= stat.damage;
        magicDamage -= stat.magicDamage;
        armor -= stat.armor;
        magicArmor -= stat.magicArmor;
        breakArmor -= stat.breakArmor;
        poision -= stat.poision;
        blockChance -= stat.blockChance;
        criticalChance -= stat.criticalChance;
    }
}
