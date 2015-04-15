package u7.u7a3;

import reversi.Coordinates;
import reversi.GameBoard;
import reversi.ReversiPlayer;

import java.util.ArrayList;

/**
 * Created by Ferdinand on 15.04.2015.
 */
public class FirstPlayer implements ReversiPlayer {
    private int color;

    @Override
    public void initialize(int color, long timeout) {
        this.color = color;
        if (color == GameBoard.RED)
        {
            System.out.println("FirstPlayer ist Spieler RED.");
        }
        else if (color == GameBoard.GREEN)
        {
            System.out.println("FirstPlayer ist Spieler GREEN.");
        }
    }

    @Override
    public Coordinates nextMove(GameBoard gameBoard) {
        System.out.println("I have: " + gameBoard.countStones(color) + " stones");

        ArrayList<Coordinates> possible = new ArrayList<>();

        try {

            for (int i = gameBoard.getSize() - 1; i > 0; i--) {
                for (int n = gameBoard.getSize() - 1; n > 0; n--) {
                    System.out.println("Occupation: " + i + " " + n + " " + gameBoard.getOccupation(new Coordinates(i, n)));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
