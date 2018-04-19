/*
 */
package plutorover;

import Exceptions.InvalidMovementException;
import java.util.Scanner;

/**
 *
 * @author Jack L. Clements
 */
public class PlutoRover {
    /**
     * Main class literally only used to test scenarios that Unit Testing could not catch
     * Mostly black box tests
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Rover rover = new Rover();
        Planet planet = new Planet();
        rover.setPlanet(planet);
        planet.placeObstacle(4, 4);
        String input = "";
        while (!input.equals("E")) {
            try {
                input = scan.next("[A-Z]{1}");
                rover.process(input.charAt(0));
                System.out.println(rover.toString());
            } catch (Exception ex) { //catch exceptions better than this
                System.out.println(ex.getMessage());
                scan.reset();
                scan.next();
            }
        }
    }

}
