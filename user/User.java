package user;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class User {
    public static String filename = "./user/userdata.txt";
    private String username;      
    private String password;     
    public int highScore;       
    public int totalGamesPlayed;  
    public int totalWins;         
    public int totalLosses;  

    public User(String username, String password) {
        this.username = username;
        this.password = password; 
        this.highScore = 0;
        this.totalGamesPlayed = 0;
        this.totalWins = 0;
        this.totalLosses = 0;
    }

    public User(String username, String password, int highScore, int totalGamesPlayed, int totalWins, int totalLosses){
        this.username = username;
        this.password = password; 
        this.highScore = highScore;
        this.totalGamesPlayed = highScore;
        this.totalWins = totalWins;
        this.totalLosses = totalLosses;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String toString() {
        return username + "," + password + "," + highScore + "," + totalGamesPlayed + "," + totalWins + "," + totalLosses;
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(username + "," + password + "," +
                         highScore+ "," + totalGamesPlayed + "," +
                         totalWins + "," + totalLosses);
            writer.newLine();
            System.out.println("Saving user data completed!");
        } catch (IOException e) {
            System.out.println("Error: saving user data failed!");
        }
    }

    public static boolean isAccountValid(String username, String password) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] userInfo = line.split(",");
            if (userInfo[0].equals(username) && userInfo[1].equals(password)) {
                reader.close();
                return true;
            }
        }
        reader.close();
        return false;
    }

    public static boolean isNameExist(String username) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] userInfo = line.split(",");
            if (userInfo[0].equals(username)) {
                reader.close();
                return true;
            }
        }
        reader.close();
        return false;
    }

    public static User loadUser(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(username) && data[1].equals(password)) {
                    User user = new User(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[4]), Integer.parseInt(data[5]));
                    return user;
                }
            }
        } catch (IOException e) {
            System.out.println("Error: reading user data failed!");
        }
        return null;
    }

    public void updateData()  {
        List<User> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                User user = new User(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[4]), Integer.parseInt(data[5]));
                if (user.getUsername().equals(username)) {
                    users.add(this); 
                } else {
                    users.add(user);
                }
            }
        } catch (IOException e) {
            System.out.println("Error: reading user data failed!");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (User user : users) {
                writer.write(user.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error: writing user data failed!");
        }
    }
}
