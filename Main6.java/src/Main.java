public class Main {
    public static void main(String[] args) {
        // Демонстрация работы класса Stack
        Stack<Integer> stack = new Stack<>(10);//Создание экземпляра класса Stack с типом Integer и начальной емкостью 10
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Stack Pop: " + stack.pop());//Вывод и удаление верхнего элемента стека
        System.out.println("Stack Peek: " + stack.peek());//Вывод верхнего элемента стека без его удаления
        stack.push(4);
        System.out.println("Stack Pop: " + stack.pop());//Вывод и удаление верхнего элемента стека

        // Демонстрация работы класса SalesTracker
        SalesTracker tracker = new SalesTracker();// экземпляр класса
        tracker.addSale("Телевизор", 30000.0);
        tracker.addSale("Телевизор", 30000.0);
        tracker.addSale("Смартфон", 20000.0);
        tracker.printSalesCount();
        System.out.println("Общая сумма продаж: " + tracker.getTotalSalesAmount());
        System.out.println("Наиболее популярный товар: " + tracker.getMostPopularProduct());
    }
}