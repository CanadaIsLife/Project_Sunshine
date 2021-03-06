package org.example.android.sunshine.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }
        Log.v(LOG_TAG, "onCreate");
    }
    public void onPause() {
        super.onPause();
        Log.v(LOG_TAG, "onPause");
    }
    public void onStart() {
        super.onStart();
        Log.v(LOG_TAG, "onStart");
    }
    public void onResume() {
        super.onResume();
        Log.v(LOG_TAG, "onResume");
    }
    public void onStop() {
        super.onStop();
        Log.v(LOG_TAG, "onStop");
    }
    public void onDestroy() {
        super.onDestroy();
        Log.v(LOG_TAG, "onDestroy");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            Intent settingsIntent = new Intent(this, SettingsActivity.class);
//            startActivity(settingsIntent);
//        }
        switch(id) {
            case R.id.action_settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                break;
            case R.id.action_map:
                openMap();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    private void openMap() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String location = preferences.getString(getString(R.string.pref_location_key), getString(R.string.pref_location_default));

            Intent mapIntent = new Intent(Intent.ACTION_VIEW);
            Uri geoLocation = Uri.parse("geo: 0, 0").buildUpon().appendQueryParameter("q", location).build();
            mapIntent.setData(geoLocation);
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            Log.d("openMap", "Couldn't find a map app!");
            Toast.makeText(this, "No map app installed.", Toast.LENGTH_SHORT).show();
        }
    }

}
