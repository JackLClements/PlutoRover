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
public class PlanetTest {
    
    public PlanetTest() {
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
     * Test of placeObstacle method, of class Planet.
     */
    @Test
    public void testPlaceObstacle() {
        System.out.println("placeObstacle");
        int x = 0;
        int y = 0;
        Planet instance = new Planet();
        instance.placeObstacle(x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isObstacle method, of class Planet.
     */
    @Test
    public void testIsObstacle() {
        System.out.println("isObstacle");
        int x = 0;
        int y = 0;
        Planet instance = new Planet();
        boolean expResult = false;
        boolean result = instance.isObstacle(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHeight method, of class Planet.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        Planet instance = new Planet();
        int expResult = 0;
        int result = instance.getHeight();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWidth method, of class Planet.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        Planet instance = new Planet();
        int expResult = 0;
        int result = instance.getWidth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
