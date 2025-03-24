package game;

import entity.Ghost;
import entity.Pacman;
import exception.WrongGameMenuOptionException;
import main.Main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import database.MySQLConnection;
import map.Map;
import user.User;

public class Game {
    public boolean gameRunning = false;
    public boolean gameRunningEachLevel = false;
    Scanner scanner;
    Map map;
    Pacman pacman;
    ArrayList<Ghost> ghostList;
    int currentLevel = 1;
    int maxLevel = 5;
    int ghostMultiplier = 3;

    public Game(Scanner mainScanner) {
        this.scanner = mainScanner;
        gameRunning = true;
    };

    public void initializeNewMap(){
        map = new Map();
    }

    public void initializePacmanOnMap(){
        pacman = new Pacman(map);
    }

    public void initializeGhostsOnMap(int level){
        ghostList = new ArrayList<Ghost>();
        int numberOfGhosts = level * ghostMultiplier;
        for (int i = 0; i < numberOfGhosts; i++) ghostList.add(new Ghost("Ghost",'G', map));
    }

    public void runGame(User userPlaying){
        userPlaying.totalGamesPlayed++;
        currentLevel = 1;
        int totalScore = 0;
        while (gameRunning) {
            initializeNewMap();
            initializePacmanOnMap();
            map.generateFood();
            initializeGhostsOnMap(currentLevel);
            
            System.out.println("\nWelcome to Pacman Game - Level " + currentLevel);
            gameRunningEachLevel = true;
            
            while(gameRunningEachLevel){
                map.printMap();
                System.out.println("\nLevel: " + currentLevel + " | Score: " + pacman.score + " | Total Score: " + totalScore);
                System.out.print("\nEnter Move (w/s/a/d/q to quit): ");
                char move = scanner.next().charAt(0);
                move = Character.toLowerCase(move);
                
                if (move != 'q'){
                    for (Ghost ghost : ghostList) ghost.moveRandomly(map);
                    pacman.move(move, map);
                    if(pacman.collidesWithGhost(ghostList)){
                        System.out.println("\nYou've Lost! You reached level " + currentLevel);
                        gameRunning = false;
                        gameRunningEachLevel = false;
                        userPlaying.totalLosses++;
                        break;
                    }
                    if(map.areAllFoodEaten()){
                        totalScore += pacman.score;
                        if (currentLevel < maxLevel) {
                            System.out.println("\nLevel " + currentLevel + " Completed! Moving to Next Level !");
                            currentLevel++;
                            gameRunningEachLevel = false;
                        } else {
                            System.out.println("\nCongratulations! You've completed all " + maxLevel + " levels!");
                            gameRunning = false;
                            gameRunningEachLevel = false;
                            userPlaying.totalWins++;
                        }
                    }
                } else {
                    gameRunning = false;
                    gameRunningEachLevel = false;
                }
            }
        }
        totalScore += pacman.score;
        if(userPlaying.highScore < totalScore) userPlaying.highScore = totalScore;
        System.out.println("\nFinal Score: " + totalScore);
        MySQLConnection.updateUserData(userPlaying);
    }

    public void practice(){       
        initializeNewMap();              
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
