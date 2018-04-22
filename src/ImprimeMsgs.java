import java.net.*;
import java.io.*;
import java.util.*;
//IMPRIME MENSAGEM NO CLIENTE
public class ImprimeMsgs extends Thread {

	private Socket socket;
	private Scanner entrada;
	ImprimeMsgs(Socket socket) throws IOException{
		this.socket = socket;
		this.entrada = new Scanner(this.socket.getInputStream());
	}
	
	public void run(){
		do {
			String linha = entrada.nextLine();
			if(linha!=null&&!linha.isEmpty()) 
				System.out.println(linha);
			
		}while(true);//ENQUANTO O CLIENTE ESTIVER CONECTADO
	}
}