package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import database.DatabaseTableUser;
import user.User;

import java.awt.*;

public class LeaderboardGUI extends JFrame {

    public LeaderboardGUI(JFrame pFrame, User currentUser, boolean isBackButton) {

        setTitle("Pacman Leaderboard");
        setSize(1000, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Pacman Leaderboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        String[] columnNames = {"Rank", "Username", "High Score", "Total Wins", "Levels Reached"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        DatabaseTableUser.getLeaderboardData(model);
        JTable leaderboardTable = new JTable(model);
        leaderboardTable.setRowHeight(25);
        leaderboardTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(leaderboardTable);

        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> {
            this.dispose();
            new GameMenuGUI(this,currentUser).setVisible(true); 
        });

        // Layout
        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        if(isBackButton) add(backButton, BorderLayout.SOUTH);
    }
}
