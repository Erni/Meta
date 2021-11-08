package org.erni.facebookprep.warmup;

/**
 * Given three integers A, B, and C, determine their sum.
 * Your task is to implement the function getSum(A, B, C) which returns the sum A + B + C
 *
 * Constraints
 * 1 ≤ A, B, C ≤ 100
 */

public class Abcs {

    public boolean isInRange(int A) {
        if(A >= 1 && A <= 100) return true;
        return false;
    }

    public int getSum(int A, int B, int C) {
        // Write your code here
        if(isInRange(A) && isInRange(B) && isInRange(C)) return A + B + C;
        else return -1;
    }

}
