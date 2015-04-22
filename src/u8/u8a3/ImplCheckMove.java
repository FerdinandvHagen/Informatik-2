package u8.u8a3;

import reversi.Coordinates;
import reversi.GameBoard;
import reversi.OutOfBoundsException;

/**
 * Created by Ferdinand on 22.04.2015.
 */
public class ImplCheckMove implements ICheckMove {
    @Override
    public boolean checkMove(GameBoard gb, int player, Coordinates coord) {
        try {
            return gb.validCoordinates(coord) && gb.getOccupation(coord) == 0 && checkStone(gb, player, coord);
        } catch (Exception ex) {
            return false;
        }
    }

    private boolean checkStone(GameBoard gb, int player, Coordinates coord) throws OutOfBoundsException {
        int stones = 0;
        for (int i = coord.getCol() - 1; gb.validCoordinates(new Coordinates(coord.getRow(), i)); i--) {
            int occupation = gb.getOccupation(new Coordinates(coord.getRow(), i));
            if (occupation == player && stones > 0) {
                return true;
            }

            if (occupation == 0 || occupation == player) {
                break;
            }

            stones++;
        }

        stones = 0;
        for (int i = coord.getCol() + 1; gb.validCoordinates(new Coordinates(coord.getRow(), i)); i++) {
            int occupation = gb.getOccupation(new Coordinates(coord.getRow(), i));
            if (occupation == player && stones > 0) {
                return true;
            }

            if (occupation == 0 || occupation == player) {
                break;
            }

            stones++;
        }

        stones = 0;
        for (int i = coord.getRow() + 1; gb.validCoordinates(new Coordinates(i, coord.getCol())); i++) {
            int occupation = gb.getOccupation(new Coordinates(i, coord.getCol()));
            if (occupation == player && stones > 0) {
                return true;
            }

            if (occupation == 0 || occupation == player) {
                break;
            }

            stones++;
        }

        stones = 0;
        for (int i = coord.getRow() - 1; gb.validCoordinates(new Coordinates(i, coord.getCol())); i--) {
            int occupation = gb.getOccupation(new Coordinates(i, coord.getCol()));
            if (occupation == player && stones > 0) {
                return true;
            }

            if (occupation == 0 || occupation == player) {
                break;
            }

            stones++;
        }

        //Now Diagonal
        stones = 0;
        for (int i = 1; gb.validCoordinates(new Coordinates(coord.getRow() + i, coord.getCol() + i)); i++) {
            int occupation = gb.getOccupation(new Coordinates(coord.getRow() + i, coord.getCol() + i));
            if (occupation == player && stones > 0) {
                return true;
            }

            if (occupation == 0 || occupation == player) {
                break;
            }

            stones++;
        }
        stones = 0;
        for (int i = 1; gb.validCoordinates(new Coordinates(coord.getRow() + i, coord.getCol() - i)); i++) {
            int occupation = gb.getOccupation(new Coordinates(coord.getRow() + i, coord.getCol() - i));
            if (occupation == player && stones > 0) {
                return true;
            }

            if (occupation == 0 || occupation == player) {
                break;
            }

            stones++;
        }
        stones = 0;
        for (int i = 1; gb.validCoordinates(new Coordinates(coord.getRow() - i, coord.getCol() + i)); i++) {
            int occupation = gb.getOccupation(new Coordinates(coord.getRow() - i, coord.getCol() + i));
            if (occupation == player && stones > 0) {
                return true;
            }

            if (occupation == 0 || occupation == player) {
                break;
            }

            stones++;
        }
        stones = 0;
        for (int i = 1; gb.validCoordinates(new Coordinates(coord.getRow() - i, coord.getCol() - i)); i++) {
            int occupation = gb.getOccupation(new Coordinates(coord.getRow() - i, coord.getCol() - i));
            if (occupation == player && stones > 0) {
                return true;
            }

            if (occupation == 0 || occupation == player) {
                break;
            }

            stones++;
        }

        return false;
    }
}
