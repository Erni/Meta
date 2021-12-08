package org.erni.adventofcode;

import java.util.*;

public class Day6Lanternfish {

    static Integer[] fishes = {1,1,1,1,3,1,4,1,4,1,1,2,5,2,5,1,1,1,4,3,1,4,1,1,1,1,1,1,1,2,1,2,4,1,1,1,1,1,1,1,3,1,1,5,1,1,2,1,5,1,1,1,1,1,1,1,1,4,3,1,1,1,2,1,1,5,2,1,1,1,1,4,5,1,1,2,4,1,1,1,5,1,1,1,1,5,1,3,1,1,4,2,1,5,1,2,1,1,1,1,1,3,3,1,5,1,1,1,1,3,1,1,1,4,1,1,1,4,1,4,3,1,1,1,4,1,2,1,1,1,2,1,1,1,1,5,1,1,3,5,1,1,5,2,1,1,1,1,1,4,4,1,1,2,1,1,1,4,1,1,1,1,5,3,1,1,1,5,1,1,1,4,1,4,1,1,1,5,1,1,3,2,2,1,1,1,4,1,3,1,1,1,2,1,3,1,1,1,1,4,1,1,1,1,2,1,4,1,1,1,1,1,4,1,1,2,4,2,1,2,3,1,3,1,1,2,1,1,1,3,1,1,3,1,1,4,1,3,1,1,2,1,1,1,4,1,1,3,1,1,5,1,1,3,1,1,1,1,5,1,1,1,1,1,2,3,4,1,1,1,1,1,2,1,1,1,1,1,1,1,3,2,2,1,3,5,1,1,4,4,1,3,4,1,2,4,1,1,3,1};
    static int MAX_DAYS = 9;
    static int RESTART_REPRODUCTION_DAY = 6;

    public static void part1(int days) {
        List<Integer> fishesList = new ArrayList<>();
        Collections.addAll(fishesList,fishes);

        while(days > 0) {
            int currentDayFishes = fishesList.size();
            int fishesToAdd = 0;

            for (int i = 0; i < currentDayFishes; i++) {
                int fish = fishesList.get(i);
                if (fish == 0) {
                    fishesList.set(i, 6);
                    fishesToAdd++;
                } else {
                    fish--;
                    fishesList.set(i, fish);
                }
            }

            for (int i = 0; i < fishesToAdd; i++) {
                fishesList.add(8);
            }
            days--;
        }

        System.out.println("Total fishes after 80 days: " + fishesList.size());
    }

    static class MutableLong {
        private long value = 0;
        public void increment () { ++value; }
        public void increment (long val) { value += val; }
        public long get ()       { return value; }
    }

    public static void nextDay(Map<Integer,MutableLong> fishesMap) {
        MutableLong zerosCount = fishesMap.get(0);

        for(int i = 1; i < MAX_DAYS; i++) {
            MutableLong count = fishesMap.get(i);
            fishesMap.put(i-1, count);
        }
        fishesMap.put(MAX_DAYS - 1, zerosCount);
        MutableLong initialDay = fishesMap.get(RESTART_REPRODUCTION_DAY);
        initialDay.increment(zerosCount.get());
        fishesMap.put(RESTART_REPRODUCTION_DAY,initialDay);
    }

    public static void part2(int days) {
        Map<Integer, MutableLong> fishesMap = new HashMap<>();

        for (int fish : fishes) {
            MutableLong count = fishesMap.get(fish);
            if (count == null) {
                MutableLong init = new MutableLong();
                init.increment();
                fishesMap.put(fish, init);
            }
            else {
                count.increment();
            }
        }

        // make sure all the keys (0 to 8) appear in the map
        for (int i = 0; i < MAX_DAYS; i++) {
            if (fishesMap.get(i) == null) fishesMap.put(i, new MutableLong());
        }

        while (days > 0) {
            nextDay(fishesMap);
            days--;
        }

        long totalFishes = 0;
        for (MutableLong value: fishesMap.values()) {
            totalFishes += value.get();
        }

        System.out.println(totalFishes);
    }

    public static void main(String[] args) {
        part1(80);
        part2(256);
    }

}
