package main;

import entity.Ghost;
import entity.Pacman;
import exception.NotMoveException;

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

    public void validateMove(char move){
        if (move != 'w' && move != 'a' && move != 's' && move != 'd'){
            throw new NotMoveException("Move can't be beside (w/a/s/d)");
        }
    }

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
            try {
                char move = scanner.next().charAt(0);
                move = Character.toLowerCase(move);
                if (move != 'q'){
                    validateMove(move);
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
            } catch (NotMoveException nme) {
                System.out.println("\nException caught: " + nme.getMessage());
                scanner.nextLine();
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
            try {
                char move = scanner.next().charAt(0);
                move = Character.toLowerCase(move);
                if(move != 'q'){
                    validateMove(move);
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
            } catch (NotMoveException nme) {
                System.out.println("\nException caught: " + nme.getMessage());
                scanner.nextLine();
            }
        }
    }
}
