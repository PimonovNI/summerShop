package com.example.summerShop.util;

public class UrlUtils {
    public static String imgProductPath(String domain, String name) {
        return "https://" + domain + "/img/product/" + name;
    }

    public static String imgProductGooglePath(String id) {
        return "https://summershop-production.up.railway.app/api/v1/product/img?id=" + id;
    }
}
