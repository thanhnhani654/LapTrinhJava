/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laptrinhjava.Scene;

import UI.BottomMenu;
import Component.GGrid;
import java.awt.Graphics;
import laptrinhjava.ImageDraw;
import Utility.Int2;
import Utility.Tag;
import GameObject.*;
import java.util.ArrayList;
import java.util.List;
import laptrinhjava.GButton;
import GameObject.GameObject;
import Component.TurnBaseSystem;
import UI.TopUi;
/**
 *
 * @author Nhan
 */
public class DungeonScene extends Scene {
    protected ImageDraw bottomBackground;
    protected ImageDraw topBackground;
    GGrid grid;
    BottomMenu bottomMenu;
    Player player;
    Gate gate;
    int idCount = 1;
    TopUi topUI;
    
    protected List<GameObject> listObject = new ArrayList<GameObject>();
    
    public DungeonScene ()
    {
        id = Scene.eSceneId.S_Dungeon;
        backGround = new ImageDraw("src/Resources/BackGround.png",0,0,WidthSize, HeightSize);
        bottomBackground = new ImageDraw("src/Resources/BottomBackground.png",0,HeightSize - 100,WidthSize, 100);
        topBackground = new ImageDraw("src/Resources/TopBackground.png",0,0,WidthSize, 100);
        topUI = TopUi.getInst();
        //Su dung laji BottomMenu
        bottomMenu = BottomMenu.getInst();
        bottomMenu.active();
        grid = GGrid.getInst();
        grid.clearGrid();
        player = Player.getInst();
        player.setPosition(new Int2(0,0));
        
        listObject.add(player.getCharacter());
        //gate = new Gate(6,i,1);
        //gate.setExitGate();
        TurnBaseSystem.getInstance().resetKey();
        createGate(false, new Int2(4,0));
        createEnemy(Tag.eTag.T_enemy, new Int2(2,1));
        createWall(Tag.eTag.T_wall, new Int2(1,1));
        //System.out.println("DUNEGEON KEY" + TurnBaseSystem.getInstance().key[1].free);
    }
    
    
    public void destroy()
    {
        //gate.destroy();
        
        for (GameObject l : listObject)
        {
            System.out.println(l.getId());
            if (l.getId() != 0)
                l.destroy();
        }
        //listObject.clear();
        GGrid.getInst().clearGrid();
    }
    
    
    public void Update (float dt)
    {
        if (!isActive())
            return;
        bottomMenu.Update();
        topUI.Update();
        player.Update();
        
        for(GameObject l : listObject)
        {
            l.Update();
        }
        
    }
    
    public void Draw (Graphics g)
    {
         //Draw BackGround
        backGround.Draw(g);
        topBackground.Draw(g);
        bottomBackground.Draw(g);
        topUI.Draw(g);
        BottomMenu.getInst().Draw(g);
        //DrawGrid
        grid.Draw(g);
        //DrawGame Object
        Player.getInst().Draw(g);
        //gate.Draw(g);
        for (GameObject l : listObject)
        {
            l.Draw(g);
        }
        
        //Button last
    }
    
    //Truyen toa do va Tag Enemy. Moi Enemy co 1 Tag rieng nen truyen Tag nao thi ra Enemy do
    public void createEnemy(Tag.eTag tag, Int2 pos)
    {
        switch (tag)
        {
            case T_enemy:
                Enemy  enemy;
                enemy = new Enemy(pos.x,pos.y,idCount);
                idCount++;
                listObject.add(enemy);
                break;
        }
    }
    
    //Neu co lam pickItem thi se co nhieu loai vat the. Neu khong lam thi chi co wall
    public void createWall(Tag.eTag tag, Int2 pos)
    {
        switch (tag)
        {
            case T_wall:
                Wall wall = new Wall(pos.x, pos.y, idCount);
                idCount++;
                listObject.add(wall);
                break;
                
        }
    }
    
    //Moi dungeon co 2 Gate
    public void createGate(boolean isDownGate, Int2 pos)
    {
        Gate gate = new Gate(pos.x,pos.y, idCount);
        idCount++;
        listObject.add(gate);
    }
}
