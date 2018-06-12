/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laptrinhjava;

import java.awt.Graphics;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import Utility.IntRect;
/**
 *
 * @author Nhan
 */
public class ImageDraw {
    private BufferedImage image = null;
    int x,y,width,height;
    IntRect posRect;
    String path;
    
    public ImageDraw(String path, int x, int y, int width, int height)
    {
        Initialize(path, x,y,width,height);
    }

    public IntRect getPosRect() {
        return posRect;
    }
    
    public ImageDraw (ImageDraw image)
    {
        Initialize(image.getPath(),image.getPosRect().x, image.posRect.y, image.getPosRect().width, image.getPosRect().height);
    }
    
    private void Initialize(String path, int x, int y, int width, int height)
    {
        this.path = path;
        try
        {
            File file = new File(path);
            image = ImageIO.read(file);
            posRect = new IntRect(x,y,width,height);
        } catch (IOException ex)
        {
            System.out.printf(path);
            System.out.printf(" ");
            System.out.printf("is wrong path!");
        }
    }

    public String getPath() {
        return path;
    }
    
    public void setPosition(int x, int y)
    {
        posRect.x = x;
        posRect.y = y;
    }
    
    public IntRect getPosition()
    {
        return posRect;
    }
    
    public void Draw(Graphics g)
    {
        g.drawImage(image, posRect.x,posRect.y,posRect.width,posRect.height, null);
    }
    
}
