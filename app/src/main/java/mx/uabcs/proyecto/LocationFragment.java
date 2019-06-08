package mx.uabcs.proyecto;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static android.content.Context.LOCATION_SERVICE;

public class LocationFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private double lon, lat;
    SupportMapFragment mapFragment;

    public LocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_map, container, false);
        init();
        return v;
    }

    private void init() {

        mapFragment = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.map);


        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        mapFragment = SupportMapFragment.newInstance();
        fragmentTransaction.replace(R.id.map, mapFragment).commit();

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {

            case 10:
                geolocalizar();
                requestLocation();
                break;
            default:
                break;
        }
    }

    public void geolocalizar() {
        if (ActivityCompat.checkSelfPermission(App.getmContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(App.getmContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}, 10);
                return;
            }
        }
        locationManager.requestLocationUpdates("gps", 0, 0, locationListener);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_HIGH);
        String provider = locationManager.getBestProvider(criteria, true);
        if (ActivityCompat.checkSelfPermission(App.getmContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(App.getmContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(provider, 10000, 0, locationListener);

    }

    public void setLatLong(double latitud, double longitud) {
        this.lat = latitud;
        this.lon = longitud;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;



        UiSettings mapUiSettings = mMap.getUiSettings();
        mapUiSettings.setCompassEnabled(false);
        mapUiSettings.setRotateGesturesEnabled(true);

        mapUiSettings.setScrollGesturesEnabled(false);
        mapUiSettings.setScrollGesturesEnabledDuringRotateOrZoom(false);
        locationManager = (LocationManager) App.getmContext().getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                lon = location.getLongitude();
                lat = location.getLatitude();

                setLatLong(location.getLatitude(), location.getLongitude());
                LatLng actual = new LatLng(lat, lon);
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(actual).title("ubicacion Actual").snippet("Esta es la posicion actual del usuario"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(actual, 20));
                mMap.animateCamera(CameraUpdateFactory.zoomIn());
                ubicaciones();
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(actual)
                        .zoom(40)
                        .bearing(90)
                        .tilt(90)
                        .build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };

        geolocalizar();
        requestLocation();

    }

    private void requestLocation() {

    }

    public void ubicaciones() {
        LatLng macro  =  new LatLng(24.102732, -110.316093);
        LatLng unidad = new LatLng(24.101827, -110.316343);
        LatLng radio  = new LatLng(24.101925, -110.315799);
        LatLng biblio = new LatLng(24.101815, -110.316392);
        LatLng poliforo  = new LatLng(24.103180, -110.315897);
        LatLng rectoria = new LatLng(24.101103, -110.316711);
        LatLng lenguas = new LatLng(24.100509, -110.316596);

        mMap.addMarker(new MarkerOptions()
                .position(macro)
                .title("UABCS: Macro"));

        mMap.addMarker(new MarkerOptions()
                .position(unidad)
                .title("UABCS: Unidad medica"));

        mMap.addMarker(new MarkerOptions()
                .position(radio)
                .title("UABCS: Radio"));

        mMap.addMarker(new MarkerOptions()
                .position(biblio)
                .title("UABCS: Biblioteca"));

        mMap.addMarker(new MarkerOptions()
                .position(poliforo)
                .title("UABCS: poliforo de la universidad"));

        mMap.addMarker(new MarkerOptions()
                .position(rectoria)
                .title("UABCS: rectoria"));

        mMap.addMarker(new MarkerOptions()
                .position(lenguas)
                .title("UABCS: lenguas"));

    }

}
