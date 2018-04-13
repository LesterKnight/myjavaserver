
import java.io.*;
import java.net.*;

public class Servidor {
	public static boolean run = true;
	public static void main(String[] args){
		
		ServerSocket server = null;
		int porta = 3333;
		try {
			System.out.println("Iniciando servidor na porta " + porta);
			server = new ServerSocket(porta);

			while(run){
				Socket cliente = server.accept();
				
				System.out.println("Conexao estabelecida...");
				System.out.println("Iniciando thread para tratar requisicao...");
				TrataRequest request = new TrataRequest(cliente);
				request.start();
			}
		
		} catch (IOException e) {/*donothing*/}
	}
}
