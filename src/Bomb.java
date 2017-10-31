import processing.core.PApplet;

public class Bomb extends Room{
    private int power;
    private long createTime;

    public Bomb(int x, int y, int power) {
        super(x, y);
        this.power = power;
        createTime = System.currentTimeMillis();
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPower() {
        return power;
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
        readImg(pApplet,0);
        pApplet.image(getImg(),40*getX() +8, 40*getY()+8);
    }

}
