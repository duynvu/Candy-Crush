import java.util.Random;
import java.util.Scanner;

public class Board {

    private final int SIZE = 8;
    private Candy[][] board = new Candy[SIZE][SIZE];
    
    int i1, i2, i3, i4;
    int s1, s2;
    Random rand = new Random();

   public Board() {
        create();
        while (checkTrue()) {
        }
        printBoard();
        System.out.println("Ready to get input.");
        getInput();
        swap(s1, s2);
        if(checkPosition(i1,i2)) {
            System.out.println("pos1");
        }
        if(checkPosition(i3,i4)) {
            System.out.println("pos2");
        }
        printBoard();
    }

    public void create() {
        for (int i = 0; i <SIZE; i++) {
            for (int j = 0; j<SIZE; j++) {
                Candy candy = new Candy(rand.nextInt(4), i*10+j);
                this.board[i][j] = candy;
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i <SIZE; i++) {
            for (int j = 0; j<SIZE; j++) {
                System.out.print(board[i][j].getColor()+"  ");
            }
            System.out.println();
        }
    }

    public void swap(int p1, int p2) {
        board[p1/SIZE][p1%SIZE].swap(board[p2/SIZE][p2%SIZE]);
    }

    public boolean checkTrue() {
        //Check vertical
        for(int i=0; i<SIZE-2; i++){
            for(int j=0; j<SIZE; j++) {
                int count=1;
                int k = i;
                while(board[k][j].getColor() == board[k+1][j].getColor()) {
                    count++;
                    if(k<SIZE-2) {
                        k++;
                    } else {
                        break;
                    }
                }
                if(count>=3) {
                    k = i;
                    while(board[k][j].getColor() == board[k+1][j].getColor()) {
                        board[k][j].setColor(-1);
                        if(k<SIZE-2) {
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
        for(int i=0; i<SIZE; i++) {
            for(int j=0;j<SIZE-2; j++) {

                int count=1;
                int k=j;
                while(board[i][k].getColor() == board[i][k+1].getColor()) {
                    count++;
                    if(k<SIZE-2) {
                        k++;
                    } else {
                        break;
                    }
                }
                if(count>=3) {
                    k = j;
                    while(board[i][k].getColor() == board[i][k+1].getColor()) {
                        board[i][k].setColor(-1);
                        if(k<SIZE-2) {
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

    public void moveDownPos(int step) {
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
