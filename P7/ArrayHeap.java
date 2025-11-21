package P7;

/**
* This class describes an ArrayHeap which extends the ArrayBinaryTree class and implements the Heap interface.
*
* @author Brandon Aikman
* @version 1.0
* File: ArrayHeap.java
* Created: Nov 2025
* Summary of Modifications: Initial version
* ©Copyright Cedarville University, its Computer Science faculty, and the author.
*
* Description: Extends the ArrayBinaryTree class and implements the Heap interface.
*/
public class ArrayHeap extends ArrayBinaryTree implements Heap {

    Comparator heapComp;

    public ArrayHeap(Comparator newComp) {
        this (newComp, DEFAULT_SIZE);
    }

    public ArrayHeap(Comparator newComp, int newSize) {
        super (newSize);
        heapComp = newComp;
    }

    /**
     * @param idx1 index 1 to be swapped.
     * @param idx2 index 2 to be swapped
     */
    public void swap(int idx1, int idx2) {
        ArrayPosition temp = btArray[idx1];

        btArray[idx1] = btArray[idx2];
        btArray[idx2] = temp;

        btArray[idx1].setIndex(idx1);
        btArray[idx2].setIndex(idx2);
    }

    /**
     * @param newKey new key to add.
     * @param newElement new element to add.
     */
    public void add(Object newKey, Object newElement) throws InvalidObjectException {
        if (!(heapComp.isComparable(newKey))) throw new InvalidObjectException("Cannot compare two different types!");
        Item newItem = new Item(newKey, newElement);
        ArrayPosition node = new ArrayPosition(size, newItem);

        if (size == btArray.length) {
            ArrayPosition[] newArr = new ArrayPosition[btArray.length * 2];
            for (int i = 0; i < size; i++) newArr[i] = btArray[i];
            btArray = newArr;
        }

        btArray[size] = node;
        size++;
        int idx = size - 1;

        while (idx > 0) {
            int parent = (idx - 1)/2;
            Item parentItem = (Item) btArray[parent].element();
            Item currItem = (Item) btArray[idx].element();

            if (heapComp.isGreaterThan(parentItem.key(), currItem.key())) {
                swap(idx, parent);
                idx = parent;
            } else break;
        }
    }

    /**
     * @return Item at root.
     */
    public Object removeRoot() throws EmptyHeapException {
        if (isEmpty()) throw new EmptyHeapException("Cannot remove root from empty heap!");
        if (size == 1) {
            Item rootItem = (Item) btArray[0].element();
            btArray[0] = null;
            size = 0;
            return rootItem;
        }

        ArrayPosition root = btArray[0];
        btArray[0] = btArray[size - 1];
        btArray[0].setIndex(0);

        btArray[size - 1] = null;
        size--;

        int idx = 0;
        while (true) {
            int left = 2 * idx + 1;
            if (left >= size) break;

            int right = left + 1;
            int swapIdx;

            if (right >= size) swapIdx = left;
            else {
                Item leftChild = (Item) btArray[left].element();
                Item rightChild = (Item) btArray[right].element();

                if (heapComp.isGreaterThan(leftChild.key(), rightChild.key())) swapIdx = right;
                else swapIdx = left;
            } 

            Item currItem = (Item) btArray[idx].element();
            Item swapItem = (Item) btArray[swapIdx].element();

            if (!heapComp.isGreaterThan(currItem.key(), swapItem.key())) break;

            swap(idx, swapIdx);
            idx = swapIdx;
        }
        
        return (Item) root.element();
    }

    public static void main (String[] args) {
	    Comparator myComp = new IntegerComparator();
        Heap myHeap = new ArrayHeap (myComp, 8);

        for (int i = 10000; i > 0; i--) {
            myHeap.add(i, i);
        }

        System.out.println(myHeap.size());
        while (!myHeap.isEmpty()) {

            Item removedItem = (Item) myHeap.removeRoot();
            System.out.print("Key:   " + removedItem.key() + "     ");
            System.out.println("Removed " + removedItem.element());
        }
        System.out.println("All nodes removed");
    }
}