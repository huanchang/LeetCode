package leetcodeArrayLongestConsecutive;

import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

public class application {
	
	static int longestConseSequenve( int[] array ){
		// find the length of the longest consecutive sequence
		int l = 0, maxL = 0;
		
		// Hash table is used to mark elements that one element is just visited once
		Hashtable<Integer, Boolean> used = new Hashtable<Integer, Boolean>();
		
		for( int i = 0; i < array.length; i++ ){
			used.put( array[i], false );
		}
		
		// what is set? difference between collection/array...
		Set<Integer> keySet = used.keySet();
		
		for (int i : keySet){
			// find the consecutive sequence including current i
			if ( used.get( i ) ){
				continue;
			}
			// reset length  of current sequence
			l = 1;
			
			// loop to find element larger than i and consecutive connected
			for( int j = i + 1 ; used.containsKey(j)==true; j++ ){
				// increase length by 1
				++l;
				// mark element as traveled
				used.put( j, true );
			}
			
			// loop to find element smaller than i and consecutive connected
			for( int j = i - 1; used.containsKey(j)==true; j--){
				// increase length by 1
				++l;
				// mark element as traveled
				used.put( j, true );
			}
			
			// Update longest sequence length
			maxL = l>maxL? l : maxL;
			
		}
		
		// return the length of longest sequence
		return maxL;	
	}
	
	public static void main( String[] args ){
		
		/*CopyRight by Huan Chang. This is a solution for LeetCode 
		 * Find the longest consecutive sequence in unsorted integers array
		 * Time complexity is O(n)
		*/
		
		Scanner input = new Scanner( System.in );
		
		//Prompt
		System.out.println("Enter the length of array: ");
		// wait for user input
		int n = input.nextInt();
		
		// create an array to hold integers
		int[] array = new int[n];
		
		//Prompt
		System.out.println("Enter elements: ");
		
		for( int i = 0; i < n; i++){
					
			// wait for user input
			array[i] = input.nextInt();
			
		}
		
		// find the longest consecutive sequence
		System.out.println("The longest consecutive sequence is of length: " + longestConseSequenve( array ) );
		
		// close scanner
		input.close();
		
	}// end main
	
}
