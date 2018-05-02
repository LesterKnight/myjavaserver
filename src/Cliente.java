import java.io.*;
import java.net.*;
import java.util.*;

public class Cliente {
	
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		String ipServer;
		int portServer;
		System.out.println("Conectando no servidor...");
		Socket socket = null;
		String username;
		PrintWriter saida = null;
		
		try {
			System.out.println("Digite o IP desejado: ");
			ipServer = teclado.nextLine();

			System.out.println("Digite a Porta: ");
			portServer = teclado.nextInt();
			teclado.nextLine();
			System.out.println("Digite seu nome: ");
			username = teclado.nextLine();
			
			socket = new Socket(ipServer, portServer);
			System.out.println("Conectado em "+ipServer+":"+portServer);
			
			saida = new PrintWriter(socket.getOutputStream());
			new ImprimeMsgs(socket).start();
	
			do {
				String mensagem = teclado.nextLine();
				if(!mensagem.isEmpty()) {
					saida.println(username+";"+mensagem);
					saida.flush();
				}

			}while (true);

		}catch(UnknownHostException e) {
			System.out.println("Host de destino não encontrado");
		}
		catch(IOException e) {
		System.out.println("Host de destino não acessivel");
		}
	}
}