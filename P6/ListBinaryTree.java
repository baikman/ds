package P6;

/**
* This class describes a ListBinaryTree implenting a BinaryTree.
*
* @author Brandon Aikman
* @version 1.0
* File: ListBinaryTree.java
* Created: Nov 2025
* Summary of Modifications: Initial version
* ©Copyright Cedarville University, its Computer Science faculty, and the author.
*
* Description: This class describes a ListBinaryTree implenting a BinaryTree.
*/
public class ListBinaryTree implements BinaryTree {
    STNode root;
    int size;

    /**
     * @param pos is the node to be validated
     * @return a reference to pos
     */
    private STNode validate(Position pos) {
        if (pos == null) throw new InvalidPositionException("Cannot perform operation on null position.");
        if (!(pos instanceof STNode)) throw new InvalidPositionException("Cannot perform operation on wrong object type.");
        return (STNode) pos;
    }

    /**
     * @return a reference to the root node
     */
    public Position root() throws EmptyTreeException {
        if (isEmpty()) throw new EmptyTreeException("Cannot return root of empty tree.");
        return root;
    }

    /**
     * @param pos is the node whose left child will be returned
     * @return a reference to left child of pos
     */
    public Position leftChild(Position pos) throws InvalidPositionException {
        STNode node = validate(pos);
        STNode left = node.getLeftChild();
        return (left == null) ? null : left;
    }

    /**
     * @param pos is the node whose right child will be returned
     * @return a reference to right child of pos
     */
    public Position rightChild(Position pos) throws InvalidPositionException {
        STNode node = validate(pos);
        STNode left = node.getLeftChild();
        if (left == null) return null;
        STNode right = left.getSibling();
        return (right == null) ? null : right;
    }

    /**
     * @param pos is the node whose sibling will be returned
     * @return a reference to the sibling of pos
     */
    public Position sibling(Position pos) throws InvalidPositionException {
        STNode node = validate(pos);
        STNode sibling = node.getSibling();
        return (sibling == null) ? null : sibling;
    }

    /**
     * @param pos is the node whose parent will be returned
     * @return a reference to the parent of pos
     */
    public Position parent(Position pos) throws InvalidPositionException{
        STNode node = validate(pos);
        STNode parent = node.getParent();
        return (parent == null) ? null : parent;
    }

    /**
     * @param pos is the node which will be examined
     * @return true if node has 1 or 2 childen
     */
    public boolean isInternal(Position pos) throws InvalidPositionException {
        STNode node = validate(pos);
        return node.getLeftChild() != null;
    }

    /**
     * @param pos is the node which will be examined
     * @return true if node has no childen
     */
    public boolean isExternal (Position pos) throws InvalidPositionException {
        STNode node = validate(pos);
        return node.getLeftChild() == null;       
    }

    /**
     * @param pos is the node which will be examined
     * @return true if the node is the root node
     */
    public boolean isRoot(Position pos) throws InvalidPositionException {
        STNode node = validate(pos);
        return node.getParent() == null;
    }

    /**
     * @return the number of Positions (nodes) in the tree
     */
    public int size() {
        return size;
    }

    /**
     * @return true if the tree currently contains no Positions
     */
    public boolean isEmpty() {
        if (size == 0) return true;
        return false;
    }
    
    public void fillTree() {
        root = new STNode(0, null, null, null);
        STNode node1 = new STNode(1, root, null, null);
        STNode node2 = new STNode(2, root, null, null);
        root.setLeftChild(node1);
        node1.setSibling(node2);

        STNode node3 = new STNode(3, node1, null, null);
        STNode node4 = new STNode(4, node1, null, null);
        node1.setLeftChild(node3);
        node3.setSibling(node4);

        STNode node5 = new STNode(5, node2, null, null);
        STNode node6 = new STNode(6, node2, null, null);
        node2.setLeftChild(node5);
        node5.setSibling(node6);

        STNode node7 = new STNode(7, node3, null, null);
        STNode node8 = new STNode(8, node3, null, null);
        node3.setLeftChild(node7);
        node7.setSibling(node8);

        STNode node9 = new STNode(9, node4, null, null);
        STNode node10 = new STNode(10, node4, null, null);
        node4.setLeftChild(node9);
        node9.setSibling(node10);

        size = 11;
    }

    public static void main (String[] args) {
        ListBinaryTree myTree = new ListBinaryTree();
        myTree.fillTree();

        PreOrder pre = new PreOrder(myTree);
        pre.performTour(myTree.root());
        System.out.println();

        InOrder in = new InOrder(myTree);
        in.performTour(myTree.root());
        System.out.println();

        PostOrder post = new PostOrder(myTree);
        post.performTour(myTree.root());
    }
}