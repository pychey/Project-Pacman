package user;

public class User {

    protected String username;
    protected String password;
    public int totalGamesPlayed;
    public int highScore;
    public int totalWins;
    public int totalLosses;
    public boolean isGuest;

    public User(String username, String password, boolean isGuest) {
        this.username = username;
        this.password = password;
        this.totalGamesPlayed = 0;
        this.highScore = 0;
        this.totalWins = 0;
        this.totalLosses = 0;
        this.isGuest = isGuest;
    }

    public User(String username, int totalGamesPlayed, int highScore, int totalWins, int totalLosses) {
        this.username = username;
        this.totalGamesPlayed = totalGamesPlayed;
        this.highScore = highScore;
        this.totalWins = totalWins;
        this.totalLosses = totalLosses;
    }

    @Override
    public String toString() {
        return username + ", High Score: " + highScore + ", Wins: " + totalWins + ", Losses: " + totalLosses;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }
}
