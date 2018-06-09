/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laptrinhjava.Scene;

import GameObject.Player;
import java.awt.Graphics;
import laptrinhjava.*;
import javax.swing.JPanel;
/**
 *
 * @author Nhan
 */
public class ChoseScene extends Scene {
    GButton Hero1;
     
    public ChoseScene()
    {
         super();
         id = Scene.eSceneId.S_chosehero;
         backGround = new ImageDraw("src/Resources/BackGround.png",0,0,WidthSize, HeightSize);
         Hero1 = new GButton("src/Resources/HeroChose.png",100,225,200,150);
    }
    
    public void destroy()
    {
        deleted = true;
        Hero1.deleteButton();
    }
    
    public void Update(float dt)
    {
        if (Hero1.IsClicked())
        {
            SceneManager.getInstance().createScene(Scene.eSceneId.S_village);
            SceneManager.getInstance().setCurrentScene(Scene.eSceneId.S_village);
            System.out.println("Hero1 Button pressed");
            Player.role = Player.eRole.R_melee;
            destroy();
        }
    }
    
    public void Draw(Graphics g)
    {
        backGround.Draw(g);
        Hero1.drawButton(g);
    }
}
