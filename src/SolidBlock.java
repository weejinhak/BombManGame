import processing.core.PApplet;
import processing.core.PImage;

public class SolidBlock extends Block{

    public SolidBlock(int x, int y) {
        super(x, y);
    }


    public void readImg(PApplet pApplet, int select) {
        ImageManager imageManager = new ImageManager(pApplet);
        setImg(imageManager.readBlock(select));
    }

    @Override
    public void draw(PApplet pApplet) {
        pApplet.image(getImg(),40*getX(), 40*getY());
    }
}
