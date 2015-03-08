package leeCode_Array;

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class nextPermutation {

	static Scanner input = new Scanner( System.in ); 
	
	
	static int[] generateArray( int n ){
		/* generate an array with n elements from 1 to n
		 * Then, shuffle elements and get a random permutation
		 * */
		
		// use a set to store all unused integer
		Vector<Integer> current = new Vector<Integer>();
		for( int i=1; i<=n; i++){
			current.add(i);
		}
		
		// loop to generate a permutation
		int i = 0;
		
		int[] array = new int[n];
		
		Random rand = new Random();
		
		while( i < n ){
			// get a random index of element in current
			int index;
			
			if ( current.size() == 1 ){
				index = 0;
			}
			else{
				index = rand.nextInt( current.size() - 1 ) + 1;
			}
			
			// append this element into array
			array[i] = current.get( index );
			
			// remove this element after added into array
			current.remove( index );
			
			//update i
			++i;
			
		}
		
		return array;
		
	}
	
	static void nextP( int[] array ){
		/* get the next permutation of this sequence
		 * Time complexity is O(n)
		 * */
		
		int start = array.length - 2;
		int end = array.length - 1;
		
		// falg: true if there's a bit found could be swap
		boolean flag = false;
		
		do{
			
			if( array[start] < array[start+1] ){
				flag = true;
			}
			else{
				--start;
			}
			
		}while( ( start >= 0 ) && ( flag == false ) );
		
		for( int i = end; i >= 0; i-- ){
			// find the first element larger than array[start]
			if( array[i] > array[start] ){
				// swap
				int temp = array[i];
				array[i] = array[start];
				array[start] = temp;
				
				break;
			}
		}
		
		++ start;
		
		while( start < end ){
			
			//swap
			int temp = array[start];
			array[start] = array[end];
			array[end] = temp;
			
			++start;
			--end;
		}
		
		printArray( array );
		
	}//end nextPermutation
	
	static void printArray( int[] array ){
		
		for( int i = 0; i < array.length; i++ ){
			// print integer
			System.out.print( array[i] );
			
			// print separator
			if( i < array.length - 1 ){
				System.out.print(", ");
			}
			else{
				System.out.print("\n");
			}//end if
			
		}//end for
		
	}//end printArray
	
	static void cleanUp(){
		//close in stream
		input.close();
	}
	
	public static void main(String[] args) {
		/* Copyright by Huan Chang
		 * Leetcode Array: find next permutation
		 * */
		
		// Prompt
		System.out.println("Enter the length of array: ");
		
		// wait for input
		int n = 0; 
		
		try{
			n = input.nextInt();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			// get a random permutation
			int[] array = generateArray( n );
			
			// print array
			printArray( array );

			// get next permutation			
			nextP( array );
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		// close all in stream
		cleanUp();
		
	}// end main

}
