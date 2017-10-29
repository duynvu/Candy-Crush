import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;


public class Board extends JPanel implements MouseListener, ActionListener {
    private final int SIZE = 8;
    private final int SIZEOFPIC = 70;
    private final int SIZESPACE = 75;
    private Candy[][] board = new Candy[SIZE][SIZE];

    private ImageIcon backGround;

    int i1, i2, i3, i4;
    int s1, s2;
    static int count = 0;
    Random rand = new Random();

    int x, y;

    private Timer timer;
    private int delay = 100;

    public Board() {
        create();
        while (checkTrue()) {}
        printBoard();
        System.out.println("Ready to get input.");
        addMouseListener(this);
        //setFocusable(true);
        //timer = new Timer(delay, this);
        //timer.start();
    }

    public void test() {
        while (true) {
            getInput();
            swap(s1, s2);
            printBoard();
        }
    }

    public void play() {
        while (!checkEndGame()) {
            getInput();
            swap(s1, s2);
            if (!checkTrue()) {
                swap(s1, s2);
                System.out.println("Wrong Candy");
            }
            while (checkTrue()) {
            }
            System.out.println("New board");
            printBoard();
            repaint();
        }
        System.out.println("Game Over");
    }

    private void create() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Candy candy = new Candy(rand.nextInt(4), i, j);
                this.board[i][j] = candy;
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j].getColor() + "  ");
            }
            System.out.println();
        }
    }

    public void swap(int p1, int p2) {
        board[p1 / SIZE][p1 % SIZE].swap(board[p2 / SIZE][p2 % SIZE]);
    }

    public boolean checkTrue() {
        //Check vertical
        for (int i = 0; i < SIZE - 2; i++) {
            for (int j = 0; j < SIZE; j++) {
                int count = 1;
                int k = i;
                while (board[k][j].getColor() == board[k + 1][j].getColor()) {
                    count++;
                    if (k < SIZE - 2) {
                        k++;
                    } else {
                        break;
                    }
                }
                if (count >= 3) {
                    k = i;
                    while (board[k][j].getColor() == board[k + 1][j].getColor()) {
                        board[k][j].setColor(-1);
                        if (k < SIZE - 2) {
                            k++;
                        } else {
                            break;
                        }
                    }
                    board[k][j].setColor(-1);
                    moveDown(count);
                    return true;
                }
            }
        }
        //Check horizontal
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE - 2; j++) {
                int count = 1;
                int k = j;
                while (board[i][k].getColor() == board[i][k + 1].getColor()) {
                    count++;
                    if (k < SIZE - 2) {
                        k++;
                    } else {
                        break;
                    }
                }
                if (count >= 3) {
                    k = j;
                    while (board[i][k].getColor() == board[i][k + 1].getColor()) {
                        board[i][k].setColor(-1);
                        if (k < SIZE - 2) {
                            k++;
                        } else {
                            break;
                        }
                    }
                    board[i][k].setColor(-1);
                    moveDown(1);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkPosition(int row, int column) {
        int countVertical = 0, countHorizontal = 0;
        int[] listVertical = new int[SIZE];
        int[] listHorizontal = new int[SIZE];
        int i, k;
        //check Vertical
        if (row > 0) {
            i = row - 1;
            k = column;
            while (board[row][column].getColor() == board[i][k].getColor()) {
                listVertical[countVertical] = i * SIZE + k;
                countVertical++;
                if (i != 0) {
                    i--;
                } else
                    break;
            }
        }
        if (row < SIZE - 1) {
            i = row + 1;
            k = column;
            while (board[row][column].getColor() == board[i][k].getColor()) {
                listVertical[countVertical] = i * SIZE + k;
                countVertical++;
                if (i != SIZE - 1) {
                    i++;
                } else
                    break;
            }
        }
        //check Horizontal
        if (column > 0) {
            i = row;
            k = column - 1;
            while (board[row][column].getColor() == board[i][k].getColor()) {
                listHorizontal[countHorizontal] = i * SIZE + k;
                countHorizontal++;
                if (k != 0) {
                    k--;
                } else
                    break;
            }
        }
        if (column < SIZE - 1) {
            i = row;
            k = column + 1;
            while (board[row][column].getColor() == board[i][k].getColor()) {
                listHorizontal[countHorizontal] = i * SIZE + k;
                countHorizontal++;
                if (k != SIZE - 1) {
                    k++;
                } else
                    break;
            }
        }
        //Check for pos
        if (countHorizontal >= 2) {
            System.out.println("True horizontal");
            board[row][column].setColor(-1);
            for(int j=0; j<countHorizontal; j++) {
                board[listHorizontal[j]/SIZE][listHorizontal[j]%SIZE].setColor(-1);
            }
            moveDown(1);
            return true;
        }

        if (countVertical >= 2) {
            System.out.print("True vertical: ");
            board[row][column].setColor(-1);
            for(int j=0; j<countVertical; j++) {
                board[listVertical[j]/SIZE][listVertical[j]%SIZE].setColor(-1);
            }
            moveDown(countVertical+1);
            return true;
        }
        return false;
    }

    public void moveDown(int step) {
        for (int i = SIZE-1; i >=step; i--) {
            for (int j = SIZE-1; j>=0; j--) {
                int k=0;
                if(board[i][j].getColor() == -1) {
                    board[i][j].swap(board[i-step][j]);
                }
            }
        }

        for (int i = SIZE-1; i >=0; i--) {
            for (int j = SIZE-1; j>=0; j--) {
                if(board[i][j].getColor() == -1) {
                    board[i][j].setColor(rand.nextInt(4));
                }
            }
        }
    }

    public void getInput() {
        Scanner scan = new Scanner(System.in);
        System.out.print("P1: ");
        i1=scan.nextInt();
        System.out.print("p2: ");
        i2=scan.nextInt();
        System.out.print("p3: ");
        i3=scan.nextInt();
        System.out.print("p4: ");
        i4=scan.nextInt();
        s1 = i1*SIZE+i2;
        s2 = i3*SIZE+i4;
        System.out.println(i1+" "+i2+" "+i3+" "+i4);
        System.out.println(s1);
        System.out.println(s2);
    }

    public boolean checkEndGame() {
        // Check for 2x3
        // check the pattern [i][j]-[i+1][j+1]-[i][j+2]
        for(int i = 0; i < SIZE-1; i++) {
            for(int j = 0; j<SIZE-2; j++) {
                if ((board[i][j].getColor() == board[i+1][j+1].getColor()) && (board[i][j].getColor() == board[i][j+2].getColor())) {
                    return false;
                }
            }
        }

        // check the pattern [i][j]-[i-1][j+1]-[i][j+2]
        for(int i = 1; i < SIZE; i++) {
            for(int j = 0; j<SIZE-2; j++) {
                if ((board[i][j].getColor() == board[i-1][j+1].getColor()) && (board[i][j].getColor() == board[i][j+2].getColor())) {
                    return false;
                }
            }
        }

        // check the pattern [i][j]-[i][j+1]-[i+1][j+2]
        for(int i = 0; i < SIZE-1; i++) {
            for(int j = 0; j<SIZE-2; j++) {
                if ((board[i][j].getColor() == board[i][j+1].getColor()) && (board[i][j].getColor() == board[i+1][j+2].getColor())) {
                    return false;
                }
            }
        }

        // check the pattern [i][j]-[i][j+1]-[i-1][j+2]
        for(int i = 1; i < SIZE; i++) {
            for(int j = 0; j<SIZE-2; j++) {
                if ((board[i][j].getColor() == board[i][j+1].getColor()) && (board[i][j].getColor() == board[i+1][j+2].getColor())) {
                    return false;
                }
            }
        }

        // check the pattern [i][j]-[i+1][j+1]-[i+1][j+2]
        for(int i = 0; i < SIZE-1; i++) {
            for(int j = 0; j<SIZE-2; j++) {
                if ((board[i][j].getColor() == board[i+1][j+1].getColor()) && (board[i][j].getColor() == board[i+1][j+2].getColor())) {
                    return false;
                }
            }
        }

        // check the pattern [i][j]-[i-1][j+1]-[i-1][j+2]
        for(int i = 1; i < SIZE; i++) {
            for(int j = 0; j<SIZE-2; j++) {
                if ((board[i][j].getColor() == board[i-1][j+1].getColor()) && (board[i][j].getColor() == board[i+1][j+2].getColor())) {
                    return false;
                }
            }
        }

        // Check for 3x2
        // check the pattern [i][j]-[i+1][j+1]-[i+2][j]
        for(int i = 0; i < SIZE-2; i++) {
            for(int j = 0; j<SIZE-1; j++) {
                if ((board[i][j].getColor() == board[i+1][j+1].getColor()) && (board[i][j].getColor() == board[i+2][j].getColor())) {
                    return false;
                }
            }
        }

        // check the pattern [i][j]-[i+1][j-1]-[i+2][j]
        for(int i = 0; i < SIZE-2; i++) {
            for(int j = 1; j<SIZE; j++) {
                if ((board[i][j].getColor() == board[i-1][j-1].getColor()) && (board[i][j].getColor() == board[i+2][j].getColor())) {
                    return false;
                }
            }
        }

        // check the pattern [i][j]-[i+1][j]-[i+2][j+1]
        for(int i = 0; i < SIZE-2; i++) {
            for(int j = 0; j<SIZE-1; j++) {
                if ((board[i][j].getColor() == board[i+1][j].getColor()) && (board[i][j].getColor() == board[i+2][j+1].getColor())) {
                    return false;
                }
            }
        }

        // check the pattern [i][j]-[i+1][j+1]-[i+2][j+1]
        for(int i = 0; i < SIZE-2; i++) {
            for(int j = 0; j<SIZE-1; j++) {
                if ((board[i][j].getColor() == board[i+1][j+1].getColor()) && (board[i][j].getColor() == board[i+2][j+1].getColor())) {
                    return false;
                }
            }
        }

        // check the pattern [i][j]-[i+1][j]-[i+2][j-1]
        for(int i = 0; i < SIZE-2; i++) {
            for(int j = 1; j<SIZE; j++) {
                if ((board[i][j].getColor() == board[i+1][j].getColor()) && (board[i][j].getColor() == board[i+2][j-1].getColor())) {
                    return false;
                }
            }
        }

        // check the pattern [i][j]-[i+1][j-1]-[i+2][j-1]
        for(int i = 0; i < SIZE-2; i++) {
            for(int j = 1; j<SIZE; j++) {
                if ((board[i][j].getColor() == board[i+1][j-1].getColor()) && (board[i][j].getColor() == board[i+2][j-1].getColor())) {
                    return false;
                }
            }
        }

        // Check 4x1
        // check the pattern [i][j]-[i][j+1]-[i][j+3]
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j<SIZE-3; j++) {
                if ((board[i][j].getColor() == board[i][j+1].getColor()) && (board[i][j].getColor() == board[i][j+3].getColor())) {
                    return false;
                }
            }
        }

        // check the pattern [i][j]-[i][j+2]-[i][j+3]
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j<SIZE-3; j++) {
                if ((board[i][j].getColor() == board[i][j+2].getColor()) && (board[i][j].getColor() == board[i][j+3].getColor())) {
                    return false;
                }
            }
        }

        // Check 1x4

        // check the pattern [i][j]-[i+1][j]-[i+3][j]
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j<SIZE-3; j++) {
                if ((board[i][j].getColor() == board[i+1][j].getColor()) && (board[i][j].getColor() == board[i+3][j].getColor())) {
                    return false;
                }
            }
        }

        // check the pattern [i][j]-[i+2][j]-[i+3][j]
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j<SIZE-3; j++) {
                if ((board[i][j].getColor() == board[i+2][j].getColor()) && (board[i][j].getColor() == board[i+3][j].getColor())) {
                    return false;
                }
            }
        }

        return true;
    }

    public void paint(Graphics g) {
        //draw blackground
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 900, 900);

        //draw background picture
        backGround = new ImageIcon("background.jpg");
        backGround.paintIcon(this, g,0, 0);

        for(int i=0; i<SIZE; i++) {
            for(int j = 0; j<SIZE; j++) {
                board[i][j].paintCandy(g);
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

    }
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Clicked");
        x = (e.getY()-20)/SIZESPACE;
        y = (e.getX()-30)/SIZESPACE;
        if(count==0) {
            i1 = (e.getY()-20)/SIZESPACE;
            i2 = (e.getX()-30)/SIZESPACE;
            count++;
            System.out.println("first choose");
            System.out.println(count+ " "+x +" "+y);
        }
        else {
            i3 = (e.getY()-20)/SIZESPACE;
            i4 = (e.getX()-30)/SIZESPACE;
            s1 = i1*SIZE+i2;
            s2 = i3*SIZE+i4;
            swap(s1,s2);
            count=0;
            System.out.println("Swap1");
            if(checkPosition(i1,i2)) {
                System.out.println("Pos1 true");
            }
            if(checkPosition(i3,i4)) {
                System.out.println("Pos2 true");
            }
            while(checkTrue()) {
                System.out.println("new");
                printBoard();
            }
            repaint();
            System.out.println(count+ " "+x +" "+y+" "+s1+" "+s2);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.print("Enter" );
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}