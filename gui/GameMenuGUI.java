package gui;

import user.User;
import database.DatabaseTableUser;
import javax.swing.*;
import java.awt.*;

public class GameMenuGUI extends JFrame {
    User userPlaying;

    public static void main(String[] args) {
        GameMenuGUI gameMenu = new GameMenuGUI(null,new User("liraky",null,false));
        gameMenu.setVisible(true);
    }

    public GameMenuGUI(JFrame pFrame, User userPlaying) {
        this.userPlaying = userPlaying;
        setTitle("Pacman Game Menu");
        setSize(1100, 700);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(pFrame);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1));
        
        JLabel titleLabel = new JLabel("Pacman Game Menu", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        JLabel userInfoLabel = new JLabel("Welcome, " + userPlaying.getUsername(), SwingConstants.CENTER);
        
        JLabel statsLabel = new JLabel("Games Played: " + userPlaying.totalGamesPlayed + " | High Score: " + userPlaying.highScore, SwingConstants.CENTER); 

        JButton playButton = createStyledButton("Play Game");
        JButton practiceButton = createStyledButton("Practice Mode");
        JButton leaderboardButton = createStyledButton("Leaderboard");
        JButton historyButton = createStyledButton("Game History");
        JButton deleteAccountButton = createStyledButton("Delete Account");
        JButton logoutButton = createStyledButton("Logout");
        JButton quitButton = createStyledButton("Quit");
        
        playButton.addActionListener(e -> startGame(false));
        practiceButton.addActionListener(e -> startGame(true));
        leaderboardButton.addActionListener(e -> openLeaderboard());
        historyButton.addActionListener(e -> openHistory());
        deleteAccountButton.addActionListener(e -> deleteAccount());
        logoutButton.addActionListener(e -> logout());
        quitButton.addActionListener(e -> quitGame());
        
        if (userPlaying.isGuest) historyButton.setEnabled(false);
        
        mainPanel.add(Box.createRigidArea(new Dimension()));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension()));
        mainPanel.add(userInfoLabel);
        mainPanel.add(statsLabel);
        mainPanel.add(Box.createRigidArea(new Dimension()));
        mainPanel.add(playButton);
        mainPanel.add(practiceButton);
        mainPanel.add(leaderboardButton);
        mainPanel.add(historyButton);
        mainPanel.add(deleteAccountButton);
        mainPanel.add(logoutButton);
        mainPanel.add(quitButton);
        mainPanel.add(Box.createRigidArea(new Dimension()));
        
        add(mainPanel);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 40));
        return button;
    }

    private void startGame(boolean isPractice) {
        GameGUI game = new GameGUI(this, userPlaying, isPractice);
        this.dispose();
        game.setVisible(true);
    }
    
    private void openLeaderboard() {
        LeaderboardGUI leaderboard = new LeaderboardGUI(this,userPlaying,false);
        leaderboard.setVisible(true);
    }
    
    private void openHistory() {
        if (!userPlaying.isGuest) {
            HistoryGUI history = new HistoryGUI(this,userPlaying);
            history.setVisible(true);
        }
    }
    

    private void deleteAccount() {
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete your account?", "Confirm Account Deletion", JOptionPane.YES_NO_OPTION);
        
        if (option == JOptionPane.YES_OPTION) {
            DatabaseTableUser.deleteAccount(userPlaying.getUsername());
            JOptionPane.showMessageDialog(this, "Account deleted successfully");
            this.dispose();
            new LoginRegisterGUI(this).setVisible(true);
        }
    }

    private void logout() {
        this.dispose();
        new LoginRegisterGUI(this).setVisible(true);
    }

    private void quitGame(){
        this.dispose();
    }
}