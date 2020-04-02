package com.dimsonpip.loftmoney.screens.second;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.dimsonpip.loftmoney.R;
import com.dimsonpip.loftmoney.screens.main.adapter.ChargeModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;


public class AddItemActivity extends AppCompatActivity {
    private TextInputEditText textInputTitle;
    private TextInputEditText textInputPrice;
    private Button btnAddTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        textInputTitle = findViewById(R.id.title_edit_text);
        textInputPrice = findViewById(R.id.price_edit_text);
        btnAddTitle = findViewById(R.id.btnAdd);

        textInputTitle.addTextChangedListener(titleTextWatcher);
        textInputPrice.addTextChangedListener(titleTextWatcher);

        btnAddTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Objects.requireNonNull(textInputTitle.getText()).toString();
                String price = Objects.requireNonNull(textInputPrice.getText()).toString();

                String priceString = price + " ₽";
                ChargeModel chargeModel = new ChargeModel(name, priceString);

                Intent intent = new Intent();
                intent.putExtra(ChargeModel.KEY_NAME, chargeModel);
                setResult(RESULT_OK, intent);
                finish();

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
