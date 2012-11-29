/**
 * 
 */
package ar.com.fi.uba.tecnicas.controlador.mail;

import java.util.ArrayList;
import java.util.List;

import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;
import ar.com.fi.uba.tecnicas.modelo.excepciones.MailException;

/**
 * Interfaz para el servicio de mails
 * @author ramiro
 */
public class ServicioMailMockImpl implements ServicioMail {

	@Override
	public List<Mensaje> getMensajesNuevos() throws MailException {
		List<Mensaje> mensajes = new ArrayList<Mensaje>();
		
		Mensaje msg = new Mensaje();
		msg.setAsunto("[ALTA-MATERIA] 7510-87330-John-Bonachon");
		mensajes.add(msg);
		
		msg = new Mensaje();
		msg.setAsunto("[ALTA-MATERIA] 7510-87330-John, Bonachon");
		msg.setDe("joh@gmail.com");
		mensajes.add(msg);
	
		msg = new Mensaje();
		msg.setAsunto("[ALTA-MATERIA] 7510-83350-Salom, Ramiro");
		msg.setDe("ramiro.salom@gmail.com");
		mensajes.add(msg);

		msg = new Mensaje();
		msg.setAsunto("[ALTA-MATERIA] 7510-83450-Igancio");
		msg.setDe("ignacio@gmail.com");
		mensajes.add(msg);

		msg = new Mensaje();
		msg.setAsunto("[ALTA-MATERIA] 7510-85730-Sebas");
		msg.setDe("sebas@gmail.com");
		mensajes.add(msg);
		
		msg = new Mensaje();
		msg.setAsunto("[ALTA-GRUPO]");
		List<String> pathAdjunto = new ArrayList<String>();
		pathAdjunto.add("home/ramiro/Downloads/grupo_1.txt");		
		mensajes.add(msg);
		
		msg = new Mensaje();
		msg.setAsunto("[ENTREGA-TP] 1");
		msg.setDe("ramiro.salom@gmail.com");
		
		pathAdjunto = new ArrayList<String>();
		pathAdjunto.add("home/ramiro/Downloads/423386_4367426825963_2014888512_n.jpeg");
		
		msg.setPahtAdjunto(pathAdjunto);
		mensajes.add(msg);
		
	
		
		msg = new Mensaje();
		msg.setAsunto("[ENTREGA-TP] 1");
		msg.setDe("ahoratecnicas2012@gmail.com");
		
		pathAdjunto = new ArrayList<String>();
		pathAdjunto.add("home/sebastian/Descargas/oso.jpeg");
		
		msg.setPahtAdjunto(pathAdjunto);
		mensajes.add(msg);
	
		
		msg = new Mensaje();
		msg.setAsunto("[CONSULTA] PUBLICA-Pregunta1");  //"[CONSULTA-PUBLICA/PRIVADA] Tema"
		msg.setDe("ramiro.salom@gmail.com");
		msg.setTextoPlano("Consulta 2");
		mensajes.add(msg);
                
		msg = new Mensaje();
		msg.setAsunto("[RECONSULTA] Pregunta1");  // Para cuando el ticket ya fue creado la consulta se hace así
		msg.setDe("ahoratecnicas2012@gmail.com");
		msg.setTextoPlano("Consulta 3");
		mensajes.add(msg);
		
		msg = new Mensaje();
		msg.setAsunto("Basura");
		mensajes.add(msg);	
		return mensajes;
	}

}
