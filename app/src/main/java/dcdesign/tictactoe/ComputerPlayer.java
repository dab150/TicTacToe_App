package dcdesign.tictactoe;

/**
 * Created by barber-cironi on 4/26/2018.
 */

public class ComputerPlayer {

        public boolean _moveToMake;
        public Board _gameBoard;
        public String _name;
        public Square _nextMove;

        public ComputerPlayer(Board gameBoard)
        {
            _gameBoard = gameBoard;
            _name = gameBoard._playerTwoName;
            _nextMove = new Square();
        }

    public void makeNextMove()
    {
        Square nextMove = getNextMove();
        _gameBoard.handleClick(_nextMove.row, _nextMove.col);
    }

    private Square getNextMove()
    {
        _moveToMake = false;

        randomMove(); //chooses a random empty spot as the default, lowest priority
        defensiveMove(); //checks for a game saving move and plays if available, medium priority
        offensiveMove(); //checks for a game winning move and plays if available, highest priority

        //if the desired move has already been filled by a previous computer move, make a random move to an empty space
        if (!_gameBoard._spaces[_nextMove.row][_nextMove.col].isEmpty)
        {
            randomMove();
        }

        return _nextMove;
    }

    private void randomMove()
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (_gameBoard._spaces[i][j].isEmpty)
                {
                    _nextMove.row = i;
                    _nextMove.col = j;
                    break;
                }
            }
        }
    }

    private void defensiveMove()
    {
        checkHorizontalBlock();
        checkVerticalBlock();
        checkDiagonalBlock();
        checkReverseDiagonalBlock();
    }

    private void checkHorizontalBlock()
    {
        int count = 0;
        int possibleRow = 2;
        int possibleCol = 2;

        if (_gameBoard._gameStarted)
        {
            for (int row = 0; row < 3; row++)
            {
                for (int col = 0; col < 3; col++)
                {
                    if(_gameBoard._spaces[row][col].isEmpty)
                    {
                        possibleRow = row;
                        possibleCol = col;
                    }
                    if (_gameBoard._spaces[row][col].currentValue == 'X')
                    {
                        count++;
                    }
                    if (count == 2)
                    {
                        _nextMove.row = possibleRow;
                        _nextMove.col = possibleCol;
                        _moveToMake = true;
                    }
                }
                count = 0;
            }
        }
    }

    private void checkVerticalBlock()
    {
        int count = 0;
        int possibleRow = 2;
        int possibleCol = 2;

        if (_gameBoard._gameStarted)
        {
            for (int col = 0; col < 3; col++)
            {
                for (int row = 0; row < 3; row++)
                {
                    if (_gameBoard._spaces[row][col].isEmpty)
                    {
                        possibleRow = row;
                        possibleCol = col;
                    }
                    if (_gameBoard._spaces[row][col].currentValue == 'X')
                    {
                        count++;
                    }
                    if (count == 2)
                    {
                        _nextMove.row = possibleRow;
                        _nextMove.col = possibleCol;
                        _moveToMake = true;
                    }
                }
                count = 0;
            }
        }
    }

    private void checkDiagonalBlock()
    {
        int count = 0;
        int possibleRow = 2;
        int possibleCol = 2;

        if (_gameBoard._gameStarted)
        {
            for (int row = 0, col = 0; row < 3 && col < 3; row++, col++)
            {
                if (_gameBoard._spaces[row][col].isEmpty)
                {
                    possibleRow = row;
                    possibleCol = col;
                }
                if (_gameBoard._spaces[row][col].currentValue == 'X')
                {
                    count++;
                }
                if (count == 2)
                {
                    _nextMove.row = possibleRow;
                    _nextMove.col = possibleCol;
                    _moveToMake = true;
                }
            }
            count = 0;
        }
    }

    private void checkReverseDiagonalBlock()
    {
        int count = 0;
        int possibleRow = 2;
        int possibleCol = 0;


        if (_gameBoard._gameStarted)
        {
            for (int row = 0, col = 2; row < 3 && col >= 0; row++, col--)
            {
                if (_gameBoard._spaces[row][col].isEmpty)
                {
                    possibleRow = row;
                    possibleCol = col;
                }
                if (_gameBoard._spaces[row][col].currentValue == 'X')
                {
                    count++;
                }
                if (count == 2)
                {
                    _nextMove.row = possibleRow;
                    _nextMove.col = possibleCol;
                    _moveToMake = true;
                }
            }
            count = 0;
        }
    }

    private void offensiveMove()
    {
        checkHorizontalPlay();
        checkVerticalPlay();
        checkDiagonalPlay();
        checkReverseDiagonalPlay();
    }

    private void checkHorizontalPlay()
    {
        int count = 0;
        int possibleRow = 2;
        int possibleCol = 2;

        if (_gameBoard._gameStarted)
        {
            for (int row = 0; row < 3; row++)
            {
                for (int col = 0; col < 3; col++)
                {
                    if (_gameBoard._spaces[row][col].isEmpty)
                    {
                        possibleRow = row;
                        possibleCol = col;
                    }
                    if (_gameBoard._spaces[row][col].currentValue == 'O')
                    {
                        count++;
                    }
                    if (count == 2)
                    {
                        _nextMove.row = possibleRow;
                        _nextMove.col = possibleCol;
                        _moveToMake = true;
                    }
                }
                count = 0;
            }
        }
    }

    private void checkVerticalPlay()
    {
        int count = 0;
        int possibleRow = 2;
        int possibleCol = 2;

        if (_gameBoard._gameStarted)
        {
            for (int col = 0; col < 3; col++)
            {
                for (int row = 0; row < 3; row++)
                {
                    if (_gameBoard._spaces[row][col].isEmpty)
                    {
                        possibleRow = row;
                        possibleCol = col;
                    }
                    if (_gameBoard._spaces[row][col].currentValue == 'O')
                    {
                        count++;
                    }
                    if (count == 2)
                    {
                        _nextMove.row = possibleRow;
                        _nextMove.col = possibleCol;
                        _moveToMake = true;
                    }
                }
                count = 0;
            }
        }
    }

    private void checkDiagonalPlay()
    {
        int count = 0;
        int possibleRow = 2;
        int possibleCol = 2;

        if (_gameBoard._gameStarted)
        {
            for (int row = 0, col = 0; row < 3 && col < 3; row++, col++)
            {
                if (_gameBoard._spaces[row][col].isEmpty)
                {
                    possibleRow = row;
                    possibleCol = col;
                }
                if (_gameBoard._spaces[row][col].currentValue == 'O')
                {
                    count++;
                }
                if (count == 2)
                {
                    _nextMove.row = possibleRow;
                    _nextMove.col = possibleCol;
                    _moveToMake = true;
                }
            }
            count = 0;
        }
    }

    private void checkReverseDiagonalPlay()
    {
        int count = 0;
        int possibleRow = 2;
        int possibleCol = 0;


        if (_gameBoard._gameStarted)
        {
            for (int row = 0, col = 2; row < 3 && col >= 0; row++, col--)
            {
                if (_gameBoard._spaces[row][col].isEmpty)
                {
                    possibleRow = row;
                    possibleCol = col;
                }
                if (_gameBoard._spaces[row][col].currentValue == 'O')
                {
                    count++;
                }
                if (count == 2)
                {
                    _nextMove.row = possibleRow;
                    _nextMove.col = possibleCol;
                    _moveToMake = true;
                }
            }
            count = 0;
        }
    }
}