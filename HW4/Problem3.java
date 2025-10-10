package HW4;

// pseudo-ish code
class Node {
    Node next;
    Node() {
        next = null;
    }
}

public class Problem3 {
    public static Node reverseLinkedList(Node head) {
        if (head.next == null) return head;
        Node fin = reverseLinkedList(head.next);
        head.next.next = head;
        head.next = null;
        return fin;
    }
}