package userdata;
import main.Main;
import java.io.IOException;
import java.util.Scanner;

public class UserManager {
    public static void handleUserLogin() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("Choose an option: ");
        int option = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        try {
            if (option == 1) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();

                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                if(User.authenticateUsername(username)){
                    System.out.println("User already exist, try another username");
                } else {
                    User newUser = new User(username, password);
                    newUser.saveUser();
                    System.out.println("Registration successful!");
                    System.out.println("To play, you have to log in, have fun!");
                }
            } else if (option == 2) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();

                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                if (User.authenticate(username, password)) {
                    System.out.println("Login successful!");
                    Main.startGame(); // Call the startGame method from Main class
                } else {
                    System.out.println("Login failed. Please check your username and password.");
                }
            } else {
                System.out.println("Invalid option.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}
