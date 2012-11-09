package ar.com.fi.uba.tecnicas.controlador.cadena;

/*
 * modificado por guillermo pantaleo
 *
 **/

public interface Eslabon
{
	public abstract void addEslabon(Eslabon c);
	public abstract void sendToEslabon(String mesg) throws Exception;
	public Eslabon getEslabon();
	public void setRegex(String regex);
	public abstract void setMediador(Mediador mediador);
	public abstract Mediador getMediador();
}
