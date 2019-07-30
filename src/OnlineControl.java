import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OnlineControl extends Thread{
	Socket socket = null;
	DataInputStream input = null;
	DataOutputStream output = null;
	char turn = ' ';
	boolean isFirst = false;
	
	static OnlineControl onlinecontrol=null;
	
	OnlineControl(){}
	
	public void set(Socket t,char m){
		socket = t;
		turn = m;
		
		try{
			output = new DataOutputStream(socket.getOutputStream());
			input = new DataInputStream(socket.getInputStream());
			isFirst = true;
		}
		catch(IOException e){}
	}
	
	public static OnlineControl get(){
		return onlinecontrol;
	}
	
	public static void newOnlineControl(){
		if(onlinecontrol ==null)
			onlinecontrol = new OnlineControl();
		else if(!onlinecontrol.isAlive()){
			onlinecontrol = new OnlineControl();
		}
		else{
			System.out.println("The Thread still Alive");
		}
	}
	
	public void run(){
		try{
			BoardView.defaultBoardView().getGame().setIsOnline(true);
			
			if(turn == ' '){
				if(input.readUTF().startsWith("R")){
					turn = 'r';
				}
				else{
					turn = 'b';
					BoardView.defaultBoardView().getGame().setFlag();
				}
			}
			else{
				if(turn == 'r'){
					output.writeUTF("B");
				}
				else{
					output.writeUTF("R");
					BoardView.defaultBoardView().getGame().setFlag();
				}
			}
			
			BoardView.defaultBoardView().setColor(turn);
			
			while(true){
				String str = input.readUTF();
				
				if(str.length()!=0){
					if(str.startsWith(" "))
						ChineseChess.runningApplication().setOutput(str.substring(1));
					else if(str.startsWith("MoveBack?")){
						Return dialog = new Return(ChineseChess.runningApplication(),Return.select);
						boolean flag = dialog.getStatus();
			
						if(flag){
							BoardView.defaultBoard.getGame().MoveBack();
							output.writeUTF("Yes_mb");
						}
						else{
							output.writeUTF("No");
						}
					}
					else if(str.startsWith("New?")){
						int n = JOptionPane.showConfirmDialog(ChineseChess.runningApplication(), "Do you want a new game?", "确定对话框", JOptionPane.INFORMATION_MESSAGE);
						
						if(n == JOptionPane.YES_OPTION){
							BoardView.defaultBoard.getGame().newGame();
							output.writeUTF("Yes_n");
						}
						else{
							output.writeUTF("No");
						}
					}
					else if(str.startsWith("Yes_mb")){
						Return.getWaitDialog().setVisible(false);
						new Return(ChineseChess.runningApplication(),Return.select_yes);
						BoardView.defaultBoard.getGame().MoveBack();
						BoardViewControl.defaultControl().setFlag(true);
					}
					else if(str.startsWith("Yes_n")){
						Return.getWaitDialog().setVisible(false);
						new Return(ChineseChess.runningApplication(),Return.select_yes);
						BoardView.defaultBoard.getGame().newGame();
						BoardViewControl.defaultControl().setFlag(true);
					}
					else if(str.startsWith("No")){
						Return.getWaitDialog().setVisible(false);
						new Return(ChineseChess.runningApplication(),Return.select_no);
						BoardViewControl.defaultControl().setFlag(true);
					}
					else{
						Game temp = BoardView.defaultBoardView().getGame();
						temp.setFlag();
						temp.move(str);
					}
				}
			}
		}
		catch(IOException e){
			close();
			BoardView.defaultBoardView().getGame().setIsOnline(false);
			BoardView.defaultBoardView().getGame()._wait();
			ChineseChess.runningApplication().stopNet();
			
			JOptionPane.showMessageDialog(ChineseChess.runningApplication(), "Disconnected", "Error", JOptionPane.INFORMATION_MESSAGE);
			
			return;
		}
	}
	
	public void InputSomething(String s){
		try{
			output.writeUTF(s);
		}
		catch(IOException e){}
	}
	
	public char getTurn(){
		return turn;
	}
	
	public void close(){
		GetSocket.close();
		
		
		if(socket != null){
			try{
				socket.close();
			}catch(IOException E){}
			
			socket = null;
		}
	}
}