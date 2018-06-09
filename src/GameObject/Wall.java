/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import Utility.Tag;
import Utility.Int2;
import java.awt.Graphics;
import laptrinhjava.ImageDraw;
import Component.GGrid;
/**
 *
 * @author Nhan
 */
public class Wall extends GameObject {
    ImageDraw image;
    
    public Wall (int x, int y, int id)  //Su dung GridPos
    {
        this.id = id;
        Int2 temp = GGrid.convertToNormalPos(new Int2(x,y));
        
        image = new ImageDraw("src/Resources/Wall.png", 0,0,100,100);
        tag = Tag.eTag.T_wall;
        setPosition(new Int2(x,y));
        image.setPosition(pos.x, pos.y);
    }
    
    public void Update()
    {
        
    }
    
    public void Draw(Graphics g)
    {
        image.Draw(g);
    }
}
