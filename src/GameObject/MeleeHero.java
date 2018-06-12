/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import laptrinhjava.ImageDraw;
import Component.AttackComponent;
import Component.LevelSystem;
/**
 *
 * @author Nhan
 */
public class MeleeHero extends Character {
    
  
    
    public MeleeHero()
    {
        
        bEnemy = false;
        attStyle = AttackComponent.eAttStyle.AS_melee;
        image = new ImageDraw("src/Resources/Player.png",pos.x,pos.y,100,100);
    }
}
