package com.csc349.project4;

/**
 * Created by Andrew LeDawson on 2/20/2018.
 */
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
