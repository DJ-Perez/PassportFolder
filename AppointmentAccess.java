package PassportFolder;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class AppointmentAccess {
    private ArrayList<PassportInformation> applications = new ArrayList<>();
    private final int MaxSlots = 25;
    private int[] timeSlotCount = new int[8];

    private String[] timeSlots = {
        "8:00 AM  -  9:00 AM",
        "9:00 AM  -  10:00 AM",
        "10:00 AM -  11:00 AM", 
        "11:00 AM -  12:00 PM",
        "1:00 PM  -  2:00 PM",
        "2:00 PM  -  3:00 PM",
        "3:00 PM  -  4:00 PM",
        "4:00 PM  -  5:00 PM"
    };

    public AppointmentAccess(ArrayList<PassportInformation> applications) {
        this.applications = applications;
    }

    public void applyPassport(Scanner sc) {
        while(true){
        //User's Credential
        System.out.println("\n=== PASSPORT APPLICATION FORM === ");
        System.out.print("Application Type(New | Renewal): ");
        String appType = sc.nextLine();

        System.out.print("Last Name: ");
        String lastName = sc.nextLine();

        System.out.print("First Name: ");
        String firstName = sc.nextLine();

        System.out.print("Middle Name: ");
        String middleName = sc.nextLine();

        System.out.print("Sex:(Male | Female): ");
        String sex = sc.nextLine();

        System.out.print("Birthdate(YYYY-MM-DD): ");
        String birthDate = sc.nextLine();

        System.out.print("Place of Birth: ");
        String placeofBirth = sc.nextLine();

        System.out.print("Address: ");
        String address = sc.nextLine();

        System.out.print("Contact Number: ");
        String contactNum = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Civil Status(Single | Married):");
        String civilStatus = sc.nextLine();

        //StandBy
        /*System.out.println("Citizenship Acquired By\n(Birth, Naturalization, Election, RA 9225): ");
        String citizenShip = sc.nextLine();

        System.out.println("Father Name");
        String fatherName = sc.nextLine();

        System.out.println("Mother Name");
        String motherName = sc.nextLine();
        */
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();
        String appointmentDate;
            
        System.out.print("Appointment Date (YYYY-MM-DD): ");
        appointmentDate = sc.nextLine();
        try{
            LocalDate date = LocalDate.parse(appointmentDate, DateTimeFormatter.ISO_LOCAL_DATE);
            if(date.isBefore(today)) {
                System.out.println("Appointment date cannot be in the past!");
            }else if(date.getYear() != currentYear) {
                System.out.println("Appointment must be within the current year.");
            }else{
                //Shows the available timeslots
                System.out.println("Select Time Slot:");
                for (int i = 0; i < timeSlots.length; i++) {
                    System.out.println((i + 1) + ". " + timeSlots[i] + " - " + (MaxSlots - timeSlotCount[i]) + " slots available");
                }
                //Select the timeSlots
                System.out.print("Enter choice (1-8): ");
                int slotChoice = sc.nextInt();
                sc.nextLine();

                if(timeSlotCount[slotChoice - 1] < MaxSlots){
                    System.out.println("This time slot is available!");
                    String chosenSlot = timeSlots[slotChoice - 1];
                    timeSlotCount[slotChoice - 1]++;  // Increment the counter for the selected time slot

                    // Create and Store the Application
                    int refNum = applications.size() + 1;
                    PassportInformation app = new PassportInformation(appType, lastName, firstName, middleName, sex,
                            birthDate, placeofBirth, address, contactNum, email, civilStatus);
                    app.setAppointmentDate(appointmentDate);
                    app.setTimeSlots(new String[]{chosenSlot});
                    applications.add(app);
                    app.setRefNumber(refNum);

                    // Review the application here
                    System.out.println("\n====== Review Your Application ======");
                    System.out.println("Application Type: " + appType);
                    System.out.println("Name: " + firstName + " " + middleName + " " + lastName);
                    System.out.println("Sex: " + sex);
                    System.out.println("Birthdate: " + birthDate);
                    System.out.println("Place of Birth: " + placeofBirth);
                    System.out.println("Address: " + address);
                    System.out.println("Contact Number: " + contactNum);
                    System.out.println("Email: " + email);
                    System.out.println("Civil Status: " + civilStatus);
                    System.out.println("Appointment Date: " + appointmentDate);
                    System.out.println("Time Slot: " + chosenSlot);

                    // Ask user to confirm or edit
                    System.out.println("\nDo you want to (1) Submit or (2) Edit the application?");
                    System.out.print("Enter choice: ");
                    int userChoice = sc.nextInt();
                    sc.nextLine();

                    if (userChoice == 1){
                        app.setStatus("Pending");
                        System.out.println("\n======== Application has been submitted! ==========");
                        System.out.println("Reference Number: " + refNum);
                    }else if(userChoice == 2) {
                        timeSlotCount[slotChoice - 1]--;
                        System.out.println("\nReturning to the form to make changes...");
                        applications.remove(app); // Remove the application if user chooses to edit
                        continue;
                    }else{
                        System.out.println("Invalid choice! Please choose either 1 or 2.");
                        continue;
                    }
                    break;
                }else{
                    System.out.println("Sorry, the selected time slot is fully booked.");
                }
            }
            }catch(Exception e){
            System.out.println("Invalid format! Please use YYYY-MM-DD.");
            }
        }
    }

    public void checkStatus(Scanner sc) {
        //Checked the Application Submitted
        System.out.print("\nEnter Reference Number: ");
        int ref = sc.nextInt();
        for (PassportInformation app : applications) {
            if (app.getRefNumber() == ref) {
                System.out.println("\nApplication Found:");
                System.out.println(applications.get(ref - 1));
                return;
            }
        }
        System.out.println("No application found for this reference number.");
    }

    public void showRequirements() {
    System.out.println("\n========== PASSPORT REQUIREMENTS ==============");
    System.out.println("====== NEW APPLICANTS =====");
    System.out.println("-- Minors (1-17 years old) --");
    System.out.println("1. Original PSA Birth Certificate");
    System.out.println("2. School ID (if applicable)");
    System.out.println("3. Form 137 (optional)");
    System.out.println("4. Parent/Guardian Requirements:");
    System.out.println("   - At least one parent must appear with valid ID");
    System.out.println("   - If parent not present: SPA + representative's valid ID");
    System.out.println("5. Marriage Certificate (if parents are married)");
    System.out.println("6. Court order/DSWD documents if under legal guardian");
    System.out.println("7. Affidavit of Consent if traveling with someone else");

    System.out.println("\n--- Adults (18 years and above) ---");
    System.out.println("1. Confirmed Online Appointment");
    System.out.println("2. Accomplished Passport Application Form");
    System.out.println("3. PSA Birth Certificate (for first-time applicants)");
    System.out.println("4. One Valid ID (UMID, Driver's License, Postal ID, SSS ID, PhilHealth ID, Voter's ID, PRC ID, Senior Citizen ID, OFW ID, Government Employee ID)");
    System.out.println("5. Supporting Documents (if applicable):");
    System.out.println("   - PSA Marriage Certificate (for married women who want to change surname)");
    System.out.println("   - Court Order for name change, legitimation, or adoption");

    System.out.println("\n===== RENEWAL APPLICANTS =====");
    System.out.println("--- Adults ---");
    System.out.println("1. Old Passport");
    System.out.println("2. Valid ID");
    System.out.println("3. Printed Appointment Confirmation");
    System.out.println("\n--- Minors ---");
    System.out.println("1. Old Passport");
    System.out.println("2. PSA Birth Certificate");
    System.out.println("3. Parent with valid ID");
    System.out.println("=========================================");
    }
}