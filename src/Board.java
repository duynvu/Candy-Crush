package gui;

import java.awt.*;
import java.util.Random;

import javax.swing.*;


public class Board extends JPanel {
    Main main;

    public static final int SIZE = 8;
    private final int SIZEOFPIC = 70;
    private final int SIZESPACE = 75;
    private Candy[][] board = new Candy[SIZE][SIZE];

    private ImageIcon backGround;

    int i1, i2, i3, i4;
    int s1, s2;
    Random rand = new Random();

    boolean check;

    public Board(Main main) {
        /* Input: Main game
        Output: create Board and checkTrue.
         */
        this.main = main;
        create();
        while (checkTrue()) {}
        printBoard();
        System.out.println("Ready to get input.");
    }

    public Candy getCandy(int row, int column) {
        /* Input: row and column of candy
        Output: return candy at the correlative position on the board.
         */
        return board[row][column];
    }

    public void moveBoard(int speedx, int speedy) {
        /*
        Input: speedx and speedy
        Output: move the board by the given speed for each Candy in the board
         */
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j].moveCandy(speedx,speedy);
            }
        }
    }

    public boolean checkMovingDown() {
        /*
        Output: return True if we have to move down some candies
                return False if all the candies is in right position.
         */
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j].checkRightPosition()!=0)
                    return true;
            }
        }
        return false;
    }

    public void movingDown(int speedx, int speedy) {        /*
        Input: speedx and speedy
        Output: move the board by the given speed for candy which is not in right position of it
        according to x and y
         */
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j].checkRightPosition()!=0)
                    board[i][j].moveCandy(speedx, speedy*board[i][j].checkRightPosition());
            }
        }
    }

    private void create() {
        /*
        Output: create the board with SIZE, each candy has its x and y, and color(using Random)
         */
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Candy candy = new Candy(rand.nextInt(4), i, j);
                this.board[i][j] = candy;
            }
        }
    }

    public void printBoard() {
        /*
        Output: print the board to check the color of the candy in the board
         */
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j].getColor() + "  ");
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print("("+board[i][j].getx() + ","+board[i][j].gety()+") ");
            }
            System.out.println();
        }

    }

    public void swap(int x1,int y1, int x2, int y2) {
        /*
        Input: Cordinate of 2 candy
        Output: Change the candy of 2 slot of the board.
         */
        Candy tempCandy = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x1][y1].setX(x1);
        board[x1][y1].setY(y1);
        board[x2][y2] = tempCandy;
        board[x2][y2].setX(x2);
        board[x2][y2].setY(y2);
    }

    public boolean checkTrue() {
        /*
        Check the board when we creat() because it may give many true positions.
        checkTrue() eliminates these positions.

        Output: return True if it changes the board.
                return False if it doesn't change the board. (means: the board is ready to play)
         */
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
                    //moveDownPos();

                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkTruePos() throws InterruptedException {
        /*
        Check the board when we creat() because it may give many true positions.
        checkTrue() eliminates these positions.

        Output: return True if it changes the board.
                return False if it doesn't change the board. (means: the board is ready to play)
         */
        boolean c=false;
        //Check vertical
        for (int i = SIZE-1; i >=0; i--) {
            for (int j = SIZE-1; j >= 0; j--) {
                if(checkPosition(i,j)) {
                    moveDownPos();
                    main.movingDownPaint();
                    c=true;
                }
            }
        }
        return c;
    }



    public boolean checkPosition(int row, int column) {
        /*
        Input: The cordinate of the candy.
        Output: Check at the this position, whether it has true horizontal or true vertical,
        then eliminate these positions.
         */

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
            //moveDownPos2(1);
            //main.movingDownPaint();
            return true;
        }

        if (countVertical >= 2) {
            System.out.print("True vertical: ");
            board[row][column].setColor(-1);
            for(int j=0; j<countVertical; j++) {
                board[listVertical[j]/SIZE][listVertical[j]%SIZE].setColor(-1);
            }
            //moveDownPos2(countVertical+1);
            //main.movingDownPaint();
            return true;
        }
        return false;
    }

    public void moveDown(int step) {
        /*
        Input: The step to move the board down

        Check the board where its color is -1 and move upper candies down to eliminated positions.
        creat the new candy for slots in the board which has its color is -1
         */
        for (int i = SIZE-1; i >=step; i--) {
            for (int j = SIZE-1; j>=0; j--) {
                int k=0;
                if(board[i][j].getColor() == -1) {
                    board[i][j].swap(board[i-step][j]);
                    //swap(i, j, i-step, j);
                }
            }
        }

        for (int i = SIZE-1; i >=0; i--) {
            for (int j = SIZE-1; j>=0; j--) {
                if(board[i][j].getColor() == -1) {
                    board[i][j] = new Candy(rand.nextInt(4), i, j);
                }
            }
        }
    }

    public void moveDownPos() {
        /*
        Input: The step to move the board down

        Check the board where its color is -1 and move upper candies down to eliminated positions.
        creat the new candy for slots in the board which has its color is -1
         */
        int[] a = new int[SIZE];
        for(int i=0; i<SIZE; i++) {
            a[i]=0;
        }

        for (int i = SIZE-1; i >=1; i--) {
            for (int j = SIZE-1; j>=0; j--) {
                int k=0;
                if(board[i][j].getColor() == -1) {
                    int step = 1;
                    while(board[i-step][j].getColor() == -1) {
                        if(i-step>0) {
                            step++;
                        }
                        if(i-step==0) {
                            if(board[i-step][j].getColor()==-1) {
                                step = -1; //no available candy above ==> no need to swap
                                break;
                            }
                            else{
                                break;
                            }
                        }
                    }
                    if (step!=-1) {
                        if(a[j]==0) {
                            a[j]=step;
                        }
                        swap(i, j, i - step, j);
                    }
                    //printBoard();
                    System.out.println();
                }
            }
        }
        //main.movingDownPaint();

        for (int i = SIZE-1; i >=0; i--) {
            for (int j = SIZE-1; j>=0; j--) {
                if(board[i][j].getColor() == -1) {
                    board[i][j] = new Candy(rand.nextInt(4), i, j);
                    board[i][j].ypos+=((SIZE-a[j])*SIZESPACE+35);
                }
            }
        }//main.movingDownPaint();
    }

    public boolean checkEndGame() {
        /*
        Check if player can make any moves or not.
        Output: return true if player can't play anymore.
                return false if player can play
         */
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

    public void paint(Graphics2D g2d) {
        //draw blackground
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, 900, 900);

        //draw background picture
        backGround = new ImageIcon("background.jpg");
        backGround.paintIcon(this, g2d,0, 0);

        g2d.setColor(new Color(0, 0, 70, 100));
        g2d.fillRoundRect(30, 20, 600, 600, 30, 30);

        for(int i=0; i<SIZE; i++) {
            for(int j = 0; j<SIZE; j++) {
                board[i][j].paintCandy(g2d);
            }
        }
    }
}
