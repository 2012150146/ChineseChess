import java.util.Vector;

public class Ma extends Piece //Horse
{
  static Ma ma;
  private Ma(){}
  
  public static Vector<Destination> possibleDestinations(char board[][], SquareCoord origin, boolean checkCheck)
  {
    if (ma == null) ma = new Ma();
    return ma._possibleDestinations(board,origin, checkCheck);
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
	  
	  if(y+2 < 9)
	  {
		  if(board[x][y+1]==' ')
		  {
			  if (x+1 < 10) checkMove(moves, x+1,y+2,piece,color, tempBoard, board, checkCheck);
			  if (x-1 > -1) checkMove(moves, x-1, y+2,piece,color, tempBoard, board, checkCheck);
		  }
	  }
	  
	  if(y-2 > -1)
	  {
		  if(board[x][y-1] == ' ')
		  {
			  if (x+1 < 10) checkMove(moves, x+1,y-2,piece,color, tempBoard, board, checkCheck);
			  if (x-1 > -1) checkMove(moves, x-1,y-2,piece,color, tempBoard, board, checkCheck);
		  }
	  }
	  
	  if(x+2 < 10)
	  {
		  if(board[x+1][y]==' ')
		  {
			  if (y+1 < 9) checkMove(moves, x+2,y+1,piece,color, tempBoard, board, checkCheck);
			  if (y-1 > -1) checkMove(moves, x+2,y-1,piece,color, tempBoard, board, checkCheck);
		  }
	  }
	  
	  if(x-2 > -1)
	  {
		  if(board[x-1][y]==' ')
		  {
			  if (y+1 < 9) checkMove(moves, x-2,y+1,piece,color, tempBoard, board, checkCheck);
			  if (y-1 > -1) checkMove(moves, x-2,y-1,piece,color, tempBoard, board, checkCheck);
		  }
	  }
	  
	  return moves;
  }
}