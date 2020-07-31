package com.mehmetomurerdolu.booking.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mehmetomurerdolu.booking.R;
import com.mehmetomurerdolu.booking.database.DatabaseClient;
import com.mehmetomurerdolu.booking.entity.UserEntity;
import com.mehmetomurerdolu.booking.ui.HomeActivity;
import com.mehmetomurerdolu.booking.ui.signup.SignupActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.kayit_btn).setOnClickListener(this);
        findViewById(R.id.giris_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.kayit_btn:
                Intent intent = new Intent(this, SignupActivity.class);
                startActivity(intent);
                break;
            case R.id.giris_btn:
                LoginControl login = new LoginControl();
                login.execute();
                break;
        }
    }


    class LoginControl extends AsyncTask<Void, Void, UserEntity> {
        ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        EditText mailTxt;
        EditText passwordTxt;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage(getResources().getString(R.string.login_is_proccess));
            progressDialog.show();
            mailTxt = findViewById(R.id.eposta_edittext);
            passwordTxt = findViewById(R.id.sifre_edittext);
        }

        @Override
        protected UserEntity doInBackground(Void... params) {
            return DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                    .userDao()
                    .findByMailAndPassword(mailTxt.getText().toString(), passwordTxt.getText().toString());
        }

        @Override
        protected void onPostExecute(UserEntity userEntity) {
            super.onPostExecute(userEntity);
            progressDialog.hide();
            if (userEntity != null) {
                SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("default"
                        ,Context.MODE_PRIVATE);
                Gson gson = new Gson();
                String json = gson.toJson(userEntity);
                sharedPref.edit().putString("user",json).apply();
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.invalid_username)
                        , Toast.LENGTH_LONG).show();
            }
        }
    }
}