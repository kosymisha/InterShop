package com.someshop.intershop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

@Service
public class XmlParseService {
    @Autowired
    private AnnouncementService announcementService;

    public void parseEbay (StringBuffer response) throws IOException, SAXException, ParserConfigurationException {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(response.toString())));
        if (doc.getFirstChild().getFirstChild().getTextContent().equals("Success")) { //if request was successful
            NodeList nList = doc.getElementsByTagName("item"); // getting elements which names are "item"
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node node = nList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    announcementService.create(
                            element.getElementsByTagName("itemId").item(0).getTextContent(),
                            element.getElementsByTagName("sellingStatus").item(0).getFirstChild().getAttributes().item(0).getTextContent(),
                            element.getElementsByTagName("sellingStatus").item(0).getFirstChild().getTextContent(),
                            element.getElementsByTagName("viewItemURL").item(0).getTextContent(),
                            element.getElementsByTagName("title").item(0).getTextContent(),
                            element.getElementsByTagName("primaryCategory").item(0).getFirstChild().getTextContent(),
                            element.getElementsByTagName("primaryCategory").item(0).getLastChild().getTextContent(),
                            "eBay",
                            element.getElementsByTagName("galleryURL").item(0).getTextContent());
                }
            }
        }
    }
}
