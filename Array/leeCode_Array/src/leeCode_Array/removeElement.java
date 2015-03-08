package leeCode_Array;


import java.util.InputMismatchException;
import java.util.Scanner;

public class removeElement {
	//Initialize input stream used
	
	static Scanner input = new Scanner( System.in );
	
	static void cleanUp(){
		// close input stream
		input.close();
	}
	
	static public int[] createArray(){
		
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

		return array;
		
	}
	
	static public int removeE( int[] array, int a){
		// remove all elements with value a
		int newLength = 0;
		
		for( int i = 0; i < array.length; i++){
			
			// first element always remain
			if( array[i] != a ){
				if( i != newLength ){
					array[newLength] = array[i];
				}
				++newLength;
			}
			
		}// end for
		
		return newLength;
			
	}
	
	static public void print( int[] array, int n ){
		
		// loop to print out all elements
		for( int i=0; i < n; i++ ){
			
			System.out.print( array[i] );
			
			// print out separator
			if( i != n - 1 ){
				System.out.print(", ");
			}
			else{
				System.out.print("\n");
			}//end if
			
		}//end for
		
	}
	
	
	static public void main( String[] args){
		/*CopyRight by Huan Chang. This is a solution for LeetCode 
		 * Remove elements with a value
		 * Time complexity is O(n)
		*/
		// Create Integer array 
		int[] array = createArray();
		
		// Prompt
		System.out.println("Enter a value to be removed: ");
		
		int a = 0;
		
		// wait user input the value to be eliminated 
		try{
			a = input.nextInt();
		}catch( InputMismatchException e){
			e.printStackTrace();
		}
		
		// remove all elements with value a
		int length = removeE( array, a );
		
		// Print new array
		print( array, length);
		
		// Close input stream
		cleanUp();
		
	}// end main
	
}
