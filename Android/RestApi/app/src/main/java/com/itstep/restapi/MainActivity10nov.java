package com.itstep.restapi;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.itstep.restapi.mockapi.ApiCallback;
import com.itstep.restapi.mockapi.EntityModel;
import com.itstep.restapi.mockapi.MockApiClient;
import com.itstep.restapi.mockapi.MockApiService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity10nov extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPermissions();
        loadContacts();

    }



    @SuppressLint("Range")
    private void loadContacts() {
        try {


            ContentResolver contentResolver = getContentResolver();
            Cursor cursor = contentResolver.query(
                    ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
            ArrayList<String> contacts = new ArrayList<String>();

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    // Получаем ID каждого контакта
                    @SuppressLint("Range") String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

                    // Получаем имя контакта
                    @SuppressLint("Range") String contactName = cursor.getString(
                            cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));

                    // Проверяем, есть ли у контакта номер телефона
                    if (Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                        // Получаем номера телефонов контакта
                        Cursor phoneCursor = contentResolver.query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                new String[]{contactId},
                                null);

                        if (phoneCursor != null) {
                            while (phoneCursor.moveToNext()) {
                                String phoneNumber = phoneCursor.getString(
                                        phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                // Добавляем имя и номер телефона в список
                                contacts.add(contactName + ": " + phoneNumber);
                            }
                            phoneCursor.close();
                        }
                    } else {
                        // Контакт не содержит номера телефона
                        contacts.add(contactName + ": No phone number");
                    }
                }
                cursor.close();
            }

            // Теперь у вас есть список контактов с именами и номерами телефонов (если они есть)
            // Добавьте код для использования или отображения этого списка
            for (String contact : contacts) {
                Log.d("Contacts", contact);
            }

        } catch (Exception e){
            Log.e("Contacts", e.getMessage());
        }
    }


    private static final int REQUEST_CODE_READ_CONTACTS=1;
    private static boolean READ_CONTACTS_GRANTED =false;



    private void getPermissions() {

        // Проверяем, есть ли уже разрешение на чтение контактов
        int hasReadContactPermission = checkSelfPermission(Manifest.permission.READ_CONTACTS);

        if (hasReadContactPermission == PackageManager.PERMISSION_GRANTED) {
            // Разрешение уже предоставлено
            Toast.makeText(this, "Разрешение на чтение контактов уже предоставлено", Toast.LENGTH_SHORT).show();
        } else {
            // Запрашиваем разрешение
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CODE_READ_CONTACTS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE_READ_CONTACTS: {
                // Проверяем, было ли предоставлено разрешение READ_CONTACTS
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Разрешение было предоставлено, выполните соответствующие действия
                    Toast.makeText(this, "Разрешение на чтение контактов предоставлено", Toast.LENGTH_SHORT).show();
                } else {
                    // Разрешение было отклонено, можно предпринять необходимые действия (например, показать сообщение пользователю)
                    Toast.makeText(this, "Доступ к контактам отклонен", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            // Добавьте обработку других разрешений по мере необходимости
        }
    }



    SQLiteDatabase db;
    protected void db(){
        db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users (name TEXT, age INTEGER, UNIQUE(name))");
        db.execSQL("INSERT OR IGNORE INTO users VALUES ('Tom Smith', 23), ('John Dow', 31);");

        Cursor query = db.rawQuery("SELECT * FROM users;", null);

        while(query.moveToNext()){
            String name = query.getString(0);
            int age = query.getInt(1);
            Log.d("db", "Name: " + name + " Age: " + age + "\n");
        }
        query.close();
        db.close();
    }




    protected void apis() {
        // Обращение через еще один уровень абстракции (в нем красиво было бы спрятать адаптер)
        MockApiService apiService = new MockApiService();
        apiService.getAllEntities();


        // Обращение сразу к клиенту - в таком случае я получаю данные в активити
        MockApiClient apiClient = new MockApiClient();
        apiClient.getAllEntities(new ApiCallback<List<EntityModel>>() {
            @Override
            public void onSuccess(List<EntityModel> data) {
                for (EntityModel entity : data) {
                    Log.d("MainActivity:", entity.getName());
                }
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("MainActivity", errorMessage);
            }
        });

        EntityModel newEntity = new EntityModel();
        newEntity.setName("Вася Пупкин");
        apiClient.createEntity(newEntity, new ApiCallback<EntityModel>() {
            @Override
            public void onSuccess(EntityModel data) {
                Log.d("MainActivity:", data.getId() + " " + data.getName());
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("MainActivity", errorMessage);
            }
        });

        newEntity.setId("1");
        newEntity.setName("Петя Пушкин");

        apiClient.updateEntity(newEntity.getId(), newEntity, new ApiCallback<EntityModel>() {
            @Override
            public void onSuccess(EntityModel data) {
                Log.d("MainActivity Update", data.getId() + " " + data.getName());
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("MainActivity", errorMessage);
            }
        });
    }

}