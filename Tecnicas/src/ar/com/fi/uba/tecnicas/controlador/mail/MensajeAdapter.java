package ar.com.fi.uba.tecnicas.controlador.mail;

import java.util.Iterator;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;

//TRANSFORMA UN MENSAJE EN UN MimeMessage

public class MensajeAdapter {
	

	private Mensaje mensajesToAdapt;
	
	
	/**
	 * Constructor
	 */
	public MensajeAdapter(){
	}
	
	/**
	 * Constructor a partir de un mensaje
	 * @param mensajesMail
	 */
	public MensajeAdapter(Mensaje mensajesMail){
		this.mensajesToAdapt = mensajesMail;
	}
	
	/**
	 * Adapto el mail a un mensaje
	 * @return El mensaje
	 * @throws MessagingException 
	 */
	public MimeMessage adaptarMensaje(Session sessionSmtp) throws MessagingException {
				
		// Una MultiParte para agrupar texto e imagen.
        MimeMultipart multiParte = new MimeMultipart();
        
        
		
		 // Se compone la parte del texto
        BodyPart texto = new MimeBodyPart();
        texto.setText(this.mensajesToAdapt.getTextoPlano());
        multiParte.addBodyPart(texto);
        
        // Se compone el adjunto con la imagen
        BodyPart adjunto = new MimeBodyPart();
        
        List<String> paths=mensajesToAdapt.getPathAdjunto();
        
        @SuppressWarnings("rawtypes")
		Iterator iter = paths.iterator();
        while (iter.hasNext())
        {
           String aPath = (String) iter.next();
           adjunto.setDataHandler(
                   new DataHandler(new FileDataSource(aPath)));
           adjunto.setFileName(aPath.substring(aPath.lastIndexOf('/') + 1));
           multiParte.addBodyPart(adjunto);
        }
        
        
        
        MimeMessage mensajesMail = new MimeMessage(sessionSmtp);
        mensajesMail.setFrom(new InternetAddress((String)this.mensajesToAdapt.getDe()));
        mensajesMail.setSubject(this.mensajesToAdapt.getAsunto());
        
        List<String> para=mensajesToAdapt.getPara();
        
        @SuppressWarnings("rawtypes")
		Iterator iterPara = para.iterator();
        
        while (iterPara.hasNext())
        {
           String aPara = (String) iterPara.next();
           mensajesMail.addRecipient( Message.RecipientType.TO,new InternetAddress(aPara));
           
        }
        
        mensajesMail.setContent(multiParte);
             
		return mensajesMail;
	}


	
}
