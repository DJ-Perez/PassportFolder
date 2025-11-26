package PassportFolder;
import java.util.*;

// ========= MAIN CLASS =========
public class Pasupoto {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<PassportInformation> applications = new ArrayList<>();
        AppointmentAccess form = new AppointmentAccess(applications);
        Admin admin = new Admin(applications);

        int mainChoice;
        do {
            System.out.println("\n===== PASSPORT APPOINTMENT SYSTEM =====");
            System.out.println("[1] Appointment");
            System.out.println("[2] Admin Access");
            System.out.println("[3] Exit");
            System.out.print("Enter choice: ");
            mainChoice = sc.nextInt();
            sc.nextLine();

            switch (mainChoice) {
                case 1:
                    Appoinment(sc, form);
                    break;
                case 2:
                    AdminAccess(sc, admin);
                    break;
                case 3:
                    System.out.println("Thank you! Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (mainChoice != 3);
        sc.close();
    }
    //Appointment Access
    public static void Appoinment(Scanner sc, AppointmentAccess form){
        int userChoice;
        do {
            System.out.println("\n===== Appointment =====");
            System.out.println("[1] Apply for Passport");
            System.out.println("[2] Check Application Status");
            System.out.println("[3] Show Passport Requirements");
            System.out.println("[4] Back");
            System.out.print("Enter choice: ");
            userChoice = sc.nextInt();
            sc.nextLine();

            switch (userChoice) {
                case 1:
                    form.applyPassport(sc);
                    break;
                case 2: 
                    form.checkStatus(sc);
                    break;
                case 3:
                    form.showRequirements();
                    break;
                case 4: 
                    System.out.println("Returning to main menu...");
                    break;
                default: 
                    System.out.println("Invalid choice.");
                    break;
            }
        }while (userChoice != 4);
    }
    //Admin Access
    public static void AdminAccess(Scanner sc, Admin admin){
        System.out.print("\nEnter Admin Username: ");
        String userName = sc.nextLine();
        System.out.print("Enter Admin Password: ");
        String password = sc.nextLine();
        if (admin.login(userName, password)) {
            int adminChoice;
            do {
                System.out.println("\n===== ADMIN MENU =====");
                System.out.println("[1] View All Applications");
                System.out.println("[2] Update Application Status");
                System.out.println("[3] Logout");
                System.out.print("Enter choice: ");
                adminChoice = sc.nextInt();
                sc.nextLine();

                switch (adminChoice) {
                    case 1:
                        admin.viewApplications();
                        break;
                    case 2: 
                        admin.updateStatus(sc);
                        break;
                    case 3: 
                        System.out.println("Logging out...");
                        break;
                    default: 
                        System.out.println("Invalid choice.");
                        break;
                }
            } while (adminChoice != 3);
        }else{
            System.out.println("Invalid admin credentials!");
        }
    }
}