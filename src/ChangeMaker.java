/*  David Twyman, Andrew LeDawson
 **  dtwyman@calpoly.edu, aledawson@calpoly.edu
 **  CSC 349-03
 **  Project 4
 **  2-23-2018
 */

import java.io.*;
import java.util.*;

public class ChangeMaker {
	public static void main(String[] args)
	{
		boolean run = true;
		Scanner scanner = new Scanner(System.in);
		while (run)
		{
			System.out.println("Enter data for the problem");
			if (scanner.hasNextInt())
			{
				int k = scanner.nextInt();
				int D[] = new int[k];
				for (int i = 0; i < k; i++)
				{
					D[i] = scanner.nextInt();
				}
				System.out.println("Enter a value for n, the amount of change you want");
				int n = scanner.nextInt();
				int[] A = change_DP(n, D);
                int[] B = change_greedy(n, D);
				printChangeDP(A, D, k, n, true);
                System.out.println();
				printChangeDP(B, D, k, n, false);
			}
			else
			{
				run = false;
			}
		}
	}


	/* Bottom up solution to change problem */
    public static int[] change_DP(int n, int[] d){
        int[] C = new int[n + 1];
        C[1] = 1;
        int dlen = d.length;
        int[][] A = new int[n][dlen]; /* Stores coin combos for optimal scores, last spot is number of coins */
        A[0][dlen-1] = 1;
        //A[0][dlen] = 1;
        for (int i = 2; i < n+1; i++)
        {
            C[i] = 1 + findMin(C, d, A, i, dlen);
            //A[i-1][dlen] = C[i];
            /* Debug
            // TODO: Fix A array
            System.out.print("A:");
            for (int j = 0; j < dlen; j++)
            {
            	System.out.print(" " + A[i-1][j]);
            }
            System.out.println(" C[" + i + "]: " + C[i]);*/
        }
        /* Debug 
        System.out.println("C array:");
        for(int i = 1; i < n+1; i++)
        {
        	System.out.println(i + ": " + C[i]);
        }*/
        return A[n-1];
    }

    /* Greedy algorithm to solve the change problem */
    public static int[] change_greedy(int n, int[] d){
        int[] results = new int[d.length];
        for(int i = 0; i < d.length; i++){
            results[i] = n/d[i];
            n -= (d[i] * results[i]);
        }
        return results;
    }

    /* Finds the min between */
    private static int findMin(int[] C, int[] d, int[][] A, int j, int dlen)
    {
        int min = C[j-1];
        int ind = dlen-1;
        boolean updated = false;
        for (int i = 0; i < dlen; i++)
        {
        	int temp = j - d[i];
            if (temp > 0)
            {
                if (C[temp] < min)
                {
                	min = C[temp];
                	ind = i;
                	copy(A[j-1], A[temp-1], dlen);
                	updated = true;
                }
            }
            else if (temp == 0) /* Make all other values zero, set coin used to 1 */
            {
            	copy(A[j-1], null, dlen);
            	A[j-1][i] = 1; 
            	return 0;
            }
        }
        if (!updated)
        {
        	copy(A[j-1], A[j-2], dlen);
        }
        A[j-1][ind]++;
        return min;
    }

    /* Function to print the result of the change_DP function*/
	private static void printChangeDP(int[] A, int[] D, int dlen, int n, boolean isDP)
	{
		int starter = 0;
		int count = 0;
		while ((A[starter] == 0) && (starter < dlen))
		{
			starter++;
		}
		if(isDP) {
            System.out.println("DP algorithm results");
        }else{
            System.out.println("Greedy algorithm results");
        }
		System.out.println("Amount: " + n);
		System.out.print("Optimal Distribution: ");
		System.out.print(A[starter] + "*" + D[starter] + "c");
		starter++;
		count += A[starter];
		for (int i = starter; i < dlen; i++)
		{
			if (A[i] != 0)
			{
				System.out.print(" + " + A[i] + "*" + D[i] + "c");;
				count += A[i];
			}
		}
		System.out.println("");
		System.out.println("Optimal coin count: " + count);
	}

	/* Copies some ints from A to B. If B is null A is filled with zeroes */
	private static void copy(int[] A, int[] B, int len)
	{
		if (B == null)
		{
			for (int i = 0; i < len; i++)
			{
				A[i] = 0;
			}
		}
		else
		{
			for (int i = 0; i < len; i++)
			{
				A[i] = B[i];
			}
		}
	}


}



