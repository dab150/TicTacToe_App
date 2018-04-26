package dcdesign.tictactoe;

import android.app.AlertDialog;
import android.util.Log;

import static java.lang.System.in;
import dcdesign.tictactoe.Square;

/**
 * Created by barber-cironi on 4/26/2018.
 */

public class Board     {

    public boolean _gameStarted;
    public boolean _gameOver;
    public boolean _gameTied;
    public String _playerOneName;
    public String _playerTwoName;
    public String _activePlayerName;
    public String _winnerName;
    public Square[][] _spaces;

    public Board(String playerOneName, String playerTwoName)
    {
        _gameStarted = false;
        _gameOver = false;
        _playerOneName = playerOneName;
        _playerTwoName = playerTwoName;
        _activePlayerName = playerOneName;
        _winnerName = "";

        _spaces = new Square[3][3];
        for(int row = 0; row < 3; row++)
        {
            for(int col = 0; col < 3; col++)
            {
                _spaces[row][col] = new Square();
                _spaces[row][col].currentValue = ' ';
                _spaces[row][col].isEmpty = true;
            }
        }
    }

    public void resetBoard()
    {
        _gameStarted = false;
        _gameOver = false;
        _activePlayerName = _playerOneName;
        _winnerName = "";
        for(int row = 0; row < 3; row++)
        {
            for(int col = 0; col < 3; col++)
            {
                _spaces[row][col].currentValue = ' ';
                _spaces[row][col].isEmpty = true;
            }
        }
    }

    public String getActivePlayer()
    {
        return _activePlayerName;
    }

    public void changeActivePlayer()
    {
        Log.d("ChangeActivePlayer", "Change Player Routine Called");
        if (_activePlayerName == _playerOneName)
            _activePlayerName = _playerTwoName;
        else if (_activePlayerName == _playerTwoName)
            _activePlayerName = _playerOneName;
    }

    public void handleClick(int row, int col)
    {
        if (!_gameOver && !_gameTied && _gameStarted && _spaces[row][col].isEmpty)
        {
            if (_activePlayerName == _playerOneName)
            {
                Log.d("HandleClick", "Player One Clicked");
                _spaces[row][col].currentValue = 'X';
                _spaces[row][col].isEmpty = false;
            }
            else if (_activePlayerName == _playerTwoName)
            {
                Log.d("HandleClick", "Player Two Clicked");
                _spaces[row][col].currentValue = 'O';
                _spaces[row][col].isEmpty = false;
            }
            changeActivePlayer();
        }
    }

    public boolean isGameWon()
    {
        determineWinner();
        if(!_winnerName.isEmpty())
            return true;
        else
            return false;
    }

    private String determineWinner()
    {
        checkHorizontal();
        checkVertical();
        checkDiagonal();
        checkReverseDiagonal();
        return _winnerName;
    }

    public boolean isGameTied()
    {
        for(int row = 0; row < 3; row++)
        {
            for(int col = 0; col < 3; col++)
            {
                if(_spaces[row][col].isEmpty)
                {
                    return false;
                }
            }
        }
        Log.i("Game State","Game Tied!");
        return true;
    }

    private void checkHorizontal()
    {
        int count = 0;

        if (_gameStarted)
        {
            for (int row = 0; row < 3; row++)
            {
                for (int col = 0; col < 3; col++)
                {
                    if (_spaces[row][col].currentValue == 'X')
                    {
                        count++;
                        if (count == 3)
                        {
                            _gameOver = true;
                            _gameStarted = false;
                            _winnerName = _playerOneName;
                        }
                    }
                        else
                    {
                        count = 0;
                        break;
                    }
                }
            }
            for (int row = 0; row < 3; row++)
            {
                for (int col = 0; col < 3; col++)
                {
                    if (_spaces[row][col].currentValue == 'O')
                    {
                        count++;
                        if (count == 3)
                        {
                            _gameOver = true;
                            _gameStarted = false;
                            _winnerName = _playerTwoName;
                        }
                    }
                        else
                    {
                        count = 0;
                        break;
                    }
                }
            }
        }
    }

    private void checkVertical()
    {
        int count = 0;

        if (_gameStarted)
        {
            for (int col = 0; col < 3; col++)
            {
                for (int row = 0; row < 3; row++)
                {
                    if (_spaces[row][col].currentValue == 'X')
                    {
                        count++;
                        if (count == 3)
                        {
                            _gameOver = true;
                            _gameStarted = false;
                            _winnerName = _playerOneName;
                        }
                    }
                        else
                    {
                        count = 0;
                        break;
                    }
                }
            }
            for(int col = 0; col < 3; col++)
            {
                for(int row = 0; row < 3; row++)
                {
                    if (_spaces[row][col].currentValue == 'O')
                    {
                        count++;
                        if (count == 3)
                        {
                            _gameOver = true;
                            _gameStarted = false;
                            _winnerName = _playerTwoName;
                        }
                    }
                        else
                    {
                        count = 0;
                        break;
                    }
                }
            }
        }
    }

    private void checkDiagonal()
    {
        int count = 0;

        if (_gameStarted)
        {
            for (int col = 0, row = 0; col < 3 && row < 3; col++, row++)
            {
                if (_spaces[row][col].currentValue == 'X')
                {
                    count++;
                    if (count == 3)
                    {
                        _gameOver = true;
                        _gameStarted = false;
                        _winnerName = _playerOneName;
                    }
                }
                    else
                {
                    count = 0;
                    break;
                }
            }
            for (int col = 0, row = 0; col < 3 && row < 3; col++, row++)
            {
                if (_spaces[row][col].currentValue == 'O')
                {
                    count++;
                    if (count == 3)
                    {
                        _gameOver = true;
                        _gameStarted = false;
                        _winnerName = _playerTwoName;
                    }
                }
                    else
                {
                    count = 0;
                    break;
                }
            }

        }
    }

    private void checkReverseDiagonal()
    {
        int count = 0;

        if (_gameStarted)
        {
            for (int col = 2, row = 0; col >= 0 && row < 3; col--, row++)
            {
                if (_spaces[row][col].currentValue == 'X')
                {
                    count++;
                    if (count == 3)
                    {
                        _gameOver = true;
                        _gameStarted = false;
                        _winnerName = _playerOneName;
                    }
                }
                    else
                {
                    count = 0;
                    break;
                }
            }

            for (int col = 2, row = 0; col >= 0 && row < 3; col--, row++)
            {
                if (_spaces[row][col].currentValue == 'O')
                {
                    count++;
                    if (count == 3)
                    {
                        _gameOver = true;
                        _gameStarted = false;
                        _winnerName = _playerTwoName;
                    }
                }
                    else
                {
                    count = 0;
                    break;
                }
            }

        }
    }
}