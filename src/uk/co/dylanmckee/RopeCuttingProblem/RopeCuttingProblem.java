package uk.co.dylanmckee.RopeCuttingProblem;

import java.util.ArrayList;

/**
 * Purpose: A Java solution to the Rope Cutting Problem, for CSC2023 Algorithm Design Assignment 2.
 * Author: Dylan McKee
 * Date: 25/11/2015
 *
 */
public class RopeCuttingProblem {
    // The minimum length of the rope from suppliers, in meters
    private static final int MIN_SUPPLIER_LENGTH = 100;

    // The maximum length of the rope from suppliers, in meters
    private static final int MAX_SUPPLIER_LENGTH = 200;

    // The minimum length a customer's rope order can be, in meters.
    private static final int MIN_ORDER_LENGTH = 100;

    // The maximum length a customer's rope order can be, in meters.
    private static final int MAX_ORDER_LENGTH = 200;

    // A list of ropes.
    private ArrayList<Rope> ropes = new ArrayList<Rope>();

    // The number of orders, as passed to the constructor
    private int numberOfOrders;


    /**
     * Construct an instance of the RopeCuttingProblem, instantiate fields.
     * @param numberOfOrders The number of orders to be simulated in this rope cutting problem.
     */
    public RopeCuttingProblem(int numberOfOrders) {
        // Set number of orders
        this.numberOfOrders = numberOfOrders;

        // Generate ropes
        generateRopes();

    }

    /**
     * An internal method to pre-compute random length ropes into the ropes array, so as not to count this operation in
     * the time taken to perform the actual rope cutting algorithms, making the test fairer & reducing code duplication.
     */
    private void generateRopes() {
        // Generate a rope for every order...
        for (int i = 0; i < numberOfOrders; i++) {
            // Instantiate the new rope object
            Rope rope = new Rope();

            // Generate a random length for the rope, within the supplier's bounds
            int length = generateRandomInteger(MIN_SUPPLIER_LENGTH, MAX_SUPPLIER_LENGTH);

            // Set the new rope's length
            rope.setLength(length);

            // Add the rope to the ropes list...
            ropes.add(rope);

            // Print to console
            System.out.println("Generated " + rope.toString());

        }

    }

    /**
     * An internal method to generate a random integer between the specified range (using the Math.random psuedorandom
     * generation method), and return the generated integer.
     */
    private int generateRandomInteger(int min, int max) {
        // Get a random double between 0.0 and 1.0...
        double randomMultiplier = Math.random();

        // Multiply the multiplier by the max minus the minimum...
        double result = (randomMultiplier * (max - min));

        // Cast to an integer (adding 0.5 first to ensure it rounds up)
        int wholeResult = (int)(result + 0.5);

        // And add on the minimum to make sure it's in bounds
        wholeResult = wholeResult + min;

        // Return integer result
        return wholeResult;

    }


}
