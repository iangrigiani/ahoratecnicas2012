package ar.com.fi.uba.tecnicas.persistencia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.PersistenceStrategy;
import com.thoughtworks.xstream.persistence.XmlArrayList;

import ar.com.fi.uba.tecnicas.Configuracion;
import ar.com.fi.uba.tecnicas.modelo.entidades.Regla;
import ar.com.fi.uba.tecnicas.modelo.excepciones.ValidacionExcepcion;

public class RepositorioReglas implements Repositorio<Regla> {

	static {
		File file = new File(Configuracion.DIRECTORIO_PRESISTENCIA_BASE + "/reglas"); 
		if (!file.exists()) {
			file.mkdir();
		}
	}
	
	private PersistenceStrategy strategyReglas = new FilePersistenceStrategy(new File(Configuracion.DIRECTORIO_PRESISTENCIA_BASE + "/reglas"));
	
	@SuppressWarnings("unchecked")
	private List<Regla> reglas = new XmlArrayList(strategyReglas);

	@Override
	public Regla obtener(String nombre) {
		Regla elementoRegla = obtenerRegla(reglas, nombre);
		if (elementoRegla != null) {
			return elementoRegla;
		}
		return elementoRegla;
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
	
	private Regla obtenerRegla(List<? extends Regla> reglas, String nombre) {
		if (reglas != null && !reglas.isEmpty()) {
			for (Regla regla : reglas) {
				if (regla.getNombre().equals(nombre)) {
					return regla;
				}
			}			
		}
		return null;
	}

	@Override
	public List<Regla> obtenerTodos() {
		return new ArrayList<Regla>(reglas);
	}
}