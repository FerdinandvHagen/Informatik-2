package u9.u9a2;

import reversi.Coordinates;
import reversi.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ferdinand on 29.04.2015.
 */
public class GameTree {
    private final int player;
    private final Coordinates coordinates;
    private int[][] occupation;
    private List<GameTree> children;

    public GameTree(int[][] occupation, int player, Coordinates coordinates) {
        this.occupation = occupation;
        this.player = player;
        this.coordinates = coordinates;
        this.children = new ArrayList<>();

        makeMove(coordinates);

        //Build the field
        for (int r = 0; r < occupation.length; r++) {
            for (int c = 0; c < occupation.length; c++) {
                if(this.occupation[r][c] == -1) {
                    this.occupation[r][c] = 0;
                }
                if (this.occupation[r][c] == 0 && getMaxStones(this.occupation, r, c) > 0) {
                    this.occupation[r][c] = -1;
                }
            }
        }

        System.out.println("\n\nCurrent table:");
        for (int j = 0; j < occupation[0].length; j++) {
            System.out.print("\t#" + (j + 1));
        }
        for (int i = 0; i < occupation.length; i++) {
            System.out.print("\n#" + (i + 1));
            for (int j = 0; j < occupation[i].length; j++) {
                System.out.print("\t$" + occupation[i][j]);
            }
        }
    }

    private void makeMove(Coordinates coordinates) {

    }

    public void addDepth() {
        if (children.size() > 0) {
            for (GameTree gameTree : children) {
                gameTree.addDepth();
            }
        } else {
            //Build the field
            for (int r = 0; r < occupation.length; r++) {
                for (int c = 0; c < occupation.length; c++) {
                    if (this.occupation[r][c] == -1) {
                        int[][] clone = occupation.clone();
                        children.add(new GameTree(clone, Utils.other(player), new Coordinates(r + 1, c + 1)));
                    }
                }
            }
        }
    }

    public int calculate(int scorePlayer) {
        if (children.size() == 0) {
            int stones = 0;
            for (int r = 0; r < occupation.length; r++) {
                for (int c = 0; c < occupation.length; c++) {
                    if (this.occupation[r][c] == scorePlayer) {
                        stones++;
                    }
                }
            }
            return stones;
        } else {
            if (scorePlayer == player) {
                int maxValue = 0;
                for (GameTree gameTree : children) {
                    maxValue = Math.min(maxValue, gameTree.calculate(scorePlayer));
                }
                return maxValue;
            } else {
                int minValue = -1000;
                for (GameTree gameTree : children) {
                    minValue = Math.max(minValue, gameTree.calculate(scorePlayer));
                }
                return minValue;
            }
        }
    }

    public GameTree chooseBest(int scorePlayer) {
        if (children.size() == 0) {
            System.err.println("Nothing found...");
            return null;
        }

        int maxValue = 0;
        GameTree best = null;
        for (GameTree gameTree : children) {
            int calculation = gameTree.calculate(scorePlayer);
            if (calculation < maxValue) {
                maxValue = calculation;
                best = gameTree;
            }
        }

        return best;
    }

    public int getMaxStones(int[][] occupation, int row, int col) {
        int sum = 0;
        sum += getSum(occupation, row, col, -1, -1);
        sum += getSum(occupation, row, col, -1, 0);
        sum += getSum(occupation, row, col, -1, 1);
        sum += getSum(occupation, row, col, 0, -1);
        sum += getSum(occupation, row, col, 0, 1);
        sum += getSum(occupation, row, col, 1, -1);
        sum += getSum(occupation, row, col, 1, 0);
        sum += getSum(occupation, row, col, 1, 1);

        return sum;
    }

    private int getSum(int[][] gb, int row, int col, int s, int t) {
        int stones = 0;
        for (int i = 1; 0 <= (row + (i * s)) && (row + (i * s)) < gb.length && 0 <= col + (i * t) && col + (i * t) <
                gb.length; i++) {
            int occupation = gb[row + (i * s)][col + (i * t)];
            if (occupation == player && stones > 0) {
                return stones;
            }

            if (occupation == 0 || occupation == player) {
                break;
            }

            stones++;
        }
        return 0;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void explain() {
        System.out.println("\n\nCurrent table:");
        for (int j = 0; j < occupation[0].length; j++) {
            System.out.print("\t#" + (j + 1));
        }
        for (int i = 0; i < occupation.length; i++) {
            System.out.print("\n#" + (i + 1));
            for (int j = 0; j < occupation[i].length; j++) {
                System.out.print("\t$" + occupation[i][j]);
            }
        }

        System.out.println("\r\n");
        System.out.println("My Children: ");
        for (GameTree gameTree : children) {
            gameTree.explain();
        }
    }
}
