package org.erni.facebookprep.level1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DirectorOfPhotography {

    /**
     * A photograph happens when we can find the chars P, A and B in the String
     * Char A must be between P and B (PAB or BAP)
     *
     * If the photograph is artistic, X and Y are the min and max distances allowed between A and P, and A and B
     *
     * @param N size of C
     * @param C String of chars 'P', 'A', 'B' or '.'
     * @param X distance allowed min
     * @param Y distance allowed max
     * @return number of artistic photographs (is a photograph and within the range)
     */
    public static int getArtisticPhotographCount(int N, String C, int X, int Y) {
        int artisticPhotographs = 0;
        for (int i = 1; i < C.length(); i ++) {
            if (C.charAt(i) == 'A') {
                // Check Ps and Bs in the allowed distance to the left
                for (int j = i - X; j >= i - Y; j--) {
                    if (j < 0) break;
                    if(C.charAt(j) == 'P') {
                        // let´s find out  if we have any Bs on the right of A
                        for(int k = i + X; k <= i + Y; k++) {
                            if (k >= N) break;
                            if (C.charAt(k) == 'B') artisticPhotographs++;
                        }
                    } else if (C.charAt(j) == 'B') {
                        // let´s find out if we have any Ps on the right of A
                        for(int k = i + X; k <= i + Y; k++) {
                            if (k >= N) break;
                            if (C.charAt(k) == 'P') artisticPhotographs++;
                        }
                    }
                }
            }
        }

        return artisticPhotographs;
    }

    public static void main(String[] args) {
        System.out.println(getArtisticPhotographCount(5, "APABA", 1, 2));
    }

}
