package org.erni.facebookprep.level1;

/**
 * There's a stack of NN inflatable discs, with the ith disc from the top having an initial radius of R_i inches.
 *
 * The stack is considered unstable if it includes at least one disc whose radius is larger than or equal
 * to that of the disc directly under it. In other words, for the stack to be stable, each disc must have a strictly
 * smaller radius than that of the disc directly under it.
 *
 * As long as the stack is unstable, you can repeatedly choose any disc of your choice and deflate it down
 * to have a radius of your choice which is strictly smaller than the disc’s prior radius.
 * The new radius must be a positive integer number of inches.
 *
 * Determine the minimum number of discs which need to be deflated in order to make the stack stable,
 * if this is possible at all. If it is impossible to stabilize the stack, return -1 instead.
 */
public class StackStabilization {

    public static int getMinimumDeflatedDiscCount(int N, int[] R) {
        int deflated = 0;

        // if bottom disc radius is too small
        if (R[R.length-1] < R.length) return -1;

        // else
        for (int i = R.length - 2; i > -1; i--) { // starting from the second to last (from the bottom)
            if(R[i] < i + 1) return -1; // if it´s too small to be deflated

            if(R[i] >= R[i+1]) { // if it´s larger or equal than the next, we have to deflate it
                R[i] = R[i+1] - 1; // deflated one inch less than the next
                deflated++;
            }
        }

        return deflated;
    }

    public static void main(String[] args) {
        System.out.println(getMinimumDeflatedDiscCount(5, new int[]{2,5,3,6,5}));
    }

}
