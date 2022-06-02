package nomina;

import leerXML.clases.Plus;
import leerXML.enums.Contrato;
import utilidades.Datos;

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
	public Plus[] plusesSalariales;
	public float totalPlusesSalariales;
	public Plus[] plusesNoSalariales;
	public float totalPlusesNoSalariales;

	public float totalDevengado; // Salario base + pagaExtra + pluses

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
		horasExtra = salarioAnual / jornadaAnual * (1 + datos.porcentajeHorasExtra / 100);

		pagaExtra = 0;
		if(datos.prorrateado)
			pagaExtra = salarioMensual * nPagasExtra / 12;

		plusesSalariales = new Plus[datos.pluses.length];
		plusesNoSalariales = new Plus[datos.pluses.length];
		totalPlusesSalariales = 0;
		totalPlusesNoSalariales = 0;

		for(int i = 0; i < datos.pluses.length; i++) {
			if (datos.pluses[i].salarial) {
				plusesSalariales[i] = datos.pluses[i];
				totalPlusesSalariales += datos.pluses[i].cantidad;
			}
			else {
				plusesNoSalariales[i] = datos.pluses[i];
				totalPlusesNoSalariales += datos.pluses[i].cantidad;
			}
		}

		totalDevengado = salarioBase + pagaExtra + totalPlusesSalariales + totalPlusesNoSalariales;

		bccc = salarioBase + pagaExtra + totalPlusesSalariales;
		bccp = bccc + horasExtra;
		bhe = horasExtra;

		contingenciasComunes = 4.7f * bccc;
		desempleo = 1.55f * bccp;
		if(datos.contrato == Contrato.TEMPORAL)
			desempleo = 1.6f * bccp;
		fp = 0.1f * bccp;
		aportacionesHorasExtra = 4.7f * bhe;

		totalAportaciones = contingenciasComunes + desempleo + fp + aportacionesHorasExtra;

		irpf = (datos.irpf / 100) * bccp;

		totalADeducir = totalAportaciones + irpf;

		neto = totalDevengado - totalADeducir;

		contingenciasComunesEmpresa = 23.6f * bccc;
		atep = bccp * (datos.porcentajeAtep / 100);
		desempleoEmpresa = 5.5f * bccp;
		if(datos.contrato == Contrato.TEMPORAL)
			desempleoEmpresa = 6.7f * bccp;
		fpEmpresa = 0.6f * bccp;
		cotizacionHorasExtra = 23.6f * bhe;

	}
}