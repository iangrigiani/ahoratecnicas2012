package ar.com.fi.uba.tecnicas;

import ar.com.fi.uba.tecnicas.controlador.ComandosUsuarioFacade;
import ar.com.fi.uba.tecnicas.vista.Consola;

/**
 * Esta clase es de ejemplo.
 */
public final class Main {

	/**
	 * El constructor lo agrego para que checkstyle no me rompa las bolas.
	 */
	private Main() {
		super();
	}
	
	/**
	 * Logger para la clase.
	 */
	//private static Logger logger = Logger.getLogger(Main.class);

	/**
	 * @param args Los argumentos del programa.
	 */
	public static void main(final String[] args) {
		
		try {
			
			Consola consola = new Consola(ComandosUsuarioFacade.class);
			
			consola.start();
			
			consola.join();
			
			

		} catch (Exception e) {
			//logger.error("Error en main: " + e.getMessage());
		}

	}

}
