package com.example.testauth.remote;

public class BaseApi {

    private BaseApi () {

    }


    public static final String base_url = "http://192.168.1.7:1997/";

    public static ApiService getDataService() {
        return ApiClient.getClient(base_url).create(ApiService.class);
    }


}
