import java.util.Scanner;

public class Game {
    Pacman pacman = new Pacman(1,1);
    Ghost ghosts[] = {
        new Ghost(1,2,"BlueGhost",'B'),
        new Ghost(8,8,"OrangeGhost",'O')
    };
    Map map= new Map();

    public Game() {



    }





    public void startGame(){
        Scanner scanner = new Scanner(System.in);
        map.placePacman(pacman.x, pacman.y);
        for (Ghost ghost : ghosts) {
            map.placeGhost(ghost.x, ghost.y);
        }
        map.generateFood();
        System.out.println("Welcome to Pacman:\n");
        map.printMap();
        while(true){
            System.out.print("\nEnter Move (w/s/a/d): ");
            char move = scanner.next().charAt(0);
            if(map.isFoodEaten(pacman.x, pacman.y)){
                pacman.score++;
            }
            map.movePacman(move, pacman);
            map.printMap();
            System.out.println("\nScore: " + pacman.score);




            if(move == 'q'){
                scanner.close();
                break;
            }
        }
    }


}
