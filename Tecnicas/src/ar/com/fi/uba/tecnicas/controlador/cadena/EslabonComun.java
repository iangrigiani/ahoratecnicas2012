package ar.com.fi.uba.tecnicas.controlador.cadena;

import java.util.List;

import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;
import ar.com.fi.uba.tecnicas.modelo.entidades.accion.Accion;

/**
 * Es el ultimo eslabon de la cadena
 * @author ramiro
 *
 */
public class EslabonComun extends Eslabon {
	
	public EslabonComun() {
	}
	
	/**
	 * En este metodo realizo el labor que tiene que hacer el eslavon y
	 * sino tengo nada para hacer entonces llamo al otro eslavon
	 * @throws Exception 
	 */
	@Override
	public void sendToEslabon(Mensaje mesg) throws Exception {
		if (getRegla().validar(mesg)) {
			getRegla().procesar(mesg);
		} else {
			getEslabon().sendToEslabon(mesg);
		}
	}

}
