package org.erni.facebookprep.level1;

/**
 * A positive integer is considered uniform if all of its digits are equal.
 * For example, 222 is uniform, while 223 is not.
 *
 * Given two positive integers A and B, determine the number of uniform integers between A and B, inclusive.
 */
public class UniformIntegers {

    public static int getUniformIntegerCountInInterval(long A, long B) {
        int uniformNumbers = 0;
        long i = A;
        int power = (int) Math.log10(A); // initialize depending on As cardinality

        if (i < 10) {
            if (B <= 9) return (int) (B - i + 1);
            else uniformNumbers = (int) (9 - i + 1);
            i = 11;
            power = 1;
        }

        // initialize current increment
        long currentIncrement = 11;
        for(int j = 1; j < power; j++) {
            currentIncrement = currentIncrement * 10 + 1;
        }

        // initialize i if no divisible by currentIncrement
        long rest = i % currentIncrement;
        if(rest != 0) {
            i = i + (currentIncrement - rest);
        }

        // here we know that if A == B, A and B are greater than 9
        if (A == B) {
            // special case A = 10 = B
            if (A == 10) return 0;
            long restA = A % currentIncrement;
            if(restA == 0) return 1;
            else return 0;
        }

        while (i < B) {
            uniformNumbers++;
            if(i > Math.pow(10,power+1)) {
                power++;
                i++;
                currentIncrement = currentIncrement * 10 + 1;
                if (i == B) {
                    uniformNumbers++;
                    break;
                }
            }
            i += currentIncrement;
        }

        return uniformNumbers;
    }

    public static void main(String[] args) {
        System.out.println(getUniformIntegerCountInInterval(5, 8)); // 4
        System.out.println(getUniformIntegerCountInInterval(8, 8)); // 1
        System.out.println(getUniformIntegerCountInInterval(8, 10)); // 2
        System.out.println(getUniformIntegerCountInInterval(8, 13)); // 3
        System.out.println(getUniformIntegerCountInInterval(8, 130)); // 12
        System.out.println(getUniformIntegerCountInInterval(75, 300)); // 5

        System.out.println(getUniformIntegerCountInInterval(7, 7)); // 1
        System.out.println(getUniformIntegerCountInInterval(9, 9)); // 1
        System.out.println(getUniformIntegerCountInInterval(10, 10)); // 0
        System.out.println(getUniformIntegerCountInInterval(11, 11)); // 1
        System.out.println(getUniformIntegerCountInInterval(22, 22)); // 1
        System.out.println(getUniformIntegerCountInInterval(222, 222)); // 1
        System.out.println(getUniformIntegerCountInInterval(532, 532)); // 0
        System.out.println(getUniformIntegerCountInInterval(13000, 13000)); // 0
        System.out.println(getUniformIntegerCountInInterval(45, 45)); // 0



        //System.out.println(getUniformIntegerCountInInterval(8, 13)); // 3
        //System.out.println(getUniformIntegerCountInInterval(8, 13)); // 3
        //System.out.println(getUniformIntegerCountInInterval(50, 80)); // 3
        //System.out.println(getUniformIntegerCountInInterval(7, 30)); // 6
        //System.out.println(getUniformIntegerCountInInterval(75, 300)); // 5
        //System.out.println(getUniformIntegerCountInInterval(99, 99)); // 1
        //System.out.println(getUniformIntegerCountInInterval(96, 96)); // 0
    }

}
