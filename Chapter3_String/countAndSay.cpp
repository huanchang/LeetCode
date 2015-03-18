#include"leetCodeHeader.h"

// Copy right by Huan Chang
// Solution for leetcode problem count and say
// sequence begin as : 1, 11, 21, 1211, 111221,...
// calculate the nth sequence
// Finished in 19 minutes

class Solution{
public:
	std::string countAndSay( int n ){
		std::string s= "1";
		std::string nextString = "";
		if( n > 0 ){
			for( int i = 0; i < n ; ++i ){
				// get the next sequence
				char count = '1', prev = 0;
				// from left to check each number
				for( unsigned j = 0; j < s.length(); ++j ){
					// add count by one if the next letter is same as current letter
					if( ( j < s.length() - 1 ) && ( s[j] == s[j+1] ) ){
						++count;
					}
					else{
						// not same or reach the end
						nextString.push_back( count );
						nextString.push_back( s[j] );
						count = '1';
					}
				}// end for j
				// update string s
				s = nextString;
				// clear up next string 
				nextString.clear();
			}// end for i
		}// end if
		else{
			std::cout<<"Invalid input n value. Should be a positive integer."<< std::endl;
		}
		return s;
	}// end countAndSay
};

void test(){
	// generate test cases
	int testNum = 10;
	Solution *mySolution = new Solution;
	for ( int i = 0; i < testNum; ++i ){
		int testCase = rand()%10;
		std::cout<< "Test case "<< i<<": "<<testCase<<"th sequence => "<<mySolution->countAndSay( testCase )<<std::endl;
	}

}// test

int main( void ){
	test();
	return 0;
}