import java.util.Map;
import java.util.TreeMap;

public class SalesTracker {
    private final TreeMap<String, Integer> salesCount;//для отслеживания количества продаж по названиям товаров
    private final TreeMap<String, Double> salesAmount;//для отслеживания суммы продаж по названиям товаров

    public SalesTracker() {
        salesCount = new TreeMap<>();
        salesAmount = new TreeMap<>();
    }

    public void addSale(String product, double price) {//Метод для добавления продажи
        salesCount.put(product, salesCount.getOrDefault(product, 0) + 1);//Увеличение счетчика продаж для данного товара
        salesAmount.put(product, salesAmount.getOrDefault(product, 0.0) + price);//Увеличение суммы продаж для данного товара
    }

    public void printSalesCount() {//Метод для печати количества продаж каждого товара
        for (Map.Entry<String, Integer> entry : salesCount.entrySet()) {//Перебор всех записей
            //Каждая запись содержит пару ключ-значение, где ключ это название товара, а значение — количество проданных единиц
            System.out.println(entry.getKey() + ": " + entry.getValue());//Вывод названия товара и количества его продаж
        }
    }

    public double getTotalSalesAmount() {//метод для получения общей суммы продаж
        double total = 0.0;//Инициализация переменной для хранения общей суммы
        for (double amount : salesAmount.values()) {//Цикл для суммирования всех значений которые представляют собой суммы продаж по каждому товару
            total += amount;//Добавление суммы каждого товара к общей сумме
        }
        return total;// сумма продаж
    }

    public String getMostPopularProduct() {
        String mostPopular = null;//для хранения названия наиболее популярного продукта
        int maxSales = 0;// отслеживание максимального количества продаж
        for (Map.Entry<String, Integer> entry : salesCount.entrySet()) {//Цикл для перебора всех записей
            if (entry.getValue() > maxSales) {//Проверка, если текущее количество продаж больше, чем максимально зафиксированное
                mostPopular = entry.getKey();//Обновление наиболее популярного товара
                maxSales = entry.getValue();//Обновление максимального количества продаж
            }
        }
        return mostPopular;
    }
}
