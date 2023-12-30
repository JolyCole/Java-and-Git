public class Main {
    public static void main(String[] args) {
        // Запуск задачи по вычислению суммы элементов массива
        String[] argsForTasks = {}; // Пустой массив аргументов для совместимости
        ArraySumTask.main(argsForTasks);

        // Запуск задачи по поиску наибольшего элемента в матрице
        MatrixMaxTask.main(argsForTasks);

        // Запуск задачи по переносу товаров грузчиками
        WarehouseTask.main(argsForTasks);
    }
}
