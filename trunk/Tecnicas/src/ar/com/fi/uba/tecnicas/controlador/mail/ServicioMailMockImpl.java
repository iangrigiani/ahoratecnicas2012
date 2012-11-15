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
		msg.setAsunto("[ALTA-MATERIA] 7510-87330-Juan");
		mensajes.add(msg);
		
		msg = new Mensaje();
		msg.setAsunto("[ENTREGA-TP] 1");
		mensajes.add(msg);
		
		msg = new Mensaje();
		msg.setAsunto("[CONSULTA] PUBLICA-Pregunta1");  //"[CONSULTA-PUBLICA/PRIVADA] Tema"
		mensajes.add(msg);
                
		msg = new Mensaje();
		msg.setAsunto("[RECONSULTA] Pregunta1");  // Para cuando el ticket ya fue creado la consulta se hace así
		mensajes.add(msg);
		
		msg = new Mensaje();
		msg.setAsunto("Basura");
		mensajes.add(msg);	
		return mensajes;
	}

}
