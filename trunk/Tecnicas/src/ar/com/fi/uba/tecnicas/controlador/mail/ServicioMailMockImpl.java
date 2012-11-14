/**
 * 
 */
package ar.com.fi.uba.tecnicas.controlador.mail;

import java.util.ArrayList;
import java.util.List;

import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;

/**
 * Interfaz para el servicio de mails
 * @author ramiro
 */
public class ServicioMailMockImpl implements ServicioMail {

	@Override
	public List<Mensaje> getMensajesNuevos() {
		List<Mensaje> mensajes = new ArrayList<Mensaje>();
		
		Mensaje msg = new Mensaje();
		msg.setAsunto("[ALTA-MATERIA-CODIGO] PADRON-NOMBRE");
		mensajes.add(msg);
		
		msg = new Mensaje();
		msg.setAsunto("[ENTREGA-TP-NUMERO]");
		mensajes.add(msg);
		
		msg = new Mensaje();
		msg.setAsunto("[CONSULTA-PUBLICA/PRIVADA] Tema");
		mensajes.add(msg);	
		
		msg = new Mensaje();
		msg.setAsunto("Basura");
		mensajes.add(msg);	
		return mensajes;
	}

}
