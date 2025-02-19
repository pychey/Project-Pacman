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

    Map() {
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
            placePacman(pacman.x, pacman.y);
        }else if(move == 's'){
            pacman.moveDown();
            placeGhost(pacman.x, pacman.y);
        }else if(move == 'a'){
            pacman.moveLeft();
            placeGhost(pacman.x, pacman.y);
        }else if(move == 'd'){
            pacman.moveRight();
            placeGhost(pacman.x, pacman.y);
        }else{
            System.out.println("Invalid move! Please use W, A, S, or D.");
        }
    }
    public boolean isFoodEaten(int x, int y){
        if(grid[x][y] == '.'){
            grid[x][y] = ' ';
            foodCount--;
        }
        return true;
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