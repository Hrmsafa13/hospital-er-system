/**
 * Requirement 3: Treatment History implemented as a Stack (LIFO).
 * Built from scratch with a linked structure (not java.util).
 */
public class TreatmentStack {

    private class Node {
        TreatmentRecord record;
        Node next;

        Node(TreatmentRecord record) {
            this.record = record;
        }
    }

    private Node top;
    private int size;

    // ---- PUSH ----
    public void push(TreatmentRecord record) {
        Node newNode = new Node(record);
        newNode.next = top;
        top = newNode;
        size++;
        System.out.println("Treatment recorded: " + record.toString());
    }

    // ---- POP ----
    public TreatmentRecord pop() {
        if (isEmpty()) {
            System.out.println("Treatment stack is empty. Nothing to remove.");
            return null;
        }
        TreatmentRecord removed = top.record;
        top = top.next;
        size--;
        return removed;
    }

    // ---- DISPLAY ----
    public void displayStack() {
        if (isEmpty()) {
            System.out.println("No treatment records found.");
            return;
        }
        System.out.println("--- Treatment History (most recent first) ---");
        Node current = top;
        while (current != null) {
            System.out.println(current.record);
            current = current.next;
        }
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }
}