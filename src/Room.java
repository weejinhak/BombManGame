import processing.core.PApplet;
import processing.core.PImage;

public abstract class Room {

    private int x;
    private int y;
    private PImage img;

   Room(int x, int y){
       this.x = x;
       this.y = y;
   }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public abstract void readImg(PApplet pApplet, int select);

    PImage getImg() {
        return img;
    }

    void setImg(PImage pImage){
        this.img = pImage;
    }

    public abstract void draw(PApplet pApplet);

}
