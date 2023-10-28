package com.itstep.elements;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    // набор данных, которые свяжем со списком
    // Плохая практика - деражть значения прямо в коде
    // String[] countries = { "Бразилия", "Аргентина", "Колумбия", "Чили", "Уругвай", "Куба"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Построить свой список
        buildListView();
        buildSpinner();

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

        // устанавливаем для списка адаптер
        countriesList.setAdapter(adapter);
    }
}