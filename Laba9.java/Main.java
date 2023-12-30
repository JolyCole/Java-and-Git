import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
// аннотации, отражение, коллекции и классы для многопоточности

// Аннотация DataProcessor
@Retention(RetentionPolicy.RUNTIME) // Указывает, что аннотация DataProcessor должна сохраняться во время выполнения и доступна через reflection.
@Target(ElementType.METHOD) // Ограничивает использование аннотации DataProcessor только методами.
@interface DataProcessor { // Определяет аннотацию DataProcessor.
    String description() default ""; // Элемент аннотации с опциональным описанием, имеющим пустое значение по умолчанию.
}

class DataManager { // Определяет класс DataManager.
    private List<Object> processors = new ArrayList<>(); // Создает приватный список объектов для хранения обработчиков данных.

    public void registerDataProcessor(Object processor) { // Метод для добавления объекта-обработчика в список.
        processors.add(processor); // Добавляет переданный объект в список обработчиков данных.
    }

    public void loadData(String source) { // Метод для имитации загрузки данных из заданного источника.
        System.out.println("Loading data from " + source); // Печатает сообщение о загрузке данных.
    }

    public void processData() { // Метод для обработки данных.
        ExecutorService executorService = Executors.newFixedThreadPool(5); // Создает пул потоков с 5 рабочими потоками.

        for (Object processor : processors) { // Перебирает все объекты в списке обработчиков данных.
            for (Method method : processor.getClass().getMethods()) { // Перебирает все методы каждого объекта.
                if (method.isAnnotationPresent(DataProcessor.class)) { // Проверяет, аннотирован ли метод аннотацией DataProcessor.
                    executorService.submit(() -> { // Отправляет задачу на выполнение в пул потоков.
                        try {
                            method.invoke(processor); // Вызывает метод на объекте-обработчике.
                        } catch (Exception e) {
                            e.printStackTrace(); // Выводит ошибку в случае исключения.
                        }
                    });
                }
            }
        }

        executorService.shutdown(); // Завершает работу пула потоков после выполнения всех задач.
    }

    public void saveData(String destination) { // Метод для сохранения данных в заданное место.
        System.out.println("Saving data to " + destination); // Печатает сообщение о сохранении данных.
    }
}

// Классы обработчиков данных с аннотированными методами
class DataFilter {
    @DataProcessor(description = "Filter data") // Аннотация метода для фильтрации данных.
    public void filter() {
        System.out.println("Filtering data"); // Метод печатает сообщение о фильтрации данных.
    }
}

class DataTransformer {
    @DataProcessor(description = "Transform data") // Аннотация метода для трансформации данных.
    public void transform() {
        System.out.println("Transforming data"); // Метод печатает сообщение о трансформации данных.
    }
}

class DataAggregator {
    @DataProcessor(description = "Aggregate data") // Аннотация метода для агрегации данных.
    public void aggregate() {
        System.out.println("Aggregating data"); // Метод печатает сообщение об агрегации данных.
    }
}

public class Main { // Главный класс для тестирования.
    public static void main(String[] args) {
        DataManager manager = new DataManager(); // Создает экземпляр DataManager.

        // Регистрация обработчиков данных
        manager.registerDataProcessor(new DataFilter());
        manager.registerDataProcessor(new DataTransformer());
        manager.registerDataProcessor(new DataAggregator());

        manager.loadData("source.txt"); // Загрузка данных из файла.
        manager.processData(); // Обработка данных.
        manager.saveData("destination.txt"); // Сохранение обработанных данных.
    }
}

