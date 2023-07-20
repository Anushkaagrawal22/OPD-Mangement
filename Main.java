/******************************************************************************

Welcome to GDB Online.
  GDB online is an online compiler and debugger tool for C, C++, Python, PHP, Ruby, 
  C#, OCaml, VB, Perl, Swift, Prolog, Javascript, Pascal, COBOL, HTML, CSS, JS
  Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.util.ArrayList;
import java.util.HashMap;

class HospitalAppointmentScheduler {
    private HashMap<String, ArrayList<Integer>> patientVisits;

    public HospitalAppointmentScheduler() {
        this.patientVisits = new HashMap<>();
    }

    public void addPatientVisit(String patientName, int priority) {
        ArrayList<Integer> visits = patientVisits.getOrDefault(patientName, new ArrayList<>());
        visits.add(priority);
        patientVisits.put(patientName, visits);
    }

    public int getNumVisitsForPatient(String patientName) {
        return patientVisits.getOrDefault(patientName, new ArrayList<>()).size();
    }

    // Function to schedule appointment based on priority (fewer visits get higher priority)
    public void scheduleAppointment(String patientName, int priority) {
        int appointmentDuration;
        int numVisits = getNumVisitsForPatient(patientName);

        switch (priority) {
            case 1: // High Priority
                appointmentDuration = 25;
                break;
            case 2: // Medium Priority
                appointmentDuration = 15;
                break;
            case 3: // Low Priority
                appointmentDuration = 5;
                break;
            default:
                System.out.println("Invalid priority level.");
                return;
        }

        int adjustedPriority = priority + numVisits; // Prioritize patients with fewer visits
        System.out.println("Scheduled an appointment of " + appointmentDuration + " minutes for "
                + patientName + " with adjusted priority " + adjustedPriority + ".");
    }

    // Function to get appointment duration with doctor's suggestion (always 25 mins if doctor suggests)
    public void getAppointmentDurationWithDoctorSuggestion(String patientName, boolean doctorSuggestsExtended) {
        if (doctorSuggestsExtended) {
            System.out.println("Doctor suggests extended appointment.");
            System.out.println("Scheduled an appointment of 25 minutes for " + patientName + ".");
        } else {
            // If doctor doesn't suggest extended appointment, use the existing scheduleAppointment function
            int priority = 1; // Assume high priority in this case
            scheduleAppointment(patientName, priority);
        }
    }

    // Other functions and main method (if needed) can remain the same
    // ...
}

public class Main {
    public static void main(String[] args) {
        // Initialize and test the HospitalAppointmentScheduler
        HospitalAppointmentScheduler scheduler = new HospitalAppointmentScheduler();

        scheduler.addPatientVisit("Alice", 1);
        scheduler.addPatientVisit("Bob", 2);
        scheduler.addPatientVisit("Alice", 3);

        System.out.println("Number of visits for Alice: " + scheduler.getNumVisitsForPatient("Alice"));
        System.out.println("Number of visits for Bob: " + scheduler.getNumVisitsForPatient("Bob"));

        // Schedule appointments for patients based on priority
        scheduler.scheduleAppointment("Alice", 1);
        scheduler.scheduleAppointment("Bob", 2);
        scheduler.scheduleAppointment("Charlie", 3);
        scheduler.scheduleAppointment("Alice", 2); // Test patient with multiple visits

        // Get appointment duration with doctor's suggestion
        scheduler.getAppointmentDurationWithDoctorSuggestion("John", true); // Doctor suggests extended appointment
        scheduler.getAppointmentDurationWithDoctorSuggestion("Emma", false); // Doctor doesn't suggest extended appointment
    }
}

