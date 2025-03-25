package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import history.History;

public class DatabaseTableHistory {
    
    protected static String url = "jdbc:mysql://localhost:3306/pacman";
    protected static String user = "root";
    protected static String pass = "@123Pychey";

    public static String getCurrentTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static void addGameHistory(String username, int totalScore, int levelReached, String gameResult){
        String query = """
                        INSERT INTO histories
                        (username, totalScores , levelReached, result, gameDate) 
                        VALUES (?, ?, ?, ?, ?)
                        """ ;
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             PreparedStatement preSt = connection.prepareStatement(query)) {
            preSt.setString(1, username);
            preSt.setInt(2, totalScore);
            preSt.setInt(3, levelReached);
            preSt.setString(4, gameResult);
            preSt.setString(5, getCurrentTime());
            preSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getHistory(String username){
        String query = """
                        SELECT * FROM histories
                        WHERE username = ?
                        ORDER BY gameDate DESC
                        """ ;
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             PreparedStatement preSt = connection.prepareStatement(query)) {
            preSt.setString(1, username);
            try (ResultSet resultSet = preSt.executeQuery()) {
                if(resultSet.next()){
                    int historyNumber = 1;
                    System.out.println("User History:");
                    do {
                        History userHistory = new History(
                            resultSet.getInt("totalScores"),
                            resultSet.getInt("levelReached"),
                            resultSet.getString("result"),
                            resultSet.getString("gameDate")
                        );
                        System.out.println(historyNumber + " - " + userHistory);
                        historyNumber += 1;
                    } while (resultSet.next());
                } else System.out.println("No History Records!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
