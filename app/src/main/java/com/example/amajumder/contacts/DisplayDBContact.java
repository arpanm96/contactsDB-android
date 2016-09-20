package com.example.amajumder.contacts;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by rmajumder on 9/14/2016.
 */
public class DisplayDBContact extends AppCompatActivity {
    int from_Where_I_Am_Coming = 0;
    private DBHelper mydb;
    public static int delCount = 1;
    int id_To_Update = 0,id_To_Delete = 0,_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_display);
        TextView name = (TextView) findViewById(R.id.displayName);
        TextView phNo = (TextView) findViewById(R.id.displayPhNo);
        TextView email = (TextView) findViewById(R.id.displayEmail);

        mydb = new DBHelper(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            //Toast.makeText(DisplayDBContact.this, "" + Value, Toast.LENGTH_SHORT).show();
            //Value += delCount;
            if (Value > 0) {
                //means this is the view part not the add contact part.
                Cursor rs = mydb.getData(Value);
                if (rs != null) {
                    id_To_Update = id_To_Delete = Value;
                    rs.moveToFirst();
                     _id =  rs.getInt(rs.getColumnIndex("id"));
                    String strName = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_NAME));
                    String strPhone = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_PHONE));
                    String strEmail = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_EMAIL));

                    if (!rs.isClosed()) {
                        rs.close();
                    }
                    name.setText(strName.toString());

                    phNo.setText(strPhone.toString());


                    email.setText(strEmail.toString());
                }
                else {
                    Toast.makeText(DisplayDBContact.this, "Contact does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        }

        Button edit = (Button) findViewById(R.id.editButton);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),EditContact.class);
                //int val = getIntent().getExtras().getInt("id");
                intent.putExtra("value",id_To_Update);
                startActivity(intent);
            }
        });


        Button del = (Button)findViewById(R.id.deleteButton);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mydb.deleteContact(id_To_Delete);
                deleteContact();
                delCount++;
               // finish();
            }
        });
    }
    public void deleteContact()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mydb.deleteContact(_id);
                        Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        AlertDialog d = builder.create();
        d.setTitle("Delete");
        d.show();
    }
}