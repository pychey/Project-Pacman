package gui;

import user.User;
import javax.swing.*;
import java.awt.*;
import database.DatabaseTableUser;

public class LoginRegisterGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel statusLabel;

    public static void main(String[] args) {
        new LoginRegisterGUI(null).setVisible(true);
    }

    public LoginRegisterGUI(JFrame pFrame) {
        setTitle("Pacman Game - Login/Register");
        setSize(1000, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(pFrame);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1));
        
        JLabel titleLabel = new JLabel("Pacman Game", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);
        
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel passwordLabel = new JLabel("Password:  ");
        passwordField = new JPasswordField(20);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        JButton guestButton = new JButton("Play as Guest");
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(guestButton);
        
        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setForeground(Color.RED);
        
        mainPanel.add(Box.createRigidArea(new Dimension()));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension()));
        mainPanel.add(usernamePanel);
        mainPanel.add(passwordPanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(statusLabel);
        
        loginButton.addActionListener(e -> handleLogin());
        registerButton.addActionListener(e -> handleRegister());
        guestButton.addActionListener(e -> showGuestPopup());
        
        add(mainPanel);
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        
        if (DatabaseTableUser.isAccountValid(username, password)) {
            User userPlaying = DatabaseTableUser.loadUser(username, password);
            openGameMenu(userPlaying);
        } else {
            statusLabel.setText("Invalid username or password.");
        }
    }

    private void handleRegister() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        
        if (username.length() < 4 || password.length() < 4) {
            statusLabel.setText("Username and password must be at least 4 characters.");
            return;
        }
        
        if (!DatabaseTableUser.isUserExist(username)) {
            User newUser = new User(username, password, false);
            DatabaseTableUser.saveUserToDatabase(newUser);
            statusLabel.setText("Registration successful!");
        } else {
            statusLabel.setText("Username already exists!");
        }
    }

    private void showGuestPopup() {
        JFrame guestPopup = new JFrame("Play as Guest");
        guestPopup.setSize(800, 300);
        guestPopup.setLayout(new GridLayout(0, 1, 10, 10));
    
        JLabel titleLabel = new JLabel("Play as Guest", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        guestPopup.add(titleLabel);
    
        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        usernamePanel.add(new JLabel("Username:"));
        JTextField usernameField = new JTextField(15);
        usernamePanel.add(usernameField);
        guestPopup.add(usernamePanel);
    
        JLabel warningLabel = new JLabel("Username must be at least 4 characters", SwingConstants.CENTER);
        warningLabel.setForeground(Color.BLACK);
        guestPopup.add(warningLabel);
    
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton submitButton = new JButton("Play");
        submitButton.addActionListener(e -> {
            String username = usernameField.getText();
    
            if(DatabaseTableUser.isUserExist(username)){
                JOptionPane.showMessageDialog(guestPopup, "Username already exists!", "Guest Error", JOptionPane.WARNING_MESSAGE);
            } else if (username.length() < 4){
                JOptionPane.showMessageDialog(guestPopup, "Username must be at least 4 characters!", "Guest Error", JOptionPane.WARNING_MESSAGE);
            } else {
                User newUser = new User(username, null,true);
                DatabaseTableUser.saveUserToDatabase(newUser);
                guestPopup.dispose();
                openGameMenu(newUser);
            }
        });
        buttonPanel.add(submitButton);
        guestPopup.add(buttonPanel);
    
        guestPopup.getRootPane().setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        guestPopup.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        guestPopup.setLocationRelativeTo(this);
        guestPopup.setVisible(true);
    }

    private void openGameMenu(User userPlaying) {
        GameMenuGUI gameMenu = new GameMenuGUI(this, userPlaying);
        this.dispose();
        gameMenu.setVisible(true);
    }
}