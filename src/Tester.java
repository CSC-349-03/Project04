/*  David Twyman, Andrew LeDawson
 **  dtwyman@calpoly.edu, aledawson@calpoly.edu
 **  CSC 349-03
 **  Project 4
 **  2-23-2018
 */

public class Tester {
	public static void main(String[] args)
	{
        int sets[][] = {{100, 50, 25, 10, 5, 1}, {100, 50, 20, 15, 10, 5, 3, 2, 1}, {64, 32, 16, 8, 4, 2, 1}, {100, 50, 25, 10, 1}, {66, 35, 27, 18, 10, 1}};
        int[] matches = new int[sets.length];
        System.out.println("Testing change_DP and change_greedy algorithms");
        for(int i = 0; i < sets.length; i++) { // Iterate through each coin set
            for (int j = 1; j <= 200; j++) { // Iterate through every change amount up to 200
                int[] dynamicResult = ChangeMaker.change_DP(j, sets[i]);
                int[] greedyResult = ChangeMaker.change_greedy(j, sets[i]);
                if(areSame(dynamicResult, greedyResult)){
                    matches[i]++;
                }
            }
        }
        for(int set = 0; set < sets.length; set++) {
            System.out.println("Testing set " + (set + 1) + ": " + matches[set] + " matches in 200 tests");
        }
	}

	private static boolean areSame(int[] a, int[] b){
	    if(a.length != b.length){
	        return false;
        }
        for(int i = 0; i < a.length; i++){
	        if(a[i] != b[i]){
	            return false;
            }
        }
        return true;
    }
}
