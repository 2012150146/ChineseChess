import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Online extends JDialog implements ActionListener{
	JRadioButton Listen,Connect;
	JTextField Ip,Port;
	JButton Ok,Cancel,Piece;
	Icon []icon;
	int i;
	String Message;
	
	Online(){}
	Online(JFrame win){
		super(win,"Online",true);
		
		Message = new String("");
		
		ButtonGroup buttonGroup = new ButtonGroup();
		Listen = new JRadioButton("Listen");
		Connect = new JRadioButton("Connect",true);
		buttonGroup.add(Listen);
		buttonGroup.add(Connect);
		Listen.addActionListener(this);
		Connect.addActionListener(this);
		
		Box RadioButtonBox = Box.createVerticalBox();
		RadioButtonBox.add(Listen);
		RadioButtonBox.add(Connect);
		RadioButtonBox.setBorder(BorderFactory.createLoweredBevelBorder());
		
		Ip = new JTextField(10);
		Port = new JTextField(4);
		
		Box TextFieldBox = Box.createVerticalBox();
		TextFieldBox.add(Ip);
		TextFieldBox.add(Box.createVerticalStrut(9));
		TextFieldBox.add(Port);
		
		Box JLabelBox = Box.createVerticalBox();
		JLabelBox.add(new JLabel("Hostname:"));
		JLabelBox.add(Box.createVerticalStrut(9));
		JLabelBox.add(new JLabel("Port num:"));
		
		Box TextBox = Box.createHorizontalBox();
		TextBox.add(JLabelBox);
		TextBox.add(Box.createHorizontalStrut(9));
		TextBox.add(TextFieldBox);
		TextBox.setBorder(BorderFactory.createLoweredBevelBorder());
		
		icon = new ImageIcon[2];
		icon[0] = new ImageIcon("pieces/rgeneral.png");
		icon[1] = new ImageIcon("pieces/bgeneral.png");
		i=0;
		Piece = new JButton(icon[i]);
		
		Piece.addActionListener(this);
		Piece.setEnabled(false);
		Piece.setFocusable(false);
		
		JPanel PiecePanel = new JPanel();
		PiecePanel.add(Piece);
		PiecePanel.setBorder(BorderFactory.createLoweredBevelBorder());
		
		JPanel panel = new JPanel();
		panel.add(RadioButtonBox);
		panel.add(TextBox);
		panel.add(PiecePanel);
		
		add(panel,BorderLayout.NORTH);
		
		Ok = new JButton("Ok");
		Ok.setFocusable(false);
		Ok.addActionListener(this);
		
		Cancel = new JButton("Cancel");
		Cancel.setFocusable(false);
		Cancel.addActionListener(this);
		
		JPanel ButtonPanel = new JPanel();
        ButtonPanel.add(Ok);
        ButtonPanel.add(Cancel);
        add(ButtonPanel,BorderLayout.SOUTH);
		
        setLocation(win.getX()+50,win.getY()+150);
        setSize(400,140);
        setResizable(false);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == Ok){
			this.dispose();
			
			if(Listen.isSelected()){
			
				String portNum = Port.getText();
				
				if(portNum.length() != 0){
					int tempNum = Integer.parseInt(portNum);
					
					if(1024 < tempNum && tempNum < 65535){
						if(i==0){
							Message+=("Lr-"+portNum);
						}
						else{
							Message+=("Lb-"+portNum);
						}
					}
					else{
						JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else{
					JOptionPane.showMessageDialog(this, "NULL input", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else{
				String portNum = Port.getText();
				String ipNum = Ip.getText();
				
				if(portNum.length() != 0 && ipNum.length() != 0){
				
					int tempNum = Integer.parseInt(portNum);
					
					if(1024 < tempNum && tempNum < 65535){
						Message+=("C"+portNum+"-"+ipNum);
					}
					else{
						JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else{
					JOptionPane.showMessageDialog(this, "NULL input", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		else if(e.getSource() == Cancel){
			this.dispose();
		}
		else if(e.getSource() == Listen){
			if(Listen.isSelected()){
				Piece.setEnabled(true);
				Ip.setEnabled(false);
			}
		}
		else if(e.getSource() == Connect){
			if(Connect.isSelected()){
				Piece.setEnabled(false);
				Ip.setEnabled(true);
			}
		}
		else if(e.getSource() == Piece){
			i++;
			i=i%2;
			Piece.setIcon(icon[i]);
		}
	}
	
	public String getMessage(){
		return Message;
	}
}