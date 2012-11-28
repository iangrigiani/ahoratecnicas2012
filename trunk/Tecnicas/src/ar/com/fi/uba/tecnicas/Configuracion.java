package ar.com.fi.uba.tecnicas;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class Configuracion {

	private static Properties properties = null;
	
	static {
		
		properties = new Properties();
		try {
			InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream("configuracion.properties");
			properties.load(systemResourceAsStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String DIRECTORIO_VALIDADOR_PARAMETROS_BASE = properties.getProperty("validador.class.path");
	
	public static String DIRECTORIO_PRESISTENCIA_BASE = properties.getProperty("directorio.persistencia.base");
	
	public static final String DIRECTORIO_ACCIONES_BASE = properties.getProperty("acciones.class.path");
	
	public static String SUBDIR_REGLAS = "/reglas";
	
	public static String SUBDIR_MENSAJES = "/mensajes";
	
	//Configuracion de mail
	public static final Boolean MAIL_CONEXION_SMTP = properties.getProperty("configuracion.server.mail.tipo.conexion.smtp").equalsIgnoreCase("true")? Boolean.TRUE : Boolean.FALSE;
	public static final String MAIL_USER_NAME = properties.getProperty("configuracion.server.mail.user");
	public static final String MAIL_USER_PASS = properties.getProperty("configuracion.server.mail.pass");
	public static final String MAIL_SERVER_NAME_POP = properties.getProperty("configuracion.server.mail.server.name.pop");
	
	//Configuracion de smtp
	public static final String MAIL_SMTP_SERVER_HOST = properties.getProperty("configuracion.server.mail.smtp.host");
	public static final String MAIL_SMTP_PUERTO = properties.getProperty("configuracion.server.mail.smtp.puerto");
	public static final Boolean MAIL_SMTP_TLS = properties.getProperty("configuracion.server.mail.smtp.tls").equalsIgnoreCase("true")? Boolean.TRUE : Boolean.FALSE;
	public static final Boolean MAIL_SMTP_NEED_PASS = properties.getProperty("configuracion.server.mail.smtp.need.pass").equalsIgnoreCase("true")? Boolean.TRUE : Boolean.FALSE;

	//Conexion POP3
	public static final String MAIL_POP3_PUERTO = properties.getProperty("configuracion.server.mail.pop.puerto");
	public static final Boolean MAIL_POP3_TLS = properties.getProperty("configuracion.server.mail.pop.tls").equalsIgnoreCase("true")? Boolean.TRUE : Boolean.FALSE;
	
	
}
