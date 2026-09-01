/**
 * Requirement 4: Patient Visit History implemented as a Singly Linked List.
 * Each Patient object owns exactly one of these.
 */
public class VisitLinkedList {

    private class Node {
        Visit visit;
        Node next;

        Node(Visit visit) {
            this.visit = visit;
        }
    }

    private Node head;

    // ---- ADD ----
    public void addVisit(Visit visit) {
        Node newNode = new Node(visit);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    // ---- REMOVE ----
    public boolean removeVisit(int visitId) {
        if (head == null) return false;

        if (head.visit.getVisitId() == visitId) {
            head = head.next;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.visit.getVisitId() == visitId) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // ---- SEARCH ----
    public Visit searchVisit(int visitId) {
        Node current = head;
        while (current != null) {
            if (current.visit.getVisitId() == visitId) return current.visit;
            current = current.next;
        }
        return null;
    }

    // ---- DISPLAY ----
    public void display() {
        if (head == null) {
            System.out.println("No visit history found.");
            return;
        }
        Node current = head;
        while (current != null) {
            System.out.println(current.visit);
            current = current.next;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }
}