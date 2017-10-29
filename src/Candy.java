import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Candy extends JPanel implements KeyListener, ActionListener {
    private final int SIZE = 8;
    private final int SIZESPACE = 75;
    private int color;//0,1,2,3 for red, yellow, blue, green
    private int position;
    private int x;
    private int y;
    private int xpos;
    private int ypos;

    private boolean move;
    Timer timer;
    int delay = 20;



    Candy(int c, int x, int y) {
        this.color = c;
        this.x = x;
        this.y = y;
        this.xpos = 30+y*SIZESPACE;
        this.ypos = 20+x*SIZESPACE;
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int c) {
        this.color=c;
    }

    public void swap(Candy othercandy) {
        int temp;
        temp = this.getColor();
        this.setColor(othercandy.getColor());
        othercandy.setColor(temp);
    }

    public int getXpos() {
        return xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }

    public void paintCandy(Graphics g) {
        g.setColor(new Color(0,0,70,100));
        g.fillRoundRect(xpos, ypos,70,70, 30, 30);
        switch(color) {
            case 0:
                ImageIcon redCandy = new ImageIcon("redcandy.png");
                redCandy.paintIcon(this, g, xpos, ypos);
                break;
            case 1:
                ImageIcon yellowCandy = new ImageIcon("yellowcandy.png");
                yellowCandy.paintIcon(this, g,xpos, ypos);
                break;
            case 2:
                ImageIcon greenCandy = new ImageIcon("greenCandy.png");
                greenCandy.paintIcon(this, g, xpos, ypos);
                break;
            case 3:
                ImageIcon blueCandy = new ImageIcon("bluecandy.png");
                blueCandy.paintIcon(this, g, xpos, ypos);
                break;
        }
    }

    public void moveCandy(Graphics g, int x, int y) {
        if (x!=y) {
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(move == true) {
            xpos+=1;
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}