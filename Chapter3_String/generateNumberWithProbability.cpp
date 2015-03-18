#include"leetCodeHeader.h"

// Copyright by Huan Chang
// Generate true or false according to a given probability
// Assume there's a function you can use which alway return false or true at 0.5 rate
// All probability below 0 would return with false,
// and all probability beyond 1 would return with true.

class Solution{
public:
	bool rateHalf(){
		// function which return true at probability 0.5
		return rand()%2 == 0 ? true : false;
	}

	bool generateWithProbability( double p ){
		// generate bool value: true or false with given probability
		// with probability p to return true
		if( p <= 0 ){
			// this is excluded from true cases
			return false;
		}
		if( p >= 1 ){
			return true;
		}
		
		// take case of all true cases
		if( p < 0.5 ){
			// p is smaller than 0.5
			if( rateHalf() ){
				// with 0.5* ( p*2 ) probability to be true
				return generateWithProbability( p * 2 );
			}
			else{
				return false;
			}// end if p< 0.5
		}
		else{
			// p is higher or equal to 0.5
			if ( rateHalf() ){
				return true;
			}
			else{
				return generateWithProbability( p*2 - 1 );
			}// end if p >= 0.5

		}// end else

	}//end generateWithProbability

};


void test(){
	// test the performance of generate bool with given probability
	
	// test times
	int testNum = 10;	// Number of general test cases
	int randomNum = 100000;	// Number of samples used to test random generator performance
	Solution *mySolution = new Solution;

	// Extreme case test
	std::cout<<"Extreme test:"<<std::endl;
	int count = 0;
	for ( int i = 0; i < 3; ++i ){
		double rate = i - 1;
		for( int j = 0; j < randomNum; ++j ){
			if( mySolution->generateWithProbability( rate ) ){
				++count;
			}
		}
		std::cout<<"Test case "<< i << ": rate = " << rate << ", result = " << ( (double) count) / ( (double) randomNum ) << std::endl;
	}
	
	// General random test: loop to test
	std::cout<<"General random test:"<<std::endl;
	
	for( int i = 0; i < testNum; ++i ){
		double rate = ( rand()%100 ) / 100.0;
		count = 0;
		// test the random generator performance over a large number of samples
		for( int j = 0; j < randomNum; ++j ){
			if( mySolution->generateWithProbability( rate ) ){
				++count;
			}
		}
		std::cout<<"Test case "<< i << ": rate = " << rate << ", result = " << ( (double) count) / ( (double) randomNum ) << std::endl;
	}


}// end test

int main(){
	test();
}