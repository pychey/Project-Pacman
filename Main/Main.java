package main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean gameQuit = false;
        int opt;
        do {
            System.out.println("1.Play");
            System.out.println("2.Practice");
            System.out.println("3.Quit");
            System.out.print("Enter : ");
            opt = scanner.nextInt();
            switch(opt){
                case 1:
                    Game game = new Game();
                    game.runGame();
                    break;
                case 2:
                    // Game game = new Game();
                    break;
                case 3:
                    gameQuit = true;
                    break;
                default:
                    System.out.println("Choose 1,2 or 3");
                    break;
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
