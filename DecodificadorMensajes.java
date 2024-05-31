import java.util.ArrayList;
import java.util.List;

/**
 * Clase DecodificadorMensajes: representa una componente capaz de descifrar
 * un mensaje en formato texto, dado el mensaje y el c�digo usado para la 
 * encripci�n. El mensaje a descifrar/decodificar debe ser un objeto
 * de tipo Mensaje (b�sicamente, una lista de strings, donde cada string
 * representa una l�nea). Se asume que el mensaje es ASCII, es decir, todos
 * los caracteres del mensaje tienen c�digos en el rango [0, 127].
 * 
 * La codificaci�n/decodificaci�n utiliza una variante de Cifrado Cesar, en 
 * el cual el desplazamiento se basa en una c�digo de encripci�n m�ltiple. 
 * V�ase Cifrado de Vigen�re para m�s detalles.
 * 
 * @author N. Aguirre
 * @version 0.1
 */
public class DecodificadorMensajes
{
    /**
     * Mensaje codificado
     */
    private Mensaje mensajeADecodificar;
    /**
     * C�digo a utilizar
     */
    private int[] codigoEncripcion;

    /**
     * Mensaje decodificado
     */
    private Mensaje mensajeDecodificado;
    private String msg;
    /**
     * Constructor de la clase DecodificadorMensajes.
     * Inicializa el mensaje a desencriptar/decodificar con el par�metro pasado, 
     * junto con el c�digo de desencripci�n. 
     * Precondici�n: tanto el mensaje msg como el c�digo codigo no pueden ser nulos
     * @param msg es el mensaje a desencriptar.
     * @param codigo es el c�digo de desencripci�n.
     */
    public DecodificadorMensajes(Mensaje msg, int[] codigo)
    {
        if (msg == null)
            throw new IllegalArgumentException("Mensaje nulo");
        if (codigo == null)
            throw new IllegalArgumentException("C�digo inv�lido.");
        mensajeADecodificar = msg;
        codigoEncripcion = codigo;
        mensajeDecodificado = null;
    }

    /**
     * Desencripta el mensaje. El mensaje no debe estar desencriptado.
     * Precondici�n: El mensaje a�n no fue descifrado (i.e., el campo 
     * mensajeDecodificado es null).
     */
    public void decodificarMensaje() 
    {
        // TODO: Implementar este metodo
        if(mensajeDecodificado!=null){
            throw new IllegalStateException("El mensaje ya a sido descodificado"); //Excepcion que verifica que el mensaje ya fue codificado
        }
         mensajeDecodificado = new Mensaje();
        for (int i = 0; i < mensajeADecodificar.cantLineas(); i++) {
            String lineaCodificada = mensajeADecodificar.obtenerLinea(i);
            String lineaDecodificada = desencriptarCadena(lineaCodificada, codigoEncripcion);
            mensajeDecodificado.agregarLinea(lineaDecodificada);
        }
        System.out.println("");
        System.out.println("El mensaje decodificado es: " + mensajeDecodificado);
    }
    /**
     * Retorna el mensaje ya decodificado/descifrado.
     * Precondici�n: el mensaje debe haber sido decodificado previamente (i.e., 
     * se debe haber llamado a decodificarMensaje()).
     * Postcondicion: se retorna el mensaje descifrado/decodificado.
     * @return el mensaje descifrado.
     */
    public Mensaje obtenerMensajeDecodificado() {
        if (mensajeDecodificado == null)
            throw new IllegalStateException("Mensaje a�n no decodificado");
        return mensajeDecodificado;
    }

    /**
     * Desencripta una cadena, dado un c�digo numérico. Se usan los d�gitos del c�digo
     * para reemplazar cada caracter de la cadena por el caracter correspondiente a 
     * "trasladar" el mismo el n�mero de lugares que indica el c�digo, en sentido inverso
     * al de encripci�n (es decir, se resta el c�digo al caracter). El c�digo tiene
     * m�ltiples valores: se usa el primero para el primer caracter, el segundo para el 
     * segundo, y as� sucesivamente. Si se agota el c�digo, se vuelve al comienzo del mismo, 
     * hasta desencriptar toda la cadena.
     * Precondici�n: tanto str como codigo no deben ser nulos.
     * @param str es la cadena a desencriptar
     * @param codigo es el c�digo a utilizar para la desencripci�n
     */
    private String desencriptarCadena(String str, int[] codigo) {
        // TODO: Implementar este método, sustituyendo la linea
        if (str== null && codigo==null)
            throw new IllegalArgumentException ("No fue ingresado el dato");
        String result = "";
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            char curr = str.charAt(i);
            char currDesencriptado = (char) ((curr - codigo[j]) % 128);
            if(currDesencriptado < 0){
                currDesencriptado =+ 128;
            }
            result = result + currDesencriptado;
            j = (j + 1) % codigo.length;
        }
        return result;
    }

}
