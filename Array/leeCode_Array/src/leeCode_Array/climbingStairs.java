/* Copyright by Huan Chang.
 * 
 * Code for leetcode problem: climb stairs
 * implemented base on the idea of Fabonacci number 
 * 
 * */


package leeCode_Array;

import java.util.Scanner;

public class climbingStairs {

	static int fabonacci( int n ){
		// calculate the nth Fabonacci number
		/* Fabonacci number 0, 1, 1, 2,...*/
		if( n == 0 ){
			return 1;
		}
		else if ( n == 1){
			return 1;
		}
		else{
			return fabonacci( n-1 ) + fabonacci( n - 2 );
		}
		
	}
	
	public static void main(String[] args) {
		/* This problem is to calculate # of possible ways to climb n stairs
		 * At each stair, you can climb 1 or 2 steps. No go back.
		 * 
		 * This calculation is the same as Fabonacci number
		 * f(n) = f(n-1) + f(n-2)
		 * 
		 * */
		// create a in stream
		Scanner input = new Scanner( System.in );
		
		//Prompt
		System.out.print("Enter a positive integer: ");
		
		try{
			// wait for user input
			int n = input.nextInt();
			
			// calculate # of ways
			System.out.println(" There are " + fabonacci(n) + " possible ways to climb " + n + " stairs.");
		}
		catch( Exception e){
			e.getStackTrace();
		}
		// close in stream
		input.close();
	}

}
