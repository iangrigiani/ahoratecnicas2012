package ar.com.fi.uba.tecnicas.persistencia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ar.com.fi.uba.tecnicas.Configuracion;
import ar.com.fi.uba.tecnicas.modelo.entidades.Ticket;
import ar.com.fi.uba.tecnicas.modelo.excepciones.ValidacionExcepcion;

import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.PersistenceStrategy;
import com.thoughtworks.xstream.persistence.XmlArrayList;

public class RepositorioTickets implements Repositorio<Ticket> {

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
	private List<Ticket> tickets = new XmlArrayList(strategyTickets);

	public static Repositorio<Ticket> getInstance() {
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
	public Ticket obtener(String nombre) {
		List<Ticket> ticketsObtenidos = obtenerTickets(tickets, nombre);
		if (ticketsObtenidos != null && !ticketsObtenidos.isEmpty()) {
			return ticketsObtenidos.get(0);
		}
		return null;
	}
	
	/**
	 * @see ar.com.fi.uba.tecnicas.persistencia.Repositorio#agregar(java.lang.Object)
	 */
	@Override
	public void agregar(Ticket ticket) throws ValidacionExcepcion {
		if (tickets.size() > 0) {
			throw new ValidacionExcepcion("Ya existe una ticket en el sistema.");
		}
		tickets.add((Ticket)ticket);
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
		
		Ticket ticket = obtener(nombre);
		removerTicket(tickets, ticket.getId());
	}
	
	private void removerTicket(List<? extends Ticket> tickets, String codigo) {
		Ticket c = null;
		for (Ticket comp : tickets) {
			if (comp.getId().equalsIgnoreCase(codigo)) {
				c = comp;
			}	
		}
		tickets.remove(c);
	}
	
	private List<Ticket> obtenerTickets(List<? extends Ticket> tickets, String codigo) {
		List<Ticket> mensajes = new ArrayList<Ticket>();
		if (tickets != null && !tickets.isEmpty()) {
			for (Ticket ticket : tickets) {
				if (ticket.getId().equals(codigo)) {
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
	public List<Ticket> obtenerTodos() {
		return new ArrayList<Ticket>(tickets);
	}

	/**
	 * @see ar.com.fi.uba.tecnicas.persistencia.Repositorio#obtenerTodos(java.lang.String)
	 */
	@Override
	public List<Ticket> obtenerTodos(String codigo) {
		return obtenerTickets(tickets, codigo);
	}
}
