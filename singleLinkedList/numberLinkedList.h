#include<stdio.h>
#include<stdlib.h>
#include<iostream>

struct listNode{
	// Node value
	int value;
	
	// Pointer to next node
	listNode *next;

	// Initialize listNode
	listNode( int v ): value(v), next(NULL){};
};

class intLinkedList{
private:
	listNode *head;
	listNode *tail;
public:
	intLinkedList(): head(NULL), tail(NULL){};
	void backInsert( int );		// insert a new list node at the end
	void frontInsert( int );	// insert a new list node with value v at head
	void deleteWithValue( int );// delete nodes with value v
	int searchFirst( int );// search for node with value at v and return the first index
	bool reverse(int, int);// reverse nodes between m and n
	void sort();
	bool eliminateDuplicateLeaveFirst();// remove nodes with duplicated value in a sorted list but the first node with the value
	bool eliminateDuplicateALL();// remove all nodes with duplicated value in a sorted list
	bool rotate( int );// rotate list to right by k places
	void print(void);// print nodes value from head to tail
};

void intLinkedList::backInsert( int n ){
	// insert a new node with value n at the tail

	// create a new node
	listNode *newNode = new listNode(n);
	
	// insert into the linked list
	if( !head ){
		// if the list is empty
		head = newNode;
		tail = head;
	}
	else{
		// insert at the end of the last node
		tail->next = newNode;
		tail = tail->next;
	}// end if

}// end backFront

void intLinkedList::frontInsert( int n ){
	// insert a new node with value n in front of the head

	// create a new node
	listNode *newNode = new listNode(n);
	
	// insert into the linked list
	if( !head ){
		// if the list is empty
		head = newNode;
		tail = head;
	}
	else{
		// insert in front of the first node
		newNode->next = head->next;
		head->next = newNode;
	}// end if
}

void intLinkedList::deleteWithValue( int v ){
	// delete all nodes with value v
	listNode *np, *npPre = head;
	
	if( head ){
		if ( npPre->value == v ){
			// move head pointer to next one
			head = head->next;
			// delete the node
			delete npPre;
		}
		else{
			np = npPre->next;
			// loop from the first node to end
			while( np ){
				if( np->value == v ){
					// found a node with value v
					if( np == tail){
						tail = npPre;
					}
					// update next pointer for the previous node
					npPre->next = np->next;
					// delete node
					delete np;
				}
			}
			
		}
	}	
}

bool intLinkedList::reverse( int m, int n ){
	// reverse nodes between m and n the Linked List
	if ( (m >= n)||(!head)){
		return false;
	}
	else{
		int count = 1;
		listNode *fnp, *fnpBefore, *lnp;
		// find the mth node
		fnp = head;
		while( ( count < m )&&( fnp ) ){
			fnpBefore = fnp;
			fnp = fnp->next;
			++count;
		}
		// if there's less than m nodes
		if( !fnp ){
			return false;
		}

		
		// find the nth node
		lnp = fnp;
		while( ( count < n )&&( lnp ) ){
			lnp = lnp->next;
			++count;
		}
		// if there's less than n nodes
		if( !lnp ){
			return false;
		}
		
		// from the mth to (n-1)th node, insert after the nth node
		while( fnp != lnp ){
			// update the next pointer of the previous node
			fnpBefore->next = fnp->next;
			fnp->next = lnp->next;
			lnp->next = fnp;
			fnp = fnpBefore->next;
		}
		return true;
	}//end if m<=n
}

void intLinkedList::sort(){
	// sort value in ascending order
	if( head ){
		// selection sort if list is not empty
		listNode *npCurr, *npNext, *npPrevious;
		npNext = head;
		npPrevious = head;
		while( npNext ){
			// loop to find the point to insert
			npCurr = head;
			while( npCurr != npNext ){
				// insert the next node after if current node is smaller than next node && next node of current node is larger than it
				if( npNext->value <= head->value ){
					npPrevious->next = npNext->next;
					npNext->next = head;
					head = npNext;
					npNext = npPrevious;
					break;
				}
				else if( ( npCurr->value <= npNext->value ) && (npCurr->next->value >= npNext->value) &&( npCurr->next != npNext ) ){
					
					npPrevious->next = npNext->next;
					npNext->next = npCurr->next;
					npCurr->next = npNext;
					npNext = npPrevious;
					break;
				}
				npCurr = npCurr->next;
			}
			// move pointer to the next node
			npPrevious = npNext;
			npNext = npNext->next;
		}// end while

	} //end if

	print( );
}

bool intLinkedList::eliminateDuplicateLeaveFirst(){
	// remove nodes with duplicated value in a sorted list but the first node with the value
	
	if( head==NULL ){ return true; }
	
	// create two pointers
	listNode *npCurr=head;

	// remove duplicate nodes in one pass
	while( npCurr->next ){

		//check if a node has the same value with its next node, then remove the next node
		// Found duplicate node
		if( npCurr->next->value == npCurr->value ){
			// hold the next next node( new next node)
			listNode *temp = npCurr->next;
			// change next pointer to new next node
			npCurr->next = temp->next;
			delete temp;
			//remove the node
		}
		else{
			//move to next node
			npCurr = npCurr->next;
		}
	}
	print();
	return true;
}
	
bool intLinkedList::eliminateDuplicateALL(){
	// remove all nodes with duplicated value in a sorted list
		
	if( head==NULL ){ return true; }
	
	// create two pointers
	listNode *npCurr=head, *npPrevious = head;
	
	//create a bool flag to mark if current node has duplicates
	bool flag = false;

	// remove duplicate nodes in one pass
	while( npCurr ){
		listNode *npNext = npCurr;
		while( npNext->next ){
			//check if a node has the same value with its next node, then remove the next node
			// Found duplicate node
			if( npNext->next->value == npCurr->value ){
				// hold the next next node( new next node)
				listNode *temp = npNext->next;
				// change next pointer to new next node
				npNext->next = temp->next;
				// delete duplicate node
				delete temp;
				// change the flag status to true: current node has duplicates
				flag = true;
			}//end if
			else{
				break;
			}
		}//end while npCurr->next

		// check if the current node need to be removed
		if( flag == true ){
			// if marked as duplicate, then remove current node
			if( npCurr == head){
				// if it is the first node, the change head
				head = npCurr->next;
				// remove and delete the node
				delete npCurr;
				npCurr = head;
				npPrevious = head;
			}
			else{
				npPrevious->next = npCurr->next;
				delete npCurr;
				npCurr = npPrevious->next;
			}// end if

			flag =false;

		}//end if flag==true
		else{
			// move to the next node
			npPrevious = npCurr;
			npCurr = npCurr->next;
		}
		

		

	}
	print();

	return true;
}
	
bool intLinkedList::rotate( int ){
	// rotate list to right by k places
	return true;
}




int intLinkedList::searchFirst( int v ){
	//search the first node with value v and return its index
	// return -1 if not found

	int index = 0;
	
	//create an iterator pointer and assign to the head
	listNode *np = head;
	
	// loop to the end of the list
	while( np ){
		if( np->value == v ){
			//return the index of the first node with value v
			return index;

		}

		// update pointer to next node
		np = np->next;
		// add index by one
		++index;
	}
	// not found, return -1
	return -1;
}

void intLinkedList::print(){
	listNode *np = head;
	while( np ){
		std::cout<<np->value;
		np = np->next;
		if(np){
			std::cout<<", ";
		}
		else{
			std::cout<<std::endl;
		}
	}
}