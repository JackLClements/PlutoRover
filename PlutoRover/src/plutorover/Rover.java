/*
 */
package plutorover;



/**
 * Class representing a single rover, with co-ordinates and a heading.
 * Heading is represented as an inner-enum in order to allow casting and potential
 * future extension. 
 * Abstraction via casting is used in methods in order to prevent needlessly
 * interpreting commands over again. 
 * @author Jack L. Clements
 */
public class Rover {
    private int x, y;
    private Heading dir;
    
    public static enum Heading{NORTH(0), EAST(90), SOUTH(180), WEST(270);
        final int headingAngle;
        Heading(int angle){
            headingAngle = angle;
        }
        
        public char getDirShort(){
            char headingChar = 'a';
            switch(headingAngle){
                    case 0:
                        headingChar = 'N';
                        break;
                    case 90:
                        headingChar = 'E';
                        break;
                    case 180:
                        headingChar = 'S';
                        break;
                    case 270:
                        headingChar = 'W';
                        break; //variable already intialised, no need for default
            }
            return headingChar;
        }
    }; //note - is this overcomplicated? May have to simply store char instead.
    
    public Rover(){
        this.x = 0;
        this.y = 0;
        this.dir = Heading.NORTH;
    }
    
    public Rover(int x, int y, Heading dir){
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
    
    //Mutator Methods
    
    public void move(char moveCommand){
        //TODO - implement, this method is a prototype
    }
    
    public void turn(char turnCommand){
        //TODO - implement, this method is a prototype
    }
    
    
    //Accessor methods
    /**
     * Returns Rover's x-value
     * @return 
     */
    public int getX(){
        return this.x;
    }
    
    /**
     * Return's Rover's y-value
     * @return 
     */
    public int getY(){
        return this.y;
    }
    
    /**
     * Returns Rover's heading as a Heading value
     * @return 
     */
    public Heading getDir(){
        return this.dir;
    }
    
    /**
     * Sets Rover's x-value
     * @param x 
     */
    public void setX(int x){
        this.x = x;
    }
    
    /**
     * Set's Rover's y-value
     * @param y 
     */
    public void setY(int y){
        this.y = y;
    }
    
    /**
     * Set's Rover's heading as a Heading object
     * @param dir 
     */
    public void setDir(Heading dir){
        this.dir = dir;
    }
    
    public void setDir(char dir){
        switch(dir){
            case 'N':
                this.dir = Heading.NORTH;
                break;
            case 'E':
                this.dir = Heading.EAST;
                break;
            case 'S':
                this.dir = Heading.SOUTH;
                break;
            case 'W':
                this.dir = Heading.WEST;
                break;
            default:
                //throw exception?
                break;
        }
    }
    
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.x).append(", ").append(this.y).append(", ").append(this.dir.getDirShort());
        return sb.toString();
    }
    
}
