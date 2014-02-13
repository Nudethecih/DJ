package com.DJ.app;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


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


       /* btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("USER_NAME", "Test");
                values.put("USER_PASSWORD", "Test");
                values.put("USER_TYPE", "Admin");
                /*DataBaseManager.instance().sqlCommand("CREATE TABLE USER(" +
                        "                USER_NAME CHAR(15)," +
                        "                USER_PASSWORD CHAR(15)," +
                        "                USER_TYPE CHAR(15)" +
                        "                )"); */
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
