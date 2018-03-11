/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad del Quind�o (Armenia - Colombia)
 * Programa de Ingenier�a de Sistemas y Computaci�n
 *
 * Asignatura: Teor�a de Lenguajes Formales
 * Ejercicio: AnalizadorLexico
 * Autor: Leonardo A. Hern�ndez R. - Agosto 2008 - Marzo 2009
 * Autor:
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package mundo;

import java.util.ArrayList;

/**
 * Clase que modela un analizador l�xico
 */

public class AnalizadorLexico {

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Extrae los tokens de un c�digo fuente dado
	 * 
	 * @param cod
	 *            - c�digo al cual se le van a extraer los tokens - !=null
	 * @return vector con los tokens
	 */
	public ArrayList extraerTokens(String codigo) {
		Token token;
		ArrayList vectorTokens = new ArrayList();

		// El primer token se extrae a partir de posici�n cero
		int i = 0;

		// Ciclo para extraer todos los tokens
		while (i < codigo.length()) {
			// Extrae el token de la posici�n i
			token = extraerSiguienteToken(codigo, i);
			if (!token.darTipo().equals("Espacio")) {
				vectorTokens.add(token);
			}
			i = token.darIndiceSiguiente();
		}
		return vectorTokens;
	}

	/**
	 * Extrae el token de la cadena cod a partir de la posici�n i, bas�ndose en
	 * el Aut�mata
	 * 
	 * @param cod
	 *            - c�digo al cual se le va a extraer un token - codigo!=null
	 * @param i
	 *            - posici�n a partir de la cual se va a extraer el token - i>=0
	 * @return token que se extrajo de la cadena
	 */
	public Token extraerSiguienteToken(String codigo, int i) {
		Token token;

		token = extraerFlotante(codigo, i);
		if (token != null)
			return token;

		token = extraerEtiquetaApertura(codigo, i);
		if (token != null)
			return token;

		token = extraerEtiquetaFinal(codigo, i);
		if (token != null)
			return token;

		token = extraerAsignacionIncremental(codigo, i);
		if (token != null)
			return token;

		token = extraerInicioClase(codigo, i);
		if (token != null)
			return token;

		token = extraerDeclaracionMetodo(codigo, i);
		if (token != null)
			return token;

		token = extraerBinario(codigo, i);
		if (token != null)
			return token;

		// // Intenta extraer un entero
		// token = extraerEntero(cod, i);
		// if (token != null)
		// return token;
		token = extraerFlecha(codigo, i);
		if (token != null)
			return token;

		token = extraerOperadorComparacion(codigo, i);
		if (token != null)
			return token;
		// Intenta extraer un operador aditivo
		// token = extraerOperadorAditivo(cod, i);
		// if (token != null)
		// return token;

		// Intenta extraer un operador de asignaci�n
		token = extraerOperadorArimetico(codigo, i);
		if (token != null)
			return token;

		token = extraerOperadorLogico(codigo, i);
		if (token != null)
			return token;

		token = extraerOperadorAsignacion(codigo, i);
		if (token != null)
			return token;

		// Intenta extraer un identificador
		token = extraerIdentificador(codigo, i);
		if (token != null)
			return token;

		token = extraerVisibilidad(codigo, i);
		if (token != null)
			return token;

		token = extraerEspacio(codigo, i);
		if (token != null)
			return token;

		token = extraerOperadorTerminal(codigo, i);
		if (token != null)
			return token;

		token = extraerRetorno(codigo, i);
		if (token != null)
			return token;

		token = extraerPalabraStatic(codigo, i);
		if (token != null)
			return token;

		token = extraerPalabraNew(codigo, i);
		if (token != null)
			return token;

		token = extraerPalabraFor(codigo, i);
		if (token != null)
			return token;

		token = extraerPalabraFinal(codigo, i);
		if (token != null)
			return token;

		token = extraerPalabraWhile(codigo, i);
		if (token != null)
			return token;

		token = extraerDecimal(codigo, i);
		if (token != null)
			return token;

		token = extraerHexadecimal(codigo, i);
		if (token != null)
			return token;

		token = extraerOctal(codigo, i);
		if (token != null)
			return token;

		token = extraerSentenciaIfElse(codigo, i);
		if (token != null)
			return token;

		token = extraerCadenaDeCaracteres(codigo, i);
		if (token != null)
			return token;

		token = extraerCadenaDeCaracteresComillasSimples(codigo, i);
		if (token != null)
			return token;

		token = extraerAperturaDeBloque(codigo, i);
		if (token != null)
			return token;

		token = extraerCierreDeBloque(codigo, i);
		if (token != null)
			return token;

		token = extraerSimboloDeAgrupacionAbrir(codigo, i);
		if (token != null)
			return token;

		token = extraerSimboloDeAgrupacionAbrir(codigo, i);
		if (token != null)
			return token;

		token = extraerSimboloDeAgrupacionCerrar(codigo, i);
		if (token != null)
			return token;

		token = extraerSimboloDeInterrogacion(codigo, i);
		if (token != null)
			return token;

		token = extraerFlecha(codigo, i);
		if (token != null)
			return token;

		token = extraerBackSlash(codigo, i);
		if (token != null)
			return token;

		token = extraerPalabraDo(codigo, i);
		if (token != null)
			return token;

		token = extraerPalabraReservadaEcho(codigo, i);
		if (token != null)
			return token;

		token = extraerIdentificadorMetodo(codigo, i);
		if (token != null)
			return token;

		token = extraerIdentificadorClase(codigo, i);
		if (token != null)
			return token;
		
		token = extraerSeparador(codigo, i);
		if (token != null)
			return token;

		// Extrae un token no reconocido
		token = extraerNoReconocido(codigo, i);
		return token;

	}

	/**
	 * M�todo que se encarga de extraer los n�meros flotantes de el c�digo
	 * 
	 * @param codigo
	 *            C�digo del cual ee extraeran los numeros
	 * @param i
	 *            �ndice en el cual se empezara a analizar el c�digo
	 * @return Token con la informaci�n de la palabra, y el �ndice en el cual
	 *         finaliz� la extraccion
	 */
	public Token extraerFlotante(String codigo, int i) {
		int j;
		if (codigo.charAt(i) == '.') {
			j = i + 1;
			if (j < codigo.length() && esDigito(codigo.charAt(j))) {

				while (j < codigo.length() && esDigito(codigo.charAt(j))) {
					j++;
				}
				String lexema = codigo.substring(i, j);
				Token token = new Token(lexema, Token.FLOTANTE, j);
				return token;
			}
			return null;
		}
		if (esDigito(codigo.charAt(i))) {
			j = i + 1;
			while (j < codigo.length() && esDigito(codigo.charAt(j))) {
				j++;
			}
			if (j < codigo.length()
					&& (codigo.charAt(j) == '.' || codigo.charAt(j) == 'e' || codigo
							.charAt(j) == 'E')) {
				j++;
				if (j < codigo.length()
						&& (codigo.charAt(j) == '-' || codigo.charAt(j) == '+')) {
					j++;
					if (j < codigo.length() && esDigito(codigo.charAt(j))) {
						// Si se quiere para mas de un solo digito poner un
						// ciclo
						j++;
						String lexema = codigo.substring(i, j);
						Token token = new Token(lexema, Token.FLOTANTE, j);
						return token;
					}
					return null;
				}

				while (j < codigo.length() && esDigito(codigo.charAt(j))) {
					j++;
				}
				if (j < codigo.length()
						&& (codigo.charAt(j) == 'e' || codigo.charAt(j) == 'E')) {
					j++;
					if (j < codigo.length()
							&& (codigo.charAt(j) == '-' || codigo.charAt(j) == '+')) {
						j++;
						if (j < codigo.length() && esDigito(codigo.charAt(j))) {
							// Si se quiere para mas de un solo digito poner un
							// ciclo
							j++;
							String lexema = codigo.substring(i, j);
							Token token = new Token(lexema, Token.FLOTANTE, j);
							return token;
						}
						return null;
					}
				}
				String lexema = codigo.substring(i, j);
				Token token = new Token(lexema, Token.FLOTANTE, j);
				return token;
			}
		}
		return null;
	}

	/**
	 * Intenta extraer un entero de la cadena cod a partir de la posici�n i,
	 * bas�ndose en el Aut�mata
	 * 
	 * @param cod
	 *            - c�digo al cual se le va a intentar extraer un entero -
	 *            codigo!=null
	 * @param i
	 *            - posici�n a partir de la cual se va a intentar extraer un
	 *            entero - 0<=indice<codigo.length()
	 * @return el token entero o NULL, si el token en la posici�n dada no es un
	 *         entero. El Token se compone de el lexema, el tipo y la posici�n
	 *         del siguiente lexema.
	 */

	// Este m�todo usa el m�todo substring(), que se explica a continuaci�n:
	// x.substring( i, j ) retorna una nueva cadena que es una subcadena de la
	// cadena x.
	// La subcadena comienza en la posici�n i y
	// se extiende hasta el car�cter en la posici�n j-1.
	// Ejemplo: "universidad".substring(3,6) retorna "ver",

	public Token extraerEntero(String codigo, int i) {

		int j;
		String lexema;
		if (codigo.charAt(i) >= '0' && codigo.charAt(i) <= '9') {
			j = i + 1;
			if (j < codigo.length() && esDigito(codigo.charAt(j))) {
				do
					j++;
				while (j < codigo.length() && esDigito(codigo.charAt(j)));
				lexema = codigo.substring(i, j);
				Token token = new Token(lexema, Token.ENTERO, j);
				return token;
			}

			lexema = codigo.substring(i, j);
			Token token = new Token(lexema, Token.ENTERO, j);
			return token;
		}

		return null;
	}

	/**
	 * Metodo encargado de extraer los n�meros decimales del c�digo
	 * 
	 * @param codigo
	 *            C�digo del cual se extraeran los numeros decimales
	 * @param i
	 *            �ndice donde se empezar� el an�lisis y la extracci�n
	 * @return Token con la informaci�n de la palabra, y el �ndice en el cual
	 *         termino la extraccion
	 */
	public Token extraerDecimal(String codigo, int i) {
		int j;
		if (codigo.charAt(i) == '0') {
			j = i + 1;
			if (j < codigo.length() && codigo.charAt(j) != '0'
					&& codigo.charAt(j) != '1' && codigo.charAt(j) != '2'
					&& codigo.charAt(j) != '3' && codigo.charAt(j) != '4'
					&& codigo.charAt(j) != '5' && codigo.charAt(j) != '6'
					&& codigo.charAt(j) != '7' && codigo.charAt(j) != 'b'
					&& codigo.charAt(j) != 'x' && codigo.charAt(j) != 'X') {

				String lexema = codigo.substring(i, j);
				Token token = new Token(lexema, Token.DECIMAL, j);
				return token;
			}
		}
		if (esDigito(codigo.charAt(i)) && codigo.charAt(i) != '0') {
			j = i + 1;
			while (j < codigo.length() && esDigito(codigo.charAt(j))) {
				j++;
			}
			String lexema = codigo.substring(i, j);
			Token token = new Token(lexema, Token.DECIMAL, j);
			return token;
		}
		return null;
	}

	/**
	 * Metodo encargado de extraer los n�meros hexadecimales del c�digo
	 * 
	 * @param codigo
	 *            C�digo del cual se extraeran los numeros hexadecimales
	 * @param i
	 *            �ndice donde se empezar� el an�lisis y la extracci�n
	 * @return Token con la informaci�n de la palabra, y el �ndice en el cual
	 *         termino la extraccion
	 */
	public Token extraerHexadecimal(String codigo, int i) {
		int j;
		if (codigo.charAt(i) == '0') {
			j = i + 1;
			if (j < codigo.length()
					&& (codigo.charAt(j) == 'x' || codigo.charAt(j) == 'X')) {
				j++;

				if (j < codigo.length()
						&& (codigo.charAt(j) == '0' || codigo.charAt(j) == '1'
								|| codigo.charAt(j) == '2'
								|| codigo.charAt(j) == '3'
								|| codigo.charAt(j) == '4'
								|| codigo.charAt(j) == '5'
								|| codigo.charAt(j) == '6'
								|| codigo.charAt(j) == '7'
								|| codigo.charAt(j) == '8'
								|| codigo.charAt(j) == '9'
								|| codigo.charAt(j) == 'a'
								|| codigo.charAt(j) == 'A'
								|| codigo.charAt(j) == 'b'
								|| codigo.charAt(j) == 'B'
								|| codigo.charAt(j) == 'c'
								|| codigo.charAt(j) == 'C'
								|| codigo.charAt(j) == 'd'
								|| codigo.charAt(j) == 'D'
								|| codigo.charAt(j) == 'e'
								|| codigo.charAt(j) == 'E'
								|| codigo.charAt(j) == 'f' || codigo.charAt(j) == 'F')) {

					j++;

					while (j < codigo.length()
							&& (codigo.charAt(j) == '0'
									|| codigo.charAt(j) == '1'
									|| codigo.charAt(j) == '2'
									|| codigo.charAt(j) == '3'
									|| codigo.charAt(j) == '4'
									|| codigo.charAt(j) == '5'
									|| codigo.charAt(j) == '6'
									|| codigo.charAt(j) == '7'
									|| codigo.charAt(j) == '8'
									|| codigo.charAt(j) == '9'
									|| codigo.charAt(j) == 'a'
									|| codigo.charAt(j) == 'A'
									|| codigo.charAt(j) == 'b'
									|| codigo.charAt(j) == 'B'
									|| codigo.charAt(j) == 'c'
									|| codigo.charAt(j) == 'C'
									|| codigo.charAt(j) == 'd'
									|| codigo.charAt(j) == 'D'
									|| codigo.charAt(j) == 'e'
									|| codigo.charAt(j) == 'E'
									|| codigo.charAt(j) == 'f' || codigo
									.charAt(j) == 'F')) {

						j++;

					}

					String lexema = codigo.substring(i, j);
					Token token = new Token(lexema, Token.HEXADECIMAL, j);
					return token;
				}
			}
			return null;
		}
		return null;
	}

	/**
	 * Intenta extraer un operador aditivo de la cadena cod a partir de la
	 * posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod
	 *            - c�digo al cual se le va a intentar extraer el operador
	 *            aditivo - codigo!=null
	 * @param i
	 *            - posici�n a partir de la cual se va a intentar extraer el
	 *            operador aditivo - 0<=i<codigo.length()
	 * @return el token operador aditivo o NULL, si el token en la posici�n dada
	 *         no es un operador aditivo.El Token se compone de el lexema, el
	 *         tipo y la posici�n del siguiente lexema.
	 */
	// public Token extraerOperadorAditivo(String cod, int i) {
	// if (cod.charAt(i) == '+' || cod.charAt(i) == '-') {
	// int j = i + 1;
	// String lex = cod.substring(i, j);
	// Token token = new Token(lex, Token.OPERADOR_ADITIVO, j);
	// return token;
	// }
	// return null;
	// }

	/**
	 * Intenta extraer un operador de asignaci�n de la cadena cod a partir de la
	 * posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod
	 *            - c�digo al cual se le va a intentar extraer el operador de
	 *            asignaci�n - codigo!=null
	 * @param i
	 *            - posici�n a partir de la cual se va a intentar extraer el
	 *            operador de asingaci�n - 0<=i<codigo.length()
	 * @return el token operador asignaci�n o NULL, si el token en la posici�n
	 *         dada no es un operador de asignaci�n. El Token se compone de el
	 *         lexema, el tipo y la posici�n del siguiente lexema.
	 */
	public Token extraerOperadorAsignacion(String cod, int i) {
		if (cod.charAt(i) == '=') {
			int j = i + 1;

			String lex = cod.substring(i, j);
			Token token = new Token(lex, Token.ASIGNACION_IGUAL, j);
			return token;
		}

		return null;
	}

	/**
	 * Intenta extraer un identificador de la cadena cod a partir de la posici�n
	 * i, bas�ndose en el Aut�mata
	 * 
	 * @param cod
	 *            - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i
	 *            - posici�n a partir de la cual se va a intentar extraer un
	 *            identificador - 0<=indice<codigo.length()
	 * @return el token identificaror o NULL, si el token en la posici�n dada no
	 *         es un identificador. El Token se compone de el lexema, el tipo y
	 *         la posici�n del siguiente lexema.
	 * 
	 */

	public Token extraerIdentificador(String cod, int i) {

		if (cod.charAt(i) == '$') {
			int j = i + 1;
			while (j < cod.length()
					&& (esLetra(cod.charAt(j)) || cod.charAt(j) == '_')) {
				j++;
				while (j < cod.length() && esDigito(cod.charAt(j))) {
					j++;
				}
			}
			String lex = cod.substring(i, j);
			if (lex.length() > 1) {
				Token token = new Token(lex, Token.IDENTIFICADOR_VARIABLE, j);
				return token;
			}
		}
		return null;
	}

	/**
	 * Extraer un lexema no reconocido de la cadena cod a partir de la posici�n
	 * i. Antes de utilizar este m�todo, debe haberse intentado todos los otros
	 * m�todos para los otros tipos de token
	 * 
	 * @param cod
	 *            - c�digo al cual se le va a extraer el token no reconocido -
	 *            codigo!=null
	 * @param i
	 *            - posici�n a partir de la cual se va a extraer el token no
	 *            reconocido - 0<=indice<codigo.length()
	 * @return el token no reconocido. El Token se compone de lexema, el tipo y
	 *         la posici�n del siguiente lexema.
	 */
	public Token extraerNoReconocido(String codigo, int i) {

		String lexema = codigo.substring(i, i + 1);
		int j = i + 1;
		Token token = new Token(lexema, Token.NO_RECONOCIDO, j);
		return token;
	}

	/**
	 * M�todo encargado de extraer las palabras reservadas referentes a
	 * visibilidad
	 * 
	 * @param codigo
	 *            codigo a ser analizado por m�todo
	 * @param i
	 *            �ndice donde se encuentra el c�digo al momento de ser
	 *            analizado
	 * @return Token que contiene el mensaje de lo que se trata la palabra y el
	 *         �ndice donde termino
	 */
	public Token extraerVisibilidad(String codigo, int i) {

		String lexema = "";

		if (codigo.charAt(i) == 'p') {

			int j = i + 1;

			if (j < codigo.length() && codigo.charAt(j) == 'u') {
				j = j + 1;
				if (j < codigo.length() && codigo.charAt(j) == 'b') {
					j = j + 1;
					if (j < codigo.length() && codigo.charAt(j) == 'l') {
						j = j + 1;
						if (j < codigo.length() && codigo.charAt(j) == 'i') {
							j = j + 1;
							if (j < codigo.length() && codigo.charAt(j) == 'c') {
								j = j + 1;
								lexema = codigo.substring(i, j);

								Token token = new Token(lexema,
										Token.VISIBILIDAD, j);

								return token;

							}
						}
					}
				}
			}

			if (j < codigo.length() && codigo.charAt(j) == 'r') {

				j = j + 1;

				if (j < codigo.length() && codigo.charAt(j) == 'i') {

					j = j + 1;

					if (j < codigo.length() && codigo.charAt(j) == 'v') {
						j = j + 1;

						if (j < codigo.length() && codigo.charAt(j) == 'a') {
							j = j + 1;

							if (j < codigo.length() && codigo.charAt(j) == 't') {
								j = j + 1;
								if (j < codigo.length()
										&& codigo.charAt(j) == 'e') {
									j = j + 1;
									lexema = codigo.substring(i, j);

									Token token = new Token(lexema,
											Token.VISIBILIDAD, j);

									return token;

								}

							}

						}

					}

				}

				if (j < codigo.length() && codigo.charAt(j) == 'o') {

					j = j + 1;

					if (j < codigo.length() && codigo.charAt(j) == 't') {
						j = j + 1;

						if (j < codigo.length() && codigo.charAt(j) == 'e') {
							j = j + 1;

							if (j < codigo.length() && codigo.charAt(j) == 'c') {
								j = j + 1;
								if (j < codigo.length()
										&& codigo.charAt(j) == 't') {
									j = j + 1;

									if (j < codigo.length()
											&& codigo.charAt(j) == 'e') {
										j = j + 1;

										if (j < codigo.length()
												&& codigo.charAt(j) == 'd') {
											j = j + 1;
											lexema = codigo.substring(i, j);

											Token token = new Token(lexema,
													Token.VISIBILIDAD, j);

											return token;

										}

									}
								}

							}

						}

					}
				}

			}
		}

		return null;
	}

	/**
	 * M�todo encargado de extraer los operadores arimeticos del c�digo
	 * 
	 * @param codigo
	 *            C�digo a ser analizado para extraer sus tokens
	 * @param i
	 *            �ndice en el que se encuentra el c�digo al momento de ser
	 *            analizado
	 * @return Token con la informaci�n obtenida, en este caso siendo un
	 *         operador arimetico y con el �ndice en el cual qued� el c�digo
	 */
	public Token extraerOperadorArimetico(String codigo, int i) {

		String lexema = "";
		int j;

		if (codigo.charAt(i) == '+' || codigo.charAt(i) == '-'
				|| codigo.charAt(i) == '%' || codigo.charAt(i) == '/') {

			j = i + 1;
			lexema = codigo.substring(i, j);
			Token token = new Token(lexema, Token.OPERADOR_ARIMETICO, j);
			return token;
		}

		if (codigo.charAt(i) == '*') {
			j = i + 1;

			if (j < codigo.length() && codigo.charAt(j) == '*') {

				j = j + 1;

				lexema = codigo.substring(i, j);

				Token token = new Token(lexema, Token.OPERADOR_ARIMETICO, j);

				return token;

			}

			lexema = codigo.substring(i, j);
			Token token = new Token(lexema, Token.OPERADOR_ARIMETICO, j);
			return token;

		}

		return null;
	}

	/**
	 * M�todo encargado de extraer operadores de comparaci�n del c�digo
	 * 
	 * @param cod
	 *            Codigo a ser analizado para su extraer sus operadores de
	 *            comparaci�n
	 * @param i
	 *            �ndice en el cual se comenzar� a analizar el c�digo
	 * @return Token con la informaci�n obtenida de la palabra, y el indice en
	 *         el cual finalizo de analizar
	 */
	public Token extraerOperadorComparacion(String cod, int i) {
		int j;
		String lex;
		if (cod.charAt(i) == '<') {
			j = i + 1;
			if (j < cod.length() && (cod.charAt(j) == '>')) {
				j++;
				String lexema = cod.substring(i, j);
				Token token = new Token(lexema, Token.OPERADOR_COMPARACION, j);
				return token;
			}
			if (j < cod.length() && (cod.charAt(j) == '=')) {
				j++;
				if (j < cod.length() && (cod.charAt(j) == '>')) {
					j++;
					String lexema = cod.substring(i, j);
					Token token = new Token(lexema, Token.OPERADOR_COMPARACION,
							j);
					return token;
				}
			}
			String lexema = cod.substring(i, j);
			Token token = new Token(lexema, Token.OPERADOR_COMPARACION, j);
			return token;
		}
		if (cod.charAt(i) == '=') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == '=') {
				j++;
				if (j < cod.length() && cod.charAt(j) == '=') {
					j++;
					String lexema = cod.substring(i, j);
					Token token = new Token(lexema, Token.OPERADOR_COMPARACION,
							j);
					return token;
				}
				String lexema = cod.substring(i, j);
				Token token = new Token(lexema, Token.OPERADOR_COMPARACION, j);
				return token;
			}
		}
		if (cod.charAt(i) == '>') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == '=') {
				j++;
				String lexema = cod.substring(i, j);
				Token token = new Token(lexema, Token.OPERADOR_COMPARACION, j);
				return token;
			}
			String lexema = cod.substring(i, j);
			Token token = new Token(lexema, Token.OPERADOR_COMPARACION, j);
			return token;
		}

		if (cod.charAt(i) == '!') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == '=') {
				j++;
				if (j < cod.length() && cod.charAt(j) == '=') {
					j++;
					String lexema = cod.substring(i, j);
					Token token = new Token(lexema, Token.OPERADOR_COMPARACION,
							j);
					return token;
				}
				String lexema = cod.substring(i, j);
				Token token = new Token(lexema, Token.OPERADOR_COMPARACION, j);
				return token;
			}
		}
		return null;
	}

	/**
	 * Metodo encargado de extraer la declaraci�n de metodo del c�digo
	 * 
	 * @param cod
	 *            Codigo a ser analizado por el m�todo para extraer su
	 *            declaraci�n de m�todo
	 * @param i
	 *            �ndice en el cual se empezara a analizar el c�digo
	 * @return Token con la informaci�n obtenida de la palabra, y el �ndice en
	 *         cual termino el analisis
	 */
	public Token extraerDeclaracionMetodo(String cod, int i) {

		int j;

		if (cod.charAt(i) == 'f') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'u') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'n') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'c') {
						j++;
						if (j < cod.length() && cod.charAt(j) == 't') {
							j++;
							if (j < cod.length() && cod.charAt(j) == 'i') {
								j++;
								if (j < cod.length() && cod.charAt(j) == 'o') {
									j++;
									if (j < cod.length()
											&& cod.charAt(j) == 'n') {
										j++;
										String lexema = cod.substring(i, j);
										Token token = new Token(lexema,
												Token.PALABRA_RESERVADA, j);
										return token;
									}
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * Metodo que se encarga de extraer la etiqueta de apertura del c�digo
	 * 
	 * @param cod
	 *            C�digo a ser analizado para extraer sus tokens
	 * @param i
	 *            �ndice en el cual se encuentra el c�digo para comenzar sus
	 *            an�lisis
	 * @return Token con la informaci�n de la palabra obtenida y la posici�n en
	 *         la cual termino el analisis
	 */
	public Token extraerEtiquetaApertura(String cod, int i) {

		int j;
		if (cod.charAt(i) == '<') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == '?') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'p') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'h') {
						j++;
						if (j < cod.length() && cod.charAt(j) == 'p') {
							j++;
							String lexema = cod.substring(i, j);
							Token token = new Token(lexema,
									Token.ETIQUETA_APERTURA, j);
							return token;
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * M�todo encargado de extraer el la palabra reservada de inicio de clase
	 * 
	 * @param cod
	 *            Codigo a ser analizado para encontrar sus tokens
	 * @param i
	 *            �ndice en el cual se encuentra el c�digo al momento de ser
	 *            analizado
	 * @return Token con la informaci�n de la palabra obtenida y la posici�n en
	 *         la cual termin� el analisis
	 */
	public Token extraerInicioClase(String cod, int i) {

		int j;
		String lex;
		if (cod.charAt(i) == 'c') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'l') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'a') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 's') {
						j++;
						if (j < cod.length() && cod.charAt(j) == 's') {
							j++;
							String lexema = cod.substring(i, j);
							Token token = new Token(lexema,
									Token.PALABRA_RESERVADA, j);
							return token;
						}
					}
				}
			}
		}
		return null;

	}

	/**
	 * M�todo encargado de extraer los espacios contenidos en el c�digo
	 * 
	 * @param codigo
	 *            C�digo a ser analizado por el m�todo
	 * @param i
	 *            �ndice en el cual se iniciar� el analisis
	 * @return Token con la informaci�n de la palabra, y el �ndice en el cual se
	 *         termino el analisis
	 */
	public Token extraerEspacio(String codigo, int i) {

		int j;
		if (codigo.charAt(i) == ' ' || codigo.charAt(i) == '\n' || codigo.charAt(i) == '\t') {

			String lexema = codigo.substring(i, i + 1);
			j = i + 1;
			Token token = new Token(lexema, Token.ESPACIO, j);
			return token;

		}
		return null;
	}
	
	/**
	 * M�todo que se encarga de extraer un separador que se 
	 * encuentra en el codigo en este caso tratandose de una coma (,)
	 * @param codigo
	 * 				C�digo a ser analizado por el m�todo para extraer
	 * 				su etiqueta final
	 * @param i
	 * 				�ndice en el cual comenzara el an�lisis del c�digo
	 * @return Token con la informaci�n de la palabra y el �ndice en el cual 
	 * 		   termino el an�lisis
	 */
	public Token extraerSeparador(String codigo, int i){
		int j;
		if(codigo.charAt(i) == ','){
			String lexema = codigo.substring(i, i+1);
			j = i+1;
			Token token = new Token(lexema, Token.SEPARADOR, j);
			return token;
		}
		return null;
	}

	/**
	 * M�todo encargado de extraer la Etiqueta final del c�digo
	 * 
	 * @param codigo
	 *            C�digo a ser analizado por el m�todo para extraer su etiqueta
	 *            final
	 * @param i
	 *            �ndice en el cual comenzara el analisis del c�digo
	 * @return Token con la informaci�n de la palabra y el �ndice en el cual
	 *         termino el an�lisis
	 */
	public Token extraerEtiquetaFinal(String codigo, int i) {

		int j;

		if (codigo.charAt(i) == '?') {
			j = i + 1;
			if (j < codigo.length() && codigo.charAt(j) == '>') {
				j++;
				String lexema = codigo.substring(i, j);

				Token token = new Token(lexema, Token.ETIQUETA_FINALIZACION, j);
				return token;

			}
		}
		return null;
	}

	/**
	 * M�todo encargado de extraer el operador terminal del c�digo
	 * 
	 * @param codigo
	 *            C�digo a ser analizado para extraer su operador final
	 * @param i
	 *            �ndice en el cual comenzara a ser analizado el c�digo
	 * @return Token con la informaci�n de la palabra extraida y el �ndice en el
	 *         cual termino el an�lisis
	 */
	public Token extraerOperadorTerminal(String codigo, int i) {

		int j;

		if (codigo.charAt(i) == ';') {
			j = i + 1;

			String lexema = codigo.substring(i, j);
			Token token = new Token(lexema, Token.OPERADOR_TERMINAL, j);
			return token;
		}

		return null;

	}

	/**
	 * Metodo encargado de extraer los operadores l�gicos del c�digo
	 * 
	 * @param cod
	 *            C�digo a ser analizado por el m�todo para extraer sus
	 *            operadores l�gicos
	 * @param i
	 *            �ndice en el que se iniciara el an�lisis del c�digo
	 * @return Token con la informaci�n de la palabra y el �ndice en el cual
	 *         termino el an�lisis
	 */
	public Token extraerOperadorLogico(String cod, int i) {
		int j;
		if (cod.charAt(i) == 'a') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'n') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'd') {
					j++;
					String lexema = cod.substring(i, j);
					Token token = new Token(lexema, Token.OPERADOR_LOGICO, j);
					return token;
				}
			}
		}
		if (cod.charAt(i) == 'o') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'r') {
				j++;
				String lexema = cod.substring(i, j);
				Token token = new Token(lexema, Token.OPERADOR_LOGICO, j);
				return token;
			}
		}
		if (cod.charAt(i) == 'x') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'o') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'r') {
					j++;
					String lexema = cod.substring(i, j);
					Token token = new Token(lexema, Token.OPERADOR_LOGICO, j);
					return token;
				}
			}
		}
		if (cod.charAt(i) == '!') {
			j = i + 1;
			String lexema = cod.substring(i, j);
			Token token = new Token(lexema, Token.OPERADOR_LOGICO, j);
			return token;
		}
		if (cod.charAt(i) == '&') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == '&') {
				j++;
				String lexema = cod.substring(i, j);
				Token token = new Token(lexema, Token.OPERADOR_LOGICO, j);
				return token;
			}
		}
		if (cod.charAt(i) == '|') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == '|') {
				j++;
				String lexema = cod.substring(i, j);
				Token token = new Token(lexema, Token.OPERADOR_LOGICO, j);
				return token;
			}
		}
		return null;
	}

	/**
	 * M�todo encargado de extraer la palabra reservada return
	 * 
	 * @param cod
	 *            C�digo del cual se extraer� la palabra reservada return
	 * @param i
	 *            �ndice en el cual se comenzar� el an�lisis
	 * @return Token con la informaci�n de la palabra y el �ndice en el cual
	 *         finalizo el an�lisis
	 */
	public Token extraerRetorno(String cod, int i) {
		int j;
		if (cod.charAt(i) == 'r') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'e') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 't') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'u') {
						j++;
						if (j < cod.length() && cod.charAt(j) == 'r') {
							j++;
							if (j < cod.length() && cod.charAt(j) == 'n') {
								j++;
								String lexema = cod.substring(i, j);
								Token token = new Token(lexema,
										Token.PALABRA_RESERVADA, j);
								return token;
							}
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * M�todo encargado de extraer la palabra reservada static
	 * 
	 * @param codigo
	 *            C�digo a ser analizado por el m�todo
	 * @param i
	 *            �ndice en el cual se iniciar� el analisis
	 * @return Token con la informaci�n de la palabra, y el �ndice en el cual se
	 *         termino el analisis
	 */
	public Token extraerPalabraStatic(String cod, int i) {
		int j;
		if (cod.charAt(i) == 's') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 't') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'a') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 't') {
						j++;
						if (j < cod.length() && cod.charAt(j) == 'i') {
							j++;
							if (j < cod.length() && cod.charAt(j) == 'c') {
								j++;
								String lexema = cod.substring(i, j);
								Token token = new Token(lexema,
										Token.PALABRA_RESERVADA, j);
								return token;
							}
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * M�todo encargado de extraer la palabra reservada new
	 * 
	 * @param codigo
	 *            C�digo a ser analizado por el m�todo
	 * @param i
	 *            �ndice en el cual se iniciar� el analisis
	 * @return Token con la informaci�n de la palabra, y el �ndice en el cual se
	 *         termino el analisis
	 */
	public Token extraerPalabraNew(String codigo, int i) {

		int j;

		if (codigo.charAt(i) == 'n') {
			j = i + 1;

			if (j < codigo.length() && codigo.charAt(j) == 'e') {
				j++;

				if (j < codigo.length() && codigo.charAt(j) == 'w') {

					j++;

					String lexema = codigo.substring(i, j);
					Token token = new Token(lexema, Token.PALABRA_RESERVADA, j);
					return token;

				}

			}
		}
		return null;

	}

	/**
	 * M�todo encargado de extraer la palabra reservada for
	 * 
	 * @param codigo
	 *            C�digo a ser analizado por el m�todo
	 * @param i
	 *            �ndice en el cual se iniciar� el analisis
	 * @return Token con la informaci�n de la palabra, y el �ndice en el cual se
	 *         termino el analisis
	 */
	public Token extraerPalabraFor(String codigo, int i) {

		int j;

		if (codigo.charAt(i) == 'f') {
			j = i + 1;

			if (j < codigo.length() && codigo.charAt(j) == 'o') {
				j++;

				if (j < codigo.length() && codigo.charAt(j) == 'r') {

					j++;

					String lexema = codigo.substring(i, j);
					Token token = new Token(lexema, Token.PALABRA_RESERVADA, j);
					return token;

				}

			}
		}
		return null;

	}

	/**
	 * M�todo encargado de extraer la palabra reservada final
	 * 
	 * @param codigo
	 *            C�digo a ser analizado por el m�todo
	 * @param i
	 *            �ndice en el cual se iniciar� el analisis
	 * @return Token con la informaci�n de la palabra, y el �ndice en el cual se
	 *         termino el analisis
	 */
	public Token extraerPalabraFinal(String codigo, int i) {

		int j;

		if (codigo.charAt(i) == 'f') {
			j = i + 1;

			if (j < codigo.length() && codigo.charAt(j) == 'i') {
				j++;

				if (j < codigo.length() && codigo.charAt(j) == 'n') {

					j++;

					if (j < codigo.length() && codigo.charAt(j) == 'a') {

						j++;

					}
					if (j < codigo.length() && codigo.charAt(j) == 'l') {

						j++;

						String lexema = codigo.substring(i, j);
						Token token = new Token(lexema,
								Token.PALABRA_RESERVADA, j);
						return token;
					}

				}

			}
		}
		return null;

	}

	/**
	 * M�todo encargado de extraer la palabra reservada while
	 * 
	 * @param codigo
	 *            C�digo a ser analizado por el m�todo
	 * @param i
	 *            �ndice en el cual se iniciar� el analisis
	 * @return Token con la informaci�n de la palabra, y el �ndice en el cual se
	 *         termino el analisis
	 */
	public Token extraerPalabraWhile(String codigo, int i) {

		int j;

		if (codigo.charAt(i) == 'w') {
			j = i + 1;
			if (j < codigo.length() && codigo.charAt(j) == 'h') {
				j++;

				if (j < codigo.length() && codigo.charAt(j) == 'i') {

					j++;

					if (j < codigo.length() && codigo.charAt(j) == 'l') {

						j++;

					}
					if (j < codigo.length() && codigo.charAt(j) == 'e') {

						j++;

						String lexema = codigo.substring(i, j);
						Token token = new Token(lexema,
								Token.PALABRA_RESERVADA, j);
						return token;
					}

				}

			}
		}
		return null;
	}

	/**
	 * M�todo encargado de extraer los incrementales que contenga el codigo
	 * 
	 * @param codigo
	 *            C�digo a ser analizado por el m�todo
	 * @param i
	 *            �ndice en el cual se iniciar� el analisis
	 * @return Token con la informaci�n de la palabra, y el �ndice en el cual se
	 *         termino el analisis
	 */
	public Token extraerAsignacionIncremental(String cod, int i) {
		int j;
		if (cod.charAt(i) == '+') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == '=') {
				j++;
				String lexema = cod.substring(i, j);
				Token token = new Token(lexema, Token.OPERADOR_ASIGNACION, j);
				return token;
			}
		}
		if (cod.charAt(i) == '-') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == '=') {
				j++;
				String lexema = cod.substring(i, j);
				Token token = new Token(lexema, Token.OPERADOR_ASIGNACION, j);
				return token;
			}
		}
		if (cod.charAt(i) == '*') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == '=') {
				j++;
				String lexema = cod.substring(i, j);
				Token token = new Token(lexema, Token.OPERADOR_ASIGNACION, j);
				return token;
			}
		}
		if (cod.charAt(i) == '/') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == '=') {
				j++;
				String lexema = cod.substring(i, j);
				Token token = new Token(lexema, Token.OPERADOR_ASIGNACION, j);
				return token;
			}
		}
		if (cod.charAt(i) == '%') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == '=') {
				j++;
				String lexema = cod.substring(i, j);
				Token token = new Token(lexema, Token.OPERADOR_ASIGNACION, j);
				return token;
			}
		}
		if (cod.charAt(i) == '&') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == '=') {
				j++;
				String lexema = cod.substring(i, j);
				Token token = new Token(lexema, Token.OPERADOR_ASIGNACION, j);
				return token;
			}
		}
		if (cod.charAt(i) == '|') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == '=') {
				j++;
				String lexema = cod.substring(i, j);
				Token token = new Token(lexema, Token.OPERADOR_ASIGNACION, j);
				return token;

			}
		}
		if (cod.charAt(i) == '^') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == '=') {
				j++;
				String lexema = cod.substring(i, j);
				Token token = new Token(lexema, Token.OPERADOR_ASIGNACION, j);
				return token;
			}
		}
		if (cod.charAt(i) == '<') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == '<') {
				j++;
				if (j < cod.length() && cod.charAt(j) == '=') {
					j++;
					String lexema = cod.substring(i, j);
					Token token = new Token(lexema, Token.OPERADOR_ASIGNACION,
							j);
					return token;
				}
			}
		}
		if (cod.charAt(i) == '>') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == '>') {
				j++;
				if (j < cod.length() && cod.charAt(j) == '=') {
					j++;
					String lexema = cod.substring(i, j);
					Token token = new Token(lexema, Token.OPERADOR_ASIGNACION,
							j);
					return token;
				}
			}
		}
		if (cod.charAt(i) == '.') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == '=') {
				j++;
				String lexema = cod.substring(i, j);
				Token token = new Token(lexema, Token.OPERADOR_ASIGNACION, j);
				return token;
			}
		}
		return null;
	}

	/**
	 * M�todo encargado de extraer las sentencias if/elseif/else
	 * 
	 * @param codigo
	 *            C�digo a ser analizado por el m�todo
	 * @param i
	 *            �ndice en el cual se iniciar� el analisis
	 * @return Token con la informaci�n de la palabra, y el �ndice en el cual se
	 *         termino el analisis
	 */
	public Token extraerSentenciaIfElse(String codigo, int i) {

		int j;

		if (codigo.charAt(i) == 'i') {
			j = i + 1;

			if (j < codigo.length() && codigo.charAt(j) == 'f') {

				j++;
				String lexema = codigo.substring(i, j);
				Token token = new Token(lexema, Token.PALABRA_RESERVADA, j);
				return token;

			}
		}
		if (codigo.charAt(i) == 'e') {
			j = i + 1;
			if (j < codigo.length() && codigo.charAt(j) == 'l') {
				j++;
				if (j < codigo.length() && codigo.charAt(j) == 's') {
					j++;
					if (j < codigo.length() && codigo.charAt(j) == 'e') {
						j++;
						if (j < codigo.length() && codigo.charAt(j) == 'i') {
							j++;

							if (j < codigo.length() && codigo.charAt(j) == 'f') {
								j++;

								String lexema = codigo.substring(i, j);
								Token token = new Token(lexema,
										Token.PALABRA_RESERVADA, j);
								return token;
							}
						}

						String lexema = codigo.substring(i, j);
						Token token = new Token(lexema,
								Token.PALABRA_RESERVADA, j);
						return token;
					}
				}
			}
		}

		return null;
	}

	/**
	 * M�todo encargado de extraer los numeros en base ocho
	 * 
	 * @param codigo
	 *            C�digo a ser analizado por el m�todo
	 * @param i
	 *            �ndice en el cual se iniciar� el analisis
	 * @return Token con la informaci�n de la palabra, y el �ndice en el cual se
	 *         termino el analisis
	 */
	public Token extraerOctal(String codigo, int i) {

		if (codigo.charAt(i) == '0') {
			int j = i + 1;

			if (j < codigo.length()
					&& (codigo.charAt(j) == '0' || codigo.charAt(j) == '1'
							|| codigo.charAt(j) == '2'
							|| codigo.charAt(j) == '3'
							|| codigo.charAt(j) == '4'
							|| codigo.charAt(j) == '5'
							|| codigo.charAt(j) == '6' || codigo.charAt(j) == '7')) {
				j++;
				while (j < codigo.length()
						&& (codigo.charAt(j) == '0' || codigo.charAt(j) == '1'
								|| codigo.charAt(j) == '2'
								|| codigo.charAt(j) == '3'
								|| codigo.charAt(j) == '4'
								|| codigo.charAt(j) == '5'
								|| codigo.charAt(j) == '6' || codigo.charAt(j) == '7')) {
					j++;
				}
				String lexema = codigo.substring(i, j);
				Token token = new Token(lexema, Token.OCTAL, j);
				return token;
			}
		}

		return null;
	}

	/**
	 * M�todo encargado de extraer los numeros en base binaria
	 * 
	 * @param codigo
	 *            C�digo a ser analizado por el m�todo
	 * @param i
	 *            �ndice en el cual se iniciar� el analisis
	 * @return Token con la informaci�n de la palabra, y el �ndice en el cual se
	 *         termino el analisis
	 */
	public Token extraerBinario(String codigo, int i) {
		int j;
		if (codigo.charAt(i) == '0') {
			j = i + 1;
			if (j < codigo.length() && codigo.charAt(j) == 'b') {
				j++;
				if (j < codigo.length()
						&& (codigo.charAt(j) == '0' || codigo.charAt(j) == '1')) {
					while (j < codigo.length()
							&& (codigo.charAt(j) == '0' || codigo.charAt(j) == '1')) {
						j++;
					}
					String lexema = codigo.substring(i, j);
					Token token = new Token(lexema, Token.BINARIO, j);
					return token;
				}
			}

		}
		return null;

	}

	/**
	 * M�todo encargado de extraer las cadenas de caracteres del codigo
	 * ingresado por el usuario (Comillas dobles)
	 * 
	 * @param codigo
	 *            C�digo a ser analizado por el m�todo
	 * @param i
	 *            �ndice en el cual se iniciar� el analisis
	 * @return Token con la informaci�n de la palabra, y el �ndice en el cual se
	 *         termino el analisis
	 */
	public Token extraerCadenaDeCaracteres(String codigo, int i) {
		boolean contiene = false;
		if (codigo.charAt(i) == '"') {
			int j = i + 1;
			while (j < codigo.length() && contiene == false) {
				if (codigo.charAt(j) == '"') {
					contiene = true;
				}
				j++;
			}
			if (contiene == true) {
				String lexema = codigo.substring(i + 1, j - 1);
				Token token = new Token(lexema, Token.CADENA_CARACTERES, j + 1);
				return token;
			} else {
				return null;
			}
		}
		return null;
	}

	/**
	 * M�todo encargado de extraer las cadenas de caracteres del codigo
	 * ingresado por el usuario (Comillas simples)
	 * 
	 * @param codigo
	 *            C�digo a ser analizado por el m�todo
	 * @param i
	 *            �ndice en el cual se iniciar� el analisis
	 * @return Token con la informaci�n de la palabra, y el �ndice en el cual se
	 *         termino el analisis
	 */
	public Token extraerCadenaDeCaracteresComillasSimples(String codigo, int i) {
		boolean contiene = false;
		String varAux1 = String.valueOf(codigo.charAt(i));
		if (varAux1.equals("'")) {
			int j = i + 1;
			while (j < codigo.length() && contiene == false) {
				String varAux2 = String.valueOf(codigo.charAt(j));
				if (varAux2.equals("'")) {
					contiene = true;
				}
				j++;
			}
			if (contiene == true) {
				String lexema = codigo.substring(i + 1, j - 1);
				Token token = new Token(lexema, Token.CADENA_CARACTERES, j);
				return token;
			} else {
				return null;
			}
		}
		return null;
	}

	/**
	 * M�todo encargado de extraer las aperturas de bloques encontradas en el
	 * codigo ingresado por el usuario
	 * 
	 * @param codigo
	 *            C�digo a ser analizado por el m�todo
	 * @param i
	 *            �ndice en el cual se iniciar� el analisis
	 * @return Token con la informaci�n de la palabra, y el �ndice en el cual se
	 *         termino el analisis
	 */
	public Token extraerAperturaDeBloque(String codigo, int i) {
		if (codigo.charAt(i) == '{') {
			String lexema = codigo.substring(i, i + 1);
			Token token = new Token(lexema, Token.APERTURA_BLOQUE, i + 1);
			return token;
		}
		return null;
	}

	/**
	 * M�todo encargado de extraer los cierres de bloques encontrados en el
	 * codigo ingresado por el usuario
	 * 
	 * @param codigo
	 *            C�digo a ser analizado por el m�todo
	 * @param i
	 *            �ndice en el cual se iniciar� el analisis
	 * @return Token con la informaci�n de la palabra, y el �ndice en el cual se
	 *         termino el analisis
	 */
	public Token extraerCierreDeBloque(String codigo, int i) {
		if (codigo.charAt(i) == '}') {
			String lexema = codigo.substring(i, i + 1);
			Token token = new Token(lexema, Token.CIERRE_BLOQUE, i + 1);
			return token;
		}
		return null;
	}

	/**
	 * M�todo encargado de extraer los caracteres especiales backslash
	 * encontrados en el codigo ingresado por el usuario
	 * 
	 * @param codigo
	 *            C�digo a ser analizado por el m�todo para los \ encontrados
	 * @param i
	 *            �ndice en el cual se iniciar� el analisis
	 * @return Token con la informaci�n de la palabra, y el �ndice en el cual se
	 *         termino el analisis
	 */
	public Token extraerBackSlash(String codigo, int i) {
		if (codigo.charAt(i) == '\\') {
			int j = i + 1;
			String lexema = codigo.substring(i, j);
			Token token = new Token(lexema, Token.CARACTER_ESPECIAL, j);
			return token;
		}
		return null;
	}

	/**
	 * M�todo encargado de extraer los simbolos de agrupacion abrir encontrados
	 * en el codigo ingresado por el usuario
	 * 
	 * @param codigo
	 *            C�digo a ser analizado por el m�todo
	 * @param i
	 *            �ndice en el cual se iniciar� el analisis
	 * @return Token con la informaci�n de la palabra, y el �ndice en el cual se
	 *         termino el analisis
	 */
	public Token extraerSimboloDeAgrupacionAbrir(String codigo, int i) {
		if (codigo.charAt(i) == '(') {
			String lexema = codigo.substring(i, i + 1);
			Token token = new Token(lexema, Token.AGRUPACION_ABRIR, i + 1);
			return token;
		}
		return null;
	}

	/**
	 * M�todo encargado de extraer los simbolos de agrupacion cerrar encontrados
	 * en el codigo ingresado por el usuario
	 * 
	 * @param codigo
	 *            C�digo a ser analizado por el m�todo
	 * @param i
	 *            �ndice en el cual se iniciar� el analisis
	 * @return Token con la informaci�n de la palabra, y el �ndice en el cual se
	 *         termino el analisis
	 */
	public Token extraerSimboloDeAgrupacionCerrar(String codigo, int i) {
		if (codigo.charAt(i) == ')') {
			String lexema = codigo.substring(i, i + 1);
			Token token = new Token(lexema, Token.AGRUPACION_CERRAR, i + 1);
			return token;
		}
		return null;
	}

	/**
	 * M�todo encargado de extraer los simbolos de interrogaci�n encontrados en
	 * el codigo ingresado por el usuario
	 * 
	 * @param codigo
	 *            C�digo a ser analizado por el m�todo
	 * @param i
	 *            �ndice en el cual se iniciar� el analisis
	 * @return Token con la informaci�n de la palabra, y el �ndice en el cual se
	 *         termino el analisis
	 */
	public Token extraerSimboloDeInterrogacion(String codigo, int i) {
		if (codigo.charAt(i) == '?') {
			String lexema = codigo.substring(i, i + 1);
			Token token = new Token(lexema, Token.CARACTER_ESPECIAL, i + 1);
			return token;
		}
		return null;
	}

	/**
	 * M�todo encargado de extraer los simbolos de flecha encontrados en el
	 * codigo ingresado por el usuario
	 * 
	 * @param codigo
	 *            C�digo a ser analizado por el m�todo
	 * @param i
	 *            �ndice en el cual se iniciar� el analisis
	 * @return Token con la informaci�n de la palabra, y el �ndice en el cual se
	 *         termino el analisis
	 */
	public Token extraerFlecha(String codigo, int i) {
		if (codigo.charAt(i) == '-') {
			int j = i + 1;
			if (codigo.charAt(j) == '>') {
				String lexema = codigo.substring(i, j + 1);
				Token token = new Token(lexema, Token.CARACTER_ESPECIAL, j + 1);
				return token;
			}
		}
		return null;
	}

	/**
	 * M�todo encargado de extraer la palabra reservada do encontrada en el
	 * codigo ingresado por el usuario
	 * 
	 * @param codigo
	 *            C�digo a ser analizado por el m�todo
	 * @param i
	 *            �ndice en el cual se iniciar� el analisis
	 * @return Token con la informaci�n de la palabra, y el �ndice en el cual se
	 *         termino el analisis
	 */
	public Token extraerPalabraDo(String codigo, int i) {

		if (codigo.charAt(i) == 'd') {
			int j = i + 1;
			if (j < codigo.length() && codigo.charAt(j) == 'o') {
				j++;
				String lexema = codigo.substring(i, j);
				Token token = new Token(lexema, Token.PALABRA_RESERVADA, j);
				return token;
			}
		}
		return null;
	}

	/**
	 * M�todo encargado de extraer la palabra reservada echo del codigo
	 * ingresado por el usuario
	 * 
	 * @param codigo
	 *            C�digo a ser analizado por el m�todo
	 * @param i
	 *            �ndice en el cual se iniciar� el analisis
	 * @return Token con la informaci�n de la palabra, y el �ndice en el cual se
	 *         termino el analisis
	 */

	public Token extraerPalabraReservadaEcho(String codigo, int i) {

		if (codigo.charAt(i) == 'e') {
			int j = i + 1;
			if (j < codigo.length() && codigo.charAt(j) == 'c') {
				j++;
				if (j < codigo.length() && codigo.charAt(j) == 'h') {
					j++;
					if (j < codigo.length() && codigo.charAt(j) == 'o') {
						j++;
						String lexema = codigo.substring(i, j);
						Token token = new Token(lexema,
								Token.PALABRA_RESERVADA, j);
						return token;
					}
				}
			}
		}
		return null;
	}

	/**
	 * M�todo encargado de identificar los nombres de los m�todos en el codigo
	 * ingresado por el usuario
	 * 
	 * @param codigo
	 *            C�digo a ser analizado por el m�todo
	 * @param i
	 *            �ndice en el cual se iniciar� el analisis
	 * @return Token con la informaci�n de la palabra, y el �ndice en el cual se
	 *         termino el analisis
	 */
	public Token extraerIdentificadorMetodo(String codigo, int i) {

		if (i < codigo.length()
				&& ((codigo.charAt(i) >= 'a' && codigo.charAt(i) <= 'z') || codigo
						.charAt(i) == '_')) {
			int j = i + 1;

			if (j < codigo.length()
					&& (codigo.charAt(j) >= 'a' && codigo.charAt(j) <= 'z')) {

				while (j < codigo.length()
						&& ((codigo.charAt(j) >= 'a' && codigo.charAt(j) <= 'z') || esDigito(codigo
								.charAt(j)))) {

					if ((j + 1) < codigo.length()
							&& (codigo.charAt(j) == 'a'
									|| codigo.charAt(j) == 'e' || codigo
									.charAt(j) == 'i')) {

						if (codigo.charAt(j + 1) == 'r') {
							j++;

							if ((j + 1) < codigo.length()
									&& (esLetra(codigo.charAt(j + 1)) || esDigito(codigo
											.charAt(j + 1)))) {

								j++;
								while (j < codigo.length()
										&& (esLetra(codigo.charAt(j)) || esDigito(codigo
												.charAt(j)))) {
									j++;
								}

								String lexema = codigo.substring(i, j);
								Token token = new Token(lexema,
										Token.IDENTIFICADOR_METODO, j);
								return token;

							}

							String lexema = codigo.substring(i, j + 1);
							Token token = new Token(lexema,
									Token.IDENTIFICADOR_METODO, j + 1);
							return token;
						}

					}
					j++;
				}

			}
		}

		return null;
	}

	/**
	 * M�todo encargado de identificar los nombres de las clases en el codigo
	 * ingresado por el usuario
	 * 
	 * @param codigo
	 *            C�digo a ser analizado por el m�todo
	 * @param i
	 *            �ndice en el cual se iniciar� el analisis
	 * @return Token con la informaci�n de la palabra, y el �ndice en el cual se
	 *         termino el analisis
	 */
	public Token extraerIdentificadorClase(String codigo, int i) {

		if (i < codigo.length() && codigo.charAt(i) >= 'A'
				&& codigo.charAt(i) <= 'Z') {
			int j = i + 1;

			while (j < codigo.length() && esLetra(codigo.charAt(j))) {
				j++;
			}

			String lexema = codigo.substring(i, j);
			Token token = new Token(lexema, Token.IDENTIFICADOR_CLASE, j);
			return token;

		}

		return null;
	}

	/**
	 * Determina si un car�cter es un d�gito
	 * 
	 * @param caracter
	 *            - Car�cter que se va a analizar - caracter!=null,
	 * @return true o false seg�n el car�cter sea un d�gito o no
	 */
	public boolean esDigito(char caracter) {
		return caracter >= '0' && caracter <= '9';
	}

	/**
	 * Determina si un car�cter es una letra
	 * 
	 * @param caracter
	 *            - Car�cter que se va a analizar - caracter!=null,
	 * @return true o false seg�n el car�cter sea una letra o no
	 */
	public boolean esLetra(char caracter) {
		return (caracter >= 'A' && caracter <= 'Z')
				|| (caracter >= 'a' && caracter <= 'z');
	}

}
