package uk.co.dylanmckee.RopeCuttingProblem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Purpose: A simple JUnit unit test case to test the Order data structure class.
 * Author: Dylan McKee
 * Date: 25/11/2015
 */
public class OrderTest {
    /**
     * A test Order instance
     */
    private Order order;

    /**
     * Creates a test Order instance before every unit test.
     * @throws Exception if an exception is thrown by this method, the test has failed.
     */
    @Before
    public void setUp() throws Exception {
        // Create test Order instance
        order = new Order();
    }

    /**
     * Destroys the test Order instance after every unit test.
     * @throws Exception if an exception is thrown by this method, the test has failed.
     */
    @After
    public void tearDown() throws Exception {
        // Destroy test order.
        order = null;
    }

    /**
     * Tests that the length of the order can be set and retrieved.
     * @throws Exception if an exception is thrown by this method, the test has failed.
     */
    @Test
    public void testLength() throws Exception {
        // Set the length, then get the length, and check they're equal
        int testLength = 105;

        order.setLength(testLength);

        int reportedLength = order.getLength();

        // Check they're equal...
        assertEquals(testLength, reportedLength);

    }

    /**
     * Tests that an order can be compared to see if it is equal to, shorter than or longer than another order instance
     * in length.
     * @throws Exception if an exception is thrown by this method, the test has failed.
     */
    @Test
    public void testCompareTo() throws Exception {
        // Make our test order a long order...
        order.setLength(199);

        // Create a shorter test order to compare to...
        Order shortOrder = new Order();
        shortOrder.setLength(101);

        int delta = order.getLength() - shortOrder.getLength();

        // Ensure that the long order is bigger...
        assertEquals(order.compareTo(shortOrder), delta);

        // Ensure that the short order is shorter
        assertEquals(shortOrder.compareTo(order), -1 * delta);

        // Create a order the same length as the short order...
        Order anotherShortOrder = new Order();
        anotherShortOrder.setLength(shortOrder.getLength());

        // Ensure the other short order is equal...
        assertEquals(shortOrder.compareTo(anotherShortOrder), 0);

    }

    /**
     * Tests that the toString method produces a String to represent the order.
     * @throws Exception if an exception is thrown by this method, the test has failed.
     */
    @Test
    public void testToString() throws Exception {
        // Ensure that the toString method doesn't return null, and doesn't throw any exceptions.
        assertNotNull(order.toString());

    }
}