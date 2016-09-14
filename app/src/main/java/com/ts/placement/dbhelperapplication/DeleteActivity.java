package com.ts.placement.dbhelperapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class DeleteActivity extends AppCompatActivity {
    Spinner spinnerr;
    ContactDBHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        dbhelper = new ContactDBHelper(this);

        // Spinner element
       spinnerr = (Spinner) findViewById(R.id.spinner);

        // Spinner Drop down elements
        List<Integer> categories = dbhelper.getAllIds();
        Log.d("FORM DELETE Activity", ""+categories.size());

        // Creating adapter for spinner
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerr.setAdapter(dataAdapter);
    }
    public void deleteContact(View view){
        int selectedId = (int)spinnerr.getSelectedItem();
        Log.d("FROM DELETE ",""+selectedId);
        //read selected item from spinner
        //call delete() method from DBHelper class
        dbhelper.deleteContectById(selectedId);
        //dispaly toast message
    }
}
