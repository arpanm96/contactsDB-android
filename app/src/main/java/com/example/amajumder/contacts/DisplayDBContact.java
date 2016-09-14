package com.example.amajumder.contacts;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
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

    int id_To_Update = 0;

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

            if (Value > 0) {
                //means this is the view part not the add contact part.
                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();

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
        }

        Button b = (Button) findViewById(R.id.editButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),EditContact.class);
                int val = getIntent().getExtras().getInt("id");
                intent.putExtra("value",val);
                startActivity(intent);
            }
        });

    }
}