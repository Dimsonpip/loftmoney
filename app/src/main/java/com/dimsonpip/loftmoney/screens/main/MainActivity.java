package com.dimsonpip.loftmoney.screens.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.dimsonpip.loftmoney.R;
import com.dimsonpip.loftmoney.screens.main.adapter.BudgetPagerAdapter;
import com.dimsonpip.loftmoney.screens.second.AddItemActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    static int ADD_ITEM_REQUEST = 1;

    public static int fragmentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tabs_main);
        final ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new BudgetPagerAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText(R.string.charges);
        tabLayout.getTabAt(1).setText(R.string.income);
//        tabLayout.getTabAt(2).setText(R.string.budget);

        FloatingActionButton floatingActionButton = findViewById(R.id.fabMain);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int activeFragmentIndex = viewPager.getCurrentItem();
                Fragment activeFragment = getSupportFragmentManager().getFragments().get(activeFragmentIndex);
                fragmentIndex = activeFragmentIndex;
                Intent AddItemActivityIntent = new Intent(getApplicationContext(), AddItemActivity.class);
                activeFragment.startActivityForResult(AddItemActivityIntent, ADD_ITEM_REQUEST);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}


