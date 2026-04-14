import java.util.Random;
import java.util.Scanner;

public class TextProcessing {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("========== ЗАВДАННЯ 1 ==========");
        task1(scanner);

        System.out.println("\n========== ЗАВДАННЯ 2 ==========");
        task2();
        
        scanner.close();
    }

    private static void task1(Scanner scanner) {
        System.out.print("Введіть кількість випадкових чисел для генерації: ");
        int count = scanner.nextInt();
        
        Random random = new Random();
        String[] stringNumbers = new String[count];

        System.out.println("\nЗгенеровані числа:");
        for (int i = 0; i < count; i++) {
            int randomNum = random.nextInt(100000); 
            stringNumbers[i] = String.valueOf(randomNum); 
            System.out.print(stringNumbers[i] + " ");
        }
        System.out.println("\n");

        System.out.println("Результат (4-значні числа із сумою цифр < 10):");
        boolean found = false;
        
        for (String strNum : stringNumbers) {
            if (strNum.length() == 4) {
                int digitSum = 0;
                
                for (int i = 0; i < strNum.length(); i++) {
                    digitSum += Character.getNumericValue(strNum.charAt(i));
                }
                
                if (digitSum < 10) {
                    System.out.println("- Число: " + strNum + " (Сума цифр: " + digitSum + ")");
                    found = true;
                }
            }
        }
        
        if (!found) {
            System.out.println("Таких чисел не знайдено.");
        }
    }

    private static void task2() {
        String originalText = "Ця лабораторна робота демонструє базову обробку рядків. " +
                              "Карамель, радар та парасолька — це цікаві слова. " +
                              "Лікар Артем радісно сказав: \"Разом ми велика сила!\" " +
                              "Слово фараон має літеру а відразу після букви р.";

        System.out.println("Вхідні параметри (умови заміни):");
        System.out.println("- Шукаємо літеру «р» або «Р»");
        System.out.println("- Якщо після неї йде «а» або «А», змінюємо її на «о» або «О» відповідно.\n");

        System.out.println("--- ТЕКСТ ДО ОБРОБКИ ---");
        System.out.println(originalText);

        String processedText = originalText
                .replaceAll("(?<=р)а", "о") 
                .replaceAll("(?<=Р)а", "о") 
                .replaceAll("(?<=р)А", "О") 
                .replaceAll("(?<=Р)А", "О"); 

        System.out.println("\n--- ТЕКСТ ПІСЛЯ ОБРОБКИ ---");
        System.out.println(processedText);
    }
}