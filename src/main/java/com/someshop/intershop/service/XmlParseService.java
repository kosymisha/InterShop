package com.someshop.intershop.service;

import com.someshop.intershop.model.Advert;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface XmlParseService {
    List<Advert> parseEbay (StringBuffer response) throws IOException, SAXException, ParserConfigurationException;
}
