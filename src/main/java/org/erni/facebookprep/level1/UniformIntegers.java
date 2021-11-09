package org.erni.facebookprep.level1;

/**
 * A positive integer is considered uniform if all of its digits are equal.
 * For example, 222 is uniform, while 223 is not.
 *
 * Given two positive integers A and B, determine the number of uniform integers between A and B, inclusive.
 */
public class UniformIntegers {

    public static int getLength (long A) {
        int length = 0;
        long temp = 1;
        while (temp <= A) {
            length++;
            temp *= 10;
        }
        return length;
    }

    public static boolean isEqualOrGreaterThanUniform (String bString, int firstNumber) {
        for (int i = 1; i < bString.length(); i++) {
            if (Character.getNumericValue(bString.charAt(i)) < firstNumber) return false;
        }

        return true;
    }

    public static boolean isEqualOrLowerThanUniform (String bString, int firstNumber) {
        for (int i = 1; i < bString.length(); i++) {
            if (Character.getNumericValue(bString.charAt(i)) > firstNumber) return false;
        }

        return true;
    }

    public static boolean isUniform(String bString) {
        char first = bString.charAt(0);
        for (int i = 1; i < bString.length(); i++) {
            if (bString.charAt(i) != first) return false;
        }

        return true;
    }

    public static int getUniformIntegerCountInInterval(long A, long B) {
        int uniformIntegers = 0;
        int lengthA = getLength(A);
        int lengthB = getLength(B);
        String bString = String.valueOf(B);
        int firstNumberB = Character.getNumericValue(bString.charAt(0));
        String aString = String.valueOf(A);
        int firstNumberA = Character.getNumericValue(aString.charAt(0));

        if (A == B) {
            if(isUniform(aString)) return 1;
            return 0;
        }

        if(lengthA == lengthB) {
            if (lengthA == 1) return (int) (B - A) + 1;
            // else
            double div = Math.pow(10, lengthA-1);
            uniformIntegers = (int) ((B - A) / div);
        } else { // lengthB > lengthA as A is always <= B
            uniformIntegers += (int) (9 * (Math.pow(10,lengthA-1)) - A) / Math.pow(10,lengthA-1);
            uniformIntegers++; // counting A as 9 or 99 or 999 or 9999...
            uniformIntegers += (lengthB - lengthA - 1) * 10;
            uniformIntegers += firstNumberB - 1;
            if (lengthA > 1 ) if (isEqualOrLowerThanUniform(aString,firstNumberA)) uniformIntegers++;
        }
        if (isEqualOrGreaterThanUniform(bString, firstNumberB)) uniformIntegers++;

        return uniformIntegers;
    }

    public static void main(String[] args) {
        //System.out.println(getUniformIntegerCountInInterval(50, 80)); // 3
        //System.out.println(getUniformIntegerCountInInterval(7, 30)); // 6
        //System.out.println(getUniformIntegerCountInInterval(75, 300)); // 5
        //System.out.println(getUniformIntegerCountInInterval(99, 99)); // 1
        System.out.println(getUniformIntegerCountInInterval(96, 96)); // 0
    }

}
