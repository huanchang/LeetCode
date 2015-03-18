#include"leetCodeHeader.h"

/*
	Copyright by Huan Chang
	Convert roman to int number
	Need leetCodeHeader.h file
*/

class Solution{
public:
	int c2n( const char c ){
		// convert single roman character into integer
		switch( c ){
			case 'I': return 1;
			case 'V': return 5;
			case 'X': return 10;
			case 'L': return 50;
			case 'C': return 100;
			case 'D': return 500;
			case 'M': return 1000;
			default: return 0;
		}
	}
	int roman2Int( std::string roman ){
		int result = 0;
		// loop to convert each roman letter
		for( unsigned i = 0; i < roman.length(); ++i ){
			if ( i > 0 ){
				// care about roman numbers like IV=> V - I = 4
				result += ( c2n( roman[i] ) > c2n( roman[ i-1 ] ) )? ( c2n( roman[i] ) - 2 * c2n( roman[i-1] ) ) : c2n( roman[i] );
			}
			else{
				result += c2n( roman[i] );
			}
			if ( result < 0 ){
				std::cout<<"Out of range of integer."<<std::endl;
				return 0;
			}
		}
		
		return result;
	}

	std::string int2Roman(const int n ){
		// const value cannot be changed
		// convert a integer to roman number
		int integer[] = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		std::string roman[] = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
		std::string result = "";
		int v = n, i = 0;
		while( v > 0 ){
			// loop to get every roman from largest to smallest
			
			while( ( v < integer[i] ) && ( i < 13 ) ){
				// find next integer smaller than or equal to n
				++i;
			}
			for( int j = 0; j < ( v / integer[i] ); ++j ){
				//get roman[i] for n/integer[i] times
				result += roman[i];
			}
			//update n value
			v %= integer[i];
		}
		return result;
	}

};

void test(){
	// generate roman number sequences to test performance
	// roman number is stored in string
	std::string testRoman[4];
	testRoman[0] = "IIIIVV";
	testRoman[1] = "IIVXXLM";
	testRoman[2] = "";
	testRoman[3] = "XXXCCCCDDMM";
	Solution *mySolution = new Solution;
	for( int i = 0; i < 4; ++i ){
		int a = mySolution->roman2Int( testRoman[i] );
		std::cout<<"Test case "<< i << " : "<<testRoman[i]<< " => "<< a ;
		std::cout<<" => "<< mySolution->int2Roman( a )<< std::endl;
	}

}

int main(){
	// test the performance with a test function
	test();
	return 0;
}