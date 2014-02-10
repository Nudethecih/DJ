package com.DJ.app;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    //.add(R.id.btnLogin, new PlaceholderFragment())
                    .commit();
        }
        Button b = (Button) findViewById(R.id.btnLogin);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText UsernameEditText = (EditText) findViewById(R.id.txtUsername);
                String Username = UsernameEditText.getText().toString();
                EditText PasswordEditText = (EditText) findViewById(R.id.txtPassword);
                String Password = PasswordEditText.getText().toString();

                if(Username.equals(getString(R.string.username))&& Password.equals(getString(R.string.password))){
                    startActivity(new Intent(MainActivity.this, second.class));


                }else {
                    AlertDialog errorAlert;
                    errorAlert = new AlertDialog.Builder(MainActivity.this).create();
                    errorAlert.setTitle("Login Failed");
                    errorAlert.setMessage("Username or Password is incorrect");
                    errorAlert.show();
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
