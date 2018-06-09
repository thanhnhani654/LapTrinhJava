/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laptrinhjava.Scene;

import UI.BottomMenu;
import laptrinhjava.*;
import java.awt.Graphics;
import Component.*;
import GameObject.*;
import Utility.Int2;
import UI.TopUi;

/**
 *
 * @author Nhan
 */
public class VillageScene extends Scene {
    protected ImageDraw bottomBackground;
    protected ImageDraw topBackground;
    GGrid grid;
    BottomMenu bottomMenu;
    TopUi topUi;
    
    Wall wall1;         //need destroy
    Gate gate;          //need destroy
    
    public VillageScene()
    {
        super();
        id = Scene.eSceneId.S_village;
        backGround = new ImageDraw("src/Resources/BackGround.png",0,0,WidthSize, HeightSize);
        bottomBackground = new ImageDraw("src/Resources/BottomBackground.png",0,HeightSize - 100,WidthSize, 100);
        topBackground = new ImageDraw("src/Resources/TopBackground.png",0,0,WidthSize, 100);
        grid = GGrid.getInst();
         //Khoi tao BottomMenu
        bottomMenu = BottomMenu.getInst();
        Player.getInst().getCharacter().setPosition(new Int2(0,0));
        Player.getInst().gameStart = true;
        topUi = TopUi.getInst();
        wall1 = new Wall(1,2,1);
        gate = new Gate(6,0,2);    
        gate.setDownGate();
    }
    
    public void destroy()
    {
        wall1.destroy();
        gate.destroy();
    }
    
    public void Update (float dt)
    {
        if (!isActive())
            return;
        bottomMenu.Update();
        Player.getInst().Update();
        topUi.Update();
        
    }
    
    public void Draw (Graphics g)
    {
        //Draw BackGround
        backGround.Draw(g);
        topBackground.Draw(g);
        bottomBackground.Draw(g);
        bottomMenu.Draw(g);
        topUi.Draw(g);
        //DrawGrid
        
        grid.Draw(g);
        //DrawGame Object
        
        Player.getInst().Draw(g);
        wall1.Draw(g);
        gate.Draw(g);
        
        
        //Button last

    }
}
