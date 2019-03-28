package com.someshop.intershop.service;

import com.someshop.intershop.model.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
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
