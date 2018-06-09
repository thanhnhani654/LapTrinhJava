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
public class MpComponent {
    private int maxMp;
    private int mp;
    
    public MpComponent ()
    {
    }
    
    public void setMaxMp(int maxhp)
    {
        this.maxMp = maxhp;
    }
    
    public void setMp (int mp)
    {
        this.mp = mp;
    }
    
    public int getMaxMp()
    {
        return maxMp;
    }
    
    public int getMp()
    {
        return mp;
    }
    
    public void doDelta(int delta)
    {
        mp += delta;
        if (mp > maxMp)
            mp = maxMp;
        if (mp < 0)
            mp = 0;
    }
}
