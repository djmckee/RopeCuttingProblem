package uk.co.dylanmckee.RopeCuttingProblem;

/**
 * Purpose: A simple data structure class to represent a length of rope that is comparable and therefore can be sorted.
 * Author: Dylan McKee
 * Date: 25/11/2015
 */
public class Rope implements Comparable<Rope> {
    // The length of the rope, in meters
    private int length;

    /**
     * A method to get the length of the rope, in meters.
     *
     * @return the length of the rope, in meters
     */
    public int getLength() {
        return this.length;
    }

    /**
     * A method to set the new length of the rope - for example after cutting - in meters.
     *
     * @param length the new length of the rope, in meters.
     */
    public void setLength(int length) {
        this.length = length;
    }


    /**
     * Compares this Rope instance with the Rope instance passed to it; compares by length in ascending order.
     */
    @Override
    public int compareTo(Rope o) {
        return (this.length - o.length);

    }

    /**
     * Return a string describing the current rope instance (including its length)
     *
     * @return a human readable String object describing the current rope instance, including its length in meters.
     */
    @Override
    public String toString() {
        return "Rope - length: " + length + "m";
    }

}
