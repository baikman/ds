package P6;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Test;

import P3.InvalidRPNString;
import P3.RPN;

/**
* This class uses JUnit to test the ListBinaryTree alongside InOrder, PostOrder, and PreOrder.
*
* @author Brandon Aikman
* @version 1.0
* File: ListBinaryTree.java
* Created: Nov 2025
* Summary of Modifications: Initial version
* ©Copyright Cedarville University, its Computer Science faculty, and the author.
*
* Description: This class uses JUnit to test the ListBinaryTree alongside InOrder, PostOrder, and PreOrder.
*/
public class ListBinaryTreeTest {
    @Test
    public void testSize() {
        ListBinaryTree myTree = new ListBinaryTree();
        myTree.fillTree();
        assertEquals(11, myTree.size());
    }

    @Test
    public void testIsEmpty() {
        ListBinaryTree myTree = new ListBinaryTree();
        myTree.fillTree();
        assertEquals(false, myTree.isEmpty());
    }

    @Test
    public void testGetRoot() {
        ListBinaryTree myTree = new ListBinaryTree();
        myTree.fillTree();
        STNode tempRoot = (STNode) myTree.root();
        assertEquals(tempRoot, myTree.root());
    }

    @Test
    public void testGetLeftChild() {
        STNode root = new STNode(0, null, null, null);
        STNode node1 = new STNode(1, root, null, null);
        STNode node2 = new STNode(2, root, null, null);
        root.setLeftChild(node1);
        node1.setSibling(node2);
        assertEquals(root.getLeftChild(), node1);
    }

    @Test
    public void testGetSibling() {
        STNode root = new STNode(0, null, null, null);
        STNode node1 = new STNode(1, root, null, null);
        STNode node2 = new STNode(2, root, null, null);
        root.setLeftChild(node1);
        node1.setSibling(node2);
        assertEquals(node1.getSibling(), node2);
    }

    @Test
    public void testIsExternal() {
        ListBinaryTree myTree = new ListBinaryTree();
        myTree.fillTree();
        STNode tempRoot = (STNode) myTree.root();
        assertEquals(tempRoot, myTree.root());
        assertEquals(myTree.isExternal(myTree.root()), false);
    }

    @Test
    public void testIsInternal() {
        ListBinaryTree myTree = new ListBinaryTree();
        myTree.fillTree();
        STNode tempRoot = (STNode) myTree.root();
        assertEquals(tempRoot, myTree.root());
        assertEquals(myTree.isInternal(myTree.root()), true);
    }

    @Test
    public void testBadParent1() {        
        InvalidPositionException exception = assertThrows(InvalidPositionException.class, () -> {
            ListBinaryTree myTree = new ListBinaryTree();
            myTree.fillTree();
            myTree.parent(null);
        });
        assertEquals("Cannot perform operation on null position.", exception.getMessage());
    }

    @Test
    public void testBadParent2() {        
        InvalidPositionException exception = assertThrows(InvalidPositionException.class, () -> {
            ListBinaryTree myTree = new ListBinaryTree();
            myTree.fillTree();
            Position inv = new Position() {
                int test = 5;
            };
            myTree.parent(inv);
        });
        assertEquals("Cannot perform operation on wrong object type.", exception.getMessage());
    }

    @Test
    public void testEmptyTreeException() {        
        EmptyTreeException exception = assertThrows(EmptyTreeException.class, () -> {
            ListBinaryTree myTree = new ListBinaryTree();
            myTree.root();
        });
        assertEquals("Cannot return root of empty tree.", exception.getMessage());
    }
}