package java3hw4.task1;

public class Main {
    public static void main(String[] args) {
        Task1();
    }

    public static void Task1() {
        task1 t = new task1();
        new Thread(() -> t.printA()).start();
        new Thread(() -> t.printB()).start();
        new Thread(() -> t.printC()).start();
    }
}
