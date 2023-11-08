package com.itstep.restapi.dto;

import android.database.Cursor;

import com.itstep.restapi.models.User;

import java.util.ArrayList;
import java.util.List;

public class userMapper {

    public List<User> queryToList(Cursor query){
        List<User> users = new ArrayList<>();
        while(query.moveToNext()){
            users.add( new User(query.getString(0), query.getInt(1)));
        }
        return users;
    }
}
