public class Candy {
    private int color;//0,1,2,3 for red, yellow, , green, blue
    private int position;

    Candy(int c, int p) {
        this.color = c;
        this.position = p;
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


}
