import java.util.regex.*;

public class PasswordValidator {
    public static void main(String[] args) {
        String password = "Password123";//пример пароля

        Pattern pattern = Pattern.compile("^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,16}$");
        ////компилирует регулярное выражение, которое проверяет пароль на соответствие заданным критериям
        Matcher matcher = pattern.matcher(password);
        //создает объект Matcher, который будет искать совпадения в пароле с использованием регулярного выражения

        if (matcher.matches()) {//проверяет, соответствует ли пароль критериям, заданным в регулярном выражении
            System.out.println("Valid password!");
        } else {
            System.out.println("Invalid password!");
        }
    }
}