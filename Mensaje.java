import java.util.ArrayList;

/**
 * Clase Mensaje: representa un mensaje en formato texto. 
 * El texto est� almacenado en una lista de strings, donde cada
 * elemento de la lista representa una l�nea (rengl�n) del mensaje.
 * Ninguna l�nea excede los 80 caracteres (81 considerando el fin de
 * l�nea impl�cito al final de cada l�nea). Los mensajes s�lo
 * contienen caracteres ASCII (es decir, el c�digo ASCII de cada 
 * caracter del mensaje es menor a 128).
 * 
 * @author N. Aguirre
 * @version 0.1
 */
public class Mensaje
{
    /**
     * Longitud m�xima de l�nea del mensaje
     */
    public static final int LONG_MAX_LINEA = 80;
    
    /**
     * l�neas del mensaje
     */ 
    private ArrayList<String> lineas;

    /**
     * Constructor por defecto de la clase Mensaje().
     * Crea un mensaje vac�o.
     */
    public Mensaje()
    {
        lineas = new ArrayList<String>();
    }

    /**
     * Retorna la cantidad de lineas del mensaje.
     * @return cantidad de lineas del mensaje.
     */
    public int cantLineas()
    {
        return lineas.size();
    }
    
    /**
     * Agrega una nueva l�nea al final del mensaje.
     * Precondici�n: la l�nea a agregar no debe ser null,
     * s�lo puede contener caracteres ascii, y 
     * su longitud debe ser menor o igual a 80.
     * Postcondici�n: la l�nea 'linea' se agrega al final 
     * de la lista de l�neas.
     * @param linea es la l�nea a agregar.
     */
    public void agregarLinea(String linea)
    {
        if (linea == null)
            throw new IllegalArgumentException("La linea a agregar no debe ser null.");
        if (linea.length() > LONG_MAX_LINEA)
            throw new IllegalArgumentException("Longitud invalida. La linea no debe tener m�s de 80 caracteres.");
        if (!esAscii(linea)) 
            throw new IllegalArgumentException("La linea a agregar contiene caracteres no ascii.");
        lineas.add(linea);
    }
    
    /**
     * Agrega una nueva l�nea al mensaje, en una posici�n espec�fica.
     * Precondici�n: la l�nea a agregar no debe ser null,
     * s�lo puede contener caracteres ascii, y 
     * su longitud debe ser menor o igual a 80.
     * La posici�n debe estar entre 0 y la cantidad de l�neas del mensaje
     * Postcondici�n: la l�nea 'linea' se agrega a la lista de l�neas, en la posici�n indicada.
     * @param linea es la l�nea a agregar.
     * @param pos es la posici�n en la cual se desea agregar la l�nea.
     */
    public void agregarLinea(int pos, String linea) //Metodo implementado
    {
        // TODO: Implementar este metodo
        if (linea == null)
            throw new IllegalArgumentException ("La linea no debe ser nula");
        if (linea.length() > LONG_MAX_LINEA) //Longitud de linea no debe ser mayor qude 80
            throw new IllegalArgumentException("Longitud invalida. La linea no debe tener mas de 80 caracteres.");
        if (!esAscii(linea)) //Linea debe ser de tipo Ascii
            throw new IllegalArgumentException("La linea a agregar contiene caracteres no ascii.");
        if (pos < 0 || pos > cantLineas()) // Si pos == cantLineas() se agrega al final
            throw new IllegalArgumentException("Posicion invalida. La posicion debe ser entre 0 y la cantidad de lineas.");
    
        lineas.add(pos, linea);
    }
    
    /**
     * Elimina la l�nea de una posici�n determinada del mensaje.
     * Precondici�n: pos debe estar entre cero y longitud (numero de l�neas) menos uno del mensaje.
     * @param pos es la posici�n (�ndice) de la l�nea a eliminar.
     */
    public void eliminarLinea(int pos)
    {
        if (pos < 0 || pos >= cantLineas())
            throw new IllegalArgumentException("Posici�n inv�lida. No existe l�nea con esa posici�n.");
        lineas.remove(pos);
    }
    
    /**
     * Retorna la l�nea de una posici�n determinada del mensaje.
     * Precondici�n: pos debe estar entre cero y longitud (numero de l�neas) menos uno del mensaje.
     * @param pos es la posici�n (�ndice) de la l�nea a retornar.
     * @return la l�nea en la posici�n (�ndice) indicada.
     */
    public String obtenerLinea(int pos)
    {
        if (pos < 0 || pos >= cantLineas())
            throw new IllegalArgumentException("Posici�n inv�lida. No existe l�nea con esa posici�n.");
        return lineas.get(pos);
    }
    
    
    /**
     * Comprueba si una cadena est� compuesta exclusivamente de caracteres ASCII.
     * Los caracteres ASCII tienen c�digo entre 0 y 127.
     * Precondici�n: la l�nea a chequear no debe ser null.
     * @param linea es la linea a chequear.
     * @return true si la cadena linea est� compuesta exclusivamente de caracteres ASCII.
     */
    public boolean esAscii(String linea)
    {
        if (linea == null) 
            throw new IllegalArgumentException("La linea a chequear no debe ser null.");
        boolean esAscii = true;
        for (int i = 0; i < linea.length() && esAscii; i++) {
            if (linea.charAt(i) > 127) {
                esAscii = false;
            }
        }
        return esAscii;
    }
    
    /**
     * Comprueba si un mensaje es igual a otro mensaje.
     * Un mensaje es igual a otro si tiene el mismo n�mero de l�neas, y cada
     * l�nea de cada mensaje coincide.
     * Precondici�n: otro debe ser distinto de null.
     * @param otro es el mensaje con el cual comparar el mensaje actual.
     * @return true si el mensaje es igual a otro (el par�metro).
     */
    public boolean equals(Mensaje otro) //Metodo implementado 
    {
        // TODO: Implementar este metodo sustituyendo la linea debajo, con el codigo de la implementacion
        if (otro==null)
            throw new IllegalArgumentException ("Ingrese bien el mensaje");
        int i = 0;
        while(i<lineas.size()){ //Mientras que i sea menor que el tama�o de la linea, comparamos los mensajes si son iguales o no
         if(!lineas.get(i).equals(otro.lineas.get(i)) || lineas.size() != otro.lineas.size() ){
                return false;
            }
                i++;
        }
        return true;
    }
    
    /**
     * Genera una representaci�n de cadena de caracteres del mensaje completo.
     * @return una cadena conteniendo el mensaje completo.
     */
    public String toString()
    {
        String result = "";
        for (String linea: lineas) {
                result = result + linea + "\n";
        }
            return result;
    }
    
    /**
     * Invariante de clase Mensaje. Chequea que la lista no sea nula, y todas sus l�neas
     * cumplan con las restricciones de longitud y contenido.
     * @return true si el mensaje no es nulo, ninguna de sus lineas es nula, y todas son 
     * ascii, de hasta 80 caracteres.
     */
    public boolean repOK() 
    {
        if (lineas == null) 
            return false;
        else {
            boolean ok = true;
            for (int i = 0; i < lineas.size() && ok; i++) {
                String corriente = lineas.get(i);
                if (corriente == null || !esAscii(corriente) || corriente.length() > LONG_MAX_LINEA) {
                     ok = false;
                }
            }
            return ok;
        }
    }
}
