package org.erni.facebookprep.level1;

import java.util.*;

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

    // this is the most elegant but not the most efficient solution due to the linear cost of LinkedList.contains() method
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

    public static boolean alreadyEaten(int dish, List<Integer> eatenDishes) {
        for (int i = 0; i < eatenDishes.size(); i++) {
            if (dish == eatenDishes.get(i)) return true;
        }
        return false;
    }

    public static int getMaximumEatenDishCount2(int N, int[] D, int K) {
        List<Integer> eatenDishes = new ArrayList<Integer>(K);
        eatenDishes.add(D[0]); // we eat the first dish :)
        int totalEatenDishes = 1;

        for (int i = 1; i < N; i ++) {
            if(!alreadyEaten(D[i], eatenDishes)) { // not already eaten
                eatenDishes.add(D[i]);
                totalEatenDishes++;
                if(eatenDishes.size() > K) eatenDishes.remove(0);
            }
        }

        return totalEatenDishes;
    }

    public static boolean alreadyEaten(int dish, int[] eatenDishes) {
        for (int i = 0; i < eatenDishes.length; i++) {
            if (dish == eatenDishes[i]) return true;
        }
        return false;
    }

    public static int getMaximumEatenDishCount3(int N, int[] D, int K) {
        int[] eatenDishes = new int[K];
        int index = 0;
        eatenDishes[index] = D[0]; // we eat the first dish :)
        int totalEatenDishes = 1;

        for (int i = 1; i < N; i ++) {
            if(!alreadyEaten(D[i], eatenDishes)) { // not already eaten
                index++;
                if(index == K) index = 0;
                eatenDishes[index] = D[i]; // we eat it
                totalEatenDishes++;
            }
        }

        return totalEatenDishes;
    }

    // this is the most efficient due to the constant cost of Set.contains() method
    public static int getMaximumEatenDishCount4(int N, int[] D, int K) {
        Queue<Integer> eatenDishes = new LinkedList<Integer>();
        Set<Integer> eatenDishesSet = new HashSet<Integer>(K);

        // we eat the first dish :)
        eatenDishes.add(D[0]);
        eatenDishesSet.add(D[0]);
        int totalEatenDishes = 1;

        for (int i = 1; i < N; i ++) {
            if(!eatenDishesSet.contains(D[i])) { // not already eaten
                eatenDishes.add(D[i]);
                eatenDishesSet.add(D[i]);
                totalEatenDishes++;
                if(eatenDishes.size() > K) {
                    int dish = eatenDishes.remove();
                    eatenDishesSet.remove(dish);
                }
            }
        }

        return totalEatenDishes;
    }

    public static void main(String[] args) {
        /*
        System.out.println(getMaximumEatenDishCount(6, new int[]{1,2,3,3,2,1}, 1));
        System.out.println(getMaximumEatenDishCount(6, new int[]{1,2,3,3,2,1}, 2));
        System.out.println(getMaximumEatenDishCount(7, new int[]{1,2,1,2,1,2,1}, 2));

        System.out.println(getMaximumEatenDishCount(6, new int[]{1,2,3,3,2,1}, 1));
        System.out.println(getMaximumEatenDishCount(6, new int[]{1,2,3,3,2,1}, 2));
        System.out.println(getMaximumEatenDishCount(7, new int[]{1,2,1,2,1,2,1}, 2));

        System.out.println(getMaximumEatenDishCount3(6, new int[]{1,2,3,3,2,1}, 1));
        System.out.println(getMaximumEatenDishCount3(6, new int[]{1,2,3,3,2,1}, 2));
        System.out.println(getMaximumEatenDishCount3(7, new int[]{1,2,1,2,1,2,1}, 2));
        */
        System.out.println(getMaximumEatenDishCount4(6, new int[]{1,2,3,3,2,1}, 1));
        System.out.println(getMaximumEatenDishCount4(6, new int[]{1,2,3,3,2,1}, 2));
        System.out.println(getMaximumEatenDishCount4(7, new int[]{1,2,1,2,1,2,1}, 2));
    }

}
