/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laptrinhjava.Scene;

import Component.GGrid;
import laptrinhjava.GButton;
import laptrinhjava.ImageDraw;
import Component.NumberComponent;
import UI.BottomMenu;
import java.awt.Graphics;
import GameObject.Player;
import Component.LevelSystem;
/**
 *
 * @author Nhan
 */
public class StatUpScene extends Scene{
    private static StatUpScene inst;
    public static StatUpScene getInstance()
    {
        if (inst  == null)
            inst = new StatUpScene();
        return inst;
    }
    
    
    Player player;
    LevelSystem level;
    //define UI
    //ImageDraw background;
    GButton attUp;
    GButton defUp;
    GButton hpUp;
    GButton mpUp;
    NumberComponent att;
    NumberComponent def;
    NumberComponent hp;
    NumberComponent mp;
    NumberComponent statPoint;
    ImageDraw attIcon;
    ImageDraw defIcon;
    ImageDraw hpIcon;
    ImageDraw mpIcon;
    ImageDraw statPointIcon;
    GButton exitButton;
    
    public StatUpScene() 
    {
        id = Scene.eSceneId.S_Stats;
        player = Player.getInst();
        level = Player.getInst().getCharacter().level;
        backGround = new ImageDraw("src/Resources/StatsBackGround.png",0,0,WidthSize, HeightSize);
        attUp = new GButton("src/Resources/AddButton.png",300,150, 50,50);
        defUp = new GButton("src/Resources/AddButton.png",660,150, 50,50);
        hpUp = new GButton("src/Resources/AddButton.png",300,300, 50,50);
        mpUp = new GButton("src/Resources/AddButton.png",660,300, 50,50);
        exitButton = new GButton("src/Resources/ExitInventory.png",750,551, 50,50);
        att = NumberComponent.createNumber( Integer.toString((int)player.getCharacter().getStatsComponent().getDamage()), 150, 130, 4,4);
        def = NumberComponent.createNumber( Integer.toString((int)player.getCharacter().getStatsComponent().getArmor()), 510, 130, 4,4);
        hp = NumberComponent.createNumber( Integer.toString((int)player.getCharacter().getStatsComponent().getHp()), 150, 280, 4,4);
        mp = NumberComponent.createNumber( Integer.toString((int)player.getCharacter().getStatsComponent().getMp()), 510, 280, 4,4);
        statPoint = NumberComponent.createNumber( Integer.toString((int)player.getCharacter().level.getStatPoint()), 450, 400, 4,4);
        attIcon = new ImageDraw("src/Resources/icoAttackPoint.png",20,140,160, 80);
        defIcon = new ImageDraw("src/Resources/icoDefPoint.png",380,140,160, 80);
        hpIcon = new ImageDraw("src/Resources/icoHPPoint.png",20,285,100, 80);
        mpIcon = new ImageDraw("src/Resources/icoMPPoint.png",380,285,100, 80);
        statPointIcon = new ImageDraw("src/Resources/icoStatPoint.png",300,400,160, 80);
    }
    
    public void active()
    {
        att.bActive = true;
        def.bActive = true;
        hp.bActive = true;
        mp.bActive = true;
        statPoint.bActive = true;
        attUp.active();
        defUp.active();
        hpUp.active();
        mpUp.active();
        bActive = true;
    }
    
    public void deActive()
    {
        att.bActive = false;
        def.bActive = false;
        hp.bActive = false;
        mp.bActive = false;
        statPoint.bActive = false;
        bActive = false;
        attUp.deActive();
        defUp.deActive();
        hpUp.deActive();
        mpUp.deActive();
    }
    
    public void destroy()
    {
        
    }
    
    public void Update(float dt)
    {
        if (exitButton.IsClicked())
        {
            SceneManager.getInstance().setCurrentScene(SceneManager.getInstance().previousScene);
            BottomMenu.getInst().active();
            GGrid.getInst().active();
            System.out.println("X button deactive");
            this.deActive();
        }
        
        
        if (level.getStatPoint() == 0)
        {
            attUp.deActive();
            defUp.deActive();
            hpUp.deActive();
            mpUp.deActive();
        }
        
        att.updateText(Integer.toString((int)player.getCharacter().getStatsComponent().getDamage()));
        def.updateText(Integer.toString((int)player.getCharacter().getStatsComponent().getArmor()));
        hp.updateText(Integer.toString(player.getCharacter().getHpComponent().getMaxHp()));
        mp.updateText(Integer.toString(player.getCharacter().getMpComponent().getMaxMp()));
        statPoint.updateText(Integer.toString((int)player.getCharacter().level.getStatPoint()));
        
        
        if (attUp.IsClicked())
        {
            if (level.usedStatPoint())
                player.getCharacter().getStatsComponent().setDamage( player.getCharacter().getStatsComponent().getDamage() + 1);
        }
        if (defUp.IsClicked())
        {
            if (level.usedStatPoint())
                player.getCharacter().getStatsComponent().setArmor( player.getCharacter().getStatsComponent().getArmor() + 1);
        }
        if (hpUp.IsClicked())
        {
            if (level.usedStatPoint())
                player.getCharacter().getHpComponent().setMaxHp(player.getCharacter().getHpComponent().getMaxHp() + 10);
        }
        if (mpUp.IsClicked())
        {
            if (level.usedStatPoint())
                player.getCharacter().getMpComponent().setMaxMp(player.getCharacter().getMpComponent().getMaxMp() + 10);
        }
    }
    
    public void Draw(Graphics g)
    {
        backGround.Draw(g);
        exitButton.drawButton(g);
        attUp.drawButton(g);
        defUp.drawButton(g);
        hpUp.drawButton(g);
        mpUp.drawButton(g);
        attIcon.Draw(g);
        defIcon.Draw(g);
        hpIcon.Draw(g);
        mpIcon.Draw(g);
        statPointIcon.Draw(g);
        
    }
}
