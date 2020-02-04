package com.example.week2cs4084pizzaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class OrderMenu extends AppCompatActivity {

    boolean mayoChecked = false;
    boolean bbqChecked = false;
    boolean cheeseChecked = false;

    boolean burntChecked = false;
    boolean flatChecked = false;

    public static final String EXTRA_MESSAGE = "com.example.week2cs4084pizzaproject";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_menu);

        final Spinner spinner = (Spinner) findViewById(R.id.option_spinner);

        ArrayList<String> dropdownList = new ArrayList<String>();
        dropdownList.add("Full Meat");
        dropdownList.add("Vegan Feast");
        dropdownList.add("Vegetarian Bonanza");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
        android.R.layout.simple_spinner_item, dropdownList);
        spinner.setAdapter(dataAdapter);

        final CheckBox mayo = (CheckBox)findViewById(R.id.Mayo);
        final CheckBox bbq = (CheckBox)findViewById(R.id.BBQ);
        final CheckBox cheese = (CheckBox)findViewById(R.id.Cheese);

        final CheckBox burnt = (CheckBox)findViewById(R.id.Burnt);
        final CheckBox flat = (CheckBox)findViewById(R.id.Flat);

        Button orderButton = (Button)findViewById(R.id.order_button);
        orderButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               String type = returnPizzaType(spinner);
               ArrayList crust = returnCrust(burnt, flat);
               ArrayList extras = returnExtras(mayo, bbq, cheese);

               String results = type.toUpperCase() ;

                if(crust.size() > 0) results += "\n\n\nCrust: ";

               for(int i = 0; i < crust.size(); i++) {
                   results += crust.get(i).toString();
                   if(i != crust.size()-1)
                       results +=  ", ";
               }

               if(extras.size() > 0) results += "\n\nExtras: ";

               for(int i = 0; i < extras.size(); i++) {
                   results += extras.get(i).toString();
                   if(i != extras.size()-1)
                       results +=  ", ";
               }

               Intent intent = new Intent(OrderMenu.this, ResultsScreen.class);
               intent.putExtra(EXTRA_MESSAGE, results);
               startActivity(intent);
            }
        });

    }
    protected String returnPizzaType(Spinner spinner){
        String pizzaType = spinner.getSelectedItem().toString();
        return pizzaType;
    }
    protected ArrayList<String> returnCrust(CheckBox burnt, CheckBox flat){
        ArrayList<String> returnValues = new ArrayList<>();
        if(burnt.isChecked()) {
            burntChecked = true;
            returnValues.add("Burnt");
        }
        if(flat.isChecked()) {
            flatChecked = true;
            returnValues.add("Flat");
        }
        return returnValues;
    }
    protected ArrayList<String> returnExtras(CheckBox mayo, CheckBox bbq, CheckBox cheese){
        ArrayList<String> returnValues = new ArrayList<>();
        if(mayo.isChecked()) {
            mayoChecked = true;
            returnValues.add("Mayo");
        }
        if(bbq.isChecked()) {
            bbqChecked = true;
            returnValues.add("BBQ");
        }
        if(cheese.isChecked()) {
            cheeseChecked = true;
            returnValues.add("Cheese");
        }

        return returnValues;
    }
}
