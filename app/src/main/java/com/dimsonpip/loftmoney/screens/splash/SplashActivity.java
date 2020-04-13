package com.dimsonpip.loftmoney.screens.splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dimsonpip.loftmoney.R;
import com.dimsonpip.loftmoney.screens.auth.AuthActivity;
import com.dimsonpip.loftmoney.screens.main.MainActivity;
import com.dimsonpip.loftmoney.screens.web.models.AuthResponse;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name),
                Context.MODE_PRIVATE);
        String authToken = sharedPreferences.getString(AuthResponse.AUTH_TOKEN_KEY, "");

        if (TextUtils.isEmpty(authToken)){
            startActivity(new Intent(getApplicationContext(), AuthActivity.class));
        } else {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        finish();
    }
}
