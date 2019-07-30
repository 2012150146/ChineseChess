import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class ChineseChess extends JFrame{
	ChineseChessCtrl control;
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem itemNew,itemNet,itemStop,itemExit;
	JTextField input;
	JTextArea output;
	Options Start,Return,Manual,ShowTurn;
	JPanel optionsPanel;
	
	static ChineseChess app;

	private ChineseChess(){
		setTitle("Chinese Chess");
		control = new ChineseChessCtrl();
		menuBar = new JMenuBar();
		menu = new JMenu("File");
	
		itemNew = new JMenuItem("New");
		itemNew.setActionCommand("NEW");
		itemNew.addActionListener(control);
		itemNew.setEnabled(false);
		menu.add(itemNew);
		
		itemNet = new JMenuItem("Net");
		itemNet.setActionCommand("NET");
		itemNet.addActionListener(control);
		itemNet.setEnabled(false);
		menu.add(itemNet);
		
		itemStop = new JMenuItem("Stop Net");
		itemStop.setActionCommand("STOP NET");
		itemStop.addActionListener(control);
		itemStop.setEnabled(false);
		menu.add(itemStop);
		
		menu.addSeparator();
		
		itemExit = new JMenuItem("Exit");
		itemExit.setActionCommand("EXIT");
		itemExit.addActionListener(control);
		menu.add(itemExit);
		
		menuBar.add(menu);
		setJMenuBar(menuBar);
		
		menu = new JMenu("User");
		JMenuItem itemSet = new JMenuItem("Preferences...");
		itemSet.setActionCommand("SET");
		itemSet.addActionListener(control);
		menu.add(itemSet);
		menuBar.add(menu);
	
		Game game = new Game();
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constrs = new GridBagConstraints();
		
		constrs.anchor = GridBagConstraints.FIRST_LINE_START;
		constrs.weightx = 1.0;
		
		getContentPane().setLayout(layout);
		BoardView.defaultBoardView().setGame(game);
	
		layout.setConstraints(BoardView.defaultBoardView(),constrs);
		add(BoardView.defaultBoardView());
		
		constrs.gridwidth = GridBagConstraints.REMAINDER;
		
		output = new JTextArea(10,14);
		output.setLineWrap(true);
		output.setEnabled(false);
		Font f = new Font("宋体", Font.PLAIN, 26);
		output.setFont(f);
		output.setForeground(Color.BLACK);
		JScrollPane scrollPane = new JScrollPane(output);
		layout.setConstraints(scrollPane,constrs);
		add(scrollPane);

		Box box = Box.createHorizontalBox();
		Toolkit tk = Toolkit.getDefaultToolkit();
		
		Start = new Options();
		Start.setImage(tk.createImage("Options/Start.png"));
		Start.setThisName("START");
		Start.addMouseListener(OptionsControl.defaultControl());
		Start.setBackground(UserProperties.getColorProperty("BoardColor"));
		box.add(Start);
		
		box.add(Box.createHorizontalStrut(53));
	
		Return = new Options();
		Return.setImage(tk.createImage("Options/Return3.png"));
		Return.setThisName("");
		Return.addMouseListener(OptionsControl.defaultControl());
		Return.setBackground(UserProperties.getColorProperty("BoardColor"));
		box.add(Return);
		
		box.add(Box.createHorizontalStrut(54));
	
		Manual = new Options();
		Manual.setImage(tk.createImage("Options/Manual3.png"));
		Manual.setThisName("");
		Manual.addMouseListener(OptionsControl.defaultControl());
		Manual.setBackground(UserProperties.getColorProperty("BoardColor"));
		box.add(Manual);
		
		optionsPanel = new JPanel();
		optionsPanel.setBackground(UserProperties.getColorProperty("BoardColor"));
		optionsPanel.add(box);
		
		ShowTurn = new Options();
		ShowTurn.setThisName("");
		ShowTurn.setImage(tk.createImage("pieces/rgeneral.png"));
		
		Box tempbox = Box.createHorizontalBox();
		tempbox.add(optionsPanel);
		tempbox.add(Box.createHorizontalStrut(65));
		tempbox.add(ShowTurn);
		
		constrs.gridwidth = GridBagConstraints.REMAINDER;
		constrs.weightx = 0.0;
		
		layout.setConstraints(tempbox,constrs);
		add(tempbox);
		
		constrs.gridwidth = GridBagConstraints.REMAINDER;
		
		input = new JTextField(27);
		input.setActionCommand("INPUT");
		input.addActionListener(control);
		layout.setConstraints(input,constrs);
		add(input);
		
		validate();
		setSize(498, 460);
		//setResizable(false);
		setVisible(true);
		
		addWindowListener(control);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
  
	static public ChineseChess runningApplication(){
		if (app == null) app = new ChineseChess();
			return app;
	}

	public void start(){
		BoardView.defaultBoardView().start();
		BoardView.defaultBoardView().setFlagValue();
		itemNew.setEnabled(true);
		itemNet.setEnabled(true);
	}
	
	public void online(String Message){
		Socket socket = GetSocket.getSocket(Message);
		
		if(socket!=null){
			Game game = new Game();
			BoardView.defaultBoardView().setGame(game);
			ChineseChess.runningApplication().setTurnShow('r');
			itemNet.setEnabled(false);
			itemStop.setEnabled(true);
			ChineseChess.runningApplication().returnSetEnabled(false);
			ChineseChess.runningApplication().manualSetEnabled(false);
			
			OnlineControl.newOnlineControl();
			
			if(Message.startsWith("L")){
				OnlineControl.get().set(socket,Message.charAt(1));
			}
			else{
				OnlineControl.get().set(socket,' ');
			}
			
			OnlineControl.get().start();
		}
	}
	
	public void setTurnShow(char toMove){
		Toolkit tk = Toolkit.getDefaultToolkit();
		
		if(toMove == 'r'){
			ShowTurn.setImage(tk.createImage("pieces/rgeneral.png"));
			ShowTurn.repaint();
		}
		else{
			ShowTurn.setImage(tk.createImage("pieces/bgeneral.png"));
			ShowTurn.repaint();
		}
	}
	
	public void returnSetEnabled(boolean b){
		Toolkit tk = Toolkit.getDefaultToolkit();
		
		if(b){
			Return.setImage(tk.createImage("Options/Return.png"));
			Return.repaint();
			Return.setThisName("RETURN");
		}
		else{
			Return.setImage(tk.createImage("Options/Return3.png"));
			Return.repaint();
			Return.setThisName("");
		}
	}
	
	public void manualSetEnabled(boolean b){
		Toolkit tk = Toolkit.getDefaultToolkit();
		
		if(b){
			Manual.setImage(tk.createImage("Options/Manual.png"));
			Manual.repaint();
			Manual.setThisName("MANUAL");
		}
		else{
			Manual.setImage(tk.createImage("Options/Manual3.png"));
			Manual.repaint();
			Manual.setThisName("");
		}
	}
	
	public void setOutput(String s){
		output.append(s);
	}
	
	public String getInput(){
		String temp = input.getText();
		input.setText("");
		return temp;
	}
	
	public void stopNet(){
		itemStop.setEnabled(false);
		itemNet.setEnabled(true);
		returnSetEnabled(false);
	}
	
	public void setColor(){
		optionsPanel.setBackground(UserProperties.getColorProperty("BoardColor"));
	}

	public static void main(String [] args){
		ChineseChess.runningApplication();
	}
}

class ChineseChessCtrl extends WindowAdapter implements ActionListener{
	public ChineseChessCtrl(){}
	
	public void actionPerformed(ActionEvent event){
		if(event.getActionCommand().compareTo("NEW") == 0){
			if(!BoardView.defaultBoardView().getGame().getIsOnline()){
				Game game = new Game();
				BoardView.defaultBoardView().setGame(game);
				BoardView.defaultBoardView().setColor('r');
				ChineseChess.runningApplication().setTurnShow('r');
				ChineseChess.runningApplication().returnSetEnabled(false);
				ChineseChess.runningApplication().manualSetEnabled(false);
			}
			else{
				OnlineControl.get().InputSomething("New?");
				Return.getWaitDialog().setVisible(true);
				BoardViewControl.defaultControl().setFlag(false);
			}
		}
		if(event.getActionCommand().compareTo("NET") == 0){
			Online dialog = new Online(ChineseChess.runningApplication());
			
			if(dialog.getMessage().length()!=0)
				ChineseChess.runningApplication().online(dialog.getMessage());
		}
		if(event.getActionCommand().compareTo("EXIT") == 0)
			System.exit(0);
			
		if(event.getActionCommand().compareTo("STOP NET") == 0){
			int n = JOptionPane.showConfirmDialog(ChineseChess.runningApplication(), "Are you sure?", "确定对话框", JOptionPane.INFORMATION_MESSAGE);
			
			if(n == JOptionPane.YES_OPTION)
				OnlineControl.get().close();
		}
		if(event.getActionCommand().compareTo("INPUT") == 0 && BoardView.defaultBoardView().getGame().getIsOnline()){
			String str = ChineseChess.runningApplication().getInput()+"\n";
			ChineseChess.runningApplication().setOutput(str);
			OnlineControl.get().InputSomething(" "+str);
		}
		if(event.getActionCommand().compareTo("SET") == 0){
			new UserPreferencesView(ChineseChess.runningApplication());
		}
	}
}

class Options extends JPanel{
	String thisName;
	Image image;
	
	public Options(){
		thisName = new String();
		image=null;
		setMinimumSize(new Dimension(60,33));
		setPreferredSize(new Dimension(60,33));
	}
	
	public void setThisName(String name){
		thisName=name;
	}
	
	public void setImage(Image i){
      image = i;
    }
	
	public String getThisName(){return thisName;}
	
	public void paint(Graphics gc){
		super.paint(gc);
		gc.drawImage(image,0,0,getWidth(),getHeight(),this);
		gc.drawRect(0,0,getWidth()-1,getHeight()-1);
    }
}