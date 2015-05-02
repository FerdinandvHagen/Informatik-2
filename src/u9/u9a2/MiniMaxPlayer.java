package u9.u9a2;

import reversi.*;

import java.util.*;

/**
 * Created by Ferdinand on 28.04.2015.
 */
public class MiniMaxPlayer implements ReversiPlayer {
    private static final int MAX_CHECKS = 3;
    private int player;
    private long timeLimit;
    private long curTime;

    private GameTree2 gameTree = null;

    @Override
    public void initialize(int i, long l) {
        this.player = i;
        this.timeLimit = 5000;
        System.out.println("TIMELIMIT: " + timeLimit);

    }

    @Override
    public Coordinates nextMove(GameBoard gameBoard) {
        GameTree2 gameTree2 = new GameTree2(gameBoard, null, Utils.other(player));

        for (int i = 0; i < 5; i++) {
            gameTree2.addDepth();
        }

        //gameTree.explain();

        GameTree2 result = gameTree2.chooseBest(player);

        if (result == null) {
            return null;
        }

        return result.getCoordinates();
    }

    private void initializeGameTree(GameBoard gameBoard, int[][] gameBoard2) throws OutOfBoundsException {
        for (int i = 1; i < gameBoard.getSize(); i++) {
            for (int j = 1; j < gameBoard.getSize(); j++) {
                gameBoard2[i - 1][j - 1] = gameBoard.getOccupation(new Coordinates(i, j));
            }
        }
    }

    private void calculate() {
        int checked = 0;
        long time = System.currentTimeMillis();

    }

    private Coordinate max(int[][] occupation) {
        //First get valid coordinates
        List<Coordinate> coordinateList = new ArrayList<>();
        Map<Coordinates, Integer> stoneMap = new HashMap<>();
        for (int r = 0; r < occupation.length; r++) {
            for (int c = 0; c < occupation.length; c++) {
                int max = getMaxStones(occupation, r, c);
                if (max > 0) {
                    //Valid Coordinates; Save them somehow
                    coordinateList.add(new Coordinate(max, r, c));
                }
            }
        }

        Collections.sort(coordinateList);
        for (Coordinate coordinate : coordinateList) {
            System.out.println("ME: " + coordinate);
        }


        if (coordinateList.size() == 0) {
            return null;
        }

        return coordinateList.get(0);
    }

    public int getMaxStones(int[][] occupation, int row, int col) {
        if (occupation[row][col] != 0) {
            return 0;
        }

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
}
