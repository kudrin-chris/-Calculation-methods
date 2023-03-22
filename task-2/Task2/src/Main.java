import java.util.*;

public class Main {
    public static void main(String[] args) {
        Text.print(1);
        Text.print(0);
        Text.print(2);
        Scanner sc = new Scanner(System.in);
        int mPlusOne = sc.nextInt();
        Text.print(3);
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        Text.print(0);
        double[][] table = new double[mPlusOne][2];
        //заполнение таблицы
        for(int i = 0; i < mPlusOne; i++){
            double m = (double)(mPlusOne - 1);
            double x_k = a + ((b - a) / m) * (double) i;
            table[i][0] = x_k;
            table[i][1] = f(x_k);
        }


        printTable(table, mPlusOne, 0);
        while(true){
            Text.print(4);
            String ans = sc.next();
            if(ans.equals("no")) break;
            Text.print(5);
            double x = sc.nextDouble();
            Text.print(6);
            System.out.print(mPlusOne - 1);
            Text.print(11);
            int n = sc.nextInt();
            if(n > mPlusOne - 1){
                Text.print(7);
                Text.print(6);
                n = sc.nextInt();
            }

            sort(table, mPlusOne, x);//сортировка таблицы

            printTable(table, mPlusOne, 1);
            double P_n_L = P_n_L(x, n, table);
            double P_n_N = TablePP_N(table, n, x, a, ((b - a) / (mPlusOne - 1)));
            Text.print(8);
            System.out.println(P_n_L);
            Text.print(10);
            System.out.println(Math.abs(f(P_n_L) - x));
            System.out.println(" ");

            Text.print(9);
            System.out.println(P_n_N);
            Text.print(10);
            System.out.println(Math.abs(f(P_n_N) - x));
        }

    }
    public static double f(double x){
        return Math.sqrt(1 + x * x);
    }
    public static double f2(double x){
        return (x * x * x * x) * (x - (double) 1.16);
    }
    public static void printTable(double[][] table, int m, int p){
        if(p == 0) System.out.println("Построенная таблица:");
        else System.out.println("Отсортированная таблица");
        System.out.println("x_k" + " \t " + "f(x_k)");
        for(int i = 0; i < m; i++){
            System.out.print(String.format("%.4f", table[i][0]));
            System.out.print(String.format("\t%.4f", table[i][1]));
            System.out.println(" ");
        }
        System.out.println(" ");
    }
    public static double L_n_L(double x, int n, int k, double[][] table){
        double l = 0;
        double numerator = 1; //числитель L_kn
        double denominator = 1; // знаменатель L_nk
        for(int i = 0; i <= n; i++) {
            if (i != k) numerator *= (x - table[i][0]);
            if (i != k) denominator *= (table[k][0] - table[i][0]);
        }
        l = numerator / denominator;
        return l;
    }
    public static double P_n_L(double x, int n, double[][] table){
        double p = 0;
        for(int i = 0; i <= n; i++){
            p += L_n_L(x, n, i, table) * table[i][1];
        }
        return p;
    }
    public static void sort(double[][] table, int m, double x){
        for(int i = 0; i < m; i++){
            double tmp = table[i][0];
            table[i][0] = table[i][1];
            table[i][1] = tmp;
        }
        boolean needIteration = true;
        while (needIteration) {
            needIteration = false;
            for (int i = 1; i < m; i++) {
                if (Math.abs(table[i][0] - x) < Math.abs(table[i - 1][0] - x)) {
                    double tmp1, tmp2;
                    tmp1 = table[i - 1][0];
                    tmp2 = table[i - 1][1];
                    table[i - 1][0] = table[i][0];
                    table[i - 1][1] = table[i][1];
                    table[i][0] = tmp1;
                    table[i][1] = tmp2;
                    needIteration = true;
                }
            }
        }
    }
    public static double A(double[][] table, int k){
        double a = 0;
        for(int i = 0; i <= k; i++){
            double w = 1;
            for(int j = 0; j <= k; j++){
                if(j != k) w *= (table[k][0] - table[j][0]);
            }
            a += table[k][1] / w;
        }
        return a;
    } //не используется
    public static double P_n_N(double x, int n, double[][] table){
        double p = 0;
        for(int i = 0; i <= n; i++){
            double dop = 1;
            for(int j = 0; j < i; j++){
                dop *= x - table[j][0];
            }
            p += A(table, i) * dop;
        }
        return p;
    }
    public static double TablePP_N(double[][] table, int n, double x, double a, double h){
        double[][] pp = new double[n + 1][n + 1];
        for(int i = 0; i <= n; i++){
            pp[i][0] = table[i][1];
        }
       for(int j = 1; j <= n; j++){
           int index = 0;
           for(int i = j; i <= n; i++){
               pp[i][j] = (pp[i][j - 1] - pp[i - 1][j - 1]) / (table[i][0] - table[index][0]);
               index++;
           }
       }
        System.out.println(" ");
        System.out.println("Таблица разделенной расности");
        System.out.println("x_k \t f(x_k) \t pp_1 \t ...");
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= i; j++){
                if(i == j) System.out.print(String.format("%.2f", table[i][0]) + "\t");
                System.out.print(String.format("%.2f", pp[i][j]) + "\t ");
            }
            System.out.println(" ");
        }
        System.out.println(" ");
        double p = pp[0][0];
        double t = (x - a) / h;
        for(int i = 1; i <= n; i++){
            double dop = 1;
            for(int j = 0; j < i; j++){
                dop *= (x - table[j][0]);
            }
            p += pp[i][i] * dop;
        }
        return p;
    }
}