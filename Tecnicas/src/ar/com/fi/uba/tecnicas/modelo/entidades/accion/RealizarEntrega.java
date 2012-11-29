/**
 * 
 */
package ar.com.fi.uba.tecnicas.modelo.entidades.accion;

import java.io.File;
import java.util.List;
import java.util.Set;

import ar.com.fi.uba.tecnicas.Configuracion;
import ar.com.fi.uba.tecnicas.modelo.entidades.Alumno;
import ar.com.fi.uba.tecnicas.modelo.entidades.AlumnoGrupo;
import ar.com.fi.uba.tecnicas.modelo.entidades.Inscripcion;
import ar.com.fi.uba.tecnicas.modelo.entidades.Materia;
import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;
import ar.com.fi.uba.tecnicas.modelo.entidades.Parametro;
import ar.com.fi.uba.tecnicas.persistencia.Repositorio;
import ar.com.fi.uba.tecnicas.persistencia.RepositorioBuilder;

/**
 * @author nacho
 * Obtiene el nro de grupo sacandolo del mail del emisor.
 * Copia el adjunto en una carpeta para el grupo
 * Crea la entidad entrega con los datos
 *
 */
public class RealizarEntrega implements Accion {

	/**
	 * @see ar.com.fi.uba.tecnicas.modelo.entidades.accion.Accion#ejecutar(ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje, java.util.List)
	 */
	@Override
	public String ejecutar(Mensaje mensaje, Set<Parametro> parametros) {
		RepositorioBuilder repoBuilder = new RepositorioBuilder();
		Repositorio<AlumnoGrupo> repositorioAlumnoGrupo = repoBuilder.getRepositorioAlumnoGrupo();
		Repositorio<Alumno> repositorioAlumno = repoBuilder.getRepositorioAlumno();
		Repositorio<Materia> repositorioMateria = repoBuilder.getRepositorioMateria();
		
		//TODO: Poner el if correspondiente para que no pinche si no hay materia
		String codigoMateria = repositorioMateria.obtenerTodos().get(0).getCodigo();
		String codigoGrupo ="";
		
		List<Alumno> obtenerTodos = repositorioAlumno.obtenerTodos();
		for (Alumno alumno : obtenerTodos) {
			if (alumno.getMail().equalsIgnoreCase(mensaje.getDe())) {
				AlumnoGrupo grupo = repositorioAlumnoGrupo.obtener(alumno.getPadron());
				//TODO: Pasar a una accion distinta
				if (grupo == null) {
					return "El alumno no esta en ningun grupo.";
				}
				codigoGrupo = grupo.getGrupo();
				break;
			}
		}
		
		String carpetaDestino = Configuracion.DIRECTORIO_PRESISTENCIA_BASE + codigoMateria + File.separatorChar + "Entregas" + File.separatorChar + codigoGrupo + File.separatorChar;
		
		crearArbolDirectorio(carpetaDestino);
		
		if (mensaje.getPathAdjunto() == null || mensaje.getPathAdjunto().isEmpty()) {
			return "No contiene adjuntos el mail";
		}
		
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

	@Override
	public String puedeEjecutar(Mensaje mesg, Set<Parametro> parametrosParaAccion) {
		return "";
	}

}
