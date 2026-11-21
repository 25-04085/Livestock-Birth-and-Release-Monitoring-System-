import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

// Main Console Application for Livestock Birth & Release Monitoring System
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AnimalManager manager = new AnimalManager();

        boolean running = true;

        System.out.println("=== LIVESTOCK BIRTH & RELEASE MONITORING SYSTEM ===");

        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("[1] Add Animal");
            System.out.println("[2] Record Release");
            System.out.println("[3] View All");
            System.out.println("[4] Search ID");
            System.out.println("[5] Summary Stats");
            System.out.println("[6] Exit");
            System.out.print("Choice: ");

            String choiceInput = scanner.nextLine().trim();

            switch (choiceInput) {
                case "1":
                    System.out.println("\n--- ADD ANIMAL ---");
                    try {
                        System.out.print("Enter ID (e.g., ANI-001): ");
                        String id = scanner.nextLine().trim();
                        if (id.isEmpty()) {
                            throw new IllegalArgumentException("Animal ID cannot be empty.");
                        }

                        System.out.print("Type (e.g., Cow, Goat, Eagle): ");
                        String type = scanner.nextLine().trim();
                        if (type.isEmpty()) {
                            throw new IllegalArgumentException("Animal Type cannot be empty.");
                        }

                        System.out.print("Breed: ");
                        String breed = scanner.nextLine().trim();
                        if (breed.isEmpty()) {
                            throw new IllegalArgumentException("Breed cannot be empty.");
                        }

                        System.out.print("Birth Date (YYYY-MM-DD): ");
                        String dateStr = scanner.nextLine().trim();
                        LocalDate birthDate = LocalDate.parse(dateStr);

                        System.out.print("Tag Number (optional, press Enter for N/A): ");
                        String tag = scanner.nextLine().trim();
                        if (tag.isEmpty()) tag = "N/A";

                        System.out.print("Farm Section (optional, press Enter for General): ");
                        String section = scanner.nextLine().trim();
                        if (section.isEmpty()) section = "General";

                        Livestock animal = new Livestock(id, type, breed, birthDate, tag, section);
                        manager.addAnimal(animal);
                        System.out.println("[SUCCESS] Record added.");

                    } catch (DateTimeParseException e) {
                        System.out.println("[ERROR] Invalid date format! Please use YYYY-MM-DD (e.g., 2026-03-15).");
                    } catch (IllegalArgumentException e) {
                        System.out.println("[ERROR] " + e.getMessage());
                    }
                    break;

                case "2":
                    System.out.println("\n--- RECORD RELEASE ---");
                    System.out.print("Enter Animal ID: ");
                    String releaseId = scanner.nextLine().trim();

                    Animal target = manager.searchById(releaseId);
                    if (target == null) {
                        System.out.println("[ERROR] Animal ID '" + releaseId + "' not found.");
                        break;
                    }

                    System.out.print("Release Location (e.g., North Pasture / Wildlife Sanctuary): ");
                    String location = scanner.nextLine().trim();

                    System.out.print("Release this animal? (Y/N): ");
                    String confirm = scanner.nextLine().trim();

                    if (confirm.equalsIgnoreCase("Y")) {
                        try {
                            manager.releaseAnimal(releaseId, location);
                            System.out.println("[SUCCESS] Status updated to RELEASED.");
                        } catch (Exception e) {
                            System.out.println("[ERROR] " + e.getMessage());
                        }
                    } else {
                        System.out.println("[CANCELLED] Release operation aborted.");
                    }
                    break;

                case "3":
                    System.out.println("\n--- ALL RECORDS ---");
                    manager.displayAllAnimals();
                    break;

                case "4":
                    System.out.println("\n--- SEARCH ID ---");
                    System.out.print("Enter Animal ID: ");
                    String searchId = scanner.nextLine().trim();
                    Animal found = manager.searchById(searchId);

                    if (found != null) {
                        System.out.println("Found: " + found.getId() + " | " + found.getType() + " | "
                                + found.getBreed() + " | " + found.getBirthDate() + " | " + found.getStatus()
                                + " | Age: " + found.getAgeSummary()
                                + (found.getStatus().equals("Released") ? " | Released To: " + found.getReleaseLocation() : ""));
                    } else {
                        System.out.println("[INFO] No record found for ID: " + searchId);
                    }
                    break;

                case "5":
                    System.out.println();
                    manager.displaySummaryStats();
                    break;

                case "6":
                    System.out.println("Exiting program... Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("[ERROR] Invalid choice. Please enter a number between 1 and 6.");
            }
        }
        scanner.close();
    }
}