package ar.com.fi.uba.tecnicas.controlador.mail;

import java.util.Properties;

/**
 * Contene los datos para establecer la conexion con el servidor de mails 
 * @author Sebastian
 */
public class DatosConexion {
	
	private Properties datosConexionSmtp;
	private Properties datosConexionPop3;
	private String mailPass;
	private String mailUser; // SE USA TANTO AFUERA COMO DENTRO DE LAS PROPIEDADES POR ESO LO NECESITO EN DOS LUGARES
	private String popNameServer;
	
	
	
	public DatosConexion(String dirMail, String passMail, String popServer) {
		datosConexionSmtp=null;
		datosConexionPop3=null;
		mailPass=passMail;
		mailUser=dirMail;
		this.popNameServer=popServer;
	}
	
	public void setDatosConexionSmtp(String newHost, boolean tls, String numeroDePuerto, String mailUsuario, boolean passNeed){
		
		datosConexionSmtp=new Properties();
		
		setHostCorreoSmtp(newHost);
		setTlsSmtp(tls);
		setPuertoEnvioCorreoSmtp(numeroDePuerto);
		setUserSmtp(mailUsuario);
		setIsPasswordNesesarioSmtp(passNeed);
	}
	
	public Properties getDatosSmtp(){
		return this.datosConexionSmtp;
	}
	
	public void setDatosConexionPop3(boolean tls,String puerto){
		datosConexionPop3 = new Properties();
		setIsTlsPop3(tls);
		setSslPop3();
		setPuertoRecepcionCorreoPop3(puerto);
	}
	
	public Properties getDatosPop3(){
		return this.datosConexionPop3;
	}
	
	
	/*-----SETSMTP-----*/
	
	public void setHostCorreoSmtp(String newHost){
		datosConexionSmtp.setProperty("mail.smtp.host", newHost);
		
	}
	
	public void setTlsSmtp (boolean tls){
		
		if (tls==true){
			datosConexionSmtp.setProperty("mail.smtp.starttls.enable", "true");
		}else{
			datosConexionSmtp.setProperty("mail.smtp.starttls.enable", "false");
		}
		
	}
	
	public void setPuertoEnvioCorreoSmtp(String numeroDePuerto){
		
		datosConexionSmtp.setProperty("mail.smtp.port", numeroDePuerto);
		
	}
	
	public void setUserSmtp(String mailUsuario){
		
		datosConexionSmtp.setProperty("mail.smtp.user", mailUsuario);
		mailUser=mailUsuario;
		
	}
	
	public void setIsPasswordNesesarioSmtp(boolean passNeed){
		
		if (passNeed==true){
			datosConexionSmtp.setProperty("mail.smtp.auth", "true");
		}else{
			datosConexionSmtp.setProperty("mail.smtp.auth", "false");
		}
			
	}
	
	/*----SET POP3---*/
	
	public void setIsTlsPop3(boolean tls){
		
		if (tls==true){
			datosConexionPop3.setProperty("mail.pop3.starttls.enable", "true");
		}else{
			datosConexionPop3.setProperty("mail.pop3.starttls.enable", "false");
		}
		
	};
	
	public void setSslPop3(){
		//USA SIEMPRE ESTAS OPCIONES SEGUN VI
			
		datosConexionPop3.setProperty("mail.pop3.socketFactory.class","javax.net.ssl.SSLSocketFactory" );
		datosConexionPop3.setProperty("mail.pop3.socketFactory.fallback", "false");
	}
	
	public void setPuertoRecepcionCorreoPop3(String puerto){
		
		datosConexionPop3.setProperty("mail.pop3.port",puerto);
		datosConexionPop3.setProperty("mail.pop3.socketFactory.port", puerto);
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
	
	public String getPopNameServer(){
		return this.popNameServer;
	}
};