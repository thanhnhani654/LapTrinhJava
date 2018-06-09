/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import laptrinhjava.ImageDraw;
import Component.AttackComponent;
/**
 *
 * @author Nhan
 */
public class MeleeHero extends Character {
    
    private int levelDungeon;
    
    
    public MeleeHero()
    {
        levelDungeon = 0;
        bEnemy = false;
        attStyle = AttackComponent.eAttStyle.AS_melee;
        image = new ImageDraw("src/Resources/Player.png",pos.x,pos.y,100,100);
    }

    public int getLevelDungeon() {
        return levelDungeon;
    }

    public void setLevelDungeon(int levelDungeon) {
        this.levelDungeon = levelDungeon;
    }
}
