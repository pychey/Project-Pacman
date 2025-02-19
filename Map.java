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
}