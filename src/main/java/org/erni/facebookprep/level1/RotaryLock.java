package org.erni.facebookprep.level1;

public class RotaryLock {

    /**
     * Going to any digit costs 1 second per digit from the start position (1)
     *
     * @param N number of digits in the round lock (going from N to 1 costs 1 second as well)
     * @param M size of array C
     * @param C is the array of codes to be set in order
     * @return
     */
    public static long getMinCodeEntryTime(int N, int M, int[] C) {
        long pointer = 1L; // initially pointing at first position
        long totalSeconds = 0L;
        long clockwiseCost = 0L;

        for (int i = 0; i < C.length; i++) {
            // if the position to go is the one we currently are
            // we continue with next iteration
            if(C[i] == pointer) continue;

            // else
            if(C[i] < pointer) {
                clockwiseCost = pointer - C[i];
                if (clockwiseCost > (N/2)) totalSeconds += N - clockwiseCost;
                else totalSeconds += clockwiseCost;
            } else { // C[i] > pointer
                clockwiseCost = C[i] - pointer;
                if (clockwiseCost > (N/2)) totalSeconds += N - clockwiseCost;
                else totalSeconds += clockwiseCost;
            }
            pointer = C[i];
        }

        return totalSeconds;
    }

    public static void main(String[] args) {
        //System.out.println(getMinCodeEntryTime(3,3,new int[]{1,2,3}));
        //System.out.println(getMinCodeEntryTime(10,4,new int[]{9,4,4,8}));
        System.out.println(getMinCodeEntryTime(5,4,new int[]{1,2,3}));
    }

}
