package ar.com.fi.uba.tecnicas.prueba.mail;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

import ar.com.fi.uba.tecnicas.controlador.mail.DatosConexion;
import ar.com.fi.uba.tecnicas.controlador.mail.MensajeAdapter;
import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;

public class TestMail {
	
	
	@Test
	public void testDatosConexionSmtp(){
		DatosConexion DatosSmtp = new DatosConexion("prueba@prueba.com","1234", "pop.server");
		
		DatosSmtp.setDatosConexionSmtp("smtp.Host", true,"666", "prueba@prueba.com", true);
		
		//PARA LA COMPARACION OBTENGO LAS PROPERTIES SMTP
		
		Properties mySmtpProps= DatosSmtp.getDatosSmtp();
				
		assertEquals("prueba@prueba.com",DatosSmtp.getMailUser());
		assertEquals("1234",DatosSmtp.getMailPass());
		
		assertEquals("smtp.Host",(String) mySmtpProps.get("mail.smtp.host"));
		assertEquals("prueba@prueba.com",(String) mySmtpProps.get("mail.smtp.user"));
		assertEquals("666",(String) mySmtpProps.get("mail.smtp.port"));
				
		assertEquals("true",(String) mySmtpProps.get("mail.smtp.starttls.enable"));
		assertEquals("true",(String) mySmtpProps.get("mail.smtp.auth"));
		
	}
	
	@Test
	public void testDatosConexionPop(){
		DatosConexion DatosPop = new DatosConexion("prueba@prueba.com","1234", "pop.server");
		
		DatosPop.setDatosConexionPop3(false,"666");
		
		//PARA LA COMPARACION OBTENGO LAS PROPERTIES POP
		
		Properties myPop3Props= DatosPop.getDatosPop3();
		
		assertEquals("javax.net.ssl.SSLSocketFactory",(String) myPop3Props.get("mail.pop3.socketFactory.class"));
		assertEquals("false",(String) myPop3Props.get("mail.pop3.socketFactory.fallback"));
		assertEquals("false",(String) myPop3Props.get("mail.pop3.starttls.enable"));
		assertEquals("666",(String) myPop3Props.get("mail.pop3.port"));
		assertEquals("666",(String) myPop3Props.get("mail.pop3.socketFactory.port"));
	}
	
	@Test 
	public void testadaptarUnMail(){
		
		//SE PROBARA LA CLASE MAIL ADAPTER
		
	}
	
	@Test
	public void testAdaptarUnMensaje(){
		
		MimeMessage nuevoMail;
		Mensaje myMensaje= new Mensaje();
		
		myMensaje.setAsunto("PRUEBA");
		myMensaje.setDe("ahoratecnicas2012@gmail.com");
		myMensaje.agregarPara("ahoratecnicas2012@gmail.com");
		myMensaje.agregarTextoPlano("Esto Es una Prueba");
		myMensaje.agregarPathAdjunto("home/sebastian/Descargas/oso.jpeg");
		
		//para el adpater necesito una sesion, no se como hacer la prueba unitaria!
		
		DatosConexion pruebaConexion= new DatosConexion("ahoratecnicas2012@gmail.com", "tecnicas123456789", "pop.gmail.com");
		pruebaConexion.setDatosConexionSmtp("smtp.gmail.com", true, "587", "ahoratecnicas2012@gmail.com", true);
		Session session = Session.getDefaultInstance(pruebaConexion.getDatosSmtp());
		
		
		MensajeAdapter mensajeAMail=new MensajeAdapter(myMensaje);
		
		try {
			
			nuevoMail=mensajeAMail.adaptarMensaje(session);
			
			assertEquals("ahoratecnicas2012@gmail.com",nuevoMail.getFrom()[0].toString());
			assertEquals("PRUEBA",nuevoMail.getSubject());
			assertEquals("ahoratecnicas2012@gmail.com",nuevoMail.getAllRecipients()[0].toString());
			
				
			//tomo las partes
				
			Multipart multi;
            multi = (Multipart) nuevoMail.getContent();
                
            Part unaParte=multi.getBodyPart(0);
				
            assertEquals("Esto Es una Prueba",unaParte.getContent().toString());
                
            unaParte=multi.getBodyPart(1);
            assertEquals("oso.jpeg",unaParte.getFileName());
			      	
	        
			
		} catch (Exception  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}

}
