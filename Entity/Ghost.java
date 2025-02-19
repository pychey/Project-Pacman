package Entity;
public class Ghost extends Character{

    public Ghost(int x, int y, String name, char symbol){
        this.x = x;
        this.y = y;
        this.name = name;
        this.symbol = symbol;
    }

    @Override
    public void sayName(){
        System.out.println("I am " + name);
    }
}