/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Puntuaciones;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 *
 * @author maite
 */
public class Puntuaciones {
    
    private Document doc; 
    private DocumentBuilderFactory factory ;
    private  DocumentBuilder builder;
    private DOMImplementation dom ; 
    private  Source source; 
    private  Result resultado; 
    private Transformer transformer; 
    private Element raiz;  
    private Element nodoPuntos;  
    private Document document; 
    
    
    public Puntuaciones(){
        
        this.crearEscritura();
        this.crearLectura();
       
    }
    public void crearLectura(){
         try{
        this.factory = DocumentBuilderFactory.newInstance();
        this.builder = factory.newDocumentBuilder();
			
        this.document = this.builder.parse(new File("Info/puntos.xml")); 

 
      //Excepciones en la creacion del documento u errores de lectura  
      }catch (SAXException e){
          
           System.out.println("SAXException");
      }
      catch(ParserConfigurationException pce){
          System.out.println("ParserConfigurationException");
      }
      catch (IOException ioe){
          System.out.println("IOException" + ioe);
      }
    }
    
    public void crearEscritura(){
        try {
            this.factory = DocumentBuilderFactory.newInstance();
                
            this.builder = factory.newDocumentBuilder();
            this.dom = builder.getDOMImplementation();
            this.doc = (Document) this.dom.createDocument(null,  "Sistema", null);
            this.source = new DOMSource(this.doc);
            this.resultado = new StreamResult(new File("Info/puntos.xml"));
                           
         } catch (ParserConfigurationException pce){
                pce.printStackTrace();
         }
        
       
        
       
         
    }
    
    public ArrayList<Integer> LeerPuntuaciones(){
        
         ArrayList<Integer> a = new ArrayList<>(); 
         
          NodeList puntos = this.document.getElementsByTagName("Punto");
          
          System.out.println("lenght : " + puntos.getLength());
           
          try{
             
                for(int i =0; i < puntos.getLength(); i++){
                   
                    Node puntosNode = puntos.item(i);

                    Element puntosElement = (Element) puntosNode; 
                    
                    Integer b ; 

                    b = Integer.parseInt( puntosElement.getElementsByTagName("punto").item(0).getTextContent());
                    System.out.println("b : " + b );
                    a.add(b);
            }
        }catch(NumberFormatException | DOMException e){
            System.out.println("----------------------------------");
            System.out.println("ERROR : " + e);
            System.out.println("----------------------------------");
        }
        
        
        return a; 
        
        
    }
    
    public void guardarPuntuaciones(ArrayList<Integer> puntuacion){
        
        Element nodoDatos = null, nodoPuntoss = null;
        Text texto = null;
        
        System.out.println("guardando datos");
         this.nodoPuntos = this.doc.createElement("Puntaje_sistema");
        //se agrega al documento este elemento, como elemento principal 
        this.doc.getDocumentElement().appendChild(this.nodoPuntos);
        
         //++++ID++++//
        for(int i =0; i < puntuacion.size(); i++){
            nodoPuntoss = this.doc.createElement("Punto"); 
            this.nodoPuntos.appendChild(nodoPuntoss);

            nodoDatos = this.doc.createElement("punto");
           nodoPuntoss.appendChild(nodoDatos);

           //se almacenan los datos individuales de cada concesionario en el docuemnto 
           texto = this.doc.createTextNode(String.valueOf(puntuacion.get(i)));
           nodoDatos.appendChild(texto);
        }
         try{
            this.transformer = TransformerFactory.newInstance().newTransformer();
             this.transformer.transform(this.source, this.resultado);

        } catch (TransformerConfigurationException tce) {
                tce.printStackTrace();

        } catch (TransformerException te) {
                te.printStackTrace();
        }
    }
    
    public void generarTop3(){
        
    }
    
}
