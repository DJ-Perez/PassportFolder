package PassportFolder;
import java.util.Arrays;

class PassportInformation {
    private String firstName, middleName, lastName, fatherName, motherName;
    private String sex, applicationType, civilStatus, placeofBirth, birthDate, address, email, contactNum, status, appointmentDate;
    private int referenceNumber;
    private String[] timeSlots;

    public PassportInformation( String appType, String lastName, String firstName, String middleName, String sex, String birthDate, 
                                String placeofBirth, String address, String contactNum, String email, String civilStatus, String fatherName, String motherName){
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
        this.fatherName = fatherName;
        this.motherName = motherName;
    // public void setIssueDate(String issueDate) {
    //     this.issueDate = issueDate;
    // }
    // public String getIssueDate() {
    //     return issueDate;
    // }
    // public void setIssueTime(String issueTime) {
    //     this.issueTime = issueTime;
    // }
    // public String getIssueTime() {
    //     return issueTime;
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
    
    public void displayInfo(){
        System.out.println("Name: " + firstName + " " + middleName + " " + lastName);
        System.out.println("Sex: " + sex);
        System.out.println("Birthdate: " + birthDate);
        System.out.println("Place of Birth: " + placeofBirth);
        System.out.println("Address: " + address);
        System.out.println("Contact Number: " + contactNum);
        System.out.println("Email: " + email);
        System.out.println("Civil Status: " + civilStatus);
        System.out.println("Father Name: " + fatherName);
        System.out.println("Mother Name: " + motherName);
        System.out.println("Appointment Date: " + appointmentDate);
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

