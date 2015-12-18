package uk.co.dylanmckee.RopeCuttingProblem;

import java.util.*;

/**
 * Purpose: A Java solution to the Rope Cutting Problem, for CSC2023 Algorithm Design Assignment 2.
 * Author: Dylan McKee
 * Date: 25/11/2015
 */
public class RopeCuttingProblem {
    // A boolean to enable logging for debug purposes (to be switched off in production and in time based tests!)
    // (defaults to false for safety)
    private boolean debugMode = false;

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

    // A queue of rope orders to be cut, using the LinkedList queue implementation.
    private final Queue<Order> orders = new LinkedList<Order>();

    // A stack of pre-computed ropes from the manufacturer, so ordering time doesn't affect runtime analysis
    private final Stack<Rope> ropeSupply = new Stack<Rope>();

    // A list of ropes in-stock to cut the orders from, stored in an ArrayList.
    private final List<Rope> ropes = new ArrayList<Rope>();

    // The number of orders to generate and process, as passed to the constructor
    private final int numberOfOrders;

    // The number of coils of rope supplied from the manufacturer (i.e. transferred from the ropeSupply stack to the ropes array).
    private int numberOfCoilsOfRopeOrdered;


    /**
     * Construct an instance of the RopeCuttingProblem, instantiate fields.
     *
     * @param numberOfOrders The number of orders to be simulated in this rope cutting problem.
     */
    public RopeCuttingProblem(int numberOfOrders) {
        // Set number of orders
        this.numberOfOrders = numberOfOrders;

        // Generate ropes
        generateRopesAndOrders();

    }


    /**
     * An internal method to pre-compute random length ropes into the ropes array, so as not to count this operation in
     * the time taken to perform the actual rope cutting algorithms, making the test fairer and reducing code duplication.
     * The length of the rope is always within the bounds of ropes supplied by the manufacturer.
     *
     * This method also pre-computes some rope from the manufacturer; adding it to the ropeSupply stack.
     *
     */
    private void generateRopesAndOrders() {
        // Generate a random length for every order...
        for (int i = 0; i < numberOfOrders; i++) {
            // Randomly generate a new order length in the order bounds...
            int randomOrderLength = generateRandomInteger(MIN_ORDER_LENGTH, MAX_ORDER_LENGTH);

            // Create a new order...
            Order order = new Order();
            order.setLength(randomOrderLength);

            // Add to the order queue...
            orders.add(order);

            // For every order, pre-compute rope generation from the manufacturer and add it to the ropeSupply Stack.
            // This ensures that rope generation time does not affect the timing.
            generateRope();

        }

    }

    /**
     * An internal method to generate a new rope of random length, and add it to the rope supply stack.
     */
    private void generateRope() {
        // Instantiate the new rope object
        Rope rope = new Rope();

        // Generate a random length for the rope, within the supplier's bounds
        int length = generateRandomInteger(MIN_SUPPLIER_LENGTH, MAX_SUPPLIER_LENGTH);

        // Set the new rope's length
        rope.setLength(length);

        // Add the rope to the ropeSupply stack...
        ropeSupply.push(rope);

        // Print status to console (when debugging only!).
        if (debugMode) {
            System.out.println("Generated " + rope);
        }
    }

    /**
     * An internal method to order new ropes. This method moves rope from the ropeSupply stack (i.e. from the manufacturer)
     * into the shop's rope supply (the ropes array), for cutting.
     */
    private void orderNewRope() {
        // Increment rope order counter...
        numberOfCoilsOfRopeOrdered++;

        // If there's rope in the rope supply, use that...
        if (!ropeSupply.isEmpty()) {
            Rope ropeFromStack = ropeSupply.pop();

            // Move it into stock
            ropes.add(ropeFromStack);
        } else {
            // Generate a new rope in the ropeSupply
            generateRope();

            // And call the order method recursively to get it added to the in store rope selection without code duplication
            orderNewRope();
        }

    }


    /**
     * An internal method to generate a random integer between the specified range (using the Math.random psuedo-random
     * generation method), and return the generated integer.
     *
     * This method uses the Math.random() Java library function, which is automatically seeded by system time.
     *
     * @param min the minimum integer that the random number generator should generate
     * @param max the maximum number that the random number generator should generate
     * @return a random integer, between the min and max range specified
     */
    private int generateRandomInteger(int min, int max) {
        // Assert that max is greater than or equal to minimum...
        if (min > max) {
            // Minimum cannot be greater than maximum; throw exception.
            throw new IllegalArgumentException("Minimum cannot be greater than maximum in range!");
        }

        // Get a random double between 0.0 and 1.0...
        double randomMultiplier = Math.random();

        // Multiply the multiplier by the max minus the minimum...
        double result = randomMultiplier * (max - min);

        // Cast to an integer (adding 0.5 first to ensure it rounds up)
        int wholeResult = (int) (result + 0.5);

        // And add on the minimum to make sure it's in bounds
        wholeResult = wholeResult + min;

        // Ensure the generated number is in the bounds; if not then throw an appropriate exception
        if (wholeResult < min || wholeResult > max) {
            // Out of bounds!
            throw new IndexOutOfBoundsException("Random number generated is out of specified bounds!");
        }

        // Return integer result
        return wholeResult;

    }

    /**
     * Returns the number of coils of rope supplied by the manufacturer.
     *
     * @return an integer containing the number of coils of rope supplied by the manufacturer
     */
    public int getNumberOfCoilsOfRopeOrdered() {
        return numberOfCoilsOfRopeOrdered;
    }

    /**
     * Toggles debug logging to whatever the 'debug' parameter boolean is set to.
     *
     * @param debug a boolean to say whether this class should debug log to the console or not.
     */
    public void setDebugMode(boolean debug) {
        debugMode = debug;
    }

    /**
     * A public method to print the current rope that's in stock to the console...
     */
    public void printRopeStock() {
        System.out.println("Current rope stock:");

        for (Rope rope : ropes) {
            System.out.println(rope);

        }

    }

    /**
     * A public method to expose the first fit rope cutting algorithm.
     */
    public void performFirstFitRopeCutting() {
        // Iterate through the orders queue, performing first fit rope cutting for each one...
        while (!orders.isEmpty()) {
            Order nextOrder = orders.poll();

            // Double check...
            if (nextOrder != null) {
                // Get order length...
                int orderLength = nextOrder.getLength();

                // Perform first fit rope cutting algorithm for this order
                firstFitRopeCutting(orderLength);
            }
        }

    }

    /**
     * An implementation of the FFRCP algorithm; this algorithm cuts from the first rope that the cut fits.
     *
     * @param orderLength the length of the order to cut from the rope, as an integer, in meters.
     */
    private void firstFitRopeCutting(int orderLength) {
        // A flag to mark if the order's complete...
        boolean orderComplete = false;

        // Iterate through the array, from first rope...
        for (Rope rope : ropes) {
            // Is it long enough?
            if (rope.getLength() >= orderLength) {
                // Yes! Cut the order from this rope...
                cutLengthFromRope(rope, orderLength);

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
            orderNewRope();

            // Recursively call the rope cutting again, this time the length will get cut from the new rope...
            firstFitRopeCutting(orderLength);
        }

    }


    /**
     * A public method to expose the best fit rope cutting algorithm.
     */
    public void performBestFitRopeCutting() {
        // Iterate through the orders queue, performing best fit rope cutting for each one...
        while (!orders.isEmpty()) {
            Order nextOrder = orders.poll();

            // Double check...
            if (nextOrder != null) {
                // Get order length...
                int orderLength = nextOrder.getLength();

                // Perform first fit rope cutting algorithm for this order
                bestFitRopeCutting(orderLength);
            }
        }

    }

    /**
     * An implementation of the BFRCP algorithm; this algorithm cuts from the first rope that the cut fits.
     *
     * @param orderLength the length of the order to cut from the rope, as an integer, in meters.
     */
    private void bestFitRopeCutting(int orderLength) {
        // Sort the ropes array in ascending order, so that the rope with the least amount left on it comes first,
        // therefore we start at the rope most likely has the least sufficient free length left on it...
        Collections.sort(ropes);

        // A flag to mark if the order's complete...
        boolean orderComplete = false;

        // Iterate through the array, from shortest rope...
        for (Rope rope : ropes) {
            // Is it long enough?
            if (rope.getLength() >= orderLength) {
                // Yes! Cut the order from this rope...
                cutLengthFromRope(rope, orderLength);

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
            orderNewRope();

            // Recursively call the rope cutting again, hoping the new rope is long enough to cut from it...
            bestFitRopeCutting(orderLength);
        }

    }

    /**
     * An internal method to remove the specified length from the specified rope. If the rope falls under the threshold
     * to be kept in stock, then this method also removes it from stock after deducting the length.
     * This method helps to reduce code duplication between the two different algorithm implementations.
     *
     * @param rope the rope instance to cut the length from.
     * @param lengthToCut the length to cut from the rope, as an integer, in meters.
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
        if (debugMode) {
            System.out.printf("Cutting %dm from a rope: initial length: %dm, new length: %dm\n", lengthToCut, initialLength, newLength);
        }

        // If the new length of the rope is less than the threshold for keeping it in stock, remove it!
        if (newLength < MIN_ROPE_STOCK_LENGTH) {
            ropes.remove(rope);

            if (debugMode) {
                System.out.println("Removed (under length threshold): " + rope);
            }

        }

    }


}
