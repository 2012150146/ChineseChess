import java.util.Vector;

public class Shi extends Piece//Scholar
{
	static Shi shi;
	private Shi(){}
	
	public static Vector<Destination> possibleDestinations(char board[][], SquareCoord origin, boolean checkCheck)
	{
	    if (shi == null) shi = new Shi();
	    return shi._possibleDestinations(board,origin, checkCheck);
	}
	
	private Vector<Destination> _possibleDestinations(char [][] board, SquareCoord origin, boolean checkCheck)
	  {
	    if (Character.isUpperCase(board[origin.getRow()][origin.getFile()])) return red(board, origin, checkCheck);
	    else return blue(board, origin, checkCheck);
	  }
	
	private Vector<Destination> red(char [][] board, SquareCoord origin, boolean checkCheck)
	  {
	    Vector<Destination> moves = new Vector<Destination>();
	    int x = origin.getRow();
	    int y = origin.getFile();
	    char [][] tempBoard = fillTempBoard(board);
	    tempBoard[x][y] = ' ';
	    char piece = board[x][y];

	    if (x+1 < 3 && y+1 < 6) checkMove(moves, x+1,y+1,piece,'r', tempBoard, board, checkCheck);
	    if (x+1 < 3 && y-1 > 2) checkMove(moves, x+1,y-1,piece,'r', tempBoard, board, checkCheck);
	    if (x-1 > -1 && y+1 < 6) checkMove(moves, x-1, y+1,piece, 'r', tempBoard, board, checkCheck);
	    if (x-1 > -1 && y-1 > 2) checkMove(moves, x-1,y-1,piece,'r', tempBoard, board, checkCheck);

	    return moves;
	  }
	  private Vector<Destination> blue(char [][] board, SquareCoord origin, boolean checkCheck)
	  {
	    Vector<Destination> moves = new Vector<Destination>();
	    int x = origin.getRow();
	    int y = origin.getFile();
	    char [][] tempBoard = fillTempBoard(board);
	    tempBoard[x][y] = ' ';
	    char piece = board[x][y];
	    
	    if (x+1 < 10 && y+1 < 6) checkMove(moves, x+1,y+1,piece,'b', tempBoard, board, checkCheck);
	    if (x+1 < 10 && y-1 > 2) checkMove(moves, x+1,y-1,piece,'b', tempBoard, board, checkCheck);
	    if (x-1 > 6 && y+1 < 6) checkMove(moves, x-1, y+1,piece, 'b', tempBoard, board, checkCheck);
	    if (x-1 > 6 && y-1 > 2) checkMove(moves, x-1,y-1,piece,'b', tempBoard, board, checkCheck);

	    return moves;
	  }
}
