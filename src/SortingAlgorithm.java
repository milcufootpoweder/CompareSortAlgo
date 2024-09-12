import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortingAlgorithm {

    private static long statementCount;

    public static void main(String[] args) {
        // Define data set sizes
        int[] dataSizes = {10000, 50000, 200000, 500000, 1000000};

        try {
            for (int size : dataSizes) {
                // Load data from CSV files
                List<Integer> bestCase = loadDataFromFile("best_case_" + size + ".csv");
                List<Integer> worstCase = loadDataFromFile("worst_case_" + size + ".csv");
                List<Integer> averageCase = loadDataFromFile("average_case_" + size + ".csv");

                // Test each sorting algorithm and print results
                System.out.println("Data Set Size: " + size);
                testSortingAlgorithm("Bubble Sort", new ArrayList<>(bestCase), new ArrayList<>(worstCase), new ArrayList<>(averageCase));
                testSortingAlgorithm("Insertion Sort", new ArrayList<>(bestCase), new ArrayList<>(worstCase), new ArrayList<>(averageCase));
                testSortingAlgorithm("Selection Sort", new ArrayList<>(bestCase), new ArrayList<>(worstCase), new ArrayList<>(averageCase));
                System.out.println("--------------------");
            }
        } catch (IOException e) {
            System.err.println("Error reading data files: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void testSortingAlgorithm(String algorithmName, List<Integer> bestCase, List<Integer> worstCase, List<Integer> averageCase) {
        System.out.println("\n" + algorithmName + ":");
        statementCount = 0;
        sort(algorithmName, new ArrayList<>(bestCase));
        System.out.println("Best Case: " + statementCount + " statements");
        // System.out.println("Sorted Data: " + bestCase); // Optional: Print sorted array

        statementCount = 0;
        sort(algorithmName, new ArrayList<>(worstCase));
        System.out.println("Worst Case: " + statementCount + " statements");
        // System.out.println("Sorted Data: " + worstCase); // Optional: Print sorted array

        statementCount = 0;
        sort(algorithmName, new ArrayList<>(averageCase));
        System.out.println("Average Case: " + statementCount + " statements");
        // System.out.println("Sorted Data: " + averageCase); // Optional: Print sorted array
    }

    private static void sort(String algorithmName, List<Integer> data) {
        switch (algorithmName) {
            case "Bubble Sort":
                bubbleSort(data);
                break;
            case "Insertion Sort":
                insertionSort(data);
                break;
            case "Selection Sort":
                selectionSort(data);
                break;
            default:
                System.err.println("Invalid sorting algorithm specified.");
                System.exit(1);
        }
    }

    // Bubble Sort Implementation
    private static void bubbleSort(List<Integer> arr) {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            statementCount++; // Outer loop statement
            for (int j = 0; j < n - i - 1; j++) {
                statementCount++; // Inner loop statement
                statementCount++; // Comparison statement
                if (arr.get(j) > arr.get(j + 1)) {
                    statementCount += 3; // 3 statements for swap
                    Collections.swap(arr, j, j + 1);
                }
            }
        }
    }

    // Insertion Sort Implementation
    private static void insertionSort(List<Integer> arr) {
        int n = arr.size();
        for (int i = 1; i < n; ++i) {
            statementCount++; // Outer loop statement
            int key = arr.get(i);
            int j = i - 1;
            statementCount++; // Assignment statement

            statementCount++; // Comparison statement for while loop
            while (j >= 0 && arr.get(j) > key) {
                statementCount++; // While loop statement
                arr.set(j + 1, arr.get(j));
                j = j - 1;
                statementCount++; // Assignment statement
            }
            arr.set(j + 1, key);
            statementCount++; // Assignment statement
        }
    }

    // Selection Sort Implementation
    private static void selectionSort(List<Integer> arr) {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            statementCount++; // Outer loop statement
            int minIndex = i;
            statementCount++; // Assignment statement
            for (int j = i + 1; j < n; j++) {
                statementCount++; // Inner loop statement
                statementCount++; // Comparison statement
                if (arr.get(j) < arr.get(minIndex)) {
                    minIndex = j;
                    statementCount++; // Assignment statement
                }
            }

            statementCount++; // Comparison statement
            if (minIndex != i) {
                statementCount += 3; // 3 statements for swap
                Collections.swap(arr, minIndex, i);
            }
        }
    }


    // Function to load data from a CSV file (Vertical/Row-wise data)
    private static List<Integer> loadDataFromFile(String filename) throws IOException {
        List<Integer> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(Integer.parseInt(line.trim()));
            }
        }
        return data;
    }
}