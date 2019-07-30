import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import	java.util.*;

public class Manual extends JDialog implements ActionListener{
	Game game;
	String []Save;
	int saveSize,saveMove;
	
	public Manual(){}
	public Manual(JFrame win){
		super(win,"Manual",true);
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constrs = new GridBagConstraints();
		
		constrs.gridwidth = GridBagConstraints.REMAINDER;
		getContentPane().setLayout(layout);
		
		game = BoardView.defaultBoardView().getGame();
		Save = game.getSave();
		saveSize = game.getSaveSize();
		saveMove = 0;
		Save[saveSize] = game.getPosition();
		
		if(BoardView.defaultBoardView().getGame().getIsOnline()){
		
			if(OnlineControl.get().getTurn() == 'b')
				BoardView.getManualBoard().setReverse(false);
		}
			
		BoardView.getManualBoard().newPosition(Save[0]);
		
		layout.setConstraints(BoardView.getManualBoard(),constrs);
		add(BoardView.getManualBoard());
		
		Box box = Box.createHorizontalBox();
		
		JButton previous = new JButton(" < ");
		previous.setActionCommand("PREVIOUS");
		previous.setFocusable(false);
		previous.addActionListener(this);
		box.add(previous);
		
		box.add(Box.createHorizontalStrut(20));
		
		JButton next = new JButton(" > ");
		next.setActionCommand("NEXT");
		next.setFocusable(false);
		next.addActionListener(this);
		box.add(next);
		
		layout.setConstraints(box,constrs);
		add(box);
		
		setLocation(win.getX()+50,win.getY()+150);
		setSize(320, 420);
        setResizable(false);
	}
	
	public void start(){
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event){
		if(event.getActionCommand().compareTo("PREVIOUS") == 0){
			if(saveMove > 0){
				saveMove--;
				BoardView.getManualBoard().newPosition(Save[saveMove]);
			}
		}
		if(event.getActionCommand().compareTo("NEXT") == 0){
			if(saveMove < saveSize){
				saveMove++;
				BoardView.getManualBoard().newPosition(Save[saveMove]);
			}
		}
	}
}