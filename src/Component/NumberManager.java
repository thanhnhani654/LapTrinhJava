/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nhan
 */
public class NumberManager {
    public static NumberManager inst = null;
    public static NumberManager getInstance()
    {
        if (inst == null)
            inst = new NumberManager();
        return inst;
    }
    public List<NumberComponent> listNumber = new ArrayList<NumberComponent>();
    NumberManager()
    {}
    
    public void Draw(Graphics g)
    {
        for (NumberComponent l : listNumber)
        {
            l.Draw(g);
        }
    }
    
    public void destroyProcess()
    {
        NumberComponent temp = null;
        for (NumberComponent l : listNumber)
        {
            if (l.deleted)
            {
                temp = l;
                break;
            }
        }
        listNumber.remove(temp);
        temp = null;
    }
        
}
