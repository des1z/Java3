package java3hw5;

/**
 * @version 08.10.2018
 * @author Viktor Chernyaev
 * @Java3 homework Lesson-5
 * @link https://github.com/des1z
 */

import java.util.concurrent.CyclicBarrier;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static Thread[] threads = new Thread[CARS_COUNT];

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel((int) (CARS_COUNT/2)), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        Car.setCb(new CyclicBarrier(CARS_COUNT, new StartMessage()));

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }

        for (int i = 0; i < CARS_COUNT; i++) {
            threads[i] = new Thread(cars[i]);
            threads[i].start();
        }

        for (int i = 0; i < cars.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("ВАЖНОЕ ОБЪЯВЕНИЕ >>> Гонка закончилась!!!");
    }
}

