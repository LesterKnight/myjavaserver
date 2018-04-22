import java.net.*;
import java.io.*;
import java.util.*;


public class TrataRequest extends Thread {

	private Socket socket;
	private Scanner entrada;
	private PrintWriter saida;
	
	TrataRequest(Socket socket) throws IOException{
		this.socket = socket;
		this.entrada = new Scanner(this.socket.getInputStream());
		this.saida = new PrintWriter(this.socket.getOutputStream());
		Servidor.lista.add(this.saida);
		
	}
	
	public void run(){
		
			do {
				String linha = entrada.nextLine();
				if(linha!=null&&!linha.isEmpty()) {
					String[] fields = linha.split(";");
					String user = fields[0];
					String msg = fields[1];
					System.out.println("mensagem deve ser exibida aqui");

					Servidor.lista.forEach(saidaDaLista->{
						saida.println("uma mensagem foi recebida pelo servidor");
						saida.flush();	
					});
				}
			}while(Servidor.run);//connected client list
		
	}
}