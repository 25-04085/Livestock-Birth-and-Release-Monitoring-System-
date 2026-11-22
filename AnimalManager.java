import java.util.ArrayList;

// Manages the ArrayList of Animal objects and handles core monitoring operations
public class AnimalManager {
    private ArrayList<Animal> animalList;
    private int totalCount;
    private int activeCount;
    private int releasedCount;

    public AnimalManager() {
        this.animalList = new ArrayList<>();
        this.totalCount = 0;
        this.activeCount = 0;
        this.releasedCount = 0;
    }

    // Check for duplicate ID
    public boolean isIdExists(String id) {
        for (Animal a : animalList) {
            if (a.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    // Add a new animal record
    public void addAnimal(Animal animal) throws IllegalArgumentException {
        if (isIdExists(animal.getId())) {
            throw new IllegalArgumentException("Animal ID '" + animal.getId() + "' already exists!");
        }
        animalList.add(animal);
        calculateStats();
    }

    // Search an animal by ID
    public Animal searchById(String id) {
        for (Animal a : animalList) {
            if (a.getId().equalsIgnoreCase(id)) {
                return a;
            }
        }
        return null;
    }

    // Record animal release by ID
    public boolean releaseAnimal(String id, String releaseLocation) throws Exception {
        Animal found = searchById(id);
        if (found == null) {
            throw new Exception("Animal ID '" + id + "' not found.");
        }
        if (found.getStatus().equalsIgnoreCase("Released")) {
            throw new Exception("Animal ID '" + id + "' is already marked as RELEASED.");
        }
        found.setStatus("Released");
        found.setReleaseLocation(releaseLocation.isEmpty() ? "Main Field" : releaseLocation);
        calculateStats();
        return true;
    }

    // Display all recorded animals
    public void displayAllAnimals() {
        if (animalList.isEmpty()) {
            System.out.println("[INFO] No animal records found.");
            return;
        }
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.printf("%-8s %-10s %-18s %-12s %-10s %-10s %-12s%n",
                "ID", "TYPE", "BREED", "BIRTH DATE", "STATUS", "TAG NO.", "SECTION");
        System.out.println("---------------------------------------------------------------------------------------");
        for (Animal a : animalList) {
            a.displayDetails(); // Polymorphic call
        }
        System.out.println("---------------------------------------------------------------------------------------");
    }

    // Recalculate total, active, and released counts
    public void calculateStats() {
        totalCount = animalList.size();
        activeCount = 0;
        releasedCount = 0;

        for (Animal a : animalList) {
            if (a.getStatus().equalsIgnoreCase("Active")) {
                activeCount++;
            } else if (a.getStatus().equalsIgnoreCase("Released")) {
                releasedCount++;
            }
        }
    }

    // Display summary statistics
    public void displaySummaryStats() {
        calculateStats();
        System.out.println("--- SUMMARY STATS ---");
        System.out.println("Total Animals    : " + totalCount);
        System.out.println("Active Stock     : " + activeCount);
        System.out.println("Released Animals : " + releasedCount);
    }
}
