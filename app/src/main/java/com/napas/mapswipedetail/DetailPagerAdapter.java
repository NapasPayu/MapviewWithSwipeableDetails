package com.napas.mapswipedetail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Payu on 2/10/16.
 */

public class DetailPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Merchant> mMerchants;

    public DetailPagerAdapter(FragmentManager fm, List<Merchant> merchants) {
        super(fm);
        mMerchants = new ArrayList<>(merchants);
    }

    @Override
    public Fragment getItem(int position) {
        return DetailFragment.newInstance(mMerchants.get(position));
    }

    @Override
    public int getCount() {
        return mMerchants.size();
    }
}
