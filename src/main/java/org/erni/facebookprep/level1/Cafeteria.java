package org.erni.facebookprep.level1;

import java.util.Arrays;

/**
 * A cafeteria table consists of a row of N seats, numbered from 1 to N from left to right.
 * Social distancing guidelines require that every diner be seated such that K seats to their left
 * and K seats to their right (or all the remaining seats to that side if there are fewer than K) remain empty.
 *
 * There are currently M diners seated at the table, the ith of whom is in seat S_i.
 * No two diners are sitting in the same seat, and the social distancing guidelines are satisfied.
 *
 * Determine the maximum number of additional diners who can potentially sit at the table
 * without social distancing guidelines being violated for any new or existing diners,
 * assuming that the existing diners cannot move and that the additional diners will cooperate to maximize how many of them can sit down.
 *
 * Please take care to write a solution which runs within the time limit.
 *
 * Constraints
 * 1 ≤ N ≤ 10^15 // number of N seats
 * 1 ≤ K ≤ N // min distance between diners
 * 1 ≤ M ≤ 500.000 // current dinners (= S.length)
 * 1 ≤ S_i ≤ N // Diner i is sit at S[i-1]
 */
public class Cafeteria {

    public static long getMaxAdditionalDinersCount(long N, long K, int M, long[] S) {
        if (N < 3) return 0L;

        long additionalDiners = 0L;

        Arrays.sort(S);
        long lastDiner = S[S.length-1];
        long nextDiner = 0L;
        long currentDiner = S[0]; // initialized to the first diner
        long distanceBetweenDiners = currentDiner - 1;

        if (currentDiner > 2) { // because K is always >= 1
            if ((distanceBetweenDiners) > K) { // we can have more diners before the first diner
                additionalDiners += distanceBetweenDiners/(K+1);
            }
        }

        for (int i = 1; i < S.length; i++) { // if there´s a single diner it would not go into this
            nextDiner = S[i];
            distanceBetweenDiners = nextDiner - currentDiner;
            if(distanceBetweenDiners > (2*K) + 1) { // we can have more diners between these 2 diners
                additionalDiners += distanceBetweenDiners/(K+1) - 1;
            }
            currentDiner = nextDiner;
        }

        // LAST: add the diners that can fit from the last diner till the end of the table
        distanceBetweenDiners = N - currentDiner;
        if(distanceBetweenDiners > K ) { // we can have more diners til the end
            additionalDiners += distanceBetweenDiners/(K+1);
        }

        return additionalDiners;
    }

    public static void main(String[] args) {
        System.out.println(getMaxAdditionalDinersCount(100,2,2, new long[]{6, 34, 54}));
    }

}
