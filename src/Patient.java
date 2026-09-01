/**
 * Represents a single patient record stored in the Patient BST.
 * Each patient also owns a Singly Linked List of their past visits.
 */
public class Patient {
    private int patientId;
    private String patientName;
    private int age;
    private String contactNumber;
    private String medicalCondition;
    private VisitLinkedList visitHistory; // Requirement 4: Singly Linked List per patient

    public Patient(int id, String name, int age, String contact, String condition) {
        this.patientId = id;
        this.patientName = name;
        this.age = age;
        this.contactNumber = contact;
        this.medicalCondition = condition;
        this.visitHistory = new VisitLinkedList();
    }

    public int getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public int getAge() {
        return age;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public VisitLinkedList getVisitHistory() {
        return visitHistory;
    }

    @Override
    public String toString() {
        return "ID: " + patientId + ", Name: " + patientName + ", Age: " + age +
                ", Contact: " + contactNumber + ", Condition: " + medicalCondition;
    }
}