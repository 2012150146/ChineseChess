import java.util.Vector;

public class Xiang extends Piece // Elephant
{
  static Xiang xiang;
  private Xiang() {}
  
  public static Vector<Destination> possibleDestinations(char board[][], SquareCoord origin, boolean checkCheck)
  {
    if (xiang == null) xiang = new Xiang();
    return xiang._possibleDestinations(board,origin, checkCheck);
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
    
    if (color == 'r')
      {
        if (x+2 < 5 && y+2 < 9)
        	if(board[x+1][y+1]==' ')
        		checkMove(moves, x+2, y+2, piece, color, tempBoard, board, checkCheck);
        
        if (x+2 < 5 && y-2 > -1)
        	if(board[x+1][y-1]==' ')
        		checkMove(moves, x+2, y-2, piece, color, tempBoard, board, checkCheck);
        
        if (x-2 > -1 && y+2 < 9)
        	if(board[x-1][y+1]==' ')
        		checkMove(moves, x-2, y+2, piece, color, tempBoard, board, checkCheck);
        
        if (x-2 > -1 && y-2 > -1)
        	if(board[x-1][y-1]==' ')
        		checkMove(moves, x-2, y-2, piece, color, tempBoard, board, checkCheck);
      }
    
    if (color == 'b')
      {
    	if (x+2 < 10 && y+2 < 9)
    		if(board[x+1][y+1]==' ')
    			checkMove(moves, x+2, y+2, piece, color, tempBoard, board, checkCheck);
    
    	if (x+2 < 10 && y-2 > -1)
    		if(board[x+1][y-1]==' ')
    			checkMove(moves, x+2, y-2, piece, color, tempBoard, board, checkCheck);
    
    	if (x-2 > 4 && y+2 < 9)
    		if(board[x-1][y+1]==' ')
    			checkMove(moves, x-2, y+2, piece, color, tempBoard, board, checkCheck);
    
    	if (x-2 > 4 && y-2 > -1)
    		if(board[x-1][y-1]==' ')
    			checkMove(moves, x-2, y-2, piece, color, tempBoard, board, checkCheck);
      }
    
    return moves;
  }
}