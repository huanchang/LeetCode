/* Copyright by Huan Chang
 * 
 * This file is using to generate Gray code for non-negative integer
 * 
 * Base on the description of Gray code
 * 1) invert integer to binary string
 * 2) convert binary string a to Gray Code g
 * 		g(i) =  ( a(i) != a(i-1) ); g(0) = a(0)
 * 
 * This code contains Gray code designer, Encoder, Decoder, test module
 * Test module run testNum TIMES WITH randomly generated message
 * Each test: encode message and decode into a decodedCode
 * check if the decodedCode == message
 * Outpur result: "Correct" or "Wrong" 
 * */

package leeCode_Array;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class garyCode {
	
	static int maxInteger = 100;
	static int codeNum = 5;
	static int testNum = 20;
	static int testCodeLength = 10;
	static void test(){
		// Test file for gCode
		
		// create integer array
		int[] array = new int[codeNum];
		
		int max = -1;
		
		// generating letters used as message unit
		Random rand = new Random();
				
		for ( int i = 0; i < codeNum; i++ ){
			
			// Randomly generate an integer
			int a = rand.nextInt(maxInteger);
			
			array[i] = a;
			
			max = a > max ? a : max;
			
		}
		
		// calculate the length of Gray code
		int l = 0;
		int product = 1;
		
		while( product < max ){
			product *= 2;
			++l;
		}
		
		// create boolean array to hole binary string and Gray string
		boolean[] binaryString;
		boolean[] GrayString;
		
		// create a map between integer and Gray code
		
		// Encoder
		Map<Integer, boolean[]> encoder = new HashMap<Integer, boolean[]>();
		// Decoder
		Map< boolean[], Integer> decoder = new HashMap< boolean[], Integer>();
		
		System.out.println("Test Gray code: totally Gray codes " + codeNum +" times");
		
		// Construct Gray Code and build Encoder/Decoder
		for ( int i = 0; i < codeNum; i++ ){
			// print out integer
			System.out.print("Test " + i + ":\tInteger: " + array[i] + "\t");
			
			// convert integer to binary string
			binaryString = Int2Binary ( array[i], l);
			print( binaryString );
			System.out.print("\t");
					
			// generate Gray code
			GrayString = gCode ( binaryString );
			print( GrayString );
			System.out.print("\n");
			
			// add to encoder
			encoder.put( array[i] , GrayString );
			// add to decoder
			decoder.put( GrayString , array[i] );
		}	
		
		System.out.println("Encode/Decode Test: totally run " + testNum +" times");
		
		// Test encoding and decoding process
		int[] testCode = new int[testCodeLength];
		int[] decodedCode;
		boolean[] codeword;
		
		for ( int i = 0; i < testNum; i++ ){
			// print out integer
			System.out.print("Test " + i  + "\t");
			
			// generate original message
			for( int j = 0; j < testCodeLength; j++ ){
				
				testCode[j] = array[ rand.nextInt( array.length - 1 ) + 1 ];
						
			}
			
			//print message
			System.out.print("Original message:");
			printArray( testCode );
			
			
			// Encode message and print out codeword
			codeword = encoderGray ( testCode, encoder, testCodeLength, l);
			System.out.print("\t Codeword:");
			print( codeword );
			System.out.print("\t");
					
			// Decode and print out decoded message
			decodedCode = decoderGray ( codeword, decoder, testCodeLength, l);
			System.out.print("\n Decoded sequence:");
			printArray( decodedCode );
			System.out.print("\t");
			
			// check correctness
			if( check( testCode, decodedCode ) ){
				System.out.print("Correct\n");
			}
			else{
				System.out.print("Wrong\n");
			}
			
			
		}// end for			
	}//end test
	
	static boolean[] encoderGray( int[] message, Map<Integer, boolean[]> coder, int testCodeLength, int l ){
		// Encode integer sequence using Gray code
		boolean[] codeword = new boolean[testCodeLength*l];
		boolean[] newWord = new boolean[l];
		
		for( int i=0; i <testCodeLength; i++ ){
			
			// encode each integer
			newWord = coder.get( message[i] ) ;
			// append to codeword
			for( int j = 0; j < l; j++){
				codeword[ i*l + j ] = newWord[j];
			}
		}
		
		return codeword;
	}
	
	static int[] decoderGray( boolean[] codeword, Map< boolean[], Integer > decoder, int testCodeLength, int l ){
		// Decode Gray codeword into integer sequence
		int[] decodedCode = new int[testCodeLength];
		int newInteger = 0;
		
		for( int i=0; i <testCodeLength; i++ ){
			
			boolean[] newWord = new boolean[l];
			
			// Decode letter by letter, each letter mapped to a binary sequence as a codeword piece
			for( int j = 0; j < l; j++){
				newWord[ j ] = codeword[ i*l + j];
			}
			
			// decode each codeword piece
			try{
				for( boolean[] word : decoder.keySet()){
					// get the word match current codeword piece
					if( checkBoolean( word, newWord ) ){
						newInteger = decoder.get( word);
						break;
					}
				}
			
			}catch(Exception e){
				// print out errors
				e.printStackTrace();
			}
			
			// append to decodedCode
			decodedCode[i] = newInteger;
		}
		
		//return decoded word
		return decodedCode;
	}
	
	static boolean checkBoolean( boolean[] testCode, boolean[] decodedCode ){
		// check whether decode successfully
		// Return true if message sequence equals decoded sequence;
		// otherwise return false
		if ( testCode.length != decodedCode.length ){
			return false;
		}
		for ( int i=0; i < testCode.length; i++ ){
			if( testCode[i] != decodedCode[i] ){
				return false;
			}
		}
		return true;
	}
	
	static boolean check( int[] testCode, int[] decodedCode ){
		// check whether decode successfully
		// Return true if message sequence equals decoded sequence;
		// otherwise return false
		for ( int i=0; i < testCode.length; i++ ){
			if( testCode[i] != decodedCode[i] ){
				return false;
			}
		}
		return true;
	}
	
	static boolean[] Int2Binary( int a, int l ){
		
		// create boolean array
		boolean[] binaryString = new boolean[l];
		
		for( int i = 0; i < l; i++){
			
			binaryString[i] = !( ( a & ( 1<<i )) == 0 );
					
		}
		
		// return array address
		return binaryString;
	}
	
	static boolean[] gCode( boolean[] binaryString ){
		// create boolean array
		int l = binaryString.length;
		boolean[] GrayString = new boolean[l];
		
		for ( int i = 0; i < l; i++){
			// g(0) = a(0)
			if( i==0 ){
				GrayString[i] = binaryString[i];
			}
			else{
				// g(i) = ( a(i) != a(i-1) )
				GrayString[i] = ( binaryString[i] != binaryString[i-1] );
			}
		}
		
		// return array address
		return GrayString;
	}
	
	static void print( boolean[] array ){
		
		// Print binary string of integer, representing by a boolean array
		for( int i = 0; i < array.length ; i++ ){
			if( array[i]==true ){
				System.out.print("1");
			}
			else{
				System.out.print("0");
			}
		}
		
	}// end print
	
	static void printArray( int[] array ){
		
		// Print binary string of integer, representing by a boolean array
		for( int i = 0; i < array.length ; i++ ){
			System.out.print( array[i] );
		}
		
	}// end print
	
	
	public static void main(String[] args) {
		// run test function: construction Gray code and test encoding/decoding correctness validation
		test();
	}

}
