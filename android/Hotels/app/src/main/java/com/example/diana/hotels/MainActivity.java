package com.example.diana.hotels;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.diana.hotels.model.Hotel;

import java.util.List;

/**
 * Created by Diana on 08-Nov-17.
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, HotelsRepoListener {

    private RecyclerView mRecyclerView;
    private Button addHotelButton;
    private HotelsRepo hotelsRepo;

    private SharedPreferences mSharedPreferences;

    ItemTouchHelper.SimpleCallback mSimpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            hotelsRepo.getHotels().remove(viewHolder.getAdapterPosition());
            hotelsRepo.notifyItemRemoved(viewHolder.getAdapterPosition());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hotelsRepo = new HotelsRepo(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String hotelName = getIntent().getStringExtra("hotelName");
        String hotelLocation = getIntent().getStringExtra("hotelLocation");

        if (hotelName != null && hotelLocation != null) {
            String position = getIntent().getStringExtra("position");
            List<Hotel> hotelList = hotelsRepo.getHotels();
            if (position != null) {
                Integer pos = Integer.parseInt(position);
                hotelList.get(pos).setName(hotelName);
                hotelList.get(pos).setLocation(hotelLocation);
            } else {
                Hotel hotel = new Hotel(hotelName, hotelLocation);
                hotelList.add(hotel);

                sendMail(hotelName, hotelLocation);
            }

            hotelsRepo.setHotels(hotelList);
        }

        addHotelButton = (Button) findViewById(R.id.add_hotel_button);
        addHotelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ManageHotelActivity.class);
                startActivity(intent);
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(hotelsRepo);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mSimpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

    }

    @Override
    public void onHotelSelected(int position) {
        Toast.makeText(this, "Hotel selected " + position, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MainActivity.this, ManageHotelActivity.class);
        intent.putExtra("position", "" + position);
        startActivity(intent);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
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

}
