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
public class KeyTurnBase {
    public boolean key;
    public boolean free;
    public int ID;
    
    public KeyTurnBase(int ID)
    {
        this.ID = ID;
        this.key = false;
        this.free = true;
    }
}
