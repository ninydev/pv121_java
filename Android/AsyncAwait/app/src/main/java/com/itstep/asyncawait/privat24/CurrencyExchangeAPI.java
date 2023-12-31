package com.itstep.asyncawait.privat24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class CurrencyExchangeAPI {
    private static final String PRIVAT_BANK_API_URL = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";


    /**
     * Получить строку JSON с банка
     */
    public String getExchange() throws IOException {
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL(PRIVAT_BANK_API_URL);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Чтение данных из потока
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }
}
