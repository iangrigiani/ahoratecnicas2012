/**
 * 
 */
package ar.com.fi.uba.tecnicas.controlador.comun;

import java.util.Date;

/**
 * @author ramiro
 *
 */
public class CuatrimestreHelper {
	
	@SuppressWarnings("deprecation")
	public static Boolean pertenecePrimerCuatrimestre(Date fecha) {
		Date limiteSuperiorPrimerCuatri = new Date(fecha.getYear(), 6, 30);
		Date limiteInferiorPrimerCuatri = new Date(fecha.getYear(), 1, 1);
		if (fecha.compareTo(limiteInferiorPrimerCuatri) >= 0 && fecha.compareTo(limiteSuperiorPrimerCuatri) <= 0) {
			return true;
		}
		return  false; 
	}
	
	@SuppressWarnings("deprecation")
	public static Boolean perteneceSegundoCuatrimestre(Date fecha) {
		Date limiteSuperiorSegundoCuatri = new Date(fecha.getYear(), 12, 31);
		Date limiteInferiorSegundoCuatri = new Date(fecha.getYear(), 7, 1);
		if (fecha.compareTo(limiteInferiorSegundoCuatri) >= 0 && fecha.compareTo(limiteSuperiorSegundoCuatri) <= 0) {
			return true;
		}
		return  false;
	}
	
}
