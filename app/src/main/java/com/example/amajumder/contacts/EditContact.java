package com.example.amajumder.contacts;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

/**
 * Created by amajumder on 9/14/2016.
 */
public class EditContact extends AppCompatActivity {
    int id_To_Update = 0;
    EditText etName ;
    EditText etPhNo ;
    EditText etEmail ;
    Button edit;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp);

        etName =(EditText)findViewById(R.id.editTextName);
        etPhNo= (EditText)findViewById(R.id.editTextPhone);
        etEmail= (EditText)findViewById(R.id.editTextEmail);

        Intent intent = this.getIntent();
        //int Value = Integer.parseInt(intent.getStringExtra("value"));
        int Value = intent.getIntExtra("value",0);
        dbHelper = new DBHelper(this);
        if (Value > 0) {
            //means this is the view part not the add contact part.
            Cursor rs = dbHelper.getData(Value);
            id_To_Update = Value;
            rs.moveToFirst();

            String strName = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_NAME));
            String strPhone = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_PHONE));
            String strEmail = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_EMAIL));

            Toast.makeText(EditContact.this, strName + strEmail + strPhone, Toast.LENGTH_SHORT).show();

            etName.setText(strName.toString());
            etPhNo.setText(strPhone.toString());
            etEmail.setText(strEmail.toString());
            edit  = (Button)findViewById(R.id.editOkButton);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    update(v);
                    Toast.makeText(EditContact.this, "Done!", Toast.LENGTH_SHORT).show();
                }
            });
            if (!rs.isClosed()) {
                rs.close();
            }
        }
    }
    public void update(View view)
    {
        etName =(EditText)findViewById(R.id.editTextName);
        etPhNo= (EditText)findViewById(R.id.editTextPhone);
        etEmail= (EditText)findViewById(R.id.editTextEmail);
        dbHelper = new DBHelper(this);
        dbHelper.updateContact(id_To_Update,etName.getText().toString(),etPhNo.getText().toString(),etEmail.getText().toString());
    }
}

