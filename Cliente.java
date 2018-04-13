
import java.io.*;
import java.net.*;
import java.util.*;

public class Cliente {
	
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		String ipServer = "www.ourkinghostdatabase.kinghost.net";
		int portServer = 3333;
		System.out.println("Conectando no servidor...");
		Socket socket = null;
		String username;
		
		
		try {
			do {
				System.out.println("Digite seu nome: ");
				username = teclado.nextLine();
				socket = new Socket(ipServer, portServer);
				System.out.println("Conectado...");
				Scanner entrada = new Scanner(socket.getInputStream());
				PrintWriter saida = new PrintWriter(socket.getOutputStream());
				saida.flush();
			while (entrada.hasNextLine()) {
						System.out.println(entrada.nextLine());
				}

			   
				}while (true);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				socket.close();
			} catch (IOException e) {
				System.out.println("Erro ao encerrar conexao");
			}
		    System.out.println("Conexao encerrada");
			
		}
	}
}