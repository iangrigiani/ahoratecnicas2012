package ar.com.fi.uba.tecnicas.controlador.cadena;

import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;

/**
 * Es el ultimo eslabon de la cadena
 * @author ramiro
 *
 */
public class EslabonFinal extends Eslabon {
	
	public EslabonFinal(String regex) {
		setRegex(regex);
	}
	
	/**
	 * En este metodo realizo el labor que tiene que hacer el eslavon y
	 * sino tengo nada para hacer entonces llamo al otro eslavon
	 * @throws Exception 
	 */
	@Override
	void sendToEslabon(Mensaje mesg) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
