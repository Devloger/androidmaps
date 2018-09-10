package example.a219.openstreetmap;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;


import java.util.List;

import example.a219.openstreetmap.R;

public class MainActivity extends Activity implements LocationListener {

    MyItemizedOverlay myItemizedOverlay = null;
    LocationManager locationManager;
    String bestProvider;
    Location location;
    MapView mapView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);

        Drawable marker = getResources().getDrawable(android.R.drawable.star_big_on);
        int markerWidth = marker.getIntrinsicWidth();
        int markerHeight = marker.getIntrinsicHeight();
        marker.setBounds(0, markerHeight, markerWidth, 0);

        myItemizedOverlay = new MyItemizedOverlay(marker);
        mapView.getOverlays().add(myItemizedOverlay);

//        GeoPoint myPoint1 = new GeoPoint(0 * 1000000, 0 * 1000000);
//        myItemizedOverlay.addItem(myPoint1, "myPoint1", "myPoint1");
//        GeoPoint myPoint2 = new GeoPoint(50 * 1000000, 50 * 1000000);
//        myItemizedOverlay.addItem(myPoint2, "myPoint2", "myPoint2");


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        bestProvider = locationManager.getBestProvider(new Criteria(), true);
        location = getLastKnownLocation();





        // UPDATES

        try {
            locationManager.requestLocationUpdates(bestProvider, 1000, 1, this);
        } catch (SecurityException e) {

        }








        double szer = location.getLatitude();
        double dl = location.getLongitude();

        System.out.println(szer + " :::::: " + dl);
        System.out.println(szer + " :::::: " + dl);
        System.out.println(szer + " :::::: " + dl);
        System.out.println(szer + " :::::: " + dl);
        System.out.println(szer + " :::::: " + dl);

        GeoPoint myPoint1 = new GeoPoint(szer, dl);
        myItemizedOverlay.addItem(myPoint1, "myPoint1", "myPoint1");


//        myLocationOverlay = new MyLocationOverlay(this);
//        mapView.getOverlays().add(myLocationOverlay);
//
//        myLocationOverlay.runOnFirstFix(new Runnable() {
//            public void run() {
//                mapView.getController().animateTo(myLocationOverlay.getMyLocation());
//            }
//        });

    }

//    @Override
//    protected void onResume() {
//        // TODO Auto-generated method stub
//        super.onResume();
//        myLocationOverlay.enableMyLocation();
//        myLocationOverlay.enableFollowLocation();
//    }

//    @Override
//    protected void onPause() {
//        // TODO Auto-generated method stub
//        super.onPause();
//        myLocationOverlay.disableMyLocation();
//        myLocationOverlay.disableFollowLocation();
//    }

    private Location getLastKnownLocation() {
        locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            @SuppressLint("MissingPermission") Location l = locationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        return bestLocation;
    }

    @Override
    public void onLocationChanged(Location location) {
        System.out.println("oki");


        Drawable marker = getResources().getDrawable(android.R.drawable.star_big_on);
        int markerWidth = marker.getIntrinsicWidth();
        int markerHeight = marker.getIntrinsicHeight();
        marker.setBounds(0, markerHeight, markerWidth, 0);

        myItemizedOverlay = new MyItemizedOverlay(marker);
        mapView.getOverlays().add(myItemizedOverlay);

//        GeoPoint myPoint1 = new GeoPoint(0 * 1000000, 0 * 1000000);
//        myItemizedOverlay.addItem(myPoint1, "myPoint1", "myPoint1");
//        GeoPoint myPoint2 = new GeoPoint(50 * 1000000, 50 * 1000000);
//        myItemizedOverlay.addItem(myPoint2, "myPoint2", "myPoint2");


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        bestProvider = locationManager.getBestProvider(new Criteria(), true);


        location = getLastKnownLocation();
        double szer = location.getLatitude();
        double dl = location.getLongitude();

        System.out.println(szer + " :::::: " + dl);
        System.out.println(szer + " :::::: " + dl);
        System.out.println(szer + " :::::: " + dl);
        System.out.println(szer + " :::::: " + dl);
        System.out.println(szer + " :::::: " + dl);

        GeoPoint myPoint1 = new GeoPoint(szer, dl);
        myItemizedOverlay.addItem(myPoint1, "myPoint1", "myPoint1");


        MapController mapController = (MapController)mapView.getController();
        mapController.setZoom(10);
        mapController.setCenter(myPoint1);
        mapView.invalidate();
        mapView.

//        myItemizedOverlay.notifyAll();
//        location.notifyAll();
//        locationManager.notifyAll();
//        mapView.notifyAll();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        System.out.println("oki");

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}