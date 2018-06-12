/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import Component.GGrid;
import Utility.Int2;
import Utility.Tag;
import java.awt.Graphics;
import laptrinhjava.ImageDraw;

/**
 *
 * @author Nhan
 */
public class StatNPC extends GameObject {
    ImageDraw image;
    
    public StatNPC (int x, int y, int id)  //Su dung GridPos
    {
        this.id = id;
        tag = Tag.eTag.T_statNPC;
        Int2 temp = GGrid.convertToNormalPos(new Int2(x,y));
        
        image = new ImageDraw("src/Resources/NPC1.png", 0,0,100,100);
        
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
