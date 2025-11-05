package P6;

public class ListBinaryTree implements BinaryTree {
    
    STNode root;
    int size;
    
    public void fillTree() {
        root = new STNode(new Integer(0), null, null, null);
        STNode node1 = new STNode(new Integer(1), root, null, null);
        STNode node2 = new STNode(new Integer(2), root, null, null);
        root.setLeftChild(node1);
        node1.setSibling(node2);

        STNode node3 = new STNode(new Integer(3), node1, null, null);
        STNode node4 = new STNode(new Integer(4), node1, null, null);
        node1.setLeftChild(node3);
        node3.setSibling(node4);

        STNode node5 = new STNode(new Integer(5), node2, null, null);
        STNode node6 = new STNode(new Integer(6), node2, null, null);
        node2.setLeftChild(node5);
        node5.setSibling(node6);

        STNode node7 = new STNode(new Integer(7), node3, null, null);
        STNode node8 = new STNode(new Integer(8), node3, null, null);
        node3.setLeftChild(node7);
        node7.setSibling(node8);

        STNode node9 = new STNode(new Integer(9), node4, null, null);
        STNode node10 = new STNode(new Integer(10), node4, null, null);
        node4.setLeftChild(node9);
        node9.setSibling(node10);

        size = 11;
    }
    public static void main (String[] args) {
        ListBinaryTree myTree = new ListBinaryTree();
        myTree.fillTree();
        // add rest of test code here
    }
}