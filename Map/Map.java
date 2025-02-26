package map;

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
        return (x >= 0 && x < height) && (y >= 0 && y < width);
    }

    public boolean isWall(int x, int y){
        return grid[x][y] == 'x';
    }

    public boolean isPacman(int x, int y){
        return grid[x][y] == 'P';
    }

    public boolean isGhost(int x, int y){
        return grid[x][y] == 'G';
    }

    public char returnGrid(int x, int y){
        return grid[x][y];
    }

    public void placePacman(int x, int y){
        if(!isWall(x, y) && isWithinBound(x, y)){
            grid[x][y] = 'P';
        }
    }

    public void placeGhost(int x, int y){
        if(!isWall(x, y) && isWithinBound(x, y)){
            grid[x][y] = 'G';
        }
    }

    public void clearGhost(int x, int y, char lastGridOfGhost){
        if(lastGridOfGhost == '.'){
            grid[x][y] = '.';
        } 
        else grid[x][y] = ' ';
    }
    
    public void clearPacman(int x, int y){
        if(grid[x][y] == 'G'){
            placeGhost(x, y);
        }
        else grid[x][y] = ' ';
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
    
    public boolean isFoodThere(int x, int y){
        if(grid[x][y] == '.'){
            grid[x][y] = ' ';
            foodCount--;
            return true;
        }
        return false;
    }

    public boolean areAllFoodEaten(){
        return foodCount == 0;
    }


    public void printMap() {
        System.out.println();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(grid[i][j] + " "); 
            }
            System.out.println();
        }
    }
}