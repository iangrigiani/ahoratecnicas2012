package ar.com.fi.uba.tecnicas.controlador.mail;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;

import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;
import ar.com.fi.uba.tecnicas.modelo.excepciones.MailException;

/**
 * Convierte un mail en un mensaje
 * @author Sebastian
 */
public class MailAdapter {

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
	 * @throws MessagingException 
	 */
	public Mensaje adaptarMail() throws MessagingException {
		
		Mensaje nuevoMensaje = new Mensaje();
		Address [] arregloDeDirrecciones = this.mensajesMail.getAllRecipients();
		
		nuevoMensaje.setAsunto(this.mensajesMail.getSubject());
		nuevoMensaje.setDe(((InternetAddress) this.mensajesMail.getFrom()[0]).getAddress());

		for (int i=0;i<arregloDeDirrecciones.length;i++){
			nuevoMensaje.agregarPara(arregloDeDirrecciones[i].toString());
		}
		
		analizaParteDeMensaje(this.mensajesMail,nuevoMensaje);
		//REALIZO LA ADAPTACION DEL MENSAJE
		
		return nuevoMensaje;
	}
	
	private void salvaUnFichero(Part unaParte,Mensaje myMensaje) throws MailException {
			   
	    FileOutputStream fichero;
		try {
			fichero = new FileOutputStream("./" + unaParte.getFileName());
			//TODO VER EL TEMA DE CAMBIAR LA UBICACION DEL PATH
			myMensaje.agregarPathAdjunto("./" + unaParte.getFileName());
			   
			InputStream imagen = unaParte.getInputStream();
			byte[] bytes = new byte[1000];
			int leidos = 0;
			
			while ((leidos = imagen.read(bytes)) > 0){
				fichero.write(bytes, 0, leidos);
			}
		} catch (FileNotFoundException e) {
			throw new MailException("Archivo no encontrado", e);
		} catch (MessagingException e) {
			throw new MailException("Archivo no encontrado", e);
		} catch (IOException e) {
			throw new MailException("Error al escribir", e);
		}
	}
	
	private void analizaParteDeMensaje(Part unaParte,Mensaje myMensaje){
	   try {
	     // Si es multipart, se analiza cada una de sus partes recursivamente.
		   if (unaParte.isMimeType("multipart/*")) {
			   Multipart multi;
		       multi = (Multipart) unaParte.getContent();
		
		       for (int j = 0; j < multi.getCount(); j++) {
		           analizaParteDeMensaje(multi.getBodyPart(j),myMensaje);
		       }
		       
		   } else {
			   // Si es texto, se escribe el texto.
			   if (unaParte.isMimeType("text/*"))  {
			       myMensaje.agregarTextoPlano((String)unaParte.getContent());
			   } else {
				   salvaUnFichero(unaParte, myMensaje);
			   }
		   }
       } catch (Exception e) {
           e.printStackTrace();
       }
	}
	
	/**
	 * @param mensajesMail the mensajesMail to set
	 */
	public void setMensajesMail(Message mensajesMail) {
		this.mensajesMail = mensajesMail;
	}
}
