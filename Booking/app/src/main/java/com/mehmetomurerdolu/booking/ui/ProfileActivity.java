package com.mehmetomurerdolu.booking.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.mehmetomurerdolu.booking.ui.login.LoginActivity;
import com.mehmetomurerdolu.booking.ui.signup.SignupActivity;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    EditText fullnameTxt;
    EditText mailTxt;
    EditText passwordTxt;
    EditText confirmPasswordTxt;
    EditText phoneTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        findViewById(R.id.back_btn).setOnClickListener(this);
        findViewById(R.id.logout_btn).setOnClickListener(this);
        findViewById(R.id.profil_duzenle_btn).setOnClickListener(this);

        getUserDetails();
    }

    private void getUserDetails() {
        fullnameTxt = findViewById(R.id.fullname_edittext);
        mailTxt = findViewById(R.id.mail_edittext);
        passwordTxt = findViewById(R.id.password_edittext);
        confirmPasswordTxt = findViewById(R.id.confirm_password_edittext);
        phoneTxt = findViewById(R.id.phone_edittext);
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("default", MODE_PRIVATE);
        String userString = preferences.getString("user", "");
        if (!userString.equals("")) {
            Gson gson = new Gson();
            UserEntity user = gson.fromJson(userString, UserEntity.class);
            fullnameTxt.setText(user.getFullname());
            mailTxt.setText(user.getMail());
            passwordTxt.setText(user.getPassword());
            confirmPasswordTxt.setText(user.getPassword());
            phoneTxt.setText(user.getPhone());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.logout_btn:
                SharedPreferences preferences = getApplicationContext().getSharedPreferences("default", MODE_PRIVATE);
                preferences.edit().clear().apply();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.profil_duzenle_btn:
                controlFields();
                break;
        }
    }

    private void controlFields() {
        if (fullnameTxt.getText().toString().isEmpty()
                || mailTxt.getText().toString().isEmpty()
                || passwordTxt.getText().toString().isEmpty()
                || confirmPasswordTxt.getText().toString().isEmpty()
                || phoneTxt.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.invalid_fields)
                    , Toast.LENGTH_LONG).show();
            return;
        }
        if(!passwordTxt.getText().toString().equals(confirmPasswordTxt.getText().toString())){
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.password_not_confirmed)
                    , Toast.LENGTH_LONG).show();
            return;
        }

        UserEntity user = new UserEntity();
        user.setFullname(fullnameTxt.getText().toString());
        user.setMail(mailTxt.getText().toString());
        user.setPassword(passwordTxt.getText().toString());
        user.setPhone(phoneTxt.getText().toString());

        UpdateUser updateUser = new UpdateUser();
        updateUser.execute(user);
    }

    class UpdateUser extends AsyncTask<UserEntity, UserEntity, UserEntity> {
        ProgressDialog progressDialog = new ProgressDialog(ProfileActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage(getResources().getString(R.string.update_is_proccess));
            progressDialog.show();
        }

        @Override
        protected UserEntity doInBackground(UserEntity... params) {
            UserEntity userEntity = params[0];
            if (userEntity != null) {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .userDao()
                        .update(userEntity);
                return userEntity;
            }
            return null;
        }

        @Override
        protected void onPostExecute(UserEntity userEntity) {
            super.onPostExecute(userEntity);
            Gson gson = new Gson();
            String json = gson.toJson(userEntity);
            SharedPreferences preferences = getApplicationContext().getSharedPreferences("default", MODE_PRIVATE);
            preferences.edit().putString("user",json).apply();
            finish();
            progressDialog.hide();
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.update_success)
                    , Toast.LENGTH_LONG).show();
        }
    }
}