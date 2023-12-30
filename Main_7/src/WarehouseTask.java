import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class WarehouseTask {
    public static void main(String[] args) {
        // Создание объекта склада
        Warehouse warehouse = new Warehouse();

        // Запуск трех потоков Loader для работы со складом
        for (int i = 0; i < 3; i++) {
            new Loader(warehouse).start();
        }
    }

    // Внутренний класс, представляющий склад
    static class Warehouse {
        // Блокировка для синхронизации доступа к текущей загрузке склада
        private final ReentrantLock lock = new ReentrantLock();
        // Условие для управления потоками ожидания/оповещения
        private final Condition condition = lock.newCondition();
        // Текущая загрузка склада
        private int currentLoad = 0;
        // Максимально допустимая загрузка склада
        private static final int MAX_LOAD = 150;

        // Метод для добавления груза на склад
        public void addLoad(int weight) throws InterruptedException {
            lock.lock(); // Блокировка для безопасного доступа к состоянию склада
            try {
                // Ожидание, пока на складе не освободится место для нового груза
                while (currentLoad + weight > MAX_LOAD) {
                    condition.await();
                }
                // Добавление груза на склад
                currentLoad += weight;
                System.out.println("Current load: " + currentLoad);
                // Выгрузка, если достигнут максимум
                if (currentLoad == MAX_LOAD) {
                    unload();
                }
            } finally {
                lock.unlock(); // Разблокировка
            }
        }

        // Метод для выгрузки груза со склада
        public void unload() {
            currentLoad = 0; // Сброс текущей загрузки
            System.out.println("Unloading and resetting load.");
            condition.signalAll(); // Оповещение всех ожидающих потоков
        }
    }

    // Внутренний класс, представляющий грузчика
    static class Loader extends Thread {
        private final Warehouse warehouse; // Ссылка на склад

        // Конструктор класса Loader
        public Loader(Warehouse warehouse) {
            this.warehouse = warehouse;
        }

        // Метод run, выполняемый при запуске потока
        public void run() {
            try {
                // Непрерывный цикл для имитации работы грузчика
                while (true) {
                    // Генерация случайного веса груза
                    int weight = (int) (Math.random() * 50) + 1;
                    // Добавление груза на склад
                    warehouse.addLoad(weight);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
