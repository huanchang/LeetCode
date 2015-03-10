package leeCode_Array;

import java.util.Scanner;
import java.util.Vector;

public class kthPermutation {

	static Scanner input = new Scanner( System.in ); 
	
	
	static int[] generateArray( int n ){
		/* generate an array with n elements from 1 to n in ascending order
		 * */
		int[] array = new int[n];
		
		// iteratively create array from 1 to n
		for( int i=0; i<n; i++){
			// add a new value
			array[i] = i + 1;
		}
		
		// return array address
		return array;
		
	}//end generateArray
	
	static int factor( int n ){
		// calculate factor of n!, time complexity is O(n)
		
		int result = 1;
		
		for( int i = 1; i <= n; i++){
			result *= i;
		}
		
		// return result
		return result;
		
	}
	
	static int[] kthP( int[] array, int k ){
		/* get the kth permutation of this sequence
		 * Time complexity is O(n)
		 * 
		 * Cantor theory: kth permutation = {a1, a2, ..., an}
		 * a1 = k/(n-1)!
		 * k2 = k % (n-1)!
		 * a2 = k2/(n-2)!
		 * ....
		 * */
		
		// length of array
		int n = array.length;
		
		// create result array
		int[] result = new int[n];
		
		// create a vector to hold elements
		Vector<Integer> a = new Vector<Integer>();
		
		for( int i = 0; i < n; i++){
			a.add( array[i] );
		}
		
		// calculate factor n!
		int f = factor( n );
		
		// Loop to add all elements according to Cantor theory
		for( int i = 0; i < n-1; i++){
			
			// Update f and k
			f /= n - i ;
			
			// ai = ki / fi;  ki = k%(n-i)!; fi = (n-i-1)!
			array[i] = a.get( ( k % ( f*(n-i) ) ) / f );
			
			a.remove( ( k % ( f*(n-i) ) ) / f );
					
		}// end for
		
		// add last element an = 0
		array[n-1] = a.get(0);
		
		a.clear();
		
		return result;
		
	}//end kthP
	
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
		 * Leetcode Array: find kth permutation
		 * */
		
		// Prompt
		System.out.println("Enter the length of array: ");
		
		// wait for input
		int n = 0, k = 0; 
		
		try{
			n = input.nextInt();
			
		}
		catch(Exception e){
			// catch and printout errors
			e.printStackTrace();
		}
		
		// Prompt
		System.out.println("Enter the value of k: ");
		try{
				k = input.nextInt();
					
		}
		catch(Exception e){
			// catch and printout errors
				e.printStackTrace();
		}
		
		// calculate kth permutation
		try{
			// get a random permutation
			int[] array = generateArray( n );
			
			// print array
			printArray( array );

			// get next permutation			
			kthP( array, k-1 );
			
			// print kth permutation array
			printArray( array );
			
		}
		catch(Exception e){
			// catch and printout errors
			e.printStackTrace();
		}
		
		// close all in stream
		// make sure do not close befor any input.nextInt()
		cleanUp();
		
	}// end main

}
