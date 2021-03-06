package com.hfad.gadsleaderboard;

public class ApiUtils {
    private ApiUtils() {
    }

    public static final String BASE_URL = "https://docs.google.com/forms/d/e/";

    public static APIService getAPIService() {
        return PostClient.getClient(BASE_URL).create(APIService.class);
    }
}