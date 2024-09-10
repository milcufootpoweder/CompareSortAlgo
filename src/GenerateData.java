import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenerateData {

    public static void main(String[] args) {
        //set the size
        int[] dataSizes = {10000, 50000, 200000, 500000, 1000000};

        try {
            generateAndStoreDataSets(dataSizes);
        } catch (IOException e) {
            System.err.println("Error generating and storing data sets: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Generates and stores data sets for all specified sizes and cases.
    private static void generateAndStoreDataSets(int[] dataSizes) throws IOException {
        for (int size : dataSizes) {
            List<Integer> bestCase = generateSortedData(size, true);
            List<Integer> worstCase = generateSortedData(size, false);
            List<Integer> averageCase = generateRandomData(size);

            writeDataToCSV(bestCase, "best_case_" + size + ".csv");
            writeDataToCSV(worstCase, "worst_case_" + size + ".csv");
            writeDataToCSV(averageCase, "average_case_" + size + ".csv");

            System.out.println("Generated data sets for size: " + size);
        }
    }

    // Generates a sorted list of integers.
    private static List<Integer> generateSortedData(int size, boolean ascending) {
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            data.add(ascending ? i : size - i);
        }
        return data;
    }

    // Generates a list of integers in random order (shuffled from 0 to size-1).
    private static List<Integer> generateRandomData(int size) {
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            data.add(i); // Add numbers from 0 to size-1
        }
        Collections.shuffle(data); // Shuffle the list randomly
        return data;
    }

    // Writes a list of integers to a CSV file, each value on a new line.
    private static void writeDataToCSV(List<Integer> data, String filename) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Integer value : data) {
                writer.append(String.valueOf(value)); // Write the value
                writer.append("\n"); // Go to the next line for the next value
            }
            writer.flush();
            System.out.println("Data set saved to: " + filename);
        }
    }
}