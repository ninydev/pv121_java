package com.itstep.testprovider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
            Log.e(TAG, "Per");
            ActivityCompat.requestPermissions(this, new String[]{"com.itstep.restapi.permission.READ_FRIENDS"}, REQUEST_CODE_READ_FRIENDS);
        }
    }

    private boolean checkReadFriendsPermission() {
        int permissionResult = ContextCompat.checkSelfPermission(this, "com.itstep.restapi.permission.READ_FRIENDS");
        return permissionResult == PackageManager.PERMISSION_GRANTED;
    }

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
}