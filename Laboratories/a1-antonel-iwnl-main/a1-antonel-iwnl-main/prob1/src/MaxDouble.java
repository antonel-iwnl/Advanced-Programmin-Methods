public class MaxDouble {
    public static void main(String[] args) {
        double max = Double.MIN_VALUE;
        for (String arg : args) {
            double num = Double.parseDouble(arg);
            if (num > max) {
                max = num;
            }
        }
        System.out.println("The maximum value is: " + max);
    }
}
