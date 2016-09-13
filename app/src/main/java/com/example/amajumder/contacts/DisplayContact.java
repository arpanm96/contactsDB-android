package com.example.amajumder.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by amajumder on 9/13/2016.
 */
public class DisplayContact extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_display);

        //View view =  inflater.inflate(R.layout.secondefragment, container, false);

        TextView name = (TextView) findViewById(R.id.displayName);
        TextView phNo = (TextView) findViewById(R.id.displayPhNo);
        TextView email = (TextView) findViewById(R.id.displayEmail);

        ImageView icon = (ImageView) findViewById(R.id.displayImage);

        Intent intent = this.getIntent();
        // Bundle bundle = intent.getExtras();
        Contact cur = (Contact) intent.getSerializableExtra("data");
        name.setText(cur.getName());
        phNo.setText(""+cur.getPhNo());
        email.setText(cur.getMailID());

        icon.setImageResource(cur.getIconID());
    }


}
