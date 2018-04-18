/*
 */
package plutorover;

import Exceptions.InvalidMovementException;

/**
 * Class representing a single rover, with co-ordinates and a heading. Heading
 * is represented as an inner-enum in order to allow casting and potential
 * future extension. Abstraction via casting is used in methods in order to
 * prevent needlessly interpreting commands over again.
 *
 * @author Jack L. Clements
 */
public class Rover {

    private int x, y;
    private Heading dir;
    private Planet planet;

    //Co-ordinates in this space are column, row (x, y)
    //this encapsulation makes the movement system extendable, you can add more headings w/ forward directions
    public static enum Heading {
        NORTH('N', 0, 1), EAST('E', 1, 0), SOUTH('S', 0, -1), WEST('W', -1, 0); //ordering of elements is important
        final char headingAngle;
        final int forwardX, forwardY;
        private static Heading[] headings = values();

        Heading(char headingAngle, int forwardX, int forwardY) {
            this.headingAngle = headingAngle;
            this.forwardX = forwardX;
            this.forwardY = forwardY;
        }

        public char getDir() {
            return headingAngle;
        }

        public Heading left() {
            /*
            if(this.equals(Heading.NORTH)){
                return Heading.WEST;
            }
            else{
                return headings[this.ordinal()-1]; //need to make sure it's never <0, test for this
            }*/ //just in case this doesn't work...
            int pos = (this.ordinal() - 1) % headings.length;
            return pos < 0 ? headings[pos + headings.length] : headings[pos];
        }

        public Heading right() {
            /*if(this.equals(Heading.WEST)){
                return Heading.NORTH;
            }
            else{
                return headings[this.ordinal()+1];
            }*/
            return headings[(this.ordinal() + 1) % headings.length];
        }

        public int getForwardX() {
            return this.forwardX;
        }

        public int getForwardY() {
            return this.forwardY;
        }

        //could implement forward/backward movement in size 2 array, but maybe no need?
    }; //note - is this overcomplicated? May have to simply store char instead.

    public Rover() {
        this.x = 0;
        this.y = 0;
        this.dir = Heading.NORTH;
    }

    public Rover(int x, int y, Heading dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    //Mutator Methods
    
    public void process(char command) throws InvalidMovementException{
        switch (command) {
            case 'F':
            case 'B':
                move(command);
                break;
            case 'L':
            case 'R':
                turn(command);
                break;
            case 'E': //put in to handle manual/console input
                break;
            default:
                throw new InvalidMovementException(command);
        }
    }
    
    //actual movement methods
    
    public void move(char moveCommand) throws InvalidMovementException {
        int newX, newY;
        switch (moveCommand) {
            case 'F':
                /*
                this.x += dir.getForwardX();
                this.y += dir.getForwardY();
                this.x = this.x % planet.getWidth();
                this.y = this.y % planet.getHeight();*/
                newX = (this.x + dir.getForwardX()) % planet.getWidth();
                newY = (this.y + dir.getForwardY()) % planet.getHeight();
                //negative bound checking, albeit inelegant
                if(newX < 0){
                    newX += planet.getWidth();
                }
                if(newY < 0){
                    newY += planet.getHeight();
                }
                
                if(!planet.isObstacle(newX, newY)){ //could spin this off into new method, but it wouldn't massively reduce code
                    this.x = newX;
                    this.y = newY;
                }
                
                break;
            case 'B':
                /*
                this.x -= dir.getForwardX();
                this.y -= dir.getForwardY();
                this.x = this.x % planet.getWidth();
                this.y = this.y % planet.getHeight();*/
                newX = (this.x - dir.getForwardX()) % planet.getWidth();
                newY = (this.y - dir.getForwardY()) % planet.getHeight();
                if(newX < 0){
                    newX += planet.getWidth();
                }
                if(newY < 0){
                    newY += planet.getHeight();
                }
                if(!planet.isObstacle(newX, newY)){
                    this.x = newX;
                    this.y = newY;
                }
                
                break;
            default:
                throw new InvalidMovementException(moveCommand);
        }
    }

    public void turn(char turnCommand) throws InvalidMovementException {
        switch (turnCommand) {
            case 'L':
                this.dir = this.dir.left();
                break;
            case 'R':
                this.dir = this.dir.right();
                break;
            default:
                throw new InvalidMovementException(turnCommand);
        }
    }

    //Accessor methods
    /**
     * Returns Rover's x-value
     *
     * @return
     */
    public int getX() {
        return this.x;
    }

    /**
     * Return's Rover's y-value
     *
     * @return
     */
    public int getY() {
        return this.y;
    }

    /**
     * Returns Rover's heading as a Heading value
     *
     * @return
     */
    public Heading getDir() {
        return this.dir;
    }

    public Planet getPlanet() {
        return this.planet;
    }

    /**
     * Sets Rover's x-value
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set's Rover's y-value
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Set's Rover's heading as a Heading object
     *
     * @param dir
     */
    public void setDir(Heading dir) {
        this.dir = dir;
    }

    public void setDir(char dir) throws InvalidMovementException {
        switch (dir) {
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
                throw new InvalidMovementException(dir);
        }
    }
    
    public void setPlanet(Planet planet){
        this.planet = planet;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.x).append(", ").append(this.y).append(", ").append(this.dir.getDir());
        return sb.toString();
    }

}
