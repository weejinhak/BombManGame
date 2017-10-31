import processing.core.PApplet;
import processing.event.KeyEvent;

public class BumbManTest extends PApplet {
    @Override
    public void settings(){
        this.size(800,600);
    }

    private Room[][] rooms;
    private Map setting;
    private Gamer gamer1 = new Gamer(1,1,1);
    private Gamer gamer2 = new Gamer(18,13,2);

    @Override
    public void setup(){
        gamer1.readImg(this,0);
        gamer2.readImg(this,0);

        setting = new Map(this);
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

        moveGamer(keyCode);

        System.out.println(key);

        if (keyCode == 16)
            gamer1.addBomb();
        if (keyCode == 32)
            gamer2.addBomb();

    }

    private void moveGamer(int key){
        int player1X = gamer1.getX();
        int player1Y = gamer1.getY();
        int player2X = gamer2.getX();
        int player2Y = gamer2.getY();

        if(key==65)
            player1X -=1;
        else if(key==87)
            player1Y -=1;
        else if(key==83)
            player1Y +=1;
        else if(key==68)
            player1X +=1;
        else if(key==37)
            player2X -=1;
        else if(key==38)
            player2Y -=1;
        else if(key==40)
            player2Y +=1;
        else if(key==39)
            player2X +=1;

        if(setting.possibleMove(player1X,player1Y)) {
            gamer1.move(player1X, player1Y);
            rooms[player1X][player1Y] = gamer1;
        }
        if(setting.possibleMove(player2X,player2Y)) {
            gamer2.move(player2X,player2Y);
            rooms[player2X][player2Y] = gamer2;
        }
    }

    @Override
    public void draw() {
        background(00,99,66);


        for(int i=0;i<20;i++)
            for (int j=0;j<15;j++) {
            if(rooms[i][j] == null ) continue;
                rooms[i][j].draw(this);
            }

        for(int i=0;i<gamer1.getBombs().size();i++){
            Bomb bomb = gamer1.getBombs().get(i);
            bomb.draw(this);
            if(System.currentTimeMillis() - bomb.getCreateTime() > 3000 ) {
                setting.makeFlame(bomb.getX(),bomb.getY(),bomb.getPower());
                gamer1.getBombs().remove(i);
            }
        }

        for(int i=0;i<gamer2.getBombs().size();i++){
            Bomb bomb = gamer2.getBombs().get(i);
            bomb.draw(this);
            if(System.currentTimeMillis() - bomb.getCreateTime() > 3000 ) {
                setting.makeFlame(bomb.getX(), bomb.getY(), bomb.getPower());
                gamer2.getBombs().remove(i);
            }
        }

    }
}
