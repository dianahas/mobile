package com.example.diana.hotels;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.diana.hotels.db.AppDatabase;
import com.example.diana.hotels.model.Hotel;
import com.example.diana.hotels.model.Locations;
import com.example.diana.hotels.model.User;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Diana on 08-Nov-17.
 */

public class ManageHotelActivity extends AppCompatActivity {

    private Button manageHotelButton;
    private EditText hotelName;
    private Spinner hotelLocation;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_hotel_layout);

        List<Locations> locations = Arrays.asList(Locations.values());
        ArrayAdapter<Locations> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, locations);

        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "test").fallbackToDestructiveMigration().allowMainThreadQueries().build();

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        manageHotelButton = findViewById(R.id.manageHotel);
        hotelName = findViewById(R.id.hotelName);
        hotelLocation = findViewById(R.id.locationSpinner);
        hotelLocation.setAdapter(adapter);

        String oldHotelName = getIntent().getStringExtra("name");
        String oldHotelLocation = getIntent().getStringExtra("location");
        if (oldHotelName != null) {
            hotelName.setText(oldHotelName);
            hotelLocation.setSelection(getIndex(hotelLocation, oldHotelLocation));
        }

        manageHotelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hotelId = getIntent().getStringExtra("id");
                String name = hotelName.getText().toString();
                String location = hotelLocation.getSelectedItem().toString();
                if (hotelId != null) {
                    User user = db.userDao().findUser(mSharedPreferences.getString(getString(R.string.key_login_email), ""));

                    if (user.isAdmin()) {
                        Integer id = Integer.parseInt(hotelId);
                        Hotel hotel = new Hotel(name, location);
                        hotel.setId(id);
                        db.hotelDao().update(hotel);

                    } else showToast("You are not admin, you cannot edit!");

                } else {
                    Hotel hotel = new Hotel(name, location);
                    db.hotelDao().add(hotel);
                    sendMail(name, location);
                }

                Intent intent = new Intent(ManageHotelActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });

    }

    private int getIndex(Spinner spinner, String myString) {
        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)) {
                index = i;
                break;
            }
        }
        return index;
    }

    private void sendMail(String hotelName, String hotelLocation) {
        Intent mailIntent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
        mailIntent.setType("text/plain");
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, "HotelsApp");
        mailIntent.putExtra(Intent.EXTRA_TEXT, "You have successfully added a new hotel with the name " + hotelName + " in " + hotelLocation);
        mailIntent.setData(Uri.parse("mailto:" + mSharedPreferences.getString(getString(R.string.key_login_email), "")));
        mailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
        startActivity(mailIntent);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
