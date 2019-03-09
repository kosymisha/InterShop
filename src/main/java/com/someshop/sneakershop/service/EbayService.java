package com.someshop.sneakershop.service;

import com.someshop.sneakershop.model.EbayProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.net.URL;
import java.util.LinkedList;
import java.util.List;

@Service
public class EbayService {

    @Autowired
    XmlRequestService xmlRequestService;

    private static String urlPattern = "http://svcs.ebay.com/services/search/FindingService/v1?" +
            "OPERATION-NAME=findItemsAdvanced" +
            "&SERVICE-VERSION=1.0.0" +
            "&SECURITY-APPNAME=MishaKos-sneakers-PRD-f16e5579d-1bf649ce" +
            "&RESPONSE-DATA-FORMAT=XML" +
            "&GLOBAL-ID=EBAY-US" +
            "&paginationInput.entriesPerPage=15";

    public List getItems(String keyword, String minPrice, String maxPrice, String categoryId ) {
        List<EbayProduct> ebayProductList = new LinkedList<EbayProduct>();
        try {

            if (keyword != "" && keyword != null) { urlPattern += "&keywords=" + keyword.replace(" ", "-"); }
            if (minPrice != null) { urlPattern += "&itemFilter(0).name=MinPrice&itemFilter(0).value=" + minPrice; }
            if (maxPrice != null) { urlPattern += "&itemFilter(1).name=MaxPrice&itemFilter(1).value=" + maxPrice; }
            if (categoryId != null) { urlPattern += "&categoryId=" + categoryId; }

            ebayProductList = xmlRequestService.getItemsByUrl(new URL(urlPattern));

        } catch (Exception e) { e.printStackTrace(); }

        return ebayProductList;
    }

}
/*
                    "http://svcs.ebay.com/services/search/FindingService/v1?" +
                    "OPERATION-NAME=findItemsAdvanced" +
                    "&SERVICE-VERSION=1.0.0" +
                    "&SECURITY-APPNAME=MishaKos-sneakers-PRD-f16e5579d-1bf649ce" +
                    "&RESPONSE-DATA-FORMAT=XML" +
                    "&GLOBAL-ID=EBAY-US" +
                    "&keywords=gucci" +
                    "&paginationInput.entriesPerPage=15" +
                    "&itemFilter(0).name=MinPrice" +
                    "&itemFilter(0).value=10" +
                    "&itemFilter(1).name=MaxPrice" +
                    "&itemFilter(1).value=150" +
                    "&categoryId=63861"
*/