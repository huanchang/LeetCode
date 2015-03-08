import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;


public class application {

static void selectSort( int[] array ){
		
		// select sort
		for(int i=0; i<array.length; i++){
			
			int temp = array[i];
			
			int j = i;
			
			for(; j>0 ; --j){
				
				if( temp < array[j-1] ){
					
					array[j] = array[j-1];
					
				}				
				else{					
					break;
				}
				
			}//end for j
			
			array[j] = temp ;
			
		}// end for i
		
	}
	
	static int binarySearch( int[] array, int target ){
		
		// Binary search for integer array in increasing order
		int min = 0, max = array.length - 1;
		
		while( min <= max ){
			
			int middle = ( max - min ) / 2 + min;
			
			// get found
			if( array[middle] == target ){
				return middle;
			}
			else if( array[middle] < target ){
				// right part binary search
				min = middle + 1;
			}
			else{
				// left part binary search
				max = middle - 1;
			}
			
		}
		
		return -1;
	}
	
	static Vector<Integer> SumSearch( int[] array ){
		/* This function is used to find out that in an unsorted array
		 *  Is there a subset with three elements gives a sum at 0.
		 * The worse case is iteratively visited every possible subsets to see if there's any 
		 * satisfies requirement
		 * 
		 * Here, the array is sorted firstly
		 * Then, we loop first two element one by one. but for the third one use binary search
		 * 
		 * */
		// select sort of array
		selectSort( array );
		
		Vector<Integer> subset = new Vector<Integer>(); 
		
		// iteratively search
		for(int i = 0; i< array.length; i++ ){
			
			//loop for second element
			for( int j = i + 1; j < array.length; j++){
				
				// binary search if there's a third element in the array could let the equation valid
				int index = binarySearch( array, - ( array[i] + array[j] ) );
				
				// store the found subset into subset Set
				if( (index >= 0) && ( index > i ) && ( index > j ) ){
					subset.addElement( array[i] );
					subset.addElement( array[j] );
					subset.addElement( array[index] );
				}// end if
				
			}//end loop for j
			
		}// end loop for i
		
		return subset;
	
	}//end 3Sum

	
	
	public static void main( String[] args ){
		
		/*CopyRight by Huan Chang. This is a solution for LeetCode 
		 * Find the subsets with 3 elements gives sum at 0
		 * Time complexity is O(n^2*log(n))
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
		
		// check out all subset with 3 elements gives sum at 0
		
		Vector<Integer> subset = SumSearch( array );
		
		if ( subset.isEmpty() == true ){
			// no subsets found
			System.out.println("No matched subset found.");
		}
		else{
			// Print out all found subsets
			int index = 1;
			
			System.out.println("Totally " + subset.size()/3 + " subsets found.");
			
			for( int i : subset){
				System.out.print(i);
				if ( index%3 == 0 ){
					System.out.print("\n");
				}
				else{
					System.out.print(", ");
				}
				
				++index;
				
			}//end for i
				
		}
		
		// close scanner
		input.close();
		
	}// end main

}
