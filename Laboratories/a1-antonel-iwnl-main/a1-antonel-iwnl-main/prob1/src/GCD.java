public class GCD {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide at least two integer numbers as command-line parameters.");
            return;
        }
        
        int gcd = Integer.parseInt(args[0]);
        for (int i = 1; i < args.length; i++) {
            int num = Integer.parseInt(args[i]);
            gcd = findGCD(gcd, num);
        }
        
        System.out.println("The greatest common divisor is: " + gcd);
    }
    
    public static int findGCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        return findGCD(b, a % b);
    }
}
