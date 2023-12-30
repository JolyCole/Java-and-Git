public class ArraySumTask {
    public static void main(String[] args) {
        try {
            // Инициализация массива целых чисел
            int[] array = {/* инициализация массива */};

            // Определение середины массива для разделения работы между потоками
            int mid = array.length / 2;

            // Создание первого потока для обработки первой половины массива
            SumThread thread1 = new SumThread(array, 0, mid);

            // Создание второго потока для обработки второй половины массива
            SumThread thread2 = new SumThread(array, mid, array.length);

            // Запуск обоих потоков
            thread1.start();
            thread2.start();

            // Ожидание завершения обоих потоков
            thread1.join();
            thread2.join();

            // Получение и суммирование результатов работы потоков
            int totalSum = thread1.getSum() + thread2.getSum();

            // Вывод общей суммы элементов массива
            System.out.println("Total sum: " + totalSum);
        } catch (InterruptedException e) {
            // Обработка исключения в случае прерывания одного из потоков
            e.printStackTrace();
        }
    }

    // Вложенный статический класс, наследующийся от класса Thread
    private static class SumThread extends Thread {
        private final int[] array; // Массив, сумму элементов которого нужно вычислить
        private final int start;   // Начальный индекс диапазона обработки
        private final int end;     // Конечный индекс диапазона обработки
        private int sum;           // Переменная для хранения рассчитанной суммы

        // Конструктор класса SumThread
        public SumThread(int[] array, int start, int end) {
            this.array = array; // Инициализация массива
            this.start = start; // Инициализация начального индекса
            this.end = end;     // Инициализация конечного индекса
        }

        // Метод run, выполняемый при запуске потока
        public void run() {
            sum = 0; // Инициализация переменной для суммы
            // Цикл для суммирования элементов массива в заданном диапазоне
            for (int i = start; i < end; i++) {
                sum += array[i]; // Добавление значения элемента к сумме
            }
        }

        // Геттер для получения рассчитанной суммы
        public int getSum() {
            return sum; // Возвращение суммы
        }
    }
}
