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
import org.devfleet.android.minerhelper.selectionSettings.MineralSelection;
import org.devfleet.android.minerhelper.systemSettings.MineralSystemSelection;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class MineralPrices extends BasePrices {

    private final int[] Sort = new int[9];
    private GreenAdapter mAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.buttons_minerals, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        base = "https://market.fuzzwork.co.uk/aggregates/?types=34,35,36,37,38,39,40,11399";

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        RecyclerView mNumbersList = findViewById(R.id.RecV);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mNumbersList.setLayoutManager(layoutManager);
        mNumbersList.setHasFixedSize(true);
        mAdapter = new GreenAdapter();
        mNumbersList.setAdapter(mAdapter);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPref.registerOnSharedPreferenceChangeListener(this);
    }

    void launchSystemSettings() {
        Intent launchSystemC = new Intent(this, MineralSystemSelection.class);
        startActivity(launchSystemC);
    }

    void launchSelectionSettings() {
        Intent launchOreC = new Intent(this, MineralSelection.class);
        startActivity(launchOreC);
    }

    void sortBy() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MineralPrices.this);
        builder.setTitle(R.string.dialog_message);
        builder.setItems(R.array.sortOptionsMi, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(MineralPrices.this);
                SharedPreferences.Editor editor = sharedPref.edit();
                switch (which) {
                    case 0:
                        editor.putString("SortMi", "Default");
                        break;
                    case 1:
                        editor.putString("SortMi", "Name");
                        break;
                    case 2:
                        editor.putString("SortMi", "Price");
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

        String[] nums = getResources().getStringArray(R.array.numsMi);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();

        for (int i = 1; i <= nums.length; i++) {
            try {
                editor.putFloat("Uncompressed SellMi" + i, Float.parseFloat(String.valueOf(obj.getJSONObject(nums[i - 1]).getJSONObject("sell").get("min"))));
            } catch (JSONException ignored) {
            }
        }
        for (int i = 1; i <= nums.length; i++) {
            try {
                editor.putFloat("Uncompressed BuyMi" + i, Float.parseFloat(String.valueOf(obj.getJSONObject(nums[i - 1]).getJSONObject("buy").get("max"))));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        for (int i = 1; i <= nums.length; i++) {
            try {
                editor.putFloat("Compressed SellMi" + i, Float.parseFloat(String.valueOf(obj.getJSONObject(nums[i - 1]).getJSONObject("sell").get("min"))));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        for (int i = 1; i <= nums.length; i++) {
            try {
                editor.putFloat("Compressed BuyMi" + i, Float.parseFloat(String.valueOf(obj.getJSONObject(nums[i - 1]).getJSONObject("buy").get("max"))));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

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


        for (int i = 1; i != 49; i++) {
            if (key.equals("CustomSMi" + String.valueOf(i)) || key.equals("Perc") || key.equals("MPC") || key.equals("Time") || key.equals("Min")) {
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
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(MineralPrices.this);
        int i = 1;
        for (int c = 1; !(c == 9); c++) {
            switch (c) {
                case 1:
                    if (sharedPref.getBoolean("Tritanium", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 2:
                    if (sharedPref.getBoolean("Pyerite", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 3:
                    if (sharedPref.getBoolean("Mexallon", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 4:
                    if (sharedPref.getBoolean("Isogen", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 5:
                    if (sharedPref.getBoolean("Nocxium", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 6:
                    if (sharedPref.getBoolean("Zydrine", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 7:
                    if (sharedPref.getBoolean("Megacyte", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 8:
                    if (sharedPref.getBoolean("Morphite", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
            }
        }

        switch (sharedPref.getString("SortMi", "Default")) {
            case "Name":
                String NamesMi[] = getResources().getStringArray(R.array.NamesMi);
                boolean tT = true;
                while (tT) {
                    tT = false;
                    for (int k = 1; k < i - 1; k++) {
                        if (NamesMi[Sort[k]].compareTo(NamesMi[Sort[k + 1]]) < 1) {
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
                        if (sharedPref.getFloat(sharedPref.getString("BS", "Compressed Sell") + "Mi" + String.valueOf(Sort[k]), (float) 0.00) > sharedPref.getFloat(sharedPref.getString("BS", "Compressed Sell") + "Mi" + String.valueOf(Sort[k + 1]), (float) 0.00)) {
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
        for (int count = 1; count != 9; count++) {
            editor.putFloat("CustomMi" + count, Float.parseFloat(sharedPref.getString("CustomSMi" + count, "0.00")));
        }
        editor.commit();
    }

    public class GreenAdapter extends RecyclerView.Adapter<GreenAdapter.NumberViewHolder> {

        final SharedPreferences sharedPref;
        private final String TAG = GreenAdapter.class.getSimpleName();

        GreenAdapter() {
            sharedPref = PreferenceManager.getDefaultSharedPreferences(MineralPrices.this);
        }

        @Override
        public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
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
            NumberViewHolder viewHolder = new NumberViewHolder(view);
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
        public void onBindViewHolder(NumberViewHolder holder, int position) {

            holder.bind(position);
        }


        @Override
        public int getItemCount() {
            int i = 0;
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(MineralPrices.this);
            if (sharedPref.getBoolean("Tritanium", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Pyerite", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Mexallon", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Isogen", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Nocxium", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Zydrine", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Megacyte", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Morphite", true)) {
                i++;
            }
            i++;

            return i;
        }

        class NumberViewHolder extends RecyclerView.ViewHolder {


            final TextView listItemNumberView;
            final TextView PPI;
            final TextView PPV;
            final TextView PPH;
            final String[] Name = getResources().getStringArray(R.array.NamesMi);

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
                        PPI.setText(String.format("%.2f", Float.parseFloat(sharedPref.getString("Perc", "100")) / 100 * sharedPref.getFloat(sharedPref.getString("BS", "Compressed Sell") + "Mi" + String.valueOf(Sort[listIndex]), (float) 0.00)));
                        PPV.setText(String.format("%.2f", Float.parseFloat(sharedPref.getString("Perc", "100")) / 100 * sharedPref.getFloat(sharedPref.getString("BS", "Compressed Sell") + "Mi" + String.valueOf(Sort[listIndex]), (float) 0.00) * 100));
                        PPH.setText("-------");
                }
            }
        }
    }
}