import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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

        Text.print(13);
        String text = sc.next();

        while (true) {
            Text.print(4);
            String ans = sc.next();
            if (ans.equals("no")) break;
            Text.print(5);
            double F = sc.nextDouble();
            Text.print(6);
            System.out.print(mPlusOne - 1);
            Text.print(11);
            int n = sc.nextInt();

            while (n > mPlusOne - 1) {
                Text.print(7);
                Text.print(6);
                n = sc.nextInt();
            }
            if (!text.equals("no")) {
                //меняем столбцы в таблице местами
                for (int i = 0; i < mPlusOne; i++) {
                    double tmp = table[i][0];
                    table[i][0] = table[i][1];
                    table[i][1] = tmp;
                }
                Text.print(12);
                sort(table, mPlusOne, F);//сортировка таблицы

                double Q_n_L = P_n_L(F, n, table);
                Text.print(8);
                System.out.println(String.format("%.15f", Q_n_L));
                Text.print(10);
                System.out.printf("%.3e", Math.abs(f(Q_n_L) - F));
                System.out.println(" ");
            }
            /////Второй способ решения
            if (!text.equals("no")) {
                //меняем столбцы в таблице местами
                for (int i = 0; i < mPlusOne; i++) {
                    double tmp = table[i][0];
                    table[i][0] = table[i][1];
                    table[i][1] = tmp;
                }
            }
            sort(table, mPlusOne, F);//сортировка таблицы
            Text.print(14);
            double e = 10.0E-12;
            System.out.print("e = ");
            System.out.printf("%.3e", e);
            ///Отделение корней
            List<PairT> list = new ArrayList();
            for (int i = 0; i < mPlusOne; i++) {
                if (a > table[i][0]) a = table[i][0];
                if (b < table[i][0]) b = table[i][0];
            }
            double h = (b - a) / (double) n;
            int counter = 0;
            double x1 = a;
            double x2 = a + h;
            double y1 = P_n(x1, F, n, table);
            while (x2 <= b) {
                double y2 = P_n(x2, F, n, table);
                if (y1 * y2 <= 0) {
                    counter++;
                    list.add(new PairT(x1, x2));
                }
                x1 = x2;
                x2 = x1 + h;
                y1 = y2;
            }
            if (list.size() == 0) System.out.println("Не найдено промежутков смены знака");
            for (int i = 0; i < list.size(); ++i) {
                System.out.println(" ");
                double t = Secant(((PairT) list.get(i)).getA(), ((PairT) list.get(i)).getB(), e, F, n, table);
                System.out.print("Q_n(F) методом секущих номер " + (i + 1) + " = ");
                System.out.print(String.format("%.15f", t));
                System.out.println();
                System.out.print("|f(x) - F| = ");
                System.out.printf("%.3e", Math.abs(f(t) - F));
            }
        }

    }
    public static void printTable(double[][] table, int m, int p){
        if(p == 0) System.out.println("Построенная таблица:");
        else System.out.println("Отсортированная таблица");
        if(p == 0)
            System.out.print(" x_k\t");
        else  System.out.print("f(x_k)   ");
        for(int i = 0; i < m; i++){
            System.out.print(String.format("%.4f", table[i][0]) + "   ");
        }
        System.out.println("");
        if(p == 0) System.out.print("f(x_k)   ");
        else System.out.print(" x_k     ");
        for(int i = 0; i < m; i++) {
            System.out.print(String.format("%.4f", table[i][1]) + "   ");
        }
        System.out.println("");
    }
    public static double f1(double x){
        return Math.sqrt(1 + x * x);
    } //монотонная ф
    public static double f(double x){
        return Math.sin(x);
    } // немонотонная ф
    public static double P_n(double x, double F, int n, double[][] table){
        return P_n_L(x, n, table) - F;
    }
    public static void sort(double[][] table, int m, double F){
        boolean needIteration = true;
        while (needIteration) {
            needIteration = false;
            for (int i = 1; i < m; i++) {
                if (Math.abs(table[i][0] - F) < Math.abs(table[i - 1][0] - F)) {
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
    public static double L_n_L(double F, int n, int k, double[][] table){
        double l = 0;
        double numerator = 1; //числитель L_kn
        double denominator = 1; // знаменатель L_nk
        for(int i = 0; i <= n; i++) {
            if (i != k) numerator *= (F - table[i][0]);
            if (i != k) denominator *= (table[k][0] - table[i][0]);
        }
        l = numerator / denominator;
        return l;
    }

    public static double P_n_L(double F, int n, double[][] table) {
        double p = 0;
        for (int i = 0; i <= n; i++) {
            p += L_n_L(F, n, i, table) * table[i][1];
        }
        return p;
    }
    public static double Secant(double a, double b, double e, double F, int n, double[][] table) { //Метод секущих
        //x_k+1 = x, x_k = y, x_k-1 = z
        double x0 = (a + b) / 2;
        double x = x0 - P_n(x0, F, n, table);
        double y = x0;
        double z = 0;
        do {
            z = y;
            y = x;
            x = y - (P_n(y, F, n, table) / (P_n(y, F, n, table) - P_n(z, F, n, table))) * (y - z);
        } while (Math.abs(x - y) > e);
        return x;
    }
    public static double binarySearch(double a, double b, double e, double F, int n, double[][] table) { //Метод биссекций
        double c = 0;
        while (b - a > 2.0 * e) { //условие выхода из цикла
            c = (a + b) / 2.0;
            if (P_n(a, F, n, table) == 0.0) {
                System.out.println("Q_n(F) = " + a);
                return a;
            }
            if (P_n(a, F, n, table) == 0.0) {
                System.out.println("Q_n(F) = " + b);
                return b;
            }
            if (P_n(a, F, n, table) * P_n(c, F, n, table) < 0.0) {
                b = c;
            } else {
                a = c;
            }
        }
        System.out.println("Q_n(F) = " + (a + b) / 2.0);
        return (a + b ) / 2;
    }
}