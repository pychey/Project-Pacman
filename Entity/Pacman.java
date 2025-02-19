package Entity;


public class Pacman extends Character{
    public int score = 0;

    public Pacman(int x, int y ){
        this.x = x;
        this.y = y;
        this.name = "Pacman";
        this.symbol = 'P';
    }

    @Override
    public void sayName(){
        System.out.println("I am " + name);
    }
}