package com.itstep.testprovider;

import android.content.ContentUris;
import android.net.Uri;

public class FriendsContract {

    static public final String TABLE_NAME = "friends";
    static public final String CONTENT_AUTHORITY = "com.itstep.restapi";
    static public final Uri CONTENT_AUTHORITY_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    static public final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;
    static public final String CONTENT_ITEM_TYPE= "vnd.android.cursor.item/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;

    public static class Columns{
        public static final String _ID = "_id";
        public static final String NAME = "Name";
        public static final String EMAIL = "Email";
        public static final String PHONE = "Phone";

        private Columns(){

        }
    }
    static public final Uri CONTENT_URI = Uri.withAppendedPath(CONTENT_AUTHORITY_URI, TABLE_NAME);
    // создает uri с помощью id
    static public Uri buildFriendUri(long taskId){
        return ContentUris.withAppendedId(CONTENT_URI, taskId);
    }
    // получает id из uri
    static public long getFriendId(Uri uri){
        return ContentUris.parseId(uri);
    }

}