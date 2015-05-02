package u9.u9a2;

import reversi.Coordinates;
import reversi.GameBoard;
import reversi.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ferdinand on 29.04.2015.
 */
public class GameTree2 {
    private final int player;
    GameBoard gameBoard;
    Coordinates coordinates;
    List<GameTree2> children;

    public GameTree2(GameBoard gameBoard, Coordinates coordinates, int player) {
        this.gameBoard = gameBoard.clone();
        this.coordinates = coordinates;
        children = new ArrayList<>();
        this.player = player;
        if (coordinates != null) {
            this.gameBoard.checkMove(player, coordinates);
            this.gameBoard.makeMove(player, coordinates);
        }
    }

    public void addDepth() {
        if (children.size() > 0) {
            for (GameTree2 gameTree : children) {
                gameTree.addDepth();
            }
        } else {
            //Build the field
            //System.out.println("Builing field...");
            for (int r = 1; r <= gameBoard.getSize(); r++) {
                for (int c = 1; c <= gameBoard.getSize(); c++) {
                    if (gameBoard.checkMove(Utils.other(player), new Coordinates(r, c))) {
                        children.add(new GameTree2(gameBoard, new Coordinates(r, c), Utils.other(player)));
                    }
                }
            }
        }
    }

    public int calculate(int scorePlayer) {
        if (children.size() == 0) {
            return gameBoard.countStones(scorePlayer);
        } else {
            if (scorePlayer == player) {
                int maxValue = 0;
                for (GameTree2 gameTree : children) {
                    maxValue = Math.max(maxValue, gameTree.calculate(scorePlayer));
                }
                return maxValue;
            } else {
                int minValue = 1000000;
                for (GameTree2 gameTree : children) {
                    minValue = Math.min(minValue, gameTree.calculate(scorePlayer));
                }
                return minValue;
            }
        }
    }

    public GameTree2 chooseBest(int scorePlayer) {
        if (children.size() == 0) {
            System.err.println("Nothing found...");
            return null;
        }

        int maxValue = 0;
        GameTree2 best = null;
        for (GameTree2 gameTree : children) {
            int calculation = gameTree.calculate(scorePlayer);
            if (calculation > maxValue) {
                maxValue = calculation;
                best = gameTree;
            }
        }

        return best;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
