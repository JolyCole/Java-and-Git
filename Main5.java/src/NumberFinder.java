import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;

public class NumberFinder {
    public static void main(String[] args) {
        String text = "The price of the product is $19.99, but there are also 20.50 items available.";
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");//компилирует регулярное выражение, которое ищет числа с десятичной точкой
        Matcher matcher = pattern.matcher(text);//создает объект, который будет искать совпадения в тексте с использованием регулярного выражения.

        List<String> numbers = new ArrayList<>();
        while (matcher.find()) {//запускает цикл, который выполняется до тех пор, пока в тексте найдутся числа
            numbers.add(matcher.group());
        }

        System.out.println("Found numbers: " + numbers);
    }
}
