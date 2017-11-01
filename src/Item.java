import processing.core.PApplet;

public abstract class Item extends Room {

    private int select;

    public Item(){
        super(0,0);
    }

    public Item(int x, int y,int select) {

        super(x, y);
        this.select=select;
    }

    @Override
    public void readImg(PApplet pApplet, int select) {
        ImageManager imageManager = new ImageManager(pApplet);
        setImg(imageManager.readItem(select));
    }

    @Override
    public void draw(PApplet pApplet) {
        readImg(pApplet,select);
        pApplet.image(getImg(),40*getX()+8, 40*getY()+8);
    }
}
