import java.io.Serializable;

public class Workout implements Serializable {
    private static final long serialVersionUID = 1L;

    private String type;
    private int duration;
    private double distance;
    private int caloriesBurned;

    public Workout(String type, int duration, double distance, int caloriesBurned) {
        this.type = type;
        this.duration = duration;
        this.distance = distance;
        this.caloriesBurned = caloriesBurned;
    }

    // Getters and setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(int caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    @Override
    public String toString() {
        return String.format("Type: %s, Duration: %d min, Distance: %.2f km, Calories Burned: %d",
                type, duration, distance, caloriesBurned);
    }
}
