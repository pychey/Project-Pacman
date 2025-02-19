public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }


}

//if entered wrong, ask to enter move again imidietly
//when moving toward wall, pacman should stay in the same place
//ghost should move after pacman move, but ghost should not move into wall
//check for colision between ghost and pacman, lost if colision
//check if all food eaten, if all eaten, win game
//when win or lose, ask player to either quit or play again
//keep track of number of game being played using static
