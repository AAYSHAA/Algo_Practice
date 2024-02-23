public class Math {
    public static int add(int x, int y) {
        return x + y;
    }
    public static void main(String[] args) {
        //int sum = Math.add(10, 20); // call the static method using the class name
        //System.out.println(sum);
        Math.add(10, 20);
        System.out.println(Math.add(20, 20));
    }
}



