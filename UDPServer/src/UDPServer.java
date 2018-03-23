import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;


public class UDPServer {
	
	DatagramSocket socketUDP;
	DatagramPacket paqueteRecibido;
	DatagramPacket paqueteAEnviar;
	int puerto;
	
	public UDPServer(int p) {
		puerto = p;
	}
	
	public void iniciar(){
		try {
			socketUDP = new DatagramSocket(puerto);
			System.out.println(" - SERVIDOR UDP INICIADO -");
			System.out.println("- Esperando Cliente -");
			while(true){
				// Creando buffer
				paqueteRecibido = new DatagramPacket(new byte[1024],1024);
				
				// Recepción del paquete
				socketUDP.receive(paqueteRecibido);
				System.out.println("Llego una peticion...");
				
				//Recibiendo vector e bytes y convirtiendo a cadena
				String mensajeRecibido = new String(paqueteRecibido.getData());
				System.out.println("Solicitud Recibida: "+ mensajeRecibido);
				
				// Envío del paquete
				String mensaje = "Bienvenido "+mensajeRecibido;
				byte mensajeEnviar[] = new byte[1024];
				
				//Convierte cadena a Vector de Bytes
				mensajeEnviar = mensaje.getBytes();
				
				//Se crea el datagrama que contendra el mensaje
				paqueteAEnviar = new DatagramPacket(mensajeEnviar,mensajeEnviar.length,paqueteRecibido.getAddress(),paqueteRecibido.getPort());
				
				//Luego se Envia el paquete al cliente
				socketUDP.send(paqueteAEnviar);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void finalizar(){
		try{
			socketUDP.close();
			System.out.println("Conexion finalizada");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	
}
