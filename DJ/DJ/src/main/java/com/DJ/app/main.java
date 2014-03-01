package com.DJ.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.Date;


/**
 * Created by Newd on 2/9/14.
 */
public class main extends Activity {
public CustomCursorAdapater adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button btnUpdate = (Button) findViewById(R.id.update);
        Button btnDelete = (Button) findViewById(R.id.delete);
        Button btnInsert = (Button) findViewById(R.id.insert);

        final AlertDialog confirm = new AlertDialog.Builder(this)
                .setTitle("Titel")
                .setMessage("Do you really want to whatever?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Yeah! play that shit!", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        Toast.makeText(main.this, "Requested for 'NSYNC's Bye Bye Bye sent", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Hell Nah dawg, dis ain't my jam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(main.this, "Request not sent", Toast.LENGTH_SHORT).show();
                    }
                }).create();


        String[] fromColumns = {"USER_NAME" ,"USER_TYPE","USER_PASSWORD"};
        int[] toViews = {R.id.User_Name, R.id.User_Type, R.id.User_Password};

        final String sqlQuery = "SELECT * FROM USER";

        Cursor rs = DataBaseManager.instance().select(sqlQuery);


       final CustomCursorAdapater adapter = new CustomCursorAdapater(this,rs);

        ListView listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(adapter);
       int count = listView.getCount();

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CustomCursorAdapater newAdap = adapter;
                DataBaseManager.instance().delete("USER", "1=1");
                newAdap.swapCursor(DataBaseManager.instance().select(sqlQuery));
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                for(int i = 0; i <5; i++){
                    for(int j = 0; j < 10; j++){
                        Date now = new Date();
                        values.put("USER_NAME", "Song " + j);
                        values.put("USER_PASSWORD", "Genre " + i);
                        values.put("USER_TYPE", now.toString());
                        DataBaseManager.instance().insert("USER", values);
                    }
                }
                final CustomCursorAdapater newAdap = adapter;
                newAdap.swapCursor(DataBaseManager.instance().select(sqlQuery));
/*                DataBaseManager.instance().sqlCommand("CREATE TABLE USER(" +
                        "                USER_NAME CHAR(15)," +
                        "                _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "                USER_PASSWORD CHAR(15)," +
                        "                USER_TYPE CHAR(15)" +
                        "                )");*/

            }
        });

 /*       btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
    }
}
