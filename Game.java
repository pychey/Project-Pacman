

public class Game {

    public Game() {



    }

    //Create map object
    Map map= new Map();
    //create pacman and ghost
    Pacman pacman = new Pacman(1,1);
    Ghost ghosts = new Ghost(8,8,"orange",'o');





    public void startGame(){
        map.placePacman(pacman.x, pacman.y);
        map.placeGhost(ghosts.x, ghosts.y);
        map.generateFood();
        map.printMap();
    }


}
