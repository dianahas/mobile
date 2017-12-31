package com.example.diana.hotels;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.diana.hotels.db.AppDatabase;
import com.example.diana.hotels.model.User;

/**
 * Created by dianahas on 12/31/2017.
 */

public class RegisterActivity extends AppCompatActivity {
    private EditText emailView;
    private EditText passwordView;
    private CheckBox adminCheck;

    private Button registerButton;

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "test").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Set up the login form.
        emailView = findViewById(R.id.email);
        passwordView = findViewById(R.id.password);
        adminCheck = findViewById(R.id.isAdmin);
        registerButton = findViewById(R.id.registerUserButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailView.getText().toString();
                String password = passwordView.getText().toString();
                Boolean isAdmin = adminCheck.isChecked();

                if (validate(email)) {
                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.putString(getString(R.string.key_login_email), email);
                    editor.putString(getString(R.string.key_login_password), password);
                    editor.apply();

                    User user = new User(email, password, isAdmin);
                    db.userDao().add(user);

//                    Intent intent = new Intent(getApplicationContext(), MainActivity.this);
//                    startActivity(intent);
                } else invalid();
            }
        });

    }

    private void invalid() {
        Toast.makeText(this, "Register has failed, please try again", Toast.LENGTH_SHORT).show();
    }

    private boolean validate(String email) {
        boolean valid = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailView.setError("Please enter a valid email address");
            valid = false;
        }
        return valid;
    }
}
