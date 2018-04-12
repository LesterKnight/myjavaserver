package cliente;

import java.io.*;
import java.net.*;
import java.util.*;

public class Cliente {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		System.out.print("Informe o endereco IP do servidor: ");
		String ipServer = teclado.nextLine();
		System.out.print("Informe a porta para conexao: ");
		int portServer = teclado.nextInt();

		System.out.println("Conectando no servidor...");
		Socket socket;
		try {
			int opcao;
			do {
				System.out.println("Escolha uma opcao: ");
				System.out.println("1. Listar conteudo diretorio");
				System.out.println("2. Mostrar espaco livre");
				System.out.println("3. Sair");
				opcao = teclado.nextInt();

				if ((opcao == 1) || (opcao == 2)) {
					socket = new Socket(ipServer, portServer);
					System.out.println("Conectado...");

					Scanner entrada = new Scanner(socket.getInputStream());
					PrintWriter saida = new PrintWriter(socket.getOutputStream());

					if (opcao == 1) {
						System.out.print("Informe o caminho: ");
						teclado.nextLine();
						String path = teclado.nextLine();
						saida.println("LISTAARQUIVOS " + path);
					} else {
						saida.println("GETFREESPACE");
					}
					saida.flush();
					while (entrada.hasNextLine()) {
						System.out.println(entrada.nextLine());
					}

					socket.close();
					System.out.println("Conexao encerrada");
				}
			} while (opcao != 3);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
