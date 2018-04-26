package dcdesign.tictactoe;

/**
 * Created by barber-cironi on 4/26/2018.
 */

public class Square {

    public char currentValue; //0 = empty, 1 = X, 2 = O
    public boolean isEmpty;
    public int row;
    public int col;

    public Square()
    {
        row = 0;
        col = 0;
        currentValue = ' ';
        isEmpty = true;
    }
}
