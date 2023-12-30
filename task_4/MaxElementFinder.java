public class MaxElementFinder extends Thread {
    private int[] row; // строка матрицы.
    private int maxElement;

    public MaxElementFinder(int[] row) {
        this.row = row;
    }

    public void run() {
        maxElement = row[0]; // первая строка
        for (int i = 1; i < row.length; i++) {
            if (row[i] > maxElement) { // является ли текущий элемент больше
                maxElement = row[i]; // если текущий элемент больше, обновляем
            }
        }
    }

    public int getMaxElement() {
        return maxElement;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        MaxElementFinder[] finders = new MaxElementFinder[matrix.length];
// Создается массив finders, который будет хранить потоки для каждой строки матрицы.

        // Создаем потоки для каждой строки матрицы
        for (int i = 0; i < matrix.length; i++) {
            finders[i] = new MaxElementFinder(matrix[i]);
            finders[i].start();
        }

        // Ожидаем завершения работы всех потоков
        try {
            for (MaxElementFinder finder : finders) {
                finder.join(); // блокирует выполнение основного потока до тех пор, пока не завершатся все потоки finders.
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int maxElement = finders[0].getMaxElement();
        for (int i = 1; i < finders.length; i++) { // ищет наибольший элемент среди всех строк.
            if (finders[i].getMaxElement() > maxElement) {
                maxElement = finders[i].getMaxElement(); // найден больше в др строке? записываем как новый
            }
        }

        System.out.println("The largest element in the matrix is: " + maxElement);
    }
}
