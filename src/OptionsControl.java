import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OptionsControl extends MouseAdapter{
	static OptionsControl defaultControl = null;
	Return dialogReturn = null;
	
	protected OptionsControl(){
	}
	
	public void mouseClicked(MouseEvent event){
		Options thisOptions = (Options)event.getSource();
		Toolkit tk = Toolkit.getDefaultToolkit();
		
		if(thisOptions.getThisName().compareTo("START") == 0){
			ChineseChess.runningApplication().start();
			thisOptions.setThisName("");
			thisOptions.setImage(tk.createImage("Options/Start3.png"));
			thisOptions.repaint();
		}
		else if(thisOptions.getThisName().compareTo("MANUAL") == 0){
			Manual manual = new Manual(ChineseChess.runningApplication());
			manual.start();
		}
		else if(thisOptions.getThisName().compareTo("RETURN") == 0){
			if(!(BoardView.defaultBoardView().getGame().getIsOnline())){
				Return dialog = new Return(ChineseChess.runningApplication(),Return.select);
				boolean flag = dialog.getStatus();
			
				if(flag){
					BoardView.defaultBoard.getGame().MoveBack();
				}
			}
			else{
				OnlineControl.get().InputSomething("MoveBack?");
				Return.getWaitDialog().setVisible(true);
				BoardViewControl.defaultControl().setFlag(false);
			}
		}
	}
	
	public void mouseExited(MouseEvent event){
		Options thisOptions = (Options)event.getSource();
		Toolkit tk = Toolkit.getDefaultToolkit();
		
		if(thisOptions.getThisName().compareTo("START") == 0){
			thisOptions.setImage(tk.createImage("Options/Start.png"));
			thisOptions.repaint();
		}
		else if(thisOptions.getThisName().compareTo("MANUAL") == 0){
			thisOptions.setImage(tk.createImage("Options/Manual.png"));
			thisOptions.repaint();
		}
		else if(thisOptions.getThisName().compareTo("RETURN") == 0){
			thisOptions.setImage(tk.createImage("Options/Return.png"));
			thisOptions.repaint();
		}
	}
	
	public void mouseEntered(MouseEvent event){
		Options thisOptions = (Options)event.getSource();
		Toolkit tk = Toolkit.getDefaultToolkit();
		
		if(thisOptions.getThisName().compareTo("START") == 0){
			thisOptions.setImage(tk.createImage("Options/Start2.png"));
			thisOptions.repaint();
		}
		else if(thisOptions.getThisName().compareTo("MANUAL") == 0){
			thisOptions.setImage(tk.createImage("Options/Manual2.png"));
			thisOptions.repaint();
		}
		else if(thisOptions.getThisName().compareTo("RETURN") == 0){
			thisOptions.setImage(tk.createImage("Options/Return2.png"));
			thisOptions.repaint();
		}
	}
	
	public static OptionsControl defaultControl(){
		if (defaultControl == null)
			defaultControl = new OptionsControl();
		return defaultControl;
	}
}