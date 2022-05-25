/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package jo.udk.leerxml;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author usuario
 */
public class LeerXML {
	/*
    public String getFileContent(HttpServletRequest request) {

        String fileContent = "";

        try {
            InputStream input = request.getInputStream();

            byte[] data = new byte[1024];

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            int nRead;
            while ((nRead = input.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }

            buffer.flush();
            fileContent = new String(buffer.toByteArray());

        } catch (IOException ex) {
            System.err.println("Error! " + ex.getMessage());
        }

        return fileContent;
    }
	*/
	
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

            // get <food>
            /*NodeList foodList = doc.getElementsByTagName("food");

            for (int i = 0; i < foodList.getLength(); i++) {

                Node nodeFood = foodList.item(i);

                //<food menu=i>
                Element elementFood = (Element) nodeFood;
                tree += "Menú " + elementFood.getAttribute("menu") + ":\n";

                NodeList courseList = elementFood.getElementsByTagName("course");

                for (int j = 0; j < courseList.getLength(); j++) {

                    Node nodeCourse = courseList.item(j);

                    //<course id=j>
                    Element elementCourse = (Element) nodeCourse;
                    tree += "\tCourse " + elementCourse.getAttribute("id") + ":\n";

                    //<name>
                    String name = elementCourse.getElementsByTagName("name").item(0).getTextContent();

                    //<price currency="x">
                    NodeList nodePrice = elementCourse.getElementsByTagName("price");
                    String price = nodePrice.item(0).getTextContent();
                    String currency = nodePrice.item(0).getAttributes().getNamedItem("currency").getTextContent();

                    //<description>
                    String description = elementCourse.getElementsByTagName("description").item(0).getTextContent();

                    //<calories>
                    String calories = elementCourse.getElementsByTagName("calories").item(0).getTextContent();

                    tree += "\t\tName = " + name + "\n";
                    tree += "\t\tDescription = " + description + "\n";
                    tree += "\t\tPrice = " + price + " " + currency + "\n";
                    tree += "\t\tCalories = " + calories + "\n";
                }
            }
			*/
			/*
			NodeList pluses = doc.getElementsByTagName("plus");
			
			for(int i=0; i<pluses.getLength(); i++){
				Node plus = pluses.item(i);
				Element elementoPlus = (Element) plus;
				
				NodeList hijosPlus = elementoPlus.getChildNodes();

				String nombre = hijosPlus.item(1).getTextContent();
				double cantidad = Double.parseDouble(hijosPlus.item(3).getTextContent());

			}
			*/
			
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
							categoria.salario = Double.parseDouble(elementoNivel.getElementsByTagName("salario").item(0).getTextContent());
							
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
								categoria.salario = Double.parseDouble(elementoSubnivel.getElementsByTagName("salario").item(0).getTextContent());

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

	public static void main(String[] args){
		String nombre = "Nominas.xml";
		File xml = new File(nombre);
		Convenio[] convenios = getXMLContent(xml);
		
		for(Convenio conv : convenios){
			if(conv != null){
				System.out.println(conv.nombre + " " + conv.fecha);
				for(Grupo grup : conv.grupos){
					if(grup != null){
						System.out.println("\t" + grup.numero);
						for(Categoria cat : grup.categorias){
							if(cat != null){
								System.out.println("\t\t" + cat.nivel);
								System.out.println("\t\t\t" + cat.nombre);
								System.out.println("\t\t\t" + cat.salario);
							}
						}
					}
				}
				System.out.println();
			}
		}
	}
	
}
