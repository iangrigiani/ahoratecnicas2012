package ar.com.fi.uba.tecnicas.controlador.mail;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;
import ar.com.fi.uba.tecnicas.modelo.excepciones.MailException;


/**
 * Realiza la conexion con un servidor de mail y obtiene los mensajes
 * @author Sebastian
 */
public class ConexionMail {
	
	private Folder carpeta;
	private List<String> bandejas = new ArrayList<String>();

	public ConexionMail(){
		bandejas.add("INBOX");
		//carpeta = store.getFolder("INBOX"); //POR AHORA SOLO LEEMOS DE INBOX
	}
	
	public void establecerConexionRecepcion(DatosConexion propiedades) throws MailException {
		
		Session sesion = Session.getInstance(propiedades.getDatosPop3());
		
		Store store;
		try {
			//POR AHORA SOLO SOPORTAMOS POP3
			store = sesion.getStore("pop3");
			store.connect(propiedades.getPopNameServer(), propiedades.getMailUser(), propiedades.getMailPass());
			carpeta = store.getFolder("INBOX");
			carpeta.open(Folder.READ_ONLY);
		} catch (NoSuchProviderException e) {
			throw new MailException("No se encontro el proveedor de mail, por favor revise la configuracion.", e);
		} 
		catch (MessagingException e) {
			throw new MailException("No se pudo conectar a la cuenta, revise usuario o contraseña.", e);
		}
				
	}
	
	/**
	 * Obtiene una lista de mensajes
	 * @return La lista de mensajes
	 * @throws MailException En caso de no poder obtener los mensajes
	 */
	public List<Mensaje> getMensaje() throws MailException {
		
		//CREO LA LISTA DE MENSAJES
		
		Message[] mensajesMail;
		try {
			mensajesMail = carpeta.getMessages();
		} catch (MessagingException e) {
			throw new MailException("No se pudieron obtener los mensajes de la carpeta configurada", e);
		}
		
		List<Mensaje> mensajes = new ArrayList<Mensaje>(mensajesMail.length);
		
		for (int i=0;i<mensajesMail.length;i++){
			MailAdapter adaptador=new MailAdapter(mensajesMail[i]);
			Mensaje nuevoMensaje=adaptador.adaptarMail();
			
			//AGREGO EL MENSAJE A LA LISTA 
			mensajes.add(nuevoMensaje);
		}
		
		//DEVUELVO LA LISTA DE MENSAJES
		return mensajes;
	}
}
