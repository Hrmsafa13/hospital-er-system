/**
 * Requirement 2: Emergency Patient Queue.
 * Built from scratch with a linked structure (not java.util) so it clearly
 * demonstrates FIFO behaviour: enqueue at the rear, dequeue from the front.
 */
public class EmergencyQueue {

    private class Node {
        Patient patient;
        Node next;

        Node(Patient patient) {
            this.patient = patient;
        }
    }

    private Node front;
    private Node rear;
    private int size;

    // ---- ENQUEUE ----
    public void enqueue(Patient patient) {
        Node newNode = new Node(patient);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println("Added to emergency queue: " + patient.getPatientName());
    }

    // ---- DEQUEUE ----
    public Patient dequeue() {
        if (isEmpty()) {
            System.out.println("Emergency queue is empty. No patient to treat.");
            return null;
        }
        Patient treated = front.patient;
        front = front.next;
        if (front == null) rear = null; // queue became empty
        size--;
        return treated;
    }

    // ---- DISPLAY ----
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("No patients currently waiting.");
            return;
        }
        System.out.println("--- Patients Waiting (front to rear) ---");
        Node current = front;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.patient);
            current = current.next;
            position++;
        }
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }
}
