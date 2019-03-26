package com.someshop.sneakershop.service;

import com.someshop.sneakershop.model.Announcement;
import com.someshop.sneakershop.model.Category;
import com.someshop.sneakershop.model.Product;
import com.someshop.sneakershop.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

@Service
public class XmlRequestService {

    @Autowired
    private ShopService shopService;

    @Autowired
    private AnnouncementService announcementService;

    public List getItemsByUrl(URL url) {
        List<Announcement> announcements = new LinkedList<Announcement>();
        try{

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) { response.append(inputLine); } in.close();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(response.toString()))); // converting String to XML document
            if (doc.getFirstChild().getFirstChild().getTextContent().equals("Success")) {
                NodeList nList = doc.getElementsByTagName("item"); // getting elements which names are "item"
                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node node = nList.item(temp);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        announcements.add(new Announcement(
                                element.getElementsByTagName("sellingStatus").item(0).getFirstChild().getAttributes().item(0).getTextContent(),
                                new BigDecimal(element.getElementsByTagName("sellingStatus").item(0).getFirstChild().getTextContent()),
                                1, //views
                                element.getElementsByTagName("viewItemURL").item(0).getTextContent(),
                                new Product(
                                        element.getElementsByTagName("title").item(0).getTextContent(),
                                        new Category(new Long(element.getElementsByTagName("primaryCategory").item(0).getFirstChild().getTextContent()),
                                                element.getElementsByTagName("primaryCategory").item(0).getLastChild().getTextContent()),
                                        element.getElementsByTagName("galleryURL").item(0).getTextContent(),
                                        "For more information click in URL."),
                                new Shop()
                        ));
                    }
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return announcements;
    }
}
