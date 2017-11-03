import processing.core.PApplet;
import processing.core.PImage;

public class EmptyRoom extends Room {
    EmptyRoom(int x, int y) {
        super(x, y);
    }

    @Override
    public void readImg(PApplet pApplet, int select) {

    }

    @Override
    public void draw(PApplet pApplet) {
    }
}
