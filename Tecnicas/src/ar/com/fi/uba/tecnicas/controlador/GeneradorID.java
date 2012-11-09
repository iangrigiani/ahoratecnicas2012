package ar.com.fi.uba.tecnicas.controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import ar.com.fi.uba.tecnicas.Configuracion;

import com.thoughtworks.xstream.XStream;

public class GeneradorID {
	
	private static int idComponentes = 1;

	private static File file = new File(Configuracion.DIRECTORIO_PRESISTENCIA_BASE + "/id.xml");
	private static XStream xstream = new XStream();
	
	public static int getIdComponente() {
		
		FileOutputStream out = null;
		FileInputStream in = null;
		
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		if (file.length() > 0) {
			idComponentes = (Integer)xstream.fromXML(in);
		}
		
		try {
			out = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		idComponentes = idComponentes + 1;
		
		xstream.toXML(idComponentes, out);
		
		try {
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return idComponentes;
	}

}
