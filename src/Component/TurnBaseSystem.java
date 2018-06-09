/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component;

/**
 *
 * @author Nhan
 */

public class TurnBaseSystem {
    private static TurnBaseSystem inst = null;
    public static TurnBaseSystem getInstance()
    {
        if (inst == null)
            inst = new TurnBaseSystem();
        return inst;
    }
    public boolean disable = false;
    
    public KeyTurnBase []key = {new KeyTurnBase(0), new KeyTurnBase(1), new KeyTurnBase(2), new KeyTurnBase(3)
                        ,new KeyTurnBase(4), new KeyTurnBase(5), new KeyTurnBase(6), new KeyTurnBase(7)};
    KeyTurnBase currentKey = key[0];
    
    public TurnBaseSystem()
    {
        resetKey();  
        key[0].free =true;
    }
    
    public int getKey()
    {
         for (KeyTurnBase l : key)
         {
             if (l.free)
             {
                 l.free = false;
                 System.out.println(l.ID);
                 return l.ID;
             }
         }
         return -1;
    }
    
    public void freeKey(int id)
    {
        key[id].free = true;
    }
    
    public boolean getKeyValue(int id)
    {
        if (id < 8)
            return key[id].key;
        return false;
    }
    
    public void setKeyValue(int id)
    {
        for (KeyTurnBase l : key)
        {
             if (l.ID != id)
                 l.key = false;
             else
             {
                 l.key = true;
                 currentKey = l;
             }
        }
    }
    
    public void resetKey()
    {
        System.out.println("CALL CONSTRUCTOR TURNBASE SYSTEM");
        for (KeyTurnBase l : key)
        {
            l.key = false;
            l.free = true;
        }
        key[0].key = true;
        key[0].free = false;
        currentKey = key[0];
    }
    
    public void nextKey()
    {
        currentKey.key = false;
        int i = currentKey.ID;
        while (true)
        {
            i++;
            if (i < 7)
            {
                if (key[i].free)
                {
                    continue;
                }
                else
                {
                    key[i].key = true;
                    currentKey = key[i];
                    break;
                }
            }
            else
            {
                key[0].key = true;
                currentKey = key[0];
                break;
            }
        }
    }
}
