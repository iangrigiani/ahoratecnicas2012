/**
 * 
 */
package ar.com.fi.uba.tecnicas.persistencia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ar.com.fi.uba.tecnicas.Configuracion;
import ar.com.fi.uba.tecnicas.modelo.entidades.Alumno;
import ar.com.fi.uba.tecnicas.modelo.excepciones.ValidacionExcepcion;

import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.PersistenceStrategy;
import com.thoughtworks.xstream.persistence.XmlArrayList;

/**
 * @author ramsal
 *
 */
public class RepositorioAlumno implements Repositorio<Alumno> {

	private static final String CARPETA_ALUMNO = "/alumno";

	private static RepositorioAlumno INSTANCE = null;

	static {
		File file = new File(Configuracion.DIRECTORIO_PRESISTENCIA_BASE + CARPETA_ALUMNO); 
		if (!file.exists()) {
			file.mkdir();
		}
	}
	
	private PersistenceStrategy strategyAlumno = new FilePersistenceStrategy(new File(Configuracion.DIRECTORIO_PRESISTENCIA_BASE + CARPETA_ALUMNO));
	
	@SuppressWarnings("unchecked")
	private List<Alumno> alumnos = new XmlArrayList(strategyAlumno);

	@Override
	public Alumno obtener(String nombre) {
		List<Alumno> elementoAlumno = obtenerAlumno(alumnos, nombre);
		if (elementoAlumno != null && !elementoAlumno.isEmpty()) {
			return elementoAlumno.get(0);
		}
		return null;
	}
	
	@Override
	public void agregar(Alumno alumno) throws ValidacionExcepcion {
		alumnos.add((Alumno)alumno);
	}
	
	@Override
	public void vaciar() {
		alumnos.clear();
	}

	@Override
	public void quitar(String nombre) {
		
		Alumno alumno = obtener(nombre);
		removerMateria(alumnos, alumno.getPadron());
	}
	
	private void removerMateria(List<? extends Alumno> alumnos, String codigo) {
		Alumno c = null;
		for (Alumno comp : alumnos) {
			if (comp.getPadron().equalsIgnoreCase(codigo)) {
				c = comp;
			}	
		}
		alumnos.remove(c);
	}
	
	private List<Alumno> obtenerAlumno(List<Alumno> alumnos, String padron) {
		List<Alumno> materiasByCodigo = new ArrayList<Alumno>();
		if (alumnos != null && !alumnos.isEmpty()) {
			for (Alumno alumno : alumnos) {
				if (alumno.getPadron().equals(padron)) {
					materiasByCodigo.add(alumno);
				}
			}			
		}
		return materiasByCodigo;
	}

	public static Repositorio<Alumno> getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new RepositorioAlumno();
		}
		return INSTANCE;
	}
	
	private RepositorioAlumno() {
		
	}
	
	@Override
	public List<Alumno> obtenerTodos() {
		return new ArrayList<Alumno>(alumnos);
	}

	@Override
	public List<Alumno> obtenerTodos(String clave) {
		return obtenerAlumno(alumnos, clave);
	}
}
