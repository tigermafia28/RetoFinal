package leerXML.clases;

import java.util.HashMap;

public class Convenio {
	public String nombre;
	public int fecha;
	public HashMap<String,Grupo> grupos;
	public HashMap<String,Plus> pluses;
	public int nPagasExtra;
	public int jornadaAnual;
	public float porcentajeHorasExtra;
	public float porcentajeAtep;
}
