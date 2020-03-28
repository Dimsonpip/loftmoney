package com.dimsonpip.loftmoney.screens.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dimsonpip.loftmoney.AddItemActivity;
import com.dimsonpip.loftmoney.R;
import com.dimsonpip.loftmoney.SecondActivity;
import com.dimsonpip.loftmoney.screens.main.adapter.ChargeModel;
import com.dimsonpip.loftmoney.screens.main.adapter.ChargesAdapter;

import static android.widget.LinearLayout.HORIZONTAL;

public class MainActivity extends AppCompatActivity {

    private ChargesAdapter chargesAdapter = new ChargesAdapter();
    static int ADD_ITEM_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerMain);
        recyclerView.setAdapter(chargesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));


        findViewById(R.id.fabMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AddItemActivityIntent = new Intent(getApplicationContext(), com.dimsonpip.loftmoney.AddItemActivity.class);
                startActivityForResult(AddItemActivityIntent, ADD_ITEM_REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_ITEM_REQUEST && resultCode == RESULT_OK && data != null) {
            ChargeModel chargeModel = (ChargeModel) data.getSerializableExtra(ChargeModel.KEY_NAME);
            chargesAdapter.addDataToTop(chargeModel);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
