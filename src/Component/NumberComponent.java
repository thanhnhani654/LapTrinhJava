/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component;
import laptrinhjava.ImageDraw;
import Utility.Int2;
import java.awt.Graphics;
/**
 *
 * @author Nhan
 */
public class NumberComponent {
    String text;
    String[] tchar;
    ImageDraw[] image;
    Int2 pos;

    public void setPos(Int2 pos) {
        this.pos = pos;
        for (int i = 0; i< text.length(); i++)
        {
            image[i].setPosition((int)(pos.x + i*size.x*align), pos.y);
        }
    }
    public void setPos(int x, int y) {       
        this.pos = new Int2(x,y);
        for (int i = 0; i< text.length(); i++)
        {
            image[i].setPosition((int)(pos.x + i*size.x*align), pos.y);
        }
    }
    
    Int2 size;
    int length;
    float scaleX;
    float scaleY;
    float align = 0.3f;
    public boolean bActive = true;
    public boolean deleted = false;
    public static NumberComponent createNumber(String text, int x, int y)
    {
        return new NumberComponent(text, x,y);
    }
    
    public static NumberComponent createNumber(String text, int x, int y, float scaleX, float scaleY)        
    {
        return new NumberComponent(text, x,y,scaleX,scaleY);
    }
    
    public NumberComponent (String text, int x, int y)
    {
        scaleX = 1;
        scaleY = 1;
        Initialize(text, x, y);
        
    }
    
    public NumberComponent(String text, int x, int y, float scaleX, float scaleY)
    {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        Initialize(text, x,y);
    }
    
    public void Initialize(String text, int x, int y)
    {
        pos = new Int2(x,y);
        size = new Int2(25,25);
        image = new ImageDraw[text.length()];
        tchar = new String[text.length()];
        this.text = text;
        length = text.length();
        analys();
        ImageInitialize();
        NumberManager.getInstance().listNumber.add(this);
    }
    
    private void analys()
    {
        int convert = Integer.parseInt(text);
        int length = text.length();
        for (int i = 0; i < length; i++)
        {
            int temp = convert - (int)(convert / 10)*10 ;
            convert /= 10;
            tchar[length-i - 1] = Integer.toString(temp);
        }
    }
    
    private void ImageInitialize()
    {
        size.x = (int)(size.x * scaleX);
        size.y = (int)(size.y * scaleY);
        String path = "src/Resources/T0.png";
        for (int i = 0; i< text.length(); i++)
        {
            switch(Integer.parseInt(tchar[i]))
            {
                case 0:
                    path = "src/Resources/T0.png"; 
                    break;
                case 1:
                    path = "src/Resources/T1.png";
                    break;
                case 2:
                    path = "src/Resources/T2.png";
                    break;
                case 3:
                    path = "src/Resources/T3.png";
                    break;
                case 4:
                    path = "src/Resources/T4.png";
                    break;
                case 5:
                    path = "src/Resources/T5.png";
                    break;
                case 6:
                    path = "src/Resources/T6.png";
                    break;
                case 7:
                    path = "src/Resources/T7.png";
                    break;
                case 8:
                    path = "src/Resources/T8.png";
                    break;
                case 9:
                    path = "src/Resources/T9.png";                    
                    break;       
            }
            image[i] = new ImageDraw(path,(int)(pos.x + i*size.x*align),pos.y, size.x, size.y);
        }
    }
    
    public void Draw(Graphics g)
    {
        if (!bActive)
            return;
        for (ImageDraw l : image)
        {
            l.Draw(g);
        }
    }
    
    public void Clear()
    {
        for (int i = 0; i < length; i++)
        {
            tchar[i] = "";
        }
        for (int i = 0; i < length; i++)
        {
            image[i] = null;
        }
        length = 0;
    }
    
    public void updateText(String text)
    {
        if (this.text.equals(text))
            return;
        Clear();
        this.text = text;
        length = text.length();
        image = new ImageDraw[length];
        tchar = new String[length];
        size = new Int2(25,25);
        analys();
        ImageInitialize();
    }
}
