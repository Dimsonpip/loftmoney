package com.dimsonpip.loftmoney;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;


public class AddItemActivity extends AppCompatActivity {
    private TextInputEditText textInputTitle;
    private TextInputEditText textInputPrice;
    private Button btnAddTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        textInputTitle = findViewById(R.id.text_input_title);
        textInputPrice = findViewById(R.id.text_input_price);
        btnAddTitle = findViewById(R.id.btnAdd);

        textInputTitle.addTextChangedListener(titleTextWatcher);
        textInputPrice.addTextChangedListener(titleTextWatcher);

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
