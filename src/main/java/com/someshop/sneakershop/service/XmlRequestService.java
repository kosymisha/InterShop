package com.someshop.sneakershop.service;

import com.someshop.sneakershop.model.EbayProduct;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

@Service
public class XmlRequestService {
    public List getItemsByUrl(URL url) {
        List<EbayProduct> ebayProductList = new LinkedList<EbayProduct>();
        try{

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            int responseCode = httpURLConnection.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            } in.close();



            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();


            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(new InputSource(new StringReader(response.toString()))); // converting String to XML document

            if (doc.getFirstChild().getFirstChild().getTextContent().equals("Success")) {
                NodeList nList = doc.getElementsByTagName("item"); // getting elements which names are "item"
                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node node = nList.item(temp);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        ebayProductList.add(new EbayProduct(
                                element.getElementsByTagName("title").item(0).getTextContent(),
                                element.getElementsByTagName("primaryCategory").item(0).getLastChild().getTextContent(),
                                element.getElementsByTagName("galleryURL").item(0).getTextContent(),
                                element.getElementsByTagName("viewItemURL").item(0).getTextContent(),
                                element.getElementsByTagName("sellingStatus").item(0).getFirstChild().getAttributes().item(0).getTextContent(),
                                element.getElementsByTagName("sellingStatus").item(0).getFirstChild().getTextContent()
                        ));
                    }
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return ebayProductList;
    }
}
