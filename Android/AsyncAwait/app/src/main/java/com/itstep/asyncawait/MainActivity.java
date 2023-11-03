package com.itstep.asyncawait;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.itstep.asyncawait.tasks.ProgressTask;
import com.itstep.asyncawait.viewmodels.MyViewModel;

import java.util.Calendar;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        log("Create");

        TextView textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);

        ProgressBar indicatorBar = findViewById(R.id.indicator);
        TextView statusView = findViewById(R.id.statusView);
        Button btnFetch = findViewById(R.id.progressBtn);
        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

        model.getValue().observe(this, value -> {
            indicatorBar.setProgress(value);
            statusView.setText("Статус: " + value);
            log("Value: " + value);
        });
        ProgressTask task = new ProgressTask(
          this, indicatorBar,statusView, UUID.randomUUID()
        );
        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                log("Start");
                // model.execute();
                // task.execute();
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });



    }


    protected void onCreateOld(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);

        // Создайте Handler, связанный с главным потоком
        Handler handler = new Handler();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Определяем объект Runnable
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            // получаем текущее время
                            Calendar c = Calendar.getInstance();
                            int hours = c.get(Calendar.HOUR_OF_DAY);
                            int minutes = c.get(Calendar.MINUTE);
                            int seconds = c.get(Calendar.SECOND);
                            String time = hours + ":" + minutes + ":" + seconds;
                            // отображаем в текстовом поле
                            // мы не можем обратиться к элементам в UI Thread потоке напрямую
                            textView.setText(time);

                            // Вариант с использованием Handler
//                            handler.post(new Runnable() {
//                                @Override
//                                public void run() {
//                                    textView.setText(time);
//                                }
//                            });


                            // Вариант с использованием runOnUiThread (без Handler)
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // Ваш код для обновления UI
                                    textView.setText(time);
                                }
                            });

                        }
                    }
                };
                // Определяем объект Thread - новый поток
                Thread thread = new Thread(runnable);
                // Запускаем поток
                thread.start();
            }
        });

    }

    public void log(String msg) {
        Log.d("MainActivity", msg);
    }

    public void toast(String msg) {
        Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}