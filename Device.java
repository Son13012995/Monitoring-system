public class Device {
    private int id;
    private String name;
    private int powerRating;
    private boolean status;

    public Device(int id, String name, int powerRating, boolean status) {
        this.id = id;
        this.name = name;
        this.powerRating = powerRating;
        this.status = status;
    }

    // Getters và Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getPowerRating() { return powerRating; }
    public void setPowerRating(int powerRating) { this.powerRating = powerRating; }

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
}
