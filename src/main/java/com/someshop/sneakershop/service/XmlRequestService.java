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
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

@Service
public class XmlRequestService {

    @Autowired
    private ShopService shopService;

    @Autowired
    private XmlParseService xmlParseService;

    public void send(URL url, String method) throws IOException, SAXException, ParserConfigurationException {
        List<Announcement> announcements = new LinkedList<Announcement>();


            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(method);
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) { response.append(inputLine); } in.close();

            xmlParseService.parseEbay(response);

    }
}
