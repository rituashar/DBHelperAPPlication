package com.ts.placement.dbhelperapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class UpdateActivity extends AppCompatActivity {
    Spinner spinnerr;
    ContactDBHelper dbhelper;
    EditText nm,ph;
    Contact contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        dbhelper = new ContactDBHelper(this);
        // Spinner element
        spinnerr = (Spinner) findViewById(R.id.spinner2);
        nm = (EditText)findViewById(R.id.name);
        ph = (EditText)findViewById(R.id.phone);
        // Spinner Drop down elements
        List<Integer> categories = dbhelper.getAllIds();
        Log.d("FORM UPDATE Activity", ""+categories.size());

        // Creating adapter for spinner
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerr.setAdapter(dataAdapter);
    }
public void showContact(View view){
    int id = (int)spinnerr.getSelectedItem();
     contact = dbhelper.getContactById(id);
    Log.d("Update Activity",""+contact);
    String name = contact.getName();
            String phone = contact.getPhone();
    nm.setText(name);
    ph.setText(phone);

}
    public void updateContact(View view){
        contact.setName(nm.getText().toString());
        contact.setPhone(ph.getText().toString());
        dbhelper.updateContectById(contact);
    }

}
