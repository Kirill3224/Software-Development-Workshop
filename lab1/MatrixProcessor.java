import java.util.Random;

public class MatrixProcessor {
    public static void main(String[] args) {
        
        System.out.println("Розробник: Прізвище І.О.\n");

        int rows = 2;
        int cols = 3;
        int[][] matrixA = new int[rows][cols];
        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrixA[i][j] = random.nextInt(51);
            }
        }

        System.out.println("- Матриця А (до обробки):");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrixA[i][j] + "\t");
            }
            System.out.println();
        }

        double totalSum = 0;
        int[] rowSums = new int[rows]; 

        for (int i = 0; i < rows; i++) {
            int currentRrowSum = 0;
            for (int j = 0; j < cols; j++) {
                currentRrowSum += matrixA[i][j];
            }
            rowSums[i] = currentRrowSum;
            totalSum += currentRrowSum;
        }
        double average = totalSum / (rows * cols); 

        System.out.println("\n- Матриця А (після обробки):");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrixA[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("\n- Суми елементів матриці за рядками:");
        for (int i = 0; i < rows; i++) {
            System.out.println("Рядок " + (i + 1) + ": " + rowSums[i]);
        }

        System.out.printf("\n- Середнє арифметичне: %.2f\n", average);
    }
}