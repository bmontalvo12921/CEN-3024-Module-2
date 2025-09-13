import java.util.*;

import static java.lang.System.*;
/**
*Main Application for Library Systems
* Prompts for File path
* Loads Members from File
* Displays Members List
* Menu Loops until user exists
 */
public class LibraryApp {
    /**
    *Runs the program
     * @param args not used
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        MembershipSystem system = new MembershipSystem();
        System.out.print("Enter path to data file: ");
        String path = scanner.nextLine().trim();

        /*
        *Load file from file path
         */
        system.loadFromFile(path);

        /*
        *Display Members
         */
        system.displayMembers();

        /*
        *Console options MemberShip System Menu
        * Add member
        * Remove Member
        * Display Members
        * Exit
         */
        while (true) {
            System.out.println("\n--- Membership System Menu ---");
            System.out.println("1. Add Member");
            System.out.println("2. Remove Member");
            System.out.println("3. Display Members");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String line = scanner.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(line);
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid option. Pick a function 1-4. ");
                continue;
            }

            switch (choice) {
                case 1:
                    system.addMember(scanner);
                    break;
                case 2:
                    system.removeMember(scanner);
                    break;
                case 3:
                    system.displayMembers();
                    break;
                case 4:
                    System.out.println("Exit");
                    return;
                    default:
                  System.out.println("Invalid choice.");

            }
        }
    }
}
