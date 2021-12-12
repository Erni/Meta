package org.erni.adventofcode;

import java.util.Arrays;
import java.util.Set;

public class Day11DumboOctopus {

    static int[][] octopuses = {
            {6,6,1,7,1,1,3,5,8,4},
            {6,5,4,4,2,1,8,6,3,8},
            {5,4,5,7,3,3,1,4,8,8},
            {1,1,3,5,6,7,5,5,8,7},
            {1,2,2,1,3,5,3,2,1,6},
            {1,8,1,1,1,2,4,3,7,8},
            {1,3,8,7,8,6,4,3,6,8},
            {4,4,2,7,6,3,7,2,6,2},
            {6,7,7,8,6,4,5,4,8,6},
            {3,6,8,2,1,4,6,7,4,5}
    };

    static int flashes = 0;

    static int TOP = 10;

    public static void flash(int row, int position) {
        flashes++;

        // flash process
        if(row == 0) { // first row
            if (position == 0) { // top left corner
                increaseEnergy(row + 1, position); // down
                increaseEnergy(row, position + 1); // right
                increaseEnergy(row + 1, position + 1); // diag down right
            } else if (position == octopuses[row].length - 1) { // top right corner
                increaseEnergy(row + 1,position); // down
                increaseEnergy(row,position - 1); // left
                increaseEnergy(row + 1, position - 1); // diag down left
            } else { // within the row
                increaseEnergy(row + 1,position); // down
                increaseEnergy(row,position - 1); // left
                increaseEnergy(row,position + 1); // right
                increaseEnergy(row + 1, position + 1); // diag down right
                increaseEnergy(row + 1, position - 1); // diag down left
            }
        } else if (row == octopuses.length - 1) { // last row
            if (position == 0) { // bottom left corner
                increaseEnergy(row - 1,position); // up
                increaseEnergy(row,position + 1); // right
                increaseEnergy(row - 1, position + 1); // diag up right
            } else if (position == octopuses[row].length - 1) { // bottom right corner
                increaseEnergy(row - 1,position); // up
                increaseEnergy(row,position - 1); // left
                increaseEnergy(row - 1, position - 1); // diag up left
            } else { // within the row
                increaseEnergy(row - 1, position); // up
                increaseEnergy(row,position - 1); // left
                increaseEnergy(row,position + 1); // right
                increaseEnergy(row - 1, position + 1); // diag up right
                increaseEnergy(row - 1, position - 1); // diag up left
            }
        } else { // within the matrix
            if (position == 0) { // height in the left
                increaseEnergy(row - 1, position); // up
                increaseEnergy(row,position + 1); // right
                increaseEnergy(row + 1, position); // down
                increaseEnergy(row - 1, position + 1); // diag up right
                increaseEnergy(row + 1, position + 1); // diag down right
            } else if (position == octopuses[row].length - 1) { // height in the right
                increaseEnergy(row - 1,position); // up
                increaseEnergy(row,position - 1); // left
                increaseEnergy(row + 1,position); // down
                increaseEnergy(row - 1, position - 1); // diag up left
                increaseEnergy(row + 1, position - 1); // diag down left
            } else { // general position within the matrix
                increaseEnergy(row - 1, position); // up
                increaseEnergy(row,position - 1); // left
                increaseEnergy(row + 1,position); // down
                increaseEnergy(row,position + 1); // right
                increaseEnergy(row - 1, position - 1); // diag up left
                increaseEnergy(row - 1, position + 1); // diag up right
                increaseEnergy(row + 1, position - 1); // diag down left
                increaseEnergy(row + 1, position + 1); // diag down right
            }
        }
    }

    public static void increaseEnergy(int row, int position) {
        if (octopuses[row][position] < TOP) {
            octopuses[row][position]++;
            if (octopuses[row][position] == TOP) flash(row, position);
        }
    }

    public static void step() {
        for (int row = 0; row < octopuses.length; row++)
            for (int i = 0; i < octopuses[row].length; i++) increaseEnergy(row, i);
    }

    public static void part1() {
        for (int iter = 0; iter < 100; iter++) {
            step();
            for (int row = 0; row < octopuses.length; row++)
                for (int i = 0; i < octopuses[row].length; i++) if (octopuses[row][i] == TOP) octopuses[row][i] = 0;
        }
    }

    public static int part2() {
        int steps = 0;
        int currentStepFlashes = 0;

        while(true) {
            step();
            steps++;
            for (int row = 0; row < octopuses.length; row++)
                for (int i = 0; i < octopuses[row].length; i++) if (octopuses[row][i] == TOP) {
                    octopuses[row][i] = 0;
                    currentStepFlashes++;
                }
            if (currentStepFlashes == 100) return steps;
            currentStepFlashes = 0;
        }
    }

    public static void main(String[] args) {
        /*
        part1();
        System.out.println("Flashes: " + flashes); // 1290 too low, 1602 too high
        for (int[] row : octopuses) System.out.println(Arrays.toString(row));
         */

        int steps = part2();
        System.out.println("Steps passed: " + steps);
        for (int[] row : octopuses) System.out.println(Arrays.toString(row));
    }

}
