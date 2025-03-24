package game;

import entity.Ghost;
import entity.Pacman;
import exception.WrongGameMenuOptionException;
import main.Main;

import java.util.InputMismatchException;
import java.util.Scanner;

import database.MySQLConnection;
import map.Map;
import user.User;

public class Game {
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

    public void runGame(User userPlaying){
        userPlaying.totalGamesPlayed++;
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
                    userPlaying.totalLosses++;
                    break;
                }
                if(map.areAllFoodEaten()){
                    System.out.println("\nCongratulation! You've won!");
                    gameRunning = false;
                    userPlaying.totalWins++;
                    break;
                }
            } else {
                gameRunning = false;
                break;
            }
        }
        if(userPlaying.highScore < pacman.score) {
            userPlaying.highScore = pacman.score;
        }
        MySQLConnection.updateUserData(userPlaying);
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

    public static void start(User userPlaying) {
        Scanner scanner = new Scanner(System.in);
        boolean gameQuit = false;
        int opt = -1;
        do {
            System.out.println("\nNumber Of Game Played: " + userPlaying.totalGamesPlayed);
            System.out.println("1.Play");
            System.out.println("2.Practice");
            System.out.println("3.Leaderboard");
            System.out.println("4.Logout");
            System.out.println("0.Quit");
            System.out.print("Enter : ");
            try {
                opt = scanner.nextInt();
                new WrongGameMenuOptionException(opt);
                switch(opt){
                    case 1:
                        Game game = new Game(scanner);
                        game.runGame(userPlaying);
                        break;
                    case 2:
                        Game gamePractice = new Game(scanner);
                        gamePractice.practice();
                        break;
                    case 3:
                        MySQLConnection.displayLeaderboard();
                        break;
                    case 4:
                        gameQuit = true;   
                        break;
                    case 0:
                        Main.quitGame = true;
                        return;
                }
            } 
            catch ( WrongGameMenuOptionException e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            } 
            catch ( InputMismatchException e) {
                System.out.println("Error: not a Number");
                scanner.nextLine();
            }
        } while (!gameQuit);
    }
}
