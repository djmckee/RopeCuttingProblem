package uk.co.dylanmckee.RopeCuttingProblem;

/**
 * Purpose: A simple data structure class to represent a length of rope that is comparable and therefore can be sorted.
 * Author: Dylan McKee
 * Date: 25/11/2015
 */
public class Rope extends AbstractLengthObject{

    /**
     * Return a string describing the current rope instance (including its length)
     *
     * @return a human readable String object describing the current rope instance, including its length in meters.
     */
    @Override
    public String toString() {
        return "Rope - length: " + getLength() + "m";
    }

}
