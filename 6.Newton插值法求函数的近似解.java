import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NewtonInterpolation {
    static int n = 0;
    static double[] diffCoef;

    static double[] x;
    static double[] y;

//    //根据差商的性质化成自变量和变量相关的线性组合计算
//    static double diffCoefFunction(int n) {
//        double sum = 0;
//        for (int i = 0; i <= n; i++) {
//            //先求出分母上的连乘积
//            double denominator = 1;
//            for (int j = 0; j <= n; j++) {
//                if (j != i) { //保证不含自身
//                    denominator *= (x[i] - x[j]);
//                }
//            }
//            sum += (y[i] / denominator);
//        }
//        return sum;
//    }

    //基于迭代法计算差商
    static void diffCoefProcess() {
        //最后一位（flag）开始，每次递减，倒序记录对角线的元素
        int flag = n;
        for (int i = 1; i <= n; i++) {
            double temp = y[0];
            for (int j = 1; j <= (n + 1 - i); j++) {
                y[j - 1] = (y[j] - y[j - 1]) / (x[i + (j - 1)] - x[j - 1]);
            }
            y[flag--] = temp;
        }
    }

    static double diffCoefFunction(int n) {
        return y[NewtonInterpolation.n - n];
    }

    static double getValue(double x_in) {
        double result = 0;
        //特殊情况指定值，保证下面乘法不需要特殊处理
        diffCoef[0] = 1;
        for (int i = 0; i <= n; i++) {
            //先求出连乘积
            double product = 1;
            for (int j = 0; j <= i - 1; j++) {
                product *= (x_in - x[j]);
            }
            result += diffCoefFunction(i) * product;
        }
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {

        /*
        * 测试用例(lagrange.in)格式
        * 给出从 x0 ... xn 个点
        * 给出其对应的 y0 ... yn
        * -------------------------
        * n
        * x0 x1 ... xn
        * y0 y1 ... yn
        *
        * x_in1 x_in2 (若干待求点自变量的值)
        * -------------------------
        */

        //从文件读取已知插值点
        File file = new File("Newton.in");
        Scanner scanner = new Scanner(file);
        n = scanner.nextInt();
        x = new double[n + 1];
        y = new double[n + 1];
        diffCoef = new double[n + 1];

        //读入每一个插值点的xi和yi
        for (int i = 0; i <= n; i++) {
            x[i] = scanner.nextDouble();
        }
        for (int i = 0; i <= n; i++) {
            y[i] = scanner.nextDouble();
        }

        //首先进行运算求得差商表并取得对角线元素
        diffCoefProcess();

        //求牛顿插值多项式的值
        double x_in;
        while (scanner.hasNextDouble()) {
            x_in = scanner.nextDouble();
            System.out.println("xi=" + x_in + "时Newton插值多项式的值为" + getValue(x_in));
        }
    }

}
