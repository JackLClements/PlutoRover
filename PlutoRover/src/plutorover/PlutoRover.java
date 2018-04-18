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

    /*
    Notes -
    - Use test driven development, think about coverage, pass/fail cases, etc.
    - Implement as efficiently as possible
     */
    /**
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
