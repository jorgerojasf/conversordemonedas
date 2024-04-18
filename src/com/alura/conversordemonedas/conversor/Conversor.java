package com.alura.conversordemonedas.conversor;

import com.alura.conversordemonedas.modelos.Moneda;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Conversor {
    public static void main(String[] args) throws IOException, InterruptedException{
        Scanner lectura = new Scanner(System.in);

        String desde = "";
        String hacia = "";
        while(true){
            System.out.println("****************************");
            System.out.println("Sea bienvenido/a al Conversor de Monedas");
            System.out.println("1) Dolar => Peso Argentino");
            System.out.println("2) Peso Argentino => Dolar");
            System.out.println("3) Dolar => Real Brasileño");
            System.out.println("4) Real Brasileño => Dolar");
            System.out.println("5) Dolar => Peso Chileno");
            System.out.println("6) Peso Chileno => Dolar");
            System.out.println("7) Salir");
            System.out.println("Elija una opcion valida");
            System.out.println("*******************************");
            var busqueda = lectura.nextLine();
            if (busqueda.equalsIgnoreCase("7")){
                break;
            }
            String clave = "b2de9a897ec94c4b28e51ba4";
            switch (busqueda){
                case "1":
                    desde = "USD";
                    hacia = "ARS";
                    break;
                case "2":
                    desde = "ARS";
                    hacia = "USD";
                    break;
                case "3":
                    desde = "USD";
                    hacia = "BRL";
                    break;
                case "4":
                    desde = "BRL";
                    hacia = "USD";
                    break;
                case "5":
                    desde = "USD";
                    hacia = "CLP";
                    break;
                case "6":
                    desde = "CLP";
                    hacia = "USD";
                    break;
                default:
                    System.out.println("Elija una opcion valida");
                    continue;
            }
            System.out.println("Ingresa el valor que deseas convertir");
            var valor = lectura.nextLine();
            String direccion = "https://v6.exchangerate-api.com/v6/"+clave+"/pair/"+desde+"/"+hacia;
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direccion))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();

            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
            System.out.println(jsonObject.get("conversion_rate").getAsString());
            var valorHacia = jsonObject.get("conversion_rate").getAsString();
            try{
                Moneda moneda = new Moneda(desde, hacia, Integer.valueOf(valor), Double.valueOf(valorHacia));
                System.out.println(moneda);
            }catch (Exception e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
            }
        }

        System.out.println("Programa finalizado");
    }
}
