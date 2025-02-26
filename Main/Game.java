package main;

import entity.Ghost;
import entity.Pacman;
import java.util.Scanner;
import map.Map;

public class Game {
    public static int NumberOfGamePlayed = 0;
    public boolean gameRunning = false;
    Scanner scanner;
    Map map = new Map();
    Pacman pacman;
    Ghost[] ghosts;

    public Game(Scanner mainScanner) {
        this.scanner = mainScanner;
        gameRunning = true;
    };

    public void initializePacmanRandomlyOnMap(){
        pacman = new Pacman(map);
    }

    public void initializeGhostRandomlyOnMap(){
        ghosts = new Ghost[] {
            new Ghost("BlueGhost",'B',map),
            new Ghost("RedGhost",'R',map)
        };
    }

    public void runGame(){
        NumberOfGamePlayed += 1;
        initializePacmanRandomlyOnMap();
        map.generateFood();
        initializeGhostRandomlyOnMap();
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
                    for (Ghost ghost : ghosts) {
                        ghost.moveRandomly(map);
                    }
                    pacman.move(move, map);
                    if(pacman.collidesWithGhost(ghosts)){
                        System.out.println("\nGame Over!");
                        gameRunning = false;
                        break;
                    }
                    if(map.areAllFoodEaten()){
                        System.out.println("\nCongratulation! You've won!");
                        gameRunning = false;
                        break;
                    }
                    break;
                default:
                    System.out.println("Invalid Move!");
                    break;
            }
        }
    }

    public void practice(){
        initializePacmanRandomlyOnMap();
        map.generateFood();
        System.out.println("\nWelcome to Pacman Game Practice: ");
        while(gameRunning){
            map.printMap();
            System.out.println("\nScore: " + pacman.score);
            System.out.print("\nEnter Move (w/s/a/d/q to quit): ");
            char move = scanner.next().charAt(0);
            switch (move) {
                case 'w':
                case 's':
                case 'a':
                case 'd':
                    pacman.move(move, map);
                    if(map.areAllFoodEaten()){
                        System.out.println("\nCongratulation! You've won!");
                        gameRunning = false;
                        break;
                    }
                    break;
                case 'q':
                    gameRunning = false;
                    break;
                default:
                    System.out.println("Invalid Move!");
                    break;
            }
        }
    }
}
