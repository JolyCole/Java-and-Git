public class Stack<T> {
    private T[] data;//массива data типа T для хранения элементов стека
    private int size;//size для отслеживания количества элементов в стеке

    public Stack(int capacity) {//: Конструктор класса Stack, принимающий емкость стека как параметр
        data = (T[]) new Object[capacity];//Инициализация массива data
        //Приведение типа необходимо, так как нельзя напрямую создать массив обобщенного типа
        size = 0;// инициализация
    }

    public void push(T element) {//push, который добавляет элемент в стек
        if (size == data.length) {//Проверка, не переполнен ли стек
            throw new IllegalStateException("Stack is full");// Если стек полон, выбрасывается исключение
        }
        data[size++] = element;//Добавление элемента в стек и увеличение size
    }

    public T pop() {//Метод pop, который удаляет и возвращает верхний элемент стека
        if (size == 0) {//Проверка, не пустой ли стек
            throw new IllegalStateException("Stack is empty");//Если стек пуст, выбрасывается исключение
        }
        T element = data[--size];//Уменьшение size и получение верхнего элемента
        data[size] = null;//(для предотвращения утечек памяти)
        return element;
    }

    public T peek() {//Метод peek, который возвращает верхний элемент стека без удаления
        if (size == 0) {//не пустой ли стек
            throw new IllegalStateException("Stack is empty");//Если стек пуст, выбрасывается исключение
        }
        return data[size - 1];//Возвращение верхнего элемента стека
    }
}