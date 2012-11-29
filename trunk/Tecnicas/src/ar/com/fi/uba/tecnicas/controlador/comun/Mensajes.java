package ar.com.fi.uba.tecnicas.controlador.comun;

public class Mensajes {
	
	
	//MENSAJES DE ERRORES
	public static final String CANTIDAD_DE_PARAMETROS_INCORRECTO = "Parseo de parametros: Cantidad de parametros incorrecto.";
	public static final String VALIDACION_PARAMETRO_INCORRECTO = "Validacion de parametros: Parametro {0} incorrecto.";
	public static final String REGLA_NO_HAY_VALIDADORES_PARA_CREAR_REGLA = "Crear Regla: No hay validadores para crear la regla.";
	public static final String REGLA_NO_HAY_ACCIONES_PARA_CREAR_REGLA = "Crear Regla: No hay acciones para crear la regla.";
	
	public static final String REPOSITORIO_YA_EXISTE_REGLA = "Ya existe una regla con el mismo nombre.";
	public static final String REPOSITORIO_YA_EXISTE_GRUPO = "Ya existe el grupo en el sistema.";
	public static final String REPOSITORIO_YA_EXISTE_MATERIA = "Ya existe una materia en el sistema.";
	public static final String REPOSITORIO_YA_EXISTE_INSCRIPCION = "Inscripcion: Ya existe el alumno inscripto.";
	public static final String SERVICIO_MAIL_NO_SE_RECUPERARON_MAILS = "No se pudo recuperar los mails nuevos del servidor de correo configurado";
	
	public static final String ACCION_MAIL_DE_ALUMNO_NO_CORRESPONDE = "Mail de Alumno: El mail emisor no pertenece a ningun alumno.";
	public static final String ACCION_PADRON_MATERIA_ALUMNO_NO_INSCRIPTO = "Padron en materia: No se encontro el padron inscripto en la materia en este cuatrimestre.";
	public static final String ACCION_REALIZAR_ENTREGA_NO_CONTIENE_ADJUNTOS = "Realizar Entrega: No contiene adjuntos el mail";
	public static final String ACCION_REALIZAR_ENTREGA_ALUMNO_NO_PERTENECE_A_UN_GRUPO_ESTE_CUATRIMESTRE = "Realizar Entrega: El alumno que entrego el trabajo no pertenece a un grupo en este cuatrimestre";
	public static final String ACCION_CREAR_TICKET = "Crear ticket: No se ha podido crear el ticket";
}
