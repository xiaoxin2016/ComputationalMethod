//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.util.Scanner;

public class Gauss {
    private static void printMatrix(int row, int column, double[][] matrix) {
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                System.out.print(matrix[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        //文件重定向
//        FileInputStream fileInputStream = null;
//        try {
//            fileInputStream = new FileInputStream("in.txt");
//            System.setIn(fileInputStream);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入增广矩阵的行数：");
        int row = scanner.nextInt();
        int column = row + 1;
        double matrix[][] = new double[row][column];

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                matrix[r][c] = scanner.nextDouble();
            }
        }

        double max_value = 0;
        int max_index = 0;
        for (int c = 0; c < column - 2; c++) {   //下标从0开始，所以column-1，只剩最后一行不用再次运算，再column-1，故判断条件为column-2。
            //选出每一列系数绝对值最大的行号
            for (int r = c; r < row; r++) {
                //忘记初始化的BUG……
                max_value = 0;
                max_index = 0;
                if (Math.abs(matrix[r][c]) > max_value) {
                    max_value = Math.abs(matrix[r][c]);
                    max_index = r;
                }
            }
            //若最大的列不为当前首列，最大的列每一个值同第一行进行交换
            if (max_index != c) {
                for (int col = 0; col < column; col++) {
                    double temp;
                    temp = matrix[max_index][col];
                    matrix[max_index][col] = matrix[c][col];
                    matrix[c][col] = temp;
                }
            }

            //判断对角线元素是否为0决定能否继续消元
            if (matrix[c][c] == 0) {
                System.err.println("不满足高斯列主元消元的条件，程序将退出。");
                System.exit(0);
            } else {
                //从当前对角线元素开始逐行消元
                for (int ro = c + 1; ro < row; ro++) {
                    double coef = -matrix[ro][c] / matrix[c][c]; //确定每一行的消元用系数
                    for (int col = c; col < column; col++) {
                        matrix[ro][col] += matrix[c][col] * coef;
                    }
                }
            }
        }

//        printMatrix(row, column, matrix);


        //归一化并化简成单变量方程
        for (int r = 0; r < row; r++) {
            double coef = 1 / matrix[r][r];
            for (int c = r; c < column; c++) {
                matrix[r][c] *= coef;
            }
        }

//        printMatrix(row, column, matrix);
        for (int r = row - 1; r > 0; r--) {
            for (int ro = r - 1; ro >= 0; ro--) {
                double coef = -matrix[ro][r];
                //倒序逐行消除元素，并并入最后结果，这样只需要每次计算只需计算两列。
                matrix[ro][r] += coef;
                matrix[ro][column - 1] += matrix[r][column - 1] * coef;
            }
        }

//        printMatrix(row, column, matrix);

        //打印结果
        for (int i = 0; i < row; i++) {
            System.out.println("x[" + (i + 1) + "]" + "=" + matrix[i][column - 1]);
        }

    }

}
