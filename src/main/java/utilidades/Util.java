package utilidades;

import baseDatos.PlusBD;
import baseDatos.RecibeBD;
import java.util.ArrayList;
import leerXML.clases.Plus;
import nomina.Nomina;

import static leerXML.enums.Contrato.TEMPORAL;
import static leerXML.enums.Percepcion.SALARIAL;

public class Util {
	public static Nomina calculaNomina(Datos datos) {
		Nomina nomina = new Nomina();

		/*
		nomina.diaInicio = datos.diaInicio;
		nomina.diaFinal = datos.diaFinal;
		nomina.totalDias = nomina.diaFinal - nomina.diaInicio + 1;

		nomina.nHorasDiarias = datos.nHorasDiarias;
		*/

		nomina.nHorasTotales = datos.nHorasTotales;
		nomina.nHorasExtra = datos.nHorasExtra;
		nomina.salarioAnual = datos.salarioAnual;
		nomina.nPagasExtra = datos.nPagasExtra;
		nomina.jornadaAnual = datos.jornadaAnual;

		nomina.salarioMensual = nomina.salarioAnual / (12 + nomina.nPagasExtra);
		nomina.salarioPorHora = nomina.salarioAnual / nomina.jornadaAnual;
		nomina.salarioBase = nomina.salarioPorHora * nomina.nHorasTotales;
		nomina.horasExtra = nomina.nHorasExtra * (nomina.salarioPorHora * (datos.porcentajeHorasExtra / 100));

		nomina.pagaExtra = 0;
		if(datos.prorrateado)
			nomina.pagaExtra = nomina.salarioMensual * nomina.nPagasExtra / 12;

		nomina.plusesSalariales = new ArrayList<>();
		nomina.plusesNoSalariales = new ArrayList<>();
		nomina.totalPlusesSalariales = 0;
		nomina.totalPlusesNoSalariales = 0;

		/*for(int i = 0; i < datos.pluses.length; i++) {
			if (datos.pluses[i] != null) {
				if (datos.pluses[i].plus.percepcion == SALARIAL) {
					nomina.plusesSalariales[i] = datos.pluses[i];
					nomina.totalPlusesSalariales += datos.pluses[i].total;
				}
				else {
					nomina.plusesNoSalariales[i] = datos.pluses[i];
					nomina.totalPlusesNoSalariales += datos.pluses[i].total;
				}
			}
		}*/
		if(datos.pluses != null)
			for(PlusCalculado plus : datos.pluses)
				if(plus.plus.percepcion == SALARIAL)
					nomina.plusesSalariales.add(plus);
				else
					nomina.plusesNoSalariales.add(plus);




		nomina.totalDevengado = nomina.salarioBase + nomina.pagaExtra + nomina.totalPlusesSalariales + nomina.totalPlusesNoSalariales + nomina.horasExtra;

		nomina.bccc = nomina.salarioBase + nomina.pagaExtra + nomina.totalPlusesSalariales;
		nomina.bccp = nomina.bccc + nomina.horasExtra;
		nomina.bhe = nomina.horasExtra;

		nomina.contingenciasComunes = 0.047f * nomina.bccc;
		nomina.desempleo = 0.0155f * nomina.bccp;
		if(datos.contrato == TEMPORAL)
			nomina.desempleo = 0.016f * nomina.bccp;
		nomina.fp = 0.001f * nomina.bccp;
		nomina.aportacionesHorasExtra = 0.047f * nomina.bhe;

		nomina.totalAportaciones = nomina.contingenciasComunes + nomina.desempleo + nomina.fp + nomina.aportacionesHorasExtra;

		nomina.irpf = (datos.irpf / 100) * nomina.bccp;

		nomina.totalADeducir = nomina.totalAportaciones + nomina.irpf;

		nomina.neto = nomina.totalDevengado - nomina.totalADeducir;

		nomina.contingenciasComunesEmpresa = 0.236f * nomina.bccc;
		nomina.atep = nomina.bccp * (datos.porcentajeAtep / 100);
		nomina.desempleoEmpresa = 0.055f * nomina.bccp;
		if(datos.contrato == TEMPORAL)
			nomina.desempleoEmpresa = 0.067f * nomina.bccp;
		nomina.fpEmpresa = 0.006f * nomina.bccp;
		nomina.fogasa = 0.002f * nomina.bccp;
		nomina.cotizacionHorasExtra = 0.236f * nomina.bhe;

		return nomina;
	}

	/*public static PlusCalculado[] calculaPluses(Plus[] pluses, PlusBD[] plusesBD){
		PlusCalculado[] plusesCalculados = new PlusCalculado[1024];

		for(int indicePlusBD = 0; plusesBD[indicePlusBD] != null; indicePlusBD++){
			plusesCalculados[indicePlusBD] = calculaPlus(pluses, plusesBD[indicePlusBD]);
		}

		return plusesCalculados;
	}

	public static PlusCalculado calculaPlus(Plus[] pluses, PlusBD plusBD){
		PlusCalculado plusCalculado = new PlusCalculado();
		for(int indicePlus = 0; pluses[indicePlus] != null; indicePlus++){
			if(plusBD.nombre.equals(pluses[indicePlus].nombre)){
				plusCalculado.plus = pluses[indicePlus];
				plusCalculado.valor = plusBD.valor;
				plusCalculado.total = plusCalculado.plus.cantidad * plusCalculado.valor;
			}
		}
		return plusCalculado;
	}*/
	
	public static ArrayList<PlusCalculado> calculaPluses(ArrayList<Plus> pluses, ArrayList<PlusBD> plusesBD, ArrayList<RecibeBD> recibe) {
		ArrayList<PlusCalculado> plusesCalculados = new ArrayList<>();
		
		for(RecibeBD r : recibe) {
			PlusCalculado pc = new PlusCalculado();
			for(PlusBD pb : plusesBD) {
				if(r.idplus == pb.id) {
					for(Plus p : pluses){
						if(pb.nombre.equals(p.nombre)) {
							pc.plus = p;
							pc.valor = r.valor;
							pc.total = pc.valor * pc.plus.cantidad;
						}
					}
				}
			}
			plusesCalculados.add(pc);
		}
		
		return plusesCalculados;
	}

}
