/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grupo2.RetoFinal;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author usuario
 */
@Service
public class RetoFinalService {
	public static Convenio[] getXMLContent(File archivo) {

        //String fileContent = getFileContent(request);

        // Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		Convenio[] listaConvenios = new Convenio[1024];

        try {

            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            //dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            //org.w3c.dom.Document doc = db.parse(new InputSource(new StringReader(fileContent)));
			org.w3c.dom.Document doc = db.parse(archivo);

            // optional, but recommended
            // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

			NodeList convenios = doc.getElementsByTagName("convenio");
			
			for(int indiceConv = 0; indiceConv < convenios.getLength(); indiceConv++){
				Node nodoConvenio = convenios.item(indiceConv);
				Element elementoConvenio = (Element) nodoConvenio;
				
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
						
						NodeList subniveles = elementoNivel.getElementsByTagName("subnivel");
						if(subniveles.getLength() == 0){
							Categoria categoria = new Categoria();
							categoria.nivel = elementoNivel.getAttribute("numeronivel");
							categoria.nombre = elementoNivel.getElementsByTagName("categoria").item(0).getTextContent();
							categoria.salario = Float.parseFloat(elementoNivel.getElementsByTagName("salario").item(0).getTextContent());
							
							cat[indiceNivAux] = categoria;
						}
						else{
							int indiceSub;
							for(indiceSub = 0; indiceSub < subniveles.getLength(); indiceSub++){
								Node nodoSubnivel = subniveles.item(indiceSub);
								Element elementoSubnivel = (Element) nodoSubnivel;
								
								Categoria categoria = new Categoria();
								categoria.nivel = elementoNivel.getAttribute("numeronivel") + elementoSubnivel.getAttribute("letra");
								categoria.nombre = elementoSubnivel.getElementsByTagName("categoria").item(0).getTextContent();
								categoria.salario = Float.parseFloat(elementoSubnivel.getElementsByTagName("salario").item(0).getTextContent());

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
				
				Convenio convenio = new Convenio();
				convenio.nombre = elementoConvenio.getAttribute("nombre");
				convenio.fecha = Integer.parseInt(elementoConvenio.getAttribute("fecha"));
				convenio.grupos = grup;
				
				listaConvenios[indiceConv] = convenio;
			}
			
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        return listaConvenios;
    }
}
