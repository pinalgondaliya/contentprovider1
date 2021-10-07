package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // content provider : create, access

    private static final String TAG = "mainact";

    RecyclerView r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r = findViewById(R.id.cotacts);
        r.setLayoutManager(new LinearLayoutManager(this));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},20);
        }

    }

    public void getContacts(View view) {

        Uri tableName = ContactsContract.Contacts.CONTENT_URI;
        String tableColProjection[] = {ContactsContract.Contacts.DISPLAY_NAME};

        String colName = ContactsContract.Contacts.DISPLAY_NAME;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

           Cursor c =  getContentResolver().query(tableName,tableColProjection,null,null);
           c.moveToFirst();

           int index =  c.getColumnIndex(colName);

            List<String> contactList = new ArrayList<>();

           while (c.moveToNext()){


               String contactName = c.getString(index);

               contactList.add(contactName);

               Log.d(TAG, "getContacts: "+contactName);

           }

           r.setAdapter(new MyAdapter(contactList));

        }


//        Uri tableName = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
//        String tableColProjection[] = {ContactsContract.CommonDataKinds.Phone.NUMBER};
//
//        String colName = ContactsContract.CommonDataKinds.Phone.NUMBER;
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//
//            Cursor c =  getContentResolver().query(tableName,tableColProjection,null,null);
//            c.moveToFirst();
//
//            int index =  c.getColumnIndex(colName);
//
//            List<String> contactList = new ArrayList<>();
//
//            while (c.moveToNext()){
//
//
//                String contactName = c.getString(index);
//
//                contactList.add(contactName);
//
//                Log.d(TAG, "getContacts: "+contactName);
//
//            }
//
//            r.setAdapter(new MyAdapter(contactList));
//
//        }
//
//    }

    }
}