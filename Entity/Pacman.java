package entity;
import java.util.ArrayList;

import map.Map;

public class Pacman extends Character{
    public int score = 0;

    public Pacman(int x, int y, Map map){
        this.x = x;
        this.y = y;
        this.name = "Pacman";
        this.symbol = 'P';
        map.placePacman(x,y);
    }

    public Pacman(Map map){
        do {
            x = (int)(Math.random() * map.height);
            y = (int)(Math.random() * map.width);
        } while(map.isWall(x,y) || map.isGhost(x,y));
        map.placePacman(x,y);
        this.name = "Pacman";
        this.symbol = 'P';
    }

    @Override
    public void sayName(){
        System.out.println("I am Pacman");
    }

    public void move(char direction,Map map){
            int oldX = x;
            int oldY = y;
            map.clearPacman(x, y);
            switch (direction) {
                case 'w':
                    moveUp();
                    break;
                case 's':
                    moveDown();
                    break;
                case 'a':
                    moveLeft();
                    break;
                case 'd':
                    moveRight();
                    break;
                default:
                    System.out.println("There's no such move");
            }

            if(map.isWall(x, y)) {
                x = oldX;
                y = oldY;
                map.placePacman(x, y);
            }

            if(map.isFoodThere(x, y)){
                score++;
            }
            map.placePacman(x, y);
        }
    
    public boolean collidesWithGhost(ArrayList<Ghost> ghosts){
        for (Ghost ghost : ghosts) {
            if(this.x == ghost.x && this.y == ghost.y){
                return true;
            }
        }
        return false;
    }
}