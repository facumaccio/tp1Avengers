

/**
 * Clase CodificadorMensajes: representa una componente capaz de cifrar
 * un mensaje en formato texto. El mensaje a cifrar debe ser un objeto
 * de tipo Mensaje (b�sicamente, una lista de strings, donde cada string
 * representa una l�nea). Se asume que el mensaje es ASCII, es decir, todos
 * los caracteres del mensaje tienen c�digos en el rango [0, 127].
 * Si el mensaje a codificar tiene al menos una l�nea, el
 * mecanismo de codificaci�n/cifrado calcula un c�digo de cifrado a partir
 * de la misma. Caso contrario, el c�digo de cifrado es irrelevante.
 * 
 * La codificaci�n utiliza una variante de Cifrado Cesar, en el cual el 
 * desplazamiento se basa en una c�digo de encripci�n m�ltiple. V�ase
 * Cifrado de Vigen�re para m�s detalles.
 * 
 * @author N. Aguirre
 * @version 0.1
 */
public class CodificadorMensajes
{
    /**
     * Mensaje a codificar
     */
    private Mensaje mensajeACodificar;
    /**
     * Mensaje codificado
     */
    private Mensaje mensajeCodificado;

    /**
     * Codigo para encripcion
     */
    private int[] codigoEncripcion;

    /**
     * Constructor de la clase CodificadorMensajes.
     * Inicializa el mensaje a encriptar/codificar con el par�metro pasado, e
     * inicializa el mensaje codificado y el codigo de encripci�n en null.
     * Precondici�n: el mensaje msg no puede ser nulo
     * @param msg es el mensaje a encriptar.
     */
    public CodificadorMensajes(Mensaje msg)
    {
        if (msg == null) {
            throw new IllegalArgumentException("Mensaje es nulo");
        }
        mensajeACodificar = msg;
        mensajeCodificado = null;
        codigoEncripcion = null;
    }

     /**
     * Encripta el mensaje. El mensaje no debe estar encriptado.
     * Precondici�n: El mensaje a�n no fue cifrado (i.e., el campo mensajeCodificado es null).
     */
    public void codificarMensaje() 
    {
        if (mensajeCodificado != null) { 
            throw new IllegalStateException("El mensaje ya está codificado"); 
        }
        if (mensajeACodificar.cantLineas() == 0) { 
            mensajeCodificado = new Mensaje(); 
            codigoEncripcion = new int[0]; 
        }
        else {
            mensajeCodificado = new Mensaje();
            codigoEncripcion = generarCodigoEncripcion(mensajeACodificar.obtenerLinea(0)); 
            for (int i = 0; i < mensajeACodificar.cantLineas(); i++) {
                String curr = mensajeACodificar.obtenerLinea(i);
                String currCodificada = encriptarCadena(curr, codigoEncripcion);
                mensajeCodificado.agregarLinea(currCodificada);
            }
        }
    }

     /**
     * Cambia el mensaje a codificar.
     * Precondici�n: el nuevo mensaje no puede ser null.
     * Postcondici�n: el mensaje a codificar se actualiza, y se vuelve el objeto
     * a un estado de "a�n no codificado".
     * @param msg es el mensaje a codificar.
     */
    public void cambiarMensaje(Mensaje msg)
    {
        if (msg == null)
            throw new IllegalArgumentException("Mensaje es nulo");
        mensajeACodificar = msg;
        mensajeCodificado = null;
        codigoEncripcion = null;
    }

     /**
     * Retorna el mensaje ya codificado/cifrado.
     * Precondici�n: el mensaje debe haber sido codificado previamente (i.e., se debe haber llamado a codificarMensaje()).
     * Postcondicion: se retorna el mensaje cifrado/codificado.
     * @return el mensaje cifrado.
     */
    public Mensaje obtenerMensajeCodificado() {
        if (mensajeCodificado == null)
            throw new IllegalStateException("Mensaje aún no codificado");
        return mensajeCodificado;
    }

    /**
     * Retorna el c�digo de cifrado.
     * Precondici�n: el mensaje debe haber sido codificado previamente (i.e., se debe haber llamado a codificarMensaje()).
     * Postcondicion: se retorna el c�digo obtenido para el cifrado.
     * @return el c�digo de cifrado.
     */
    public int[] obtenerCodigoEncripcion() {
        if (mensajeCodificado == null)
            throw new IllegalStateException("Mensaje aún no codificado");
        return codigoEncripcion;
    }

    /**
     * Computa el c�digo de encripci�n correspondiente a una cadena str.
     * Para calcular el c�digo de encripci�n se suman los c�digos ASCII de str, 
     * y se divide por 99991 (el n�mero primo de 5 d�gitos m�s grande). Los 
     * d�gitos del resto de la divisi�n constituyen el c�digo de encripci�n.
     * Ej: para la cadena "hola", los c�digos ascii de los caracteres son
     * 104, 111, 108 y 97, respectivamente. Su suma es 420, y 420 % 99991 es
     * 420. Luego, el c�digo de inscripci�n es el arreglo {4, 2, 0}.
     */
    private int[] generarCodigoEncripcion(String str) 
    {
    //Inicializamos nuestras variables locales
    int suma = 0;
    int resto = 0;
    int i = 0;
    int j;
    String resultadoFinal;
    //Este bucle itera de 1 en 1, y suma los valores del codigo Ascii de cada caracter de la cadena
    while (i < str.length()) {
        suma = str.charAt(i) + suma;
        i++;
    }
    resto = suma % 99991;
    resultadoFinal= resto + "";
    //Se crea un arreglo para guardar el codigo de encripcion del tama�o de la cadena
    int codigoEncripcion[] = new int[resultadoFinal.length()];
    System.out.println("");
    System.out.println("Codigo de Encripcion:");
    // Llena el arreglo codigoEncripcion con los d�gitos del resto
    for (i = resultadoFinal.length(); i > 0; i--) {
        codigoEncripcion[i-1] = resto % 10;
        resto = resto / 10;
    }
    //Imprime el codigo de encripcion
    for (j = 0; j < codigoEncripcion.length; j++) {
        System.out.print(codigoEncripcion[j] + " ");
    }
    //Retorna el codigo de encripcion
    return codigoEncripcion;
    }

     /**
     * Encripta una cadena, dado un c�digo num�rico. Se usan los d�gitos del c�digo
     * para reemplazar cada caracter de la cadena por el caracter correspondiente a 
     * "trasladar" el mismo el n�mero de lugares que indica el c�digo. El c�digo tiene
     * m�ltiples valores: se usa el primero para el primer caracter, el segundo para el segundo,
     * y as� sucesivamente. Si se agota el c�digo, se vuelve al comienzo del mismo, hasta
     * encriptar toda la cadena.
     * Precondici�n: tanto str como codigo no deben ser nulos.
     * @param str es la cadena a encriptar
     * @param codigo es el c�digo a utilizar para la encripci�n
     */
    private String encriptarCadena(String str, int[] codigo) {
        if (str == null) // Verifica si la cadena es nula y lanza una excepci�n si lo es
            throw new IllegalArgumentException("Cadena nula");
        if (codigo == null)  // Verifica si el c�digo es nulo y lanza una excepci�n si lo es
            throw new IllegalArgumentException("Codigo Invalido");
        String result = "";
        int j = 0;
        //Bucle para recorrer cada caracter de str y encripta los digitos
        for (int i = 0; i < str.length(); i++) {
            char curr = str.charAt(i);
            char currEncriptado = (char) ((curr + codigo[j]) % 128);
            result = result + currEncriptado;
            j = (j + 1) % (codigo.length);
        }
        return result;
    }

}