package com.itstep.elements;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.itstep.elements.adapters.StateAdapter;
import com.itstep.elements.models.State;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // набор данных, которые свяжем со списком
    // Плохая практика - деражть значения прямо в коде
    // String[] countries = { "Бразилия", "Аргентина", "Колумбия", "Чили", "Уругвай", "Куба"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Построить свой список
        buildListViewCustomAdapter();
        buildSpinner();

        (
          (StateAdapter)
                ((ListView)
                        findViewById(R.id.countriesList))
                 .getAdapter())
          .loadDataFromServer();

    }

    void buildListViewCustomAdapter(){
        // получаем элемент ListView
        // Обольше похож на ul / ol элементы html
        ListView countriesList = findViewById(R.id.countriesList);

        List<State> states= new ArrayList<>();
        states.add(new State("England", "London", R.drawable.en));
        states.add(new State("Ukraine", "Kyiv", R.drawable.ua));

        StateAdapter adapter = new StateAdapter(this, states);

        // устанавливаем для списка адаптер
        countriesList.setAdapter(adapter);

        // adapter.loadDataFromServer();

    }

    // https://metanit.com/java/android/5.4.php
    void buildSpinner(){
        Spinner spinner = findViewById(R.id.spinner);

        // получаем ресурс
        String[] countries = getResources().getStringArray(R.array.countries);

        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, countries);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedItem = countries[position];
                toast("Spinner: " + selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                toast("Spinner: Nothing Selected" );
            }
        });

        // Применяем адаптер к элементу spinner
        spinner.setAdapter(adapter);

    }



    // https://metanit.com/java/android/5.1.php
    void buildListView(){
        // получаем элемент ListView
        // Обольше похож на ul / ol элементы html
        ListView countriesList = findViewById(R.id.countriesList);

        // получаем ресурс
        String[] countries = getResources().getStringArray(R.array.countries);

        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                //android.R.layout.simple_list_item_1 // Верстка элементов стандартная из шаблона
                R.layout.my_list_item
                , countries);

        countriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedItem = countries[position];
                toast("ListView: " + selectedItem);
            }
        });

        // устанавливаем для списка адаптер
        countriesList.setAdapter(adapter);
    }



    // System Fun

    public void log(String msg) {
        Log.d("MainActivity", msg);
    }

    public void toast(String msg) {
        Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}