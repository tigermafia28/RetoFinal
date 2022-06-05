package nomina;

import leerXML.clases.Plus;
import static leerXML.enums.Contrato.*;
import static leerXML.enums.Percepcion.*;
import utilidades.Datos;
import utilidades.PlusCalculado;

public class Nomina {

	// Periodo de liquidación y otros datos
	public int diaInicio;
	public int diaFinal;
	public int totalDias; // totalDias = diaFinal - diaInicio + 1
	public int nHorasDiarias;
	public int nHorasExtra;

	// Datos anuales
	public float salarioAnual;
	public int nPagasExtra;
	public int jornadaAnual;

	// Devengos
	public float salarioMensual; // Salario mensual = Salario bruto anual / numero de pagas(12 + extra)
	public float salarioPorHora; // Salario/hora = Salario mensual / 30(dias de un mes) / 8(horas de una jornada completa)
	public float salarioBase; // Salario base = Salario/hora * numero de horas diarias * numero de dias
	public float pagaExtra; // Paga extraordinaria = Salario mensual * Numero de pagas extras / 12
	public float horasExtra; // (Salario bruto anual / número de pagas(12 + extra)) * porcentaje de aumento horas extra

	// Pluses
	public PlusCalculado[] plusesSalariales;
	public float totalPlusesSalariales;
	public PlusCalculado[] plusesNoSalariales;
	public float totalPlusesNoSalariales;

	public float totalDevengado; // Salario base + pagaExtra + pluses + horas extra

	//Deducciones
	//Aportaciones
	public float contingenciasComunes;	// 4,70% de la BCCC
	public float desempleo; // 1,55% BCCP (indefinidos) 1,60% (temporales)
	public float fp; // 0,10% de la BCCP
	public float aportacionesHorasExtra; // 4,70% de la BHE

	public float totalAportaciones;

	public float irpf; // Se aplica el procentaje dado por Hacienda

	public float totalADeducir; // totalADeducir = totalAportaciones + irpf

	// Cálculo neto
	public float neto; // totalDevengado - totalADeducir

	// Bases de cotización
	public float bccc; // Todos los devengos que cotizan + la parte proporcional de pagas extras, menos complementos que no cotizan y horas extraordinarias.
	public float bccp; // BCCC + la cantidad recibida por horas extras
	public float bhe; // La cantidad recibida por horas extras

	// Cotización (aportación empresa)
	public float contingenciasComunesEmpresa; // 23,6% de la BCCC
	public float atep; // Según el grado de peligrosidad de la actividad
	public float desempleoEmpresa; // 5,50% BCCP (indefinidos) 6,70% BCCP (temporales)
	public float fpEmpresa; // 0,6% de la BCCP
	public float fogasa; // 0,2% de la BCCP
	public float cotizacionHorasExtra; // 23,6% de la BHE


	// Constructores
/*
	public Nomina(Datos datos){
		diaInicio = datos.diaInicio;
		diaFinal = datos.diaFinal;
		totalDias = diaFinal - diaInicio + 1;

		nHorasDiarias = datos.nHorasDiarias;
		nHorasExtra = datos.nHorasExtra;
		salarioAnual = datos.salarioAnual;
		nPagasExtra = datos.nPagasExtra;
		jornadaAnual = datos.jornadaAnual;

		salarioMensual = salarioAnual / (12 + nPagasExtra);
		salarioPorHora = salarioMensual / 30 / 8;
		salarioBase = salarioPorHora * nHorasDiarias * totalDias;
		horasExtra = nHorasExtra * (salarioAnual / jornadaAnual * (datos.porcentajeHorasExtra / 100));

		pagaExtra = 0;
		if(datos.prorrateado)
			pagaExtra = salarioMensual * nPagasExtra / 12;

		plusesSalariales = new Plus[datos.pluses.length];
		plusesNoSalariales = new Plus[datos.pluses.length];
		totalPlusesSalariales = 0;
		totalPlusesNoSalariales = 0;

		for(int i = 0; i < datos.pluses.length; i++) {
			if (datos.pluses[i] != null) {
				if (datos.pluses[i].percepcion == SALARIAL) {
					plusesSalariales[i] = datos.pluses[i];
					totalPlusesSalariales += datos.pluses[i].cantidad;
				}
				else {
					plusesNoSalariales[i] = datos.pluses[i];
					totalPlusesNoSalariales += datos.pluses[i].cantidad;
				}
			}
		}

		totalDevengado = salarioBase + pagaExtra + totalPlusesSalariales + totalPlusesNoSalariales + horasExtra;

		bccc = salarioBase + pagaExtra + totalPlusesSalariales;
		bccp = bccc + horasExtra;
		bhe = horasExtra;

		contingenciasComunes = 0.047f * bccc;
		desempleo = 0.0155f * bccp;
		if(datos.contrato == TEMPORAL)
			desempleo = 0.016f * bccp;
		fp = 0.001f * bccp;
		aportacionesHorasExtra = 0.047f * bhe;

		totalAportaciones = contingenciasComunes + desempleo + fp + aportacionesHorasExtra;

		irpf = (datos.irpf / 100) * bccp;

		totalADeducir = totalAportaciones + irpf;

		neto = totalDevengado - totalADeducir;

		contingenciasComunesEmpresa = 0.236f * bccc;
		atep = bccp * (datos.porcentajeAtep / 100);
		desempleoEmpresa = 0.055f * bccp;
		if(datos.contrato == TEMPORAL)
			desempleoEmpresa = 0.067f * bccp;
		fpEmpresa = 0.006f * bccp;
		fogasa = 0.002f * bccp;
		cotizacionHorasExtra = 0.236f * bhe;

	}
	*/

}