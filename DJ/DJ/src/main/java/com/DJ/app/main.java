package com.DJ.app;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


/**
 * Created by Newd on 2/9/14.
 */
public class main extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button btnUpdate = (Button) findViewById(R.id.update);
        Button btnDelete = (Button) findViewById(R.id.delete);
        Button btnInsert = (Button) findViewById(R.id.insert);
        String[] stringArray = {"India", "Pakistan", "USA", "UK"};
        String[] fromColumns = {"USERNAME",
                "USER_TYPE"};
        int[] toViews = {R.id.User_Name, R.id.User_Type};
        /*ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview,
                stringArray);*/
        Cursor rs = DataBaseManager.instance().select("SELECT * FROM USER");

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.activity_listview, rs, fromColumns, toViews, 0);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor rs = DataBaseManager.instance().select("SELECT * FROM USER");
                rs.moveToFirst();
                TextView Username = (TextView) findViewById(R.id.USERNAME);
                Username.setText(rs.getString(0));
                TextView Password = (TextView) findViewById(R.id.PASSWORD);
                Password.setText(rs.getString(1));
                TextView UserType = (TextView) findViewById(R.id.USERTYPE);
                UserType.setText(rs.getString(2));
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("USER_NAME", "Test");
                values.put("USER_PASSWORD", "Test");
                values.put("USER_TYPE", "Admin");
                DataBaseManager.instance().sqlCommand("CREATE TABLE USER(" +
                        "                USER_NAME CHAR(15)," +
                        "                USER_PASSWORD CHAR(15)," +
                        "                USER_TYPE CHAR(15)" +
                        "                )");
                DataBaseManager.instance().insert("USER", values);
            }
        });

 /*       btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
    }
}
