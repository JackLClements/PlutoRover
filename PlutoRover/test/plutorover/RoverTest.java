/*
 */
package plutorover;

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
    
    public RoverTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of move method, of class Rover.
     */
    @Test
    public void testMove() {
        System.out.println("move");
        char moveCommand = ' ';
        Rover instance = new Rover();
        instance.move(moveCommand);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of turn method, of class Rover.
     */
    @Test
    public void testTurn() {
        System.out.println("turn");
        char turnCommand = ' ';
        Rover instance = new Rover();
        instance.turn(turnCommand);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getX method, of class Rover.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        Rover instance = new Rover();
        int expResult = 0;
        int result = instance.getX();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getY method, of class Rover.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        Rover instance = new Rover();
        int expResult = 0;
        int result = instance.getY();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDir method, of class Rover.
     */
    @Test
    public void testGetDir() {
        System.out.println("getDir");
        Rover instance = new Rover();
        Rover.Heading expResult = null;
        Rover.Heading result = instance.getDir();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setX method, of class Rover.
     */
    @Test
    public void testSetX() {
        System.out.println("setX");
        int x = 0;
        Rover instance = new Rover();
        instance.setX(x);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setY method, of class Rover.
     */
    @Test
    public void testSetY() {
        System.out.println("setY");
        int y = 0;
        Rover instance = new Rover();
        instance.setY(y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDir method, of class Rover.
     */
    @Test
    public void testSetDir() {
        System.out.println("setDir");
        Rover.Heading dir = null;
        Rover instance = new Rover();
        instance.setDir(dir);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Rover.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Rover instance = new Rover();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
