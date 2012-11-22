package ar.com.fi.uba.tecnicas.persistencia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ar.com.fi.uba.tecnicas.Configuracion;
import ar.com.fi.uba.tecnicas.modelo.entidades.Regla;
import ar.com.fi.uba.tecnicas.modelo.excepciones.ValidacionExcepcion;

import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.PersistenceStrategy;
import com.thoughtworks.xstream.persistence.XmlArrayList;

public class RepositorioReglas implements Repositorio<Regla> {

	private static final String DIRECTORIO_REGLAS = "/reglas";

	static {
		File file = new File(Configuracion.DIRECTORIO_PRESISTENCIA_BASE + DIRECTORIO_REGLAS); 
		if (!file.exists()) {
			file.mkdir();
		}
	}
	
	private PersistenceStrategy strategyReglas = new FilePersistenceStrategy(new File(Configuracion.DIRECTORIO_PRESISTENCIA_BASE + DIRECTORIO_REGLAS));
	
	@SuppressWarnings("unchecked")
	private List<Regla> reglas = new XmlArrayList(strategyReglas);

	@Override
	public Regla obtener(String nombre) {
		List<Regla> elementoRegla = obtenerRegla(reglas, nombre);
		if (elementoRegla != null && !elementoRegla.isEmpty()) {
			return elementoRegla.get(0);
		}
		return null;
	}
	
	@Override
	public void agregar(Regla regla) throws ValidacionExcepcion {
		if (obtener(regla.getNombre()) != null) {
			throw new ValidacionExcepcion("Ya existe una regla con el mismo nombre.");
		}
		reglas.add((Regla)regla);
	}
	
	@Override
	public void vaciar() {
		reglas.clear();
	}

	@Override
	public void quitar(String nombre) {
		
		Regla regla = obtener(nombre);
		removerRegla(reglas, regla.getNombre());
	}
	
	private void removerRegla(List<? extends Regla> reglas, String nombre) {
		Regla c = null;
		for (Regla regla : reglas) {
			if (regla.getNombre().equalsIgnoreCase(nombre)) {
				c = regla;
			}	
		}
		reglas.remove(c);
	}
	
	private List<Regla> obtenerRegla(List<? extends Regla> reglas, String nombre) {
		List<Regla> reglasByClave = new ArrayList<Regla>();
		if (reglas != null && !reglas.isEmpty()) {
			for (Regla regla : reglas) {
				if (regla.getNombre().equals(nombre)) {
					reglasByClave.add(regla);
				}
			}			
		}
		return reglasByClave;
	}

	@Override
	public List<Regla> obtenerTodos() {
		return new ArrayList<Regla>(reglas);
	}

	@Override
	public List<Regla> obtenerTodos(String nombre) {
		return obtenerRegla(reglas, nombre);
	}
}
