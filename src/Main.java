package gui;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Main extends JPanel {

    Board board = new Board(this);

    InputHandle input = new InputHandle(this);

    public Main() {
    }

    public boolean isChanging() {
        System.out.print("");
//        if (input.isChanging() && ((board.getCandy(input.x1, input.y1).xpos != input.x2pos) || (board.getCandy(input.x1, input.y1).ypos != input.y2pos)) ) {
//            return true;
//        }
//        else if(input.isChanging() && !((board.getCandy(input.x1, input.y1).xpos != input.x2pos) && (board.getCandy(input.x1, input.y1).ypos != input.y2pos)) ) {
//            board.swap(input.x1, input.y1, input.x2, input.y2);
//            board.check = true;
//            input.setChanging(false);
//            return false;
//        }
//        else
//            input.setChanging(false);
//            return false;
        return input.isChanging();
    }

    public void changeBoard() throws InterruptedException {
        if(board.checkPosition(input.x1, input.y1)) {
                board.printBoard();
                this.movingDownPaint();
                System.out.println("Pos1 true");
            }
            if(board.checkPosition(input.x2, input.y2)) {
                board.printBoard();
                this.movingDownPaint();
                System.out.println("Pos2 true");
            }

            while(board.checkTrue()) {
                this.movingDownPaint();
                System.out.println("new");
                this.repaint();
            }
        this.repaint();

    }

    public void moveTwoCandy() {
        System.out.println(input.x1+" "+input.y1+ " "+input.x2+ " "+input.y2);

        board.getCandy(input.x1, input.y1).moveCandy(-(input.x2pos-input.x1pos)/15,-(input.y2pos-input.y1pos)/15);
        board.getCandy(input.x2, input.y2).moveCandy(-(input.x1pos-input.x2pos)/15,-(input.y1pos-input.y2pos)/15);
    }

    public void moveFirst() {
        //System.out.print("Move ");
        board.moveBoard(0,2);
    }

    public void movingDownPaint() throws InterruptedException {
        int i = 0;
        while(board.checkMovingDown()) {
            if(true) {
                this.movingDown();
                this.repaint();
                Thread.sleep(2);
                i++;
                //System.out.print("i="+i);11
            }
            else
                break;
        }
    }

    public void movingDown() {
        //System.out.print("Move ");
        board.movingDown(0, 1);
    }


    @Override
    public void paint(Graphics g) {
        //System.out.print("Printed");
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        board.paint(g2d);
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Candy Crush clone");
        Main main = new Main();
        frame.add(main);
        frame.setSize(700, 700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        main.movingDownPaint();

        while(true && !main.board.checkEndGame()) {
            boolean success = false;
            if (main.isChanging()) {
                System.out.println("");
                main.board.swap(InputHandle.x1, InputHandle.y1, InputHandle.x2, InputHandle.y2);
                System.out.println("Swap");
                main.board.printBoard();
                while ((main.board.getCandy(main.input.x1, main.input.y1).xpos != main.input.x1pos) || (main.board.getCandy(main.input.x1, main.input.y1).ypos != main.input.y1pos)) {
                    main.moveTwoCandy();
                    main.repaint();
                    Thread.sleep(10);
                }
                main.input.setChanging(false);
                main.board.check = true;
            }

            if(main.board.check) {
                if(main.board.checkPosition(main.input.x1, main.input.y1)) {
                    main.board.moveDownPos();
                    main.movingDownPaint();
                    main.board.printBoard();
                    //main.repaint();
                    //main.movingDownPaint();
                    System.out.println("Pos1 true");
                    success = true;
                }

                if(main.board.checkPosition(main.input.x2, main.input.y2)) {
                    main.board.moveDownPos();
                    main.movingDownPaint();
                    main.board.printBoard();
                    //main.repaint();
                    //main.movingDownPaint();
                    System.out.println("Pos2 true");
                    success = true;
                }

                //main.movingDownPaint();
                while(main.board.checkTruePos()) {}
                main.board.check = false;

                if(!success) {
                    main.board.check = false;
                    main.board.swap(InputHandle.x1, InputHandle.y1, InputHandle.x2, InputHandle.y2);
                    while ((main.board.getCandy(main.input.x1, main.input.y1).xpos != main.input.x1pos) || (main.board.getCandy(main.input.x1, main.input.y1).ypos != main.input.y1pos)) {
                        main.moveTwoCandy();
                        main.repaint();
                        Thread.sleep(10);
                    }
                    success = false;

                }
            }
        }
    }
}