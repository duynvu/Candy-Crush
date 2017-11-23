import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InputHandle implements MouseListener {

    Main main;
    private static int count;

    public static int x1, y1;
    public static int x2, y2;

    int x1pos, y1pos;
    int x2pos, y2pos;

    private boolean changing;

    public InputHandle(Main main) {
        main.addMouseListener(this);
        changing = false;
        count = 0;
    }

    public void setChanging(boolean b) {
        changing = b;
    }

    public boolean isChanging() {
        return changing;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        x1 = x2; x1pos = x2pos;
//        y1 = y2; y1pos = y2pos;
//        x2 = (e.getY()-20)/Candy.SIZESPACE;
//        y2 = (e.getX()-30)/Candy.SIZESPACE;
//        x2pos = 30 + y2 * Candy.SIZESPACE;
//        y2pos = 20 + x2 * Candy.SIZESPACE;

        if(count==0) {
            count++;
            x1 = (e.getY()-20)/Candy.SIZESPACE;
            y1 = (e.getX()-30)/Candy.SIZESPACE;
            x1pos = 30 + y1 * Candy.SIZESPACE;
            y1pos = 20 + x1 * Candy.SIZESPACE;
            System.out.print(x1+ " " + y1+" ");
        }
        else {
            count = 0;
            x2 = (e.getY()-20)/Candy.SIZESPACE;
            y2 = (e.getX()-30)/Candy.SIZESPACE;
            x2pos = 30 + y2 * Candy.SIZESPACE;
            y2pos = 20 + x2 * Candy.SIZESPACE;
            System.out.println(x1+" "+y1+ " "+x2+ " "+y2);
            System.out.println(x2+ " " + y2);
            //System.out.println("---"+x1+" "+y1+ " "+x2+ " "+y2);
            changing = true;
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

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
