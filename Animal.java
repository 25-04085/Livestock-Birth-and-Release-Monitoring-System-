import java.time.LocalDate;
import java.time.Period;

// Abstraction & Encapsulation: Base abstract class for all Animals
public abstract class Animal {
    private String id;
    private String type;
    private String breed;
    private LocalDate birthDate;
    private String status; // "Active" or "Released"
    private String releaseLocation;

    public Animal(String id, String type, String breed, LocalDate birthDate) {
        this.id = id;
        this.type = type;
        this.breed = breed;
        this.birthDate = birthDate;
        this.status = "Active";
        this.releaseLocation = "N/A";
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getBreed() {
        return breed;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReleaseLocation() {
        return releaseLocation;
    }

    public void setReleaseLocation(String releaseLocation) {
        this.releaseLocation = releaseLocation;
    }

    // Method to compute age in months/days
    public String getAgeSummary() {
        Period period = Period.between(birthDate, LocalDate.now());
        if (period.isNegative()) {
            return "0 mos (Upcoming)";
        }
        return period.getYears() + "y " + period.getMonths() + "m";
    }

    // Abstract method for Polymorphism
    public abstract void displayDetails();
}
