package com.itstep.restapi;

import android.Manifest;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.itstep.restapi.mockapi.ApiCallback;
import com.itstep.restapi.mockapi.EntityModel;
import com.itstep.restapi.mockapi.MockApiClient;
import com.itstep.restapi.mockapi.MockApiService;
import com.itstep.restapi.providers.FriendsContract;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "FriendsContract";
    private static final int REQUEST_CODE_READ_FRIENDS = 123; // любое уникальное значение
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkReadFriendsPermission()) {
            getAll(null);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{"com.itstep.restapi.permission.READ_FRIENDS"}, REQUEST_CODE_READ_FRIENDS);
        }
    }

    private boolean checkReadFriendsPermission() {
        int permissionResult = ContextCompat.checkSelfPermission(this, "com.itstep.restapi.permission.READ_FRIENDS");
        return permissionResult == PackageManager.PERMISSION_GRANTED;
    }



    // получение всех
    public void getAll(View view){
        String[] projection = {
                FriendsContract.Columns._ID,
                FriendsContract.Columns.NAME,
                FriendsContract.Columns.EMAIL,
                FriendsContract.Columns.PHONE
        };
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(FriendsContract.CONTENT_URI,
                projection,
                null,
                null,
                FriendsContract.Columns.NAME);
        if(cursor != null){
            Log.d(TAG, "count: " + cursor.getCount());
            // перебор элементов
            while(cursor.moveToNext()){
                for(int i=0; i < cursor.getColumnCount(); i++){
                    Log.d(TAG, cursor.getColumnName(i) + " : " + cursor.getString(i));
                }
                Log.d(TAG, "=========================");
            }
            cursor.close();
        }
        else{
            Log.d(TAG, "Cursor is null");
        }
    }
    // Добавление
    public void add(View view){
        ContentResolver contentResolver = getContentResolver();
        ContentValues values = new ContentValues();
        values.put(FriendsContract.Columns.NAME, "Sam");
        values.put(FriendsContract.Columns.EMAIL, "sam@gmail.com");
        values.put(FriendsContract.Columns.PHONE, "+13676254985");
        Uri uri = contentResolver.insert(FriendsContract.CONTENT_URI, values);
        Log.d(TAG, "Friend added");
    }

    // Обновление
    public void update(View view){
        ContentResolver contentResolver = getContentResolver();
        ContentValues values = new ContentValues();
        values.put(FriendsContract.Columns.EMAIL, "sammy@gmail.com");
        values.put(FriendsContract.Columns.PHONE, "+55555555555");
        String selection = FriendsContract.Columns.NAME + " = 'Sam'";
        int count = contentResolver.update(FriendsContract.CONTENT_URI, values, selection, null);
        Log.d(TAG, "Friend updated");
    }
    // Удаление
    public void delete(View view){
        ContentResolver contentResolver = getContentResolver();
        String selection = FriendsContract.Columns.NAME + " = ?";
        String[] args = {"Sam"};
        int count = contentResolver.delete(FriendsContract.CONTENT_URI, selection, args);
        Log.d(TAG, "Friend deleted");
    }





}