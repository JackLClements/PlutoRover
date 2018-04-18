/*
 */
package plutorover;

import Exceptions.InvalidHeaderException;
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

    private int x, y; //row and column
    private Heading dir;
    private Planet planet; //relational link to planet

    //Co-ordinates in this space are column, row (x, y)

    /**
     * Rather than split logic out, a heading is defined here.
     * This has multiple benefits, first all logic related to turning and the Rover headings are encapsulated.
     * Second, this makes the no. of headings easily expandable, so e.g. North-East can be added as NORTHEAST('Q', 1, 1) etc.
     */
    public static enum Heading {
        NORTH('N', 0, 1), EAST('E', 1, 0), SOUTH('S', 0, -1), WEST('W', -1, 0); //ordering of elements is important
        final char headingAngle;
        final int forwardX, forwardY;
        private static Heading[] headings = values();
        
        /**
         * Value based constructor, only ever used by the program.
         * @param headingAngle char representation of heading
         * @param forwardX number of X-axis grid spaces moving forward at this heading
         * @param forwardY number of Y-axis grid spaces moving forward at this heading
         */
        Heading(char headingAngle, int forwardX, int forwardY) {
            this.headingAngle = headingAngle;
            this.forwardX = forwardX;
            this.forwardY = forwardY;
        }
        
        /**
         * Returns the char representation of the heading
         * @return char representation of heading
         */
        public char getDir() {
            return headingAngle;
        }
        
        /**
         * Returns the next left angle at 90 degree angle
         * @return new Heading enum value
         */
        public Heading left() {
            int pos = (this.ordinal() - 1) % headings.length; //this step is always going to subtract
            return pos < 0 ? headings[pos + headings.length] : headings[pos]; //thus we make it positive in the if-else statement
        }
        
        /**
         * Returns the next right direction at 90 degree angle
         * @return new Heading enum value
         */
        public Heading right() {
            return headings[(this.ordinal() + 1) % headings.length]; //if overlapping, modulo back into bounds, else leave
        }
        
        /**
         * 
         * @return 
         */
        public int getForwardX() {
            return this.forwardX;
        }
        
        /**
         * 
         * @return 
         */
        public int getForwardY() {
            return this.forwardY;
        }

    }; //note - this logic can be seperated out into the parent class with dir. stored as a char, but this encapsulates it all neatly
    
    /**
     * Default constructor, intitialises to default values.
     */
    public Rover() {
        this.x = 0;
        this.y = 0;
        this.dir = Heading.NORTH;
    }
    
    /**
     * Value based constructor, works on column-row (x, y) co-ordinates system.
     * @param x Column location of object
     * @param y Row location of object
     * @param dir Heading of object
     */
    public Rover(int x, int y, Heading dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    //Mutator Methods
    
    /**
     * 
     * @param command
     * @throws InvalidMovementException 
     */
    public void process(char command) throws InvalidMovementException {
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

    /**
     * Processes a string of commands, in order.
     * @param commands commands to execute
     * @throws InvalidMovementException 
     */
    public void process(String commands) throws InvalidMovementException {
        for (int i = 0; i < commands.length(); i++) {
            process(commands.charAt(i));
        }
    }

    /**
     * Moves the rover based on a single command
     * Collision checking and wrapping of boundaries is also done in this method
     * @param moveCommand direction to move in, forward or backwards
     * @throws InvalidMovementException if movement does not match turn or move syntax
     */
    public void move(char moveCommand) throws InvalidMovementException {
        int newX, newY;
        switch (moveCommand) {
            case 'F':
                newX = (this.x + dir.getForwardX()) % planet.getWidth();
                newY = (this.y + dir.getForwardY()) % planet.getHeight(); //modular arithmatic wraps bounds
                
                //The following could be spun off into seperate methods, but have been left here due to clarity
                
                if (newX < 0) {
                    newX += planet.getWidth(); //checks for negative values to wrap bounds, due to how Java handles negative %
                }
                if (newY < 0) {
                    newY += planet.getHeight();
                }

                if (!planet.isObstacle(newX, newY)) { //checks collisions
                    this.x = newX;
                    this.y = newY;
                }

                break;
            case 'B':
                newX = (this.x - dir.getForwardX()) % planet.getWidth();
                newY = (this.y - dir.getForwardY()) % planet.getHeight(); 
                
                
                if (newX < 0) {
                    newX += planet.getWidth();
                }
                if (newY < 0) {
                    newY += planet.getHeight();
                }
                if (!planet.isObstacle(newX, newY)) {
                    this.x = newX;
                    this.y = newY;
                }

                break;
            default:
                throw new InvalidMovementException(moveCommand);
        }
    }
    
    /**
     * Turns the rover left or right, dependent on input
     * @param turnCommand
     * @throws InvalidMovementException if movement does not match turn or move syntax
     */
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
     * @return
     */
    public int getX() {
        return this.x;
    }

    /**
     * Return's Rover's y-value
     * @return
     */
    public int getY() {
        return this.y;
    }

    /**
     * Returns Rover's heading as a Heading value
     * @return
     */
    public Heading getDir() {
        return this.dir;
    }
    
    /**
     * Returns reference to instance's Planet field
     * @return 
     */
    public Planet getPlanet() {
        return this.planet;
    }

    /**
     * Sets Rover's x-value
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets Rover's y-value
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Sets Rover's heading as a Heading object
     * @param dir
     */
    public void setDir(Heading dir) {
        this.dir = dir;
    }
    
    /**
     * Sets direction field based on given character
     * @param dir
     * @throws InvalidMovementException when given value outside of
     */
    public void setDir(char dir) throws InvalidHeaderException {
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
                throw new InvalidHeaderException(dir);
        }
    }
    
    /**
     * Sets Planet field
     * @param planet 
     */
    public void setPlanet(Planet planet) {
        this.planet = planet;
    }
    
    /**
     * Returns value of object as easily readable string
     * also allows for unit testing of object instances without needing to check w/ tuples
     * @return 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.x).append(", ").append(this.y).append(", ").append(this.dir.getDir());
        return sb.toString();
    }

}
