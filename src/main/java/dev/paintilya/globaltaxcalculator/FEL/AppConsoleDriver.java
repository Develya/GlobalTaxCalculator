package dev.paintilya.globaltaxcalculator.FEL;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;


public class AppConsoleDriver {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Double input = in.nextDouble();

        String uri = "http://localhost:8083/api/v1/gtc/calculate?netIncome=" + input;
        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpRequest request = HttpRequest.newBuilder(URI.create(uri)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                System.out.println(response.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}