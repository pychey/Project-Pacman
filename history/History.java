package history;

public class History {
    protected String username;
    public int totalScores;
    public int levelReached;
    public String result;
    public String gameDate;

    public History(int totalScores, int levelReached, String result, String gameDate) {
        this.totalScores = totalScores;
        this.levelReached = levelReached;
        this.result = result;
        this.gameDate = gameDate;
    }

    @Override
    public String toString() {
        return gameDate + "-> Result: " + result + ", Total Scores: " + totalScores + ", Level Reached: " + levelReached;
    }
}
