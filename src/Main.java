import javax.swing.*;

public class Main extends JFrame {
    public static void main(String[] args) {
        JFrame obj = new JFrame();
        Board board = new Board();

        obj.setBounds(50,50, 700, 700);
        obj.setVisible(true);
        obj.setResizable(true);
        obj.setTitle("Candy Crush clone");
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(board);
    }
}