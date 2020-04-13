package com.dimsonpip.loftmoney.screens.auth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dimsonpip.loftmoney.R;
import com.dimsonpip.loftmoney.screens.main.MainActivity;
import com.dimsonpip.loftmoney.screens.web.WebFactory;
import com.dimsonpip.loftmoney.screens.web.models.AuthResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AuthActivity extends AppCompatActivity {

    private List<Disposable> disposables = new ArrayList<>();
    private Button btnAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        btnAuth = findViewById(R.id.btnAuthSignIn);
        btnAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSingIn();
                
            }
        });
    }

    @Override
    protected void onDestroy() {
        for (Disposable disposable : disposables){
            disposable.dispose();
        }
        super.onDestroy();
    }

    private void startSingIn() {
        btnAuth.setVisibility(View.INVISIBLE);

        Disposable request = new WebFactory().getInstance().authRequest().request("DMP")
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AuthResponse>() {
                    @Override
                    public void accept(AuthResponse authResponse) throws Exception {
                        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name),
                                Context.MODE_PRIVATE);
                        sharedPreferences.edit()
                                .putString(AuthResponse.AUTH_TOKEN_KEY, authResponse.getAuthToken())
                                .apply();

                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        btnAuth.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "Error" + throwable.getLocalizedMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });

        disposables.add(request);
    }
}
