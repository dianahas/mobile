package com.example.diana.hotels;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Diana on 08-Nov-17.
 */

public class ManageHotelActivity extends AppCompatActivity {

    private Button addHotelButton;
    private EditText hotelName;
    private EditText hotelLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_hotel_layout);

        addHotelButton = (Button) findViewById(R.id.addHotel);

        hotelName = (EditText) findViewById(R.id.hotelName);
        hotelLocation = (EditText) findViewById(R.id.hotelLocation);

        addHotelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String position = getIntent().getStringExtra("position");

                Intent intent = new Intent(ManageHotelActivity.this, MainActivity.class);
                intent.putExtra("hotelName", hotelName.getText().toString());
                intent.putExtra("hotelLocation", hotelLocation.getText().toString());

                if(position != null){
                    intent.putExtra("position", position);
                }
                startActivity(intent);
            }
        });

    }
}
