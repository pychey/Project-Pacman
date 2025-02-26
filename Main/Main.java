package main;

import java.util.InputMismatchException;
import java.util.Scanner;

import exception.WrongMenuOptionException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean gameQuit = false;
        int opt;
        do {
            System.out.println("\nNumber Of Game Played: " + Game.NumberOfGamePlayed);
            System.out.println("1.Play");
            System.out.println("2.Practice");
            System.out.println("3.Quit");
            System.out.print("Enter : ");
            try {
                opt = scanner.nextInt();
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
            catch ( WrongMenuOptionException e) {
                System.out.println("\nInput has be number 1,2 or 3");
            } 
            catch (InputMismatchException e) {
                System.out.println("\nInput has to be number 1,2 or 3");
                scanner.nextLine();
            }
        } while (!gameQuit);
        scanner.close();
    }

    public static void validateOption(int option) {
        if(option < 1 || option > 3){
            throw new WrongMenuOptionException();
        }
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

