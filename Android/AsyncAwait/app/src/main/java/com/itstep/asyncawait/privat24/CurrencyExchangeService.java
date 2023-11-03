package com.itstep.asyncawait.privat24;

import java.util.List;

public class CurrencyExchangeService {

    CurrencyExchangeListener listener;

    public CurrencyExchangeService(CurrencyExchangeListener listener) {
        this.listener = listener;
    }

    public void getExchange(){

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    CurrencyExchangeAPI api = new CurrencyExchangeAPI();
                    String response = api.getExchange();

                    CurrencyExchangeDTO dto = new CurrencyExchangeDTO();
                    List<CurrencyExchangeModel> currencyList = dto.jsonToList(response);

                    if (listener != null) {
                        listener.onExchangeDataReceived(currencyList);
                    }
                } catch (Exception e) {
                    if (listener != null)
                        listener.onExchangeDataError(e);
                }
            }
        }).start();

    }
}
