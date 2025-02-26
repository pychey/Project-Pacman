package entity;
import map.Map;

public class Ghost extends Character{
    char lastGridOfGhost = '.';

    public Ghost(int x, int y, String name, char symbol){
        this.x = x;
        this.y = y;
        this.name = name;
        this.symbol = symbol;
    }

    public Ghost(String name, char symbol, Map map){
        do {
            x = (int)(Math.random() * (map.height -1));
            y = (int)(Math.random() * (map.width -1));
        } while(map.isWall(x,y) || map.isPacman(x,y) || map.isGhost(x,y));
        map.placeGhost(x,y);
        this.name = name;
        this.symbol = symbol;
    }

    @Override
    public void sayName(){
        System.out.println("I am Ghost: " + name);
    }

    public void moveRandomly(Map map){
        int oldX = x;
        int oldY = y;
        int move;
        map.clearGhost(x,y,lastGridOfGhost);
        do {
            x = oldX;
            y = oldY;
            move = (int)(Math.random() * 5);
            switch(move){
                case 0:
                    moveUp();
                    break;
                case 1:
                    moveDown();
                    break;
                case 2:
                    moveLeft();
                    break;
                case 3:
                    moveRight();
                    break;
                case 4:
                    break;
            }
        } while(map.isWall(x,y) || !map.isWithinBound(x,y) || map.isGhost(x,y));

        lastGridOfGhost = map.returnGrid(x, y);
        
        map.placeGhost(x, y);
    }
}