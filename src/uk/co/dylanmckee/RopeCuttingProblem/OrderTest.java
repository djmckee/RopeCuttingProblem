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
    // A test Order instance
    private Order order;

    @Before
    public void setUp() throws Exception {
        // Create test Order instance
        order = new Order();
    }

    @After
    public void tearDown() throws Exception {
        // Destroy test order.
        order = null;
    }

    @Test
    public void testLength() throws Exception {
        // Set the length, then get the length, and check they're equal
        int testLength = 105;

        order.setLength(testLength);

        int reportedLength = order.getLength();

        // Check they're equal...
        assertEquals(testLength, reportedLength);

    }


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

    @Test
    public void testToString() throws Exception {
        // Ensure that the toString method doesn't return null, and doesn't throw any exceptions.
        assertNotNull(order.toString());

    }
}