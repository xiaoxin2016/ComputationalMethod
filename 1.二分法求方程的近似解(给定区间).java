import java.util.Scanner;

public class Binary {
    static double f(double x) {
        double y;
        y = x * x - 2 * x + 1;
        return y;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double error = 1e-4;
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();

        int iteration = 0;
        double result = 0;

        while (b - a >= error) {
            iteration++;

            double x = (a + b) / 2;
            if (f(a) * f(b) > 0) {
                a = x;
            } else {
                b = x;
            }
            result = (a + b) / 2;
        }

        System.out.println("迭代次数为：" + iteration);
        System.out.print("误差范围内的结果为：");
        System.out.printf("%.4f\n",result);

    }
}
