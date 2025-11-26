package PassportFolder;
import java.util.*;

class Admin{
    private ArrayList<PassportInformation> applications = new ArrayList<>();
    private final String adminUser = "admin09";
    private final String adminPass = "1234";

    public Admin(ArrayList<PassportInformation> applications) {
        this.applications = applications;
    }

    public boolean login(String username, String password) {
        return username.equals(adminUser) && password.equals(adminPass);
    }

    public void viewApplications() {
        if (applications.isEmpty()) {
            System.out.println("\nNo applications found.");
            return;
        }else{
            System.out.println("\n======== ALL APPLICATIONS =======");
            for (PassportInformation app : applications) {
            System.out.println(app);
            }
        }
    }

    public void updateStatus(Scanner sc) {
    System.out.print("\nEnter Reference Number: ");
    int ref = sc.nextInt();
    sc.nextLine();
    for (PassportInformation app : applications) {
        if (app.getRefNumber() == ref) {
            System.out.println("Current Status: " + app.getStatus());

            System.out.print("Enter new status (Approved | Declined): ");
            String newStatus = sc.nextLine().trim();

            while(!newStatus.equalsIgnoreCase("Approved") && !newStatus.equalsIgnoreCase("Declined")){
                System.out.println("Invalid status. Please enter Approved or Declined.");
                System.out.print("Input here: ");
                newStatus = sc.nextLine().trim();
            }
            app.setStatus(newStatus);
            System.out.println("Status updated!");
            return;
        }
    }
    System.out.println("Application not found.");
    }
}