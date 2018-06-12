/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import laptrinhjava.ImageDraw;
import Component.LevelSystem;
import java.awt.Graphics;
/**
 *
 * @author Nhan
 */
public class ExpBar {
    LevelSystem level;
    ImageDraw[] image;
    int expPercent;
    
    public ExpBar(LevelSystem level)
    {
        this.level = level;
        image = new ImageDraw[100];
        for (int i = 0; i < 100; i++)
        {
            image[i] = new ImageDraw("src/Resources/ExpBar.png",140+i*2,90,2,10);
        }
    }
    
    public void Update()
    {
        expPercent = level.getCurrentExp()* 100 / level.getUpLvExp();
    }
    
    public void Draw(Graphics g)
    {
        
        for (int i = 0; i < expPercent; i++)
        {
            image[i].Draw(g);
        }
    }
}
