import processing.core.PApplet;


public class Setting implements FlameInterface {
    private Room[][] rooms;
    private PApplet pApplet;

    Setting(PApplet pApplet) {

        this.pApplet = pApplet;

        rooms = new Room[20][15];

        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 15; j++)
                rooms[i][j] = new EmptyRoom(i, j);
    }

    void settingBorder() {
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 15; j++)
                if (i == 0 || i == 19 || j == 0 || j == 14) {
                    SolidBlock solidBlock = new SolidBlock(i, j);
                    rooms[i][j] = solidBlock;
                    solidBlock.readImg(pApplet, 9);
                }
    }

    void settingPopBlock() {
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

    void settingSolidBlock() {
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

    void settingGamer(Gamer gamer1, Gamer gamer2) {
        rooms[1][1] = gamer1;
        rooms[18][13] = gamer2;

        rooms[1][2] = new EmptyRoom(1, 2);
        rooms[2][1] = new EmptyRoom(2, 1);

        rooms[18][12] = new EmptyRoom(18, 12);
        rooms[17][13] = new EmptyRoom(17, 13);
    }


    Room[][] getRooms() {
        return rooms;
    }

    private boolean possibleMove(int x, int y) {
        boolean result = false;

        if (!(rooms[x][y] instanceof SolidBlock) && !(rooms[x][y] instanceof PopBlock))
            result = true;

        return result;
    }

    private void eatItem(int x, int y, Gamer gamer) {
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

    private boolean isSolidBlock(int x, int y) {
        boolean result = false;

        if (rooms[x][y] instanceof SolidBlock)
            result = true;

        return result;
    }

    private boolean isPopBlock(int x, int y) {
        boolean result = false;

        if (rooms[x][y] instanceof PopBlock)
            result = true;

        return result;
    }

    private boolean isGamer(int x, int y) {
        boolean result = false;
        if (rooms[x][y] instanceof Gamer)
            result = true;
        return result;
    }

    void makeFlame(int x, int y, int power) {
        //-x_axis
        for (int i = 1; i <= power; i++) {
            if (isPopBlock(x - i, y)) {
                rooms[x-i][y]=new Flame(x-i,y,3,this);
                rooms[x-i][y] = new PopBlock(x-i,y).getItem();
                break;
            }
            if (isSolidBlock(x - i, y)) break;
            if (isGamer(x-i,y)){rooms[x-i][y]=new Flame(x-i,y,3,this);}
            rooms[x-i][y]=new Flame(x-i,y,3,this);
        }
        //+x_axis
        for (int i = 1; i <= power; i++) {
            if(isPopBlock(x+i,y)){
                rooms[x+i][y]=new Flame(x+i,y,3,this);
                rooms[x+i][y] = new PopBlock(x+i,y).getItem();
                break;
            }
            if (isSolidBlock(x + i, y)) break;
            if(isGamer(x+i,y)){rooms[x+i][y]=new Flame(x+i,y,3,this);continue;}
            rooms[x+i][y]=new Flame(x+i,y,3,this);
        }
        //-y_axis
        for (int i = 1; i <= power; i++) {
            if(isPopBlock(x,y-i)){
                rooms[x][y-i]=new Flame(x,y-i,3,this);
                rooms[x][y-i] = new PopBlock(x,y-i).getItem();
                break;
            }
            if (isSolidBlock(x, y-i)) break;
            if(isGamer(x,y-i)){rooms[x][y-i]=new Flame(x,y-i,3,this);continue;}
            rooms[x][y-i]=new Flame(x,y-i,3,this);
        }
        //+y_axis
        for (int i = 1; i <= power; i++) {
            if(isPopBlock(x,y+i)){
                rooms[x][y+i]=new Flame(x,y+i,3,this);
                rooms[x][y+i] = new PopBlock(x,y+i).getItem();
                break;
            }
            if (isSolidBlock(x , y+i)) break;
            if(isGamer(x,y)){rooms[x][y+i]=new Flame(x,y+i,3,this);continue;}
            rooms[x][y+i]=new Flame(x,y+i,3,this);
        }

    }


    void moveGamer(int key, Gamer gamer1, Gamer gamer2){
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

        if (key == 16)
            gamer1.addBomb();
        if (key == 32)
            gamer2.addBomb();

        if(possibleMove(player1X,player1Y)) {
            gamer1.move(player1X, player1Y);
            eatItem(player1X,player1Y,gamer1);
        }
        if(possibleMove(player2X,player2Y)) {
            gamer2.move(player2X,player2Y);
            eatItem(player2X,player2Y,gamer2);
        }
    }


    @Override
    public void destroyFrame(int x, int y) {
        rooms[x][y] = null;
    }

}
