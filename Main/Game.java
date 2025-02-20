package main;

import entity.Ghost;
import entity.Pacman;
import java.util.Scanner;
import map.Map;

public class Game {
    public static int NumberOfGamePlayed = 0;
    public boolean gameRunning = false;
    Pacman pacman = new Pacman(1,1);
    Ghost ghosts[] = {
        new Ghost(1,2,"BlueGhost",'B'),
        new Ghost(8,8,"OrangeGhost",'O')
    };
    Map map = new Map();

    public Game() {
        gameRunning = true;
        NumberOfGamePlayed += 1;
    };

    public void runGame(){
        Scanner scanner = new Scanner(System.in);
        map.placePacman(pacman.x, pacman.y);
        for (Ghost ghost : ghosts) { map.placeGhost(ghost.x, ghost.y); }
        map.generateFood();
        System.out.println("\nWelcome to Pacman Game: ");
        while(gameRunning){
            map.printMap();
            System.out.println("\nScore: " + pacman.score);
            System.out.print("\nEnter Move (w/s/a/d): ");
            char move = scanner.next().charAt(0);
            switch (move) {
                case 'w':
                case 's':
                case 'a':
                case 'd':
                    pacman.move(move, map);
                    //map.moveGhosts(ghosts);
                    if(pacman.collidesWithGhost(ghosts)){
                        System.out.println("\nGame Over!\n");
                        gameRunning = false;
                        break;
                    }
                    if(map.areAllFoodEaten()){
                        System.out.println("Congratulation! You've won!");
                        gameRunning = false;
                        break;
                    }
                    break;
                default:
                    System.out.println("Invalid Move!");
                    break;
            }
        }
        scanner.close();
    }


}
