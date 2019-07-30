import java.util.Vector;

public class Che extends Piece // Rook
{
  static Che che;
  private Che() {}
  public static Vector<Destination> possibleDestinations(char board[][], SquareCoord origin, boolean checkCheck)
  {
    if (che == null) che = new Che();
    return che._possibleDestinations(board,origin, checkCheck);
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
    
    for (i = x+1; i < 9 && board[i][y] == ' '; i++)
    	checkMove(moves, i, y, piece, color, tempBoard, board, checkCheck);
    
    if(i < 10)
    	checkMove(moves, i, y, piece, color, tempBoard, board, checkCheck);
    
    for (i = x-1; i > 0 && board[i][y] == ' '; i--)
    	checkMove(moves, i, y, piece, color, tempBoard, board, checkCheck);
    
    if(i > -1)
    	checkMove(moves, i, y, piece, color, tempBoard, board, checkCheck);
    
    for (i = y+1; i < 8 && board[x][i] == ' '; i++)
    	checkMove(moves, x, i, piece, color, tempBoard, board, checkCheck);
    
    if(i < 9)
    	checkMove(moves, x, i, piece, color, tempBoard, board, checkCheck);
    
    for (i = y-1; i > 0 && board[x][i] == ' '; i--)
    	checkMove(moves, x, i, piece, color, tempBoard, board, checkCheck);
    
    if(i > -1)
    	checkMove(moves, x, i, piece, color, tempBoard, board, checkCheck);
    
    return moves;
  }
}
