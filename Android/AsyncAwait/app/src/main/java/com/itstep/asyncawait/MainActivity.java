package com.itstep.asyncawait;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.itstep.asyncawait.privat24.CurrencyExchangeArrayAdapter;
import com.itstep.asyncawait.privat24.CurrencyExchangeModel;
import com.itstep.asyncawait.privat24.CurrencyExchangeService;
import com.itstep.asyncawait.tasks.ProgressTask;
import com.itstep.asyncawait.viewmodels.MyViewModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView contentView = findViewById(R.id.content);
        WebView webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        Button btnFetch = findViewById(R.id.downloadBtn);
        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<CurrencyExchangeModel> currencyList = new ArrayList<>();
                CurrencyExchangeArrayAdapter adapter = new CurrencyExchangeArrayAdapter(v.getContext(), 0, currencyList);
                CurrencyExchangeService exchangeService = new CurrencyExchangeService(adapter);
                exchangeService.getExchange();



                contentView.setText("Загрузка...");
//                new Thread(new Runnable() {
//                    public void run() {
//                        try{
//                            String content = getContent("https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5");
//                            webView.post(new Runnable() {
//                                public void run() {
//                                    webView.loadDataWithBaseURL("https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5",content, "text/html", "UTF-8", "https://stackoverflow.com/");
//                                    Toast.makeText(getApplicationContext(), "Данные загружены", Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                            contentView.post(new Runnable() {
//                                public void run() {
//                                    contentView.setText(content);
//                                }
//                            });
//                        }
//                        catch (IOException ex){
//                            contentView.post(new Runnable() {
//                                public void run() {
//                                    contentView.setText("Ошибка: " + ex.getMessage());
//                                    Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                        }
//                    }
//                }).start();
            }
        });


    }


    private String getContent(String path) throws IOException {
        BufferedReader reader=null;
        InputStream stream = null;
        HttpsURLConnection connection = null;
        try {
            URL url=new URL(path);
            connection =(HttpsURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000);
            connection.connect();
            stream = connection.getInputStream();
            reader= new BufferedReader(new InputStreamReader(stream));
            StringBuilder buf=new StringBuilder();
            String line;
            while ((line=reader.readLine()) != null) {
                buf.append(line).append("\n");
            }
            return(buf.toString());
        }
        finally {
            if (reader != null) {
                reader.close();
            }
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }


    public void log(String msg) {
        Log.d("MainActivity", msg);
    }

    public void toast(String msg) {
        Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}