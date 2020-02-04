package com.example.week2cs4084pizzaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultsScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_screen);

        TextView order_details = (TextView) findViewById(R.id.order_details);

        Intent intent = getIntent();
        String results = intent.getStringExtra(OrderMenu.EXTRA_MESSAGE);
        order_details.setText(results);

        DateFormat dateFormat = new SimpleDateFormat("HH:mm");

        long hour = 3600000;
        Date currentDate = new Date();
        Date deliveryDate = new Date(currentDate.getTime() + hour);


        TextView dateField = (TextView)findViewById(R.id.date);

        dateField.setText(dateFormat.format(deliveryDate));
    }
}
