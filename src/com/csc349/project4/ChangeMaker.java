/*  David Twyman, Andrew LeDawson
 **  dtwyman@calpoly.edu, aledawson@calpoly.edu
 **  CSC 349-03
 **  Project 4
 **  2-23-2018
 */

package com.csc349.project4;

public class ChangeMaker {
    public static int[] change_DP(int n, int[] d){
        // TODO
        return null;
    }

    public static int[] change_greedy(int n, int[] d){
        int[] results = new int[d.length];
        for(int i = 0; i < d.length; i++){
            results[i] = n/d[i];
            n -= (d[i] * results[i]);
        }
        return results;
    }

    public static void main() {
        // TODO
    }
}
