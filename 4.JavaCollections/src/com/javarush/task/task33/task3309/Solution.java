package com.javarush.task.task33.task3309;

import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.swing.text.Document;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/*
Комментарий внутри xml
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) {
            StringWriter stringWriter = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(obj, stringWriter);

            /*строку в документ*/
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            org.w3c.dom.Document document = documentBuilder.parse(new InputSource(new StringReader(stringWriter.toString())));
            System.out.println(document);

            /*документ в строку*/
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // здесь мы указываем, что хотим убрать XML declaration:
            // тег <?xml ... ?> и его содержимое
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"yes");
            StringWriter stringWriter1 = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(stringWriter1));
            System.out.println(stringWriter1.toString());

            NodeList nodeList = document.getElementsByTagName("cat");
            for (int i = 0; i < nodeList.getLength(); i++) {
                System.out.println("---------------------------------");
                System.out.println("node " + nodeList.item(i));
                System.out.println("---------------------------------");
            }

        } catch (JAXBException e) {e.printStackTrace();
        } catch (ParserConfigurationException e) {e.printStackTrace();
        } catch (SAXException e) {e.printStackTrace();
        } catch (IOException e) {e.printStackTrace();
        } catch (TransformerConfigurationException e) {e.printStackTrace();
        } catch (TransformerException e) {e.printStackTrace();
        }
        return stringWriter.toString();
    }

    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.name = "Rrrrr";

        System.out.println(toXmlWithComment(cat,"fff", "comment"));
    }
    @XmlType(name = "cat")
    @XmlRootElement
    private static class Cat {
        public Cat() {
        }

        public String name;
    }
}
