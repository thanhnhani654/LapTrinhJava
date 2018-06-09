/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import Utility.Tag;
import java.awt.Graphics;
import laptrinhjava.ImageDraw;
import Component.GGrid;
import Utility.Int2;
import UI.BottomMenu;
import laptrinhjava.Scene.Scene;
import laptrinhjava.Scene.SceneManager;
import Component.TurnBaseSystem;
/**
 *
 * @author Nhan
 */
public class Gate extends GameObject {
    
    private ImageDraw downImage;
    private ImageDraw upImage;
    
    private static int currentLV = 0;
    
    boolean bDown = false;
    
    public Gate(int x, int y, int id)
    {
        tag = Tag.eTag.T_gate;
        this.id = id;
        
        Int2 temp = GGrid.convertToNormalPos(new Int2(x,y));
        
        downImage = new ImageDraw("src/Resources/OpenGate.png",temp.x,temp.y,100, 100);
        upImage = new ImageDraw("src/Resources/CloseGate.png",temp.x,temp.y,100, 100);
        GGrid.getInst().attachTile(new Int2(x,y), tag, id);
    }
    
    public void goDown()
    {
        currentLV++;
        BottomMenu.getInst().deActive();
        Scene temp = SceneManager.getInstance().getCurrentScene();
        SceneManager.getInstance().deleteScene(temp);
        //TurnBaseSystem.getInstance().resetKey();
        Scene temp2 = SceneManager.getInstance().createScene(Scene.eSceneId.S_Dungeon);
        SceneManager.getInstance().setCurrentScene(temp2);   
    }
    
    public void exit()
    {
        Scene temp = SceneManager.getInstance().getCurrentScene();
        SceneManager.getInstance().deleteScene(temp);
        //TurnBaseSystem.getInstance().resetKey();
        Scene temp2 = SceneManager.getInstance().createScene(Scene.eSceneId.S_village);
        SceneManager.getInstance().setCurrentScene(temp2); 
    }
    
    public void setDownGate()
    {
        bDown = true;
    }
    
    public void setExitGate()
    {
        bDown = false;
    }
    
    public boolean isDownGate()
    {
        return bDown;
    }
    
    public void Update()
    {
        
    }
    
    public void Draw (Graphics g)
    {
        if (bDown)
        {
            downImage.Draw(g);
        }
        else
        {
            upImage.Draw(g);
        }
    }
}
