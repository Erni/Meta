package org.erni.facebookprep.level1;

import java.util.ArrayList;
import java.util.Arrays;

public class ScoreboardInference {

    /**
     * You are spectating a programming contest with N competitors,
     * each trying to independently solve the same set of programming problems.
     * Each problem has a point value, which is either 1 or 2.
     *
     * On the scoreboard, you observe that the ith competitor has attained a score of S_i,
     * which is a positive integer equal to the sum of the point values of all the problems they have solved.
     * The scoreboard does not display the number of problems in the contest, nor their point values.
     * Using the information available, you would like to determine the minimum possible number of problems in the contest.
     *
     * @param N
     * @param S
     * @return
     */
    public static int getMinProblemCount(int N, int[] S) {
        Arrays.sort(S);
        int max = 0;
        boolean thereisanimpair = false;

        int problems;
        int minProblems = 0;

        for (int i = 0; i < S.length; i++) {
            // if this score has appeared already, skip it
            if (S[i] == max) continue;
            // else
            max = S[i];

            // if the number is pair
            if (S[i] % 2 == 0) {
                problems = S[i] / 2;
                if (thereisanimpair) problems++;
            } else {
                thereisanimpair = true;
                problems = (S[i] / 2) + 1;
            }

            if(problems > minProblems) minProblems = problems;
        }

        return minProblems;
    }

    public static void main(String[] args) {
        System.out.println(getMinProblemCount(6, new int[]{1,2,3,4,5,6})); // 4
        System.out.println(getMinProblemCount(4, new int[]{4,3,3,4})); // 3
        System.out.println(getMinProblemCount(4, new int[]{2,4,6,8})); // 4
        System.out.println(getMinProblemCount(4, new int[]{2,2,2,2})); // 1
        System.out.println(getMinProblemCount(4, new int[]{2,2,3,2})); // 2
    }

}
