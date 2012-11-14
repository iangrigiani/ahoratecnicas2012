/**
 * 
 */
package ar.com.fi.uba.tecnicas.controlador.mail;

import java.util.List;

import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;

/**
 * Interfaz para el servicio de mails
 * @author ramiro
 */
public interface ServicioMail {

	/**
	 * Obtiene los mensajes nuevos
	 * @return Lista de mensajes nuevos
	 */
	List<Mensaje> getMensajesNuevos();

}
