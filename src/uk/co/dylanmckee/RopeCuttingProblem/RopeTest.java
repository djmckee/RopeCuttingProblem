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

    @Before
    public void setUp() throws Exception {
        // Create test Rope instance
        this.rope = new Rope();
    }

    @After
    public void tearDown() throws Exception {
        // Destroy test rope.
        this.rope = null;
    }

    @Test
    public void testLength() throws Exception {
        // Set the length, then get the length, and check they're equal
        int testLength = 105;

        this.rope.setLength(testLength);

        int reportedLength = this.rope.getLength();

        // Check they're equal...
        assertEquals(testLength, reportedLength);

    }


    @Test
    public void testCompareTo() throws Exception {
        // Make our test rope a long rope...
        this.rope.setLength(199);

        // Create a shorter test rope to compare to...
        Rope shortRope = new Rope();
        shortRope.setLength(101);

        int delta = this.rope.getLength() - shortRope.getLength();

        // Ensure that the long rope is bigger...
        assertEquals(this.rope.compareTo(shortRope), delta);

        // Ensure that the short rope is shorter
        assertEquals(shortRope.compareTo(this.rope), -1 * delta);

        // Create a rope the same length as the short rope...
        Rope anotherShortRope = new Rope();
        anotherShortRope.setLength(shortRope.getLength());

        // Ensure the other short rope is equal...
        assertEquals(shortRope.compareTo(anotherShortRope), 0);

    }

    @Test
    public void testToString() throws Exception {
        // Ensure that the toString method doesn't return null, and doesn't throw any exceptions.
        assertNotNull(this.rope.toString());

    }
}