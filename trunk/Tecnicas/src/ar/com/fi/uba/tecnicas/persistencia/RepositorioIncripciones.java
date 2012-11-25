/**
 * 
 */
package ar.com.fi.uba.tecnicas.persistencia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ar.com.fi.uba.tecnicas.Configuracion;
import ar.com.fi.uba.tecnicas.modelo.entidades.Inscripcion;
import ar.com.fi.uba.tecnicas.modelo.excepciones.ValidacionExcepcion;

import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.PersistenceStrategy;
import com.thoughtworks.xstream.persistence.XmlArrayList;

/**
 * @author ramsal
 *
 */
public class RepositorioIncripciones implements Repositorio<Inscripcion> {

	private static final String CARPETA_INSCRIPCION = "/inscripcion";

	private static RepositorioIncripciones INSTANCE = null;

	static {
		File file = new File(Configuracion.DIRECTORIO_PRESISTENCIA_BASE + CARPETA_INSCRIPCION); 
		if (!file.exists()) {
			file.mkdir();
		}
	}
	
	private PersistenceStrategy strategyInscripcion = new FilePersistenceStrategy(new File(Configuracion.DIRECTORIO_PRESISTENCIA_BASE + CARPETA_INSCRIPCION));
	
	@SuppressWarnings("unchecked")
	private List<Inscripcion> inscripicones = new XmlArrayList(strategyInscripcion);

	public static Repositorio<Inscripcion> getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new RepositorioIncripciones();
		}
		return INSTANCE;
	}
	
	private RepositorioIncripciones() {
		
	}
	
	
	@Override
	public Inscripcion obtener(String nombre) {
		List<Inscripcion> elementoInscripcion = obtenerInscripcion(inscripicones, nombre);
		if (elementoInscripcion != null && !elementoInscripcion.isEmpty()) {
			return elementoInscripcion.get(0);
		}
		return null;
	}
	
	@Override
	public void agregar(Inscripcion inscripcion) throws ValidacionExcepcion {
		inscripicones.add((Inscripcion)inscripcion);
	}
	
	@Override
	public void vaciar() {
		inscripicones.clear();
	}

	@Override
	public void quitar(String nombre) {
		
		Inscripcion Inscripcion = obtener(nombre);
		removerMateria(inscripicones, Inscripcion.getPadron());
	}
	
	private void removerMateria(List<? extends Inscripcion> inscripciones, String codigo) {
		Inscripcion c = null;
		for (Inscripcion comp : inscripciones) {
			if (comp.getPadron().equalsIgnoreCase(codigo)) {
				c = comp;
			}	
		}
		inscripciones.remove(c);
	}
	
	private List<Inscripcion> obtenerInscripcion(List<Inscripcion> inscripciones, String padron) {
		List<Inscripcion> materiasByCodigo = new ArrayList<Inscripcion>();
		if (inscripciones != null && !inscripciones.isEmpty()) {
			for (Inscripcion inscripcion : inscripciones) {
				if (inscripcion.getPadron().equals(padron)) {
					materiasByCodigo.add(inscripcion);
				}
			}			
		}
		return materiasByCodigo;
	}

	@Override
	public List<Inscripcion> obtenerTodos() {
		return new ArrayList<Inscripcion>(inscripicones);
	}

	@Override
	public List<Inscripcion> obtenerTodos(String clave) {
		return obtenerInscripcion(inscripicones, clave);
	}
}
