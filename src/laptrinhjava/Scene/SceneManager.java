/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laptrinhjava.Scene;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
/**
 *
 * @author Nhan
 */
public class SceneManager {
    static private SceneManager instance = null;
    static protected List<Scene> listScene = new ArrayList<Scene>();
    Scene currentScene;
    Scene previousScene;
    
    static public SceneManager getInstance()
    {
        if (instance == null)
        {
            instance = new SceneManager();
        }
        return instance;
    }
    
    public void addScene(Scene s)
    {
        listScene.add(s);
    }
    
    public Scene getScene(Scene.eSceneId id)
    {
        for (Scene l : listScene)
        {
            if (l.getId() == id)
                return l;
        }
        return null;
    }
    
    public void deleteScene(Scene.eSceneId id)
    {
        for (Scene l : listScene)
        {
            if (l.getId() == id)
            {
                l.destroy();
                listScene.remove(l);
            }
        }
    }
    
    public void deleteScene(Scene s)
    {
        s.destroy();
        listScene.remove(s);
    }
    
    public void setCurrentScene(Scene.eSceneId id)
    {
        for (Scene l : listScene)
        {
            if (l.getId() == id)
            {
                previousScene = currentScene;
                currentScene = l;
                System.out.println(currentScene.toString());
            }
        }
    }
    
    public void setCurrentScene(Scene s)
    {
        for (Scene l : listScene)
        {
            if (l == s)
            {
                previousScene = currentScene;
                currentScene = l;
                System.out.println(currentScene.toString());
            }
        }
    }
    
    public Scene getCurrentScene()
    {
        return currentScene;
    }
    
    //Debug
    public int getSize()
    {
        return listScene.size();
    }
    
    public void Update (float dt)
    {
        if (currentScene != null)
            currentScene.Update(dt);
        for (Scene l : listScene)
        {
            if (l.deleted)
            {
                this.deleteScene(l.getId());
            }
        }
    }
    
    public void Draw (Graphics g)
    {
        if (currentScene != null)
        {
            currentScene.Draw(g);   
        }
    }
    
    public Scene createScene(Scene.eSceneId id)
    {
        Scene s;
        switch (id)
        {
            case S_start:
                s = new StartScene();
                break;
            case S_chosehero:
                s = new ChoseScene();
                break;
            case S_village:
                s = new VillageScene();
                break;
            case S_inventory:
                s = new InventoryScene();
                break;
            case S_Dungeon:
                s = new DungeonScene();
                break;
            default:
                s = null;
                break;
        }
        return s;
    }
}
