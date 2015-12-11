package com.example.benidictdulce.sqlitebenjie;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by Benidict Dulce on 7/31/2015.
 */

public class MainActivity extends Activity {

    DatabaseHelper userdb;
    EditText admin, ids;
    Button add, view, update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userdb = new DatabaseHelper(this);

        ids = (EditText) findViewById(R.id.idd);
        admin = (EditText) findViewById(R.id.user);
        add = (Button) findViewById(R.id.addbutton);
        view = (Button) findViewById(R.id.viewbutton);
        update = (Button) findViewById(R.id.updatebutton);

        ViewData();
        AddData();
        UpdateData();
    }


    public void AddData(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = userdb.insertData(admin.getText().toString());

                if (isInserted == true)
                {
                    Toast.makeText(MainActivity.this, "NICE ONE", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "NOT NICE", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void ViewData(){

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = userdb.getAllData();
                if (res.getCount() == 0)
                {
                    // show message
                    showMessage("Error 404", "Data Not  found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("User: "+ res.getString(1)+"\n");
                }
                //show all data
                showMessage("Data", buffer.toString());
            }
        });
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void UpdateData(){
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = userdb.updateData(ids.getText().toString(), admin.getText().toString());

                if (isUpdate == true){
                    Toast.makeText(MainActivity.this, "NICE ONE UPDATE", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(MainActivity.this, "NOT NICE UPDATE", Toast.LENGTH_LONG).show();
            }
          }
        });
    }

}
