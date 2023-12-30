import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.lang.reflect.Method;

public class DataManager {
    private final List<Object> processors = new ArrayList<>();

    public void registerDataProcessor(Object processor) {
        processors.add(processor);
    }

    public List<String> loadData(String source) {
        // Моковые данные для примера
        return List.of("data1", "data2", "data3");
    }

    public List<String> processData(List<String> data) {
        ExecutorService executor = Executors.newFixedThreadPool(processors.size());
        List<String> currentData = new ArrayList<>(data); // Объявляем currentData здесь

        for (Object processor : processors) {
            // Замыкание для currentData внутри лямбда-выражения
            List<String> finalCurrentData = new ArrayList<>(currentData);

            Future<List<String>> future = executor.submit(() -> {
                List<String> result = new ArrayList<>(finalCurrentData); // Используем копию currentData
                for (Method method : processor.getClass().getDeclaredMethods()) {
                    if (method.isAnnotationPresent(DataProcessor.class)) {
                        try {
                            result = (List<String>) method.invoke(processor, result);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                return result;
            });

            try {
                // Обновляем currentData с результатом будущего выполнения
                currentData = future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        executor.shutdown(); // Закрытие пула потоков после выполнения задач
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException ie) {
            executor.shutdownNow();
        }

        return currentData;
    }



    public void saveData(String destination, List<String> data) {
        // Здесь должен быть код для записи обработанных данных в файл
        System.out.println("Сохраненные данные: " + data);
    }
}
