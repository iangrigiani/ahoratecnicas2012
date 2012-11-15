package ar.com.fi.uba.tecnicas.persistencia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ar.com.fi.uba.tecnicas.Configuracion;
import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;
import ar.com.fi.uba.tecnicas.modelo.excepciones.ValidacionExcepcion;

import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.PersistenceStrategy;
import com.thoughtworks.xstream.persistence.XmlArrayList;

public class RepositorioTickets implements Repositorio<Mensaje> {

	private static final String CARPETA_TICKETS = "/tickets";

	static {
		File file = new File(Configuracion.DIRECTORIO_PRESISTENCIA_BASE + CARPETA_TICKETS); 
		if (!file.exists()) {
			file.mkdir();
		}
	}
	
	private PersistenceStrategy strategyTickets = new FilePersistenceStrategy(new File(Configuracion.DIRECTORIO_PRESISTENCIA_BASE + CARPETA_TICKETS));
	
	@SuppressWarnings("unchecked")
	private List<Mensaje> tickets = new XmlArrayList(strategyTickets);

	@Override
	public Mensaje obtener(String nombre) {
		Mensaje elementoMateria = obtenerTickets(tickets, nombre);
		if (elementoMateria != null) {
			return elementoMateria;
		}
		return elementoMateria;
	}
	
	@Override
	public void agregar(Mensaje ticket) throws ValidacionExcepcion {
		if (tickets.size() > 0) {
			throw new ValidacionExcepcion("Ya existe una ticket en el sistema.");
		}
		tickets.add((Mensaje)ticket);
	}
	
	@Override
	public void vaciar() {
		tickets.clear();
	}

	@Override
	public void quitar(String nombre) {
		
		Mensaje ticket = obtener(nombre);
		removerTicket(tickets, ticket.getAsunto());
	}
	
	private void removerTicket(List<? extends Mensaje> tickets, String codigo) {
		Mensaje c = null;
		for (Mensaje comp : tickets) {
			if (comp.getAsunto().equalsIgnoreCase(codigo)) {
				c = comp;
			}	
		}
		tickets.remove(c);
	}
	
	private Mensaje obtenerTickets(List<? extends Mensaje> tickets, String codigo) {
		if (tickets != null && !tickets.isEmpty()) {
			for (Mensaje ticket : tickets) {
				if (ticket.getAsunto().equals(codigo)) {
					return ticket;
				}
			}			
		}
		return null;
	}

	@Override
	public List<Mensaje> obtenerTodos() {
		return new ArrayList<Mensaje>(tickets);
	}
}
