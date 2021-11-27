package com.sapient.weather.app.clients;

import okhttp3.OkHttpClient;

public class HttpClient {

    private HttpClient() {

    }

    public static OkHttpClient getHttpClient() {
        return HttpClientHolder.HTTP_CLIENT;
    }

    private static class HttpClientHolder {
        private static final OkHttpClient HTTP_CLIENT = new OkHttpClient();
    }
}
