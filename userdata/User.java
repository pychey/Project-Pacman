package userdata;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Save user to a file
    public void saveUser() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true));
        writer.write(username + "," + password);
        writer.newLine();
        writer.close();
    }

    // Authenticate user
    public static boolean authenticate(String username, String password) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
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
}
