import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FitnessTracker {
    private List<Workout> workouts = new ArrayList<>();
    private int totalCaloriesBurned = 0;
    private static final String FILE_NAME = "workouts.dat";

    public void addWorkout(Workout workout) {
        workouts.add(workout);
        totalCaloriesBurned += workout.getCaloriesBurned();
        saveWorkouts();
    }

    public void printSummary() {
        System.out.println("Total Workouts: " + workouts.size());
        System.out.println("Total Calories Burned: " + totalCaloriesBurned);
        for (Workout workout : workouts) {
            System.out.println(workout);
        }
    }

    private void saveWorkouts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(workouts);
            oos.writeInt(totalCaloriesBurned);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadWorkouts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            workouts = (List<Workout>) ois.readObject();
            totalCaloriesBurned = ois.readInt();
        } catch (FileNotFoundException e) {
            System.out.println("No previous workout data found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FitnessTracker tracker = new FitnessTracker();
        tracker.loadWorkouts();

        Scanner scanner = new Scanner(System.in);
        boolean continueInput = true;

        while (continueInput) {
            System.out.println("Enter workout type (e.g., running, cycling): ");
            String type = scanner.nextLine();

            System.out.println("Enter duration in minutes: ");
            int duration = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter distance in kilometers: ");
            double distance = Double.parseDouble(scanner.nextLine());

            System.out.println("Enter calories burned: ");
            int caloriesBurned = Integer.parseInt(scanner.nextLine());

            Workout workout = new Workout(type, duration, distance, caloriesBurned);
            tracker.addWorkout(workout);

            System.out.println("Would you like to enter another workout? (yes/no): ");
            String response = scanner.nextLine();
            continueInput = response.equalsIgnoreCase("yes");
        }

        tracker.printSummary();
        scanner.close();
    }
}
