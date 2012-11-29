/**
 * 
 */
package ar.com.fi.uba.tecnicas.modelo.entidades.accion;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Set;

import ar.com.fi.uba.tecnicas.Configuracion;
import ar.com.fi.uba.tecnicas.controlador.comun.CuatrimestreHelper;
import ar.com.fi.uba.tecnicas.controlador.comun.Mensajes;
import ar.com.fi.uba.tecnicas.modelo.entidades.Alumno;
import ar.com.fi.uba.tecnicas.modelo.entidades.AlumnoGrupo;
import ar.com.fi.uba.tecnicas.modelo.entidades.Materia;
import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;
import ar.com.fi.uba.tecnicas.modelo.entidades.Parametro;
import ar.com.fi.uba.tecnicas.persistencia.Repositorio;
import ar.com.fi.uba.tecnicas.persistencia.RepositorioBuilder;

/**
 * Obtiene el nro de grupo sacandolo del mail del emisor.
 * Copia el adjunto en una carpeta para el grupo
 * Crea la entidad entrega con los datos
 * @author nacho
 *
 */
public class RealizarEntrega implements Accion {

	private AlumnoGrupo alumnoGrupo;
	/**
	 * @see ar.com.fi.uba.tecnicas.modelo.entidades.accion.Accion#ejecutar(ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje, java.util.List)
	 */
	@Override
	public String ejecutar(Mensaje mensaje, Set<Parametro> parametros) {
		RepositorioBuilder repoBuilder = new RepositorioBuilder();
		Repositorio<AlumnoGrupo> repositorioAlumnoGrupo = repoBuilder.getRepositorioAlumnoGrupo();
		Repositorio<Materia> repositorioMateria = repoBuilder.getRepositorioMateria();
		
		//TODO: Poner el if correspondiente para que no pinche si no hay materia
		String codigoMateria = repositorioMateria.obtenerTodos().get(0).getCodigo();
		String codigoGrupo ="";
		
		List<AlumnoGrupo> grupo = repositorioAlumnoGrupo.obtenerTodos(alumnoGrupo.getPadron());
		codigoGrupo = grupo.get(grupo.indexOf(alumnoGrupo)).getGrupo();
				
		String carpetaDestino = Configuracion.DIRECTORIO_PRESISTENCIA_BASE + codigoMateria + File.separatorChar + "Entregas" + File.separatorChar + codigoGrupo + File.separatorChar;
		
		crearArbolDirectorio(carpetaDestino);
		
		for (String adjunto : mensaje.getPathAdjunto()) {
			File file = new File(adjunto);
			File fileDestino = new File(carpetaDestino + file.getName());
			file.renameTo(fileDestino);
		}
		
		return "";
	}

	private void crearArbolDirectorio(String carpetaDestino) {
		String[] carpetas = carpetaDestino.split(String.valueOf(File.separatorChar));
		File file;
		String acumulador = String.valueOf(File.separatorChar);
		for (String string : carpetas) {
			file = new File(acumulador + string); 
			if (!file.exists()) {
				file.mkdir();
			}			
			acumulador += string + File.separatorChar;
		}
	}

	/**
	 * @see ar.com.fi.uba.tecnicas.modelo.entidades.accion.Accion#puedeEjecutar(ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje, java.util.Set)
	 */
	@Override
	public String puedeEjecutar(Mensaje mesg, Set<Parametro> parametrosParaAccion) {
		RepositorioBuilder repoBuilder = new RepositorioBuilder();
		Repositorio<AlumnoGrupo> repositorioAlumnoGrupo = repoBuilder.getRepositorioAlumnoGrupo();
		Repositorio<Alumno> repositorioAlumno = repoBuilder.getRepositorioAlumno();

		if (mesg.getPathAdjunto() == null || mesg.getPathAdjunto().isEmpty()) {
			return Mensajes.ACCION_REALIZAR_ENTREGA_NO_CONTIENE_ADJUNTOS;
		}
		
		alumnoGrupo = new AlumnoGrupo();
		List<Alumno> obtenerTodos = repositorioAlumno.obtenerTodos();
		for (Alumno alumno : obtenerTodos) {
			if (alumno.getMail().equalsIgnoreCase(mesg.getDe())) {
				alumnoGrupo.setPadron(alumno.getPadron());
				break;
			}
		}

		alumnoGrupo.setPrimerCuatrimestre(CuatrimestreHelper.pertenecePrimerCuatrimestre(new Date()));
		
		List<AlumnoGrupo> grupos = repositorioAlumnoGrupo.obtenerTodos(alumnoGrupo.getPadron());
		//Tengo al alumno en un grupo en ese cuatrimestre?
		if (grupos != null && grupos.contains(alumnoGrupo)) {
			//Esta inscripto en este cuatrimestre
			return "";
		} else {
			//No Esta inscripto en este cuatrimestre o no tiene grupo asignado
			return Mensajes.ACCION_REALIZAR_ENTREGA_ALUMNO_NO_PERTENECE_A_UN_GRUPO_ESTE_CUATRIMESTRE;
		}		
		
		
		
		
	}

}
