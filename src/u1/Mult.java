package u1;

public class Mult {
    /**
     * Berechnet das Produkt von zwei natürlichen Zahlen mit der ägyptischen Multiplikationsmethode
     * @param a 1. Faktor (a Element von N)
     * @param b 2. Faktor (b Element von N)
     * @return a*b
     * @throws IllegalArgumentException Zahlen sind keine natürlichen Zahlen
     */
    private static int f(int a, int b) throws IllegalArgumentException {
        if (a < 1 || b < 1) {
            throw new IllegalArgumentException();
        }

        if (b == 1) return a;
        if (b % 2 == 0) return f(2 * a, b / 2);
        else return a + f(2 * a, b / 2);
    }

    public static int mult(int a, int b) {
        return f(a, b);
    }
}
