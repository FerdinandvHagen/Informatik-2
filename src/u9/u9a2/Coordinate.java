package u9.u9a2;

/**
 * Created by Ferdinand on 28.04.2015.
 */
public class Coordinate implements Comparable<Coordinate> {
    private int score = 0;
    private int row;
    private int col;

    public Coordinate(int max, int r, int c) {
        score = max;
        row = r;
        col = c;
    }

    @Override
    public int compareTo(Coordinate o) {
        if (score == o.score) {
            return 0;
        } else if (score < o.score) {
            return 1;
        } else {
            return -1;
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return "Score: " + score + " ROW: " + row + " COL:" + col;
    }
}
