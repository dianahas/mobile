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

import com.example.diana.hotels.db.HotelsDBManager;
import com.example.diana.hotels.model.Hotel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diana on 08-Nov-17.
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, HotelsRepoListener {

    private RecyclerView mRecyclerView;
    private Button addHotelButton;
    private HotelsRepo hotelsRepo;

    private HotelsDBManager dbManager;
    private List<Hotel> hotels;

    ItemTouchHelper.SimpleCallback mSimpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            Integer hotelId = hotelsRepo.getHotels().get(viewHolder.getAdapterPosition()).getId();
            dbManager.deleteHotel(hotelId);
            hotelsRepo.getHotels().remove(viewHolder.getAdapterPosition());
            hotelsRepo.notifyItemRemoved(viewHolder.getAdapterPosition());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = HotelsDBManager.getInstance(this);
        getHotels();

        hotelsRepo = new HotelsRepo(hotels, this);

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
        intent.putExtra("name", "" + hotelsRepo.getHotels().get(position).getName());
        intent.putExtra("location", "" + hotelsRepo.getHotels().get(position).getLocation());
        intent.putExtra("id", "" + hotelsRepo.getHotels().get(position).getId());
        startActivity(intent);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    private void getHotels() {
        // Query Products
        hotels = dbManager.getAllProducts(true);

        // If Hotels list is empty insert the mock data into DB and query again
        if (hotels.isEmpty()) {
            dbManager.insertHotels(populateHotelList());
            hotels = dbManager.getAllProducts(true);
        }
    }

    private List<Hotel> populateHotelList() {
        List<Hotel> hotelsMock = new ArrayList<>();
        hotelsMock.add(new Hotel(1, "aa", "cluj"));
        hotelsMock.add(new Hotel(2, "aaaa", "buc"));

        return hotelsMock;

    }

}
