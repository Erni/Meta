package org.erni.facebookprep.level2;

import java.util.*;

/**
 * A family of frogs in a pond are traveling towards dry land to hibernate.
 * They hope to do so by hopping across a trail of N lily pads, numbered from 1 to N in order.
 *
 * There are F frogs, numbered from 1 to F. Frog i is currently perched atop lily pad P_i.
 * No two frogs are currently on the same lily pad.
 * Lily pad N is right next to the shore, and none of the frogs are initially on lily pad N.
 *
 * Each second, one frog may hop along the trail towards lily pad N.
 * When a frog hops, it moves to the nearest lily pad after its current lily pad which is not currently
 * occupied by another frog (hopping over any other frogs on intermediate lily pads along the way).
 * If this causes it to reach lily pad N, it will immediately exit onto the shore.
 * Multiple frogs may not simultaneously hop during the same second.
 *
 * Assuming the frogs work together optimally when deciding which frog should hop during each second,
 * determine the minimum number of seconds required for all F of them to reach the shore.
 */
public class Hops {

    // good working solution but too slow
    public static long getSecondsRequired(long N, int F, long[] P) {
        SortedSet<Long> set = new TreeSet<Long>();
        for (int i = 0; i < P.length; i++) {
            set.add(P[i]);
        }

        long jumps = 0;
        long nextPad;
        long currentFirst;

        while (!set.isEmpty()) { // while there are frogs
            currentFirst = set.first();
            nextPad = currentFirst + 1;
            jumps++;
            while(set.contains(nextPad) && nextPad < N) { // if current pad is occupied
                nextPad++;
            }
            if(nextPad < N) {
                set.add(nextPad);
            } // else target == N and therefore frog exits
            set.remove(currentFirst); // removing original position
        }
        return jumps;
    }

    // perfect solution, very fast
    public static long getSecondsRequired2(long N, int F, long[] P) {
        Arrays.sort(P);
        long jumps = 0;

        for (int i = 0; i < F - 1; i ++) {
            jumps += P[i + 1] - P[i] - 1;
        }

        // jump until all the frogs are in N
        jumps += N - P[F - 1] - 1; // jumps for all the frogs to arrive before N
        jumps += F; // 1 more jump per frog

        return jumps;
    }

    public static void main(String[] args) {
        System.out.println(getSecondsRequired2(3,1,new long[]{1})); // 2
        System.out.println(getSecondsRequired2(6,3,new long[]{5,2,4})); // 4
    }

}
