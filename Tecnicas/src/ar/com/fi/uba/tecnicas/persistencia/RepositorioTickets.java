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

	private static RepositorioTickets INSTANCE = null;

	static {
		File file = new File(Configuracion.DIRECTORIO_PRESISTENCIA_BASE + CARPETA_TICKETS); 
		if (!file.exists()) {
			file.mkdir();
		}
	}
	
	private PersistenceStrategy strategyTickets = new FilePersistenceStrategy(new File(Configuracion.DIRECTORIO_PRESISTENCIA_BASE + CARPETA_TICKETS));
	
	@SuppressWarnings("unchecked")
	private List<Mensaje> tickets = new XmlArrayList(strategyTickets);

	public static Repositorio<Mensaje> getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new RepositorioTickets();
		}
		return INSTANCE;
	}
	
	private RepositorioTickets() {
		
	}
	
	/**
	 * @see ar.com.fi.uba.tecnicas.persistencia.Repositorio#obtener(java.lang.String)
	 */
	@Override
	public Mensaje obtener(String nombre) {
		List<Mensaje> mensajes = obtenerTickets(tickets, nombre);
		if (mensajes != null && !mensajes.isEmpty()) {
			return mensajes.get(0);
		}
		return null;
	}
	
	/**
	 * @see ar.com.fi.uba.tecnicas.persistencia.Repositorio#agregar(java.lang.Object)
	 */
	@Override
	public void agregar(Mensaje ticket) throws ValidacionExcepcion {
		if (tickets.size() > 0) {
			throw new ValidacionExcepcion("Ya existe una ticket en el sistema.");
		}
		tickets.add((Mensaje)ticket);
	}
	
	/**
	 * @see ar.com.fi.uba.tecnicas.persistencia.Repositorio#vaciar()
	 */
	@Override
	public void vaciar() {
		tickets.clear();
	}

	/**
	 * @see ar.com.fi.uba.tecnicas.persistencia.Repositorio#quitar(java.lang.String)
	 */
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
	
	private List<Mensaje> obtenerTickets(List<? extends Mensaje> tickets, String codigo) {
		List<Mensaje> mensajes = new ArrayList<Mensaje>();
		if (tickets != null && !tickets.isEmpty()) {
			for (Mensaje ticket : tickets) {
				if (ticket.getAsunto().equals(codigo)) {
					mensajes.add(ticket);
				}
			}			
		}
		return mensajes;
	}

	/**
	 * @see ar.com.fi.uba.tecnicas.persistencia.Repositorio#obtenerTodos()
	 */
	@Override
	public List<Mensaje> obtenerTodos() {
		return new ArrayList<Mensaje>(tickets);
	}

	/**
	 * @see ar.com.fi.uba.tecnicas.persistencia.Repositorio#obtenerTodos(java.lang.String)
	 */
	@Override
	public List<Mensaje> obtenerTodos(String codigo) {
		return obtenerTickets(tickets, codigo);
	}
}
