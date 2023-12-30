import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Задание 1: Нахождение среднего арифметического элементов массива
        int[] arr = {1, 2, 3, 4, 5};
        int sum = 0;
        try {
            for (int value : arr) {
                sum += value;
            }
            double average = (double) sum / arr.length;
            System.out.println("Среднее арифметическое: " + average);
        } catch (Exception e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
        }

        // Задание 2: Копирование содержимого файла в другой файл
        FileInputStream sourceFile = null;
        FileOutputStream destinationFile = null;

        try {
            sourceFile = new FileInputStream("source.txt");
            destinationFile = new FileOutputStream("destination.txt");

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, bytesRead);
            }

            System.out.println("Файл успешно скопирован.");
        } catch (IOException e) {
            System.err.println("Произошла ошибка при копировании файла: " + e.getMessage());
        } finally {//........................................................
            try {
                if (sourceFile != null) {
                    sourceFile.close();
                }
            } catch (IOException e) {
                System.err.println("Ошибка при закрытии исходного файла: " + e.getMessage());
            }
            try {
                if (destinationFile != null) {
                    destinationFile.close();
                }
            } catch (IOException e) {
                System.err.println("Ошибка при закрытии целевого файла: " + e.getMessage());
            }
        }

        // Задание 3: Обработка пользовательского исключения CustomAgeException
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите возраст пользователя: ");
            int age = scanner.nextInt();

            if (age < 0 || age > 120) {
                throw new CustomAgeException("Недопустимый возраст");
            }

            System.out.println("Возраст пользователя: " + age);
        } catch (CustomAgeException e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
            logException(e);
        } catch (Exception e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
        }
    }

    private static void logException(Exception e) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("exception.log", true))) {
            writer.println("Exception: " + e.getMessage());
            e.printStackTrace(writer);
        } catch (IOException ioException) {
            System.err.println("Ошибка при записи лога исключения: " + ioException.getMessage());
        }
    }
}

class CustomAgeException extends Exception {
    public CustomAgeException(String message) {
        super(message);
    }
}
