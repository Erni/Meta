package org.erni.facebookprep.warmup;

/**
 * You're playing Battleship on a grid of cells with RR rows and CC columns.
 * There are 00 or more battleships on the grid, each occupying a single distinct cell.
 * The cell in the iith row from the top and jjth column from the left either contains a battleship
 * (G_ij = 1G) or doesn't (G_ij = 0)
 *
 * You're going to fire a single shot at a random cell in the grid. You'll choose this cell uniformly at random from the R*CR∗C possible cells. You're interested in the probability that the cell hit by your shot contains a battleship.
 * Your task is to implement the function getHitProbability(R, C, G) which returns this probability.
 * Note: Your return value must have an absolute or relative error of at most 10^(-6) to be considered correct.
 *
 * Constraints
 * 1 ≤ R, C ≤ 100
 * 0 ≤ G_ij ≤ 1
 */
public class Battleship {

    public boolean isInRange(int A) {
        if(A >= 1 && A <= 100) return true;
        return false;
    }

    public double getHitProbability(int R, int C, int[][] G) {
        // remember that if battleships was not a double, the int division at the end would round up to 0 or 1
        double battleships = 0;
        for(int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (G[i][j] == 1) battleships++;
            }
        }

        return battleships/(R*C);
    }

}
