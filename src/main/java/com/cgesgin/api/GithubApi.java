package com.cgesgin.api;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GithubApi {

    private HttpClient httpClient;

    public GithubApi() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public String getUserEvents(String username) throws IOException, InterruptedException, URISyntaxException{
        
        String url = "https://api.github.com/users/"+ username +"/events";
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("application", "application/vnd.github+json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
         if (response.statusCode() == 200) {
            return response.body();
        } else {
            return "Hata: " + response.statusCode();
        }
    }
}