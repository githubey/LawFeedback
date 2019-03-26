package com.codefair.lawfeedback.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.codefair.lawfeedback.R;
import com.codefair.lawfeedback.model.LoginDTO;
import com.codefair.lawfeedback.network.RetrofitManager;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView registerNormalText = findViewById(R.id.registerNormalText);
        registerNormalText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerNormalIntent = new Intent(LoginActivity.this, RegisterNormalActivity.class);
                startActivity(registerNormalIntent);
            }
        });

        TextView registerLawmakerText = findViewById(R.id.registerLawMakerText);
        registerLawmakerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerLawmakerIntent = new Intent(LoginActivity.this, RegisterLawmakerActivity.class);
                startActivity(registerLawmakerIntent);
            }
        });

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText email = findViewById(R.id.idText);
                EditText password = findViewById(R.id.passwordText);
                RetrofitManager.getInstance().login(new LoginDTO(email.getText().toString(), password.getText().toString()));
            }
        });
    }
}
