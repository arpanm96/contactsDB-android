package com.example.amajumder.contacts;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        setContentView(R.layout.edit_contact);

        etName =(EditText)findViewById(R.id.editTextName);
        etPhNo= (EditText)findViewById(R.id.editTextPhone);
        etEmail= (EditText)findViewById(R.id.editTextEmail);
        //int _id = 0;
        Intent intent = this.getIntent();
        //int Value = Integer.parseInt(intent.getStringExtra("value"));
        int Value = intent.getIntExtra("value",0);
        dbHelper = new DBHelper(this);
        if (Value > 0) {
            //means this is the view part not the add contact part.
            Cursor rs = dbHelper.getData(Value);
            id_To_Update = Value;
            rs.moveToFirst();
            final int _id =  rs.getInt(rs.getColumnIndex("id"));
            String strName = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_NAME));
            String strPhone = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_PHONE));
            String strEmail = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_EMAIL));

            //Toast.makeText(EditContact.this, strName + strEmail + strPhone, Toast.LENGTH_SHORT).show();
            //Toast.makeText(EditContact.this, ""+id_To_Update, Toast.LENGTH_SHORT).show();
            etName.setText(strName.toString());
            etPhNo.setText(strPhone.toString());
            etEmail.setText(strEmail.toString());
            edit  = (Button)findViewById(R.id.editOkButton);
            //Log.e("Edit error","ID : " + id_To_Update + " _ID " + _id);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    update(v,_id);
                    //Toast.makeText(EditContact.this, "ID :  " + id_To_Update + "_ID : "+_id, Toast.LENGTH_SHORT).show();
                    Toast.makeText(EditContact.this, "Done!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
            if (!rs.isClosed()) {
                rs.close();
            }
        }
    }
    public void update(View view,int idUpdt)
    {
        etName =(EditText)findViewById(R.id.editTextName);
        etPhNo= (EditText)findViewById(R.id.editTextPhone);
        etEmail= (EditText)findViewById(R.id.editTextEmail);
        dbHelper = new DBHelper(this);

        dbHelper.updateContact(idUpdt,etName.getText().toString(),etPhNo.getText().toString(),etEmail.getText().toString());
    }
}

