package servidor;

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
		
		String linha = entrada.nextLine();
		
		System.out.println("Requisicao Recebida: " + linha);
		
		if (linha.contains("LISTAARQUIVOS")){
			this.listaArquivos(linha.replace("LISTAARQUIVOS ", ""));
		} else if (linha.contains("GETFREESPACE")){
			this.retornaEspacoLivre();
		} else {
			saida.println("Comando Invalido");
			saida.flush();
		}
		
		System.out.println("Encerrando conexao: " + linha);
		try {
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}

	private void listaArquivos(String strPath){
		
		File path = new File(strPath);
		if (!path.exists()){
			saida.println("Diretorio Inexistente");
			saida.flush();
			return;
		}
		
		if (!path.isDirectory()){
			saida.println("Caminho nao eh um diretorio");
			saida.flush();
			return;
		}
		
		String[] arquivos = path.list();
		if (arquivos.length == 0){
			saida.println("Diretorio nao possui arquivos");
			saida.flush();
			return;
		}
		
		for(String arquivo : arquivos){
			saida.println(arquivo);
		}
		saida.flush();
	}
	
	public void retornaEspacoLivre(){
		File arquivo = new File("/");
		saida.println(arquivo.getFreeSpace());
		saida.flush();
	}
	
}
