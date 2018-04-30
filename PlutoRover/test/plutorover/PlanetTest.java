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
    private Planet planet;
    
    @Before
    public void setUp() {
        planet = new Planet();
    }
    
    @After
    public void tearDown() { 
    }

    /**
     * Test placement and checking of obstacle methods, of class Planet.
     */
    @Test
    public void testObstacles() {
        System.out.println("placeObstacle");
        int x = 2;
        int y = 3;
        planet.placeObstacle(x, y);
        assertTrue(planet.isObstacle(2, 3));
    }

    /**
     * Test of getHeight method, of class Planet.
     */
    @Test
    public void testSetGetHeight() {
        System.out.println("height");
        planet.setHeight(15);
        int result = planet.getHeight();
        assertEquals(15, result);
    }

    /**
     * Test of getWidth method, of class Planet.
     */
    @Test
    public void testSetGetWidth() {
        System.out.println("getWidth");
        planet.setWidth(102);
        int result = planet.getWidth();
        assertEquals(102, result);
    }
    
    /**
     * Test of setWidth method, checks that array is properly resized
     */
    @Test
    public void testGetObstacles(){
        System.out.println("getObstacles");
        planet.setWidth(98);
        planet.setHeight(8);
        boolean [][] planetArray = planet.getObstacles();
        assertEquals(8, planetArray.length); //is this bad practice? 
        assertEquals(98, planetArray[0].length);
        
    }
    
    
}
