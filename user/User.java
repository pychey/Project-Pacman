package user;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private static String url = "jdbc:mysql://localhost:3306/pacman";
    private static String user = "root";
    private static String pass = "@123Pychey";
    
    private String username;
    private String password;
    public int totalGamesPlayed;
    public int highScore;
    public int totalWins;
    public int totalLosses;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.totalGamesPlayed = 0;
        this.highScore = 0;
        this.totalWins = 0;
        this.totalLosses = 0;
    }

    public User(String username, String password, int totalGamesPlayed, int highScore, int totalWins, int totalLosses) {
        this.username = username;
        this.password = password;
        this.totalGamesPlayed = totalGamesPlayed;
        this.highScore = highScore;
        this.totalWins = totalWins;
        this.totalLosses = totalLosses;
    }

    @Override
    public String toString() {
        return username + ", High Score: " + highScore + ", Wins: " + totalWins + ", Losses: " + totalLosses;
    }

    public void saveToDatabase() {
        String query = """
                        INSERT INTO users
                        (username, password, totalGamesPlayed, highScore, totalWins, totalLosses) 
                        VALUES (?, ?, ?, ?, ?, ?)
                        """ ;
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            PreparedStatement preSt = connection.prepareStatement(query);
            preSt.setString(1, username);
            preSt.setString(2, password);
            preSt.setInt(3, totalGamesPlayed);
            preSt.setInt(4, highScore);
            preSt.setInt(5, totalWins);
            preSt.setInt(6, totalLosses);
            preSt.executeUpdate();
            System.out.println("User Saved Successfully!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean isAccountValid(String username, String password) {
        String query = """
                        SELECT 1 FROM users 
                        WHERE username = ? AND password = ?
                        """ ; 
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            PreparedStatement preSt = connection.prepareStatement(query);
            preSt.setString(1, username);
            preSt.setString(2, password);
            try (ResultSet resultSet = preSt.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean isNameExist(String username) {
        String query = """
                        SELECT 1 FROM users 
                        WHERE username = ?
                        """;
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            PreparedStatement preSt = connection.prepareStatement(query);
            preSt.setString(1, username);
            try (ResultSet resultSet = preSt.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static User loadUser(String username, String password) {
        String query = """
                        SELECT * FROM users
                        WHERE username = ? AND password = ?
                        """ ;
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            PreparedStatement preSt = connection.prepareStatement(query);
            preSt.setString(1, username);
            preSt.setString(2, password);
            try (ResultSet resultSet = preSt.executeQuery()) {
                if (resultSet.next()) {
                    return new User(
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getInt("totalGamesPlayed"),
                        resultSet.getInt("highScore"),
                        resultSet.getInt("totalWins"),
                        resultSet.getInt("totalLosses")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void updateData() {
        String query = """
                        UPDATE users 
                        SET totalGamesPlayed = ?, highScore = ?, totalWins = ?, totalLosses = ? 
                        WHERE username = ?
                        """ ;
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             PreparedStatement preSt = connection.prepareStatement(query)) {
            preSt.setInt(1, totalGamesPlayed);
            preSt.setInt(2, highScore);
            preSt.setInt(3, totalWins);
            preSt.setInt(4, totalLosses);
            preSt.setString(5, username);
            preSt.executeUpdate();
            System.out.println("Userdata Updated Successfully!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void displayLeaderboard() {
        ArrayList<User> users = new ArrayList<>();
        String query = """
                        SELECT * FROM users 
                        ORDER BY highScore DESC
                        """;
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             PreparedStatement preSt = connection.prepareStatement(query);
             ResultSet resultSet = preSt.executeQuery()) {
            while (resultSet.next()) {
                users.add(new User(
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getInt("totalGamesPlayed"),
                    resultSet.getInt("highScore"),
                    resultSet.getInt("totalWins"),
                    resultSet.getInt("totalLosses")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println("Leaderboard:");
        for (User user : users) {
            System.out.println(user);
        }
    }
}
