import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class RecordBook {
    private String studentName;
    private String bookNumber;
    private List<Session> sessions;

    public RecordBook(String studentName, String bookNumber) {
        this.studentName = studentName;
        this.bookNumber = bookNumber;
        this.sessions = new ArrayList<>();
        System.out.println("[СИСТЕМА] Створено залікову книжку №" + bookNumber + " для студента " + studentName);
    }

    public class Session {
        private String discipline;
        private String controlForm; 
        private int grade;          

        public Session(String discipline, String controlForm, int grade) {
            this.discipline = discipline;
            this.controlForm = controlForm;
            this.grade = grade;
        }

        public String getDiscipline() {
            return discipline;
        }

        public String getControlForm() {
            return controlForm;
        }

        @Override
        public String toString() {
            return String.format("| %-25s | %-15s | %-6d |", discipline, controlForm, grade);
        }
    }

    public void addSessionRecord(String discipline, String controlForm, int grade) {
        Session newSession = new Session(discipline, controlForm, grade);
        sessions.add(newSession);
        System.out.println("[СИСТЕМА] Додано новий запис про сесію: " + discipline + " (" + controlForm + ")");
    }

    public void printAllRecords() {
        System.out.println("\n==========================================================");
        System.out.println(" ЗАЛІКОВА КНИЖКА №: " + bookNumber + " | СТУДЕНТ: " + studentName);
        System.out.println("==========================================================");
        System.out.println(String.format("| %-25s | %-15s | %-6s |", "Дисципліна", "Форма контролю", "Оцінка"));
        System.out.println("----------------------------------------------------------");
        
        if (sessions.isEmpty()) {
            System.out.println("| Записів про складені сесії поки немає.                 |");
        } else {
            for (Session session : sessions) {
                System.out.println(session.toString());
            }
        }
        System.out.println("==========================================================\n");
    }

    public void searchByDiscipline(String keyword) {
        System.out.println("[СИСТЕМА] Виконується пошук за дисципліною: \"" + keyword + "\"");
        boolean found = false;
        
        System.out.println("----------------------------------------------------------");
        for (Session session : sessions) {
            if (session.getDiscipline().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(session.toString());
                found = true;
            }
        }
        System.out.println("----------------------------------------------------------");
        
        if (!found) {
            System.out.println("[СИСТЕМА] Записів із вказаною дисципліною не знайдено.");
        }
    }
}

public class FifthLab {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Лабораторна робота №5 (Варіант 4) ===");
        System.out.print("Введіть ПІБ студента: ");
        String name = scanner.nextLine();
        System.out.print("Введіть номер залікової книжки: ");
        String number = scanner.nextLine();

        RecordBook recordBook = new RecordBook(name, number);

        boolean running = true;
        while (running) {
            System.out.println("\n--- ГОЛОВНЕ МЕНЮ ---");
            System.out.println("1. Додати запис про іспит/залік");
            System.out.println("2. Вивести всю залікову книжку");
            System.out.println("3. Знайти інформацію за дисципліною");
            System.out.println("0. Вийти з програми");
            System.out.print("Оберіть дію: ");
            
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Введіть назву дисципліни: ");
                    String discipline = scanner.nextLine();
                    System.out.print("Введіть форму контролю (Іспит/Залік/Курсова): ");
                    String form = scanner.nextLine();
                    System.out.print("Введіть отриману оцінку (бали): ");
                    int grade;
                    try {
                        grade = Integer.parseInt(scanner.nextLine());
                        recordBook.addSessionRecord(discipline, form, grade);
                    } catch (NumberFormatException e) {
                        System.out.println("[ПОМИЛКА] Оцінка має бути числом! Запис не додано.");
                    }
                    break;
                case "2":
                    recordBook.printAllRecords();
                    break;
                case "3":
                    System.out.print("Введіть назву дисципліни або її частину для пошуку: ");
                    String query = scanner.nextLine();
                    recordBook.searchByDiscipline(query);
                    break;
                case "0":
                    System.out.println("[СИСТЕМА] Завершення роботи програми.");
                    running = false;
                    break;
                default:
                    System.out.println("[ПОМИЛКА] Невідома команда. Спробуйте ще раз.");
            }
        }
        scanner.close();
    }
}