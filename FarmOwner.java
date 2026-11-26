import java.util.ArrayList;

// Encapsulation: Represents a Farm Owner managing multiple farms
public class FarmOwner {
    private String ownerId;
    private String fullName;
    private String email;
    private String phone;
    private ArrayList<String> ownedFarms;
    
public FarmOwner(String ownerId, String fullName, String email, String phone) {
        this.ownerId = ownerId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.ownedFarms = new ArrayList<>();
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getFullName() {
        return fullName;
    }

    public ArrayList<String> getOwnedFarms() {
        return ownedFarms;
    }

    public void addFarm(String farmName) {
        if (!ownedFarms.contains(farmName)) {
            ownedFarms.add(farmName);
        }
    }

    public void removeFarm(String farmName) {
        ownedFarms.remove(farmName);
    }
}
