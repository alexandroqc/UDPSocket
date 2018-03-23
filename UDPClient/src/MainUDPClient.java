import java.io.IOException;
import java.net.UnknownHostException;

public class MainUDPClient {

	public static void main(String[] args) throws IOException {
		UDPClient C = new UDPClient("127.0.0.1",9999);
		C.iniciar();
	}

}
