/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laptrinhjava;

import Component.ItemCarrier;
import GameObject.Player;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Utility.Int2;
//Input
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.MouseInfo;
import laptrinhjava.Scene.*;
import Utility.LoadJSon;
import Component.NumberComponent;
import Component.NumberManager;
import Utility.LoadJSon;

/**
 *
 * @author Nhan
 */
public class Game extends JPanel implements MouseListener{
    static int count = 0;
    SceneManager sceneManager;
    public static boolean bMousePressed;
    public static boolean bMouseReleased;
    public static Int2 mousePos;
    NumberManager numManager;
    
    public Game(JFrame f)
    {
        numManager = NumberManager.getInstance();
        LoadJSon loader = new LoadJSon();
        //NumberComponent.createNumber("0123456789",200,200,0.6f,0.6f);
        //NumberComponent a= new NumberComponent("0123456789",200,200,0.6f,0.6f);
        sceneManager.getInstance().createScene(Scene.eSceneId.S_start);
        sceneManager.getInstance().setCurrentScene(Scene.eSceneId.S_start);
        addMouseListener(this);
        mousePos = new Int2(0,0);
        
    }
    public void run(float dt)
    {
        mousePos.x = MouseInfo.getPointerInfo().getLocation().x;
        mousePos.y = MouseInfo.getPointerInfo().getLocation().y;
        
        GButton.update();
        GameObject.GameObject.Destroyer();
        sceneManager.getInstance().Update(dt);
    }
    public void paintComponent(Graphics g){
        this.run(1.0f);
        
        
        sceneManager.getInstance().Draw(g);
        numManager.Draw(g);
        //GButton.draw(g);
        repaint(); 
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public void mousePressed(MouseEvent e) 
    {
       GButton.updateEventClicked(e.getX(), e.getY());
       Player.getInst().getMousePress(e.getX(), e.getY());
       bMousePressed = true;
       bMouseReleased = false;
       
        
    }

    public void mouseReleased(MouseEvent e) 
    {
       GButton.updateEventReleased(e.getX(), e.getY());
       bMousePressed = false;
       bMouseReleased = true;
        
    }

    public void mouseEntered(MouseEvent e) 
    {

    }

    public void mouseExited(MouseEvent e) 
    {
            
    }

    public void mouseClicked(MouseEvent e) 
    {
       
    }
}
