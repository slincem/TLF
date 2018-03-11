package interfaz;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import mundo.Token;

public class TableModelTokenValido extends DefaultTableModel implements
		TableModel {
	/**
	 * Variable que representa el arrayList que contien los tokens valido
	 */
	private List<Token> tokens = new ArrayList<Token>();
	/**
	 * Variable que representa el nombre de la columna primera y unica columna
	 */
	private String[] columnas = { "Token valido" };

	/**
	 * Cosntructor de la clase
	 * 
	 * @param tokens
	 *            toekn a analizar
	 */
	public TableModelTokenValido(List<Token> tokens) {
		this.tokens = tokens;

	}

	/**
	 * Método que la columna solicitada
	 */
	@Override
	public String getColumnName(int column) {
		return columnas[column];
	}

	/**
	 * Metodo que devuelve el numero de columnas de una tabla
	 */
	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	/**
	 * Método que devuelvel al numero de filas
	 * 
	 * @return numero de filas de la tabal
	 */
	@Override
	public int getRowCount() {
		return tokens != null ? tokens.size() : 0;
	}

	/**
	 * Método que devuelve un dato de una celda
	 * 
	 * @return devuelve el objeto en esa celda
	 */
	@Override
	public Object getValueAt(int row, int column) {
		Token token = tokens.get(row);

		return token.darDescripcion();

	}

	/**
	 * @return the tokens
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

	/**
	 * Metodo que permite la modificación de celdas
	 */
	public void fireTableRowsUpdated(int firstRow, int lastRow) {
		fireTableChanged(new TableModelEvent(this, firstRow, lastRow,
				TableModelEvent.ALL_COLUMNS, TableModelEvent.UPDATE));
	}

}
