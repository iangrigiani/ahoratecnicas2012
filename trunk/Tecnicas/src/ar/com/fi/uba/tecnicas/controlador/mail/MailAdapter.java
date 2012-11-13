package ar.com.fi.uba.tecnicas.controlador.mail;

import javax.mail.Message;

import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;

/**
 * Convierte un mail en un mensaje
 * @author Sebastian
 */
public class MailAdapter {

	@SuppressWarnings("unused")
	private Message mensajesMail;
	
	/**
	 * Constructor
	 */
	public MailAdapter(){
		
	}
	
	/**
	 * Constructor a partir de un mensaje
	 * @param mensajesMail
	 */
	public MailAdapter(Message mensajesMail){
		this.mensajesMail = mensajesMail;
	}
	
	/**
	 * Adapto el mail a un mensaje
	 * @return El mensaje
	 */
	public Mensaje adaptarMail() {
		
		Mensaje nuevoMensaje = new Mensaje();
		
		//REALIZO LA ADAPTACION DEL MENSAJE
		
		return nuevoMensaje;
	}

	/**
	 * @param mensajesMail the mensajesMail to set
	 */
	public void setMensajesMail(Message mensajesMail) {
		this.mensajesMail = mensajesMail;
	}
}
