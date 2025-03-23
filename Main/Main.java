package main;
import java.util.InputMismatchException;
import java.util.Scanner;
import exception.WrongMainMenuOptionException;
import game.Game;
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
                new WrongMainMenuOptionException(opt);

                switch (opt) {
                    case 1:
                        System.out.print("Enter username: ");
                        String newUsername = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String newPassword = scanner.nextLine();
                        if(!User.isNameExist(newUsername)){
                            User newUser = new User(newUsername, newPassword);
                            newUser.saveToDatabase();
                        } else System.out.println("Username already exists!");
                        break;
                    case 2:
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();
                        if(User.isAccountValid(username, password)){
                            User userPlaying = User.loadUser(username, password);
                            Game.start(userPlaying);
                        } else System.out.println("User doesn't exist!");
                        break;
                    case 3:
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