import processing.core.PApplet;

public class Flame extends Room{
    private long createTime;
    private int select;
    private FlameInterface flameInterface;

    Flame(int x, int y, int select, FlameInterface flameInterface) {

        super(x, y);
        this.flameInterface = flameInterface;

        createTime = System.currentTimeMillis();
        this.select = select;
    }

    public long getCreateTime() {
        return createTime;
    }

    @Override
    public void readImg(PApplet pApplet, int select) {
        ImageManager imageManager = new ImageManager(pApplet);
        setImg(imageManager.readEffect(select));
    }

    @Override
    public void draw(PApplet pApplet) {

        readImg(pApplet,select);
        pApplet.image(getImg(),40*getX()+8, 40*getY()+8);
        if(System.currentTimeMillis() - createTime > 500) {
            this.flameInterface.destroyFrame(getX(), getY());
        }
    }

}
