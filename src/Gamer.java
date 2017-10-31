import processing.core.PApplet;
import java.util.ArrayList;

public class Gamer extends Room{
    private int speed;
    private int state;
    private int bombCount;
    private int powerCount;
    private ArrayList<Bomb> bombs;

    public Gamer(int x, int y, int player) {
        super(x, y);
        bombCount = 1;
        powerCount = 3;
        bombs = new ArrayList<Bomb>();
        state = 0;
    }

    void setSpeed(int speed) {
        if(this.speed<4)
            this.speed = speed;
    }

    void setBombCount(int bombCount) {
        if(this.bombCount<4)
            this.bombCount = bombCount;
    }

    void setPowerCount(int powerCount) {
        if(this.powerCount<4)
            this.powerCount = powerCount;
    }

    public ArrayList<Bomb> getBombs() {
        return bombs;
    }

    public void move(int x, int y){
        setX(x);
        setY(y);
    }

    public void addBomb(){
        if (bombs.size() < bombCount) {
            Bomb bomb = new Bomb(getX(), getY(), powerCount);
            bombs.add(bomb);
        }
    }


    public void readImg(PApplet pApplet, int select) {
        ImageManager imageManager = new ImageManager(pApplet);
        setImg(imageManager.readGamer(select));
    }

    @Override
    public void draw(PApplet pApplet) {
        pApplet.image(getImg(),40*getX()+8, 40*getY()+5);
    }

}
