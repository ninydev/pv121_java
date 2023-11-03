package com.itstep.asyncawait.privat24;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class CurrencyExchangeDTO {

    /**
     * Преобразовывает строку в массив элементов
     * @param jsonString
     * @return
     */
    public List<CurrencyExchangeModel> jsonToList(String jsonString) {
        List<CurrencyExchangeModel> currencyList = new ArrayList<>();

        JsonParser parser = new JsonParser();
        JsonArray jsonArray = (JsonArray) parser.parse(jsonString);

        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
            String code = jsonObject.get("ccy").getAsString();
            String baseCode = jsonObject.get("base_ccy").getAsString();
            double buyRate = jsonObject.get("buy").getAsDouble();
            double sellRate = jsonObject.get("sale").getAsDouble();

            CurrencyExchangeModel currency = new CurrencyExchangeModel(code, baseCode, buyRate, sellRate);
            currencyList.add(currency);
        }
        return currencyList;
    }
}
