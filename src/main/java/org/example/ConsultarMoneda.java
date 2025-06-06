package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultarMoneda {

    String urlBase = "https://v6.exchangerate-api.com/v6/";
    String apiKey = "d9fdac83ce5a794a0bb8dd2b";

    public String ConvertirMonedas(String monedaBase) {
        URI direccion = URI.create(urlBase + apiKey + "/latest/" + monedaBase);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body();
            } else {
                throw new RuntimeException("Respuesta inválida: " + response.statusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener datos de la API: " + e.getMessage(), e);


        }
    }
};
