package zadanie1_2;

import java.util.ArrayList;

/**
 * @version 24.09.2018
 * @author Viktor Chernyaev
 * @Java3 homework Lesson-1
 * @link https://github.com/des1z
 */

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