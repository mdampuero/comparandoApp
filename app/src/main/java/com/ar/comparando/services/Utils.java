package com.ar.comparando.services;

public class Utils {
    private Utils() {}

    public static final String BASE_URL = "http://192.168.100.12/api/";

    public static Api getApi() {

        return RetrofitClient.getClient(BASE_URL).create(Api.class);
    }
}
