package com.someshop.sneakershop.service;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestService {
    public void getProductsByUrl(String keyword) {
        try{
            URL url = new URL("http://svcs.ebay.com/services/search/FindingService/v1?" +
                    "OPERATION-NAME=findItemsAdvanced&" +
                    "SERVICE-VERSION=1.0.0&" +
                    "SECURITY-APPNAME=MishaKos-sneakers-PRD-f16e5579d-1bf649ce&" +
                    "RESPONSE-DATA-FORMAT=XML&" +
                    "GLOBAL-ID=EBAY-US&" +
                    "keywords=gucci&" +
                    "paginationInput.entriesPerPage=15&" +
                    "itemFilter(0).name=MinPrice&" +
                    "itemFilter(0).value=10&" +
                    "categoryId=63861");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = httpURLConnection.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            System.out.println(response.toString());
            Document doc = null;
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = null;

            builder = factory.newDocumentBuilder();
            doc = builder.parse(new InputSource(new StringReader(response.toString())));////////

            System.out.println(doc.getFirstChild().getFirstChild().getTextContent());
            in.close();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
