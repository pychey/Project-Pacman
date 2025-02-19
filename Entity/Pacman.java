package Entity;


public class Pacman extends Character{
    public int score = 0;

    public Pacman(int x, int y ){
        this.x = x;
        this.y = y;
        this.name = "Pacman";
        this.symbol = 'P';
    }
}