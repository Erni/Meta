package org.erni.facebookprep.level1;

import java.util.LinkedList;
import java.util.Queue;

/**
 * There are N dishes in a row on a kaiten belt, with the ith dish being of type D_i.
 * Some dishes may be of the same type as one another.
 *
 * You're very hungry, but you'd also like to keep things interesting. The N dishes will arrive in front of you,
 * one after another in order, and for each one you'll eat it as long as it isn't the same type
 * as any of the previous K dishes you've eaten. You eat very fast, so you can consume a dish
 * before the next one gets to you.
 *
 * Determine how many dishes you'll end up eating.
 */
public class Kaitenzushi {

    public static int getMaximumEatenDishCount(int N, int[] D, int K) {
        Queue<Integer> eatenDishes = new LinkedList<Integer>();
        eatenDishes.add(D[0]); // we eat the first dish :)
        int totalEatenDishes = 1;

        for (int i = 1; i < N; i ++) {
            if(!eatenDishes.contains(D[i])) { // not already eaten
                eatenDishes.add(D[i]);
                totalEatenDishes++;
                if(eatenDishes.size() > K) eatenDishes.remove();
            }
        }

        return totalEatenDishes;
    }

    public static void main(String[] args) {
        System.out.println(getMaximumEatenDishCount(6, new int[]{1,2,3,3,2,1}, 1));
        System.out.println(getMaximumEatenDishCount(6, new int[]{1,2,3,3,2,1}, 2));
        System.out.println(getMaximumEatenDishCount(7, new int[]{1,2,1,2,1,2,1}, 2));
    }

}
