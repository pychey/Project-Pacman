package gui;

import map.Map;
import user.User;
import database.DatabaseTableHistory;
import database.DatabaseTableUser;
import entity.Ghost;
import entity.Pacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameGUI extends JFrame implements KeyListener {
    private User userPlaying;
    private boolean isPractice;
    private JLabel mapDisplay;
    private JLabel scoreLabel;
    private JLabel instructionLabel;
    public Map map;
    public Pacman pacman;
    public ArrayList<Ghost> ghostList;
    int currentLevel;
    int maxLevel = 5;
    int ghostMultiplier = 2;
    String gameResult;
    int totalScore;

    public GameGUI(JFrame pFrame, User user, boolean isPractice) {
        this.userPlaying = user;
        this.isPractice = isPractice;
        
        setLayout(new BorderLayout());
        setTitle(isPractice ? "Pacman Practice Mode" : "Pacman Game");
        setSize(1000, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(pFrame);
        
        mapDisplay = new JLabel();
        mapDisplay.setFont(new Font("Monospaced", Font.PLAIN, 18));
        mapDisplay.setForeground(new Color(0x00FF00));
		mapDisplay.setBackground(Color.black);
        mapDisplay.setOpaque(true);
        mapDisplay.setVerticalAlignment(JLabel.CENTER);
		mapDisplay.setHorizontalAlignment(JLabel.CENTER); 

        scoreLabel = new JLabel("Level: 1 | Score: 0 | Total Scores: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));

        instructionLabel = new JLabel("Use W/A/S/D to move. Press Q to quit.", SwingConstants.CENTER);

        add(scoreLabel,BorderLayout.NORTH);
        add(mapDisplay,BorderLayout.CENTER);
        add(instructionLabel,BorderLayout.SOUTH);
        
        addKeyListener(this);
        setFocusable(true);

        userPlaying.totalGamesPlayed++;
        currentLevel = 1;
        totalScore = 0;
        startGameForEachLevel();
    }

    private void startGameForEachLevel() {
        map = new Map();
        pacman = new Pacman(map);
        map.generateFood();
        if (!isPractice) initializeGhostsOnMap(currentLevel);
        printMapGUI();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char move = Character.toLowerCase(e.getKeyChar());
        if (move != 'q') {
            if (!isPractice) for (Ghost ghost : ghostList) ghost.moveRandomly(map);
            pacman.move(move, map);
            if (!isPractice){
                if (pacman.collidesWithGhost(ghostList)) {
                    userPlaying.totalLosses++;
                    gameResult = "Lose";
                    endGame(false);
                    return;
                }
            }
            if (map.areAllFoodEaten()) {
                if(isPractice) {
                    printMapGUI();
                    removeKeyListener(this);
                    JOptionPane.showMessageDialog(null, "Congratulation! You've isWon!", "Pracetice Completed", JOptionPane.PLAIN_MESSAGE);
                    this.dispose();
                    return;
                }
                
                totalScore += pacman.score;
                if (currentLevel < maxLevel) {
                    JOptionPane.showMessageDialog(null, "Level " + currentLevel + " Completed! Moving to Next Level !", "Level Completed", JOptionPane.PLAIN_MESSAGE);
                    currentLevel++;
                    startGameForEachLevel();
                } else {
                    userPlaying.totalWins++;
                    gameResult = "Win";
                    endGame(true);
                }
            }
            
            printMapGUI();
        } else {
            gameResult = "Surrender";
            endGame(false);
            return;}
        }

    private void endGame(boolean isWon) {
        removeKeyListener(this);
        totalScore += pacman.score;
    
        String resultMessage = isWon ? "Congratulations! You've completed all " + maxLevel + " levels!" + "\nTotal Score: " + totalScore : "You've Lost! You reached level " + currentLevel + "\nTotal Score: " + totalScore;

        int option = JOptionPane.showOptionDialog(this, resultMessage, "Game Result", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Leaderboard", "Return to Menu"}, "Leaderboard");
    
        if (!isPractice) {
            if(userPlaying.highScore < totalScore) userPlaying.highScore = totalScore;
            if(userPlaying.levelReached < currentLevel) userPlaying.levelReached = currentLevel;
            DatabaseTableUser.updateUserData(userPlaying);
            if (!userPlaying.isGuest) DatabaseTableHistory.addGameHistory(userPlaying.getUsername(),totalScore,currentLevel,gameResult);
        }
    
        if (option == JOptionPane.YES_OPTION) {
            LeaderboardGUI leaderboard = new LeaderboardGUI(this,userPlaying,true);
            this.dispose();
            leaderboard.setVisible(true);
        } else {
            GameMenuGUI gameMenu = new GameMenuGUI(this,userPlaying);
            this.dispose();
            gameMenu.setVisible(true); 
        }
    }

    private void printMapGUI() {
        StringBuilder mapString = new StringBuilder("<html><pre>");
        for (int i = 0; i < map.height; i++) {
            for (int j = 0; j < map.width ; j++) {
                mapString.append(map.grid[i][j]).append(" ");
            }
            mapString.append("\n");
        }
        mapString.append("</pre></html>");
        mapDisplay.setText(mapString.toString());
        scoreLabel.setText("Level: " + currentLevel + " | Score: " + pacman.score + " | Total Scores: " + totalScore);
    }

    public void initializeGhostsOnMap(int level){
        ghostList = new ArrayList<Ghost>();
        int numberOfGhosts = level * ghostMultiplier;
        for (int i = 0; i < numberOfGhosts; i++) ghostList.add(new Ghost("Ghost",'G', map));
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}