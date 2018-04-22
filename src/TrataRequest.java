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
			
			String linha = entrada.nextLine();
			if(linha!=null&&!linha.isEmpty()) {
				String[] fields = linha.split(";");
				String user = fields[0];
				String msg = fields[1];
				System.out.println("Servidor recebeu uma mensagem de : "+user);
				
				//efetua broadcast da mensagem para todos os clientes
				Servidor.listaClientes.forEach(saidaDaLista->{
					saidaDaLista.saida.println(user+" "+msg);
				});
				
			}
		}while(true);
	}
}