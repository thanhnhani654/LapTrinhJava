/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;
import Component.MpComponent;
import laptrinhjava.ImageDraw;
import java.awt.Graphics;
/**
 *
 * @author Nhan
 */
public class MpBar {
    MpComponent mp;
    ImageDraw[] image;
    int mpPercent;
    
    public MpBar(MpComponent mp)
    {
        this.mp = mp;
        image = new ImageDraw[100];
        for (int i = 0; i < 100; i++)
        {
            image[i] = new ImageDraw("src/Resources/MPBar.png",140+i*2,70,2,10);
        }
    }
    
    public void Update()
    {
        mpPercent = mp.getMp() * 100 / mp.getMaxMp();
    }
    
    public void Draw(Graphics g)
    {
        for (int i = 0; i < mpPercent; i++)
        {
            image[i].Draw(g);
        }
    }
}
