package app;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {

        /*String URL = "http://export.arxiv.org/api/query?search_query=all:computer";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .build();

        CompletableFuture<HttpResponse<String>> responseFuture = client.sendAsync(
                request, HttpResponse.BodyHandlers.ofString()
        );

        responseFuture.thenAccept(response -> {
            String responseFinal = response.body();
            System.out.println(responseFinal);
        });
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */

    }
}