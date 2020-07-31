package com.mehmetomurerdolu.booking.ui.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mehmetomurerdolu.booking.R;
import com.mehmetomurerdolu.booking.database.DatabaseClient;
import com.mehmetomurerdolu.booking.entity.UserEntity;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        findViewById(R.id.kayit_tamamla_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.kayit_tamamla_btn) {
            controlFields();
        }
    }

    private void controlFields() {
        EditText fullnameTxt = findViewById(R.id.fullname_edittext);
        EditText mailTxt = findViewById(R.id.mail_edittext);
        EditText passwordTxt = findViewById(R.id.password_edittext);
        EditText confirmPasswordTxt = findViewById(R.id.confirm_password_edittext);
        EditText phoneTxt = findViewById(R.id.phone_edittext);
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

        SaveUser saveUser = new SaveUser();
        saveUser.execute(user);
    }

    class SaveUser extends AsyncTask<UserEntity, UserEntity, UserEntity> {
        ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage(getResources().getString(R.string.signup_is_proccess));
            progressDialog.show();
        }

        @Override
        protected UserEntity doInBackground(UserEntity... params) {
            UserEntity userEntity = params[0];
            if (userEntity != null) {
                UserEntity entity = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                                .userDao()
                                .findByMail(userEntity.getMail());
                if(entity != null){
                    return entity;
                }
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .userDao()
                        .insert(userEntity);
                return userEntity;
            }
            return null;
        }

        @Override
        protected void onPostExecute(UserEntity userEntity) {
            super.onPostExecute(userEntity);
            if(userEntity != null) {
                if(userEntity.getUserId() != null){
                    progressDialog.hide();
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.mail_already_using)
                            , Toast.LENGTH_LONG).show();
                }else {
                    finish();
                    progressDialog.hide();
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.signup_success)
                            , Toast.LENGTH_LONG).show();
                }
            }else{
                progressDialog.hide();
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.signup_failed)
                        , Toast.LENGTH_LONG).show();
            }
        }
    }
}