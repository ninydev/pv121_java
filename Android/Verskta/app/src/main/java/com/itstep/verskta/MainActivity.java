package com.itstep.verskta;

import android.annotation.SuppressLint;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.itstep.verskta.listeners.MyBtnOnClickListener;
import com.itstep.verskta.textwatchers.MyTextWatcher;
import com.itstep.verskta.validations.MyRegisterFromValidator;

public class MainActivity extends AppCompatActivity {

    public void log(String msg) {
        Log.d("MainActivity", msg);
    }

    public void toast(String msg) {
        Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        createMemoryGamePad();

    }

    protected void createMemoryGamePad() {
        ConstraintLayout constraintLayout = new ConstraintLayout(this);

        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.scorpion);

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams
                (ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);

        imageView.setLayoutParams(layoutParams);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast("Click");
            }
        });

        constraintLayout.addView(imageView);

        setContentView(constraintLayout);
    }

    protected void createElements() {
        ConstraintLayout constraintLayout = new ConstraintLayout(this);

        // Создадим элемент элемент класса
        // document.crateElement("button");
        Button btn = new Button(this);
        btn.setText(" Hello World");

        // Параметры отображения элемента зависят от типа layout в котором но будет находиться
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams
                (ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);

        // Мне необходимо назначить параметры на нужный мне элемент
        btn.setLayoutParams(layoutParams);

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                toast("Hello World");
//            }
//        });

        // Добавим элемент в layout
        // div.appendChild(btn");
        constraintLayout.addView(btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast("Hello World After addView");
            }
        });

        setContentView(constraintLayout);
    }




//    protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.register_form);
//
//            MyRegisterFromValidator validator = new MyRegisterFromValidator(this);
//
////            ((EditText)findViewById(R.id.inpEmail)).addTextChangedListener(validator);
////            ((EditText)findViewById(R.id.inpPassword)).addTextChangedListener(validator);
////            ((EditText)findViewById(R.id.inpPasswordConfirm)).addTextChangedListener(validator);
//
//        }


//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        EditText inp = (EditText) findViewById(R.id.inpText);
//
//        inp.addTextChangedListener(new MyTextWatcher(this));
//
//        View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus) {
//                    // Вызывается, когда EditText теряет фокус (пользователь переходит к другому элементу)
//                    EditText editText = (EditText) v;
//                    String enteredText = editText.getText().toString();
//                    // Здесь вы можете обрабатывать текст
//                    toast(enteredText);
//                }
//            }
//        };
//
//        inp.setOnFocusChangeListener(onFocusChangeListener);
//
//    }




    /**
     * В этой точчке я уже могу работать с элементами верстки.
     * @param savedInstanceState
     */
    protected void onCreateBtn(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        log("onCreate");

        // Метод ищет элемент по его id, который вы указали в верстке
        // findViewById(R.id.btnClickMe);
        // R - Это класс, который содержит в себе ссылки на все элементы в проекте
        // С его помощью можно найти любой элемент в коде

        // Будет работтаь как с базовым классом и возможно будут проблемы
        // View v = findViewById(R.id.btnClickMe);

        // Будет оращаться к элементу как конкретно к кнопке
        Button btn = (Button) findViewById(R.id.btnClickMe);

        btn.setText("My New Btn Text");

        // Первый варинт - мы можем создавать слушателей прямо в момент назначения

//        String var = " V";
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Удобно то, что я могу сразу обращаться к методам Activity и переменным
//                toast("Click Ok");
//                log("Click log" + var);
//            }
//        });

        // Второй вариант - когда я описываю слушателя отдельно, реализую в нем интерфейс, нужный мне
        // и назначаю его тут
        btn.setOnClickListener(new MyBtnOnClickListener(this));






    }





    /**
     * Группа методов дл яобработки поведения жизненного цикла экрана (Activity)
     */
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        log("onStart");
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        log("onRestart");
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Toast.makeText(this.getApplicationContext(), "THK U Return", Toast.LENGTH_LONG).show();
//        log("onResume");
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        log("onPause");
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        log("onStop");
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        log("onDestroy");
//    }
}