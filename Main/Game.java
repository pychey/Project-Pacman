package main;

import entity.Ghost;
import entity.Pacman;
import exception.WrongMenuOptionException;

import java.util.InputMismatchException;
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

    public void initializePacmanOnMap(){
        pacman = new Pacman(map);
    }

    public void initializeGhostOnMap(){
        ghosts = new Ghost[] {
            new Ghost("BlueGhost",'b',map),
            new Ghost("RedGhost",'r',map),
            new Ghost("OrangeGhost",'o',map),
            new Ghost("PinkGhost",'p',map)
        };
    }

    public void runGame(){
        NumberOfGamePlayed += 1;
        initializePacmanOnMap();
        map.generateFood();
        initializeGhostOnMap();
        System.out.println("\nWelcome to Pacman Game: ");
        while(gameRunning){
        map.printMap();
        System.out.println("\nScore: " + pacman.score);
        System.out.print("\nEnter Move (w/s/a/d/q to quit): ");
            char move = scanner.next().charAt(0);
            move = Character.toLowerCase(move);
            if (move != 'q'){
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
            } else {
                gameRunning = false;
                break;
            }
        }
    }

    public void practice(){                     
        initializePacmanOnMap();
        map.generateFood();
        System.out.println("\nWelcome to Pacman Game Practice: ");
        while(gameRunning){
            map.printMap();
            System.out.println("\nScore: " + pacman.score);
            System.out.print("\nEnter Move (w/s/a/d/q to quit): ");
            char move = scanner.next().charAt(0);
            move = Character.toLowerCase(move);
            if(move != 'q'){
                pacman.move(move, map);
                if(map.areAllFoodEaten()){
                    System.out.println("\nCongratulation! You've won!");
                    gameRunning = false;
                    break;
                }
            } else {
                gameRunning = false; 
                break;
            }
        }
    }

    public static void start() {
        Scanner scanner = new Scanner(System.in);
        boolean gameQuit = false;
        int opt = -1;
        do {
            System.out.println("\nNumber Of Game Played: " + Game.NumberOfGamePlayed);
            System.out.println("1.Play");
            System.out.println("2.Practice");
            System.out.println("3.Quit");
            System.out.print("Enter : ");
            try {
                opt = scanner.nextInt();
                new WrongMenuOptionException(opt);
                switch(opt){
                    case 1:
                        Game game = new Game(scanner);
                        game.runGame();
                        break;
                    case 2:
                        Game gamePractice = new Game(scanner);
                        gamePractice.practice();
                        break;
                    case 3:
                        gameQuit = true;
                        break;
                }
            } 
            catch ( WrongMenuOptionException e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            } 
            catch ( InputMismatchException e) {
                System.out.println("Error: not a Number");
                scanner.nextLine();
            }
        } while (!gameQuit);
        scanner.close();
    }
}
