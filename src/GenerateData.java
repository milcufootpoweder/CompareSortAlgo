import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenerateData {

    public static void main(String[] args) {
        int[] dataSizes = {10000, 50000, 200000, 500000, 1000000};

        try {
            generateAndStoreDataSets(dataSizes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void generateAndStoreDataSets(int[] dataSizes) throws IOException {
        for (int size : dataSizes) {
            List<Integer> bestCase = generateSortedData(size, true);
            List<Integer> worstCase = generateSortedData(size, false);
            List<Integer> averageCase = generateRandomData(size);

            writeDataToCSV(bestCase, "best_case_" + size + ".csv");
            writeDataToCSV(worstCase, "worst_case_" + size + ".csv");
            writeDataToCSV(averageCase, "average_case_" + size + ".csv");

        }
    }


    private static List<Integer> generateSortedData(int size, boolean ascending) {
        List<Integer> data = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            data.add(ascending ? i : size - i + 1);
        }
        return data;
    }
    private static List<Integer> generateRandomData(int size) {
        List<Integer> data = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            data.add(i);
        }
        Collections.shuffle(data);
        return data;
    }

    private static void writeDataToCSV(List<Integer> data, String filename) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Integer value : data) {
                writer.append(String.valueOf(value));
                writer.append("\n");
            }
            writer.flush();
            System.out.println("Data set saved to: " + filename);
        }
    }
}