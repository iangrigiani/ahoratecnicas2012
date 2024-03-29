/**
 * 
 */
package ar.com.fi.uba.tecnicas.modelo.entidades.accion;

import java.util.Set;

import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;
import ar.com.fi.uba.tecnicas.modelo.entidades.Parametro;

/**
 * @author ramiro
 *
 */
public class EnviarMail implements Accion {

	/**
	 * @see ar.com.fi.uba.tecnicas.modelo.entidades.accion.Accion#ejecutar(ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje, java.util.List)
	 */
	@Override
	public String ejecutar(Mensaje mensaje, Set<Parametro> parametros) {
		System.out.println("Envio un mail!");
		return "";
	}

	@Override
	public String puedeEjecutar(Mensaje mesg, Set<Parametro> parametrosParaAccion) {
		System.out.println("Chequeo Envio un mail!");
		return "";
	}

}
