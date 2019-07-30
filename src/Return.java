import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Return extends JDialog implements ActionListener{
	public static final int select = 0,wait = 1,select_yes = 2,select_no = 3;
	
	public static Return dialogwait = null;
	
    JLabel Show;
    boolean Status=false;
    
    public Return(){}
	
    public Return(JFrame win,int Mode){
        super(win,"back moves",false);
		setLocation(win.getX()+50,win.getY()+150);
		setResizable(false);
		
		Show = new JLabel();
		Show.setFont(new Font("宋体",Font.BOLD,16));
		Show.setForeground(new Color(0,95,191));
		add(Show,BorderLayout.CENTER);
        
		JButton Yes = new JButton("Yes");
		Yes.addActionListener(this);
		Yes.setFocusable(false);
		Yes.setActionCommand("YES");
		
		JButton No = new JButton("No");
		No.addActionListener(this);
		No.setFocusable(false);
		No.setActionCommand("NO");
        
		JPanel ButtonPanel = new JPanel();
		ButtonPanel.add(Yes);
		ButtonPanel.add(No);
		
		validate();
		
		if(Mode == select){
			setSize(320,95);
			Show.setText("是否同意悔棋");
			add(ButtonPanel,BorderLayout.SOUTH);
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setModal(true);
			setVisible(true);
		}
		else if(Mode == wait){
			setSize(286,73);
			Show.setText("正在等待回应");
			setModal(true);
		}
		else if(Mode == select_yes){
			setSize(286,73);
			Show.setText("对方同意");
			setLocation(Return.getWaitDialog().getX(),Return.getWaitDialog().getY());
			setVisible(true);
			showATime();
		}
		else if(Mode == select_no){
			setSize(286,73);
			Show.setText("对方拒绝");
			setLocation(Return.getWaitDialog().getX(),Return.getWaitDialog().getY());
			setVisible(true);
			showATime();
		}
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand().compareTo("YES") == 0){
            dispose();
            Status = true;
        }
        else if(ae.getActionCommand().compareTo("NO") == 0){
            dispose();
            Status = false;
        }
    }
    
    public boolean getStatus(){
        return Status;
    }
	
	private void showATime(){
		try{
			Thread.sleep(600);
			dispose();
		}
		catch(InterruptedException e){}
	}
	
	public static Return getWaitDialog(){
		if(dialogwait == null)
			dialogwait = new Return(ChineseChess.runningApplication(),wait);
		return dialogwait;
	}
}