/**
 * 
 */
package ar.com.fi.uba.tecnicas.modelo.entidades.accion;

import java.util.ArrayList;
import java.util.Set;

import ar.com.fi.uba.tecnicas.controlador.comun.Mensajes;
import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;
import ar.com.fi.uba.tecnicas.modelo.entidades.Parametro;
import ar.com.fi.uba.tecnicas.modelo.entidades.Ticket;
import ar.com.fi.uba.tecnicas.modelo.excepciones.ValidacionExcepcion;
import ar.com.fi.uba.tecnicas.persistencia.RepositorioBuilder;

/**
 * @author nacho
 * Parsea el txt de los padrones
 * Chequea que los padrones sean validos
 * Chequea que los padrones existan en la materia en el cuatrimestre
 * Chequea que los padrones NO pertenezcan ya a algun grupo
 * 
 *
 */
public class CrearTicket implements Accion {

	/**
	 * @see ar.com.fi.uba.tecnicas.modelo.entidades.accion.Accion#ejecutar(ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje, java.util.List)
	 */
	@Override
	public String ejecutar(Mensaje mensaje, Set<Parametro> parametros) {
		
		RepositorioBuilder repo = new RepositorioBuilder();
		
		Ticket ticket = new Ticket();
		ticket.setMensajes(new ArrayList<Mensaje>());
		ticket.getMensajes().add(mensaje);
		
		for (Parametro parametro : parametros) {
			if (parametro.getValidador().getDescripcion().equals("PRIVACIDAD")) {
				ticket.setPrivada(parametro.getValor().equalsIgnoreCase("PUBLICA")? Boolean.FALSE : Boolean.TRUE);
			}
			if (parametro.getValidador().getDescripcion().equals("NOMBRE_ALUMNO")) {
				ticket.setTitulo(parametro.getValor());
			}
		}
		try {
			repo.getRepositorioTickets().agregar(ticket);
		} catch (ValidacionExcepcion e) {
			return Mensajes.ACCION_CREAR_TICKET;
		}
		return "";
	}
     
	@Override
	public String puedeEjecutar(Mensaje mesg, Set<Parametro> parametrosParaAccion) {
		return "";
	}

}

