package interfaz;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import mundo.Token;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */

/**
 * Clase que se encarga de mostrar la tabla con los tokens validos y no validos
 * encontrados en el codigo ingresado por el usuario
 * 
 * @author Santiago Montaño Lince
 * @author Sebastian Romero
 * @author Jesica Tapasco Velez
 * 
 */
public class VentanaAutomata extends javax.swing.JFrame {

	/**
	 * Variables que representan los arrayList de con tokens validos e
	 * invalidos.
	 */
	private ArrayList tokenValido, tokenNoValido;

	/**
	 * Varible que represnta la instancia del que contiene la imagen de fondo de
	 * la ventana
	 */
	private PanelImagenFondo panelImagen;

	/**
	 * Constructor de la clase
	 * 
	 * @param tokenValido
	 *            Lista de tokens aceptados por el lenguaje php
	 * @param tokenNoValido
	 *            Lista de tokens no aceptados por el lenguaje php
	 */
	public VentanaAutomata(ArrayList tokenValido, ArrayList tokenNoValido) {
		super();
		this.tokenValido = tokenValido;
		this.tokenNoValido = tokenNoValido;
		initGUI();
	}

	/**
	 * Método que inicializa y agrega los componentes a la ventana
	 */
	private void initGUI() {
		try {
			panelImagen = new PanelImagenFondo("fondo/imagen1.jpg");
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			pack();
			this.setSize(1002, 542);
			setLayout(null);
			setLocationRelativeTo(null);
			setResizable(false);
			add(panelImagen);
			panelImagen.setBounds(0, 0, 996, 508);
			{
				final JTable tablaValidos = new JTable();

				TableModelTokenValido tableModelValidos = new TableModelTokenValido(
						tokenValido);
				tablaValidos.setModel(tableModelValidos);
				tablaValidos.setBounds(20, 26, 336, 190);

				JScrollPane panelTabla = new JScrollPane(tablaValidos,
						JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
						JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				panelTabla.setBounds(40, 26, 438, 432);

				panelImagen.add(panelTabla);

				panelTabla.setViewportView(tablaValidos);

				final TableModelTokenValido tabToken = tableModelValidos;
				tablaValidos.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						mostrarAutomatas(tablaValidos, e, tabToken);
					}
				});
			}
			{
				JTable tablaNoValidos = new JTable();

				tablaNoValidos.setBounds(20, 26, 336, 190);

				TableModelTokenNoValido tableModelNoValidos = new TableModelTokenNoValido(
						tokenNoValido);
				tablaNoValidos.setModel(tableModelNoValidos);
				JScrollPane panelTabla = new JScrollPane(tablaNoValidos,
						JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
						JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				panelTabla.setBounds(530, 26, 435, 432);

				panelImagen.add(panelTabla);
				tablaNoValidos.setEnabled(false);
				panelTabla.setViewportView(tablaNoValidos);

			}
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	/**
	 * Método que se encarga de enviar el nombre del autómata a mostrar a la
	 * ventana mostrar autómata
	 * 
	 * @param token
	 *            Token del cual se mostrara el autómata
	 */
	private void automata(Token token) {

		String imagen = "";

		if (token.darTipo().equals("Numero flotante")) {
			imagen = "automatas/AFD-Flotante.jpg";
		}
		if (token.darTipo().equals("Numero decimal")) {
			imagen = "automatas/AFD-Decimal.png";
		}
		if (token.darTipo().equals("Numero hexadecimal")) {
			imagen = "automatas/AFD-Hexadecimal.png";
		}
		if (token.darTipo().equals("Numero binario")) {
			imagen = "automatas/AFD-Binarios.png";
		}
		if (token.darTipo().equals("Numero octal")) {
			imagen = "automatas/AFD-Octal.png";
		}
		if (token.darTipo().equals("Cadena de caracteres")) {
			imagen = "automatas/AFD-CadenaDeCaracteresComillasDobles.png";
		}
		if (token.darTipo().equals("Identificador de variable")) {
			imagen = "automatas/AFD-IdentificadorDeVariable.png";
		}
		if (token.darTipo().equals("Caracter especial")) {
			if (token.darLexema().equals("?")) {
				imagen = "automatas/AFD-CaracterEspecialSimboloPregunta.png";
			} else if (token.darLexema().equals("->")) {
				imagen = "automatas/AFD-CaracterEspecialFlecha.png";
			} else if (token.darLexema().equals("\\")) {
				imagen = "automatas/AFD-CaracterEspecialBackSlash.png";
			}
		}
		if (token.darTipo().equals("Apertura de bloque")) {
			imagen = "automatas/AFD-AperturaDeBloque.png";
		}
		if (token.darTipo().equals("Cierre de bloque")) {
			imagen = "automatas/AFD-CierreDeBloque.png";
		}
		if (token.darTipo().equals("Operador de asignación")) {
			imagen = "automatas/AFD-OperadoresDeAsignacion.jpg";
		}
		if (token.darTipo().equals("Operador de asignación igual")){
			imagen = "automatas/AFD-Asignacion.png";
		}
		if (token.darTipo().equals("Separador")){
			imagen = "automatas/AFD-Separador.png";
		}
		// if (token.darTipo().equals("Identificador")) {
		// imagen = "IdentificadorDeAsignacion";
		// }
		if (token.darTipo().equals("Operador de visibilidad")) {
			imagen = "automatas/AFD-Visibilidad.png";
		}
		if (token.darTipo().equals("Operador arimetico ")) {
			imagen = "automatas/AFD-operadoresAritmeticos.png";
		}
		if (token.darTipo().equals("Operador de comparacion")) {
			imagen = "automatas/AFD-OperadoresDeComparacion.PNG";
		}
		if (token.darTipo().equals("Palabra reservada")) {

			if (token.darLexema().equals("final")) {
				imagen = "automatas/AFD-final.png";
			}
			if (token.darLexema().equals("do")) {
				imagen = "automatas/AFD-do.png";
			}
			if (token.darLexema().equals("echo")) {
				imagen = "automatas/AFD-echo.png";
			}
			if (token.darLexema().equals("class")) {
				imagen = "automatas/AFD-class.png";
			}
			if (token.darLexema().equals("for")) {
				imagen = "automatas/AFD-for.png";
			}
			if (token.darLexema().equals("while")) {
				imagen = "automatas/AFD-while.png";
			}
			if (token.darLexema().equals("if")) {
				imagen = "automatas/AFD-if(else_if).png";
			}
			if (token.darLexema().equals("else")) {
				imagen = "automatas/AFD-if(else_if).png";
			}
			if (token.darLexema().equals("elseif")) {
				imagen = "automatas/AFD-if(else_if).png";
			}
			if (token.darLexema().equals("return")) {
				imagen = "automatas/AFD-return.png";
			}
			if (token.darLexema().equals("static")) {
				imagen = "automatas/AFD-static.png";
			}
			if (token.darLexema().equals("function")) {
				imagen = "automatas/AFD-DeclaracionDeMetodo.png";
			}
			// if (token.darLexema().equals("")) {
			// imagen = "automatas/AFD-return.png";
			// }

		}
		if (token.darTipo().equals("Etiqueta apertura")) {
			imagen = "automatas/AFD-EtiquetaApertura.png";
		}
		if (token.darTipo().equals("Etiqueta finalizacion")) {
			imagen = "automatas/AFD-EtiquetaCierre.png";
		}
		if (token.darTipo().equals("Operador terminal")) {
			imagen = "automatas/AFD-Terminal.png";
		}
		if (token.darTipo().equals("Operador lógico")) {
			imagen = "automatas/AFD-OperadoresLogicos.png";
		}
		if (token.darTipo().equals("Identificador de metodo")) {
			imagen = "automatas/AFD-IdentificadorDeMetodo.png";
		}
		if (token.darTipo().equals("Identificador de clase")) {
			imagen = "automatas/AFD-IdentificadorDeClase.png";
		}
		if(token.darTipo().equals("Agrupacion de apertura")){
			imagen = "automatas/AFD-AgrupacionDeApertura.PNG";
		}
		if(token.darTipo().equals("Agrupacion de cierre")){
			imagen = "automatas/AFD-AgrupacionDeCierre.PNG";
		}

		VentanaMostrarAutomata vtnMosAuto = new VentanaMostrarAutomata(imagen);
	}

	/**
	 * Método que se encarga de enviar el token seleccionado al método automata
	 * 
	 * @param tablaValidos
	 *            JTable que contiene la TableModel de tokens validos en el
	 *            lenguaje php
	 * @param e
	 *            Mouse Event generado por el usuario al seleccionar una celda
	 *            de la JTable
	 * @param tabToken
	 *            TableModel que contiene la lista de tokens validos
	 */
	protected void mostrarAutomatas(JTable tablaValidos, MouseEvent e,
			TableModelTokenValido tabToken) {
		int fila = tablaValidos.rowAtPoint(e.getPoint());
		int columna = tablaValidos.columnAtPoint(e.getPoint());

		if ((fila > -1) && (columna > -1)) {
			Token token = tabToken.getTokens().get(fila);
			automata(token);
		}
	}
}
