package com.itstep.asyncawait.privat24;

/**
 "ccy":"EUR",
 "base_ccy":"UAH",
 "buy":"19.20000",
 "sale":"20.00000"
 */
public class CurrencyModel {
    private String code;
    private String baseCode;
    private double buy;
    private double sale;

    // Конструктор
    public CurrencyModel(String currencyCode, String baseCode, double buyRate, double sellRate) {
        this.code = currencyCode;
        this.baseCode = baseCode;
        this.buy = buyRate;
        this.sale = sellRate;
    }

    // Геттеры и сеттеры (по желанию)
    public String getCode() {
        return code;
    }

    public void setCode(String currencyCode) {
        this.code = currencyCode;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }
}
