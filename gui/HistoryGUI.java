package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import database.DatabaseTableHistory;
import user.User;

import java.awt.*;

public class HistoryGUI extends JFrame {

    public HistoryGUI(JFrame pFrame, User currentUser) {

        setTitle("Game History for " + currentUser.getUsername());
        setSize(1000, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(pFrame);

        JLabel titleLabel = new JLabel("Game History", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        String[] columnNames = {"Date", "Result", "Total Score", "Level Reached"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        DatabaseTableHistory.getHistoryData(currentUser.getUsername(), model);
        JTable historyTable = new JTable(model);
        historyTable.setRowHeight(25);
        historyTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        JScrollPane scrollPane = new JScrollPane(historyTable);

        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}
