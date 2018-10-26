package org.devfleet.android.minerhelper.priceActivities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.devfleet.android.minerhelper.R;
import org.devfleet.android.minerhelper.selectionSettings.MoonSelection;
import org.devfleet.android.minerhelper.systemSettings.MoonSystemSelection;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class MoonPrices extends BasePrices {

    private final int[] Sort = new int[61];
    private GreenAdapter mAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.buttons_moon, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        base = "https://market.fuzzwork.co.uk/aggregates/?types=34,35,36,38,37,39,40,16633,16636,16635,16634,16640,16639,16637,16638,16641,16643,16644,16642,16649,16646,16647,16648,16652,16651,16650,16653";

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        RecyclerView mNumbersList = findViewById(R.id.RecV);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mNumbersList.setLayoutManager(layoutManager);
        mNumbersList.setHasFixedSize(true);
        mAdapter = new GreenAdapter();
        mNumbersList.addItemDecoration(new DividerItemDecoration(this));
        mNumbersList.setAdapter(mAdapter);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPref.registerOnSharedPreferenceChangeListener(this);
    }

    void launchSystemSettings() {
        Intent launchSystemC = new Intent(this, MoonSystemSelection.class);
        startActivity(launchSystemC);
    }

    void launchSelectionSettings() {
        Intent launchOreC = new Intent(this, MoonSelection.class);
        startActivity(launchOreC);
    }

    void sortBy() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MoonPrices.this);
        builder.setTitle(R.string.dialog_message);
        builder.setItems(R.array.sortOptionsMo, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(MoonPrices.this);
                SharedPreferences.Editor editor = sharedPref.edit();
                switch (which) {
                    case 0:
                        editor.putString("SortMo", "Default");
                        break;
                    case 1:
                        editor.putString("SortMo", "Name");
                        break;
                    case 2:
                        editor.putString("SortMo", "Price");
                        break;
                }
                editor.commit();
                resort();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void getInfo(String s) {
        JSONObject obj = null;
        try {
            obj = new JSONObject(s);
        } catch (Throwable ignored) {
        }

        Float b34, b35, b36, b38, b37, b39, b40, b16633, b16636, b16635, b16634, b16640, b16639, b16637, b16638, b16641, b16643, b16644, b16642, b16649, b16646, b16647, b16648, b16652, b16651, b16650, b16653;
        b34 = b35 = b36 = b38 = b37 = b39 = b40 = b16633 = b16636 = b16635 = b16634 = b16640 = b16639 = b16637 = b16638 = b16641 = b16643 = b16644 = b16642 = b16649 = b16646 = b16647 = b16648 = b16652 = b16651 = b16650 = b16653 = null;

        Float s34, s35, s36, s38, s37, s39, s40, s16633, s16636, s16635, s16634, s16640, s16639, s16637, s16638, s16641, s16643, s16644, s16642, s16649, s16646, s16647, s16648, s16652, s16651, s16650, s16653;
        s34 = s35 = s36 = s38 = s37 = s39 = s40 = s16633 = s16636 = s16635 = s16634 = s16640 = s16639 = s16637 = s16638 = s16641 = s16643 = s16644 = s16642 = s16649 = s16646 = s16647 = s16648 = s16652 = s16651 = s16650 = s16653 = null;

        try {
            b34 = Float.parseFloat(String.valueOf(obj.getJSONObject("34").getJSONObject("buy").get("max")));
            b35 = Float.parseFloat(String.valueOf(obj.getJSONObject("35").getJSONObject("buy").get("max")));
            b36 = Float.parseFloat(String.valueOf(obj.getJSONObject("36").getJSONObject("buy").get("max")));
            b38 = Float.parseFloat(String.valueOf(obj.getJSONObject("38").getJSONObject("buy").get("max")));
            b37 = Float.parseFloat(String.valueOf(obj.getJSONObject("37").getJSONObject("buy").get("max")));
            b39 = Float.parseFloat(String.valueOf(obj.getJSONObject("39").getJSONObject("buy").get("max")));
            b40 = Float.parseFloat(String.valueOf(obj.getJSONObject("40").getJSONObject("buy").get("max")));
            b16633 = Float.parseFloat(String.valueOf(obj.getJSONObject("16633").getJSONObject("buy").get("max")));
            b16636 = Float.parseFloat(String.valueOf(obj.getJSONObject("16636").getJSONObject("buy").get("max")));
            b16635 = Float.parseFloat(String.valueOf(obj.getJSONObject("16635").getJSONObject("buy").get("max")));
            b16634 = Float.parseFloat(String.valueOf(obj.getJSONObject("16634").getJSONObject("buy").get("max")));
            b16640 = Float.parseFloat(String.valueOf(obj.getJSONObject("16640").getJSONObject("buy").get("max")));
            b16639 = Float.parseFloat(String.valueOf(obj.getJSONObject("16639").getJSONObject("buy").get("max")));
            b16637 = Float.parseFloat(String.valueOf(obj.getJSONObject("16637").getJSONObject("buy").get("max")));
            b16638 = Float.parseFloat(String.valueOf(obj.getJSONObject("16638").getJSONObject("buy").get("max")));
            b16641 = Float.parseFloat(String.valueOf(obj.getJSONObject("16641").getJSONObject("buy").get("max")));
            b16643 = Float.parseFloat(String.valueOf(obj.getJSONObject("16643").getJSONObject("buy").get("max")));
            b16644 = Float.parseFloat(String.valueOf(obj.getJSONObject("16644").getJSONObject("buy").get("max")));
            b16642 = Float.parseFloat(String.valueOf(obj.getJSONObject("16642").getJSONObject("buy").get("max")));
            b16649 = Float.parseFloat(String.valueOf(obj.getJSONObject("16649").getJSONObject("buy").get("max")));
            b16646 = Float.parseFloat(String.valueOf(obj.getJSONObject("16646").getJSONObject("buy").get("max")));
            b16647 = Float.parseFloat(String.valueOf(obj.getJSONObject("16647").getJSONObject("buy").get("max")));
            b16648 = Float.parseFloat(String.valueOf(obj.getJSONObject("16648").getJSONObject("buy").get("max")));
            b16652 = Float.parseFloat(String.valueOf(obj.getJSONObject("16652").getJSONObject("buy").get("max")));
            b16651 = Float.parseFloat(String.valueOf(obj.getJSONObject("16651").getJSONObject("buy").get("max")));
            b16650 = Float.parseFloat(String.valueOf(obj.getJSONObject("16650").getJSONObject("buy").get("max")));
            b16653 = Float.parseFloat(String.valueOf(obj.getJSONObject("16653").getJSONObject("buy").get("max")));

            s34 = Float.parseFloat(String.valueOf(obj.getJSONObject("34").getJSONObject("sell").get("min")));
            s35 = Float.parseFloat(String.valueOf(obj.getJSONObject("35").getJSONObject("sell").get("min")));
            s36 = Float.parseFloat(String.valueOf(obj.getJSONObject("36").getJSONObject("sell").get("min")));
            s38 = Float.parseFloat(String.valueOf(obj.getJSONObject("38").getJSONObject("sell").get("min")));
            s37 = Float.parseFloat(String.valueOf(obj.getJSONObject("37").getJSONObject("sell").get("min")));
            s39 = Float.parseFloat(String.valueOf(obj.getJSONObject("39").getJSONObject("sell").get("min")));
            s40 = Float.parseFloat(String.valueOf(obj.getJSONObject("40").getJSONObject("sell").get("min")));
            s16633 = Float.parseFloat(String.valueOf(obj.getJSONObject("16633").getJSONObject("sell").get("min")));
            s16636 = Float.parseFloat(String.valueOf(obj.getJSONObject("16636").getJSONObject("sell").get("min")));
            s16635 = Float.parseFloat(String.valueOf(obj.getJSONObject("16635").getJSONObject("sell").get("min")));
            s16634 = Float.parseFloat(String.valueOf(obj.getJSONObject("16634").getJSONObject("sell").get("min")));
            s16640 = Float.parseFloat(String.valueOf(obj.getJSONObject("16640").getJSONObject("sell").get("min")));
            s16639 = Float.parseFloat(String.valueOf(obj.getJSONObject("16639").getJSONObject("sell").get("min")));
            s16637 = Float.parseFloat(String.valueOf(obj.getJSONObject("16637").getJSONObject("sell").get("min")));
            s16638 = Float.parseFloat(String.valueOf(obj.getJSONObject("16638").getJSONObject("sell").get("min")));
            s16641 = Float.parseFloat(String.valueOf(obj.getJSONObject("16641").getJSONObject("sell").get("min")));
            s16643 = Float.parseFloat(String.valueOf(obj.getJSONObject("16643").getJSONObject("sell").get("min")));
            s16644 = Float.parseFloat(String.valueOf(obj.getJSONObject("16644").getJSONObject("sell").get("min")));
            s16642 = Float.parseFloat(String.valueOf(obj.getJSONObject("16642").getJSONObject("sell").get("min")));
            s16649 = Float.parseFloat(String.valueOf(obj.getJSONObject("16649").getJSONObject("sell").get("min")));
            s16646 = Float.parseFloat(String.valueOf(obj.getJSONObject("16646").getJSONObject("sell").get("min")));
            s16647 = Float.parseFloat(String.valueOf(obj.getJSONObject("16647").getJSONObject("sell").get("min")));
            s16648 = Float.parseFloat(String.valueOf(obj.getJSONObject("16648").getJSONObject("sell").get("min")));
            s16652 = Float.parseFloat(String.valueOf(obj.getJSONObject("16652").getJSONObject("sell").get("min")));
            s16651 = Float.parseFloat(String.valueOf(obj.getJSONObject("16651").getJSONObject("sell").get("min")));
            s16650 = Float.parseFloat(String.valueOf(obj.getJSONObject("16650").getJSONObject("sell").get("min")));
            s16653 = Float.parseFloat(String.valueOf(obj.getJSONObject("16653").getJSONObject("sell").get("min")));
        } catch (JSONException e) {
        }

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();


        editor.putFloat("Uncompressed SellMo" + 1, 0.01f * (s34 * 6000 + s35 * 6000 + s36 * 400 + s16633 * 65));
        editor.putFloat("Uncompressed SellMo" + 2, 0.01f * (s34 * 10000 + s35 * 2000 + s36 * 400 + s16636 * 65));
        editor.putFloat("Uncompressed SellMo" + 3, 0.01f * (s34 * 8000 + s35 * 8000 + s36 * 400 + s16635 * 65));
        editor.putFloat("Uncompressed SellMo" + 4, 0.01f * (s34 * 4000 + s35 * 6000 + s36 * 400 + s16634 * 65));

        editor.putFloat("Uncompressed SellMo" + 5, 0.01f * (s34 * 7500 + s35 * 10000 + s36 * 500 + s16640 * 40));
        editor.putFloat("Uncompressed SellMo" + 6, 0.01f * (s34 * 10000 + s35 * 7500 + s36 * 500 + s16639 * 40));
        editor.putFloat("Uncompressed SellMo" + 7, 0.01f * (s34 * 12500 + s35 * 5000 + s36 * 500 + s16637 * 40));
        editor.putFloat("Uncompressed SellMo" + 8, 0.01f * (s34 * 15000 + s35 * 2500 + s36 * 500 + s16638 * 40));

        editor.putFloat("Uncompressed SellMo" + 9, 0.01f * (s35 * 5000 + s36 * 1250 + s37 * 750 + s38 * 50 + s16633 * 10 + s16641 * 40));
        editor.putFloat("Uncompressed SellMo" + 10, 0.01f * (s34 * 5000 + s36 * 1500 + s37 * 500 + s38 * 50 + s16634 * 10 + s16643 * 40));
        editor.putFloat("Uncompressed SellMo" + 11, 0.01f * (s34 * 5000 + s36 * 1000 + s37 * 1000 + s39 * 50 + s16635 * 10 + s16644 * 40));
        editor.putFloat("Uncompressed SellMo" + 12, 0.01f * (s35 * 5000 + s36 * 750 + s37 * 1250 + s39 * 50 + s16636 * 10 + s16642 * 40));

        editor.putFloat("Uncompressed SellMo" + 13, 0.01f * (s36 * 1000 + s37 * 1250 + s39 * 50 + s16634 * 15 + s16640 * 10 + s16649 * 50));
        editor.putFloat("Uncompressed SellMo" + 14, 0.01f * (s36 * 1500 + s37 * 750 + s40 * 50 + s16635 * 15 + s16637 * 10 + s16646 * 50));
        editor.putFloat("Uncompressed SellMo" + 15, 0.01f * (s36 * 1250 + s37 * 1000 + s39 * 50 + s16633 * 15 + s16639 * 10 + s16647 * 50));
        editor.putFloat("Uncompressed SellMo" + 16, 0.01f * (s36 * 1750 + s37 * 500 + s40 * 50 + s16636 * 15 + s16638 * 10 + s16648 * 50));

        editor.putFloat("Uncompressed SellMo" + 17, 0.01f * (s38 * 100 + s39 * 200 + s40 * 50 + s16633 * 20 + s16639 * 20 + s16644 * 10 + s16652 * 22));
        editor.putFloat("Uncompressed SellMo" + 18, 0.01f * (s38 * 50 + s39 * 150 + s40 * 150 + s16635 * 20 + s16637 * 20 + s16641 * 10 + s16651 * 22));
        editor.putFloat("Uncompressed SellMo" + 19, 0.01f * (s38 * 200 + s39 * 100 + s40 * 50 + s16634 * 20 + s16640 * 20 + s16642 * 10 + s16650 * 22));
        editor.putFloat("Uncompressed SellMo" + 20, 0.01f * (s38 * 50 + s39 * 100 + s40 * 200 + s16636 * 20 + s16638 * 20 + s16643 * 10 + s16653 * 22));

        editor.putFloat("Uncompressed BuyMo" + 1, 0.01f * (b34 * 6000 + b35 * 6000 + b36 * 400 + b16633 * 65));
        editor.putFloat("Uncompressed BuyMo" + 2, 0.01f * (b34 * 10000 + b35 * 2000 + b36 * 400 + b16636 * 65));
        editor.putFloat("Uncompressed BuyMo" + 3, 0.01f * (b34 * 8000 + b35 * 8000 + b36 * 400 + b16635 * 65));
        editor.putFloat("Uncompressed BuyMo" + 4, 0.01f * (b34 * 4000 + b35 * 6000 + b36 * 400 + b16634 * 65));

        editor.putFloat("Uncompressed BuyMo" + 5, 0.01f * (b34 * 7500 + b35 * 10000 + b36 * 500 + b16640 * 40));
        editor.putFloat("Uncompressed BuyMo" + 6, 0.01f * (b34 * 10000 + b35 * 7500 + b36 * 500 + b16639 * 40));
        editor.putFloat("Uncompressed BuyMo" + 7, 0.01f * (b34 * 12500 + b35 * 5000 + b36 * 500 + b16637 * 40));
        editor.putFloat("Uncompressed BuyMo" + 8, 0.01f * (b34 * 15000 + b35 * 2500 + b36 * 500 + b16638 * 40));

        editor.putFloat("Uncompressed BuyMo" + 9, 0.01f * (b35 * 5000 + b36 * 1250 + b37 * 750 + b38 * 50 + b16633 * 10 + b16641 * 40));
        editor.putFloat("Uncompressed BuyMo" + 10, 0.01f * (b34 * 5000 + b36 * 1500 + b37 * 500 + b38 * 50 + b16634 * 10 + b16643 * 40));
        editor.putFloat("Uncompressed BuyMo" + 11, 0.01f * (b34 * 5000 + b36 * 1000 + b37 * 1000 + b39 * 50 + b16635 * 10 + b16644 * 40));
        editor.putFloat("Uncompressed BuyMo" + 12, 0.01f * (b35 * 5000 + b36 * 750 + b37 * 1250 + b39 * 50 + b16636 * 10 + b16642 * 40));

        editor.putFloat("Uncompressed BuyMo" + 13, 0.01f * (b36 * 1000 + b37 * 1250 + b39 * 50 + b16634 * 15 + b16640 * 10 + b16649 * 50));
        editor.putFloat("Uncompressed BuyMo" + 14, 0.01f * (b36 * 1500 + b37 * 750 + b40 * 50 + b16635 * 15 + b16637 * 10 + b16646 * 50));
        editor.putFloat("Uncompressed BuyMo" + 15, 0.01f * (b36 * 1250 + b37 * 1000 + b39 * 50 + b16633 * 15 + b16639 * 10 + b16647 * 50));
        editor.putFloat("Uncompressed BuyMo" + 16, 0.01f * (b36 * 1750 + b37 * 500 + b40 * 50 + b16636 * 15 + b16638 * 10 + b16648 * 50));

        editor.putFloat("Uncompressed BuyMo" + 17, 0.01f * (b38 * 100 + b39 * 200 + b40 * 50 + b16633 * 20 + b16639 * 20 + b16644 * 10 + b16652 * 22));
        editor.putFloat("Uncompressed BuyMo" + 18, 0.01f * (b38 * 50 + b39 * 150 + b40 * 150 + b16635 * 20 + b16637 * 20 + b16641 * 10 + b16651 * 22));
        editor.putFloat("Uncompressed BuyMo" + 19, 0.01f * (b38 * 200 + b39 * 100 + b40 * 50 + b16634 * 20 + b16640 * 20 + b16642 * 10 + b16650 * 22));
        editor.putFloat("Uncompressed BuyMo" + 20, 0.01f * (b38 * 50 + b39 * 100 + b40 * 200 + b16636 * 20 + b16638 * 20 + b16643 * 10 + b16653 * 22));

        editor.putFloat("Compressed SellMo" + 1, 0.01f * (s34 * 6000 + s35 * 6000 + s36 * 400 + s16633 * 65));
        editor.putFloat("Compressed SellMo" + 2, 0.01f * (s34 * 10000 + s35 * 2000 + s36 * 400 + s16636 * 65));
        editor.putFloat("Compressed SellMo" + 3, 0.01f * (s34 * 8000 + s35 * 8000 + s36 * 400 + s16635 * 65));
        editor.putFloat("Compressed SellMo" + 4, 0.01f * (s34 * 4000 + s35 * 6000 + s36 * 400 + s16634 * 65));

        editor.putFloat("Compressed SellMo" + 5, 0.01f * (s34 * 7500 + s35 * 10000 + s36 * 500 + s16640 * 40));
        editor.putFloat("Compressed SellMo" + 6, 0.01f * (s34 * 10000 + s35 * 7500 + s36 * 500 + s16639 * 40));
        editor.putFloat("Compressed SellMo" + 7, 0.01f * (s34 * 12500 + s35 * 5000 + s36 * 500 + s16637 * 40));
        editor.putFloat("Compressed SellMo" + 8, 0.01f * (s34 * 15000 + s35 * 2500 + s36 * 500 + s16638 * 40));

        editor.putFloat("Compressed SellMo" + 9, 0.01f * (s35 * 5000 + s36 * 1250 + s37 * 750 + s38 * 50 + s16633 * 10 + s16641 * 40));
        editor.putFloat("Compressed SellMo" + 10, 0.01f * (s34 * 5000 + s36 * 1500 + s37 * 500 + s38 * 50 + s16634 * 10 + s16643 * 40));
        editor.putFloat("Compressed SellMo" + 11, 0.01f * (s34 * 5000 + s36 * 1000 + s37 * 1000 + s39 * 50 + s16635 * 10 + s16644 * 40));
        editor.putFloat("Compressed SellMo" + 12, 0.01f * (s35 * 5000 + s36 * 750 + s37 * 1250 + s39 * 50 + s16636 * 10 + s16642 * 40));

        editor.putFloat("Compressed SellMo" + 13, 0.01f * (s36 * 1000 + s37 * 1250 + s39 * 50 + s16634 * 15 + s16640 * 10 + s16649 * 50));
        editor.putFloat("Compressed SellMo" + 14, 0.01f * (s36 * 1500 + s37 * 750 + s40 * 50 + s16635 * 15 + s16637 * 10 + s16646 * 50));
        editor.putFloat("Compressed SellMo" + 15, 0.01f * (s36 * 1250 + s37 * 1000 + s39 * 50 + s16633 * 15 + s16639 * 10 + s16647 * 50));
        editor.putFloat("Compressed SellMo" + 16, 0.01f * (s36 * 1750 + s37 * 500 + s40 * 50 + s16636 * 15 + s16638 * 10 + s16648 * 50));

        editor.putFloat("Compressed SellMo" + 17, 0.01f * (s38 * 100 + s39 * 200 + s40 * 50 + s16633 * 20 + s16639 * 20 + s16644 * 10 + s16652 * 22));
        editor.putFloat("Compressed SellMo" + 18, 0.01f * (s38 * 50 + s39 * 150 + s40 * 150 + s16635 * 20 + s16637 * 20 + s16641 * 10 + s16651 * 22));
        editor.putFloat("Compressed SellMo" + 19, 0.01f * (s38 * 200 + s39 * 100 + s40 * 50 + s16634 * 20 + s16640 * 20 + s16642 * 10 + s16650 * 22));
        editor.putFloat("Compressed SellMo" + 20, 0.01f * (s38 * 50 + s39 * 100 + s40 * 200 + s16636 * 20 + s16638 * 20 + s16643 * 10 + s16653 * 22));

        editor.putFloat("Compressed BuyMo" + 1, 0.01f * (b34 * 6000 + b35 * 6000 + b36 * 400 + b16633 * 65));
        editor.putFloat("Compressed BuyMo" + 2, 0.01f * (b34 * 10000 + b35 * 2000 + b36 * 400 + b16636 * 65));
        editor.putFloat("Compressed BuyMo" + 3, 0.01f * (b34 * 8000 + b35 * 8000 + b36 * 400 + b16635 * 65));
        editor.putFloat("Compressed BuyMo" + 4, 0.01f * (b34 * 4000 + b35 * 6000 + b36 * 400 + b16634 * 65));

        editor.putFloat("Compressed BuyMo" + 5, 0.01f * (b34 * 7500 + b35 * 10000 + b36 * 500 + b16640 * 40));
        editor.putFloat("Compressed BuyMo" + 6, 0.01f * (b34 * 10000 + b35 * 7500 + b36 * 500 + b16639 * 40));
        editor.putFloat("Compressed BuyMo" + 7, 0.01f * (b34 * 12500 + b35 * 5000 + b36 * 500 + b16637 * 40));
        editor.putFloat("Compressed BuyMo" + 8, 0.01f * (b34 * 15000 + b35 * 2500 + b36 * 500 + b16638 * 40));

        editor.putFloat("Compressed BuyMo" + 9, 0.01f * (b35 * 5000 + b36 * 1250 + b37 * 750 + b38 * 50 + b16633 * 10 + b16641 * 40));
        editor.putFloat("Compressed BuyMo" + 10, 0.01f * (b34 * 5000 + b36 * 1500 + b37 * 500 + b38 * 50 + b16634 * 10 + b16643 * 40));
        editor.putFloat("Compressed BuyMo" + 11, 0.01f * (b34 * 5000 + b36 * 1000 + b37 * 1000 + b39 * 50 + b16635 * 10 + b16644 * 40));
        editor.putFloat("Compressed BuyMo" + 12, 0.01f * (b35 * 5000 + b36 * 750 + b37 * 1250 + b39 * 50 + b16636 * 10 + b16642 * 40));

        editor.putFloat("Compressed BuyMo" + 13, 0.01f * (b36 * 1000 + b37 * 1250 + b39 * 50 + b16634 * 15 + b16640 * 10 + b16649 * 50));
        editor.putFloat("Compressed BuyMo" + 14, 0.01f * (b36 * 1500 + b37 * 750 + b40 * 50 + b16635 * 15 + b16637 * 10 + b16646 * 50));
        editor.putFloat("Compressed BuyMo" + 15, 0.01f * (b36 * 1250 + b37 * 1000 + b39 * 50 + b16633 * 15 + b16639 * 10 + b16647 * 50));
        editor.putFloat("Compressed BuyMo" + 16, 0.01f * (b36 * 1750 + b37 * 500 + b40 * 50 + b16636 * 15 + b16638 * 10 + b16648 * 50));

        editor.putFloat("Compressed BuyMo" + 17, 0.01f * (b38 * 100 + b39 * 200 + b40 * 50 + b16633 * 20 + b16639 * 20 + b16644 * 10 + b16652 * 22));
        editor.putFloat("Compressed BuyMo" + 18, 0.01f * (b38 * 50 + b39 * 150 + b40 * 150 + b16635 * 20 + b16637 * 20 + b16641 * 10 + b16651 * 22));
        editor.putFloat("Compressed BuyMo" + 19, 0.01f * (b38 * 200 + b39 * 100 + b40 * 50 + b16634 * 20 + b16640 * 20 + b16642 * 10 + b16650 * 22));
        editor.putFloat("Compressed BuyMo" + 20, 0.01f * (b38 * 50 + b39 * 100 + b40 * 200 + b16636 * 20 + b16638 * 20 + b16643 * 10 + b16653 * 22));

        editor.putFloat("Uncompressed SellMo" + (20 + 1), 0.01f * ((s34 * 6000 + s35 * 6000 + s36 * 400 + s16633 * 65) * 1.15f));
        editor.putFloat("Uncompressed SellMo" + (20 + 2), 0.01f * ((s34 * 10000 + s35 * 2000 + s36 * 400 + s16636 * 65) * 1.15f));
        editor.putFloat("Uncompressed SellMo" + (20 + 3), 0.01f * ((s34 * 8000 + s35 * 8000 + s36 * 400 + s16635 * 65) * 1.15f));
        editor.putFloat("Uncompressed SellMo" + (20 + 4), 0.01f * ((s34 * 4000 + s35 * 6000 + s36 * 400 + s16634 * 65) * 1.15f));

        editor.putFloat("Uncompressed SellMo" + (20 + 5), 0.01f * ((s34 * 7500 + s35 * 10000 + s36 * 500 + s16640 * 40) * 1.15f));
        editor.putFloat("Uncompressed SellMo" + (20 + 6), 0.01f * ((s34 * 10000 + s35 * 7500 + s36 * 500 + s16639 * 40) * 1.15f));
        editor.putFloat("Uncompressed SellMo" + (20 + 7), 0.01f * ((s34 * 12500 + s35 * 5000 + s36 * 500 + s16637 * 40) * 1.15f));
        editor.putFloat("Uncompressed SellMo" + (20 + 8), 0.01f * ((s34 * 15000 + s35 * 2500 + s36 * 500 + s16638 * 40) * 1.15f));

        editor.putFloat("Uncompressed SellMo" + (20 + 9), 0.01f * ((s35 * 5000 + s36 * 1250 + s37 * 750 + s38 * 50 + s16633 * 10 + s16641 * 40) * 1.15f));
        editor.putFloat("Uncompressed SellMo" + (20 + 10), 0.01f * ((s34 * 5000 + s36 * 1500 + s37 * 500 + s38 * 50 + s16634 * 10 + s16643 * 40) * 1.15f));
        editor.putFloat("Uncompressed SellMo" + (20 + 11), 0.01f * ((s34 * 5000 + s36 * 1000 + s37 * 1000 + s39 * 50 + s16635 * 10 + s16644 * 40) * 1.15f));
        editor.putFloat("Uncompressed SellMo" + (20 + 12), 0.01f * ((s35 * 5000 + s36 * 750 + s37 * 1250 + s39 * 50 + s16636 * 10 + s16642 * 40) * 1.15f));

        editor.putFloat("Uncompressed SellMo" + (20 + 13), 0.01f * ((s36 * 1000 + s37 * 1250 + s39 * 50 + s16634 * 15 + s16640 * 10 + s16649 * 50) * 1.15f));
        editor.putFloat("Uncompressed SellMo" + (20 + 14), 0.01f * ((s36 * 1500 + s37 * 750 + s40 * 50 + s16635 * 15 + s16637 * 10 + s16646 * 50) * 1.15f));
        editor.putFloat("Uncompressed SellMo" + (20 + 15), 0.01f * ((s36 * 1250 + s37 * 1000 + s39 * 50 + s16633 * 15 + s16639 * 10 + s16647 * 50) * 1.15f));
        editor.putFloat("Uncompressed SellMo" + (20 + 16), 0.01f * ((s36 * 1750 + s37 * 500 + s40 * 50 + s16636 * 15 + s16638 * 10 + s16648 * 50) * 1.15f));

        editor.putFloat("Uncompressed SellMo" + (20 + 17), 0.01f * ((s38 * 100 + s39 * 200 + s40 * 50 + s16633 * 20 + s16639 * 20 + s16644 * 10 + s16652 * 22) * 1.15f));
        editor.putFloat("Uncompressed SellMo" + (20 + 18), 0.01f * ((s38 * 50 + s39 * 150 + s40 * 150 + s16635 * 20 + s16637 * 20 + s16641 * 10 + s16651 * 22) * 1.15f));
        editor.putFloat("Uncompressed SellMo" + (20 + 19), 0.01f * ((s38 * 200 + s39 * 100 + s40 * 50 + s16634 * 20 + s16640 * 20 + s16642 * 10 + s16650 * 22) * 1.15f));
        editor.putFloat("Uncompressed SellMo" + (20 + 20), 0.01f * ((s38 * 50 + s39 * 100 + s40 * 200 + s16636 * 20 + s16638 * 20 + s16643 * 10 + s16653 * 22) * 1.15f));

        editor.putFloat("Uncompressed BuyMo" + (20 + 1), 0.01f * ((b34 * 6000 + b35 * 6000 + b36 * 400 + b16633 * 65) * 1.15f));
        editor.putFloat("Uncompressed BuyMo" + (20 + 2), 0.01f * ((b34 * 10000 + b35 * 2000 + b36 * 400 + b16636 * 65) * 1.15f));
        editor.putFloat("Uncompressed BuyMo" + (20 + 3), 0.01f * ((b34 * 8000 + b35 * 8000 + b36 * 400 + b16635 * 65) * 1.15f));
        editor.putFloat("Uncompressed BuyMo" + (20 + 4), 0.01f * ((b34 * 4000 + b35 * 6000 + b36 * 400 + b16634 * 65) * 1.15f));

        editor.putFloat("Uncompressed BuyMo" + (20 + 5), 0.01f * ((b34 * 7500 + b35 * 10000 + b36 * 500 + b16640 * 40) * 1.15f));
        editor.putFloat("Uncompressed BuyMo" + (20 + 6), 0.01f * ((b34 * 10000 + b35 * 7500 + b36 * 500 + b16639 * 40) * 1.15f));
        editor.putFloat("Uncompressed BuyMo" + (20 + 7), 0.01f * ((b34 * 12500 + b35 * 5000 + b36 * 500 + b16637 * 40) * 1.15f));
        editor.putFloat("Uncompressed BuyMo" + (20 + 8), 0.01f * ((b34 * 15000 + b35 * 2500 + b36 * 500 + b16638 * 40) * 1.15f));

        editor.putFloat("Uncompressed BuyMo" + (20 + 9), 0.01f * ((b35 * 5000 + b36 * 1250 + b37 * 750 + b38 * 50 + b16633 * 10 + b16641 * 40) * 1.15f));
        editor.putFloat("Uncompressed BuyMo" + (20 + 10), 0.01f * ((b34 * 5000 + b36 * 1500 + b37 * 500 + b38 * 50 + b16634 * 10 + b16643 * 40) * 1.15f));
        editor.putFloat("Uncompressed BuyMo" + (20 + 11), 0.01f * ((b34 * 5000 + b36 * 1000 + b37 * 1000 + b39 * 50 + b16635 * 10 + b16644 * 40) * 1.15f));
        editor.putFloat("Uncompressed BuyMo" + (20 + 12), 0.01f * ((b35 * 5000 + b36 * 750 + b37 * 1250 + b39 * 50 + b16636 * 10 + b16642 * 40) * 1.15f));

        editor.putFloat("Uncompressed BuyMo" + (20 + 13), 0.01f * ((b36 * 1000 + b37 * 1250 + b39 * 50 + b16634 * 15 + b16640 * 10 + b16649 * 50) * 1.15f));
        editor.putFloat("Uncompressed BuyMo" + (20 + 14), 0.01f * ((b36 * 1500 + b37 * 750 + b40 * 50 + b16635 * 15 + b16637 * 10 + b16646 * 50) * 1.15f));
        editor.putFloat("Uncompressed BuyMo" + (20 + 15), 0.01f * ((b36 * 1250 + b37 * 1000 + b39 * 50 + b16633 * 15 + b16639 * 10 + b16647 * 50) * 1.15f));
        editor.putFloat("Uncompressed BuyMo" + (20 + 16), 0.01f * ((b36 * 1750 + b37 * 500 + b40 * 50 + b16636 * 15 + b16638 * 10 + b16648 * 50) * 1.15f));

        editor.putFloat("Uncompressed BuyMo" + (20 + 17), 0.01f * ((b38 * 100 + b39 * 200 + b40 * 50 + b16633 * 20 + b16639 * 20 + b16644 * 10 + b16652 * 22) * 1.15f));
        editor.putFloat("Uncompressed BuyMo" + (20 + 18), 0.01f * ((b38 * 50 + b39 * 150 + b40 * 150 + b16635 * 20 + b16637 * 20 + b16641 * 10 + b16651 * 22) * 1.15f));
        editor.putFloat("Uncompressed BuyMo" + (20 + 19), 0.01f * ((b38 * 200 + b39 * 100 + b40 * 50 + b16634 * 20 + b16640 * 20 + b16642 * 10 + b16650 * 22) * 1.15f));
        editor.putFloat("Uncompressed BuyMo" + (20 + 20), 0.01f * ((b38 * 50 + b39 * 100 + b40 * 200 + b16636 * 20 + b16638 * 20 + b16643 * 10 + b16653 * 22) * 1.15f));

        editor.putFloat("Compressed SellMo" + (20 + 1), 0.01f * ((s34 * 6000 + s35 * 6000 + s36 * 400 + s16633 * 65) * 1.15f));
        editor.putFloat("Compressed SellMo" + (20 + 2), 0.01f * ((s34 * 10000 + s35 * 2000 + s36 * 400 + s16636 * 65) * 1.15f));
        editor.putFloat("Compressed SellMo" + (20 + 3), 0.01f * ((s34 * 8000 + s35 * 8000 + s36 * 400 + s16635 * 65) * 1.15f));
        editor.putFloat("Compressed SellMo" + (20 + 4), 0.01f * ((s34 * 4000 + s35 * 6000 + s36 * 400 + s16634 * 65) * 1.15f));

        editor.putFloat("Compressed SellMo" + (20 + 5), 0.01f * ((s34 * 7500 + s35 * 10000 + s36 * 500 + s16640 * 40) * 1.15f));
        editor.putFloat("Compressed SellMo" + (20 + 6), 0.01f * ((s34 * 10000 + s35 * 7500 + s36 * 500 + s16639 * 40) * 1.15f));
        editor.putFloat("Compressed SellMo" + (20 + 7), 0.01f * ((s34 * 12500 + s35 * 5000 + s36 * 500 + s16637 * 40) * 1.15f));
        editor.putFloat("Compressed SellMo" + (20 + 8), 0.01f * ((s34 * 15000 + s35 * 2500 + s36 * 500 + s16638 * 40) * 1.15f));

        editor.putFloat("Compressed SellMo" + (20 + 9), 0.01f * ((s35 * 5000 + s36 * 1250 + s37 * 750 + s38 * 50 + s16633 * 10 + s16641 * 40) * 1.15f));
        editor.putFloat("Compressed SellMo" + (20 + 10), 0.01f * ((s34 * 5000 + s36 * 1500 + s37 * 500 + s38 * 50 + s16634 * 10 + s16643 * 40) * 1.15f));
        editor.putFloat("Compressed SellMo" + (20 + 11), 0.01f * ((s34 * 5000 + s36 * 1000 + s37 * 1000 + s39 * 50 + s16635 * 10 + s16644 * 40) * 1.15f));
        editor.putFloat("Compressed SellMo" + (20 + 12), 0.01f * ((s35 * 5000 + s36 * 750 + s37 * 1250 + s39 * 50 + s16636 * 10 + s16642 * 40) * 1.15f));

        editor.putFloat("Compressed SellMo" + (20 + 13), 0.01f * ((s36 * 1000 + s37 * 1250 + s39 * 50 + s16634 * 15 + s16640 * 10 + s16649 * 50) * 1.15f));
        editor.putFloat("Compressed SellMo" + (20 + 14), 0.01f * ((s36 * 1500 + s37 * 750 + s40 * 50 + s16635 * 15 + s16637 * 10 + s16646 * 50) * 1.15f));
        editor.putFloat("Compressed SellMo" + (20 + 15), 0.01f * ((s36 * 1250 + s37 * 1000 + s39 * 50 + s16633 * 15 + s16639 * 10 + s16647 * 50) * 1.15f));
        editor.putFloat("Compressed SellMo" + (20 + 16), 0.01f * ((s36 * 1750 + s37 * 500 + s40 * 50 + s16636 * 15 + s16638 * 10 + s16648 * 50) * 1.15f));

        editor.putFloat("Compressed SellMo" + (20 + 17), 0.01f * ((s38 * 100 + s39 * 200 + s40 * 50 + s16633 * 20 + s16639 * 20 + s16644 * 10 + s16652 * 22) * 1.15f));
        editor.putFloat("Compressed SellMo" + (20 + 18), 0.01f * ((s38 * 50 + s39 * 150 + s40 * 150 + s16635 * 20 + s16637 * 20 + s16641 * 10 + s16651 * 22) * 1.15f));
        editor.putFloat("Compressed SellMo" + (20 + 19), 0.01f * ((s38 * 200 + s39 * 100 + s40 * 50 + s16634 * 20 + s16640 * 20 + s16642 * 10 + s16650 * 22) * 1.15f));
        editor.putFloat("Compressed SellMo" + (20 + 20), 0.01f * ((s38 * 50 + s39 * 100 + s40 * 200 + s16636 * 20 + s16638 * 20 + s16643 * 10 + s16653 * 22) * 1.15f));

        editor.putFloat("Compressed BuyMo" + (20 + 1), 0.01f * ((b34 * 6000 + b35 * 6000 + b36 * 400 + b16633 * 65) * 1.15f));
        editor.putFloat("Compressed BuyMo" + (20 + 2), 0.01f * ((b34 * 10000 + b35 * 2000 + b36 * 400 + b16636 * 65) * 1.15f));
        editor.putFloat("Compressed BuyMo" + (20 + 3), 0.01f * ((b34 * 8000 + b35 * 8000 + b36 * 400 + b16635 * 65) * 1.15f));
        editor.putFloat("Compressed BuyMo" + (20 + 4), 0.01f * ((b34 * 4000 + b35 * 6000 + b36 * 400 + b16634 * 65) * 1.15f));

        editor.putFloat("Compressed BuyMo" + (20 + 5), 0.01f * ((b34 * 7500 + b35 * 10000 + b36 * 500 + b16640 * 40) * 1.15f));
        editor.putFloat("Compressed BuyMo" + (20 + 6), 0.01f * ((b34 * 10000 + b35 * 7500 + b36 * 500 + b16639 * 40) * 1.15f));
        editor.putFloat("Compressed BuyMo" + (20 + 7), 0.01f * ((b34 * 12500 + b35 * 5000 + b36 * 500 + b16637 * 40) * 1.15f));
        editor.putFloat("Compressed BuyMo" + (20 + 8), 0.01f * ((b34 * 15000 + b35 * 2500 + b36 * 500 + b16638 * 40) * 1.15f));

        editor.putFloat("Compressed BuyMo" + (20 + 9), 0.01f * ((b35 * 5000 + b36 * 1250 + b37 * 750 + b38 * 50 + b16633 * 10 + b16641 * 40) * 1.15f));
        editor.putFloat("Compressed BuyMo" + (20 + 10), 0.01f * ((b34 * 5000 + b36 * 1500 + b37 * 500 + b38 * 50 + b16634 * 10 + b16643 * 40) * 1.15f));
        editor.putFloat("Compressed BuyMo" + (20 + 11), 0.01f * ((b34 * 5000 + b36 * 1000 + b37 * 1000 + b39 * 50 + b16635 * 10 + b16644 * 40) * 1.15f));
        editor.putFloat("Compressed BuyMo" + (20 + 12), 0.01f * ((b35 * 5000 + b36 * 750 + b37 * 1250 + b39 * 50 + b16636 * 10 + b16642 * 40) * 1.15f));

        editor.putFloat("Compressed BuyMo" + (20 + 13), 0.01f * ((b36 * 1000 + b37 * 1250 + b39 * 50 + b16634 * 15 + b16640 * 10 + b16649 * 50) * 1.15f));
        editor.putFloat("Compressed BuyMo" + (20 + 14), 0.01f * ((b36 * 1500 + b37 * 750 + b40 * 50 + b16635 * 15 + b16637 * 10 + b16646 * 50) * 1.15f));
        editor.putFloat("Compressed BuyMo" + (20 + 15), 0.01f * ((b36 * 1250 + b37 * 1000 + b39 * 50 + b16633 * 15 + b16639 * 10 + b16647 * 50) * 1.15f));
        editor.putFloat("Compressed BuyMo" + (20 + 16), 0.01f * ((b36 * 1750 + b37 * 500 + b40 * 50 + b16636 * 15 + b16638 * 10 + b16648 * 50) * 1.15f));

        editor.putFloat("Compressed BuyMo" + (20 + 17), 0.01f * ((b38 * 100 + b39 * 200 + b40 * 50 + b16633 * 20 + b16639 * 20 + b16644 * 10 + b16652 * 22) * 1.15f));
        editor.putFloat("Compressed BuyMo" + (20 + 18), 0.01f * ((b38 * 50 + b39 * 150 + b40 * 150 + b16635 * 20 + b16637 * 20 + b16641 * 10 + b16651 * 22) * 1.15f));
        editor.putFloat("Compressed BuyMo" + (20 + 19), 0.01f * ((b38 * 200 + b39 * 100 + b40 * 50 + b16634 * 20 + b16640 * 20 + b16642 * 10 + b16650 * 22) * 1.15f));
        editor.putFloat("Compressed BuyMo" + (20 + 20), 0.01f * ((b38 * 50 + b39 * 100 + b40 * 200 + b16636 * 20 + b16638 * 20 + b16643 * 10 + b16653 * 22) * 1.15f));

        editor.putFloat("Uncompressed SellMo" + (40 + 1), 0.01f * ((s34 * 6000 + s35 * 6000 + s36 * 400 + s16633 * 65) * 2));
        editor.putFloat("Uncompressed SellMo" + (40 + 2), 0.01f * ((s34 * 10000 + s35 * 2000 + s36 * 400 + s16636 * 65) * 2));
        editor.putFloat("Uncompressed SellMo" + (40 + 3), 0.01f * ((s34 * 8000 + s35 * 8000 + s36 * 400 + s16635 * 65) * 2));
        editor.putFloat("Uncompressed SellMo" + (40 + 4), 0.01f * ((s34 * 4000 + s35 * 6000 + s36 * 400 + s16634 * 65) * 2));

        editor.putFloat("Uncompressed SellMo" + (40 + 5), 0.01f * ((s34 * 7500 + s35 * 10000 + s36 * 500 + s16640 * 40) * 2));
        editor.putFloat("Uncompressed SellMo" + (40 + 6), 0.01f * ((s34 * 10000 + s35 * 7500 + s36 * 500 + s16639 * 40) * 2));
        editor.putFloat("Uncompressed SellMo" + (40 + 7), 0.01f * ((s34 * 12500 + s35 * 5000 + s36 * 500 + s16637 * 40) * 2));
        editor.putFloat("Uncompressed SellMo" + (40 + 8), 0.01f * ((s34 * 15000 + s35 * 2500 + s36 * 500 + s16638 * 40) * 2));

        editor.putFloat("Uncompressed SellMo" + (40 + 9), 0.01f * ((s35 * 5000 + s36 * 1250 + s37 * 750 + s38 * 50 + s16633 * 10 + s16641 * 40) * 2));
        editor.putFloat("Uncompressed SellMo" + (40 + 10), 0.01f * ((s34 * 5000 + s36 * 1500 + s37 * 500 + s38 * 50 + s16634 * 10 + s16643 * 40) * 2));
        editor.putFloat("Uncompressed SellMo" + (40 + 11), 0.01f * ((s34 * 5000 + s36 * 1000 + s37 * 1000 + s39 * 50 + s16635 * 10 + s16644 * 40) * 2));
        editor.putFloat("Uncompressed SellMo" + (40 + 12), 0.01f * ((s35 * 5000 + s36 * 750 + s37 * 1250 + s39 * 50 + s16636 * 10 + s16642 * 40) * 2));

        editor.putFloat("Uncompressed SellMo" + (40 + 13), 0.01f * ((s36 * 1000 + s37 * 1250 + s39 * 50 + s16634 * 15 + s16640 * 10 + s16649 * 50) * 2));
        editor.putFloat("Uncompressed SellMo" + (40 + 14), 0.01f * ((s36 * 1500 + s37 * 750 + s40 * 50 + s16635 * 15 + s16637 * 10 + s16646 * 50) * 2));
        editor.putFloat("Uncompressed SellMo" + (40 + 15), 0.01f * ((s36 * 1250 + s37 * 1000 + s39 * 50 + s16633 * 15 + s16639 * 10 + s16647 * 50) * 2));
        editor.putFloat("Uncompressed SellMo" + (40 + 16), 0.01f * ((s36 * 1750 + s37 * 500 + s40 * 50 + s16636 * 15 + s16638 * 10 + s16648 * 50) * 2));

        editor.putFloat("Uncompressed SellMo" + (40 + 17), 0.01f * ((s38 * 100 + s39 * 200 + s40 * 50 + s16633 * 20 + s16639 * 20 + s16644 * 10 + s16652 * 22) * 2));
        editor.putFloat("Uncompressed SellMo" + (40 + 18), 0.01f * ((s38 * 50 + s39 * 150 + s40 * 150 + s16635 * 20 + s16637 * 20 + s16641 * 10 + s16651 * 22) * 2));
        editor.putFloat("Uncompressed SellMo" + (40 + 19), 0.01f * ((s38 * 200 + s39 * 100 + s40 * 50 + s16634 * 20 + s16640 * 20 + s16642 * 10 + s16650 * 22) * 2));
        editor.putFloat("Uncompressed SellMo" + (40 + 20), 0.01f * ((s38 * 50 + s39 * 100 + s40 * 200 + s16636 * 20 + s16638 * 20 + s16643 * 10 + s16653 * 22) * 2));

        editor.putFloat("Uncompressed BuyMo" + (40 + 1), 0.01f * ((b34 * 6000 + b35 * 6000 + b36 * 400 + b16633 * 65) * 2));
        editor.putFloat("Uncompressed BuyMo" + (40 + 2), 0.01f * ((b34 * 10000 + b35 * 2000 + b36 * 400 + b16636 * 65) * 2));
        editor.putFloat("Uncompressed BuyMo" + (40 + 3), 0.01f * ((b34 * 8000 + b35 * 8000 + b36 * 400 + b16635 * 65) * 2));
        editor.putFloat("Uncompressed BuyMo" + (40 + 4), 0.01f * ((b34 * 4000 + b35 * 6000 + b36 * 400 + b16634 * 65) * 2));

        editor.putFloat("Uncompressed BuyMo" + (40 + 5), 0.01f * ((b34 * 7500 + b35 * 10000 + b36 * 500 + b16640 * 40) * 2));
        editor.putFloat("Uncompressed BuyMo" + (40 + 6), 0.01f * ((b34 * 10000 + b35 * 7500 + b36 * 500 + b16639 * 40) * 2));
        editor.putFloat("Uncompressed BuyMo" + (40 + 7), 0.01f * ((b34 * 12500 + b35 * 5000 + b36 * 500 + b16637 * 40) * 2));
        editor.putFloat("Uncompressed BuyMo" + (40 + 8), 0.01f * ((b34 * 15000 + b35 * 2500 + b36 * 500 + b16638 * 40) * 2));

        editor.putFloat("Uncompressed BuyMo" + (40 + 9), 0.01f * ((b35 * 5000 + b36 * 1250 + b37 * 750 + b38 * 50 + b16633 * 10 + b16641 * 40) * 2));
        editor.putFloat("Uncompressed BuyMo" + (40 + 10), 0.01f * ((b34 * 5000 + b36 * 1500 + b37 * 500 + b38 * 50 + b16634 * 10 + b16643 * 40) * 2));
        editor.putFloat("Uncompressed BuyMo" + (40 + 11), 0.01f * ((b34 * 5000 + b36 * 1000 + b37 * 1000 + b39 * 50 + b16635 * 10 + b16644 * 40) * 2));
        editor.putFloat("Uncompressed BuyMo" + (40 + 12), 0.01f * ((b35 * 5000 + b36 * 750 + b37 * 1250 + b39 * 50 + b16636 * 10 + b16642 * 40) * 2));

        editor.putFloat("Uncompressed BuyMo" + (40 + 13), 0.01f * ((b36 * 1000 + b37 * 1250 + b39 * 50 + b16634 * 15 + b16640 * 10 + b16649 * 50) * 2));
        editor.putFloat("Uncompressed BuyMo" + (40 + 14), 0.01f * ((b36 * 1500 + b37 * 750 + b40 * 50 + b16635 * 15 + b16637 * 10 + b16646 * 50) * 2));
        editor.putFloat("Uncompressed BuyMo" + (40 + 15), 0.01f * ((b36 * 1250 + b37 * 1000 + b39 * 50 + b16633 * 15 + b16639 * 10 + b16647 * 50) * 2));
        editor.putFloat("Uncompressed BuyMo" + (40 + 16), 0.01f * ((b36 * 1750 + b37 * 500 + b40 * 50 + b16636 * 15 + b16638 * 10 + b16648 * 50) * 2));

        editor.putFloat("Uncompressed BuyMo" + (40 + 17), 0.01f * ((b38 * 100 + b39 * 200 + b40 * 50 + b16633 * 20 + b16639 * 20 + b16644 * 10 + b16652 * 22) * 2));
        editor.putFloat("Uncompressed BuyMo" + (40 + 18), 0.01f * ((b38 * 50 + b39 * 150 + b40 * 150 + b16635 * 20 + b16637 * 20 + b16641 * 10 + b16651 * 22) * 2));
        editor.putFloat("Uncompressed BuyMo" + (40 + 19), 0.01f * ((b38 * 200 + b39 * 100 + b40 * 50 + b16634 * 20 + b16640 * 20 + b16642 * 10 + b16650 * 22) * 2));
        editor.putFloat("Uncompressed BuyMo" + (40 + 20), 0.01f * ((b38 * 50 + b39 * 100 + b40 * 200 + b16636 * 20 + b16638 * 20 + b16643 * 10 + b16653 * 22) * 2));

        editor.putFloat("Compressed SellMo" + (40 + 1), 0.01f * ((s34 * 6000 + s35 * 6000 + s36 * 400 + s16633 * 65) * 2));
        editor.putFloat("Compressed SellMo" + (40 + 2), 0.01f * ((s34 * 10000 + s35 * 2000 + s36 * 400 + s16636 * 65) * 2));
        editor.putFloat("Compressed SellMo" + (40 + 3), 0.01f * ((s34 * 8000 + s35 * 8000 + s36 * 400 + s16635 * 65) * 2));
        editor.putFloat("Compressed SellMo" + (40 + 4), 0.01f * ((s34 * 4000 + s35 * 6000 + s36 * 400 + s16634 * 65) * 2));

        editor.putFloat("Compressed SellMo" + (40 + 5), 0.01f * ((s34 * 7500 + s35 * 10000 + s36 * 500 + s16640 * 40) * 2));
        editor.putFloat("Compressed SellMo" + (40 + 6), 0.01f * ((s34 * 10000 + s35 * 7500 + s36 * 500 + s16639 * 40) * 2));
        editor.putFloat("Compressed SellMo" + (40 + 7), 0.01f * ((s34 * 12500 + s35 * 5000 + s36 * 500 + s16637 * 40) * 2));
        editor.putFloat("Compressed SellMo" + (40 + 8), 0.01f * ((s34 * 15000 + s35 * 2500 + s36 * 500 + s16638 * 40) * 2));

        editor.putFloat("Compressed SellMo" + (40 + 9), 0.01f * ((s35 * 5000 + s36 * 1250 + s37 * 750 + s38 * 50 + s16633 * 10 + s16641 * 40) * 2));
        editor.putFloat("Compressed SellMo" + (40 + 10), 0.01f * ((s34 * 5000 + s36 * 1500 + s37 * 500 + s38 * 50 + s16634 * 10 + s16643 * 40) * 2));
        editor.putFloat("Compressed SellMo" + (40 + 11), 0.01f * ((s34 * 5000 + s36 * 1000 + s37 * 1000 + s39 * 50 + s16635 * 10 + s16644 * 40) * 2));
        editor.putFloat("Compressed SellMo" + (40 + 12), 0.01f * ((s35 * 5000 + s36 * 750 + s37 * 1250 + s39 * 50 + s16636 * 10 + s16642 * 40) * 2));

        editor.putFloat("Compressed SellMo" + (40 + 13), 0.01f * ((s36 * 1000 + s37 * 1250 + s39 * 50 + s16634 * 15 + s16640 * 10 + s16649 * 50) * 2));
        editor.putFloat("Compressed SellMo" + (40 + 14), 0.01f * ((s36 * 1500 + s37 * 750 + s40 * 50 + s16635 * 15 + s16637 * 10 + s16646 * 50) * 2));
        editor.putFloat("Compressed SellMo" + (40 + 15), 0.01f * ((s36 * 1250 + s37 * 1000 + s39 * 50 + s16633 * 15 + s16639 * 10 + s16647 * 50) * 2));
        editor.putFloat("Compressed SellMo" + (40 + 16), 0.01f * ((s36 * 1750 + s37 * 500 + s40 * 50 + s16636 * 15 + s16638 * 10 + s16648 * 50) * 2));

        editor.putFloat("Compressed SellMo" + (40 + 17), 0.01f * ((s38 * 100 + s39 * 200 + s40 * 50 + s16633 * 20 + s16639 * 20 + s16644 * 10 + s16652 * 22) * 2));
        editor.putFloat("Compressed SellMo" + (40 + 18), 0.01f * ((s38 * 50 + s39 * 150 + s40 * 150 + s16635 * 20 + s16637 * 20 + s16641 * 10 + s16651 * 22) * 2));
        editor.putFloat("Compressed SellMo" + (40 + 19), 0.01f * ((s38 * 200 + s39 * 100 + s40 * 50 + s16634 * 20 + s16640 * 20 + s16642 * 10 + s16650 * 22) * 2));
        editor.putFloat("Compressed SellMo" + (40 + 20), 0.01f * ((s38 * 50 + s39 * 100 + s40 * 200 + s16636 * 20 + s16638 * 20 + s16643 * 10 + s16653 * 22) * 2));

        editor.putFloat("Compressed BuyMo" + (40 + 1), 0.01f * ((b34 * 6000 + b35 * 6000 + b36 * 400 + b16633 * 65) * 2));
        editor.putFloat("Compressed BuyMo" + (40 + 2), 0.01f * ((b34 * 10000 + b35 * 2000 + b36 * 400 + b16636 * 65) * 2));
        editor.putFloat("Compressed BuyMo" + (40 + 3), 0.01f * ((b34 * 8000 + b35 * 8000 + b36 * 400 + b16635 * 65) * 2));
        editor.putFloat("Compressed BuyMo" + (40 + 4), 0.01f * ((b34 * 4000 + b35 * 6000 + b36 * 400 + b16634 * 65) * 2));

        editor.putFloat("Compressed BuyMo" + (40 + 5), 0.01f * ((b34 * 7500 + b35 * 10000 + b36 * 500 + b16640 * 40) * 2));
        editor.putFloat("Compressed BuyMo" + (40 + 6), 0.01f * ((b34 * 10000 + b35 * 7500 + b36 * 500 + b16639 * 40) * 2));
        editor.putFloat("Compressed BuyMo" + (40 + 7), 0.01f * ((b34 * 12500 + b35 * 5000 + b36 * 500 + b16637 * 40) * 2));
        editor.putFloat("Compressed BuyMo" + (40 + 8), 0.01f * ((b34 * 15000 + b35 * 2500 + b36 * 500 + b16638 * 40) * 2));

        editor.putFloat("Compressed BuyMo" + (40 + 9), 0.01f * ((b35 * 5000 + b36 * 1250 + b37 * 750 + b38 * 50 + b16633 * 10 + b16641 * 40) * 2));
        editor.putFloat("Compressed BuyMo" + (40 + 10), 0.01f * ((b34 * 5000 + b36 * 1500 + b37 * 500 + b38 * 50 + b16634 * 10 + b16643 * 40) * 2));
        editor.putFloat("Compressed BuyMo" + (40 + 11), 0.01f * ((b34 * 5000 + b36 * 1000 + b37 * 1000 + b39 * 50 + b16635 * 10 + b16644 * 40) * 2));
        editor.putFloat("Compressed BuyMo" + (40 + 12), 0.01f * ((b35 * 5000 + b36 * 750 + b37 * 1250 + b39 * 50 + b16636 * 10 + b16642 * 40) * 2));

        editor.putFloat("Compressed BuyMo" + (40 + 13), 0.01f * ((b36 * 1000 + b37 * 1250 + b39 * 50 + b16634 * 15 + b16640 * 10 + b16649 * 50) * 2));
        editor.putFloat("Compressed BuyMo" + (40 + 14), 0.01f * ((b36 * 1500 + b37 * 750 + b40 * 50 + b16635 * 15 + b16637 * 10 + b16646 * 50) * 2));
        editor.putFloat("Compressed BuyMo" + (40 + 15), 0.01f * ((b36 * 1250 + b37 * 1000 + b39 * 50 + b16633 * 15 + b16639 * 10 + b16647 * 50) * 2));
        editor.putFloat("Compressed BuyMo" + (40 + 16), 0.01f * ((b36 * 1750 + b37 * 500 + b40 * 50 + b16636 * 15 + b16638 * 10 + b16648 * 50) * 2));

        editor.putFloat("Compressed BuyMo" + (40 + 17), 0.01f * ((b38 * 100 + b39 * 200 + b40 * 50 + b16633 * 20 + b16639 * 20 + b16644 * 10 + b16652 * 22) * 2));
        editor.putFloat("Compressed BuyMo" + (40 + 18), 0.01f * ((b38 * 50 + b39 * 150 + b40 * 150 + b16635 * 20 + b16637 * 20 + b16641 * 10 + b16651 * 22) * 2));
        editor.putFloat("Compressed BuyMo" + (40 + 19), 0.01f * ((b38 * 200 + b39 * 100 + b40 * 50 + b16634 * 20 + b16640 * 20 + b16642 * 10 + b16650 * 22) * 2));
        editor.putFloat("Compressed BuyMo" + (40 + 20), 0.01f * ((b38 * 50 + b39 * 100 + b40 * 200 + b16636 * 20 + b16638 * 20 + b16643 * 10 + b16653 * 22) * 2));

        editor.commit();

        Calendar c = Calendar.getInstance();
        timeMS = c.getTimeInMillis();
        updateTV();
        mAdapter.notifyItemChanged(0);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();


        for (int i = 1; i != 61; i++) {
            if (key.equals("CustomSMo" + String.valueOf(i)) || key.equals("Perc") || key.equals("MPC") || key.equals("Time") || key.equals("Min")) {
                {
                    if (sharedPref.getString(key, "0.00").equals("")) {
                        editor.remove(key);
                        editor.commit();
                    }
                }
            }
        }

        if (key.equals("SystemNames")) {
            refresh();
        }
    }

    void resort() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(MoonPrices.this);
        int i = 1;
        for (int c = 1; !(c == 61); c++) {
            switch (c) {
                case 1:
                    if (sharedPref.getBoolean("Bitumens", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 2:
                    if (sharedPref.getBoolean("Coesite", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 3:
                    if (sharedPref.getBoolean("Sylvite", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 4:
                    if (sharedPref.getBoolean("Zeolites", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 5:
                    if (sharedPref.getBoolean("Cobaltite", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 6:
                    if (sharedPref.getBoolean("Euxenite", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 7:
                    if (sharedPref.getBoolean("Scheelite", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 8:
                    if (sharedPref.getBoolean("Titanite", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 9:
                    if (sharedPref.getBoolean("Chromite", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 10:
                    if (sharedPref.getBoolean("Otavite", false)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 11:
                    if (sharedPref.getBoolean("Sperrylite", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 12:
                    if (sharedPref.getBoolean("Vanadinite", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 13:
                    if (sharedPref.getBoolean("Carnotite", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 14:
                    if (sharedPref.getBoolean("Cinnabar", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 15:
                    if (sharedPref.getBoolean("Pollucite", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 16:
                    if (sharedPref.getBoolean("Zircon", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 17:
                    if (sharedPref.getBoolean("Loparite", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 18:
                    if (sharedPref.getBoolean("Monazite", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 19:
                    if (sharedPref.getBoolean("Xenotime", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 20:
                    if (sharedPref.getBoolean("Ytterbite", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 1 + 20:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Bitumens", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 2 + 20:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Coesite", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 3 + 20:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Sylvite", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 4 + 20:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Zeolites", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 5 + 20:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Cobaltite", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 6 + 20:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Euxenite", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 7 + 20:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Scheelite", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 8 + 20:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Titanite", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 9 + 20:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Chromite", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 10 + 20:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Otavite", false)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 11 + 20:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Sperrylite", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 12 + 20:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Vanadinite", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 13 + 20:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Carnotite", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 14 + 20:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Cinnabar", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 15 + 20:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Pollucite", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 16 + 20:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Zircon", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 17 + 20:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Loparite", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 18 + 20:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Monazite", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 19 + 20:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Xenotime", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 20 + 20:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Ytterbite", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 1 + 40:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Bitumens", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 2 + 40:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Coesite", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 3 + 40:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Sylvite", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 4 + 40:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Zeolites", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 5 + 40:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Cobaltite", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 6 + 40:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Euxenite", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 7 + 40:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Scheelite", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 8 + 40:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Titanite", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 9 + 40:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Chromite", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 10 + 40:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Otavite", false)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 11 + 40:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Sperrylite", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 12 + 40:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Vanadinite", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 13 + 40:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Carnotite", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 14 + 40:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Cinnabar", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 15 + 40:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Pollucite", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 16 + 40:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Zircon", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 17 + 40:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Loparite", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 18 + 40:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Monazite", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 19 + 40:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Xenotime", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
                case 20 + 40:
                    if (sharedPref.getBoolean("VariantsMoon", false)) {
                        if (sharedPref.getBoolean("Ytterbite", true)) {
                            Sort[i] = c;
                            i++;
                        }
                    }
                    break;
            }
        }

        switch (sharedPref.getString("SortMo", "Default")) {
            case "Name":
                String NamesMo[] = getResources().getStringArray(R.array.NamesMo);
                boolean tT = true;
                while (tT) {
                    tT = false;
                    for (int k = 1; k < i - 1; k++) {
                        if (NamesMo[Sort[k]].compareTo(NamesMo[Sort[k + 1]]) < 1) {
                            int r = Sort[k];
                            Sort[k] = Sort[k + 1];
                            Sort[k + 1] = r;
                            tT = true;
                        }
                    }
                }
                int[] reverseArray1 = new int[i];
                for (int j = 1; j < i; j++) {
                    reverseArray1[j] = Sort[i - j];
                }
                System.arraycopy(reverseArray1, 1, Sort, 1, i - 1);
                break;
            case "Price":
                boolean t = true;
                while (t) {
                    t = false;
                    for (int k = 1; k < i - 1; k++) {
                        if (sharedPref.getFloat(sharedPref.getString("BS", "Compressed Sell") + "Mo" + String.valueOf(Sort[k]), (float) 0.00) > sharedPref.getFloat(sharedPref.getString("BS", "Compressed Sell") + "Mo" + String.valueOf(Sort[k + 1]), (float) 0.00)) {
                            int r = Sort[k];
                            Sort[k] = Sort[k + 1];
                            Sort[k + 1] = r;
                            t = true;
                        }
                    }
                }
                int[] reverseArray2 = new int[i];
                for (int j = 1; j < i; j++) {
                    reverseArray2[j] = Sort[i - j];
                }
                System.arraycopy(reverseArray2, 1, Sort, 1, i - 1);
                break;
            default:
                break;
        }

        mAdapter.notifyDataSetChanged();
    }

    void writeStringstofloats() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();
        for (int count = 1; count != 61; count++) {
            editor.putFloat("CustomMo" + count, Float.parseFloat(sharedPref.getString("CustomSMo" + count, "0.00")));
        }
        editor.commit();
    }

    public class GreenAdapter extends RecyclerView.Adapter<GreenAdapter.NumberViewHolder> {

        final SharedPreferences sharedPref;
        private final String TAG = MoonPrices.GreenAdapter.class.getSimpleName();

        GreenAdapter() {
            sharedPref = PreferenceManager.getDefaultSharedPreferences(MoonPrices.this);
        }

        @Override
        public MoonPrices.GreenAdapter.NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            Context context = viewGroup.getContext();
            int layoutIdForListItem;
            if (viewType == 1) {
                layoutIdForListItem = R.layout.first;
            } else {
                layoutIdForListItem = R.layout.number_list_item;
            }
            LayoutInflater inflater = LayoutInflater.from(context);
            boolean shouldAttachToParentImmediately = false;

            View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
            MoonPrices.GreenAdapter.NumberViewHolder viewHolder = new MoonPrices.GreenAdapter.NumberViewHolder(view);
            return viewHolder;
        }

        @Override
        public int getItemViewType(int position) {
            int First;
            switch (position) {
                case 0:
                    First = 1;
                    break;
                default:
                    First = 0;
            }
            return First;
        }

        @Override
        public void onBindViewHolder(MoonPrices.GreenAdapter.NumberViewHolder holder, int position) {
            holder.bind(position);
        }


        @Override
        public int getItemCount() {
            int i = 0;
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(MoonPrices.this);

            if (sharedPref.getBoolean("Bitumens", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Coesite", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Sylvite", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Zeolites", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Cobaltite", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Euxenite", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Scheelite", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Titanite", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Chromite", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Otavite", false)) {
                i++;
            }
            if (sharedPref.getBoolean("Sperrylite", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Vanadinite", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Carnotite", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Cinnabar", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Pollucite", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Zircon", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Loparite", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Monazite", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Xenotime", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Ytterbite", true)) {
                i++;
            }
            if (sharedPref.getBoolean("VariantsMoon", false)) {
                i *= 3;
            }

            i++;

            return i;
        }

        class NumberViewHolder extends RecyclerView.ViewHolder {


            final TextView listItemNumberView;
            final TextView PPI;
            final TextView PPV;
            final TextView PPH;
            final String[] Name = getResources().getStringArray(R.array.NamesMo);

            NumberViewHolder(View itemView) {
                super(itemView);

                listItemNumberView = itemView.findViewById(R.id.TV1);
                PPI = itemView.findViewById(R.id.PPI);
                PPV = itemView.findViewById(R.id.PPV);
                PPH = itemView.findViewById(R.id.PPH);
            }

            void bind(int listIndex) {
                switch (listIndex) {
                    case 0:
                        listItemNumberView.setText(Status);
                        if (setStatusError) {
                            listItemNumberView.append(" Can't connect");
                        }
                        TextView listItemNumberView2 = itemView.findViewById(R.id.TV2);
                        listItemNumberView2.setText(sharedPref.getString("SystemNames", "Jita") + " ");

                        String BSt = "Custom";
                        switch (sharedPref.getString("BS", "Compressed Sell")) {
                            case "Custom":
                                BSt = "Custom";
                                break;
                            case "Compressed Buy":
                                BSt = "Buy";
                                break;
                            case "Compressed Sell":
                                BSt = "Sell";
                                break;
                            case "Uncompressed Sell":
                                BSt = "Sell";
                                break;
                            case "Uncompressed Buy":
                                BSt = "Buy";
                                break;
                        }

                        listItemNumberView2.append(BSt + " prices");
                        break;
                    default:
                        listItemNumberView.setText(Name[Sort[listIndex]]);
                        PPI.setText(String.format("%.2f", Float.parseFloat(sharedPref.getString("Perc", "100")) / 100 * sharedPref.getFloat(sharedPref.getString("BS", "Compressed Sell") + "Mo" + String.valueOf(Sort[listIndex]), (float) 0.00)));
                        PPV.setText(String.format("%.2f", Float.parseFloat(sharedPref.getString("Perc", "100")) / 100 * sharedPref.getFloat(sharedPref.getString("BS", "Compressed Sell") + "Mo" + String.valueOf(Sort[listIndex]), (float) 0.00) / 10f));
                        PPH.setText(String.format("%.2f", Float.parseFloat(sharedPref.getString("Perc", "100")) / 100 * 3600 * Float.parseFloat(sharedPref.getString("MPC", "1500.00")) * Float.parseFloat(sharedPref.getString("Min", "1")) * sharedPref.getFloat(sharedPref.getString("BS", "Compressed Sell") + "Mo" + String.valueOf(Sort[listIndex]), (float) 0.00) / 10f / 1000000 / Float.parseFloat(sharedPref.getString("Time", "120"))) + "M");
                }
            }
        }
    }
}