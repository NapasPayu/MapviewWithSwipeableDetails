package com.napas.mapswipedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Payu on 2/10/16.
 */

public class DetailFragment extends Fragment {

    public static final String BUNDLE_MERCHANT = "bundle_merchant";

    public static DetailFragment newInstance(Merchant merchant) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(BUNDLE_MERCHANT, merchant);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Merchant merchant = getArguments().getParcelable(BUNDLE_MERCHANT);
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_detail, container, false);

        TextView tvName = (TextView) rootView.findViewById(R.id.tv_title);
        TextView tvDetail = (TextView) rootView.findViewById(R.id.tv_detail);
        ImageView ivProfile = (ImageView) rootView.findViewById(R.id.iv_profile);

        tvName.setText(merchant.getName());
        tvDetail.setText(merchant.getDescription());
        Picasso.with(getActivity()).load(merchant.getImageUrl()).resize(100,100).centerCrop().into(ivProfile);

        return rootView;
    }
}
