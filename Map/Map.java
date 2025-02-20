package map;

public class Map {
    public int width,height;
    public int foodCount = 0;
    public static char[][] grid1 = {
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
    public char[][] grid = grid1;

    public Map() {
        height = grid.length;
        width = grid[0].length;
    }

    public boolean isWithinBound(int x, int y){
        return (x >= 0 && x < width) && (y >= 0 && y < height);
    }

    public boolean isWall(int x, int y){
        return grid[x][y] == 'x';
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
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(grid[i][j] + " "); 
            }
            System.out.println();
        }
        System.out.println();
    }
}