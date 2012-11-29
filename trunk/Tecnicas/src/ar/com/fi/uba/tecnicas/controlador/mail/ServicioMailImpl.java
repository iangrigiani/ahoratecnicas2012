/**
 * 
 */
package ar.com.fi.uba.tecnicas.controlador.mail;

import java.util.List;

import ar.com.fi.uba.tecnicas.Configuracion;
import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;
import ar.com.fi.uba.tecnicas.modelo.excepciones.MailException;

/**
 * Interfaz para el servicio de mails
 * @author ramiro
 */
public class ServicioMailImpl implements ServicioMail {

	@Override
	public List<Mensaje> getMensajesNuevos() throws MailException {
		DatosConexion datos = new DatosConexion(
									Configuracion.MAIL_USER_NAME,
									Configuracion.MAIL_USER_PASS,
									Configuracion.MAIL_SERVER_NAME_POP);
		
		if (Configuracion.MAIL_CONEXION_SMTP) {
			datos.setDatosConexionSmtp(
					Configuracion.MAIL_SMTP_SERVER_HOST,
					Configuracion.MAIL_SMTP_TLS,
					Configuracion.MAIL_SMTP_PUERTO,
					Configuracion.MAIL_USER_NAME,
					Configuracion.MAIL_SMTP_NEED_PASS);
		} else {
			datos.setDatosConexionPop3(
					Configuracion.MAIL_POP3_TLS,
					Configuracion.MAIL_POP3_PUERTO);
		}
		
		ConexionMail conexionMail = new ConexionMail();
		try {
			conexionMail.establecerConexionRecepcion(datos);
			return conexionMail.getMensaje();
		} catch (MailException e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}

}
