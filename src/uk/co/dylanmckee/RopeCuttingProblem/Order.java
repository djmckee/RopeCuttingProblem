package uk.co.dylanmckee.RopeCuttingProblem;

/**
 * Purpose: A simple data structure class to represent an order for a length of rope.
 * Author: Dylan McKee
 * Date: 26/11/2015
 */
public class Order extends AbstractLengthObject {

    /**
     * Return a string describing the current order instance (including its length)
     *
     * @return a human readable String object describing the current order instance, including its length in meters.
     */
    public String toString() {
        return "Order - length: " + getLength() + "m";
    }

}
