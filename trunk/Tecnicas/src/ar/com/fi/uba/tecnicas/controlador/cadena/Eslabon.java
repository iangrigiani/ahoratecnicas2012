package ar.com.fi.uba.tecnicas.controlador.cadena;

/*
 * modificado por guillermo pantaleo
 *
 **/

public interface Eslabon
{ 
	
	abstract void addEslabon(Eslabon c);
	abstract void sendToEslabon(String mesg) throws Exception;
	Eslabon getEslabon();
	void setRegex(String regex);
	abstract void setMediador(Mediador mediador);
	abstract Mediador getMediador();
	abstract void setNombre(String nombre);
	abstract String getNombre();
}
