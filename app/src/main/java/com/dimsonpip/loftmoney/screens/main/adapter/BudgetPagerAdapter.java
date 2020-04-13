package com.dimsonpip.loftmoney.screens.main.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.dimsonpip.loftmoney.screens.main.BudgetFragment;
import com.dimsonpip.loftmoney.screens.main.ChargeFragment;
import com.dimsonpip.loftmoney.screens.main.IncomeFragment;

public class BudgetPagerAdapter extends FragmentPagerAdapter {

    public BudgetPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0: return new ChargeFragment();
            case 1: return new IncomeFragment();
//            case 2: return new BudgetFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
