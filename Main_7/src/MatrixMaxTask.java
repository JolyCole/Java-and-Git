public class MatrixMaxTask {
    public static void main(String[] args) {
        try {
            // Инициализация двумерного массива (матрицы)
            int[][] matrix = {/* инициализация матрицы */};

            // Массив для хранения потоков, обрабатывающих каждую строку матрицы
            MaxThread[] threads = new MaxThread[matrix.length];

            // Переменная для хранения максимального значения в матрице
            int max = Integer.MIN_VALUE;

            // Цикл для создания и запуска потоков для каждой строки матрицы
            for (int i = 0; i < matrix.length; i++) {
                threads[i] = new MaxThread(matrix[i]); // Создание потока для строки матрицы
                threads[i].start(); // Запуск потока
            }

            // Цикл ожидания завершения всех потоков и поиска максимального элемента
            for (MaxThread thread : threads) {
                thread.join(); // Ожидание завершения потока
                max = Math.max(max, thread.getMax()); // Обновление максимального значения
            }

            // Вывод максимального элемента в матрице
            System.out.println("Max element: " + max);
        } catch (InterruptedException e) {
            // Обработка исключения, если поток был прерван
            e.printStackTrace();
        }
    }

    // Вложенный статический класс для потока, обрабатывающего строку матрицы
    private static class MaxThread extends Thread {
        private final int[] row; // Массив для хранения строки матрицы
        private int max; // Переменная для хранения максимального элемента в строке

        // Конструктор класса MaxThread
        public MaxThread(int[] row) {
            this.row = row; // Инициализация массива строкой матрицы
        }

        // Метод run, который выполняется при запуске потока
        public void run() {
            max = Integer.MIN_VALUE; // Инициализация максимального значения минимально возможным значением
            for (int value : row) {
                max = Math.max(max, value); // Поиск максимального элемента в строке
            }
        }

        // Метод для получения максимального элемента, найденного потоком
        public int getMax() {
            return max; // Возвращение максимального значения
        }
    }
}
