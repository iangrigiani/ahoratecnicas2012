/**
 * 
 */
package ar.com.fi.uba.tecnicas.modelo.entidades.accion;

import java.io.File;
import java.util.Set;

import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;
import ar.com.fi.uba.tecnicas.modelo.entidades.Parametro;

/**
 * @author nacho
 * chequea que el mensaje tenga un adjunto
 *
 */
public class ExisteAdjunto implements Accion {

	/**
	 * @see ar.com.fi.uba.tecnicas.modelo.entidades.accion.Accion#ejecutar(ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje, java.util.List)
	 */
	@Override
	public String ejecutar(Mensaje mensaje, Set<Parametro> parametros) {
		return existenAdjuntos(mensaje);
	}

	@Override
	public String puedeEjecutar(Mensaje mesg, Set<Parametro> parametrosParaAccion) {
		return existenAdjuntos(mesg);
	}

	private String existenAdjuntos(Mensaje mesg) {
		if (mesg.getPathAdjunto() == null || mesg.getPathAdjunto().isEmpty()) {
			return "No contiene adjuntos el mail";
		}
		for (String adjunto : mesg.getPathAdjunto()) {
			File file = new File(adjunto);
			if (!file.exists()) {
				return "El adjunto " + adjunto + " no exite.";
			}
		}
		
		return "";
	}

}
