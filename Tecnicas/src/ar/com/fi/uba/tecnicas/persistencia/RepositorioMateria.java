package ar.com.fi.uba.tecnicas.persistencia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ar.com.fi.uba.tecnicas.Configuracion;
import ar.com.fi.uba.tecnicas.controlador.comun.Mensajes;
import ar.com.fi.uba.tecnicas.modelo.entidades.Materia;
import ar.com.fi.uba.tecnicas.modelo.excepciones.ValidacionExcepcion;

import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.PersistenceStrategy;
import com.thoughtworks.xstream.persistence.XmlArrayList;

public class RepositorioMateria implements Repositorio<Materia> {

	private static final String CARPETA_MATERIA = "/materia";

	private static RepositorioMateria INSTANCE = null;
	
	static {
		File file = new File(Configuracion.DIRECTORIO_PRESISTENCIA_BASE + CARPETA_MATERIA); 
		if (!file.exists()) {
			file.mkdir();
		}
	}
	
	private PersistenceStrategy strategyMaterias = new FilePersistenceStrategy(new File(Configuracion.DIRECTORIO_PRESISTENCIA_BASE + CARPETA_MATERIA));
	
	@SuppressWarnings("unchecked")
	private List<Materia> materias = new XmlArrayList(strategyMaterias);

	private RepositorioMateria() {
		
	}
	
	@Override
	public Materia obtener(String nombre) {
		List<Materia> elementoMateria = obtenerMateria(materias, nombre);
		if (elementoMateria != null && !elementoMateria.isEmpty()) {
			return elementoMateria.get(0);
		}
		return null;
	}
	
	@Override
	public void agregar(Materia materia) throws ValidacionExcepcion {
		if (materias.size() > 0) {
			throw new ValidacionExcepcion(Mensajes.REPOSITORIO_YA_EXISTE_MATERIA);
		}
		materias.add((Materia)materia);
	}
	
	@Override
	public void vaciar() {
		materias.clear();
	}

	@Override
	public void quitar(String nombre) {
		
		Materia materia = obtener(nombre);
		removerMateria(materias, materia.getCodigo());
	}
	
	private void removerMateria(List<? extends Materia> materias, String codigo) {
		Materia c = null;
		for (Materia comp : materias) {
			if (comp.getCodigo().equalsIgnoreCase(codigo)) {
				c = comp;
			}	
		}
		materias.remove(c);
	}
	
	private List<Materia> obtenerMateria(List<? extends Materia> materias, String codigo) {
		List<Materia> materiasByCodigo = new ArrayList<Materia>();
		if (materias != null && !materias.isEmpty()) {
			for (Materia materia : materias) {
				if (materia.getCodigo().equals(codigo)) {
					materiasByCodigo.add(materia);
				}
			}			
		}
		return materiasByCodigo;
	}

	@Override
	public List<Materia> obtenerTodos() {
		return new ArrayList<Materia>(materias);
	}

	@Override
	public List<Materia> obtenerTodos(String clave) {
		return obtenerMateria(materias, clave);
	}

	public static Repositorio<Materia> getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new RepositorioMateria();
		}
		return INSTANCE;
	}
	
}
