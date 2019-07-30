import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserPreferencesView extends JDialog implements ActionListener,PropertyObserver{
	JButton button[],Ok,Cancel,Reset;
	JLabel label[];
	Color color[];
	
	public UserPreferencesView(JFrame win){
        super(win,"User Preferences",true);
		button = new JButton[4];
		label = new JLabel[4];
		color = new Color[4];
		
		Box boxV1 = Box.createVerticalBox();
		boxV1.add(Box.createVerticalStrut(4));
		
		label[0] = new JLabel("Board Color:");
		label[1] = new JLabel("Selection Color:");
		label[2] = new JLabel("Destination Color:");
		label[3] = new JLabel("Capture Color:");
		
		for(int i=0;i<4;i++){
			boxV1.add(label[i]);
			boxV1.add(Box.createVerticalStrut(18));
		}
		
		Box boxV2 = Box.createVerticalBox();
		for(int i=0;i<4;i++){
			button[i] = new JButton("Set...");
			button[i].addActionListener(this);
			button[i].setFocusable(false);
			boxV2.add(button[i]);
			boxV2.add(Box.createVerticalStrut(8));
		}
		
		button[0].setBackground(UserProperties.getColorProperty("BoardColor"));
		button[1].setBackground(UserProperties.getColorProperty("HighlightColor"));
		button[2].setBackground(UserProperties.getColorProperty("DestinationColor"));
		button[3].setBackground(UserProperties.getColorProperty("CaptureColor"));
		
		for(int i=0;i < 4;i++){
			color[i] = null;
		}
		
		Box box = Box.createHorizontalBox();
		box.add(Box.createHorizontalStrut(8));
		box.add(boxV1);
		box.add(Box.createHorizontalStrut(8));
		box.add(boxV2);
		
		add(box,BorderLayout.NORTH);
		
		Ok = new JButton("ÊÇ");
		Ok.addActionListener(this);
		
		Cancel = new JButton("·ñ");
		Cancel.addActionListener(this);
		
		Reset = new JButton("ÖØÖÃ");
		Reset.addActionListener(this);
		Reset.setEnabled(false);
		
		JPanel panel = new JPanel();
        panel.add(Ok);
        panel.add(Cancel);
		panel.add(Reset);
		add(panel,BorderLayout.SOUTH);
		
		setSize(210,220);
		setResizable(false);
		setLocation(win.getX()+50,win.getY()+150);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource() == Cancel){
			dispose();
		}
		if(ae.getSource() == Ok){
			UserProperties.defaultProperties().addObserver(this);
			
			for(int i=0;i<4;i++){
				if(color[i] != null){
					String temp = label[i].getText();
					String property = temp.substring(0,temp.indexOf(" "))+temp.substring(temp.indexOf(" ")+1,temp.indexOf(":"));
					UserProperties.setColorProperty(property,color[i]);
				}
			}
			dispose();
		}
		if(ae.getSource() == Reset){
			color[0] = null;
			button[0].setBackground(UserProperties.getColorProperty("BoardColor"));
			
			color[1] = null;
			button[1].setBackground(UserProperties.getColorProperty("HighlightColor"));
			
			color[2] = null;
			button[2].setBackground(UserProperties.getColorProperty("DestinationColor"));
			
			color[3] = null;
			button[3].setBackground(UserProperties.getColorProperty("CaptureColor"));
		}
		
		for(int i=0;i<4;i++){
			if(ae.getSource() == button[i]){
				color[i] = JColorChooser.showDialog(this,"Choose Color",button[i].getBackground());
				
				if(color[i] != null){
					if(color[i] != button[i].getBackground()){
						button[i].setBackground(color[i]);
						Reset.setEnabled(true);
					}
				}
			}
		}
	}
	
	public void userPropertyChanged(){
		BoardView.defaultBoard.setColor();
		BoardView.getManualBoard().setColor();
		ChineseChess.runningApplication().setColor();
		UserProperties.storeUserProperties();
		
		UserProperties.defaultProperties().removeObserver(this);
	}
}