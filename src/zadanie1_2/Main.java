package zadanie1_2;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("New array:");
        Mas<?> m = new Mas<>(new Dog(), new Cat());
        m.info();

        System.out.println("\nExchange array items:");
        m.masElementsExchange();
        m.info();

        System.out.println("\nArrayList:");
        ArrayList<?> list = m.arrayToArrayList();
        for (Object o : list) System.out.println(o.getClass().getName());
    }
}