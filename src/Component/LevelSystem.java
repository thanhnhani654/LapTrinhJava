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
public class LevelSystem {
    private int currentExp;
    private int upLvExp;
    private int Level;
    private int StatPoint;
    
    public LevelSystem()
    {
        currentExp = 0;
        upLvExp = 100;
        Level = 1;
        StatPoint = 0;
    }
    
    public void setCurrentExp(int currentExp) {
        this.currentExp = currentExp;
    }

    public void setUpLvExp(int upLvExp) {
        this.upLvExp = upLvExp;
    }

    public void setLevel(int Level) {
        this.Level = Level;
    }

    public int getCurrentExp() {
        return currentExp;
    }

    public int getUpLvExp() {
        return upLvExp;
    }

    public int getLevel() {
        return Level;
    }
    
    public int getStatPoint()
    {
        return StatPoint;
    }
     
    public void addExp(int exp)
    {
        currentExp += exp;
        if (currentExp >= upLvExp)
        {
            currentExp -= upLvExp;
            upLevel();
        }
    }
    
    public void upLevel()
    {
        Level ++;
        switch(Level)
        {
            case 1:
                upLvExp = 100;
             
                break;
            case 2:
                upLvExp = 120;
                break;
            case 3:
                upLvExp = 150;
                break;
            case 4:
                upLvExp = 180;
                break;
            case 5:
                upLvExp = 220;
                break;
            case 6:
                upLvExp = 270;
                break;
            case 7:
                upLvExp = 330;
                break;
            case 8:
                upLvExp = 400;
                break;
            case 9:
                upLvExp = 480;
                break;
            case 10:
                upLvExp = 570; 
                break;  
        }
        addStatPoint();
    }
    
    public void addStatPoint()
    {
        StatPoint += 5;
    }
    
    public boolean usedStatPoint()
    {
        if (StatPoint < 1)
            return false;
        StatPoint -= 1;
        return true;
    }
}
