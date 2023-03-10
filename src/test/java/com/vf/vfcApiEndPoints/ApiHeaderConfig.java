package com.vf.vfcApiEndPoints;

import java.util.HashMap;
import java.util.Map;

public class ApiHeaderConfig {

    public static String getBaseURI() {
        return "https://qa.xapi.vfc.com";
    }
    public static Map<String, String> getHeaders() {

        Map<String, String> headers = new HashMap<>();

        headers.put("siteId", "TBL-US");
        headers.put("x-transaction-id", "A v4 style guid");
        headers.put("locale", "en_US");
        headers.put("channel", "ECOMM");
        headers.put("source", "ECOM15");
        headers.put("region", "NORA");
        headers.put("brand", "TBL");// change it to TNF or VANS



       // headers.put("client_id", "4f655085a6ec4b338786d62922667746");
       // headers.put("client_secret", "e327F89fA8294bF3A5b174a64f33bA9d");

       // headers.put("x-usid","43523423432");
        return headers;
    }
}


