package com.itstep.asyncawait.privat24;

import android.util.Log;

import java.util.List;

public class CurrencyExchangeService {

    public CurrencyExchangeService() {
    }

    public void getExchange(){

        new Thread(new Runnable() {

            @Override
            public void run() {
                CurrencyExchangeAPI api = new CurrencyExchangeAPI();
                String response = api.getExchange();
                Log.d("Currency", response);

                CurrencyExchangeDTO dto = new CurrencyExchangeDTO();

                List<CurrencyModel> currencyList = dto.toList(response);
            }
        }).start();

    }
}
