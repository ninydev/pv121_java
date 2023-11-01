package com.itstep.asyncawait.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    // Мутирующая переменная - показывающая - работает ли сейчас поток
    private MutableLiveData<Boolean> isStarted = new MutableLiveData<Boolean>(false);

    // Мутируюащя переменная - значение (от 0 до 100)
    private MutableLiveData<Integer> value;

    // Геттер для значения
    public LiveData<Integer> getValue() {
        if (value == null) {
            value = new MutableLiveData<Integer>(0);
        }
        return value;
    }

    // Процесс выполнения
    public void execute(){

        if(!isStarted.getValue()){
            isStarted.postValue(true);
            Runnable runnable = new Runnable() {
                @Override
                public void run() {

                    for(int i = value.getValue();  i <= 100; i++){
                        try {
                            value.postValue(i);
                            Thread.sleep(400);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
}