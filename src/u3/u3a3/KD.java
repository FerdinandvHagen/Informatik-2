package u3.u3a3;

public class KD {
    /**
     * Parse a "Klammerdarstellung" (KD) of a tree.
     * <p>
     * <ul>
     * <li>An empty tree is encoded as '-'.</li>
     * <li>A node is encoded as an uppercase letter, i.e. everything accepted by {@link Character#isUpperCase(char)}.</li>
     * <li>Children are appended to the father as a ','-separated list of nodes enclosed in round brackets.</li>
     * </ul>
     *
     * @param kd Tree encoded in KD
     * @throws ParseException if the given String is not a valid KD of a tree.
     */
    public static void parse(String kd) throws ParseException {
        if (parseBaum(kd, 0) != kd.length()) {
            throw new ParseException("Failed: Length does not match", kd.length());
        }
    }

    public static int parseBaum(String kd, int index) throws ParseException {
        if (charAt(kd, index) == '-') {
            return index + 1;
        }

        index = parseKnoten(kd, index);

        if (kd.length() == index) {
            return index;
        }

        if (charAt(kd, index) != '(') {
            return index;
        }

        index = parseUnterbaum(kd, index + 1);

        if (charAt(kd, index) != ')') {
            throw new ParseException("Missing closing bracket", index);
        }

        return index + 1;
    }

    private static int parseUnterbaum(String kd, int index) throws ParseException {
        do {
            index = parseBaum(kd, index);
        } while (charAt(kd, index++) == ',');

        return index - 1;
    }

    private static int parseKnoten(String kd, int index) throws ParseException {
        if (charAt(kd, index) < 'A' || charAt(kd, index) > 'Z') {
            throw new ParseException(kd.charAt(index) + "is no valid Knoten", index);
        }

        return index + 1;
    }

    private static char charAt(String kd, int index) throws ParseException {
        if (index >= kd.length()) {
            throw new ParseException("Index equals length", index);
        }

        return kd.charAt(index);
    }
}
