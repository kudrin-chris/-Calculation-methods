import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(" Рассматриваем функцию exp(7.5 * x)");
        System.out.println("");
       /* System.out.println("Подбор шага h:");
        double hh = 0.0001;
        double p = fDxC(1, hh); //x = 1
        double fx1 = 7.5f * Math.exp(7.5f);
        for(int i = 0; i < 10; i++){
            System.out.println("h = " + hh + "\t\tпогрешность = " + Math.abs(p - fx1));
            hh = hh / 2;
            p = fDxC(1, hh);
        } */
        Scanner sc = new Scanner(System.in);
        while (true) { //оптимальное h = 0,00001
            System.out.println(" ");
            System.out.println("Хотите продолжить? yes/no");
            String ans = sc.next();
            if (ans.equals("no")) break;
            System.out.println("Введите точку А");
            double a = sc.nextDouble();
            System.out.println("Введите степень числа h");
            int tmp = sc.nextInt();
            double h = Math.pow(10, tmp);
            System.out.println("h = " + h);
            System.out.println("Введите число значений в таблице");
            int mPlusOne = sc.nextInt();
            double[][] table = new double[mPlusOne][2];
            //заполнение таблицы
            for (int i = 0; i < mPlusOne; i++) {
                double m = (double) (mPlusOne - 1);
                double x_k = a + h * (double) i;
                table[i][0] = x_k;
                table[i][1] = f(x_k);
            }
            printTable(table, mPlusOne);
            System.out.println("x_i\t\t\t\tf(x_i)\t\t\tf'(x_i)чд\t\t\t|f'(x_i)т - f'(x_i)чд|\tотнос погрешность\t\tf''(x_i)чд\t\t\t|f''(x_i)т - f''(x_i)чд|\t\t\tотнос погрешность");
            for(int i = 0; i < mPlusOne; i++){
                System.out.print(String.format("%.10f", table[i][0]) + "\t");
                System.out.print(String.format("%.10f", table[i][1]) + "\t\t");
                if(i == 0){ //начальная точка
                    double xi = fDxN(table[i][0], h);
                    double xxi = fDDxxP(table[i][0], h);
                    System.out.print(String.format("%.3f", xi) + "\t\t");
                    System.out.print(String.format("%.3e", Math.abs(xi - (7.5)*(table[i][1]))) + "\t\t\t\t");
                    System.out.print(String.format("%.3e", (Math.abs(xi - (7.5)*(table[i][1])))/xi) + "\t\t\t\t");
                    System.out.print(String.format("%.3f", xxi) + "\t\t\t\t");
                    System.out.print(String.format("%.3e", Math.abs(xxi - (56.25)*(table[i][1]))) + "\t\t\t\t");
                    System.out.print(String.format("%.3e", (Math.abs(xxi - (56.25f)*(table[i][1])))/xxi) + "\t\t\t\t");
                }
                else if(i == mPlusOne - 1) { //точка в конце таблицы
                    double xi = fDxK(table[i][0], h);
                    double xxi = fDDxxP(table[i][0], h);
                    System.out.print(String.format("%.3f", xi) + "\t\t");
                    System.out.print(String.format("%.3e", Math.abs(xi - (7.5f)*(table[i][1]))) + "\t\t\t\t");
                    System.out.print(String.format("%.3e", (Math.abs(xi - (7.5)*(table[i][1])))/xi) + "\t\t\t\t");
                    System.out.print(String.format("%.3f", xxi) + "\t\t\t\t");
                    System.out.print(String.format("%.3e", Math.abs(xxi - (56.25f)*(table[i][1]))) + "\t\t\t\t");
                    System.out.print(String.format("%.3e", (Math.abs(xxi - (56.25f)*(table[i][1])))/xxi) + "\t\t\t\t");
                }
                else{ //центральные точки
                    double xi = fDxC(table[i][0], h);
                    double xxi = fDDxxC(table[i][0], h);
                    System.out.print(String.format("%.3f", xi) + "\t\t");
                    System.out.print(String.format("%.3e", Math.abs(xi - (7.5f)*(table[i][1]))) + "\t\t\t\t");
                    System.out.print(String.format("%.3e", (Math.abs(xi - (7.5)*(table[i][1])))/xi) + "\t\t\t\t");
                    System.out.print(String.format("%.3f", xxi) + "\t\t\t\t");
                    System.out.print(String.format("%.3e", Math.abs(xxi - (56.25f)*(table[i][1]))) + "\t\t\t\t");
                    System.out.print(String.format("%.3e", (Math.abs(xxi - (56.25f)*(table[i][1])))/xxi) + "\t\t\t\t");
                }
                System.out.println();
            }
        }
    }
    public static double f(double x){
        return Math.exp(1.5f * 5.f * x);
    }
    public static void printTable(double[][] table, int m){
        System.out.println("Построенная таблица:");
        System.out.println("x_k\t\t\t\t f(x_k)");
        for(int i = 0; i < m; i++){
            System.out.print(String.format("%.6f", table[i][0]) + "   ");
            System.out.print(String.format("%.6f", table[i][1]) + "   ");
            System.out.println();
        }
        System.out.println("");
    }
    public static double fDxP(double a, double h){ //правая рп
        return (f(a + h) - f(a)) / h;
    }
    public static double fDxL(double a, double h){ //левая рп
        return (f(a) - f(a - h)) / h;
    }
    public static double fDxC(double a, double h){ //центральная рп
        return (f(a + h) - f(a - h)) / (2 * h);
    }
    public static double fDxN(double a, double h){ //точка в начале таблицы
        return ((4.f * f(a + h) - 3.f * f(a) - f(a + 2.f * h)) / (2.f * h));
    }
    public static double fDxK(double a, double h){ //точка в конце таблицы
        return ((3.f * f(a) - 4.f * f(a - h) + f(a - 2.f * h)) / (2.f * h));
    }
    public static double fDDxxP(double a, double h){
        return (2.f * f(a) - 5.f * f(a + h) + 4.f * f(a + 2.f * h) - f(a + 3.f * h)) / (h * h);
    }
    public static double fDDxxL(double a, double h){
        return (2.f * f(a) - 5.f * f(a - h) + 4.f * f(a - 2.f * h) - f(a + 3.f - h)) / (h * h);
    }
    public static double fDDxxC(double a, double h){
        return ((f(a + h) - 2.f * f(a) + f(a - h)) / (h * h));
    }

}