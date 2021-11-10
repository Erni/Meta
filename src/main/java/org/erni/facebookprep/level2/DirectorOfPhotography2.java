package org.erni.facebookprep.level2;

import java.util.HashMap;
import java.util.Map;

/**
 * A photograph happens when we can find the chars P, A and B in the String
 * Char A must be between P and B (PAB or BAP)
 *
 * If the photograph is artistic, X and Y are the min and max distances allowed between A and P, and A and B
 *
 * @param N size of C (much longer than version 1)
 * @param C String of chars 'P', 'A', 'B' or '.'
 * @param X distance allowed min
 * @param Y distance allowed max
 * @return number of artistic photographs (is a photograph and within the range)
 */
public class DirectorOfPhotography2 {

    public static long getArtisticPhotographCount(int N, String C, int X, int Y) {
        int artisticPhotographs = 0;
        for (int i = X; i < N-X; i++) {
            if (C.charAt(i) == 'A') {
                // Count the number of Ps and Bs on the left and the right
                int PsLeft = 0;
                int BsLeft = 0;
                int PsRight = 0;
                int BsRight = 0;

                // Count Ps and Bs in the allowed distance to the left
                for (int j = i - X; j >= i - Y && j > -1; j--) {
                    if(C.charAt(j) == 'P') PsLeft++;
                    else if(C.charAt(j) == 'B') BsLeft++;
                }
                // Count Ps and Bs in the allowed distance to the right
                for(int j = i + X; j <= i + Y && j < N; j++) {
                    if(C.charAt(j) == 'P') PsRight++;
                    else if(C.charAt(j) == 'B') BsRight++;
                }

                artisticPhotographs += (PsLeft * BsRight) + (PsRight * BsLeft);
            }
        }

        return artisticPhotographs;
    }

    public static void main(String[] args) {
        //System.out.println(getArtisticPhotographCount(5, "APABA", 1, 2)); // 1
        //System.out.println(getArtisticPhotographCount(5, "APABA", 2, 3)); // 0
        System.out.println(getArtisticPhotographCount(8, ".PBAAP.B", 1, 3)); // 3
    }

}
