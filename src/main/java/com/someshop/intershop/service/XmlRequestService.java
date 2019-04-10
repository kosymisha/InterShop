package com.someshop.intershop.service;

import com.someshop.intershop.model.Advert;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public interface XmlRequestService {
    List<Advert> getAds(URL url) throws IOException, SAXException, ParserConfigurationException;
    String getCurrencyValue (String currency) throws IOException, ParserConfigurationException, SAXException;
}
