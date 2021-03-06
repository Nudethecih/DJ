package com.DJ.app;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Newd on 2/28/14.
 */
public class CustomCursorAdapater extends CursorAdapter {

    private LayoutInflater mInflater;

    public CustomCursorAdapater(Context context, Cursor c) {
        super(context, c);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    // Accepts the view(the row), also accepts context(the class containing the listview), also accepts a cursor(recordset)
    public void bindView(View view, Context context, Cursor cursor) {
        final Context context1 = context;
        //If rs.value
        if(cursor.getString(3).equals("Admin2")) {
            //Set row values
            view.setBackgroundColor(Color.CYAN);
            view.setOnClickListener(null);
        }
        else {
            view.setBackgroundColor(Color.BLUE);
            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                        // Cast the row.field.value as a string
                        String selected = ((TextView) view.findViewById(R.id.User_Name)).getText().toString();
                        // Create a message on the 'context'(on the activity currently using the customCursorAdapter)
                        Toast toast = Toast.makeText(context1, selected, Toast.LENGTH_SHORT);
                        //Display the message
                        toast.show();

                    }
            });
        }

        // Set local variables for any Textviews you want to use
        TextView content1 = (TextView) view.findViewById(R.id.User_Name);
        TextView content2 = (TextView) view.findViewById(R.id.User_Type);
        TextView content3 = (TextView) view.findViewById(R.id.User_Password);

        // Link textviews to a certain column from the recordset
        content1.setText(cursor.getString(0));
        content2.setText(cursor.getString(2));
        content3.setText(cursor.getString(3));

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return mInflater.inflate(R.layout.activity_listview, parent, false);
    }

}