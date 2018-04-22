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
		
		
	}
	
	public void run(){
		
			do {
				String linha = entrada.nextLine();
				if(linha!=null&&!linha.isEmpty()) {
					String[] fields = linha.split(";");
					String user = fields[0];
					String msg = fields[1];
					System.out.println("mensagem deve ser exibida aqui");

					Servidor.listaClientes.forEach(saidaDaLista->{
						PrintWriter saidaExtraidaDaLista = null;
						try {
							saidaExtraidaDaLista = new PrintWriter(saidaDaLista.getOutputStream());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						saidaExtraidaDaLista.println("mensagem enviada a todos os clientes");
					});
				}
			}while(Servidor.run);//connected client list
		
	}
}