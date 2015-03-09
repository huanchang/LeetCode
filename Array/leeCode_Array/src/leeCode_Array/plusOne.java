package leeCode_Array;

import java.util.Random;
import java.util.Vector;
import java.util.Scanner;

public class plusOne {
	
	// create Scanner
	
	static Scanner input = new Scanner( System.in );
	
	static Vector<Integer> generateArray( int n ){
		// create new array
		Vector<Integer> array = new Vector<Integer>();
		
		// create a random interface
		Random rand = new Random();
		
		// add digits one by one
		for( int i = 0; i < n; i++){
			
			array.add( rand.nextInt(9) );
			
			if( ( i == 0 ) && ( array.get(0) == 0 ) ){
				array.insertElementAt( rand.nextInt(9) , 0 );
			}
		}
		
		return array;
	}//end generateArray
	
	static void test( int n ){
		// Test code for addByOne
		
		// Test 1: general test
		System.out.println("Test case 1: general random test:" );
		// generate a random digit
		Vector<Integer> array = generateArray( n );
				
		// add one to the number
		addByOne( array );
						
		array.clear();
				
		// Test 2: extreme test	999...9	
		for( int i = 0; i < n; i++ ){
			array.add( 9 );
		}
						
		// add one to the number
		addByOne( array );
						
		// Test 3: empty test  0
		array.clear();
		addByOne( array );
		
		
	}
	
	static void addByOne( Vector<Integer> array ){
		// add one to a number stored in a vector
		
		// Print array
		System.out.println("\tOriginal number: " );
		print(array);
		
		int d = 1, n = array.size();
		
		for( int i = n-1; i >= 0; i--){
			
			if( d==0 ){
				break;
			}
			
			int v = d + array.get( i );
			
			array.set( i, v%10 );
			
			d = v / 10;
		}
		
		// add an extra value in front
		if( d > 0 ){
			array.add(0, d);
		}
		
		// Print result number
		System.out.println("\tResult number: " );
		print(array);
		
	}// end

	static void print( Vector<Integer> array ){
		
		for(int i : array){
			System.out.print( i );
		}
		System.out.print("\n");
		
	}// end print
	
	public static void main(String[] args) {
		/* precisely add one to a number
		 * Number is represented by an array of digits
		 * */
		System.out.println("Enter the length of array:");
		
		int n = input.nextInt();
		
		test( n );
		
		// close in stream
		input.close();
		
	}

}
