/*
 */
package plutorover;

import Exceptions.InvalidHeaderException;
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
    public void testProcess1() throws InvalidMovementException {
        System.out.println("process (individual)");
        char command = 'M';
        rover.process(command);
    }
    
    /**
     * Test of process method, of class Rover.
     * Can only test for failure, success was tested via debugger
     */
    @Test(expected=InvalidMovementException.class)
    public void testProcess2() throws InvalidMovementException {
        System.out.println("process (string/chain)");
        String commands = "FBFLLRFO";
        rover.process(commands);
    }
    

    /**
     * Test of move method, of class Rover.
     */
    @Test
    public void testMove1() throws InvalidMovementException {
        System.out.println("move");
        char moveCommand = 'F';
        rover.move(moveCommand);
        assertEquals(1, rover.getY());
    }
    
    /**
     * Test of move method, of class Rover.
     */
    @Test
    public void testMove2() throws InvalidMovementException {
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
    public void testTurn1() throws InvalidMovementException {
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
    public void testTurn2() throws InvalidMovementException {
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
    public void testTurn3() throws InvalidMovementException {
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
    public void testTurn4() throws InvalidMovementException {
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
    public void testTurnFail() throws InvalidMovementException {
        System.out.println("turn");
        char turnCommandA = 'E';
        rover.turn(turnCommandA);
    }
    /**
     * Tests Movement System in tandem inc. looping
     */
    @Test
    public void testMovementTandem() throws InvalidMovementException {
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
    
    /**
     * Test collision system on a basic collision on a small size grid
     */
    @Test
    public void testCollision() throws InvalidMovementException{
        System.out.println("collision (small)");
        planet.placeObstacle(2, 2);
        char moveCommand = 'F';
        char turnCommand = 'R';
        rover.move(moveCommand);
        rover.move(moveCommand);
        rover.turn(turnCommand);
        rover.move(moveCommand);
        rover.move(moveCommand); //hits object
        assertEquals("1, 2, E", rover.toString());
    }
    
    /**
     * Test collision system on a series of commands on a larger size grid
     */
    @Test
    public void testCollision2() throws InvalidMovementException{
        System.out.println("collision (large)");
        Planet planet2 = new Planet(100, 100);
        planet2.placeObstacle(30, 45);
        rover.setPlanet(planet2);
   
        rover.turn('L');
        for(int i = 0; i < 70; i++){
            rover.move('F');
        }
        rover.turn('L');
        for(int i = 0; i < 55; i++){
            rover.move('F');
        }
        assertEquals("30, 46, S", rover.toString());
    }
    
    /**
     * Test collision system on a String of input movements
     * @throws InvalidMovementException 
     */
    @Test
    public void testCollisionStr() throws InvalidMovementException{
        System.out.println("collisions (string)");
        planet.placeObstacle(2, 2);
        String moves = "FFRFF";
        rover.process(moves);
        assertEquals("1, 2, E", rover.toString());
    }
    
    /**
     * Test that a Rover object cannot spawn in negative co-ordinates on a planet
     */
    @Test
    public void testInitialWrappingTest(){
        System.out.println("initial wrapping");
        Planet planet2 = new Planet();
        Rover init = new Rover(-5, -3, Rover.Heading.NORTH, planet2);
        assertEquals("5, 7, N", init.toString());
    }
    
    /**
     * Test that a Rover object cannot spawn in too high co-ordinates on a planet
     */
    @Test
    public void testInitialWrappingTest2(){
        System.out.println("initial wrapping");
        Planet planet2 = new Planet();
        Rover init = new Rover(11, 15, Rover.Heading.NORTH, planet2);
        assertEquals("1, 5, N", init.toString());
    }
    
    //Accessor tests
    
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
    public void testSetGetDir_char() throws InvalidHeaderException {
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
