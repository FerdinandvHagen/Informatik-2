package u9.u9a2;

import reversi.Coordinates;
import reversi.GameBoard;
import reversi.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ferdinand on 28.04.2015.
 */
public class MinMaxTreeItem {
    private final GameBoard gameBoard;
    private final int player;
    private List<MinMaxTreeItem> children;
    private int score;
    private int scorePlayer;
    private Coordinates coordinates;

    public MinMaxTreeItem(GameBoard gameBoard, Coordinates coordinates, int player, int scorePlayer) {
        this.gameBoard = gameBoard.clone();
        this.gameBoard.checkMove(player, coordinates);
        this.gameBoard.makeMove(player, coordinates);
        children = new ArrayList<>();
        this.player = player;
        this.scorePlayer = scorePlayer;
        this.score = gameBoard.countStones(scorePlayer);
        this.coordinates = coordinates;
    }

    public int addDepth() {
        if (children.size() > 0) {
            for (MinMaxTreeItem minMaxTree : children) {
                minMaxTree.addDepth();
            }
        } else {
            for (int i = 1; i < gameBoard.getSize(); i++) {
                for (int j = 1; j < gameBoard.getSize(); j++) {
                    if (gameBoard.checkMove(player, new Coordinates(i, j))) {
                        children.add(new MinMaxTreeItem(gameBoard, new Coordinates(i, j), Utils.other(player),
                                scorePlayer));
                    }
                }
            }
        }

        if (player == scorePlayer) {
            int bestValue = 0;
            for (MinMaxTreeItem minMaxTreeItem : children) {
                if (minMaxTreeItem.getScore() > bestValue) {
                    bestValue = minMaxTreeItem.getScore();
                }
            }

            this.score = bestValue;
            return bestValue;
        } else {
            int worstValue = 100000000;
            for (MinMaxTreeItem minMaxTreeItem : children) {
                if (minMaxTreeItem.getScore() < worstValue) {
                    worstValue = minMaxTreeItem.getScore();
                }
            }

            this.score = worstValue;
            return worstValue;
        }
    }

    public int getScore() {
        return score;
    }

    public Coordinates getCoordinate() {
        return coordinates;
    }
}
