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
	
}
