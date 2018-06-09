/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laptrinhjava.Scene;
import java.awt.Graphics;
import laptrinhjava.*;
import javax.swing.*;



    
/**
 *
 * @author Nhan
 */
public abstract class Scene {
    
    public static enum eSceneId{
        S_start,
        S_chosehero,
        S_village,
        S_inventory,
        S_Dungeon
    }
    
    protected eSceneId id;
    ImageDraw backGround;
    protected boolean deleted = false;
    protected boolean bActive = true;
    
    protected final int WidthSize = 800;
    protected final int HeightSize = 600;
    
    public eSceneId getId()
    {
        return this.id;
    }
    
    public Scene ()
    {
        SceneManager.getInstance().addScene(this);
    }
    
    public void active()
    {
        bActive = true;
        
    }
    
    public void deActive()
    {
        bActive = false;
    }
    
    public boolean isActive()
    {
        return bActive;
    }
    
    public abstract void destroy();
    
    public abstract void Update(float dt);

    
    public abstract void Draw(Graphics g);
   
}
