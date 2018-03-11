package interfaz;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Clase que se encarga de pinta la imagen en el jframe de donde es llamado
 * 
 * @author Santiago Montaño Lince
 * @author Sebastian Romero
 * @author Jesica Tapasco Velez
 * 
 */
@SuppressWarnings("serial")
public class PanelImagenFondo extends JPanel {

	/**
	 * Variable que representa el atributo imagen de la clase
	 */
	private ImageIcon imagen;

	/**
	 * Variable que representa el atributo nombre de la clase
	 */
	private String nombre;

	/**
	 * Método constructor, que recibe el nombre de la imagen que contendrán los
	 * paneles creados de esta clase.
	 * 
	 * @param nombre
	 *            nombre de la imagen
	 */

	public PanelImagenFondo(String nombre) {
		this.nombre = nombre;
		this.setLayout(null);
	}

	/**
	 * Método que pinta la imagen en el panel.
	 * 
	 * @param g
	 *            Variable que permite asignar el tamaño, la altura y la imagen
	 *            al panel
	 */
	public void paint(Graphics g) {
		Dimension tamano = getSize();
		imagen = new ImageIcon(getClass().getResource(nombre));
		g.drawImage(imagen.getImage(), 0, 0, tamano.width, tamano.height, null);
		setOpaque(false);
		super.paint(g);
	}

}
