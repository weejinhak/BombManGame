import processing.core.PApplet;

import java.util.ArrayList;

public class Map implements FlameInterface {
    private Room[][] rooms;
    private PApplet pApplet;

    private ArrayList<Flame> flames;

    public Map(PApplet pApplet) {
        flames = new ArrayList<Flame>();
        this.pApplet = pApplet;

        rooms = new Room[20][15];

        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 15; j++)
                rooms[i][j] = new EmptyRoom(i, j);
    }

    public void settingBorder() {
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 15; j++)
                if (i == 0 || i == 19 || j == 0 || j == 14) {
                    SolidBlock solidBlock = new SolidBlock(i, j);
                    rooms[i][j] = solidBlock;
                    solidBlock.readImg(pApplet, 9);
                }
    }

    public void settingPopBlock() {
        for (int i = 1; i < 19; i++)
            for (int j = 1; j < 14; j++) {
                int quarter = (int) (Math.random() * 4);
                if (quarter == 0) {
                    PopBlock popBlock = new PopBlock(i, j);
                    rooms[i][j] = popBlock;
                    popBlock.readImg(pApplet, 1);
                }// if
            }// for j
    }

    public void settingSolidBlock() {
        int width = (int) (Math.random() * 3) + 3;
        int height = (int) (Math.random() * 2) + 3;

        for (int i = 1; i < 19; i++)
            for (int j = 1; j < 14; j++)
                if (i % width == 0 && j % height == 0) {
                    SolidBlock solidBlock = new SolidBlock(i, j);
                    rooms[i][j] = solidBlock;
                    solidBlock.readImg(pApplet, 9);
                }
    }

    public void settingGamer(Gamer gamer1, Gamer gamer2) {
        rooms[1][1] = gamer1;
        rooms[18][13] = gamer2;

        rooms[1][2] = new EmptyRoom(1, 2);
        rooms[2][1] = new EmptyRoom(2, 1);

        rooms[18][12] = new EmptyRoom(18, 12);
        rooms[17][13] = new EmptyRoom(17, 13);
    }

    public void setRooms(Room[][] rooms) {
        this.rooms = rooms;
    }

    public Room[][] getRooms() {
        return rooms;
    }

    public boolean possibleMove(int x, int y) {
        boolean result = false;

        if (!(rooms[x][y] instanceof SolidBlock) && !(rooms[x][y] instanceof PopBlock))
            result = true;

        return result;
    }

    public boolean isSolidBlock(int x, int y) {
        boolean result = false;

        if (rooms[x][y] instanceof SolidBlock)
            result = true;

        return result;
    }

    public boolean isPopBlock(int x, int y) {
        boolean result = false;

        if (rooms[x][y] instanceof PopBlock)
            result = true;

        return result;
    }

    public void makeFlame(int x, int y, int power) {
        for (int i = 1; i <= power; i++) {
            if (isPopBlock(x - i, y)) {
                rooms[x-1][y]=new Flame(x-i,y,3,this);
                break;
            }
            rooms[x - i][y] = new Flame(x - i, y, 3, this);
            if (!isSolidBlock(x - i, y))
                rooms[x+i][y]=new Flame(x-i,y,3,this);
                break;
        }
        for (int i = 1; i <= power; i++) {
            if (!isSolidBlock(x + i, y))
                rooms[x + i][y] = new Flame(x + i, y, 3, this);
            else
                break;
        }
        for (int i = 1; i <= power; i++) {
            if (!isSolidBlock(x, y - i))
                rooms[x][y - i] = new Flame(x, y - i, 3, this);
            else
                break;
        }
        for (int i = 1; i <= power; i++) {
            if (!isSolidBlock(x, y + i))
                rooms[x][y + i] = new Flame(x, y + i, 3, this);
            else
                break;
        }

    }

    void eatItem(int x, int y, Gamer gamer) {
        int countUp = 1;
        if (rooms[x][y] instanceof SpeedItem) {
            gamer.setSpeed(countUp);
            gamer.move(x, y);
            rooms[x][y] = gamer;
        } else if (rooms[x][y] instanceof PowerItem) {
            gamer.setPowerCount(countUp);
            gamer.move(x, y);
            rooms[x][y] = gamer;
        } else if (rooms[x][y] instanceof BombUpItem) {
            gamer.setBombCount(countUp);
            gamer.move(x, y);
            rooms[x][y] = gamer;
        }
    }

    @Override
    public void destroyFrame(int x, int y) {
        rooms[x][y] = null;
    }

}
