/*Copyright by Huan Chang
 * SOlution for leetcode: Candy
 * Find the minimum candy for children
 * 
 * 1). loop from left to right
 * 2). loop from right to left
 * run in O(n)
 * 
 * */

package leeCode_Array;

import java.util.Scanner;
import java.util.Random;

public class candy {
	// define the maximum value of rating value
	static int maxRaing = 100;
	// define # of tests
	static int testNum = 10;
	
	// create scanner
	static Scanner input = new Scanner( System.in );
	
	static void test( int n ){
		// test giveCandy program
		
		for( int i = 0; i < testNum; i++){
			System.out.println("Test " + i + " : ");
			
			//create rating array
			int[] rating = generateRating( n );
			
			int[] candy = giveCandy( rating );
			
			check( rating, candy );
			
		}// end for
		
		
	}//end test
	
	
	static int[] generateRating( int n ){
		//create rating array
		int[] rating = new int[n];
				
		// generate raing value array for children
		Random rand = new Random();
		
		for( int i = 0; i < n; i++ ){
			// get a random number from 1 to maxRating
			rating[i] = rand.nextInt( n - 1 ) + 1;
		}
		
		// print array
		print( rating );
		
		// return rating array
		return rating;
	}
	
	static int[] giveCandy( int[] array ){
		
		int n = array.length;
		
		// create array for children's candy
		int[] candy = new int[n];
		
		// loop from left to right, O(n)
		for( int i = 0; i < n; i++){
			// candy[0] = 1;
			if( i==0 ){
				candy[i] = 1;
			}
			else if( array[i] > array[i-1] ){
				// larger than left neighbor, then have one more candy
				candy[i] = candy[i-1] + 1;
			}
			else{
				// smaller than or equal to left neighbor
				candy[i] = 1;
			}// end if
			
		}//end for i
		
		// loop from right to left, O(n)
		for( int i = n - 2 ; i >= 0 ; i--){
			// candy[0] = 1;
			if( array[i] > array[i + 1] ){
				// larger than right neighbor, then have one more candy
				candy[i] = candy[i] > ( candy[i+1] + 1 )? candy[i]: candy[i+1]+1;
			}
					
		}//end for i
		
		// print # of candies for each child
		print( candy );
		
		// return candy array
		return candy;
	}
	
	static void check( int[] rating, int[] candy){
		// Check the correctness of candy distribution
		// "Correct" if no child get less candy than the neighbor with lower rating
		if( rating.length != candy.length ){
			System.out.println(" Wrong: not equal length.");
		}
		else{
			// loop to check all children with their neighbor
			for( int i = 1; i < rating.length; i++ ){
				if( ( ( rating[i]> rating[i-1] )&&( candy[i] < candy[i-1] ) )||( ( rating[i]> rating[i-1] )&&( candy[i] < candy[i-1] ) ) ){
					System.out.println("Wrong");
				}
			}
			System.out.println("Correct");
		}
		
	}
	
	static void print( int[] array ){
		// loop to print all value
		for( int i = 0; i < array.length; i++ ){
			System.out.print( array[i] + " " );
		}
		System.out.println(" ");
	}
	
	public static void main(String[] args) {
		// Main program for candy
		
		// prompt
		System.out.println("Enter the number of children:");
		
		int n = input.nextInt();
		
		test( n );
		
		input.close();
	}

}
