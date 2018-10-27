import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lagrange {
    static int n = 0;
    static double[] x;
    static double[] y;

    //插值基函数，带入值为x_in，计算完整的值
    public static double l(int i, double x_in) {
        double sum = 1;
        for (int j = 0; j <= n; j++) {
            if (j != i) {
                sum *= ((x_in - x[j]) / (x[i] - x[j]));
            }
        }
        return sum;
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
        * x_in (待求点自变量的值)
        * -------------------------
        */

        //从文件中读取插值点个数并初始化相关数组
        File file = new File("lagrange.in");
        Scanner scanner = new Scanner(file);
        n = scanner.nextInt();
        x = new double[n + 1];
        y = new double[n + 1];

        //读取插值点的值
        for (int i = 0; i <= n; i++) {
            x[i] = scanner.nextDouble();
        }
        for (int i = 0; i <= n; i++) {
            y[i] = scanner.nextDouble();
        }

        //计算Lagrange插值多项式的值
        double result = 0;
        double x_in = scanner.nextDouble();
        for (int i = 0; i <= n; i++) {
            result += l(i, x_in) * y[i];
        }

        System.out.println("Lagrange插值结果为：" + result);
    }
}
