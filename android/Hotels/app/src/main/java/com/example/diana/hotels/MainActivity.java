package com.example.diana.hotels;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.diana.hotels.db.AppDatabase;
import com.example.diana.hotels.model.Hotel;
import com.example.diana.hotels.model.User;
import com.example.diana.hotels.services.ApiClient;
import com.example.diana.hotels.services.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Diana on 08-Nov-17.
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, HotelsRepoListener {

    private RecyclerView mRecyclerView;
    private Button addHotelButton;
    private HotelsRepo hotelsRepo;

    private List<Hotel> hotels;

    private static AppDatabase db;
    private SharedPreferences mSharedPreferences;
    //private HotelsTask hotelsTask;

    private static ApiService apiService;


    ItemTouchHelper.SimpleCallback mSimpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            Integer hotelId = hotelsRepo.getHotels().get(viewHolder.getAdapterPosition()).getId();
            Hotel hotel = db.hotelDao().getById(hotelId);
            db.hotelDao().delete(hotel);
            deleteFromServer(hotelId);
            hotelsRepo.getHotels().remove(viewHolder.getAdapterPosition());
            hotelsRepo.notifyItemRemoved(viewHolder.getAdapterPosition());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "test").fallbackToDestructiveMigration().allowMainThreadQueries().build();

        apiService = ApiClient.getClient().create(ApiService.class);

        hotelsRepo = new HotelsRepo(new ArrayList<Hotel>(), this);

        addHotelButton = findViewById(R.id.add_hotel_button);
        addHotelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                User user = db.userDao().findUser(mSharedPreferences.getString(getString(R.string.key_login_email), ""));

                if (user.isAdmin()) {
                    Intent intent = new Intent(MainActivity.this, ManageHotelActivity.class);
                    startActivity(intent);

                } else showToast("You are not an admin, you cannot add a hotel! ");
            }
        });

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(hotelsRepo);


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mSimpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

    }

    @Override
    protected void onStart() {
        super.onStart();
//        hotelsTask = new HotelsTask();
//        hotelsTask.execute();

        getHotelsFromServer();

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

    private void getHotelsFromServer() {
        apiService.getHotels().enqueue(new Callback<List<Hotel>>() {
            @Override
            public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {

                if (response.isSuccessful()) {
                    hotels = response.body();
                    hotelsRepo.setHotels(hotels);
                    Log.d("MainActivity", "posts loaded from API");
                } else {
                    int statusCode = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<List<Hotel>> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");
            }
        });

    }

    private void deleteFromServer(Integer hotelId) {
        apiService.delete(hotelId).enqueue(new Callback<Hotel>() {
            @Override
            public void onResponse(Call<Hotel> call, Response<Hotel> response) {

                if (response.isSuccessful()) {
                    Log.d("MainActivity", "posts loaded from API");
                } else {
                    int statusCode = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<Hotel> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");
            }
        });
    }

    private List<Hotel> populateHotelList() {
        List<Hotel> hotelsMock = new ArrayList<>();
        hotelsMock.add(new Hotel("aa", "cluj"));
        hotelsMock.add(new Hotel("aaaa", "buc"));

        return hotelsMock;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

//    private class HotelsTask extends AsyncTask<Void, Void, List<Hotel>> {
//
//        @Override
//        protected List<Hotel> doInBackground(Void... params) {
//            return getHotelsFromServer();
//        }
//
//        @Override
//        protected void onPostExecute(List<Hotel> hotels) {
//            hotelsRepo.setHotels(hotels);
//        }
//    }
}
