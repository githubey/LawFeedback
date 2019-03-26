package com.codefair.lawfeedback.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codefair.lawfeedback.R;
import com.codefair.lawfeedback.listener.SuccessRegisterationLawmakerListListener;
import com.codefair.lawfeedback.model.User;
import com.codefair.lawfeedback.network.RetrofitManager;

public class RegisterLawmakerActivity extends AppCompatActivity implements SuccessRegisterationLawmakerListListener {

    private static String TAG = "RegisterLawmakerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_lawmaker);

        Button registerLawmakerButton = findViewById(R.id.registerLawmakerButton);

        RetrofitManager.getInstance().setOnSuccessRegisterationLawmakerListener(this);

        registerLawmakerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText email = findViewById(R.id.idText);
                EditText password = findViewById(R.id.passwordText);
                EditText name = findViewById(R.id.nameText);
                User user = User.createLawmakerUserModel(email.getText().toString(), password.getText().toString(), name.getText().toString(), 1L);
                RetrofitManager.getInstance().registerLawmaker(user);
            }
        });
    }

    @Override
    public void onSuccessRegister() {
        Intent registerSuccessIntent = new Intent(RegisterLawmakerActivity.this, RegisterSuccessActivity.class);
        startActivity(registerSuccessIntent);
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy");
        RetrofitManager.getInstance().removeSuccessRegisterationLawmakerListener();
        super.onDestroy();
    }
}
