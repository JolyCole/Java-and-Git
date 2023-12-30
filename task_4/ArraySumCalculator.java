public class ArraySumCalculator extends Thread {
    private int[] array; // над которым будет производиться операция
    private int start;
    private int end;
    private int sum; // результат суммирования

    public ArraySumCalculator(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    public void run() {
        sum = 0;
        for (int i = start; i < end; i++) {
            sum += array[i];
        }
    }

    public int getSum() { // для получения результата суммирования после выполнения потока.
        return sum;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int mid = array.length / 2;

        // Создаем два потока для вычисления суммы элементов массива по половинкам
        ArraySumCalculator thread1 = new ArraySumCalculator(array, 0, mid);
        ArraySumCalculator thread2 = new ArraySumCalculator(array, mid, array.length);
// от нач до сер
// от сер до конца
        // Запускаем потоки
        thread1.start();
        thread2.start();

        // Ожидаем завершение работы потоков
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Складываем результаты вычислений
        int totalSum = thread1.getSum() + thread2.getSum();
        System.out.println("Total sum of array elements: " + totalSum);
    }
}
