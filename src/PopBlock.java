import processing.core.PApplet;
import processing.core.PImage;

public class PopBlock extends Block{
    private Item item;
    private boolean hasItem;

    public PopBlock(int x, int y) {
        super(x, y);

        int thrid = (int) (Math.random()*3);

        if (thrid == 0)
            item = new BombUpItem(x, y);
        else if (thrid == 1)
            item = new PowerItem(x, y);
        else if (thrid == 2)
            item = new SpeedItem(x, y);
    }

    public Item getItem() {
        return item;
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
