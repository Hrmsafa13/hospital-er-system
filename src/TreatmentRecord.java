/**
 * Represents a completed treatment, stored on the Treatment Stack.
 */
public class TreatmentRecord {
    private int recordId;
    private int patientId;
    private String patientName;
    private String treatmentDetails;
    private String completionDate;

    public TreatmentRecord(int recordId, int patientId, String patientName,
                           String treatmentDetails, String completionDate) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.treatmentDetails = treatmentDetails;
        this.completionDate = completionDate;
    }

    public int getRecordId() {
        return recordId;
    }

    @Override
    public String toString() {
        return "Record ID: " + recordId +
                " | Patient ID: " + patientId +
                " | Patient: " + patientName +
                " | Treatment: " + treatmentDetails +
                " | Completed: " + completionDate;
    }
}