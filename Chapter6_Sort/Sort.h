/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    void merge(int A[], int m, int B[], int n) {
        // leetCode problem 88: in place merge. time complexity: O(m+n)
        // merge two array into one. Array A is large enough to hold all elements.

        // Use two iterator to compare elements from back to front
        int i = m - 1, j = n - 1, k = n + m - 1 ;
        while( i >= 0 && j >= 0 ){
            if( A[i] >= B[j] ){
                A[k] = A[i];
                --i;
            }
            else{
                A[k] = B[j];
                --j;
            }
            --k;
        }

        // Move rest elements, if there's elements in A not considered
        // Then, no need to move. Otherwise, copy rest elements in B into A
        while( j >= 0 && k >= 0 ){
            A[k] = B[j];
            --j;
            --k;
        }
    }

    ListNode* mergeTwoLists(ListNode *l1, ListNode *l2) {
        // LeetCode 21: merge two sorted linked list together
        // time complexity: O( m + n )
        // if there's one or two linked list is empty, then no need to sort, just return the head of the other one

        if( l1 == nullptr ){
            return l2;
        }
        if( l2 == nullptr ){
            return l1;
        }
        
        // Iteratively from front to end, each time get the smaller number in two lists
        ListNode *head = nullptr, *p, *next;

        while( l1 && l2 ){
            
            if ( l1->val <= l2->val ){
                next = l1;
                l1 = l1->next;
            } 
            else {
                next = l2;
                l2 = l2->next;
            }

            // Get the first element
            if( head == nullptr ){
                head = next;
                p = head;
            }
            else{
                p->next = next;
                p = p->next;
            }
            
        }
        
        // append rest linked list node to the end of new merged list
        if( l1){ p->next = l1;}
        else{ p->next = l2;}
        
        
        return head;
    }

    ListNode* mergeKLists(vector<ListNode *> &lists) {
        // leetCode 23: merge k sorted lists together
        // Merge every two lists and generate a new lists
        // use Queue to store new generated lists
        // Iteratively merge until just one list in queue
        // Even lists: Time complexity is O( k/2 * 2 * n + k*4*4*n + ...+ 1*2*k/2*n) = O(kn)

        // Do not merge from frist two and use merged lists to merge again with next lists
        // Time complexity for this method is O( 2n + 3n + .. + kn) = O(k*k*n)
        if( lists.empty() ){
            return NULL;
        }
        queue<ListNode*> listQueue;
        
        // merge every two lists
        for( int i = 0; i < lists.size(); ++i ){
            listQueue.push( lists[i] );
        }
        while( listQueue.size() >= 2 ){
            ListNode* p1 = listQueue.front();
            listQueue.pop();
            ListNode* p2 = listQueue.front();
            listQueue.pop();
            listQueue.push( mergeTwoLists( p1, p2) );
        }
        
        return listQueue.front();
    }

    int firstMissingPositive(int A[], int n) {
        // bucket sort
        for( int i = 0; i < n; ++i ){
            while( A[i] != i + 1 ){
                
                // find a negative or zero element
                if( A[i] < 1 || A[i] > n || A[i] == A[ A[i] - 1 ] ){
                    break;
                }
                
                int temp = A[i];
                A[i] = A[ temp - 1 ] ;
                A[ temp - 1 ] = temp;
            }
        }
        
        for( int i = 0; i < n ; ++i ){
            if( A[i] != i + 1 ){
                return i + 1;
            }
        }
        return n + 1;
    }
};