public class Board {

    private final int SIZE = 8;
    private Candy[][] board = new Candy[SIZE][SIZE];

    public Board() {
        create();
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
}