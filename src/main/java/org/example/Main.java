package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ConsultarMoneda servicio = new ConsultarMoneda();
        MonedasUsar monedas = new MonedasUsar();

        System.out.println("Bienvenido/a al Conversor de Moneda 🌎💱");

        while (true) {
            System.out.println("\n ARS - Peso argentino");
            System.out.println(" BOB - Boliviano");
            System.out.println(" BRL - Real brasileño");
            System.out.println(" CLP - Peso chileno");
            System.out.println(" COP - Peso colombiano");
            System.out.println(" USD - Dólar estadounidense");
            System.out.print("Elija la moneda base: ");
            String monedaBase = scanner.nextLine().toUpperCase();

            String json = servicio.ConvertirMonedas(monedaBase);

            if (!monedas.cargarTasas(json)) {
                System.out.println("Error al cargar tasas. Intenta con otra moneda.");
                continue;
            }

            System.out.print("Ingrese la moneda a convertir (ej: ARS): ");
            String monedaDestino = scanner.nextLine().toUpperCase();

            System.out.print("Ingrese el monto a convertir: ");
            double monto = scanner.nextDouble();
            scanner.nextLine();

            try {
                double resultado = monedas.convertirMoneda(monto, monedaBase, monedaDestino);
                System.out.printf("Resultado: %.2f %s = %.2f %s%n",
                        monto, monedaBase, resultado, monedaDestino);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.print("¿Deseas hacer otra conversión? (s/n): ");
            String opcion = scanner.nextLine().trim().toLowerCase();
            if (!opcion.equals("s")) {
                System.out.println("Gracias por usar el conversor. ¡Hasta pronto!");
                break;
            }
        }

        scanner.close();
    }
    }
