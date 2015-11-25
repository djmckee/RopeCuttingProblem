package uk.co.dylanmckee.RopeCuttingProblem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Purpose: A simple JUnit unit test case to test that the RopeCuttingProblem runs without throwing exceptions.
 * Author: Dylan McKee
 * Date: 25/11/2015
 *
 */
public class RopeCuttingProblemTest {

    private final static int TEST_NUMBER_OF_ORDERS = 100000;

    private RopeCuttingProblem ropeCuttingProblem;

    @Before
    public void setUp() throws Exception {
        // Initialise a test instance of the RopeCuttingProblem
        ropeCuttingProblem = new RopeCuttingProblem(TEST_NUMBER_OF_ORDERS);

    }

    @After
    public void tearDown() throws Exception {
        // Destroy test instance
        ropeCuttingProblem = null;

    }

    @Test
    public void testPrintRopeStock() throws Exception {
        // Ensure it prints to console exception free...
        ropeCuttingProblem.printRopeStock();
    }

    @Test
    public void testPerformFirstFitRopeCutting() throws Exception {
        // Ensure test is performed exception free; also - note the time that the test takes.
        ropeCuttingProblem.performFirstFitRopeCutting();
    }

    @Test
    public void testPerformBestFitRopeCutting() throws Exception {
        // Ensure test is performed exception free; also - note the time that the test takes.
        ropeCuttingProblem.performBestFitRopeCutting();
    }
}