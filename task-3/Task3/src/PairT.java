public class PairT {
    double a;
    double b;

    PairT(double aa, double bb){
        a = aa;
        b = bb;
    }
    @Override
    public String toString() {
        return "[" + a + ", " + b + "]";
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }
}