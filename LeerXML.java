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
	
    public static String getXMLContent(File archivo) {

        String tree = "";

        //String fileContent = getFileContent(request);

        // Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

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

            tree += "Root Element: " + doc.getDocumentElement().getNodeName() + "\n";
            tree += "=======\n";

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
			
			NodeList pluses = doc.getElementsByTagName("plus");
			
			for(int i=0; i<pluses.getLength(); i++){
				Node plus = pluses.item(i);
				Element elementoPlus = (Element) plus;
				tree += "Plus " + (i+1) + "\n";
				
				String nombre = elementoPlus.getElementsByTagName("nombre").item(0).getTextContent();
				double cantidad = Double.parseDouble(elementoPlus.getElementsByTagName("cantidad").item(0).getTextContent());
				
				tree += "\nNombre: " + nombre;
				tree += "\nCantidad: " + cantidad + "\n=======\n";
			}
			
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.err.println("Error: " + e.getMessage());
            tree += "NO CONTENT";
        }

        return tree;
    }

	public static void main(String[] args){
		String nombre="Nominas.xml";
		File xml = new File(nombre);
		System.out.println(getXMLContent(xml));
	}
	
}
