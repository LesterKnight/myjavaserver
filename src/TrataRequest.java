import java.net.*;
import java.io.*;
import java.util.*;

public class TrataRequest extends Thread {
	private Socket socket;
	private Scanner entrada;

	
	TrataRequest(Socket socket) throws IOException{
		this.socket = socket;
		this.entrada = new Scanner(this.socket.getInputStream());
		Servidor.listaClientes.add(this.socket.getOutputStream());
		//GET OLD MESSAGES
		
		ArrayList msgAnteriores = Servidor.getOldMessages();
		int msgCount = msgAnteriores.size();
		
		if(msgCount>20) {
			int index = msgCount-20;
			
			PrintWriter saida = new PrintWriter(socket.getOutputStream());
			
			for(int i=index;i<msgCount;i++) {
				saida.println(msgAnteriores.get(i));
				saida.flush();
			}
		}else{
			
			PrintWriter saida = new PrintWriter(socket.getOutputStream());
			
			for(int i=0;i<msgCount;i++) {
				saida.println(msgAnteriores.get(i));
				saida.flush();
			}
		}
		
		
		
		
		
		
	}
	
	public void run(){
		do {
			String linha = entrada.nextLine();
			if(linha!=null&&!linha.isEmpty()) {
				
				String[] fields = linha.split(";");
				
				String user = fields[0];
				String msg = fields[1];
				
				System.out.println("Servidor recebeu uma mensagem de : "+user);
				Servidor.saidaArquivo.println(user+"-->"+msg);
				Servidor.saidaArquivo.flush();
				
				for(OutputStream c : Servidor.listaClientes) {
					PrintWriter saida = new PrintWriter(c);//printstream x printwriter dados e texto
					saida.println(user+"-->"+msg);
					saida.flush();
				}
				
			}
		}while(true);
	}
}
