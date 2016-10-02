package com.napas.mapswipedetail;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, ViewPager.OnPageChangeListener, GoogleMap.OnMarkerClickListener
{

    private GoogleMap mMap;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private ArrayList<Merchant> mMerchants;
    private HashMap<Integer, Marker> mMarkerMap = new HashMap<>();
    private int clickedPos = 0;
    private static final float DEFAULT_ZOOM = 15.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        createData();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.viewpager);
        mPagerAdapter = new DetailPagerAdapter(getSupportFragmentManager(), mMerchants);
        mPager.addOnPageChangeListener(this);
        mPager.setAdapter(mPagerAdapter);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for (int i = 0; i < mMerchants.size(); i++) {
            Merchant merchant = mMerchants.get(i);
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(merchant.getLocLat(), merchant.getLocLong()))
                    .icon(BitmapDescriptorFactory.defaultMarker(i == clickedPos ? BitmapDescriptorFactory.HUE_GREEN : BitmapDescriptorFactory.HUE_RED)));
            marker.setTag(i);
            mMarkerMap.put(i, marker);
        }

        Merchant firstMerchant = mMerchants.get(0);
        LatLng initialLatLng = new LatLng(firstMerchant.getLocLat(), firstMerchant.getLocLong());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initialLatLng, DEFAULT_ZOOM));
        mMap.setOnMarkerClickListener(this);
    }

    private void createData() {
        mMerchants = new ArrayList<>();

        Merchant mer1 = new Merchant("Spinelli Coffee Company - Fusionopolis",
                "One-north Gateway", 1.299908, 103.788698,
                "https://lh5.googleusercontent.com/-C3cxFXcU6aA/Vx3cByZeqvI/AAAAAAAAUFQ/7alpp_B-oMAiNoZsw3KfZ-pEIjBe7emBQCLIB/s408-k-no/");
        Merchant mer2 = new Merchant("Bread Yard",
                "1 Fusionopolis Place", 1.299935, 103.787989,
                "https://lh5.googleusercontent.com/-ppELI7oZLlQ/VyQrdyAmBLI/AAAAAAAAcyA/E0pQRefH70AiTj6UXKb3e8evthYK7GOigCLIB/s455-k-no/");
        Merchant mer3 = new Merchant("Penang Place Restaurant",
                "Fusionopolis One", 1.298809, 103.787657,
                "https://lh6.googleusercontent.com/-XWJqbH9mJEY/V5iy3zf7kQI/AAAAAAACBsM/nUrg29y1lkY-D1zrGFw5ynvTPIXWPEVPACLIB/s455-k-no/");
        Merchant mer4 = new Merchant("Starbucks",
                "Rochester Drive", 1.305486, 103.787968,
                "https://lh3.googleusercontent.com/-APvx8g6mfYQ/VpXPpJfvDcI/AAAAAAAAB_8/wE8wZIzFK9Anu0DPEhzTeFAtESdyf7LkQ/s408-k-no/");
        Merchant mer5 = new Merchant("Master Crab Seafood Restaurant",
                "170 Ghim Moh Road 279621", 1.311543, 103.788975,
                "https://lh3.googleusercontent.com/-DEk1sPWauIA/VuN9FLeOgVI/AAAAAAAACgI/TPYDhVdlH20AHjVp-tNEF97748HLLxs9g/s544-k-no/");
        mMerchants.add(mer1);
        mMerchants.add(mer2);
        mMerchants.add(mer3);
        mMerchants.add(mer4);
        mMerchants.add(mer5);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (clickedPos != position) {
            Marker clickedMarker = mMarkerMap.get(clickedPos);
            clickedMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        }
        Merchant merchant = mMerchants.get(position);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(merchant.getLocLat(), merchant.getLocLong())));
        Marker marker = mMarkerMap.get(position);
        marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        clickedPos = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        int position = (int) marker.getTag();
        mPager.setCurrentItem(position);
        return false;
    }
}
