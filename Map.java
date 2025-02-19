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
    public void printMap() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(grid[i][j] + " "); // Print each character with a space
            }
            System.out.println(); // New line after each row
        }
        System.out.println(); // Extra line for spacing
    }
}