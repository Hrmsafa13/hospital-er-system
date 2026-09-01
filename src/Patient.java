public class Patient {
    private int patientId;
    private String patientName;
    private int age;
    private String contactNumber;
    private String medicalCondition;
    public Patient(int id, String name, int age, String contact, String condition) {
        this.patientId = id;
        this.patientName = name;
        this.age = age;
        this.contactNumber = contact;
        this.medicalCondition = condition;
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
    @Override
    public String toString() {
        return "ID: " + patientId + ", Name: " + patientName + ", Age: " + age +
                ", Contact: " + contactNumber + ", Condition: " + medicalCondition;
    }
}
