/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;
import GameObject.Player;
import Component.HpComponent;
import laptrinhjava.ImageDraw;
import java.awt.Graphics;
/**
 *
 * @author Nhan
 */
public class HpBar {
    HpComponent hp;
    ImageDraw[] image;
    int hpPercent;
    
    public HpBar(HpComponent hp)
    {
        this.hp = hp;
        image = new ImageDraw[100];
        for (int i = 0; i < 100; i++)
        {
            image[i] = new ImageDraw("src/Resources/HPBar.png",140+i*2,50,2,10);
        }
    }
    
    public void Update()
    {
        hpPercent = hp.getHp() * 100 / hp.getMaxHp();
    }
    
    public void Draw(Graphics g)
    {
        
        for (int i = 0; i < hpPercent; i++)
        {
            image[i].Draw(g);
        }
    }
}
