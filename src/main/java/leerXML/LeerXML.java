package leerXML;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import leerXML.clases.Categoria;
import leerXML.clases.Convenio;
import leerXML.clases.Grupo;
import leerXML.clases.Plus;
import leerXML.enums.Ambito;
import static leerXML.enums.Contrato.*;
import leerXML.enums.Retribucion;
import nomina.Nomina;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import utilidades.Datos;

public class LeerXML {
	
    public static Convenio[] getXMLContent(File archivo) {

        // Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		Convenio[] listaConvenios = new Convenio[1024];

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
				Grupo[] grup = new Grupo[1024];
				
				for(int indiceGrup = 0; indiceGrup < grupos.getLength(); indiceGrup++){
					Node nodoGrupo = grupos.item(indiceGrup);
					Element elementoGrupo = (Element) nodoGrupo;
					
					NodeList niveles = elementoGrupo.getElementsByTagName("nivel");
					Categoria[] cat = new Categoria[1024];
					
					for(int indiceNiv = 0, indiceNivAux = 0; indiceNiv < niveles.getLength(); indiceNiv++, indiceNivAux++){
						Node nodoNivel = niveles.item(indiceNiv);
						Element elementoNivel = (Element) nodoNivel;

						NodeList nodosPlus = elementoNivel.getElementsByTagName("plus");
						Plus[] pluses = new Plus[1024];

						if(nodosPlus != null){
							for(int indicePlus = 0; indicePlus < nodosPlus.getLength(); indicePlus++){
								Node nodoPlus = nodosPlus.item(indicePlus);
								Element elementoPlus = (Element) nodoPlus;

								Plus plus = new Plus();
								plus.nombre = elementoPlus.getElementsByTagName("nombre").item(0).getTextContent();
								plus.cantidad = Float.parseFloat(elementoPlus.getElementsByTagName("cantidad").item(0).getTextContent());
								plus.retribucion = Retribucion.valueOf(elementoPlus.getElementsByTagName("retribucion").item(0).getTextContent().toUpperCase());
								plus.ambito = Ambito.ESPECIFICO;

								pluses[indicePlus] = plus;
							}
						}

						NodeList subniveles = elementoNivel.getElementsByTagName("subnivel");
						if(subniveles.getLength() == 0){
							Categoria categoria = new Categoria();
							categoria.nivel = elementoNivel.getAttribute("numeronivel");
							categoria.nombre = elementoNivel.getElementsByTagName("categoria").item(0).getTextContent();
							categoria.salario = Float.parseFloat(elementoNivel.getElementsByTagName("salario").item(0).getTextContent());
							categoria.pluses = pluses;

							cat[indiceNivAux] = categoria;
						}
						else{
							int indiceSub;
							for(indiceSub = 0; indiceSub < subniveles.getLength(); indiceSub++){
								Node nodoSubnivel = subniveles.item(indiceSub);
								Element elementoSubnivel = (Element) nodoSubnivel;

								nodosPlus = elementoSubnivel.getElementsByTagName("plus");
								pluses = new Plus[1024];

								if(nodosPlus != null){
									for(int indicePlus = 0; indicePlus < nodosPlus.getLength(); indicePlus++){
										Node nodoPlus = nodosPlus.item(indicePlus);
										Element elementoPlus = (Element) nodoPlus;

										Plus plus = new Plus();
										plus.nombre = elementoPlus.getElementsByTagName("nombre").item(0).getTextContent();
										plus.cantidad = Float.parseFloat(elementoPlus.getElementsByTagName("cantidad").item(0).getTextContent());
										plus.retribucion = Retribucion.valueOf(elementoPlus.getElementsByTagName("retribucion").item(0).getTextContent().toUpperCase());
										plus.ambito = Ambito.ESPECIFICO;

										pluses[indicePlus] = plus;
									}
								}

								Categoria categoria = new Categoria();
								categoria.nivel = elementoNivel.getAttribute("numeronivel") + elementoSubnivel.getAttribute("letra");
								categoria.nombre = elementoSubnivel.getElementsByTagName("categoria").item(0).getTextContent();
								categoria.salario = Float.parseFloat(elementoSubnivel.getElementsByTagName("salario").item(0).getTextContent());
								categoria.pluses = pluses;

								cat[indiceNivAux + indiceSub] = categoria;
							}
							
							indiceNivAux += indiceSub - 1;
						}
					}
					
					Grupo grupo = new Grupo();
					grupo.numero = Short.parseShort(elementoGrupo.getAttribute("numerogrupo"));
					grupo.categorias = cat;
					
					grup[indiceGrup] = grupo;
				}

				NodeList nodosPlus = null;
				Plus[] pluses = new Plus[1024];

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
						plus.retribucion = Retribucion.valueOf(elementoPlus.getElementsByTagName("retribucion").item(0).getTextContent().toUpperCase());
						plus.ambito = Ambito.GENERAL;

						pluses[indicePlus] = plus;
					}
				}

				Convenio convenio = new Convenio();
				convenio.nombre = elementoConvenio.getAttribute("nombre");
				convenio.fecha = Integer.parseInt(elementoConvenio.getAttribute("fecha"));
				convenio.grupos = grup;
				convenio.pluses = pluses;
				
				listaConvenios[indiceConv] = convenio;
			}
			
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        return listaConvenios;
    }

	public static void main(String[] args){
		/*String nombre = "Nominas.xml";
		File xml = new File(nombre);
		Convenio[] convenios = getXMLContent(xml);
		
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
									for (Plus plus : cat.pluses) {
										if (plus != null) {
											System.out.println("\t\t\t\t" + plus.nombre);
											System.out.println("\t\t\t\t\t Ámbito: " + plus.ambito.name().toLowerCase());
											System.out.println("\t\t\t\t\t Retribución: " + plus.retribucion.name().toLowerCase());
											System.out.println("\t\t\t\t\t Cantidad: " + plus.cantidad);
										}
									}
								}
							}
						}
					}
				}
				System.out.println("\tPluses");
				for(Plus plus : conv.pluses){
					if(plus != null){
						System.out.println("\t\t" + plus.nombre);
						System.out.println("\t\t\t Ámbito: " + plus.ambito.name().toLowerCase());
						System.out.println("\t\t\t Retribución: " + plus.retribucion.name().toLowerCase());
						System.out.println("\t\t\t Cantidad: " + plus.cantidad);
					}
				}
				System.out.println();
			}
		}
		*/
		
		Datos datos = new Datos();
		datos.diaInicio = 1;
		datos.diaFinal = 30;
		datos.nHorasDiarias = 8;
		datos.nHorasExtra = 0;
		datos.salarioAnual = 26400.00f;
		datos.nPagasExtra = 3;
		datos.jornadaAnual = 1790;
		datos.irpf = 12f;
		datos.porcentajeHorasExtra = 50f;
		datos.porcentajeAtep = 1.6f;
		datos.prorrateado = true;
		datos.contrato = INDEFINIDO;
		datos.pluses = new Plus[1024];
		
		Nomina nomina = new Nomina(datos);
		System.out.println("ole");
	}
	
}
