public class Test
{
    public static void main(String[] args) {
        int x = 10;
        printZero(x);
        printOne();
        printOne();
        printTwo();
    }

    public static void printZero(int x) {
        System.out.println(x);
    }

    public static void printOne() {
        System.out.println("Hello World");
    }

    public static void printTwo() {
        printOne();
        printOne();
    }
}
