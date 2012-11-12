import java.util.List;


public class ConexionMail {
	
	private Folder carpeta;

	public ConexionMail(){
		carpeta = store.getFolder("INBOX"); //POR AHORA SOLO LEEMOS DE INBOX
	}
	
	public void establecerConexionRecepcion(DatosConexion propiedades){
		
		Session sesion = Session.getInstance(propiedades.getDatosPop3());
		
		Store store = sesion.getStore("pop3"); //POR AHORA SOLO SOPORTAMOS POP3
		store.connect(propiedades.getPopNameServer(),propiedades.getMailUser(),propiedades.getMailPass());
		carpeta.open(Folder.READ_ONLY);
		
				
	}
	
	public List<Mensaje> getMensaje(){
		
		//CREO LA LISTA DE MENSAJES
		
		Message [] mensajesMail = carpeta.getMessages();
		
		for (int i=0;i<mensajesMail.length;i++){
			MailAdapter adaptador=new MailAdapter(mensajesMail[i]);
			Mensaje nuevoMensaje=adaptador.adaptarMail();
			
			//AGREGO EL MENSAJE A LA LISTA 
		}
		
		//DEVUELVO LA LISTA DE MENSAJES
	}
}
