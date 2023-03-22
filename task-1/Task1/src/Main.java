//task-1 
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите А");
        double a = sc.nextDouble();
        System.out.println("Введите В");
        double b = sc.nextDouble();
        double e = 10.0E-12;
        List<PairT> list = new ArrayList();
        System.out.println("Введите N");
        int n = sc.nextInt();

        ///Отделение корней
        double h = (b - a) / (double)n;
        int counter = 0;
        double x1 = a;
        double x2 = a + h;
        double y1 = f(x1);
        while(x2 <= b){
            double y2 = f(x2);
            if(y1 * y2 <= 0){
                counter++;
                list.add(new PairT(x1, x2));
            }
            x1 = x2;
            x2 = x1 + h;
            y1 = y2;
        }
        System.out.println("Ищем решения уравнения: sqrt(4x + 7) - 3cos(x) = 0");
        System.out.println("[A, B] = [" + a + ", " + b + "]");
        System.out.println("N = " + n);
        System.out.println("Точность поиска решения: " + e);
        System.out.println("Найдено промежутков смены знака : " + counter);
        System.out.println(list.toString());
        System.out.println(" ");



        for(int i = 0; i < list.size(); ++i) {
            System.out.println(" ");
            System.out.println("ПОИСК КОРНЯ НОМЕР " + (i + 1));
            binarySearch(((PairT)list.get(i)).getA(), ((PairT)list.get(i)).getB(), e);
            Newton(((PairT)list.get(i)).getA(), ((PairT)list.get(i)).getB(), e);
            Newton2(((PairT)list.get(i)).getA(), ((PairT)list.get(i)).getB(), e);
            Secant(((PairT)list.get(i)).getA(), ((PairT)list.get(i)).getB(), e);
        }
    }
    public static Double f(double x) {
        return Math.sqrt(4.0 * x + 7.0) - 3.0 * Math.cos(x);
    }

    public static Double fDx(double x) {
        return 2.0 * (1.0 / Math.sqrt(4.0 * x + 7.0)) + 3.0 * Math.sin(x);
    }

    public static Double fDxx(double x) {
        return -4.0 / ((4.0 * x + 7.0) * Math.sqrt(4.0 * x + 7.0)) + 3.0 * Math.cos(x);
    }
    public static void binarySearch(double a, double b, double e) { //Метод биссекций
        System.out.println(" ");
        System.out.println("Метод биссекций");
        int count = 0;
        double c = 0;
        while (b - a > 2.0 * e) { //условие выхода из цикла
            c = (a + b) / 2.0;
            if (f(a) == 0.0) {
                System.out.println("X = " + a);
                System.out.println("|f(X) - 0| = " + 0);
                System.out.println("Произведено интераций : " + count);
                return;
            }
            if (f(b) == 0.0) {
                System.out.println("X = " + b);
                System.out.println("|f(X) - 0| = " + 0);
                System.out.println("Произведено интераций : " + count);
                return;
            }
            if (f(a) * f(c) < 0.0) {
                b = c;
            } else {
                a = c;
            }
            count++;
        }
        System.out.println("X = " + (a + b) / 2.0);
        System.out.println("|f(X) - 0| = " + Math.abs(f((a + b) / 2.0)));
        System.out.println("Произведено интераций : " + count);
    }
    public static void Newton(double a, double b, double e) { //Метод Ньютона
        System.out.println(" ");
        System.out.println("Метод Ньютона");
        double x = (a + b) / 2;
        double y = x;
        int count = 0;
        System.out.println("f(x0) * f''(x0) = " + f(x) * fDxx(x)); //для сходимости
        do {
            count++;
            y = x; //хранить предыдущее значение
            if(fDx(y) != 0) x = y - (f(y) / fDx(y));
            else break;
        } while(Math.abs(x - y) > e);

        System.out.println("X = " + x);
        System.out.println("|f(X) - 0| = " + Math.abs(f(x)));
        System.out.println("Произведено итераций : " + count);
    }
    public static void Newton2(double a, double b, double e) { //Модифицированный метод Ньютона
        System.out.println(" ");
        System.out.println("Модифицированный метод Ньютона");
        double x0 = (a + b) / 2;
        double x = x0;
        double y = x0;
        int count = 0;
        System.out.println("f(x0) * f''(x0) = " + f(x0) * fDxx(x0)); //для сходимости
        System.out.println("f'(x0) = " + fDx(x0));
        do {
            count++;
            y = x; //хранить предыдущее значение
            x = y - (f(y) / fDx((x0)));
        } while(Math.abs(x - y) > e);

        System.out.println("X = " + x);
        System.out.println("|f(X) - 0| = " + Math.abs(f(x)));
        System.out.println("Произведено итераций : " + count);
    }

    public static void Secant(double a, double b, double e) { //Метод секущих
        //x_k+1 = x, x_k = y, x_k-1 = z
        System.out.println(" ");
        System.out.println("Метод секущих");
        double x0 = (a + b) / 2;
        double x = x0 - f(x0) / fDx(x0);
        double y = x0;
        double z = 0;
        int count = 0;
        do {
            z = y;
            y = x;
            x = y - (f(y) / (f(y) - f(z))) * (y - z);
            count++;
        } while (Math.abs(x - y) > e);
        System.out.println("X = " + x);
        System.out.println("|f(X) - 0| = " + Math.abs(f(x)));
        System.out.println("Произведено итераций : " + count);
    }
}