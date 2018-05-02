
import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {
	public static boolean run = true;
	public static ArrayList<OutputStream> listaClientes = new ArrayList<>();
	public static FileWriter arquivo;
	public static File file;
	public static PrintWriter saidaArquivo;
	
	public static ArrayList<String> getOldMessages() {
		ArrayList mensagens = new ArrayList();
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				mensagens.add(scanner.nextLine());
			}
			
		
			
		} catch (FileNotFoundException e) {
			// nenhuma mensagem anterior sera enviada ao cliente
		}
		catch (IOException e) {
			System.out.println("Problema ao conectar com cliente");
		}

		scanner.close();
		
		return mensagens;
	}
	
	
	
	public static void main(String[] args){
		
		ServerSocket server = null;
		int porta = 8976;

		try {
			System.out.println("Iniciando servidor na porta " + porta);
			server = new ServerSocket(porta);
			
			file = new File("chatlog.txt");
			if(!file.exists())
			    file.createNewFile();
			
			try {
				saidaArquivo = new PrintWriter(file);
			}catch(FileNotFoundException e) {
				System.out.println("Problema ao gerar arquivo");
			}
			
			
			while(run){
				Socket cliente = server.accept();
				System.out.println("Conexao estabelecida...");
				new TrataRequest(cliente).start();
			}
		
		} catch (IOException e) {/*donothing*/}
	}
}
