package user;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setHighScore(int highScore) {
        if (highScore > this.highScore) {
            this.highScore = highScore;
        }
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
}
