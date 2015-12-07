package uk.co.dylanmckee.RopeCuttingProblem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Purpose: A simple JUnit unit test case to test the Rope data structure class.
 * Author: Dylan McKee
 * Date: 25/11/2015
 */
public class RopeTest {
    // A test Rope instance
    private Rope rope;

    // Constants for different rope lengths
    private static final int SHORT_ROPE_LENGTH = 101;

    // The long rope MUST be longer than the short rope...
    private static final int LONG_ROPE_LENGTH = 199;

    @Before
    public void setUp() throws Exception {
        // Create test Rope instance
        rope = new Rope();
    }

    @After
    public void tearDown() throws Exception {
        // Destroy test rope.
        rope = null;
    }

    @Test
    public void testLength() throws Exception {
        // Set the length, then get the length, and check they're equal
        final int testLength = SHORT_ROPE_LENGTH;

        rope.setLength(testLength);

        int reportedLength = rope.getLength();

        // Check they're equal...
        assertEquals(testLength, reportedLength);

    }


    @Test
    public void testCompareTo() throws Exception {
        // Make our test rope a long rope...
        rope.setLength(LONG_ROPE_LENGTH);

        // Create a shorter test rope to compare to...
        Rope shortRope = new Rope();
        shortRope.setLength(SHORT_ROPE_LENGTH);

        int delta = rope.getLength() - shortRope.getLength();

        // Ensure that the long rope is bigger...
        assertEquals(rope.compareTo(shortRope), delta);

        // Ensure that the short rope is shorter
        final int invertedDelta = (-1 * delta);
        assertEquals(shortRope.compareTo(rope), invertedDelta);

        // Create a rope the same length as the short rope...
        Rope anotherShortRope = new Rope();
        anotherShortRope.setLength(SHORT_ROPE_LENGTH);

        // Ensure the other short rope is equal...
        assertEquals(shortRope.compareTo(anotherShortRope), 0);

    }

    @Test
    public void testToString() throws Exception {
        // Ensure that the toString method doesn't return null, and doesn't throw any exceptions.
        assertNotNull(rope.toString());

    }
}