import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPServer {
	
	ServerSocket sServidor;
	Socket sCliente;
	int puerto;
	PrintStream salida;
	Scanner entrada;
	String mensajeSolicitud = "";
	String mensajeRespuesta = "";
	
	public TCPServer(int p){
		puerto = p;
	}
	
	public void iniciar(){
		
		try {
			// Se inicia el servidor
			sServidor = new ServerSocket(puerto);
			System.out.println("- Esperando Cliente -");
			
			// Se esperan las peticiones del cliente
			while(true){
				// El servidor es aceptado
				sCliente = sServidor.accept();
				entrada = new Scanner(sCliente.getInputStream());
				salida = new PrintStream(sCliente.getOutputStream());
				mensajeSolicitud = entrada.nextLine();
				
				System.out.println("Solicitud del Cliente :"+ mensajeSolicitud );
				mensajeRespuesta = "Bienvenido "+ mensajeSolicitud;
				salida.println(mensajeRespuesta);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			finalizar();
		}
		finally{
			finalizar();
		}
	}
	public void finalizar(){
		try {
			// Finalizando los flujos y el socket
			salida.close();
			entrada.close();
			sServidor.close();
			sCliente.close();
			System.out.println("Conexion Finalizada...");
		} catch (Exception e) {
			// Imprimir error
			e.printStackTrace();
		}
	}
}
