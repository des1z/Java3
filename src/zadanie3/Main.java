package zadanie3;

/**
 * @version 24.09.2018
 * @author Viktor Chernyaev
 * @Java3 homework Lesson-1
 * @link https://github.com/des1z
 */

public class Main {
    public static void main(String[] args) {
        Box<Apple> appleBox1 = new Box<>();
        Box<Orange> orangeBox2 = new Box<>();

        Apple a1 = new Apple();
        Apple a2 = new Apple();
        Apple a3 = new Apple();
        Orange o1 = new Orange();
        Orange o2 = new Orange();
        System.out.println("1 apple weight is: " + a1.getWeight());
        System.out.println("1 orange weight is: " + o1.getWeight() + "\n");

        System.out.println("Add 3 apples & 2 orange");

        appleBox1.addFruit(a1);
        appleBox1.addFruit(a2);
        appleBox1.addFruit(a3);
        orangeBox2.addFruit(o1);
        orangeBox2.addFruit(o2);

        System.out.println("\nappleBox1 weight is: " + appleBox1.getWeight());
        System.out.println("orangeBox2 weight is: " + orangeBox2.getWeight());

        System.out.println("Compare weights: " + appleBox1.compare(orangeBox2) + "\n");

        Box<Apple> appleBox2 = new Box<Apple>();

        System.out.println("Apple boxes weights before replacing: ");
        System.out.println("appleBox1 weight: " + appleBox1.getWeight());
        System.out.println("appleBox2 weight: " + appleBox2.getWeight());
        System.out.println("\nReplacing applebox1 -> applebox2\n");
        appleBox1.replaceAllFruitsToOtherBox(appleBox2);
        System.out.println("Apple boxes weights after replacing: ");
        System.out.println("appleBox1 weight: " + appleBox1.getWeight());
        System.out.println("appleBox2 weight: " + appleBox2.getWeight());
    }
}
