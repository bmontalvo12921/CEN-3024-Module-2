import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
/**
*Membership systems handles all procedures for the console program.
* Add,
* Remove,
* Display for members
*
 */

public class MembershipSystem {
    /**
    *Membership programs holds all procedure for the Program
    * Loads file from File path
    * hold methods to add, remove, and display all members in the program
    * it also catches any inputs outside of the parameters defined below
    * Loops menu until the user decides to exit
     */
    private final List<Members> membersList = new ArrayList<>();
    /**
     * Loads txt file from file
     * @param path file path  to the txt file
     */
    public void loadFromFile(String path) {
        int loaded = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;

                /*
                *split into max 4 parts so addresses with dashes are okay
                 */
                String[] parts = line.split("-", 4);
                if (parts.length < 4) {
                    System.out.println("Skipping bad line: " + line);
                    continue;
                }

                String id = parts[0].trim();
                String name = parts[1].trim();
                String address = parts[2].trim();
                String fineStr = parts[3].trim();
                /*
                *Catches any input outside of parameters and loops again
                 */
                if (!id.matches("\\d{7}")) {
                    System.out.println("Skipping (bad ID): " + line);
                    continue;
                }

                double fine;
                try {
                    fine = Double.parseDouble(fineStr);
                } catch (NumberFormatException e) {
                    System.out.println("Skipping (bad fine): " + line);
                    continue;
                }

                if (fine < 0 || fine > 250) {
                    System.out.println("Skipping (fine out of range 0–250): " + line);
                    continue;
                }

                /*
                 *no duplicates by ID
                 */
                if (findById(id) != null) {
                    System.out.println("Skipping (duplicate ID): " + id);
                    continue;
                }

                membersList.add(new Members(id, name, address, fine));
                loaded++;
            }
            System.out.println("Loaded " + loaded + " member(s) from file.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

   /**
   *Displays all Members on the list
    */
    public void displayMembers() {
        if (membersList.isEmpty()) {
            System.out.println("No members in the system.");
            return;
        }
        /*
        *Sort by ID so the list is stable/clean
         */
        membersList.sort(Comparator.comparing(Members::getId));
        System.out.println("\n--- Members (" + membersList.size() + ") ---");
        for (Members m : membersList) {
            System.out.println(m);
        }
        System.out.println("---------------------------\n");
    }

    /**
    *adds new members
    * catches any invalid id
     */
    public void addMember(Scanner sc) {
        System.out.print("Enter 7-digit ID: ");
        String id = sc.nextLine().trim();
        if (!id.matches("\\d{7}")) {
            System.out.println("Invalid ID. Must be exactly 7 digits.");
            return;
        }
        if (findById(id) != null) {
            System.out.println("That ID already exists.");
            return;
        }

        System.out.print("Enter full name: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");

            return;
        }

        System.out.print("Enter address: ");
        String address = sc.nextLine().trim();
        if (address.isEmpty()) {
            System.out.println("Address cannot be empty.");
            return;
        }

        System.out.print("Enter fine (0–250): ");
        String fineStr = sc.nextLine().trim();
        double fine;
        try {
            fine = Double.parseDouble(fineStr);
        } catch (NumberFormatException e) {
            System.out.println("Fine must be a number.");
            return;
        }
        if (fine < 0 || fine > 250) {
            System.out.println("Fine must be between 0 and 250.");
            return;
        }

        membersList.add(new Members(id, name, address, fine));
        System.out.println("Member added.");
        displayMembers();
        // show updated list
    }


    /**
     *  Removes Members through id number
      */
    public void removeMember(Scanner sc) {
        System.out.print("Enter ID to remove: ");
        String id = sc.nextLine().trim();
        Members found = findById(id);
        if (found == null) {
            System.out.println("No member found with ID " + id);
            return;
        }
        membersList.remove(found);
        System.out.println("Member removed.");
        displayMembers(); /* show updated list */
    }

    /**
     * Find by ID
     */
    private Members findById(String id) {
        for (Members m : membersList) {
            if (m.getId().equals(id)) return m;
        }
        return null;
    }
}
