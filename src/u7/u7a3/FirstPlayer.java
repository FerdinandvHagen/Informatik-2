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
    private int size;

    @Override
    public void initialize(int color, long timeout) {
        this.color = color;
        if (color == GameBoard.RED) {
            System.out.println("FirstPlayer ist Spieler RED.");
        } else if (color == GameBoard.GREEN) {
            System.out.println("FirstPlayer ist Spieler GREEN.");
        }
    }

    @Override
    public Coordinates nextMove(GameBoard gameBoard) {
        System.out.println("I have: " + gameBoard.countStones(color) + " stones");

        size = gameBoard.getSize();

        ArrayList<Coordinates> possible = new ArrayList<>();

        int[][] field = new int[gameBoard.getSize()][gameBoard.getSize()];
        int[][] possibles = new int[gameBoard.getSize()][gameBoard.getSize()];

        try {
            //Find all places
            for (int i = 0; i < gameBoard.getSize(); i++) {
                for (int n = 0; n < gameBoard.getSize(); n++) {
                    //Now we have every possibility
                    field[i][n] = gameBoard.getOccupation(new Coordinates(i + 1, n + 1));

                    if (gameBoard.checkMove(this.color, new Coordinates(i + 1, n + 1))) {
                        possibles[i][n] = 1;
                        possible.add(new Coordinates(i + 1, n + 1));
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        for (Coordinates coordinates : possible) {
            System.out.println("Found: " + coordinates.toString());
        }

        System.out.println("\n\nCurrent table:");
        for (int j = 0; j < possibles[0].length; j++) {
            System.out.print("\t#" + (j + 1));
        }
        for (int i = 0; i < possibles.length; i++) {
            System.out.print("\n#" + (i + 1));
            for (int j = 0; j < possibles[i].length; j++) {
                System.out.print("\t$" + possibles[i][j]);
            }
        }
        System.out.println();

        return possible.get((int)(Math.random() * possible.size() - 1));
    }
}
