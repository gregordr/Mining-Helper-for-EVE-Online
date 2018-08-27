package org.devfleet.android.minerhelper;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import org.devfleet.android.minerhelper.priceActivities.GasPrices;
import org.devfleet.android.minerhelper.priceActivities.IcePrices;
import org.devfleet.android.minerhelper.priceActivities.MineralPrices;
import org.devfleet.android.minerhelper.priceActivities.OrePrices;

public class LaunchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String launchName = PreferenceManager.getDefaultSharedPreferences(this).getString("launchName", "Ore");
        Intent launchIntent;

        switch (launchName) {
            case "Ore":
                launchIntent = new Intent(this, OrePrices.class);
                break;
            case "Ice":
                launchIntent = new Intent(this, IcePrices.class);
                break;
            case "Gas":
                launchIntent = new Intent(this, GasPrices.class);
                break;
            case "Mineral":
                launchIntent = new Intent(this, MineralPrices.class);
                break;
            default:
                launchIntent = new Intent(this, OrePrices.class);
                break;
        }

        finish();
        startActivity(launchIntent);
        this.overridePendingTransition(0, 0);
    }
}
