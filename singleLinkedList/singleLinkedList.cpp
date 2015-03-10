#include"numberLinkedList.h"

#define TESTNUM 20
#define MAXLENGTH 20
#define MAXNUM 9

void test(void);

int main(void){
	test();
}

void test(){
	// test the linked list structure and functions
	int n;// length of array from 1 to 20
	int lowBound = 3, highBound = 7;
	
	//test run TESTNUM times
	for( int i = 0; i < TESTNUM; i++ ){
		std::cout<<"Test "<< i<<": ";

		// get a random lenth
		n = rand()%( MAXLENGTH - 1 ) + 1;
		
		// create a new linkedlist
		intLinkedList *myList = new intLinkedList;

		for( int j = 0; j < n; j++ ){
			myList->frontInsert( rand()%MAXNUM );
		}
		
		myList->print();
		
		std::cout<<"\tAfter reverse between "<<lowBound<<" and "<< highBound<< " : ";
		if( !myList->reverse( 3, 7) ){
			std::cout<<"Failed."<<std::endl;
		}
		else{
			myList->print();
			std::cout<<" Successfullly reversed."<<std::endl;		
		}

		// sort the linkedlist in ascending order
		std::cout<<"\t After sorted: ";
		myList->sort();

		// remove duplicate nodes
		//myList->eliminateDuplicateLeaveFirst();

		// remove all nodes has duplicates
		myList->eliminateDuplicateALL();

		// separate each test
		std::cout<<"\n *************"<<std::endl;

		// delete the linkedlist
		delete myList;

	}

}
