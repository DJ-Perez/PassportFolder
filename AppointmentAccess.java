package PassportFolder;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AppointmentAccess {
    private ArrayList<PassportInformation> applications = new ArrayList<>();
    private final int MaxSlots = 25;
    private int[] timeSlotCount = new int[8];

    private String[] timeSlots = {
            "8:00 AM - 9:00 AM",
            "9:00 AM - 10:00 AM",
            "10:00 AM - 11:00 AM",
            "11:00 AM - 12:00 PM",
            "1:00 PM - 2:00 PM",
            "2:00 PM - 3:00 PM",
            "3:00 PM - 4:00 PM",
            "4:00 PM - 5:00 PM"
    };

    public AppointmentAccess(ArrayList<PassportInformation> applications) {
        this.applications = applications;
    }

    public void applyPassport(Scanner sc) {
        //====== User's Credentials ========
        while (true) {
            System.out.println("\n======== PASSPORT APPLICATION FORM ========");
            String appType;
            while (true) {
                System.out.print("Application Type(New | Renewal): ");
                appType = sc.nextLine().trim();
                if (appType.equalsIgnoreCase("New") || appType.equalsIgnoreCase("Renewal")) break;
                System.out.println("Invalid input. Only 'New' or 'Renewal' allowed.");
            }
            
            String lastName = getValidatedInput(sc, "Last Name", "[a-zA-Z ]+");
            String firstName = getValidatedInput(sc, "First Name", "[a-zA-Z ]+");
            String middleName = getValidatedInput(sc, "Middle Name", "[a-zA-Z ]+");

            String sex;
            while (true) {
                System.out.print("Sex(Male | Female): ");
                sex = sc.nextLine().trim();
                if (sex.equalsIgnoreCase("Male") || sex.equalsIgnoreCase("Female")) break;
                System.out.println("Invalid input. Only 'Male' or 'Female' allowed.");
            }

            String birthDate;
            while (true) {
                System.out.print("Birthdate(YYYY-MM-DD): ");
                birthDate = sc.nextLine().trim();
                try {
                    LocalDate date = LocalDate.parse(birthDate, DateTimeFormatter.ISO_LOCAL_DATE);
                    int year = date.getYear();
                    if (year >= 1990 && year <= 2025) break;
                    System.out.println("Year must be between 1995 and 2025.");
                } catch (Exception e) {
                    System.out.println("Invalid format! Use (YYYY-MM-DD) for your BirthDate.");
                }
            }

            String placeOfBirth = getValidatedInput(sc, "Place of Birth(e.g. Manila)", "^[a-zA-ZÀ-ÿ ' -]+$");
            String address = getValidatedInput(sc, "Address(e.g 866 Karuhatan, Valenzuela City)", "^[a-zA-Z0-9À-ÿ ,.\\-#]+$");

            String contactNum = getValidatedInput(sc, "Contact Number", "^(09|\\+639)\\d{9}$|^(09|\\+639)\\d{2}-\\d{3}-\\d{4}$");
            String email = getValidatedInput(sc, "Email", "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-z]{2,7}$");

            String civilStatus;
            while (true) {
                System.out.print("Civil Status(Single | Married | Widowed | Divorced | Separated): ");
                civilStatus = sc.nextLine().trim();
                if (civilStatus.equalsIgnoreCase("Single") || civilStatus.equalsIgnoreCase("Married") ||
                        civilStatus.equalsIgnoreCase("Widowed") || civilStatus.equalsIgnoreCase("Divorced") ||
                        civilStatus.equalsIgnoreCase("Separated")) break;
                System.out.println("Invalid input. Enter one of the allowed options.");
            }

            String fatherName = getValidatedInput(sc, "Father Name", "[a-zA-Z ]+");
            String motherName = getValidatedInput(sc, "Mother Name", "[a-zA-Z ]+");

            //======== Appoinment Date Process ========
            LocalDate today = LocalDate.now();
            int currentYear = today.getYear();
            String appointmentDate;
            while (true) {
                System.out.print("Appointment Date (YYYY-MM-DD): ");
                appointmentDate = sc.nextLine().trim();
                try {
                    LocalDate date = LocalDate.parse(appointmentDate, DateTimeFormatter.ISO_LOCAL_DATE);
                    if (date.isBefore(today)) {
                        System.out.println("Appointment date cannot be in the past!");
                    } else if (date.getYear() != currentYear) {
                        System.out.println("Appointment must be within the current year.");
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid format! Please use (YYYY-MM-DD) for your Appoinment Date.");
                }
            }

            // ======== TIME SLOT SELECTION ========
            int slotChoice;
            while (true) {
                System.out.println("Select Time Slot:");
                for (int i = 0; i < timeSlots.length; i++) {
                    System.out.println((i + 1) + ". " + timeSlots[i] + " - " + (MaxSlots - timeSlotCount[i]) + " slots available");
                }
                System.out.print("Enter choice (1-8): ");
                try {
                    slotChoice = Integer.parseInt(sc.nextLine());
                    if (slotChoice >= 1 && slotChoice <= timeSlots.length) {
                        if (timeSlotCount[slotChoice - 1] < MaxSlots) {
                            timeSlotCount[slotChoice - 1]++;
                            break;
                        } else {
                            System.out.println("This time slot is fully booked. Choose another.");
                        }
                    } else {
                        System.out.println("Invalid choice. Enter 1-" + timeSlots.length + ".");
                    }
                }catch(NumberFormatException e) {
                    System.err.println("Invalid input. Enter a number 1-" + timeSlots.length + ".");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Invalid slot selection. Please try again.");
                }
            }

            // ======== CREATE APPLICATION ========
            int refNum = applications.size() + 1;
            PassportInformation app = new PassportInformation(
                    appType, lastName, firstName, middleName, sex, birthDate,
                    placeOfBirth, address, contactNum, email, civilStatus,
                    fatherName, motherName
            );
            app.setAppointmentDate(appointmentDate);
            app.setTimeSlots(new String[]{timeSlots[slotChoice - 1]});
            app.setRefNumber(refNum);

            // ======= REVIEW APPLICATION ========
            System.out.println("\n====== Review Your Application ======");
            app.displayInfo();

            // ====== CONFIRM OR EDIT =========
            int userChoice;
            while (true) {
                System.out.println("\nDo you want to (1) Submit or (2) Edit the application?");
                System.out.print("Enter choice: ");
                try {
                    userChoice = Integer.parseInt(sc.nextLine());
                    if (userChoice == 1) {
                        app.setStatus("Pending");
                        applications.add(app);
                        System.out.println("\n======== Application has been submitted! ==========");
                        System.out.println("Reference Number: " + refNum);
                        System.out.println("Just wait our message in your gmail for the payment. Thank you!");
                        System.out.println("=====================================================");
                        break;
                    } else if (userChoice == 2) {
                        System.out.println("\nReturning to form to make changes...");
                        timeSlotCount[slotChoice - 1]--;
                        break;
                    } else {
                        System.out.println("Invalid choice. Enter 1 or 2.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Enter 1 or 2.");
                }
            }

            if (userChoice == 1) break;
        }
    }
    // ======= Helper Method for the Validation ========
    private String getValidatedInput(Scanner sc, String prompt, String regex) {
        String input;
        while (true) {
            System.out.print(prompt + ": ");
            input = sc.nextLine().trim();
            if (input.matches(regex)) break;
            System.out.println("Invalid input. Please follow the required Information!.");
        }
        return input;
    }

    //====== CHECK STATUS =======
    public void checkStatus(Scanner sc) {
        System.out.print("\nEnter Reference Number: ");
        int ref = sc.nextInt();
        sc.nextLine(); // consume newline
        for (PassportInformation app : applications) {
            if (app.getRefNumber() == ref) {
                System.out.println("\nApplication Found:");
                System.out.println(app);
                return;
            }
        }
        System.out.println("No application found for this reference number.");
    }

    // ======= SHOW REQUIREMENTS =========
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
        System.out.println("4. One Valid ID");
        System.out.println("5. Supporting Documents (if applicable)");
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
