package Map;
import Entity.Pacman;

public class Map {
    public int width,height;
    public int foodCount = 0;
    public char[][] grid = {
        {'x','x','x','x','x','x','x','x','x','x','x'},
        {'x',' ',' ',' ',' ',' ',' ',' ',' ',' ','x'},
        {'x',' ','x',' ','x',' ','x',' ','x',' ','x'},
        {'x',' ','x',' ','x',' ','x',' ','x',' ','x'},
        {'x',' ','x',' ','x',' ','x',' ','x',' ','x'},
        {'x',' ','x',' ','x',' ','x',' ','x',' ','x'},
        {'x',' ','x',' ','x',' ','x',' ','x',' ','x'},
        {'x',' ','x',' ','x',' ','x',' ','x',' ','x'},
        {'x',' ',' ',' ',' ',' ',' ',' ',' ',' ','x'},
        {'x','x','x','x','x','x','x','x','x','x','x'}
    };

    public Map() {
        height = grid.length;
        width = grid[0].length;
    }
    public boolean isWithinBound(int x, int y){
        return (x >= 0 && x < width) && (y >= 0 && y < height);
    }
    public void placePacman(int x, int y){
        if(grid[x][y] != 'x' && isWithinBound(x, y)){
            grid[x][y] = 'P';
        }
    }
    public void placeGhost(int x, int y){
        if(grid[x][y] != 'x' && isWithinBound(x, y)){
            grid[x][y] = 'G';
        }
    }
    
    public void clearCharacter(int x, int y){
        grid[x][y] = ' ';
    }

    public void generateFood(){
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                if(grid[i][j] == ' '){
                    grid[i][j] = '.';
                    foodCount++;
                }
            }
        }
    }
    public void movePacman(char move, Pacman pacman){
        clearCharacter(pacman.x, pacman.y);
        if(move == 'w'){
            pacman.moveUp();
        }else if(move == 's'){
            pacman.moveDown();
        }else if(move == 'a'){
            pacman.moveLeft();
        }else if(move == 'd'){
            pacman.moveRight();
        }else{
            System.out.println("Invalid move! Please use W, A, S, or D.");
        }
        if(isFoodThere(pacman.x, pacman.y)){
            pacman.score++;
        }
        placePacman(pacman.x, pacman.y);
    }
    public boolean isFoodThere(int x, int y){
        if(grid[x][y] == '.'){
            grid[x][y] = ' ';
            foodCount--;
            return true;
        }
        return false;
    }


    public void printMap() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(grid[i][j] + " "); 
            }
            System.out.println();
        }
        System.out.println();
    }
}