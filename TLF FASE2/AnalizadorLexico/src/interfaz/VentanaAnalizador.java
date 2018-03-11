package interfaz;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import mundo.AnalizadorLexico;
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
 * Clase que se encarga de mostrar en área de codificación
 * 
 * @author Santiago Montaño Lince
 * @author Sebastian Romero
 * @author Jesica Tapasco Velez
 * 
 */
public class VentanaAnalizador extends javax.swing.JFrame implements
		ActionListener {

	/**
	 * Auto-generated main method to display this JFrame
	 * Método que permite ejecutar la aplicación, incluyendo el mundo y su interfaz
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				VentanaAnalizador inst = new VentanaAnalizador();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	/**
	 * Variable que representa al aotributo JButton ver token de la ventana
	 */
	private JButton btnVerToken;
	/**
	 * Variable que repesenta el JLabel con el titulo del area de codificación
	 */
	private JLabel lblAreaCodificacion;
	/**
	 * Variable que representa la instancia de la clase Analizador lexico
	 */
	private AnalizadorLexico analizadorLexico;
	/**
	 * Variable que representa el area de texto donde se ingresara el código a
	 * verificar
	 */
	private JTextArea txtArCodigo;
	/**
	 * Variables que representan los componentes JTable de la ventana
	 */
	private JTable tablaNoValidos, tablaValidos;
	/**
	 * Variable que representa la tabla de tokens reconociados del código
	 * ingresado
	 */
	private TableModelTokenValido tableModelValidos;
	/**
	 * Variable que representa la tabla de tokens no reconocidos del codigo
	 * ingresado
	 */
	private TableModelTokenNoValido tableModelNoValidos;
	/**
	 * Variable que represneta el panel hubicado en el fondo del JFrame
	 */
	PanelImagenFondo panel;

	/**
	 * Constructor de la clase
	 */
	public VentanaAnalizador() {
		super();
		analizadorLexico = new AnalizadorLexico();
		initGUI();
	}

	/**
	 * Método que inicializa los componentes y los agrega a la ventana
	 */
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			pack();
			panel = new PanelImagenFondo(
					"fondo/1680x1050_elephant-on-black.jpg");
			getContentPane().add(panel);
			panel.setBounds(0, 0, 503, 451);
			// panel.setLayout(null);
			this.setSize(509, 485);
			setLocationRelativeTo(null);
			setLayout(null);
			setResizable(false);

			{
				txtArCodigo = new JTextArea();
				panel.add(txtArCodigo);
				txtArCodigo.setBounds(12, 25, 420, 500);
				JScrollPane panel3 = new JScrollPane(txtArCodigo,
						JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
						JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				panel3.setBounds(7, 64, 347, 375);

				panel.add(panel3);
				panel3.setViewportView(txtArCodigo);
				// txtArCodigo.setPreferredSize(new java.awt.Dimension(347,
				// 372));
			}
			{
				lblAreaCodificacion = new JLabel();
				panel.add(lblAreaCodificacion);
				lblAreaCodificacion.setText("Area de codificación");
				lblAreaCodificacion.setBounds(74, 20, 266, 24);
				lblAreaCodificacion.setFont(new java.awt.Font(
						"Monotype Corsiva", 1, 24));
				lblAreaCodificacion.setForeground(new java.awt.Color(255, 255,
						255));
			}
			{
				btnVerToken = new JButton();
				panel.add(btnVerToken);
				btnVerToken.setText("Ver token");
				btnVerToken.setBounds(365, 23, 105, 23);
				btnVerToken
						.setFont(new java.awt.Font("Monotype Corsiva", 2, 16));
				btnVerToken.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						ArrayList tokens = verTokens(txtArCodigo.getText());
						if (tokens != null) {
							ArrayList tokenValido;
							ArrayList tokenNoValido;

							tokenValido = new ArrayList<Token>();
							tokenNoValido = new ArrayList<Token>();

							for (int i = 0; i < tokens.size(); i++) {

								Token token = (Token) tokens.get(i);

								if (token.darTipo().equals("No reconocido")) {
									tokenNoValido.add(token);
								} else {
									tokenValido.add(token);
								}
							}

							tablaNoValidos = new JTable();
							tablaValidos = new JTable();

							VentanaAutomata vtnAutomata = new VentanaAutomata(
									tokenValido, tokenNoValido);
							vtnAutomata.setVisible(true);
						}
					}
				});
			}

		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	/**
	 * Método que se encarga de agrupar los tokens del codigo en una lista
	 * 
	 * @param codigo
	 *            Código que ingresado por el ususario, al cual se le hallaran
	 *            juntos
	 * @return List vectorTonkesn Lista con los tokens del código
	 */
	public ArrayList verTokens(String codigo) {

		if (!codigo.equals("")) {
			ArrayList vectorTokens = analizadorLexico.extraerTokens(codigo);
			ArrayList vectorTokensEditados = new ArrayList();
			Token token;
			for (int i = 0; i < vectorTokens.size(); i++) {
				token = (Token) vectorTokens.get(i);
				vectorTokensEditados.add(token.darDescripcion());
			}

			return vectorTokens;
		} else {
			JOptionPane.showMessageDialog(this,
					"Debe ingresar una codigo fuente primero", "Error",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}

	/**
	 * Metodo que permite obtener evento realizados por el usuario
	 * @param e
	 * 	Evento ocasionado por un click hecho por el usuario
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
