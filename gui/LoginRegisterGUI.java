package gui;

import game.Game;
import user.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginRegisterGUI {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel statusLabel;

    public static void main(String[] args) {
        new LoginRegisterGUI();
    }

    public LoginRegisterGUI() {
        frame = new JFrame("Pacman Game - Login/Register");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 1));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        statusLabel = new JLabel("", SwingConstants.CENTER);

        frame.add(new JLabel("Username:"));
        frame.add(usernameField);
        frame.add(new JLabel("Password:"));
        frame.add(passwordField);
        frame.add(loginButton);
        frame.add(registerButton);
        frame.add(statusLabel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegister();
            }
        });

        frame.setVisible(true);
    }

    private void handleRegister() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (!User.isNameExist(username)) {
            User newUser = new User(username, password);
            newUser.saveToDatabase();
            statusLabel.setText("Registration successful! You can now log in.");
        } else {
            statusLabel.setText("Username already exists!");
        }
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
    
        if (User.isAccountValid(username, password)) {
            statusLabel.setText("Login successful! Starting game");
            User userPlaying = User.loadUser(username, password);
            frame.dispose();
            startGame(userPlaying);
        } else {
            statusLabel.setText("Invalid username or password.");
        }
    }

    private void startGame(User userPlaying) {
        new Thread(() -> {
            Game.start(userPlaying);  
            restartGUI();     
        }).start();
    }
    
    private void restartGUI() {
        javax.swing.SwingUtilities.invokeLater(LoginRegisterGUI::new);
    }
}
