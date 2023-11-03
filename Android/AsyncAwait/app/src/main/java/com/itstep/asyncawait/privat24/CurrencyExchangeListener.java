package com.itstep.asyncawait.privat24;

import java.util.List;

public interface CurrencyExchangeListener {
    void onExchangeDataReceived(List<CurrencyExchangeModel> currencyList);
    void onExchangeDataError(Exception e);
}
