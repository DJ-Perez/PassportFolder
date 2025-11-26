package PassportFolder;
import java.util.Arrays;

class PassportInformation {
    private String firstName, middleName, lastName, sex;
    private String applicationType, civilStatus, placeofBirth, birthDate, address, email, contactNum, status, appointmentDate;
    private int referenceNumber;
    private String[] timeSlots;

    public PassportInformation( String appType, String lastName, String firstName, String middleName, String sex, String birthDate, 
                                String placeofBirth, String address, String contactNum, String email, String civilStatus){
        applicationType = appType;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.sex = sex;
        this.birthDate = birthDate;
        this.placeofBirth = placeofBirth;
        this.address = address;
        this.contactNum = contactNum;
        this.email = email;
        this.civilStatus = civilStatus;
        status = "Pending";
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
    public String getAppointmentDate() {
        return appointmentDate;
    }
    public void setTimeSlots(String[] timeSlots) {
        this.timeSlots = timeSlots;
    }
    public String[] getTimeSlots() {
        return timeSlots;
    }
    public void setRefNumber(int referenceNumber) { 
        this.referenceNumber = referenceNumber; 
    }
    public int getRefNumber() { 
        return referenceNumber; 
    }
    public void setStatus(String status) { 
        this.status = status; 
    }
    public String getStatus() { 
        return status; 
    }
    // Reciept of Appointment 
    public String toString() {
        return "Ref#: " + referenceNumber + 
        " | Type: "  + applicationType + 
        " | Name: " + lastName + ", " + firstName + " "+ middleName + " " +
        " | Status: " + status + 
        " | Appointment: " + appointmentDate + 
        " | Time Slots: " + Arrays.toString(timeSlots);
    }
}

/*
System.out.print("Application Type(New|Renewal): ");
String appType = sc.nextLine();
if (appType.isEmpty()) appType = "New";

System.out.print("Last Name: ");
String lastName = sc.nextLine();
if (lastName.isEmpty()) lastName = "N/A";

System.out.print("First Name: ");
String firstName = sc.nextLine();
if (firstName.isEmpty()) firstName = "N/A";

System.out.print("Middle Name: ");
String middleName = sc.nextLine();
if (middleName.isEmpty()) middleName = "N/A";

System.out.print("Sex: ");
String sex = sc.nextLine();
if (sex.isEmpty()) sex = "Unspecified";

System.out.print("Birthdate(YYYY-MM-DD): ");
String birthDate = sc.nextLine();
if (birthDate.isEmpty()) birthDate = "2000-01-01";

System.out.print("Place of Birth: ");
String placeofBirth = sc.nextLine();
if (placeofBirth.isEmpty()) placeofBirth = "Unknown";

System.out.print("Address: ");
String address = sc.nextLine();
if (address.isEmpty()) address = "No Address";

System.out.print("Contact Number: ");
String contactNum = sc.nextLine();
if (contactNum.isEmpty()) contactNum = "0000000000";

System.out.print("Email: ");
String email = sc.nextLine();
if (email.isEmpty()) email = "noemail@example.com";

System.out.print("Civil Status: ");
String civilStatus = sc.nextLine();
if (civilStatus.isEmpty()) civilStatus = "Single";
*/

