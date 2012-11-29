 package ar.com.fi.uba.tecnicas.controlador;


import java.util.ArrayList;
import java.util.List;

import ar.com.fi.uba.tecnicas.controlador.cadena.Mediador;
import ar.com.fi.uba.tecnicas.controlador.validador.ValidadorParametro;
import ar.com.fi.uba.tecnicas.modelo.entidades.Grupo;
import ar.com.fi.uba.tecnicas.modelo.entidades.Materia;
import ar.com.fi.uba.tecnicas.modelo.entidades.Parametro;
import ar.com.fi.uba.tecnicas.modelo.entidades.Regla;
import ar.com.fi.uba.tecnicas.modelo.excepciones.ValidacionExcepcion;
import ar.com.fi.uba.tecnicas.vista.InterfazUsuario;

/**
 * Facade con los metodos que puede ejecutar el usuario.
 * @author cristian
 *
 */
public class ComandosUsuarioFacade {

	private List<ValidadorParametro> validadores;
	private List<String> acciones;
	private Mediador mediador;

	/**
	 * Crea la regla interactuando con el usuario
	 * @param invocador
	 * @return
	 */
	public String crearRegla(InterfazUsuario invocador){
		inicializar(invocador);
		
		if (validadores.isEmpty()) {
			invocador.mensaje("No hay validadores para crear la regla.");
			return "";
		}

		if (acciones.isEmpty()) {
			invocador.mensaje("No hay acciones para crear la regla.");
			return "";
		}

		Regla regla = new Regla();
		regla.setNombre(invocador.obtenerDatos("Ingrese el nombre de la regla: "));
		regla.setAsunto(invocador.obtenerDatos("Ingrese el asunto que será validado: "));
		
		agregarParametros(invocador, regla);
	
		agregarAcciones(invocador, regla);
		
		try {
			mediador.agregarRegla(regla);
		} catch (ValidacionExcepcion e) {
			invocador.mensaje("No pudo guardarse la nueva regla, puede que ya exista. Por favor intentelo de nuevo.");
			return "";
		}
		return "Se creo la regla.";
	}

	/**
	 * Crea un grupo interactuando con el usuario
	 * @param invocador
	 * @return
	 */
	public String crearGrupo(InterfazUsuario invocador){
		if (mediador == null) {
			mediador = new Mediador();
		}
			
		Grupo grupo = new Grupo();
		grupo.setCodigo(invocador.obtenerDatos("Ingrese el código o número de grupo: "));
		grupo.setNombre(invocador.obtenerDatos("Ingrese el nombre del grupo: "));

		try {
			mediador.agregarGrupo(grupo);
		} catch (ValidacionExcepcion e) {
			invocador.mensaje("No pudo guardarse la nueva regla, puede que ya exista. Por favor intentelo de nuevo.");
			return "";
		}
		return "Se creo el grupo.";
	}
	
	/**
	 * Crea la regla interactuando con el usuario
	 * @param invocador
	 * @return
	 */
	public String crearMateria(InterfazUsuario invocador){
		if (mediador == null) {
			mediador = new Mediador();
		}
			
		Materia materia = new Materia();
		materia.setNombre(invocador.obtenerDatos("Ingrese el nombre de la materia: "));
		materia.setCodigo(invocador.obtenerDatos("Ingrese el codigo de la materia: "));
		
		try {
			mediador.agregarMateria(materia);
		} catch (ValidacionExcepcion e) {
			invocador.mensaje("No pudo guardarse porque ya existe una materia.");
			return "";
		}
		return "Se creo la materia.";
	}

	/**
	 * @param invocador
	 * @param regla
	 */
	private void agregarAcciones(InterfazUsuario invocador, Regla regla) {
		Integer indice;
		String agregarMasAcciones;
		do {
			//Necesito listar los validadores que tengo para los parametros
			invocador.mensaje("Acciones disponibles para ejecutarse: ");
			for (String accion : acciones) {
				invocador.mensaje(acciones.indexOf(accion) + ") " + accion);
			}
			indice = Integer.valueOf(invocador.obtenerDatos("Elija la accion para ejecutar: "));
			if (!regla.addAccion(acciones.get(indice))) {
				invocador.mensaje("No pudo agregarse el nuevo parametro por favor intentelo de nuevo.");
			}
			agregarMasAcciones = invocador.obtenerDatos("Desea especificar más acciones? (Si/No): ");	
		} while (agregarMasAcciones.equalsIgnoreCase("si"));
	}

	/**
	 * @param invocador
	 * @param regla
	 */
	private void agregarParametros(InterfazUsuario invocador, Regla regla) {
		Parametro parametro;
		Integer indice;
		String agregarParametros = invocador.obtenerDatos("Desea especificar parametros? (Si/No): "); 
		while (agregarParametros.equalsIgnoreCase("si")) {
			parametro = new Parametro();
			//Necesito listar los validadores que tengo para los parametros
			invocador.mensaje("Validadores disponibles para su parametro: ");
			for (ValidadorParametro validador : validadores) {
				invocador.mensaje(validadores.indexOf(validador) + ") " + validador.getDescripcion());
			}
			indice = Integer.valueOf(invocador.obtenerDatos("Elija el validador para su parametro: "));
			parametro.setValidador(validadores.get(indice));
			if (!regla.addParametro(parametro)) {
				invocador.mensaje("No pudo agregarse el nuevo parametro por favor intentelo de nuevo.");
			}
			agregarParametros = invocador.obtenerDatos("Desea especificar más parametros? (Si/No): ");	
		}
	}

	/**
	 * @param invocador
	 */
	private void inicializar(InterfazUsuario invocador) {
		if (mediador == null) {
			mediador = new Mediador();
		}
		if (validadores == null) {
			//reemplazar este new por la llamada a una factory o builder 
			validadores = new ArrayList<ValidadorParametro>();
		}
		validadores = mediador.getValidadores();
		if (acciones == null) {
			//reemplazar este new por la llamada a una factory o builder 
			acciones = new ArrayList<String>();
		}
		acciones = mediador.getAcciones();
	}
	
	/**
	 * Actualiza las bandejas del sistema
	 * @param invocador
	 * @return
	 */
	public String actualizarBandejas(InterfazUsuario invocador){
		if (mediador == null) {
			mediador = new Mediador();
		}
		try {
			mediador.generarTickets();
		} catch (ValidacionExcepcion e) {
			invocador.mensaje("No pudo actualizarse las bandejas y/o generar los tickets. Por favor intentelo de nuevo.");
			return "";
		}
		return "Los tickets fueron creados correctamente";
	}
	
	
	
	/**
	 * help! I need somebody!
	 * @param invocador
	 * @return
	 */
	public String ayuda(InterfazUsuario invocador){
		String mensaje="LISTA DE COMANDOS\n" +
				"* crearRegla \n" +
				"	-Crea una regla vinculando los validadores y las acciones disponibles al momento de crearla." +
				"* crearMateria \n" +
				"	-Crea una materia." +
				"* actualizarBandejas\n" +
				"	-Actualiza las bandejas disponibles.\n\n"+
				"\nFIN\n";
		return mensaje;
			
	}
	
}
