package uk.co.dylanmckee.RopeCuttingProblem;

/**
 * Purpose: An abstract data structure to represent a type of object that has a length associated with it, e.g. a rope.
 *          Length getter and setter methods are implemented in this abstract class to reduce code duplication.
 *          Comparable is also implemented, so that AbstractLengthObject derivatives can be sorted in ascending order easily.
 *          toString is marked as abstract, to ensure the classes that inherit this implement
 * Author: Dylan McKee
 * Date: 26/11/2015
 */
public abstract class AbstractLengthObject implements Comparable<AbstractLengthObject> {
    // The length of the object, in meters
    private int length;

    // Ensure classes that subclass this all implement toString by contract
    public abstract String toString();

    /**
     * A method to get the length of the object, in meters.
     *
     * @return the length of the object, in meters
     */
    public int getLength() {
        return length;
    }

    /**
     * A method to set the new length of the object - for example after cutting - in meters.
     *
     * @param length the new length of the object, in meters.
     */
    public void setLength(int length) {
        this.length = length;
    }


    /**
     * Compares this AbstractLengthObject instance with the AbstractLengthObject instance passed to it; compares by length in ascending order.
     */
    @Override
    public int compareTo(AbstractLengthObject o) {
        return length - o.length;

    }

}
