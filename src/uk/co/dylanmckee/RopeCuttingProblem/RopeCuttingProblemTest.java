package uk.co.dylanmckee.RopeCuttingProblem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Purpose: A simple JUnit unit test case to test that the RopeCuttingProblem runs without throwing exceptions.
 * Author: Dylan McKee
 * Date: 25/11/2015
 */
public class RopeCuttingProblemTest {

    /**
     * The number of orders to generate in these tests.
     */
    private static final int TEST_NUMBER_OF_ORDERS = 90000;

    /**
     * A test instance of the rope cutting problem.
     */
    private RopeCuttingProblem ropeCuttingProblem;

    /**
     * Creates a test Rope Cutting Problem instance before every unit test.
     * @throws Exception if an exception is thrown by this method, the test has failed.
     */
    @Before
    public void setUp() throws Exception {
        // Initialise a test instance of the RopeCuttingProblem
        ropeCuttingProblem = new RopeCuttingProblem(TEST_NUMBER_OF_ORDERS);

        // Ensure DEBUG is OFF for these unit tests - we're using them to measure time taken and printf() is a very slow function.
        ropeCuttingProblem.setDebugMode(false);

    }

    /**
     * Destroys the test Rope Cutting Problem instance after every unit test.
     * @throws Exception if an exception is thrown by this method, the test has failed.
     */
    @After
    public void tearDown() throws Exception {
        // Log number of coils ordered in this test - I'm doing it here in the @After method so that time to run this is not included in the total test time.
        System.out.println("Number of coils of extra rope ordered from manufacturer: " + ropeCuttingProblem.getNumberOfCoilsOfRopeOrdered());


        // Destroy test instance
        ropeCuttingProblem = null;

    }

    /**
     * Ensures that the rope stock can be printed to the console without error.
     * @throws Exception if an exception is thrown by this method, the test has failed.
     */
    @Test
    public void testPrintRopeStock() throws Exception {
        // Ensure it prints to console exception free...
        ropeCuttingProblem.printRopeStock();
    }

    /**
     * Ensures that first fit rope cutting can be performed without an error occurring;
     * also provides ideal opportunity to time how long the algorithm takes.
     * @throws Exception if an exception is thrown by this method, the test has failed.
     */
    @Test
    public void testPerformFirstFitRopeCutting() throws Exception {
        // Ensure test is performed exception free; also - note the time that the test takes.
        ropeCuttingProblem.performFirstFitRopeCutting();

    }

    /**
     * Ensures that best fit rope cutting can be performed without an error occurring;
     * also provides ideal opportunity to time how long the algorithm takes.
     * @throws Exception if an exception is thrown by this method, the test has failed.
     */
    @Test
    public void testPerformBestFitRopeCutting() throws Exception {
        // Ensure test is performed exception free; also - note the time that the test takes.
        ropeCuttingProblem.performBestFitRopeCutting();

    }
}