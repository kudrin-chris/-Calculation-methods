public class Text {
    public static void print(int p) {
        if(p == 0){
            System.out.println(" ");
        }
        if (p == 1) {
            System.out.println("Задача алгебраического интерполирования");
            System.out.println("Вариант 4");
            System.out.println("f(x) = sqrt(1 + x ^ 2)");
        }
        if(p == 2){
            System.out.println("Введите число значений в таблице:");
            System.out.print("m + 1 = ");
        }
        if(p == 3){
            System.out.print("Введите границы отрезка А и В: ");
        }
        if(p == 4){
            System.out.println(" ");
            System.out.println("ХОТИТЕ ПРОДОЛЖИТЬ? yes/no");
        }
        if(p == 5){
            System.out.println("Значение в какой точке хотите узнать?");
            System.out.print("Х = ");
        }
        if(p == 6){
            System.out.print("Введите степень многочлена, не больше m = ");
        }
        if(p == 7){
            System.out.println("Нужно ввести число не больше m!");
        }
        if(p == 8){
            System.out.print("P_n  Методом Лагранжа = ");
        }
        if(p == 9){
            System.out.print("P_n Методом Ньютона = ");
        }
        if(p == 10){
            System.out.print("|f(x) - P_n(x)| = ");
        }
        if(p == 11){
            System.out.println(" ");
            System.out.print("n = ");
        }
    }
}
