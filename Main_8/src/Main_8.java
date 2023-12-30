import java.util.List;
public class Main_8 {
    public static void main(String[] args) {
        DataManager dataManager = new DataManager();

        // Регистрация обработчиков данных
        dataManager.registerDataProcessor(new DataFilter());
        dataManager.registerDataProcessor(new DataTransformer());
        dataManager.registerDataProcessor(new DataAggregator());

        // Загрузка данных
        List<String> data = dataManager.loadData("path/to/source");

        // Обработка данных
        List<String> processedData = dataManager.processData(data);

        // Сохранение результатов
        dataManager.saveData("path/to/destination", processedData);
    }
}
