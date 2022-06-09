package leerXML;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import baseDatos.PlusBD;
import java.util.HashMap;
import leerXML.clases.Categoria;
import leerXML.clases.Convenio;
import leerXML.clases.Grupo;
import leerXML.clases.Plus;
import static leerXML.enums.Ambito.*;
import static leerXML.enums.Contrato.*;
import leerXML.enums.Percepcion;
import leerXML.enums.Retribucion;
import nomina.Nomina;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import utilidades.Datos;
import utilidades.Util;

public class LeerXML {
	
    public static HashMap getXMLContent(File archivo) {

        // Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		HashMap<String,Convenio> listaConvenios = new HashMap<>();

        try {

            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            // dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

			org.w3c.dom.Document doc = db.parse(archivo);

            // optional, but recommended
            // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

			NodeList convenios = doc.getElementsByTagName("convenio");
			
			for(int indiceConv = 0; indiceConv < convenios.getLength(); indiceConv++){
				Node nodoConvenio = convenios.item(indiceConv);
				Element elementoConvenio = (Element) nodoConvenio;

				NodeList nodosHijoConvenio = elementoConvenio.getChildNodes();
				
				NodeList grupos = elementoConvenio.getElementsByTagName("grupo");
				HashMap<String,Grupo> listagrupos = new HashMap<>();
				
				for(int indiceGrup = 0; indiceGrup < grupos.getLength(); indiceGrup++){
					Node nodoGrupo = grupos.item(indiceGrup);
					Element elementoGrupo = (Element) nodoGrupo;
					
					NodeList niveles = elementoGrupo.getElementsByTagName("nivel");
					HashMap<String,Categoria> listacategorias = new HashMap<>();
					
					for(int indiceNiv = 0, indiceNivAux = 0; indiceNiv < niveles.getLength(); indiceNiv++, indiceNivAux++){
						Node nodoNivel = niveles.item(indiceNiv);
						Element elementoNivel = (Element) nodoNivel;

						NodeList nodosPlus = elementoNivel.getElementsByTagName("plus");
						HashMap<String,Plus> listapluses = new HashMap<>();

						if(nodosPlus != null){
							for(int indicePlus = 0; indicePlus < nodosPlus.getLength(); indicePlus++){
								Node nodoPlus = nodosPlus.item(indicePlus);
								Element elementoPlus = (Element) nodoPlus;

								Plus plus = new Plus();
								plus.nombre = elementoPlus.getElementsByTagName("nombre").item(0).getTextContent();
								plus.cantidad = Float.parseFloat(elementoPlus.getElementsByTagName("cantidad").item(0).getTextContent());
								//plus.retribucion = Retribucion.valueOf(elementoPlus.getElementsByTagName("retribucion").item(0).getTextContent().toUpperCase());
								plus.percepcion = Percepcion.valueOf(elementoPlus.getElementsByTagName("percepcion").item(0).getTextContent().toUpperCase());
								//plus.ambito = ESPECIFICO;

								listapluses.put(plus.nombre,plus);
							}
						}

						NodeList subniveles = elementoNivel.getElementsByTagName("subnivel");
						if(subniveles.getLength() == 0){
							Categoria categoria = new Categoria();
							categoria.nivel = elementoNivel.getAttribute("numeronivel");
							categoria.nombre = elementoNivel.getElementsByTagName("categoria").item(0).getTextContent();
							categoria.salario = Float.parseFloat(elementoNivel.getElementsByTagName("salario").item(0).getTextContent());
							categoria.pluses = listapluses;

							listacategorias.put(categoria.nivel,categoria);
						}
						else{
							int indiceSub;
							for(indiceSub = 0; indiceSub < subniveles.getLength(); indiceSub++){
								Node nodoSubnivel = subniveles.item(indiceSub);
								Element elementoSubnivel = (Element) nodoSubnivel;

								nodosPlus = elementoSubnivel.getElementsByTagName("plus");
								listapluses = new HashMap<>();

								if(nodosPlus != null){
									for(int indicePlus = 0; indicePlus < nodosPlus.getLength(); indicePlus++){
										Node nodoPlus = nodosPlus.item(indicePlus);
										Element elementoPlus = (Element) nodoPlus;

										Plus plus = new Plus();
										plus.nombre = elementoPlus.getElementsByTagName("nombre").item(0).getTextContent();
										plus.cantidad = Float.parseFloat(elementoPlus.getElementsByTagName("cantidad").item(0).getTextContent());
										//plus.retribucion = Retribucion.valueOf(elementoPlus.getElementsByTagName("retribucion").item(0).getTextContent().toUpperCase());
										plus.percepcion = Percepcion.valueOf(elementoPlus.getElementsByTagName("percepcion").item(0).getTextContent().toUpperCase());
										//plus.ambito = ESPECIFICO;
										
										
										listapluses.put(plus.nombre, plus);
									}
								}

								Categoria categoria = new Categoria();
								categoria.nivel = elementoNivel.getAttribute("numeronivel") + elementoSubnivel.getAttribute("letra");
								categoria.nombre = elementoSubnivel.getElementsByTagName("categoria").item(0).getTextContent();
								categoria.salario = Float.parseFloat(elementoSubnivel.getElementsByTagName("salario").item(0).getTextContent());
								categoria.pluses = listapluses;

								listacategorias.put(categoria.nivel,categoria);
							}
							
							indiceNivAux += indiceSub - 1;
						}
					}
					
					Grupo grupo = new Grupo();
					grupo.numero = Short.parseShort(elementoGrupo.getAttribute("numerogrupo"));
					grupo.categorias = listacategorias;
					
					listagrupos.put(String.valueOf(grupo.numero),grupo);
				}

				NodeList nodosPlus = null;
				HashMap<String,Plus> listapluses = new HashMap<>();

				for(int indiceNodosHijo = 0; indiceNodosHijo < nodosHijoConvenio.getLength(); indiceNodosHijo++)
					if(nodosHijoConvenio.item(indiceNodosHijo).getNodeName().equals("pluses"))
						nodosPlus = ((Element) nodosHijoConvenio.item(indiceNodosHijo)).getElementsByTagName("plus");

				if(nodosPlus != null){
					for(int indicePlus = 0; indicePlus < nodosPlus.getLength(); indicePlus++){
						Node nodoPlus = nodosPlus.item(indicePlus);
						Element elementoPlus = (Element) nodoPlus;

						Plus plus = new Plus();
						plus.nombre = elementoPlus.getElementsByTagName("nombre").item(0).getTextContent();
						plus.cantidad = Float.parseFloat(elementoPlus.getElementsByTagName("cantidad").item(0).getTextContent());
						//plus.retribucion = Retribucion.valueOf(elementoPlus.getElementsByTagName("retribucion").item(0).getTextContent().toUpperCase());
						plus.percepcion = Percepcion.valueOf(elementoPlus.getElementsByTagName("percepcion").item(0).getTextContent().toUpperCase());
						//plus.ambito = GENERAL;

						listapluses.put(plus.nombre, plus);
					}
				}

				Convenio convenio = new Convenio();
				convenio.nombre = elementoConvenio.getAttribute("nombre");
				convenio.fecha = Integer.parseInt(elementoConvenio.getAttribute("fecha"));
				convenio.grupos = listagrupos;
				convenio.pluses = listapluses;
				convenio.nPagasExtra = Integer.parseInt(elementoConvenio.getElementsByTagName("pagasextra").item(0).getTextContent());
				convenio.jornadaAnual = Integer.parseInt(elementoConvenio.getElementsByTagName("jornadaanual").item(0).getTextContent());
				convenio.porcentajeHorasExtra = Float.parseFloat(elementoConvenio.getElementsByTagName("horasextra").item(0).getTextContent());
				convenio.porcentajeAtep = Float.parseFloat(elementoConvenio.getElementsByTagName("atep").item(0).getTextContent());
				
				listaConvenios.put(convenio.nombre + " " + convenio.fecha, convenio);
			}
			
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        return listaConvenios;
    }

	public static void main(String[] args){
		String nombre = "Nominas.xml";
		File xml = new File(nombre);
		HashMap<String, Convenio> convenios = getXMLContent(xml);
		
		/*
		for(Convenio conv : convenios){
			if(conv != null){
				System.out.println(conv.nombre + " " + conv.fecha);
				for(Grupo grup : conv.grupos){
					if(grup != null){
						System.out.println("\tGrupo " + grup.numero);
						for(Categoria cat : grup.categorias){
							if(cat != null){
								System.out.println("\t\tNivel " + cat.nivel);
								System.out.println("\t\t\t" + cat.nombre);
								System.out.println("\t\t\t" + cat.salario);
								if(cat.pluses[0] != null) {
									System.out.println("\t\t\tPluses");
									for (PlusBD plus : cat.pluses) {
										if (plus != null) {
											System.out.println("\t\t\t\t" + plus.nombre);
											System.out.println("\t\t\t\t\t Ámbito: " + plus.ambito.name().toLowerCase());
											System.out.println("\t\t\t\t\t Retribución: " + plus.retribucion.name().toLowerCase());
											System.out.println("\t\t\t\t\t Percepción: " + plus.percepcion.name().toLowerCase());
											System.out.println("\t\t\t\t\t Cantidad: " + plus.cantidad);
										}
									}
								}
							}
						}
					}
				}
				System.out.println("\tPluses");
				for(PlusBD plus : conv.pluses){
					if(plus != null){
						System.out.println("\t\t" + plus.nombre);
						System.out.println("\t\t\t Ámbito: " + plus.ambito.name().toLowerCase());
						System.out.println("\t\t\t Retribución: " + plus.retribucion.name().toLowerCase());
						System.out.println("\t\t\t Percepción: " + plus.percepcion.name().toLowerCase());
						System.out.println("\t\t\t Cantidad: " + plus.cantidad);
					}
				}
				System.out.println("\t Número de pagas extra: " + conv.nPagasExtra);
				System.out.println("\t Jornada anual: " + conv.jornadaAnual);
				System.out.println("\t Porcentaje horas extra: " + conv.porcentajeHorasExtra);
				System.out.println("\t Porcentaje AT/EP: " + conv.porcentajeAtep);
				System.out.println();
			}
		}
		*/

		/*
		PlusBD[] plusesBD = new PlusBD[1024];
		PlusBD plusBD = new PlusBD();
		plusBD.nombre = "Plus de transporte";
		plusBD.valor = 30;

		PlusBD plusBD2 = new PlusBD();
		plusBD2.nombre = "Dieta media";
		plusBD2.valor = 30;

		plusesBD[0] = plusBD;
		plusesBD[1] = plusBD2;

		*/


		Convenio c1 = convenios.get("Industria del metal 2022");
		Grupo g1 = c1.grupos.get("3");
		Categoria cat1 = g1.categorias.get("1");

		Datos datos = new Datos();
		//datos.diaInicio = 1; //bd
		//datos.diaFinal = 30; //bd
		//datos.nHorasDiarias = 8; //bd
		datos.nHorasTotales = 150;
		datos.nHorasExtra = 0; //bd
		//datos.salarioAnual = convenios[0].grupos[1].categorias[0].salario; //xml, categoria
		datos.salarioAnual = cat1.salario;
		datos.nPagasExtra = c1.nPagasExtra; //xml, convenio
		datos.jornadaAnual = c1.jornadaAnual; //xml, convenio
		datos.irpf = 2f; //bd o 12% ?
		datos.porcentajeHorasExtra = c1.porcentajeHorasExtra; //xml, convenio
		datos.porcentajeAtep = c1.porcentajeAtep; //xml, convenio
		datos.prorrateado = true; //bd
		datos.contrato = INDEFINIDO; //bd
		//datos.pluses = Util.calculaPluses(c1.pluses, plusesBD); //bd? + xml
		
		
		Nomina nomina = Util.calculaNomina(datos);
		System.out.println("ole");
		
	}
	
}
