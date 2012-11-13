/**
 * 
 */
package ar.com.fi.uba.tecnicas.modelo.excepciones;

/**
 * Excepcion al momento de validar
 * @author ramiro
 */
public class ValidacionExcepcion extends Exception {

	/**
	 * Serial Id
	 */
	private static final long serialVersionUID = 4427650262179853543L;

	/**
	 * Constructor vacio
	 */
	public ValidacionExcepcion() {
		super();
	}

	/**
	 * Constructor a partir de un mensaje de error y una excepcion
	 * @param arg0 Mensaje de error 
	 * @param arg1 La excepcion
	 */
	public ValidacionExcepcion(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor a partir de un mensaje de error
	 * @param arg0 Mensaje de error
	 */
	public ValidacionExcepcion(String message) {
		super(message);
	}

	/**
	 * Constructor a partir de una excepcion
	 * @param arg0 Excepcion
	 */
	public ValidacionExcepcion(Throwable cause) {
		super(cause);
	}

}
