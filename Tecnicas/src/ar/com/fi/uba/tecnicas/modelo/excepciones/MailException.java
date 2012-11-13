/**
 * 
 */
package ar.com.fi.uba.tecnicas.modelo.excepciones;

/**
 * Excepcion al momento de trabajar con mails
 * @author ramiro
 *
 */
public class MailException extends Exception {

	/**
	 * Serial Id
	 */
	private static final long serialVersionUID = -4305140978400190915L;

	/**
	 * Constructor vacio
	 */
	public MailException() {
		super();
	}

	/**
	 * Constructor a partir de un mensaje de error
	 * @param arg0 Mensaje de error
	 */
	public MailException(String arg0) {
		super(arg0);
	}

	/**
	 * Constructor a partir de una excepcion
	 * @param arg0 Excepcion
	 */
	public MailException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * Constructor a partir de un mensaje de error y una excepcion
	 * @param arg0 Mensaje de error 
	 * @param arg1 La excepcion
	 */
	public MailException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
