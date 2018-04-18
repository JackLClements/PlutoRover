/*
 */
package plutorover;

import Exceptions.InvalidMovementException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jack L. Clements
 */
public class RoverTest {
    
    private Rover rover;
    private Planet planet;
    
    /**
     * TESTS LEFT TO DO
     * - LOGIC FOR COLLISIONS
     */
    
    
    
    @Before
    public void setUp() {
        rover = new Rover();
        planet = new Planet();
        rover.setPlanet(planet);
    }
    
    @After //garbage collector should get all of these
    public void tearDown() {
    }

    /**
     * Test of process method, of class Rover.
     * Can only test for failure, success was tested via debugger
     */
    @Test(expected=InvalidMovementException.class)
    public void testProcess() throws Exception {
        System.out.println("process");
        char command = 'M';
        rover.process(command);
    }
    

    /**
     * Test of move method, of class Rover.
     */
    @Test
    public void testMove1() throws Exception {
        System.out.println("move");
        char moveCommand = 'F';
        rover.move(moveCommand);
        assertEquals(1, rover.getY());
    }
    
    /**
     * Test of move method, of class Rover.
     */
    @Test
    public void testMove2() throws Exception {
        System.out.println("move");
        char moveCommand = 'B';
        rover.move(moveCommand);
        assertEquals(9, rover.getY());
    }
    
    /**
     * Test of move method, of class Rover.
     */
    @Test(expected=InvalidMovementException.class)
    public void testMoveFail() throws Exception {
        System.out.println("move");
        char moveCommand = 'G';
        rover.move(moveCommand);
    }
    
    

    /**
     * Test of turn method, specifically self-looping behaviour
     */
    @Test
    public void testTurn1() throws Exception {
        System.out.println("turn");
        char turnCommand = 'L';
        rover.turn(turnCommand);
        rover.turn(turnCommand);
        Rover.Heading expt = Rover.Heading.SOUTH;
        assertEquals(expt, rover.getDir());
    }
    
    /**
     * Test of turn method, specifically secondary self-looping behaviour
     */
    @Test
    public void testTurn2() throws Exception {
        System.out.println("turn");
        char turnCommandA = 'L';
        char turnCommandB = 'R';
        rover.turn(turnCommandA);
        rover.turn(turnCommandA);
        rover.turn(turnCommandB);
        rover.turn(turnCommandB);
        rover.turn(turnCommandB);
        Rover.Heading expt = Rover.Heading.EAST;
        assertEquals(expt, rover.getDir());
    }
    
    /**
     * Tests a 360 loop of directions
     */
    @Test
    public void testTurn3() throws Exception {
        System.out.println("turn");
        char turnCommandA = 'L';
        rover.turn(turnCommandA);
        rover.turn(turnCommandA);
        rover.turn(turnCommandA);
        rover.turn(turnCommandA);
        Rover.Heading expt = Rover.Heading.NORTH;
        assertEquals(expt, rover.getDir());
    }
    
    /**
     * Tests a 360 loop of directions
     */
    @Test
    public void testTurn4() throws Exception {
        System.out.println("turn");
        char turnCommandA = 'R';
        rover.turn(turnCommandA);
        rover.turn(turnCommandA);
        rover.turn(turnCommandA);
        rover.turn(turnCommandA);
        Rover.Heading expt = Rover.Heading.NORTH;
        assertEquals(expt, rover.getDir());
    }
    
    /**
     * 
     * @throws Exception 
     */
    @Test(expected=InvalidMovementException.class)
    public void testTurnFail() throws Exception {
        System.out.println("turn");
        char turnCommandA = 'E';
        rover.turn(turnCommandA);
    }
    /**
     * Tests Movement System in tandem inc. looping
     */
    @Test
    public void testMovementTandem() throws Exception {
        Planet planet2 = new Planet(4, 4);
        rover.setPlanet(planet2);
        System.out.println("movement");
        char turnCommand = 'R';
        char moveCommandA = 'F';
        rover.move(moveCommandA);
        rover.move(moveCommandA);
        rover.move(moveCommandA);
        rover.move(moveCommandA);
        rover.turn(turnCommand);
        rover.turn(turnCommand);
        rover.turn(turnCommand);
        rover.turn(turnCommand);
        rover.turn(turnCommand);
        rover.move(moveCommandA);
        String expt = "1, 0, E";
        assertEquals(expt, rover.toString());
    }
    
    @Test
    public void testCollision(){
        System.out.println("collision");
        
    }
    
    /**
     * Test of setX method, of class Rover.
     */
    @Test
    public void testSetGetX() {
        System.out.println("setX");
        int x = 5;
        rover.setX(x);
        assertEquals(x, rover.getX());
    }
    
    /**
     * Test of setY method, of class Rover.
     */
    @Test
    public void testSetGetY() {
        System.out.println("setY");
        int y = 5;
        rover.setY(y);
        assertEquals(y, rover.getY());
    }

    /**
     * Test of setDir method, of class Rover.
     */
    @Test
    public void testSetGetDir_RoverHeading() {
        System.out.println("setDir/getDir (Heading)");
        Rover.Heading dir = Rover.Heading.WEST;
        rover.setDir(dir);
        assertEquals(dir, rover.getDir());
    }

    /**
     * Test of setDir method, of class Rover.
     */
    @Test
    public void testSetGetDir_char() throws Exception {
        System.out.println("setDir/getDir (char)");
        char dir = 'S';
        rover.setDir(dir);
        assertEquals(dir, rover.getDir().getDir());
    }

    /**
     * Test accessor methods for Planet, of class Rover.
     */
    @Test
    public void testSetGetPlanet() {
        System.out.println("setPlanet/getPlanet");
        Planet planet = new Planet();
        rover.setPlanet(planet);
        Planet planet2 = rover.getPlanet();
        assertEquals(planet, planet2);
    }

    /**
     * Test of toString method, of class Rover.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "0, 0, N";
        String result = rover.toString();
        assertEquals(expResult, result);
    }
    
}
