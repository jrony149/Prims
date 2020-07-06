

import java.util.Scanner;
import java.util.Arrays;
import java.util.NoSuchElementException;


class BinHeap {
    //Defining class variables and array
    private static final int d = 2;
    public int heapSize;
    public MWST.Qelement[] heap;

    MWST mwst = new MWST();
    MWST.Qelement tmp; //temp variable for use throughout Heap for Qelement conversion.



    public BinHeap(int capacity){
        heapSize = 0; // initializing heap size to 0
        heap = new MWST.Qelement[capacity + 1];//making sure size of heap is one greater than order of graph
    }


    public boolean isEmpty( ){
        return heapSize == 0;
    }

    public boolean isFull( ){
        return heapSize == heap.length;
    }

    public void makeEmpty( ){
        heapSize = 0;
    }

    private int parent(int i){//Getting index of parent

        return (i - 1)/d;
    }


    private int kthChild(int i, int k){//Getting kth child of i

        return d * i + k;
    }


    public void insert(MWST.Qelement x){//insert element function; ensures min value is at top of heap
        if (isFull( ) )
            throw new NoSuchElementException("Overflow Exception");
        heap[heapSize++] = x;
        heapifyUp(heapSize - 1);
    }


    public MWST.Qelement findMin( ){//returns min element but does not delete it
        if (isEmpty() )
            throw new NoSuchElementException("Underflow Exception");
        return heap[0];
    }


    public MWST.Qelement deleteMin(){ //deletes min element and returns it
        tmp = heap[0];
        delete(0);
        return tmp;
    }


    public MWST.Qelement delete(int ind){ //deletes specific element
        if (isEmpty() )
            throw new NoSuchElementException("Underflow Exception");
        tmp = heap[ind];
        heap[ind] = heap[heapSize - 1];
        heapSize--;
        heapifyDown(ind);
        return tmp;
    }


    private void heapifyUp(int childInd){ //heapfiying up for use by insert
        tmp = heap[childInd];
        while (childInd > 0 && tmp.getKey() < heap[parent(childInd)].getKey()){
            heap[childInd] = heap[ parent(childInd) ];
            childInd = parent(childInd);
        }
        heap[childInd] = tmp;
    }


    private void heapifyDown(int ind){//heapifying down for use by delete
        int child;
        tmp = heap[ ind ];
        while (kthChild(ind, 1) < heapSize){
            child = minChild(ind);
            if (heap[child].getKey() < tmp.getKey())
                heap[ind] = heap[child];
            else
                break;
            ind = child;
        }
        heap[ind] = tmp;
    }


    private int minChild(int ind){ //returns smallest child
        int bestChild = kthChild(ind, 1);
        int k = 2;
        int pos = kthChild(ind, k);
        while ((k <= d) && (pos < heapSize)){
            if (heap[pos].getKey() < heap[bestChild].getKey())
                bestChild = pos;
            pos = kthChild(ind, k++);
        }
        return bestChild;
    }

}