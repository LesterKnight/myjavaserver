
import java.net.*;
import java.io.*;
import java.util.*;

import sun.applet.Main;

public class TrataRequest extends Thread {

	private Socket socket;
	private Scanner entrada;
	private PrintWriter saida;
	
	TrataRequest(Socket socket) throws IOException{
		this.socket = socket;
		this.entrada = new Scanner(this.socket.getInputStream());
		this.saida = new PrintWriter(this.socket.getOutputStream());
	}
	
	public void run(){
		
		String linha = entrada.nextLine();
		if(linha!=null&&!linha.isEmpty()) {

			String[] fields = linha.split(";");
			String user = fields[0];
			String msg = fields[1];
			System.out.println("mensagem recebida de: "+user);
			
			saida.println("@"+user+": "+msg);
			saida.flush();
			if(linha.contains("meteope"))
				try {
					this.socket.close();
					Servidor.run = false;
				} catch (IOException e) {
					e.printStackTrace();
				}
		}	
	}			
}

	

