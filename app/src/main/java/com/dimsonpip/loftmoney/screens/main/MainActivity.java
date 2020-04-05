package com.dimsonpip.loftmoney.screens.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.dimsonpip.loftmoney.R;
import com.dimsonpip.loftmoney.screens.main.adapter.BudgetPagerAdapter;
import com.dimsonpip.loftmoney.screens.web.WebFactory;
import com.dimsonpip.loftmoney.screens.web.models.GetItemResponseModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tabs_main);
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new BudgetPagerAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText(R.string.charges);
        tabLayout.getTabAt(1).setText(R.string.income);
        tabLayout.getTabAt(2).setText(R.string.budget);
    }


}
