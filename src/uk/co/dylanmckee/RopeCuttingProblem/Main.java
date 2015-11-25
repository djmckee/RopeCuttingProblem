package uk.co.dylanmckee.RopeCuttingProblem;

/**
 * Purpose: A main testing class to create an instance of and run my Java solution to the Rope Cutting Problem.
 * Author: Dylan McKee
 * Date: 25/11/2015
 */
public class Main {

    // A constant number of orders, to use within the test of the RopeCuttingProblem.
    public final static int NUMBER_OF_ORDERS = 100000;

    public static void main(String[] args) {
        // Create Rope Cutting Problem test instance
        RopeCuttingProblem ropeCuttingProblemTest = new RopeCuttingProblem(NUMBER_OF_ORDERS);

        ropeCuttingProblemTest.performBestFitRopeCutting();

    }
}
