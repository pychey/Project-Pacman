package database;

import user.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLConnection {

    private static String url = "jdbc:mysql://localhost:3306/pacman";
    private static String user = "root";
    private static String pass = "@123Pychey";

    public static void saveUserToDatabase(User userToSave) {
        String query = """
                        INSERT INTO users
                        (username, password, totalGamesPlayed, highScore, totalWins, totalLosses) 
                        VALUES (?, ?, ?, ?, ?, ?)
                        """ ;
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             PreparedStatement preSt = connection.prepareStatement(query)) {
            preSt.setString(1, userToSave.getUsername());
            preSt.setString(2, userToSave.getPassword());
            preSt.setInt(3, userToSave.totalGamesPlayed);
            preSt.setInt(4, userToSave.highScore);
            preSt.setInt(5, userToSave.totalWins);
            preSt.setInt(6, userToSave.totalLosses);
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
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             PreparedStatement preSt = connection.prepareStatement(query)) {
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

    public static boolean isUserExist(String username) {
        String query = """
                        SELECT 1 FROM users 
                        WHERE username = ?
                        """;
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             PreparedStatement preSt = connection.prepareStatement(query)) {
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
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             PreparedStatement preSt = connection.prepareStatement(query)) {
            preSt.setString(1, username);
            preSt.setString(2, password);
            try (ResultSet resultSet = preSt.executeQuery()) {
                if (resultSet.next()) {
                    return new User(
                        resultSet.getString("username"),
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

    public static void updateUserData(User userPlaying) {
        String query = """
                        UPDATE users 
                        SET totalGamesPlayed = ?, highScore = ?, totalWins = ?, totalLosses = ? 
                        WHERE username = ?
                        """ ;
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             PreparedStatement preSt = connection.prepareStatement(query)) {
            preSt.setInt(1, userPlaying.totalGamesPlayed);
            preSt.setInt(2, userPlaying.highScore);
            preSt.setInt(3, userPlaying.totalWins);
            preSt.setInt(4, userPlaying.totalLosses);
            preSt.setString(5, userPlaying.getUsername());
            preSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void displayLeaderboard() {
        String query = """
                        SELECT * FROM users 
                        ORDER BY highScore DESC
                        """ ;
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             PreparedStatement preSt = connection.prepareStatement(query);
             ResultSet resultSet = preSt.executeQuery()) {
            System.out.println("Leaderboard:");
            while (resultSet.next()) {
                User user = new User(
                    resultSet.getString("username"),
                    resultSet.getInt("totalGamesPlayed"),
                    resultSet.getInt("highScore"),
                    resultSet.getInt("totalWins"),
                    resultSet.getInt("totalLosses")
                );
                System.out.println(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
