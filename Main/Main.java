package main;
import java.util.InputMismatchException;
import java.util.Scanner;

import database.DatabaseTableUser;
import exception.WrongMainMenuOptionException;
import game.Game;
import user.User;

public class Main {
    public static boolean quitGame = false;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nPacman Game - User System");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Play as Guest");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            
            try {
                int opt = scanner.nextInt();
                scanner.nextLine(); 
                new WrongMainMenuOptionException(opt);

                switch (opt) {
                    case 1:
                        System.out.println("Username and Password has to be at least 4 characters");
                        System.out.print("Enter username: ");
                        String newUsername = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String newPassword = scanner.nextLine();
                        if(DatabaseTableUser.isUserExist(newUsername)){
                            System.out.println("Username already exists!");
                        } else if (newUsername.length() < 4 && newPassword.length() < 4){
                            System.out.println("Both Username and Password has to be at least 4 characters !");
                        } else if (newUsername.length() < 4){
                            System.out.println("Username has to be at least 4 characters !");
                        } else if (newPassword.length() < 4){
                            System.out.println("Password has to be at least 4 characters !");
                        }  else {
                            User newUser = new User(newUsername, newPassword,false);
                            DatabaseTableUser.saveUserToDatabase(newUser);
                        }
                        break;
                    case 2:
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();
                        if(DatabaseTableUser.isAccountValid(username, password)){
                            User userPlaying = DatabaseTableUser.loadUser(username, password);
                            Game.start(userPlaying);
                            if(quitGame) running = false;
                        } else System.out.println("User doesn't exist!");
                        break;
                    case 3:
                        System.out.print("Enter username: ");
                        String newGuestname = scanner.nextLine();
                        if(DatabaseTableUser.isUserExist(newGuestname)){
                            System.out.println("Username already exists!");
                        } else if (newGuestname.length() < 4){
                            System.out.println("Username has to be at least 4 characters !");
                        } else {
                            User newUser = new User(newGuestname, null,true);
                            DatabaseTableUser.saveUserToDatabase(newUser);
                            Game.start(newUser);
                            if(quitGame) running = false;
                        }
                        break;
                    case 0:
                        running = false;
                        System.out.println("/// Cya! ///");
                        break;
                    }
            } catch ( WrongMainMenuOptionException e) {
                System.out.println(e.getMessage());
            } 
            catch ( InputMismatchException e) {
                System.out.println("Error: not a Number");
                scanner.nextLine();
            }
        }
        scanner.close();
    }
}