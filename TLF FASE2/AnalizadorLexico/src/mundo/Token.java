/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad del Quind�o (Armenia - Colombia)
 * Programa de Ingenier�a de Sistemas y Computaci�n
 *
 * Asignatura: Teor�a de Lenguajes Formales
 * Ejercicio: AnalizadorLexico
 * Autor: Sant
 * Autor:
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
/**
 * Clase que se contiene los tokens posibles del c�digo ingresado
 * 
 * @author Santiago Monta�o Lince
 * @author Sebastian Romero
 * @author Jesica Tapasco Velez
 * 
 */
package mundo;

/**
 * Clase que modela un token
 */

public class Token {
	/**
	 * Constantes para modelar los posibles tipos de token que se van a analizar
	 */
	final public static String ENTERO = "Entero";
	final public static String OPERADOR_ADITIVO = "Operador aditivo";
	final public static String OPERADOR_ASIGNACION = "Operador de asignaci�n";
	final public static String IDENTIFICADOR = "Identificador";
	final public static String NO_RECONOCIDO = "No reconocido";
	final public static String VISIBILIDAD = "Operador de visibilidad";
	final public static String OPERADOR_ARIMETICO = "Operador arimetico ";
	final public static String OPERADOR_COMPARACION = "Operador de comparacion";
	final public static String PALABRA_RESERVADA = "Palabra reservada";
	final public static String ETIQUETA_APERTURA = "Etiqueta apertura";
	final public static String ESPACIO = "Espacio";
	final public static String ETIQUETA_FINALIZACION = "Etiqueta finalizacion";
	final public static String OPERADOR_TERMINAL = "Operador terminal";
	final public static String OPERADOR_LOGICO = "Operador l�gico";
	public static final String BINARIO = "Numero binario";
	public static final String OCTAL = "Numero octal";
	public static final String CADENA_CARACTERES = "Cadena de caracteres";
	public static final String IDENTIFICADOR_VARIABLE = "Identificador de variable";
	public static final String HEXADECIMAL = "Numero hexadecimal";
	public static final String DECIMAL = "Numero decimal";
	public static final String FLOTANTE = "Numero flotante";
	public static final String APERTURA_BLOQUE = "Apertura de bloque";
	public static final String CIERRE_BLOQUE = "Cierre de bloque";
	public static final String AGRUPACION_ABRIR = "Agrupacion de apertura";
	public static final String AGRUPACION_CERRAR = "Agrupacion de cierre";
	public static final String CARACTER_ESPECIAL = "Caracter especial";
	public static final String IDENTIFICADOR_METODO = "Identificador de metodo";
	public static final String IDENTIFICADOR_CLASE = "Identificador de clase";
	public static final String SEPARADOR = "Separador";
	public static final String ASIGNACION_IGUAL = "Operador de asignaci�n igual";

	/**
	 * Lexema
	 */
	private String lexema;

	/**
	 * tipo
	 */
	private String tipo;

	/**
	 * posici�n del siguiente lexema
	 */
	private int indiceSiguiente;
	/**
	 * Constructor de un token
	 * 
	 * @param elLexema
	 *            - cadena - laCadena != null
	 * @param elTipo
	 *            - tipo del token - elTipo != null
	 * @param elIndiceSiguiente
	 *            - posici�n del siguiente token - laPosicionSiguiente > 0
	 */
	public Token(String elLexema, String elTipo, int elIndiceSiguiente) {
		lexema = elLexema;
		tipo = elTipo;
		indiceSiguiente = elIndiceSiguiente;
	}

	/**
	 * Entrega la informaci�n del token
	 * 
	 * @return Descripci�n del token
	 */
	public String darDescripcion() {
		return "Token: " + lexema + "     Tipo: " + tipo
				+ "     �ndice del siguiente: " + indiceSiguiente;
	}

	/**
	 * M�todo que retorna el lexema del token
	 * 
	 * @return el lexema del token
	 */
	public String darLexema() {
		return lexema;
	}

	/**
	 * M�todo que retorna la posici�n del siguiente lexema
	 * 
	 * @return posici�n del siguiente token
	 */
	public int darIndiceSiguiente() {
		return indiceSiguiente;
	}

	/**
	 * M�todo que retorna el tipo del token
	 * 
	 * @return el tipo del token
	 */
	public String darTipo() {
		return tipo;
	}

}
