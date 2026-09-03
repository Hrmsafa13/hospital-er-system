# Mini Hospital Emergency Management System

**Course:** CIT300 - Data Structures and Algorithms
**Assignment:** Individual Mid Assignment
**Language:** Java

## Overview
A console-based Java application that simulates a hospital's emergency
management workflow: registering patients, queuing them for emergency
treatment, recording completed treatments, and tracking each patient's
visit history.

## Data Structures Used

| Requirement | Data Structure | File(s) |
|---|---|---|
| Patient Records | Binary Search Tree (keyed on Patient ID) | `PatientBST.java`, `Patient.java` |
| Emergency Patient Queue | Queue (FIFO, custom linked implementation) | `EmergencyQueue.java` |
| Treatment History | Stack (LIFO, custom linked implementation) | `TreatmentStack.java`, `TreatmentRecord.java` |
| Patient Visit History | Singly Linked List (one per patient) | `VisitLinkedList.java`, `Visit.java` |

## Project Structure
hospital-er-system/
├── src/
│ ├── Main.java # Console menu / entry point
│ ├── Patient.java # Patient model (holds its own visit history)
│ ├── PatientBST.java # BST: insert, search, delete, in-order traversal
│ ├── EmergencyQueue.java # Queue: enqueue, dequeue, display
│ ├── TreatmentRecord.java # Treatment record model
│ ├── TreatmentStack.java # Stack: push, pop, display
│ ├── Visit.java # Visit model
│ └── VisitLinkedList.java # Singly linked list: add, remove, search, display
├── .gitignore
└── README.md

## How to Compile & Run
```bash
cd src
javac -d out *.java
java -cp out Main
```
Or simply open the project in IntelliJ IDEA and run `Main.java` directly.

## How to Use
1. **Patient Records (BST):** add, search, delete, and list all patients
   sorted by ID (in-order traversal).
2. **Emergency Patient Queue:** add a registered patient to the waiting
   line; dequeuing calls the next patient (FIFO) and optionally logs a
   treatment to the stack.
3. **Treatment History (Stack):** push a completed treatment record, pop
   the most recent one, or view the full stack (most recent first, LIFO).
4. **Patient Visit History (Linked List):** for a chosen patient, add,
   remove, search, and display their past visits.

**Note:** all data is stored in memory only and resets each time the
program restarts. This is expected — the assignment focus is on
demonstrating correct data structure behavior, not persistence.

## Design Notes
- The Queue and Stack are implemented from scratch using linked nodes
  (not `java.util.Queue`/`Stack`) to directly demonstrate the underlying
  data structure mechanics required by the assignment.
- Each `Patient` object owns its own `VisitLinkedList`, so visit history
  is naturally scoped per patient.
- BST deletion handles all three cases: leaf node, one child, and two
  children (using the in-order successor).
- A `.gitignore` excludes compiled build output (`out/`) so the
  repository only tracks source code.

## Testing
The system was manually tested end-to-end, covering:
- Adding patients with out-of-order IDs and confirming sorted in-order traversal
- Searching for and deleting existing/non-existing patients
- Enqueuing multiple patients and confirming FIFO dequeue order
- Pushing multiple treatment records and confirming LIFO pop/display order
- Adding, searching, removing, and displaying visits for a patient

Screenshots of each test are included in the submission.

## Author
MM.Fathima Safa — 23DA2-0699
