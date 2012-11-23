package ar.com.fi.uba.tecnicas.persistencia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ar.com.fi.uba.tecnicas.Configuracion;
import ar.com.fi.uba.tecnicas.modelo.entidades.AlumnoGrupo;
import ar.com.fi.uba.tecnicas.modelo.excepciones.ValidacionExcepcion;

import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.PersistenceStrategy;
import com.thoughtworks.xstream.persistence.XmlArrayList;

public class RepositorioAlumnoGrupo implements Repositorio<AlumnoGrupo> {

	private static final String CARPETA_ALUMNO_GRUPO = "/alumno_grupo";

	static {
		File file = new File(Configuracion.DIRECTORIO_PRESISTENCIA_BASE + CARPETA_ALUMNO_GRUPO); 
		if (!file.exists()) {
			file.mkdir();
		}
	}
	
	private PersistenceStrategy strategyAlumnoGrupo = new FilePersistenceStrategy(new File(Configuracion.DIRECTORIO_PRESISTENCIA_BASE + CARPETA_ALUMNO_GRUPO));
	
	@SuppressWarnings("unchecked")
	private List<AlumnoGrupo> alumnoGrupos = new XmlArrayList(strategyAlumnoGrupo);

	@Override
	public AlumnoGrupo obtener(String nombre) {
		List<AlumnoGrupo> elementoAlumnoGrupo = obtenerAlumnoGrupoPorPadron(alumnoGrupos, nombre);
		if (elementoAlumnoGrupo != null && !elementoAlumnoGrupo.isEmpty()) {
			return elementoAlumnoGrupo.get(0);
		}
		return null;
	}
	
	@Override
	public void agregar(AlumnoGrupo grupo) throws ValidacionExcepcion {
		if (alumnoGrupos.size() > 0) {
			throw new ValidacionExcepcion("Ya existe una grupo en el sistema.");
		}
		alumnoGrupos.add((AlumnoGrupo)grupo);
	}
	
	@Override
	public void vaciar() {
		alumnoGrupos.clear();
	}

	@Override
	public void quitar(String nombre) {
		
		AlumnoGrupo grupo = obtener(nombre);
		removerAlumnoGrupo(alumnoGrupos, grupo);
	}
	
	private void removerAlumnoGrupo(List<? extends AlumnoGrupo> grupos, AlumnoGrupo grupo) {
		grupos.remove(grupo);
	}
	
	private List<AlumnoGrupo> obtenerAlumnoGrupoPorPadron(List<? extends AlumnoGrupo> grupos, String codigo) {
		List<AlumnoGrupo> gruposByCodigo = new ArrayList<AlumnoGrupo>();
		if (grupos != null && !grupos.isEmpty()) {
			for (AlumnoGrupo grupo : grupos) {
				if (grupo.getPadron().equals(codigo)) {
					gruposByCodigo.add(grupo);
				}
			}			
		}
		return gruposByCodigo;
	}

	@Override
	public List<AlumnoGrupo> obtenerTodos() {
		return new ArrayList<AlumnoGrupo>(alumnoGrupos);
	}

	@Override
	public List<AlumnoGrupo> obtenerTodos(String clave) {
		return obtenerAlumnoGrupoPorPadron(alumnoGrupos, clave);
	}
}
