
import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {
	public static boolean run = true;
	public static void main(String[] args){
		
		ServerSocket server = null;
		int porta = 3333;
		public static ArrayList<PrintWriter> lista = new ArrayList<PrintWriter>();
		try {
			System.out.println("Iniciando servidor na porta " + porta);
			server = new ServerSocket(porta);

			while(run){
				Socket cliente = server.accept();
				System.out.println("Conexao estabelecida...");
				new TrataRequest(cliente).start();
			}
		
		} catch (IOException e) {/*donothing*/}
	}
}
