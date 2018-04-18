/*
 */
package plutorover;

import java.util.ArrayList;

/**
 *
 * @author Jack L. Clements
 */
public class Planet {
    private int height, width;
    private boolean [][] obstacles; //2D array, true if obstacle exists, otherwise false
    //Use of 2D array over objects will save memory, but creation of more complex
    //obstacles with mean setting each individual grid space, which can have a method
    public Planet(){
        this.height = 10;
        this.width = 10;
        this.obstacles = new boolean [height][width]; //boolean defaults to false
    }
    
    public Planet(int sizeX, int sizeY){
        this.height = sizeX;
        this.width = sizeY;
        this.obstacles = new boolean [sizeX][sizeY];
    }
    
    //Mutator methods
    public void placeObstacle(int x, int y){ //remember java access is row-column
        obstacles[y][x] = true;
    }
    
    public boolean isObstacle(int x, int y){
        return obstacles[y][x];
    }
    
    //Accessor methods
    public int getHeight(){
        return this.height;
    }
    
    public int getWidth(){
        return this.width;
    }
    
    
    
    
}
