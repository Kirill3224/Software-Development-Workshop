import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public double distanceTo(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

public class SixthLab {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashSet<Point> points = new HashSet<>();
        int numberOfPoints = 5;

        System.out.println("=== Лабораторна робота №6 (Варіант 4) ===");
        System.out.println("Введіть координати для " + numberOfPoints + " точок:");

        for (int i = 1; i <= numberOfPoints; i++) {
            System.out.println("\n--- Точка " + i + " ---");
            System.out.print("Координата x: ");
            double x = scanner.nextDouble();
            System.out.print("Координата y: ");
            double y = scanner.nextDouble();

            boolean added = points.add(new Point(x, y));
            if (!added) {
                System.out.println("[Увага] Ця точка вже існує у множині! Введіть іншу.");
                i--; 
            }
        }

        Point bestPoint = null;
        double minDistanceSum = Double.MAX_VALUE;

        System.out.println("\n================ ОБЧИСЛЕННЯ ================");

        for (Point currentPoint : points) {
            double currentSum = 0;
            
            for (Point targetPoint : points) {
                if (!currentPoint.equals(targetPoint)) {
                    currentSum += currentPoint.distanceTo(targetPoint);
                }
            }
            
            System.out.printf("Точка %s -> Сума відстаней до інших: %.4f%n", currentPoint, currentSum);

            if (currentSum < minDistanceSum) {
                minDistanceSum = currentSum;
                bestPoint = currentPoint;
            }
        }

        System.out.println("================ РЕЗУЛЬТАТ =================");
        if (bestPoint != null) {
            System.out.printf("Точка з найменшою сумою відстаней: %s%n", bestPoint);
            System.out.printf("Мінімальна сума: %.4f%n", minDistanceSum);
        }

        scanner.close();
    }
}