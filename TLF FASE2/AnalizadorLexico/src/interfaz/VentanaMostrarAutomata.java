package interfaz;

import java.awt.CardLayout;
import javax.swing.JLabel;

import javax.swing.ImageIcon;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

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
 * Clase que se encarga de mostrar los automatas por medio de un panel que
 * contiene la imagen solicitada
 * 
 * @author Santiago Montaño Lince
 * @author Sebastian Romero
 * @author Jesica Tapasco Velez
 * 
 */
public class VentanaMostrarAutomata extends javax.swing.JFrame {

	private String imagen;
	private PanelImagenFondo panelImagen;
	private JLabel lblImagen;

	public VentanaMostrarAutomata(String imagen) {

		this.imagen = imagen;
		initGUI();
		setVisible(true);

	}

	/**
	 * Método que permite inicializar la ventana de mostrar automata,la cual sera vista por el usuario
	 */
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			pack();
			ImageIcon ima = new ImageIcon(getClass().getResource(imagen));
			setSize(ima.getIconWidth(), ima.getIconHeight());
			{
				lblImagen = new JLabel();
				lblImagen.setIcon(ima);
				this.add(lblImagen);
			}
			setResizable(false);
			setLocationRelativeTo(null);

		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

}
