package ar.com.fi.uba.tecnicas.persistencia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.PersistenceStrategy;
import com.thoughtworks.xstream.persistence.XmlArrayList;

import ar.com.fi.uba.tecnicas.Configuracion;
import ar.com.fi.uba.tecnicas.controlador.cadena.Eslabon;
import ar.com.fi.uba.tecnicas.modelo.excepciones.ValidacionExcepcion;

public class RepositorioReglas implements Repositorio<Eslabon> {

	static {
		File file = new File(Configuracion.DIRECTORIO_PRESISTENCIA_BASE + "/reglas"); 
		if (!file.exists()) {
			file.mkdir();
		}
	}
	
	private PersistenceStrategy strategyReglas = new FilePersistenceStrategy(new File(Configuracion.DIRECTORIO_PRESISTENCIA_BASE + "/reglas"));
	
	@SuppressWarnings("unchecked")
	private List<Eslabon> reglas = new XmlArrayList(strategyReglas);

	@Override
	public Eslabon obtener(String nombre) {
		Eslabon elementoRegla = obtenerRegla(reglas, nombre);
		if (elementoRegla != null) {
			return elementoRegla;
		}
		return elementoRegla;
		
	}
	
	@Override
	public void agregar(Eslabon componente) throws ValidacionExcepcion {
		
		if (obtener(componente.getNombre()) != null) {
			throw new ValidacionExcepcion("Ya existe un componente del mismo nombre.");
		}
		
		reglas.add((Eslabon)componente);
	}
	
	@Override
	public void vaciar() {
		reglas.clear();
	}

	@Override
	public void quitar(String nombre) {
		
		Eslabon componente = obtener(nombre);
		removerComponente(reglas, componente.getNombre());
	}
	
	private void removerComponente(List<? extends Eslabon> componentes,String nombre) {
		Eslabon c = null;
		for (Eslabon comp : componentes) {
			if (comp.getNombre().equalsIgnoreCase(nombre)) {
				c = comp;
			}	
		}
		componentes.remove(c);
	}
	
	private Eslabon obtenerRegla(List<? extends Eslabon> eslabones, String nombre) {
		if (eslabones != null && !eslabones.isEmpty()) {
			for (Eslabon comp : eslabones) {
				if (comp.getNombre().equals(nombre)) {
					return comp;
				}
			}			
		}
		return null;
	}

	@Override
	public List<Eslabon> obtenerTodos() {
		return new ArrayList<Eslabon>(reglas);
	}
}
