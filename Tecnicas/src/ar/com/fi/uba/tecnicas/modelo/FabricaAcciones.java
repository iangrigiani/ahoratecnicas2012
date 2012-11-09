package ar.com.fi.uba.tecnicas.modelo;

import ar.com.fi.uba.tecnicas.controlador.EValidacion;
import ar.com.fi.uba.tecnicas.modelo.entidades.Circuito;
import ar.com.fi.uba.tecnicas.modelo.entidades.ComponenteBasico;
import ar.com.fi.uba.tecnicas.modelo.entidades.Demultiplexor;

public class FabricaComponente {
	
	public static ComponenteBasico crearComponenteBasico (int cantEntradas,int cantSalidas,String nombre) throws EValidacion{
		return new ComponenteBasico(cantEntradas,cantSalidas,nombre);
	}
	
	public static Circuito crearCircuito(int cantEntradas,int cantSalidas,String nombre) throws EValidacion{
		return new Circuito(cantEntradas,cantSalidas,nombre);
	}
	
	public static Demultiplexor crearDemultiplexor(int cantEntradas,int cantSalidas,String nombre) throws EValidacion{
		return new Demultiplexor(cantEntradas,cantSalidas,nombre);
	}

}
