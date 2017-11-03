import processing.core.PApplet;
import processing.event.KeyEvent;

public class BumbManTest extends PApplet {
    @Override
    public void settings() {
        this.size(800, 600);
        smooth(5);

    }

    private Room[][] rooms;
    private Setting setting;
    private Gamer gamer1 = new Gamer(1, 1, 1);
    private Gamer gamer2 = new Gamer(18, 13, 2);

    @Override
    public void setup() {
        gamer1.readImg(this, 0);
        gamer2.readImg(this, 0);
        setting = new Setting(this);
        setting.settingBorder();
        setting.settingPopBlock();
        setting.settingSolidBlock();
        setting.settingGamer(gamer1, gamer2);
        rooms = setting.getRooms();
    }

    public static void main(String[] args) {
        PApplet.main("BumbManTest");
    }

    @Override
    public void keyPressed(KeyEvent event) {

        int keyCode = event.getKeyCode();

        setting.moveGamer(keyCode, gamer1, gamer2);

    }

    @Override
    public void draw() {
        background(00, 99, 66);


        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 15; j++) {
                if (rooms[i][j] == null) continue;
                rooms[i][j].draw(this);
            }

        for (int i = 0; i < gamer1.getBombs().size(); i++) {
            Bomb bomb = gamer1.getBombs().get(i);
            bomb.draw(this);
            if (System.currentTimeMillis() - bomb.getCreateTime() > 3000) {
                setting.makeFlame(bomb.getX(), bomb.getY(), bomb.getPower());
                gamer1.getBombs().remove(i);
            }
        }

        for (int i = 0; i < gamer2.getBombs().size(); i++) {
            Bomb bomb = gamer2.getBombs().get(i);
            bomb.draw(this);
            if (System.currentTimeMillis() - bomb.getCreateTime() > 3000) {
                setting.makeFlame(bomb.getX(), bomb.getY(), bomb.getPower());
                gamer2.getBombs().remove(i);
            }
        }

    }
}
