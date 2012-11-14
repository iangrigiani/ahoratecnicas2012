package ar.com.fi.uba.tecnicas.controlador;


import java.util.ArrayList;
import java.util.List;

import ar.com.fi.uba.tecnicas.Configuracion;
import ar.com.fi.uba.tecnicas.controlador.comun.Constantes;
import ar.com.fi.uba.tecnicas.controlador.comun.Converter;
import ar.com.fi.uba.tecnicas.controlador.validador.ValidadorParametro;
import ar.com.fi.uba.tecnicas.modelo.entidades.Parametro;
import ar.com.fi.uba.tecnicas.modelo.entidades.Regla;
import ar.com.fi.uba.tecnicas.modelo.excepciones.ValidacionExcepcion;
import ar.com.fi.uba.tecnicas.persistencia.Repositorio;
import ar.com.fi.uba.tecnicas.persistencia.RepositorioReglas;
import ar.com.fi.uba.tecnicas.vista.InterfazUsuario;

/**
 * Facade con los metodos que puede ejecutar el usuario.
 * @author cristian
 *
 */
public class ComandosUsuarioFacade {
	
	private Repositorio<Regla> repositorioRegla;
	private List<ValidadorParametro> validadores;
	private List<String> acciones;

	/**
	 * Crea la regla interactuando con el usuario
	 * @param invocador
	 * @return
	 */
	public String crearRegla(InterfazUsuario invocador){
		inicializar(invocador);
		
		Regla regla = new Regla();
		regla.setNombre(invocador.obtenerDatos("Ingrese el nombre de la regla: "));
		regla.setAsunto(invocador.obtenerDatos("Ingrese el asunto que será validado: "));
		
		agregarParametros(invocador, regla);
		
		agregarAcciones(invocador, regla);
		
		try {
			repositorioRegla.agregar(regla);
		} catch (ValidacionExcepcion e) {
			invocador.mensaje("No pudo guardarse la nueva regla, puede que ya exista. Por favor intentelo de nuevo.");
			return "";
		}
		return "Se creo la regla.";
	}

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

	private void agregarParametros(InterfazUsuario invocador, Regla regla) {
		Parametro parametro;
		Integer indice;
		String agregarParametros = invocador.obtenerDatos("Desea especificar parametros? (Si/No): "); 
		while (agregarParametros.equalsIgnoreCase("si")) {
			parametro = new Parametro();
			parametro.setNombre(invocador.obtenerDatos("Ingrese el nombre del parametro: "));
			//Necesito listar los validadores que tengo para los parametros
			invocador.mensaje("Validadores disponibles para su parametro: ");
			for (ValidadorParametro validador : validadores) {
				invocador.mensaje(validadores.indexOf(validador) + ") " + validador.getDescripcion());
			}
			indice = Integer.valueOf(invocador.obtenerDatos("Elija el validador para su parametro: "));
			if (!regla.addParametro(parametro, validadores.get(indice))) {
				invocador.mensaje("No pudo agregarse el nuevo parametro por favor intentelo de nuevo.");
			}
			agregarParametros = invocador.obtenerDatos("Desea especificar más parametros? (Si/No): ");	
		}
	}

	private void inicializar(InterfazUsuario invocador) {
		if (repositorioRegla == null) {
			repositorioRegla = new RepositorioReglas();
		}
		if (validadores == null) {
			//reemplazar este new por la llamada a una factory o builder 
			validadores = new ArrayList<ValidadorParametro>();
			validadores = Converter.getValidadoresParametros(BuscadorClases.buscarClasesImplementanInterfaz(Constantes.NOMBRE_INTERFAZ_VALIDADOR, 
														   Configuracion.DIRECTORIO_VALIDADOR_PARAMETROS_BASE, 
														   Constantes.PAQUETE_INTERFAZ_VALIDADOR));
		}
		if (acciones == null) {
			//reemplazar este new por la llamada a una factory o builder 
			acciones = new ArrayList<String>();
			acciones = BuscadorClases.buscarNombreClasesImplementanInterfaz(Constantes.NOMBRE_INTERFAZ_ACCION, 
					   Configuracion.DIRECTORIO_ACCIONES_BASE, 
					   Constantes.PAQUETE_INTERFAZ_VALIDADOR);
		}
	}
	
	/**
	 * Muestra todos los componentes del repositorio
	 * @param invocador
	 * @return
	 */
	public String mostrarRepositorio(InterfazUsuario invocador){
		return "";
	}
	
	/**
	 * help! I need somebody!
	 * @param invocador
	 * @return
	 */
	public String ayuda(InterfazUsuario invocador){
		String mensaje="LISTA DE COMANDOS\n" +
				"* crearComponente \n" +
				"	-Crea un componente con los datos solicitados, el tipo toma dos valores generico/demux y la cantidad de entradas numeros enteros\n\n" +
				"* crearCircuito\n" +
				"	-Crea un circuito con los datos solicitados y lo deja listo para su edicion.\n\n"+
				"* agregarComponente <NOMBRE>\n" +
				"	-Agrega un componente ya creado en el circuito que esta actulmente en edicion.\n\n"+
				"* conectarComponentes .\n" +
				"	-Conecta la salida del componente indicado con la entrada indicada.\n\n"+
				"* conectarEntradaCircuito .\n" +
				"	-Conecta la entrada del circuito en edicion con la entrada del conponente indicado.\n\n"+
				"* conectarSalidaCircuito  .\n" +
				"	-Conecta la salida indicada del circuito en edicion con la salida del componente\n\n"+
				"* mostrar \n" +
				"	-Muestra los componentes que contiene el circuito actualente en edicion\n\n"+
				"* limpiar \n" +
				"	- vacia el repositorio\n\n" +
				"* guardar \n " +
				"	- Guarda el circuito en edicion.\n\n"+
				"* mostrarRepositorio \n" +
				"   - Muestra los componentes existentes\n\n" +
				"* mostrarComponente <NOMBRE COMP>\n" +
				"	- Muestra un detalle del componente\n\n"+
				"* abrir <NOMBRE CIRCUITO>\n" +
				"	- Abre el circuito nombrado y lo deja listo para edición.\n\n" +
				"* cerrar\n" +
				"	- cierra el circuito actualmente en edicion\n\n" +
				"* simular\n " +
				"	- Ejecuta la simulacion del circuito actualmente en edicion\n\n"+ 
				"* guardar\n" +
				"	- Guarda el circuito actualmente en edicion y lo saca de edicion\n\n"+
				"* limpiar\n" +
				"	- Vacía el repositorio\n\n"+
				"* borrarComponente <NOMBRE>\n" +
				"	- Elimina del repositorio, el componente indicado por el nombre\n\n" +
				"* borrarCircuito <NOMBRE>\n" +
				"	- Elimina del repositorio, el circuito indicado por el nombre\n\n" +
				"* sacarComponente <ID COMP>\n" +
				"	- Remueve el componente del circuito actualmente en edición\n\n"+
				"\nFIN\n";
		return mensaje;
			
	}
	
}
