/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laptrinhjava;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
/**
 *
 * @author Nhan
 */
public class LapTrinhJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame f = new JFrame();
        Game m=new Game(f);
        f.add(m);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
	f.setSize(816,640);
	f.setVisible(true);
        
    }
    
}
