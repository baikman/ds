package P6;

/**
* This file describes the PreOrder class.
*
* @author Brandon Aikman
* @version 1.0
* File: PreOrder.java
* Created: Nov 2025
* Summary of Modifications: Initial version
* ©Copyright Cedarville University, its Computer Science faculty, and the author.
*
* Description: This file describes the PreOrder class.
*/
public class PreOrder extends EulerTour {
    public ListBinaryTree tree;

    /**
     * @param newTree is the new tree for the traversal
     */
    public PreOrder(ListBinaryTree newTree) {
        super(newTree);
    }

    /**
     * @param pos is the node being visited
     * @param result is a storage mechanism for results computed as this node
     */
    @Override
    public void visitPreorder(Position pos) {
        System.out.println(pos.element());
    }

    /**
     * @param pos is the node being visited
     * @param result is a storage mechanism for results computed as this node
     */
    @Override
    public void visitExternal(Position pos) {
        System.out.println(pos.element());
    }
}
