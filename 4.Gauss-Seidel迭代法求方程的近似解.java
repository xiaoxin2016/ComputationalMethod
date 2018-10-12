import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GaussSeidel {
    public static void main(String[] args) {
        //文件重定向
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("in.txt");
            System.setIn(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);

        //输入变量
        System.out.println("请输入变量的个数：");
        int n = scanner.nextInt();
        double[][] inputCoef = new double[n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++) {
                inputCoef[i][j] = scanner.nextDouble();
            }
        }

        //输入初始值
        double[] result = new double[n];
        System.out.println("请输入初始值：");
        for (int i = 0; i < n; i++) {
            result[i] = scanner.nextDouble();
        }

        //分离变量形式
        for (int i = 0; i < n; i++) {
            double oldCoef = inputCoef[i][i];
            for (int j = 0; j < n + 1; j++) {
                inputCoef[i][j] /= oldCoef;
            }

            for (int j = 0; j < n; j++) {
                if (j >= i) {
                    inputCoef[i][j] = -inputCoef[i][j + 1];
                } else {
                    inputCoef[i][j] = -inputCoef[i][j];
                }
            }
            inputCoef[i][n - 1] = -inputCoef[i][n - 1];
            inputCoef[i][n] = 1;
        }

        System.out.println("请输入迭代次数:");
        int count = scanner.nextInt();
        while (count != 0) {
            //初始值开始迭代
            for (int i = 0; i < n; i++) {
                double sum = 0;
                for (int j = 0; j < n - 1; j++) {
                    if (i > j) {
                        sum += result[j] * inputCoef[i][j];
                    } else {
                        sum += result[j + 1] * inputCoef[i][j];
                    }
                }
                result[i] = sum + inputCoef[i][n - 1];
            }
            count--;
        }

//        //矩阵显示
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n + 1; j++) {
//                System.out.print(inputCoef[i][j] + " ");
//            }
//            System.out.println();
//        }

        //最终结果显示
        for (int i = 0; i < n; i++) {
            System.out.println(result[i] + " ");
        }

    }
}
