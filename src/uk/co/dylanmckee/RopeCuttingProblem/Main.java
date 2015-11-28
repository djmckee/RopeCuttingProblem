package uk.co.dylanmckee.RopeCuttingProblem;

/**
 * Purpose: A main testing class to create an instance of and run my Java solution to the Rope Cutting Problem.
 * Author: Dylan McKee
 * Date: 25/11/2015
 */
public class Main {

    // A constant number of orders, to use within the test of the
    public static final int NUMBER_OF_ORDERS = 1000;

    public static void main(String[] args) {
        // Create Rope Cutting Problem test instance
        RopeCuttingProblem ropeCuttingProblemTest = new RopeCuttingProblem(NUMBER_OF_ORDERS);

        // This is just a debug test, not a unit test that we're measuring runtime for; so enable debug logging...
        ropeCuttingProblemTest.setDebug(true);

        ropeCuttingProblemTest.performBestFitRopeCutting();

    }
}
