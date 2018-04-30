/*
 */
package plutorover;

import java.util.ArrayList;

/**
 * Class representing a single planet
 * Comprises of a set height/width, with a no. of collidable objects
 * @author Jack L. Clements
 */
public class Planet {
    private int height, width;
    private boolean [][] obstacles; //2D array, true if obstacle exists, otherwise false
    //Use of 2D array over objects will save memory, but creation of more complex
    //obstacles would mean setting each individual grid space
    
    /*
    NOTE -
    Remember X/Width - no. of columns
             Y/Height - no. of rows
    
    It's easy to get turned around. From the outside, a user should be able to only see X/Y and correctly assume column-row indexing.
    It's the job of this class to format passed parameters to column-row.
    */
    
    /**
     * Default constructor
     */
    public Planet(){
        this.height = 10;
        this.width = 10;
        this.obstacles = new boolean [height][width]; //boolean defaults to false
    }
    
    /**
     * Value based constructor
     * @param sizeX no. of horizontal tiles/columns
     * @param sizeY no. of vertical tiles/rows
     */
    public Planet(int sizeX, int sizeY){
        this.width = sizeX;
        this.height = sizeY;
        this.obstacles = new boolean [sizeY][sizeX]; //indexed as row-column, 
    }
    
    //Mutator methods
    /**
     * Places a collidable object on the grid
     * @param x x co-ordinate
     * @param y y co-ordinate
     */
    public void placeObstacle(int x, int y){ //remember java access is row-column
        obstacles[y][x] = true;
    }
    
    /**
     * Checks whether a given co-ordinate contains an object 
     * @param x x co-ordinate
     * @param y y co-ordinate
     * @return 
     */
    public boolean isObstacle(int x, int y){
        return obstacles[y][x];
    }
    
    //Accessor methods
    /**
     * Returns planet height
     * @return 
     */
    public int getHeight(){
        return this.height;
    }
    
    /**
     * Returns planet width
     * @return 
     */
    public int getWidth(){
        return this.width;
    }
    
    /**
     * 
     */
    public boolean [][] getObstacles(){
        return this.obstacles;
    }
    
    /**
     * Sets planet height
     * @param height 
     */
    public void setHeight(int height){
        this.height = height;
        this.obstacles = new boolean [height][this.width];
    }
    
    /**
     * Sets planet width
     * @param width 
     */
    public void setWidth(int width){
        this.width = width;
        this.obstacles = new boolean [this.height][width];
    }
}
