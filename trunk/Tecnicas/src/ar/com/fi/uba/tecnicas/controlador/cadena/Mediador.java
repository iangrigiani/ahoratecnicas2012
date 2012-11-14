package ar.com.fi.uba.tecnicas.controlador.cadena;

import java.util.List;

import ar.com.fi.uba.tecnicas.Configuracion;
import ar.com.fi.uba.tecnicas.controlador.BuscadorClases;
import ar.com.fi.uba.tecnicas.controlador.comun.Constantes;
import ar.com.fi.uba.tecnicas.controlador.comun.Converter;
import ar.com.fi.uba.tecnicas.controlador.validador.ValidadorParametro;
import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;
import ar.com.fi.uba.tecnicas.modelo.entidades.Regla;
import ar.com.fi.uba.tecnicas.modelo.entidades.accion.Accion;
import ar.com.fi.uba.tecnicas.modelo.excepciones.ValidacionExcepcion;
import ar.com.fi.uba.tecnicas.persistencia.Repositorio;
import ar.com.fi.uba.tecnicas.persistencia.RepositorioReglas;

/**
 * Implementacion del patron Mediador junto con la cadena de responsabilidades
 * @author ramiro
 *
 */
public class Mediador {
	
	private Eslabon extremoCadena;
	private Repositorio<Regla> repositorioRegla;
	private Repositorio<Mensaje> repositorioTickets;

	public Mediador() {
		this.repositorioRegla = new RepositorioReglas();
		this.extremoCadena = CadenaFactory.crearCadenaReglas(repositorioRegla.obtenerTodos(), this);
	}
	
    public Eslabon getEslavon() {
		return extremoCadena;
	}
	public void setEslavon(Eslabon eslavon) {
		this.extremoCadena = eslavon;
	}

	/**
	 * Actualiza las bandejas que tengamos
	 */
	public void actualizarBandejas() {
		
		
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
	 * 
	 * @param regla
	 * @throws ValidacionExcepcion
	 */
	public void agregarRegla(Regla regla) throws ValidacionExcepcion {
		repositorioRegla.agregar(regla);
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
	public Repositorio<Mensaje> getRepositorioTickets() {
		return repositorioTickets;
	}

	/**
	 * @param repositorioTickets the repositorioTickets to set
	 */
	public void setRepositorioTickets(Repositorio<Mensaje> repositorioTickets) {
		this.repositorioTickets = repositorioTickets;
	}


}
