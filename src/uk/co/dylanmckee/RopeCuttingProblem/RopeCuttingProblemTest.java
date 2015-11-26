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

    private static final int TEST_NUMBER_OF_ORDERS = 100000;

    private RopeCuttingProblem ropeCuttingProblem;

    @Before
    public void setUp() throws Exception {
        // Initialise a test instance of the RopeCuttingProblem
        this.ropeCuttingProblem = new RopeCuttingProblem(RopeCuttingProblemTest.TEST_NUMBER_OF_ORDERS);

    }

    @After
    public void tearDown() throws Exception {
        // Log number of coils ordered in this test - I'm doing it here in the @After method so that time to run this is not included in the total test time.
        System.out.println("Number of coils of extra rope ordered from manufacturer: " + this.ropeCuttingProblem.getNumberOfCoilsOfRopeOrderedAdditionally());


        // Destroy test instance
        this.ropeCuttingProblem = null;

    }

    @Test
    public void testPrintRopeStock() throws Exception {
        // Ensure it prints to console exception free...
        this.ropeCuttingProblem.printRopeStock();
    }

    @Test
    public void testPerformFirstFitRopeCutting() throws Exception {
        // Ensure test is performed exception free; also - note the time that the test takes.
        this.ropeCuttingProblem.performFirstFitRopeCutting();

    }

    @Test
    public void testPerformBestFitRopeCutting() throws Exception {
        // Ensure test is performed exception free; also - note the time that the test takes.
        this.ropeCuttingProblem.performBestFitRopeCutting();

    }
}