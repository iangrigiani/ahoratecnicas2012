import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.text.StyledEditorKit.BoldAction;

/*ESTA CLASE GUARDA LOS DATOS DE LAS PARA ESTABLECER LA CONECION*/

public class ConeccionMail {
	
	private Properties datosConeccionSmtp;
	private Properties datosConeccionPop3;
	private String mailPass;
	private String mailUser; // SE USA TANTO AFUERA COMO DENTRO DE LAS PROPIEDADES POR ESO LO NECESITO EN DOS LUGARES
	
	
	public  ConeccionMail() {
		datosConeccionSmtp=null;
		datosConeccionPop3=null;
		mailPass="";
		mailUser="";
	}
	
	public ConeccionMail(String newHost,boolean tls,String numeroDePuerto,String mailUsuario,boolean passNeed){
		
		datosConeccionSmtp=new Properties();
		
		setHostCorreoSmtp(newHost);
		setTlsSmtp (tls);
		setPuertoEnvioCorreoSmtp(numeroDePuerto);
		setUserSmtp(mailUsuario);
		setIsPasswordNesesarioSmtp(passNeed);
	}
	
	/*-----SETSMTP-----*/
	
	public void setHostCorreoSmtp(String newHost){
		
		datosConeccionSmtp.setProperty("mail.smtp.host", newHost);
		
	}
	
	public void setTlsSmtp (boolean tls){
		
		if (tls==true){
			datosConeccionSmtp.setProperty("mail.smtp.starttls.enable", "true");
		}else{
			datosConeccionSmtp.setProperty("mail.smtp.starttls.enable", "false");
		}
		
	}
	
	public void setPuertoEnvioCorreoSmtp(String numeroDePuerto){
		
		datosConeccionSmtp.setProperty("mail.smtp.port", numeroDePuerto);
		
	}
	
	public void setUserSmtp(String mailUsuario){
		
		datosConeccionSmtp.setProperty("mail.smtp.user", mailUsuario);
		mailUser=mailUsuario;
		
	}
	
	public void setIsPasswordNesesarioSmtp(boolean passNeed){
		
		if (passNeed==true){
			datosConeccionSmtp.setProperty("mail.smtp.auth", "true");
		}else{
			datosConeccionSmtp.setProperty("mail.smtp.auth", "false");
		}
			
	}
	
	/*----SET POP3---*/
	
	public void setIsTlsPop3(boolean tls){
		
		if (tls==true){
			datosConeccionPop3.setProperty("mail.pop3.starttls.enable", "true");
		}else{
			datosConeccionPop3.setProperty("mail.pop3.starttls.enable", "false");
		}
		
	};
	
	public void setSslPop3(String socketsSllNombre){
		
		datosConeccionPop3.setProperty("mail.pop3.starttls.enable", "true");
	}
	
	public void setPuertoRecepcionCorreoPop3(String puerto){
		
		datosConeccionPop3.setProperty("mail.pop3.port",puerto);
		datosConeccionPop3.setProperty("mail.pop3.socketFactory.port", puerto);
	}
	
	/*---SET Y GET OTROS ---*/
	
	public void setMailPass(String pass){
			
		this.mailPass=pass;
	}
	
	public String getMailPass(){
		
		return this.mailPass;
	}
	
	public String getMailUser(){
		
		return this.mailUser;
	}
};