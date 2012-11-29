package ar.com.fi.uba.tecnicas.persistencia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ar.com.fi.uba.tecnicas.Configuracion;
import ar.com.fi.uba.tecnicas.modelo.entidades.Grupo;
import ar.com.fi.uba.tecnicas.modelo.excepciones.ValidacionExcepcion;

import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.PersistenceStrategy;
import com.thoughtworks.xstream.persistence.XmlArrayList;

public class RepositorioGrupo implements Repositorio<Grupo> {

	private static final String CARPETA_GRUPO = "/grupo";

	private static RepositorioGrupo INSTANCE = null;

	static {
		File file = new File(Configuracion.DIRECTORIO_PRESISTENCIA_BASE + CARPETA_GRUPO); 
		if (!file.exists()) {
			file.mkdir();
		}
	}
	
	private PersistenceStrategy strategyGrupo = new FilePersistenceStrategy(new File(Configuracion.DIRECTORIO_PRESISTENCIA_BASE + CARPETA_GRUPO));
	
	@SuppressWarnings("unchecked")
	private List<Grupo> grupos = new XmlArrayList(strategyGrupo);

	public static Repositorio<Grupo> getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new RepositorioGrupo();
		}
		return INSTANCE;
	}
	
	private RepositorioGrupo() {
		
	}
	
	@Override
	public Grupo obtener(String nombre) {
		List<Grupo> elementoGrupo = obtenerGrupo(grupos, nombre);
		if (elementoGrupo != null && !elementoGrupo.isEmpty()) {
			return elementoGrupo.get(0);
		}
		return null;
	}
	
	@Override
	public void agregar(Grupo grupo) throws ValidacionExcepcion {
		if (grupos.contains(grupo)) {
			throw new ValidacionExcepcion("Ya existe una grupo en el sistema.");
		}
		grupos.add((Grupo)grupo);
	}
	
	@Override
	public void vaciar() {
		grupos.clear();
	}

	@Override
	public void quitar(String nombre) {
		
		Grupo grupo = obtener(nombre);
		removerGrupo(grupos, grupo.getCodigo());
	}
	
	private void removerGrupo(List<? extends Grupo> grupos, String codigo) {
		Grupo c = null;
		for (Grupo comp : grupos) {
			if (comp.getCodigo().equalsIgnoreCase(codigo)) {
				c = comp;
			}	
		}
		grupos.remove(c);
	}
	
	private List<Grupo> obtenerGrupo(List<? extends Grupo> grupos, String codigo) {
		List<Grupo> gruposByCodigo = new ArrayList<Grupo>();
		if (grupos != null && !grupos.isEmpty()) {
			for (Grupo grupo : grupos) {
				if (grupo.getCodigo().equals(codigo)) {
					gruposByCodigo.add(grupo);
				}
			}			
		}
		return gruposByCodigo;
	}

	@Override
	public List<Grupo> obtenerTodos() {
		return new ArrayList<Grupo>(grupos);
	}

	@Override
	public List<Grupo> obtenerTodos(String clave) {
		return obtenerGrupo(grupos, clave);
	}
}
