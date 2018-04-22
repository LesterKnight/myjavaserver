import java.io.*;
import java.net.*;
import java.util.*;

public class Cliente {
	
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		String ipServer = "127.0.0.1";
		int portServer = 3333;
		System.out.println("Conectando no servidor...");
		Socket socket = null;
		String username;
		
		
		try {
			
			System.out.println("Digite seu nome: ");
			username = teclado.nextLine();
			socket = new Socket(ipServer, portServer);
			System.out.println("Conectado...");
			PrintWriter saida = new PrintWriter(socket.getOutputStream());
			new ImprimeMsgs(socket).start();//INICIALIZA A THREAD QUE FAZ OUTPUT DAS MENSAGENS NO CLIENTE ATUAL
			
			

			do {
				saida.println(username+";"+teclado.nextLine());
				saida.flush();
				}while (true);
			
			
		} catch (IOException e) { e.printStackTrace();	}
		
		
		
		//bloco inutil
		teclado.close();
		
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
}