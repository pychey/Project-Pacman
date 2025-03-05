package main;
import userdata.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import exception.NotNumberException;
import exception.WrongMenuOptionException;

public class Main {

    public static void validateOption(int option) {
        if(option != 1 && option != 2 && option != 3){
            throw new WrongMenuOptionException("Option can't be beside 1,2,3");
        }
    }

    public static void main(String[] args) {
        UserManager.handleUserLogin();
    }

    public static void startGame(){
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
                try {
                    opt = scanner.nextInt();
                } catch (InputMismatchException e) {
                    throw new NotNumberException("Option can't be String");
                }
                validateOption(opt);
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
            catch ( WrongMenuOptionException | NotNumberException e) {
                System.out.println("\nException caught: " + e.getMessage());
                scanner.nextLine();
            } 
        } while (!gameQuit);
        scanner.close();
    }
}
//if entered wrong, ask to enter move again imidietly
//when moving toward wall, pacman should stay in the same place
//ghost should move after pacman move, but ghost should not move into wall
//check for colision between ghost and pacman, lost if colision
//check if all food eaten, if all eaten, win game
//when win or lose, ask player to either quit or play again
//keep track of number of game being played using static

//ghost generate on pacman
//ghosts stack on each other eat the food
//ghost wouldnt spawn on the last empty column
//add exception

