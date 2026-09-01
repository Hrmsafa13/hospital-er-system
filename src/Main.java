import java.util.Scanner;

/**
 * Mini Hospital Emergency Management System
 * CIT300 - Data Structures and Algorithms - Individual Mid Assignment
 *
 * Ties together:
 *  1. PatientBST        - patient records (Binary Search Tree)
 *  2. EmergencyQueue     - waiting patients (Queue / FIFO)
 *  3. TreatmentStack     - completed treatments (Stack / LIFO)
 *  4. VisitLinkedList    - per-patient visit history (Singly Linked List)
 */
public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static PatientBST patientBST = new PatientBST();
    private static EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static TreatmentStack treatmentStack = new TreatmentStack();

    private static int nextTreatmentRecordId = 1;

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1: patientMenu(); break;
                case 2: queueMenu(); break;
                case 3: stackMenu(); break;
                case 4: visitHistoryMenu(); break;
                case 0:
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }

    // =========================================================
    // MAIN MENU
    // =========================================================
    private static void printMainMenu() {
        System.out.println("\n===== MINI HOSPITAL EMERGENCY MANAGEMENT SYSTEM =====");
        System.out.println("1. Patient Records (BST)");
        System.out.println("2. Emergency Patient Queue (Queue)");
        System.out.println("3. Treatment History (Stack)");
        System.out.println("4. Patient Visit History (Linked List)");
        System.out.println("0. Exit");
    }

    // =========================================================
    // 1. PATIENT RECORDS - BST
    // =========================================================
    private static void patientMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Patient Records (BST) ---");
            System.out.println("1. Add new patient");
            System.out.println("2. Search patient by ID");
            System.out.println("3. Delete patient");
            System.out.println("4. Display all patients (in-order)");
            System.out.println("0. Back to main menu");
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1: addPatient(); break;
                case 2: {
                    int id = readInt("Enter Patient ID to search: ");
                    Patient p = patientBST.search(id);
                    System.out.println(p != null ? "Found -> " + p : "Patient not found.");
                    break;
                }
                case 3: {
                    int id = readInt("Enter Patient ID to delete: ");
                    boolean deleted = patientBST.delete(id);
                    System.out.println(deleted ? "Patient deleted." : "Patient not found.");
                    break;
                }
                case 4: patientBST.inorderTraversal(); break;
                case 0: back = true; break;
                default: System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addPatient() {
        int id = readInt("Patient ID: ");
        if (patientBST.search(id) != null) {
            System.out.println("A patient with this ID already exists.");
            return;
        }
        System.out.print("Name: ");
        String name = scanner.nextLine();
        int age = readInt("Age: ");
        System.out.print("Contact Number: ");
        String contact = scanner.nextLine();
        System.out.print("Medical Condition: ");
        String condition = scanner.nextLine();

        Patient patient = new Patient(id, name, age, contact, condition);
        patientBST.insert(patient);
        System.out.println("Patient added successfully.");
    }

    // =========================================================
    // 2. EMERGENCY QUEUE
    // =========================================================
    private static void queueMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Emergency Patient Queue ---");
            System.out.println("1. Enqueue patient (send to waiting queue)");
            System.out.println("2. Dequeue patient (call next for treatment)");
            System.out.println("3. Display waiting queue");
            System.out.println("0. Back to main menu");
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1: {
                    int id = readInt("Enter Patient ID to add to queue: ");
                    Patient p = patientBST.search(id);
                    if (p == null) {
                        System.out.println("No such patient. Add them in Patient Records first.");
                    } else {
                        emergencyQueue.enqueue(p);
                    }
                    break;
                }
                case 2: {
                    Patient treated = emergencyQueue.dequeue();
                    if (treated != null) {
                        System.out.println("Now treating: " + treated);
                        completeTreatment(treated);
                    }
                    break;
                }
                case 3: emergencyQueue.displayQueue(); break;
                case 0: back = true; break;
                default: System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void completeTreatment(Patient patient) {
        System.out.print("Enter treatment details to log (or press Enter to skip): ");
        String details = scanner.nextLine();
        if (!details.isEmpty()) {
            TreatmentRecord record = new TreatmentRecord(
                    nextTreatmentRecordId++, patient.getPatientId(), patient.getPatientName(),
                    details, "2026-09-02");
            treatmentStack.push(record);
        }
    }

    // =========================================================
    // 3. TREATMENT HISTORY - STACK
    // =========================================================
    private static void stackMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Treatment History (Stack) ---");
            System.out.println("1. Push completed treatment record");
            System.out.println("2. Pop most recent treatment record");
            System.out.println("3. Display treatment history");
            System.out.println("0. Back to main menu");
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1: {
                    int id = readInt("Enter Patient ID for this treatment: ");
                    Patient p = patientBST.search(id);
                    if (p == null) {
                        System.out.println("No such patient.");
                        break;
                    }
                    System.out.print("Treatment details: ");
                    String details = scanner.nextLine();
                    System.out.print("Completion date (e.g. 2026-09-02): ");
                    String date = scanner.nextLine();
                    TreatmentRecord record = new TreatmentRecord(
                            nextTreatmentRecordId++, p.getPatientId(), p.getPatientName(), details, date);
                    treatmentStack.push(record);
                    break;
                }
                case 2: {
                    TreatmentRecord removed = treatmentStack.pop();
                    if (removed != null) System.out.println("Removed: " + removed);
                    break;
                }
                case 3: treatmentStack.displayStack(); break;
                case 0: back = true; break;
                default: System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // =========================================================
    // 4. VISIT HISTORY - SINGLY LINKED LIST
    // =========================================================
    private static void visitHistoryMenu() {
        int id = readInt("Enter Patient ID to manage visit history: ");
        Patient p = patientBST.search(id);
        if (p == null) {
            System.out.println("No such patient. Add them in Patient Records first.");
            return;
        }

        boolean back = false;
        while (!back) {
            System.out.println("\n--- Visit History for " + p.getPatientName() + " (ID " + p.getPatientId() + ") ---");
            System.out.println("1. Add new visit");
            System.out.println("2. Remove visit by Visit ID");
            System.out.println("3. Search visit by Visit ID");
            System.out.println("4. Display full visit history");
            System.out.println("0. Back to main menu");
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1: {
                    int visitId = readInt("Visit ID: ");
                    System.out.print("Visit Date: ");
                    String date = scanner.nextLine();
                    System.out.print("Doctor Name: ");
                    String doctor = scanner.nextLine();
                    System.out.print("Diagnosis: ");
                    String diagnosis = scanner.nextLine();
                    System.out.print("Treatment: ");
                    String treatment = scanner.nextLine();
                    p.getVisitHistory().addVisit(new Visit(visitId, date, doctor, diagnosis, treatment));
                    System.out.println("Visit added.");
                    break;
                }
                case 2: {
                    int visitId = readInt("Enter Visit ID to remove: ");
                    boolean removed = p.getVisitHistory().removeVisit(visitId);
                    System.out.println(removed ? "Visit removed." : "Visit not found.");
                    break;
                }
                case 3: {
                    int visitId = readInt("Enter Visit ID to search: ");
                    Visit v = p.getVisitHistory().searchVisit(visitId);
                    System.out.println(v != null ? "Found -> " + v : "Visit not found.");
                    break;
                }
                case 4: p.getVisitHistory().display(); break;
                case 0: back = true; break;
                default: System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // =========================================================
    // INPUT HELPERS
    // =========================================================
    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}