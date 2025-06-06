package org.example;

import com.google.gson.Gson;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MonedasUsar {

    List<String> monedasFiltradas = Arrays.asList("ARS", "BOB", "BRL", "CLP", "COP", "USD");
    Map<String, Double> tasas;

    public boolean cargarTasas(String jsonResponse) {

        Gson gson = new Gson();

        // Convertir el JSON a un objeto de tipo ExchangeRateResponse
        RecordMoneda response = gson.fromJson(jsonResponse, RecordMoneda.class);

        if (!"success".equalsIgnoreCase(response.result())) {
            System.out.println("La consulta no fue exitosa.");
            return false;
        }

       //recuperamos los datos en el map (codigo-valor)
        //solo nos interesa el conversion_rates
        tasas = response.conversion_rates();
        return true;
    }

    public double convertirMoneda(double monto, String monedaOrigen, String monedaDestino) {

        if (monedaOrigen.equalsIgnoreCase(monedaDestino)) {
            return monto;
        }


        Double tasaOrigen = tasas.get(monedaOrigen.toUpperCase());
        Double tasaDestino = tasas.get(monedaDestino.toUpperCase());


        if (tasaOrigen == null || tasaDestino == null) {
            throw new IllegalArgumentException("Moneda no disponible");
        }


        return monto * (tasaDestino / tasaOrigen);
    }
}
