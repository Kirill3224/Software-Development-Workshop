import java.io.*;
import java.util.*;
import java.util.regex.*;

public class FourthLab {

    public static void main(String[] args) {
        Scanner consoleScanner = new Scanner(System.in);

        System.out.println("=== Лабораторна робота №4 (Варіант 4) ===");

        System.out.print("Введіть шлях до вхідного файлу (наприклад, input.txt): ");
        String inputFileName = consoleScanner.nextLine();

        System.out.print("Введіть шлях до вихідного файлу (наприклад, output.txt): ");
        String outputFileName = consoleScanner.nextLine();

        processFile(inputFileName, outputFileName);
        
        consoleScanner.close();
    }

    private static void processFile(String inputFile, String outputFile) {
        System.out.println("\nПочаток обробки файлу...");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            int lineNumber = 1;
            Pattern wordPattern = Pattern.compile("[\\p{L}']+"); 

            while ((line = reader.readLine()) != null) {
                Matcher matcher = wordPattern.matcher(line);
                List<int[]> wordsToRemove = new ArrayList<>();

                while (matcher.find()) {
                    String word = matcher.group();
                    if (word.length() >= 3 && word.length() <= 5) {
                        wordsToRemove.add(new int[]{matcher.start(), matcher.end()});
                    }
                }

                if (wordsToRemove.size() % 2 != 0) {
                    wordsToRemove.remove(wordsToRemove.size() - 1); 
                }

                StringBuilder newLine = new StringBuilder();
                int currentIndex = 0;
                
                for (int[] bounds : wordsToRemove) {
                    newLine.append(line, currentIndex, bounds[0]);
                    currentIndex = bounds[1]; 
                }
                newLine.append(line.substring(currentIndex));

                String processedLine = newLine.toString().replaceAll(" +", " ");

                writer.write(processedLine);
                writer.newLine();
                System.out.println("Рядок " + lineNumber + ": знайдено та видалено " + wordsToRemove.size() + " слів.");
                lineNumber++;
            }
            
            System.out.println("\nОбробку успішно завершено! Результат записано у файл: " + outputFile);

        } catch (FileNotFoundException e) {
            System.out.println("Помилка: Вхідний або вихідний файл не знайдено! Перевірте шлях до файлу.");
        } catch (IOException e) {
            System.out.println("Помилка вводу/виводу під час роботи з файлами: " + e.getMessage());
        }
    }
}