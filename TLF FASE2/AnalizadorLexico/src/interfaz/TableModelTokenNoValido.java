package interfaz;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import mundo.Token;

/**
 * Clase que se encarga que contiene la tabla con los tokens validos y no
 * validos encontrados en el codigo ingresado por el usuario
 * 
 * @author Santiago Montaño Lince
 * @author Sebastian Romero
 * @author Jesica Tapasco Velez
 * 
 */
public class TableModelTokenNoValido extends DefaultTableModel implements
		TableModel {
	/**
	 * Vlriable que representa al atributo que contiene una lista de tokens no
	 * validos
	 */
	private List<Token> tokens = new ArrayList<Token>();
	/**
	 * VAriable que contiene el titulo de la columna
	 */
	private String[] columnas = { "Token no valido" };

	/**
	 * Constructor de la clase
	 * 
	 * @param tokens
	 *            Lista de tokns que se preciben para mostrar en la table
	 */
	public TableModelTokenNoValido(List<Token> tokens) {
		this.tokens = tokens;

	}

	/**
	 * Método que retorna un valor a una de las columnas
	 * 
	 * @return el dato en esa poscion
	 */

	@Override
	public String getColumnName(int column) {
		return columnas[column];
	}

	/**
	 * Método que retona el numero de columnas
	 */
	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	/**
	 * Método que devuelve el numero de una fila donde se encuentra el dato
	 */
	@Override
	public int getRowCount() {
		return tokens != null ? tokens.size() : 0;
	}

	/**
	 * Método que devuelve un dato de una elda
	 */
	@Override
	public Object getValueAt(int row, int column) {
		Token token = tokens.get(row);

		return token.darDescripcion();

	}

	/**
	 * @return tokens 
	 */
	public List<Token> getTokens() {
		return tokens;
	}

	/**
	 * @param tokens
	 *            the tokens to set
	 */
	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}
	/**
	 * Método que permite el correcta eliminacion de celdas
	 */

	public void removeRow(int row) {
		tokens.remove(row);
		fireTableRowsDeleted(row, 0);
	}

	public void fireTableRowsUpdated(int firstRow, int lastRow) {
		fireTableChanged(new TableModelEvent(this, firstRow, lastRow,
				TableModelEvent.ALL_COLUMNS, TableModelEvent.UPDATE));
	}

}
