package main;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import exception.WrongMenuOptionException;
import user.User;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nPacman Game - User System");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            
            try {
                int opt = scanner.nextInt();
                scanner.nextLine(); 
                new WrongMenuOptionException(opt);

                switch (opt) {
                    case 1:
                        System.out.print("Enter username: ");
                        String newUsername = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String newPassword = scanner.nextLine();
                        if(!User.isNameExist(newUsername)){
                            User newUser = new User(newUsername, newPassword);
                            newUser.saveToFile();
                        } else System.out.println("Username already exists!");
                        break;
                    case 2:
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();
                        if(User.isAccountValid(username, password)){
                            Game.start();
                        } else System.out.println("User doesn't exist!");
                        break;
                    case 3:
                        running = false;
                        System.out.println("/// Cya! ///");
                        break;
                    }
            } catch ( WrongMenuOptionException e) {
                System.out.println(e.getMessage());
            } 
            catch ( InputMismatchException e) {
                System.out.println("Error: not a Number");
                scanner.nextLine();
            }
            catch ( IOException e){
                System.out.println("Error: file handling failed!");
            }
        }
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

//keep only wrongMenuException
//add constructor to throw exception instead of function
//catch only e.message
//make another upgraded pacman that extends from pacman
//put both pacman and upgraded pacman in character array
//handle register/login users (add, read, update, remove)
//add levels to the game where each level increase ghost
//keep high score and total scores of all level of users
//after certain level, pacman should be upgraded
//add leaderboard according to total scores of all users
//see how inner class, ananymous class could be applied
//make functional interface and have lambda expression
//add gui


//teacher's comment
//add user to database (can use teacher's code)
//only add gui for user register/login
//when playing, just keep it to cli
//when winning, show win score and leader board
//users could play as guest or make account
//both account and guest should be in leaderboard
//name of user should not be the same
//user who have account should be able to see their history