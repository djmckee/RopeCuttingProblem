package uk.co.dylanmckee.RopeCuttingProblem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Purpose: A Java solution to the Rope Cutting Problem, for CSC2023 Algorithm Design Assignment 2.
 * Author: Dylan McKee
 * Date: 25/11/2015
 */
public class RopeCuttingProblem {
    // A boolean to enable logging for debug purposes (to be switched off in production and in time based tests!)
    // (defaults to false for safety)
    private boolean DEBUG = false;

    // The minimum length of the rope from suppliers, in meters
    private static final int MIN_SUPPLIER_LENGTH = 100;

    // The maximum length of the rope from suppliers, in meters
    private static final int MAX_SUPPLIER_LENGTH = 200;

    // The minimum length a customer's rope order can be, in meters.
    private static final int MIN_ORDER_LENGTH = 1;

    // The maximum length a customer's rope order can be, in meters.
    private static final int MAX_ORDER_LENGTH = 100;

    // The minimum length, in meters, that a rope can be to be allowed in stock.
    // If the length is below this threshold, it's removed from our stock.
    private static final int MIN_ROPE_STOCK_LENGTH = 5;

    // A queue of orders, using the LinkedList queue implementation.
    private final Queue<Order> orders = new LinkedList<Order>();

    // A list of ropes, stored in an ArrayList.
    private final ArrayList<Rope> ropes = new ArrayList<Rope>();

    // The number of orders, as passed to the constructor
    private final int numberOfOrders;

    // The number of coils of rope supplied from the manufacturer in addition to those pre-computed.
    private int numberOfCoilsOfRopeOrderedAdditionally;


    /**
     * Construct an instance of the RopeCuttingProblem, instantiate fields.
     *
     * @param numberOfOrders The number of orders to be simulated in this rope cutting problem.
     */
    public RopeCuttingProblem(int numberOfOrders) {
        // Set number of orders
        this.numberOfOrders = numberOfOrders;

        // Generate ropes
        this.generateRopesAndOrders();

    }

    /**
     * A public method to print the current rope that's in stock to the console...
     */
    public void printRopeStock() {
        System.out.println("Current rope stock:");

        for (Rope rope : this.ropes) {
            System.out.println(rope);

        }

    }

    /**
     * A public method to expose the first fit rope cutting algorithm.
     */
    public void performFirstFitRopeCutting() {
        // Iterate through the orders queue, performing first fit rope cutting for each one...
        while (!this.orders.isEmpty()) {
            Order nextOrder = this.orders.poll();

            // Double check...
            if (nextOrder != null) {
                // Get order length...
                int orderLength = nextOrder.getLength();

                // Perform first fit rope cutting algorithm for this order
                this.firstFitRopeCutting(orderLength);
            }
        }

    }

    /**
     * An implementation of the FFRCP algorithm; this algorithm cuts from the first rope that the cut fits.
     */
    private void firstFitRopeCutting(int orderLength) {
        // A flag to mark if the order's complete...
        boolean orderComplete = false;

        // Iterate through the array, from first rope...
        for (int i = 0; i < this.ropes.size(); i++) {
            // Get the rope being iterated...
            Rope rope = this.ropes.get(i);

            // Is it long enough?
            if (rope.getLength() >= orderLength) {
                // Yes! Cut the order from this rope...
                this.cutLengthFromRope(rope, orderLength);

                // Order complete!
                orderComplete = true;

                // Now that we've successfully done the cutting order, stop looping.
                break;
            }

            // If the rope isn't long enough, continue on to the next one

        }

        // Is the order complete?
        if (!orderComplete) {
            // Order not completed because we have no suitable ropes, order a new rope, then try again.
            this.orderNewRope();

            this.numberOfCoilsOfRopeOrderedAdditionally++;

            // Recursively call the rope cutting again, hoping the new rope is long enough to cut from it...
            this.firstFitRopeCutting(orderLength);
        }

    }


    /**
     * A public method to expose the best fit rope cutting algorithm.
     */
    public void performBestFitRopeCutting() {
        // Iterate through the orders queue, performing best fit rope cutting for each one...
        while (!this.orders.isEmpty()) {
            Order nextOrder = this.orders.poll();

            // Double check...
            if (nextOrder != null) {
                // Get order length...
                int orderLength = nextOrder.getLength();

                // Perform first fit rope cutting algorithm for this order
                this.bestFitRopeCutting(orderLength);
            }
        }

    }

    /**
     * An implementation of the BFRCP algorithm; this algorithm cuts from the first rope that the cut fits.
     */
    private void bestFitRopeCutting(int orderLength) {
        // Sort the ropes array in ascending order, so that the rope with the least amount left on it comes first,
        // therefore we start at the rope most likely has the least sufficient free length left on it...
        Collections.sort(this.ropes);

        // A flag to mark if the order's complete...
        boolean orderComplete = false;

        // Iterate through the array, from shortest rope...
        for (int i = 0; i < this.ropes.size(); i++) {
            // Get the rope being iterated...
            Rope rope = this.ropes.get(i);

            // Is it long enough?
            if (rope.getLength() >= orderLength) {
                // Yes! Cut the order from this rope...
                this.cutLengthFromRope(rope, orderLength);

                // Order complete!
                orderComplete = true;

                // Now that we've successfully done the cutting order, stop looping.
                break;
            }

            // If the rope isn't long enough, continue on to the next one

        }

        // Is the order complete?
        if (!orderComplete) {
            // Order not completed because we have no suitable ropes, order a new rope, then try again.
            this.orderNewRope();

            this.numberOfCoilsOfRopeOrderedAdditionally++;

            // Recursively call the rope cutting again, hoping the new rope is long enough to cut from it...
            this.bestFitRopeCutting(orderLength);
        }

    }

    /**
     * An internal method to pre-compute random length ropes into the ropes array, so as not to count this operation in
     * the time taken to perform the actual rope cutting algorithms, making the test fairer & reducing code duplication.
     * The length of the rope is always within the bounds of ropes supplied by the manufacturer.
     * <p>
     * This method also pre-computes a random length for every order, and fills the orders queue.
     */
    private void generateRopesAndOrders() {
        // Generate a random length for every order...
        for (int i = 0; i < this.numberOfOrders; i++) {
            // Randomly generate a new order length in the order bounds...
            int randomOrderLength = this.generateRandomInteger(MIN_ORDER_LENGTH, MAX_ORDER_LENGTH);

            // Create a new order...
            Order order = new Order();
            order.setLength(randomOrderLength);

            // Add to the order queue...
            this.orders.add(order);

        }

        // Generate a rope for every order...
        for (int i = 0; i < this.numberOfOrders; i++) {
            this.orderNewRope();

        }

    }

    /**
     * An internal method to order new ropes. This can be called when precomputing the ropes for some orders, or, if
     * there's no suitable ropes in stock, this method can be called to have a new rope instantly delivered from the
     * manufacturer and added to the ropes array.
     */
    private void orderNewRope() {
        // Instantiate the new rope object
        Rope rope = new Rope();

        // Generate a random length for the rope, within the supplier's bounds
        int length = this.generateRandomInteger(MIN_SUPPLIER_LENGTH, MAX_SUPPLIER_LENGTH);

        // Set the new rope's length
        rope.setLength(length);

        // Add the rope to the ropes list...
        this.ropes.add(rope);

        // Print status to console (when debugging only!).
        if (DEBUG) {
            System.out.println("Generated " + rope.toString());
        }

    }

    /**
     * An internal method to remove the specified length from the specified rope. If the rope falls under the threshold
     * to be kept in stock, then this method also removes it from stock after deducting the length.
     * This method helps to reduce code duplication between the two different algorithm implementations.
     */
    private void cutLengthFromRope(Rope rope, int lengthToCut) {
        // Do some basic bounds checking...
        if (rope.getLength() < lengthToCut) {
            // Fail - this is impossible!
            throw new IllegalArgumentException("The length to cut is greater than the length of the rope.");

        }

        // Remove the length from the rope...
        int initialLength = rope.getLength();
        int newLength = initialLength - lengthToCut;
        rope.setLength(newLength);


        // Print status to console (when debugging only!).
        if (DEBUG) {
            System.out.printf("Cutting %dm from a rope: initial length: %dm, new length: %dm\n", lengthToCut, initialLength, newLength);
        }

        // If the new length of the rope is less than the threshold for keeping it in stock, remove it!
        if (newLength < MIN_ROPE_STOCK_LENGTH) {
            this.ropes.remove(rope);

            if (DEBUG) {
                System.out.println("Removed (under length threshold): " + rope.toString());
            }

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
        double result = randomMultiplier * (max - min);

        // Cast to an integer (adding 0.5 first to ensure it rounds up)
        int wholeResult = (int) (result + 0.5);

        // And add on the minimum to make sure it's in bounds
        wholeResult = wholeResult + min;

        // Return integer result
        return wholeResult;

    }

    /**
     * Returns the number of coils of rope supplied by the manufacturer.
     *
     * @return an integer containing the number of coils of rope supplied by the manufacturer
     */
    public int getNumberOfCoilsOfRopeOrderedAdditionally() {
        return this.numberOfCoilsOfRopeOrderedAdditionally;
    }

    /**
     * Toggles debug logging to whatever the 'debug' parameter boolean is set to.
     */
    public void setDebug(boolean debug) {
        DEBUG = debug;
    }

}
