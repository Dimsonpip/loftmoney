package com.dimsonpip.loftmoney.screens.second;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dimsonpip.loftmoney.R;
import com.dimsonpip.loftmoney.screens.web.WebFactory;
import com.dimsonpip.loftmoney.screens.web.models.AuthResponse;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.dimsonpip.loftmoney.screens.main.MainActivity.fragmentIndex;

public class AddItemActivity extends AppCompatActivity {

    private TextInputEditText textInputTitle;
    private TextInputEditText textInputPrice;
    private Button btnAddTitle;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.slide_out_right);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        textInputTitle = findViewById(R.id.title_edit_text);
        textInputPrice = findViewById(R.id.price_edit_text);
        btnAddTitle = findViewById(R.id.btnAdd);

        textInputTitle.addTextChangedListener(titleTextWatcher);
        textInputPrice.addTextChangedListener(titleTextWatcher);

        if (fragmentIndex == 0){
            type = "expense";
        } else
            type = "income";

        btnAddTitle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sendNewItem(Integer.valueOf(textInputPrice.getText().toString()),
                            textInputTitle.getText().toString(), type);
            }
        });
    }

    private void setLoading(Boolean state){
        textInputTitle.setEnabled(!state);
        textInputPrice.setEnabled(!state);
        btnAddTitle.setVisibility(state ? View.INVISIBLE : View.VISIBLE);
    }

    private void sendNewItem(Integer price, String name, String type) {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name),
                Context.MODE_PRIVATE);
        String authToken = sharedPreferences.getString(AuthResponse.AUTH_TOKEN_KEY, "");

        setLoading(true);

        Disposable disposable =new WebFactory().postItemRequest().request(price, name, type, authToken)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        Toast.makeText(getApplicationContext(), getString(R.string.message_success),
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        setLoading(false);
                        Toast.makeText(getApplicationContext(),throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private TextWatcher titleTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String titleInput = Objects.requireNonNull(textInputTitle.getText()).toString();
            String priceInput = Objects.requireNonNull(textInputPrice.getText()).toString();

            btnAddTitle.setEnabled(!titleInput.isEmpty() && !priceInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };
}
