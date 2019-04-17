package com.someshop.intershop.service.impl;

import com.someshop.intershop.model.Advert;
import com.someshop.intershop.service.XmlParseService;
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
import java.util.LinkedList;
import java.util.List;

@Service
public class XmlParseServiceImpl implements XmlParseService {

    @Autowired
    private AdvertServiceImpl advertServiceImpl;

    public List<Advert> parseEbay (StringBuffer response) throws IOException, SAXException, ParserConfigurationException {
        List<Advert> adverts = new LinkedList<Advert>();
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(response.toString())));
        if (doc.getFirstChild().getFirstChild().getTextContent().equals("Success")) { //if request was successful
            NodeList nList = doc.getElementsByTagName("item"); // getting elements which names are "item"
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node node = nList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.getElementsByTagName("galleryURL").getLength() != 0) {
                        adverts.add(
                                advertServiceImpl.create(
                                        element.getElementsByTagName("itemId").item(0).getTextContent(),
                                        element.getElementsByTagName("sellingStatus").item(0).getFirstChild().getAttributes().item(0).getTextContent(),
                                        element.getElementsByTagName("sellingStatus").item(0).getFirstChild().getTextContent(),
                                        element.getElementsByTagName("viewItemURL").item(0).getTextContent(),
                                        element.getElementsByTagName("title").item(0).getTextContent(),
                                        element.getElementsByTagName("primaryCategory").item(0).getFirstChild().getTextContent(),
                                        element.getElementsByTagName("primaryCategory").item(0).getLastChild().getTextContent(),
                                        "eBay",
                                        element.getElementsByTagName("galleryURL").item(0).getTextContent()));
                    }
                }
            }
        }
        return adverts;
    }

    @Override
    public String parseCurrency(StringBuffer response, String currency) throws IOException, SAXException, ParserConfigurationException {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(response.toString())));
        NodeList nList = doc.getElementsByTagName("Valute");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item((temp));
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                if (element.getElementsByTagName("CharCode").item(0).getTextContent().equals(currency)) {
                    return element.getElementsByTagName("Value").item(0).getTextContent();
                }
            }
        }
        return null;
    }
}
