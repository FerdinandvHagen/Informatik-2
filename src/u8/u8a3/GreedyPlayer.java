package u8.u8a3;

import reversi.Coordinates;
import reversi.GameBoard;
import reversi.OutOfBoundsException;
import reversi.ReversiPlayer;

/**
 * Created by Ferdinand on 22.04.2015.
 */
public class GreedyPlayer implements ReversiPlayer {
    private int player;

    @Override
    public void initialize(int color, long timeout) {
        this.player = color;
        if (color == GameBoard.RED) {
            System.out.println("GreedyPlayer ist Spieler RED.");
        } else if (color == GameBoard.GREEN) {
            System.out.println("GreedyPlayer ist Spieler GREEN.");
        }
    }

    @Override
    public Coordinates nextMove(GameBoard gameBoard) {
        Coordinates result = null;
        int maxValue = 0;
        //Find all places
        for (int i = 0; i < gameBoard.getSize(); i++) {
            for (int n = 0; n < gameBoard.getSize(); n++) {
                //Now we have every possibility
                if (gameBoard.checkMove(this.player, new Coordinates(i + 1, n + 1))) {
                    try {
                        if (getMaxStones(gameBoard, new Coordinates(i + 1, n + 1)) > maxValue) {
                            System.out.println("Found good coordinates: " + new Coordinates(i + 1, n + 1) + " Stones:" +
                                    " " + getMaxStones(gameBoard, new Coordinates(i + 1, n + 1)));
                            result = new Coordinates(i + 1, n + 1);
                            maxValue = getMaxStones(gameBoard, new Coordinates(i + 1, n + 1));
                        }
                        ;
                    } catch (OutOfBoundsException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        System.out.println("Using coordinates: " + result);

        return result;
    }

    public int getMaxStones(GameBoard gb, Coordinates coord) throws OutOfBoundsException {
        int sum = 0;
        sum += getSum(gb, coord, -1, -1);
        sum += getSum(gb, coord, -1, 0);
        sum += getSum(gb, coord, -1, 1);
        sum += getSum(gb, coord, 0, -1);
        sum += getSum(gb, coord, 0, 1);
        sum += getSum(gb, coord, 1, -1);
        sum += getSum(gb, coord, 1, 0);
        sum += getSum(gb, coord, 1, 1);

        return sum;
    }

    private int getSum(GameBoard gb, Coordinates coord, int s, int t) throws OutOfBoundsException {
        int stones = 0;
        for (int i = 1; gb.validCoordinates(new Coordinates(coord.getRow() + (i * s), coord.getCol() + (i * t))); i++) {
            int occupation = gb.getOccupation(new Coordinates(coord.getRow() + (i * s), coord.getCol() + (i * t)));
            if (occupation == player && stones > 0) {
                return stones + 1;
            }

            if (occupation == 0 || occupation == player) {
                break;
            }

            stones++;
        }
        return 0;
    }
}
