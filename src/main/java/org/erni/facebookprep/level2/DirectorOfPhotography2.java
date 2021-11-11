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
                char c;
                for (int j = i - X; j >= i - Y && j > -1; j--) {
                    c = C.charAt(j);
                    if(c == 'P') PsLeft++;
                    else if(c == 'B') BsLeft++;
                }
                // Count Ps and Bs in the allowed distance to the right
                for(int j = i + X; j <= i + Y && j < N; j++) {
                    c = C.charAt(j);
                    if(c == 'P') PsRight++;
                    else if(c == 'B') BsRight++;
                }

                artisticPhotographs += (PsLeft * BsRight) + (PsRight * BsLeft);
            }
        }

        return artisticPhotographs;
    }

    public static long getArtisticPhotographCount2(int N, String C, int X, int Y) {
        int leftWindowPs = 0;
        int leftWindowBs = 0;
        int rightWindowPs = 0;
        int rightWindowBs = 0;
        int artisticPhotographs = 0;
        int LWLI = 0; // Left Window Lower Index
        int LWUI = 0; // Left Window Upper Index
        int RWLI = X + X - 1; // Right Window Lower Index, Starts at twice min distance
        int RWUI = RWLI + (Y - X); // Right Window Upper Index, Starts at Right Lower Index + (max - min) distance
        if (RWUI >= N) RWUI = N - 1;  // in the special case it´s initialized greater than length of C
        int WINDOW_LENGTH = Y - X + 1;

        // initialize left window to the first char
        char c = C.charAt(0);
        if (c == 'P') leftWindowPs++;
        else if (c == 'B') leftWindowBs++;
        int leftWindowLength = 1;

        // we initialize the right window to its total length
        for (int i = RWLI; i <= RWUI; i++) { // we start at RWLI
            c = C.charAt(i);
            if (c == 'P') rightWindowPs++;
            else if (c == 'B') rightWindowBs++;
        }

        for (int i = X; i < N-X; i++) {
            // let window upkeep
            if(i - X > LWUI) { // if we have to increase (or move up one space) the window
                LWUI++;
                int lwuiChar = C.charAt(LWUI);
                if (lwuiChar == 'P') leftWindowPs++;
                else if (lwuiChar == 'B') leftWindowBs++;
                leftWindowLength++;
                if(leftWindowLength > WINDOW_LENGTH) {
                    // increase lower left index
                    int lwliChar = C.charAt(LWLI);
                    if (lwliChar == 'P') leftWindowPs--;
                    else if (lwliChar == 'B') leftWindowBs--;
                    LWLI++;
                    leftWindowLength--;
                }
            }

            // right window upkeep
            if (RWLI == N) break;
            c = C.charAt(RWLI);
            if (c == 'P') rightWindowPs--;
            else if (c == 'B') rightWindowBs--;
            RWLI++;
            if(RWUI < N - 1) { // check if RWUI is already at the end
                RWUI++;
                c = C.charAt(RWUI);
                if (c == 'P') rightWindowPs++;
                else if (c == 'B') rightWindowBs++;
            }

            // start checking
            if (C.charAt(i) == 'A') {
                artisticPhotographs += (leftWindowPs * rightWindowBs) + (rightWindowPs * leftWindowBs);
            }
        }

        return artisticPhotographs;
    }

    public static void main(String[] args) {
        /*
        System.out.println(getArtisticPhotographCount2(5, "APABA", 1, 2)); // 1
        System.out.println(getArtisticPhotographCount2(5, "APABA", 2, 3)); // 0

         */
        System.out.println(getArtisticPhotographCount2(8, ".PBAAP.B", 1, 3)); // 3
        /*
        System.out.println(getArtisticPhotographCount2(8, ".PBAAP.B", 2, 3)); // 1
        System.out.println(getArtisticPhotographCount2(12, ".PBAAAAAAP.B", 2, 3)); // 0
        System.out.println(getArtisticPhotographCount2(3, "PBA", 1, 3)); // 0
        System.out.println(getArtisticPhotographCount2(3, "PAB", 1, 3)); // 1
        System.out.println(getArtisticPhotographCount2(3, "BAP", 1, 3)); // 1
        System.out.println(getArtisticPhotographCount2(5, "BAPAB", 1, 3)); // 2
        System.out.println(getArtisticPhotographCount2(5, "BAPAB", 1, 5)); // 2
        System.out.println(getArtisticPhotographCount2(5, "BAPAA", 1, 5)); // 1
        System.out.println(getArtisticPhotographCount2(6, "BAPABA", 1, 6)); // 2
        System.out.println(getArtisticPhotographCount2(6, "BAPABA", 1, 1)); // 2

         */

        //System.out.println(getArtisticPhotographCount2(7, "PAA.BAB", 2, 4));
    }

}
