/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laptrinhjava.Scene;

import java.awt.Graphics;
import laptrinhjava.*;
import javax.swing.JPanel;


/**
 *
 * @author Nhan
 */
public class StartScene extends Scene {
    
    GButton startButton;
    public StartScene ()
    {
        super();
        id = eSceneId.S_start;
        backGround = new ImageDraw("src/Resources/BackGround.png",0,0,WidthSize, HeightSize);
        startButton = new GButton("src/Resources/ButtonStart.png",350,250,75,56);
    }
    
    public void active()
    {
        bActive = true;
    }
    
    public void destroy()
    {
        deleted = true;
        startButton.deleteButton();
    }
    
    public void Update(float dt)
    {
        if (startButton.IsClicked())
        {
            SceneManager.getInstance().createScene(Scene.eSceneId.S_chosehero);
            SceneManager.getInstance().setCurrentScene(Scene.eSceneId.S_chosehero);
            System.out.println("Start Button pressed");
            
            destroy();
        }
    }
    
    
    public void Draw(Graphics g)
    {
        backGround.Draw(g);
        startButton.drawButton(g);
    }
}
