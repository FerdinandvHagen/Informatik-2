package u9.u9a2;

import reversi.Coordinates;
import reversi.GameBoard;
import reversi.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ferdinand on 28.04.2015.
 */
public class MinMaxTree {
    private final GameBoard gameBoard;
    private final int player;
    private List<MinMaxTreeItem> children;

    public MinMaxTree(GameBoard gameBoard, int player) {
        this.gameBoard = gameBoard.clone();
        this.player = player;
        children = new ArrayList<>();

        for (int i = 1; i < gameBoard.getSize(); i++) {
            for (int j = 1; j < gameBoard.getSize(); j++) {
                if (gameBoard.checkMove(player, new Coordinates(i, j))) {
                    children.add(new MinMaxTreeItem(gameBoard, new Coordinates(i,j), Utils.other(player), player));
                }
            }
        }
    }

    public Coordinates iterate() {
        int bestValue = 0;
        Coordinates coordinates = null;
        for(MinMaxTreeItem item : children) {
            if(item.addDepth() > bestValue) {
                coordinates = item.getCoordinate();
            }
        }

        return coordinates;
    }
}
