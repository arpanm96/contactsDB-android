package com.example.amajumder.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amajumder on 9/12/2016.
 */
public class AddContact extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);

        Intent intent = this.getIntent();

        Button button = (Button)findViewById(R.id.addContact);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText etName = (EditText)findViewById(R.id.name);
                EditText etEmail = (EditText)findViewById(R.id.email);
                EditText etPhNo = (EditText)findViewById(R.id.phone);


                String name = etName.getText().toString();
                String phNo = etPhNo.getText().toString();
                String email = etEmail.getText().toString();

                DBHelper dbHelper = new DBHelper(AddContact.this);
                dbHelper.insertContact(name,phNo,email);
                Toast.makeText(AddContact.this, "Contact added!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}