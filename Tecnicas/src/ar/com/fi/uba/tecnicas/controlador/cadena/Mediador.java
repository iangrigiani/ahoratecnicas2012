package ar.com.fi.uba.tecnicas.controlador.cadena;

import java.util.List;

import ar.com.fi.uba.tecnicas.Configuracion;
import ar.com.fi.uba.tecnicas.controlador.BuscadorClases;
import ar.com.fi.uba.tecnicas.controlador.comun.Constantes;
import ar.com.fi.uba.tecnicas.controlador.comun.Converter;
import ar.com.fi.uba.tecnicas.controlador.comun.Mensajes;
import ar.com.fi.uba.tecnicas.controlador.mail.ServicioMail;
import ar.com.fi.uba.tecnicas.controlador.mail.ServicioMailMockImpl;
import ar.com.fi.uba.tecnicas.controlador.validador.ValidadorParametro;
import ar.com.fi.uba.tecnicas.modelo.entidades.Grupo;
import ar.com.fi.uba.tecnicas.modelo.entidades.Materia;
import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;
import ar.com.fi.uba.tecnicas.modelo.entidades.Regla;
import ar.com.fi.uba.tecnicas.modelo.entidades.Ticket;
import ar.com.fi.uba.tecnicas.modelo.entidades.accion.Accion;
import ar.com.fi.uba.tecnicas.modelo.excepciones.MailException;
import ar.com.fi.uba.tecnicas.modelo.excepciones.ValidacionExcepcion;
import ar.com.fi.uba.tecnicas.persistencia.Repositorio;
import ar.com.fi.uba.tecnicas.persistencia.RepositorioGrupo;
import ar.com.fi.uba.tecnicas.persistencia.RepositorioMateria;
import ar.com.fi.uba.tecnicas.persistencia.RepositorioReglas;
import ar.com.fi.uba.tecnicas.persistencia.RepositorioTickets;

/**
 * Implementacion del patron Mediador junto con la cadena de responsabilidades
 * @author ramiro
 *
 */
public class Mediador {
	
	private Eslabon extremoCadena;
	private Repositorio<Regla> repositorioRegla;
	private Repositorio<Materia> repositorioMateria;
	private Repositorio<Grupo> repositorioGrupo;
	private Repositorio<Ticket> repositorioTickets;
	private ServicioMail servicioMail;
	private List<String> nombreAcciones;

	public Mediador() {
		this.repositorioRegla = RepositorioReglas.getInstance();
		this.repositorioMateria = RepositorioMateria.getInstance();
		this.repositorioGrupo = RepositorioGrupo.getInstance();
		this.repositorioTickets = RepositorioTickets.getInstance();
		List<Regla> todasLasReglas = repositorioRegla.obtenerTodos();
		if (todasLasReglas == null || todasLasReglas.isEmpty()) {
			System.out.println("Warning: No se encontraron reglas definidas por el usuario.");
		}
		this.extremoCadena = CadenaFactory.crearCadenaReglas(todasLasReglas, this);	
		
	}
	
    public Eslabon getEslavon() {
		return extremoCadena;
	}
	public void setEslavon(Eslabon eslavon) {
		this.extremoCadena = eslavon;
	}

	/**
	 * Genera los tickets de los mensajes nuevos
	 * @throws ValidacionExcepcion 
	 */	
	public void generarTickets() throws ValidacionExcepcion {
		if (servicioMail == null) {
			servicioMail = new ServicioMailMockImpl();
			//servicioMail = new ServicioMailImpl();
		}
		List<Mensaje> mensajes;
		
		try {
			mensajes = servicioMail.getMensajesNuevos();
		} catch (MailException e1) {
			throw new ValidacionExcepcion(Mensajes.SERVICIO_MAIL_NO_SE_RECUPERARON_MAILS, e1);
		}
		
		for (Mensaje mensaje : mensajes) {
			try {
				extremoCadena.sendToEslabon(mensaje);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	

	/**
	 * @return the extremoCadena
	 */
	public Eslabon getExtremoCadena() {
		return extremoCadena;
	}


	/**
	 * @param extremoCadena the extremoCadena to set
	 */
	public void setExtremoCadena(Eslabon extremoCadena) {
		this.extremoCadena = extremoCadena;
	}

	/**
	 * Agrega una regla al repositorio de reglas
	 * @param regla
	 * @throws ValidacionExcepcion
	 */
	public void agregarRegla(Regla regla) throws ValidacionExcepcion {
		repositorioRegla.agregar(regla);
		this.extremoCadena = CadenaFactory.crearCadenaReglas(repositorioRegla.obtenerTodos(), this);	
	}
	
	/**
	 * Agrega un grupo al repositorio de grupos
	 * @param grupo
	 * @throws ValidacionExcepcion
	 */
	public void agregarGrupo(Grupo grupo) throws ValidacionExcepcion {
		repositorioGrupo.agregar(grupo);
	}

	/**
	 * 
	 * @param regla
	 * @throws ValidacionExcepcion
	 */
	public void agregarMateria(Materia materia) throws ValidacionExcepcion {
		repositorioMateria.agregar(materia);
	}

	
	
	/**
	 * Busca los validadores disponibles y las instancia
	 * @return Los validadores disponibles instanciadas
	 */
	public List<ValidadorParametro> getValidadores() {
		return Converter.getValidadoresParametros(BuscadorClases.buscarClasesImplementanInterfaz(Constantes.NOMBRE_INTERFAZ_VALIDADOR, 
				   Configuracion.DIRECTORIO_VALIDADOR_PARAMETROS_BASE, 
				   Constantes.PAQUETE_INTERFAZ_VALIDADOR));
	}

	/**
	 * Busca los nombres de las acciones disponibles
	 * @return Los nombres de las acciones disponibles 
	 */
	public List<String> getAcciones() {
		return BuscadorClases.buscarNombreClasesImplementanInterfaz(Constantes.NOMBRE_INTERFAZ_ACCION, 
				   Configuracion.DIRECTORIO_ACCIONES_BASE, 
				   Constantes.PAQUETE_INTERFAZ_ACCION);
	}

	/**
	 * Busca las acciones disponibles y las instancia
	 * @return Las acciones disponibles instanciadas
	 */
	public List<Accion> getAccionesInstanciadas() {
		return Converter.getAcciones(BuscadorClases.buscarClasesImplementanInterfaz(Constantes.NOMBRE_INTERFAZ_ACCION, 
				   Configuracion.DIRECTORIO_ACCIONES_BASE, 
				   Constantes.PAQUETE_INTERFAZ_ACCION));
	}

	/**
	 * @return the repositorioRegla
	 */
	public Repositorio<Regla> getRepositorioRegla() {
		return repositorioRegla;
	}

	/**
	 * @param repositorioRegla the repositorioRegla to set
	 */
	public void setRepositorioRegla(Repositorio<Regla> repositorioRegla) {
		this.repositorioRegla = repositorioRegla;
	}

	/**
	 * @return the repositorioTickets
	 */
	public Repositorio<Ticket> getRepositorioTickets() {
		return repositorioTickets;
	}

	/**
	 * @param repositorioTickets the repositorioTickets to set
	 */
	public void setRepositorioTickets(Repositorio<Ticket> repositorioTickets) {
		this.repositorioTickets = repositorioTickets;
	}

	/**
	 * @return the nombreAcciones
	 */
	public List<String> getNombreAcciones() {
		return nombreAcciones;
	}

	/**
	 * @param nombreAcciones the nombreAcciones to set
	 */
	public void setNombreAcciones(List<String> nombreAcciones) {
		this.nombreAcciones = nombreAcciones;
	}

	/**
	 * @return the repositorioMateria
	 */
	public Repositorio<Materia> getRepositorioMateria() {
		return repositorioMateria;
	}

	/**
	 * @param repositorioMateria the repositorioMateria to set
	 */
	public void setRepositorioMateria(Repositorio<Materia> repositorioMateria) {
		this.repositorioMateria = repositorioMateria;
	}

	/**
	 * @return the repositorioGrupo
	 */
	public Repositorio<Grupo> getRepositorioGrupo() {
		return repositorioGrupo;
	}

	/**
	 * @param repositorioGrupo the repositorioGrupo to set
	 */
	public void setRepositorioGrupo(Repositorio<Grupo> repositorioGrupo) {
		this.repositorioGrupo = repositorioGrupo;
	}

	/**
	 * @return the servicioMail
	 */
	public ServicioMail getServicioMail() {
		return servicioMail;
	}

	/**
	 * @param servicioMail the servicioMail to set
	 */
	public void setServicioMail(ServicioMail servicioMail) {
		this.servicioMail = servicioMail;
	}

}

