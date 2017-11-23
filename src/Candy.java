package gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Candy extends JPanel {
    private final int SIZE = 8;
    public static final int SIZESPACE = 75;
    private int color;//0,1,2,3 for red, yellow, blue, green
    private int position;
    private int x;
    private int y;
    public int xpos;
    public int ypos;


    private static ImageIcon redCandy = new ImageIcon("redcandy.png");
    private static ImageIcon yellowCandy = new ImageIcon("yellowcandy.png");
    private static ImageIcon greenCandy = new ImageIcon("greenCandy.png");
    private static ImageIcon blueCandy = new ImageIcon("bluecandy.png");

    private Main main;

    Candy(int c, int x, int y) {
        this.color = c;
        this.x = x;
        this.y = y;
        this.xpos = 30 + y * SIZESPACE;
        this.ypos = 20 + x * SIZESPACE-700;
    }

    public int checkRightPosition() {
        if (ypos == (20 + x * SIZESPACE)) {
            return 0;
        }
        else if (ypos < (20 + x * SIZESPACE)) {
            return 1;
        }
        else
            return -1;
    }

    public void moveCandy(int speedx, int speedy) {
        xpos = xpos + speedx;
        ypos = ypos + speedy;
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int c) {
        this.color = c;
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

    public void paintCandy(Graphics2D g) {
        //System.out.println("paint " + xpos + ", " + ypos);
//        g.setColor(new Color(0, 0, 70, 100));
//        g.fillRoundRect(xpos, ypos, 70, 70, 30, 30);
        switch (color) {
            case 0:
                redCandy.paintIcon(this, g, xpos, ypos);
                break;
            case 1:
                yellowCandy.paintIcon(this, g,xpos, ypos);
                break;
            case 2:
                greenCandy.paintIcon(this, g, xpos, ypos);
                break;
            case 3:
                blueCandy.paintIcon(this, g, xpos, ypos);
                break;
        }
    }
}