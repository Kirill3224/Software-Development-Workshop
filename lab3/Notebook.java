import java.util.Scanner;
import java.util.regex.Pattern;

class Abonent {
    private String lastName;
    private String address;
    private String phone;
    private String info;

    public Abonent(String lastName, String address, String phone, String info) {
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.info = info;
    }

    public String getLastName() { return lastName; }
    public String getPhone() { return phone; }

    public static void printHeader() {
        System.out.println("\n" + "=".repeat(85));
        System.out.printf("| %-3s | %-15s | %-20s | %-15s | %-20s |\n", "№", "Прізвище", "Адреса", "Телефон", "Інфо");
        System.out.println("-".repeat(85));
    }

    public void printAsRow(int index) {
        System.out.printf("| %-3d | %-15s | %-20s | %-15s | %-20s |\n", 
                index, lastName, address, phone, info);
    }
    
    public static void printFooter() {
        System.out.println("=".repeat(85));
    }
}

public class Notebook {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Abonent[] notebook = new Abonent[5];

        System.out.println("=== Введення даних для записної книжки (мінімум 5 записів) ===");

        for (int i = 0; i < notebook.length; i++) {
            System.out.println("\nСтворення запису #" + (i + 1));
            notebook[i] = createAbonentWithValidation(scanner);
        }

        printFullTable(notebook, "ПОВНИЙ СПИСОК АБОНЕНТІВ");

        System.out.print("\nВведіть першу літеру для пошуку прізвищ: ");
        String letter = scanner.nextLine().trim();
        searchByLetter(notebook, letter);

        searchMobileUsers(notebook);

        scanner.close();
    }

    private static Abonent createAbonentWithValidation(Scanner sc) {
        while (true) {
            try {
                System.out.print("Прізвище: ");
                String ln = sc.nextLine().trim();
                if (ln.isEmpty() || !Pattern.matches("[А-Яа-яІіЇїЄєҐґA-Za-z\\-']+", ln)) 
                    throw new Exception("Прізвище не може бути порожнім і має містити лише літери!");

                System.out.print("Адреса: ");
                String addr = sc.nextLine().trim();
                if (addr.isEmpty()) throw new Exception("Адреса не може бути порожньою!");

                System.out.print("Телефон (напр. 0671234567): ");
                String ph = sc.nextLine().trim();
                if (!Pattern.matches("[0-9+\\-()\\s]{5,15}", ph)) 
                    throw new Exception("Некоректний формат телефону!");

                System.out.print("Додаткова інформація: ");
                String inf = sc.nextLine().trim();

                return new Abonent(ln, addr, ph, inf);

            } catch (Exception e) { 
                System.out.println("ПОМИЛКА ВВОДУ: " + e.getMessage());
                System.out.println("Будь ласка, повторіть введення всього запису.");
            }
        }
    }

    private static void printFullTable(Abonent[] list, String title) {
        System.out.println("\n" + title);
        Abonent.printHeader();
        for (int i = 0; i < list.length; i++) {
            list[i].printAsRow(i + 1);
        }
        Abonent.printFooter();
    }

    private static void searchByLetter(Abonent[] list, String letter) {
        System.out.println("\nРезультати пошуку за літерою '" + letter + "':");
        boolean found = false;
        int count = 1;
        
        for (Abonent a : list) {
            if (a.getLastName().toLowerCase().startsWith(letter.toLowerCase())) {
                if (!found) Abonent.printHeader();
                a.printAsRow(count++);
                found = true;
            }
        }
        
        if (found) Abonent.printFooter();
        else System.out.println("Абонентів, чиє прізвище починається на '" + letter + "', не знайдено.");
    }

    private static void searchMobileUsers(Abonent[] list) {
        System.out.println("\nСписок абонентів з мобільними телефонами (10 цифр або формат +38...):");
        boolean found = false;
        int count = 1;

        for (Abonent a : list) {
            String cleanPhone = a.getPhone().replaceAll("[^0-9+]", "");
            if (cleanPhone.startsWith("+") || cleanPhone.length() >= 10) {
                if (!found) Abonent.printHeader();
                a.printAsRow(count++);
                found = true;
            }
        }
        
        if (found) Abonent.printFooter();
        else System.out.println("Мобільних номерів у базі не знайдено."); 
    }
}