import java.util.SortedMap;

public class Text {
    public static void print(int p) {
        if(p == 0){
            System.out.println(" ");
        }
        if (p == 1) {
            System.out.println("f(x) = sqrt(1 + x ^ 2)");
        }
        if(p == 20){
            System.out.println("f(x) = sin(x)");
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
            System.out.println(" ");
            System.out.println("ХОТИТЕ ПРОДОЛЖИТЬ? yes/no");
        }
        if(p == 5){
            System.out.println("Введите значение точки F");
            System.out.print("F = ");
        }
        if(p == 6){
            System.out.print("Введите степень многочлена, не больше m = ");
        }
        if(p == 7){
            System.out.println("Нужно ввести число не больше m!");
        }
        if(p == 8){
            System.out.println(" ");
            System.out.print("Q_n(F) Методом Лагранжа = ");
        }
        if(p == 9){
            System.out.print("P_n Методом Ньютона = ");
        }
        if(p == 10){
            System.out.print("|f(x) - F| = ");
        }
        if(p == 11){
            System.out.println(" ");
            System.out.print("n = ");
        }
        if(p == 12){
            System.out.println(" ");
            System.out.println("Первый способ решения");
        }
        if(p == 13){
            System.out.println(" ");
            System.out.println("Функция монотонна? yes/no");
        }
        if(p == 14){
            System.out.println(" ");
            System.out.println("Второй способ решения");
            System.out.println(" ");
        }
    }
}
