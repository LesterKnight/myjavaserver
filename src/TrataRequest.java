import java.net.*;
import java.io.*;
import java.util.*;

//TRATA MENSAGENS ENVIADAS DOS CLIENTES E REENVIA
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
		
			do {
				/*
				String linha = entrada.nextLine();
				if(linha!=null&&!linha.isEmpty()) {
					String[] fields = linha.split(";");
					String user = fields[0];
					String msg = fields[1];
					System.out.println("Servidor recebeu a mensagem: "+user+" "+msg);
				}
				*/
				
				
				
				System.out.println("Servidor realizando broadcast");
				
				Servidor.listaClientes.forEach(saidaDaLista->{
					PrintWriter saidaExtraidaDaLista = null;
					try {
						saidaExtraidaDaLista = new PrintWriter(saidaDaLista.getOutputStream());
						saidaExtraidaDaLista.println("mensagem enviada a todos os clientes");
					} catch (IOException e) {}
				});
				
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {}
				
				
			}while(true);//connected client list
	}
}