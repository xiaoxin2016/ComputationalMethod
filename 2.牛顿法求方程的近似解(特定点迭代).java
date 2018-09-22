import java.util.Scanner;

public class Newton {
    static double f(double x) {
        double y = x * x - 2 * x + 1;
        return y;
    }

    static double df(double x) {
        double y = 2 * x - 2;
        return y;
    }

    static double iteration(double x) {
        return x - f(x) / df(x);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double error = 1e-4;
        double x0 = scanner.nextDouble();
        double x = iteration(x0);
        int count=1;

        while (x-x0>error){
            x0=x;
            x=iteration(x0);
            count++;
        }

        System.out.println("迭代次数为："+count);
        System.out.print("程序运行结果为：");
        System.out.printf("%.4f\n",x);
    }
}
