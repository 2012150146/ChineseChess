import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GetSocket{
	static GetSocket getsocket = null;
	ServerSocket server;
	Socket socket;
	
	private GetSocket(){
		server = null;
		socket = null;
	}
		
	private Socket _getSocket(String Message){
		if(Message.startsWith("L")){
			try{
				server = new ServerSocket(Integer.parseInt(Message.substring(Message.indexOf("-")+1)));
				server.setSoTimeout(10000);
				socket = server.accept();
			}
			catch(SocketTimeoutException e){
				_close();
				JOptionPane.showMessageDialog(ChineseChess.runningApplication(), "Connection Timeout", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
			catch(IOException e){
				socket = null;
				JOptionPane.showMessageDialog(ChineseChess.runningApplication(), "Address already in use", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else{
			String host = Message.substring(Message.indexOf("-")+1);
			int port = Integer.parseInt(Message.substring(1,Message.indexOf("-")));
			
			try{
				socket = new Socket(host,port);
			}
			catch(IOException e){
				socket = null;
				JOptionPane.showMessageDialog(ChineseChess.runningApplication(), "Server Disconnected", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		return socket;
	}
	
	private Socket _getSocket(){
		return socket;
	}
	
	public static Socket getSocket(String Message){
		if(getsocket == null)
			getsocket = new GetSocket();
		return getsocket._getSocket(Message);
	}
	
	public static Socket getSocket(){
		if(getsocket == null)
			getsocket = new GetSocket();
		return getsocket._getSocket();
	}
	
	private void _close(){
		if(server != null){
			try{
				server.close();
			}catch(IOException E){}

			server = null;
		}
		
		if(socket !=null){
			try{
				socket.close();
			}catch(IOException E){}

			socket = null;
		}
	}
	
	public static void close(){
		getsocket._close();
	}
}