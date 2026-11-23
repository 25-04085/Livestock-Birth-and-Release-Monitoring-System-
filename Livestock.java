import java.time.LocalDate;

// Inheritance: Livestock extends Animal
public class Livestock extends Animal {
    private String tagNumber;
    private String farmSection;

    public Livestock(String id, String type, String breed, LocalDate birthDate, String tagNumber, String farmSection) {
        super(id, type, breed, birthDate);
        this.tagNumber = tagNumber;
        this.farmSection = farmSection;
    }

    public String getTagNumber() {
        return tagNumber;
    }

    public String getFarmSection() {
        return farmSection;
    }

    // Polymorphism: Overriding displayDetails()
    @Override
    public void displayDetails() {
        System.out.printf("%-8s %-10s %-18s %-12s %-10s %-10s %-12s%n",
                getId(), getType(), getBreed(), getBirthDate(), getStatus(), tagNumber, farmSection);
    }
}
