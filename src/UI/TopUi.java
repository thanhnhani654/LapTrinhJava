/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;
import GameObject.Player;
import java.awt.Graphics;
/**
 *
 * @author Nhan
 */
public class TopUi {
    HpBar hpBar;
    MpBar mpBar;
    ExpBar expBar;
    private static TopUi inst;
    
    public TopUi(Player player)
    {
        hpBar = new HpBar(player.getHp());
        mpBar = new MpBar(player.getMp());
        expBar = new ExpBar(player.hero.level);
    }
    
    public static TopUi getInst()
    {
        if (inst == null)
        {
            inst = new TopUi(Player.getInst());
        }
        return inst;
    }
    
    public void Update()
    {
        hpBar.Update();
        mpBar.Update();
        expBar.Update();
    }
    
    public void Draw (Graphics g)
    {
        hpBar.Draw(g);
        mpBar.Draw(g);
        expBar.Draw(g);
    }
}
