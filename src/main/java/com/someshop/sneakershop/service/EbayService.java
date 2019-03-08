package com.someshop.sneakershop.service;

import com.someshop.sneakershop.model.EbayProduct;
import org.springframework.stereotype.Service;


import java.util.LinkedList;
import java.util.List;

@Service
public class EbayService extends RequestService {

    private static String url = "";

    public List getItems(String keyword) {

        List<EbayProduct> list = new LinkedList<EbayProduct>();
        list.add(new EbayProduct("adidas iniki", "shoes", "photo.jpg", "product.url", "USD", "115"));
        list.add(new EbayProduct("adidas nmd", "shoes", "photo.jpg", "product.url", "USD", "150"));
        list.add(new EbayProduct("adidas iniki", "shoes", "photo.jpg", "product.url", "USD", "115"));
        list.add(new EbayProduct("adidas boost", "shoes", "photo.jpg", "product.url", "USD", "230"));



        return list;
    }

}
/*
http://svcs.ebay.com/services/search/FindingService/v1?
    OPERATION-NAME=findItemsAdvanced&
    SERVICE-VERSION=1.0.0&
    SECURITY-APPNAME=MishaKos-sneakers-PRD-f16e5579d-1bf649ce&
    RESPONSE-DATA-FORMAT=XML&
    GLOBAL-ID=EBAY-US&
    keywords=gucci&
    paginationInput.entriesPerPage=15&
    itemFilter(0).name=MinPrice&
    itemFilter(0).value=10&
    categoryId=63861
    import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpURLConnectionExample {

	private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {

		HttpURLConnectionExample http = new HttpURLConnectionExample();

		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet();

		System.out.println("\nTesting 2 - Send Http POST request");
		http.sendPost();

	}

	// HTTP GET request
	private void sendGet() throws Exception {

		String url = "http://www.google.com/search?q=mkyong";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}
}
* */