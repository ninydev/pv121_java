package com.itstep.asyncawait.privat24;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public class CurrencyExchangeArrayAdapter extends ArrayAdapter<CurrencyExchangeModel> implements CurrencyExchangeListener
{
    private LayoutInflater inflater;
    private Context context;
    private int layout;

    private List<CurrencyExchangeModel> currencyList;


    public CurrencyExchangeArrayAdapter(
            Context context, int resource, List<CurrencyExchangeModel> currencyList
            ){
        super(context, resource,currencyList);
        this.context = context;
        this.currencyList = currencyList;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);
        return view;
    }


    @Override
    public void onExchangeDataReceived(List<CurrencyExchangeModel> currencyList) {
        this.currencyList = currencyList;
        Log.d("Currency", currencyList.toString());
        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onExchangeDataError(Exception e) {
        Log.e("Adapter", e.getMessage());
    }
}
