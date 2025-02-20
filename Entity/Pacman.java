package Entity;
import Map.Map;

public class Pacman extends Character{
    public int score = 0;

    public Pacman(int x, int y){
        this.x = x;
        this.y = y;
        this.name = "Pacman";
        this.symbol = 'P';
    }

    @Override
    public void sayName(){
        System.out.println("I am Pacman");
    }

    public void move(char direction,Map map){
            map.clearCharacter(x, y);
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
            if(map.isFoodThere(x, y)){
                score++;
            }
            map.placePacman(x, y);
        }
    
    public boolean collidesWithGhost(Ghost[] ghosts){
        for (Ghost ghost : ghosts) {
            if(this.x == ghost.x && this.y == ghost.y){
                return true;
            }
        }
        return false;
    }
}