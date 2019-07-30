import java.util.Vector;

public class Pao extends Piece // Cannon
{
  static Pao pao;
  private Pao() {}
  public static Vector<Destination> possibleDestinations(char board[][], SquareCoord origin, boolean checkCheck)
  {
    if (pao == null) pao = new Pao();
    return pao._possibleDestinations(board,origin, checkCheck);
  }

  private Vector<Destination> _possibleDestinations(char board[][], SquareCoord origin, boolean checkCheck)
  {
    Vector<Destination> moves = new Vector<Destination>();
    int x = origin.getRow();
    int y = origin.getFile();
    char [][] tempBoard = fillTempBoard(board);
    tempBoard[x][y] = ' ';
    char piece = board[x][y];
    char color = ' ';
    if (Character.isUpperCase(piece)) color = 'r';
    else color = 'b';
    
    int i = 0;
    
    for (i = x+1; i < 10 && board[i][y] == ' '; i++)
    	checkMove(moves, i, y, piece, color, tempBoard, board, checkCheck);
    for (i=i+1; i < 9 && board[i][y] == ' '; i++);
    if(i < 10 && board[i][y]!=' ')
    	checkMove(moves, i, y, piece, color, tempBoard, board, checkCheck);
    
    for (i = x-1; i > -1 && board[i][y] == ' '; i--)
    	checkMove(moves, i, y, piece, color, tempBoard, board, checkCheck);
    for (i=i-1; i > 0 && board[i][y] == ' '; i--);
    if(i > -1 && board[i][y]!=' ')
    	checkMove(moves, i, y, piece, color, tempBoard, board, checkCheck);
    
    for (i = y+1; i < 9 && board[x][i] == ' '; i++)
    	checkMove(moves, x, i, piece, color, tempBoard, board, checkCheck);
    for (i=i+1; i < 8 && board[x][i] == ' '; i++);
    if(i < 9 && board[x][i]!=' ')
    	checkMove(moves, x, i, piece, color, tempBoard, board, checkCheck);
    
    for (i = y-1; i > -1 && board[x][i] == ' '; i--)
    	checkMove(moves, x, i, piece, color, tempBoard, board, checkCheck);
    for (i=i-1; i > 0 && board[x][i] == ' '; i--);
    if(i > -1 && board[x][i]!=' ')
    	checkMove(moves, x, i, piece, color, tempBoard, board, checkCheck);
    
    return moves;
  }
}