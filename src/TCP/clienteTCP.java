package TCP;

import java.io.*;
import java.net.*;
class ClienteTCP{
	public static void main(String args[]){
		// Leemos el primer par�metro, donde debe ir la direcci�n
		// IP del servidor
		InetAddress direcc = null;
		try{
			direcc = InetAddress.getByName(args[0]);
			System.out.println("Direccion : "+ direcc);
		}catch(UnknownHostException uhe){
			System.err.println("Host no encontrado : " + uhe);
			System.exit(-1);
		}
		// Puerto que hemos usado para el servidor
		int puerto = 1234; 
		// Para cada uno de los argumentos...
		
		for (int n=1;n<args.length;n++){
			Socket sckt = null;
			DataInputStream dis = null;
			DataOutputStream dos = null;
			try{
				// Convertimos el texto en n�mero
				int numero = Integer.parseInt(args[n]);
				// Creamos el Socket
				sckt = new Socket(direcc,puerto);
				System.out.println(sckt);
				System.out.println("Socket cliente ; "+sckt);
				// Extraemos los streams de entrada y salida
				dis = new DataInputStream(sckt.getInputStream());
				System.out.println("Dis : " +dis);
				dos = new DataOutputStream(sckt.getOutputStream());
				System.out.println("Dos : " +dos);
				// Lo escribimos
				dos.writeInt(numero);
				// Leemos el resultado final
				long resultado = dis.readLong();
				// Indicamos en pantalla
				System.out.println( "Solicitud = " + numero +" a\tResultado = " +resultado );
				// y cerramos los streams y el socket
				dis.close();
				dos.close();
			}
			catch(Exception e){
				System.err.println("Se ha producido la excepci�n : " +e);
			}
			try{
				if (sckt!=null) sckt.close();
			}
			catch(IOException ioe){
				System.err.println("Error al cerrar el socket :" + ioe);
			}
		}
	}
} 
