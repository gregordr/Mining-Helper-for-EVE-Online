package org.devfleet.android.minerhelper.priceActivities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.devfleet.android.minerhelper.R;
import org.devfleet.android.minerhelper.selectionSettings.IceSelection;
import org.devfleet.android.minerhelper.systemSettings.SystemCice;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class IcePrices extends BasePrices {

    private final int[] Sort = new int[13];
    private GreenAdapter mAdapter;
    private RecyclerView mNumbersList;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.buttonsice, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        base = "https://market.fuzzwork.co.uk/aggregates/?types=16262,16265,16264,16263,17978,17976,17975,17977,16266,16267,16268,16269,28434,28444,28433,28438,28436,28441,28443,28442,28439,28435,28437,28440";

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mNumbersList = findViewById(R.id.RecV);
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
        Intent launchSystemC = new Intent(this, SystemCice.class);
        startActivity(launchSystemC);
    }

    void launchSelectionSettings() {
        Intent launchOreC = new Intent(this, IceSelection.class);
        startActivity(launchOreC);
    }

    void sortBy() {
    }

    public void getInfo(String s) {
        JSONObject obj = null;
        try {

            obj = new JSONObject(s);


        } catch (Throwable ignored) {

        }

        String[] nums = getResources().getStringArray(R.array .numsI);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();

        for (int i = 1; i!=13; i++) {
            try {
                editor.putFloat("Uncompressed SellI" + i, Float.parseFloat(String.valueOf(obj.getJSONObject(nums[i-1]).getJSONObject("sell").get("min"))));
            } catch (JSONException ignored) {
            }
        }
        for (int i = 1; i!=13; i++) {
            try {
                editor.putFloat("Uncompressed BuyI" + i, Float.parseFloat(String.valueOf(obj.getJSONObject(nums[i-1]).getJSONObject("buy").get("max"))));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        for (int i = 1; i!=13; i++) {
            try {
                editor.putFloat("Compressed SellI" + i, Float.parseFloat(String.valueOf(obj.getJSONObject(nums[i+12-1]).getJSONObject("sell").get("min"))));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        for (int i = 1; i!=13; i++) {
            try {
                editor.putFloat("Compressed BuyI" + i, Float.parseFloat(String.valueOf(obj.getJSONObject(nums[i+12-1]).getJSONObject("buy").get("max"))));
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


        for (int i = 1; i != 13; i++) {
            if (key.equals("CustomSI" + String.valueOf(i)) || key.equals("Perc") || key.equals("MPC") || key.equals("Time") || key.equals("Min")) {
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
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(IcePrices.this);
        int i = 1;
        for (int c = 1; !(c == 13); c++) {
            switch (c) {
                case 1:
                    if (sharedPref.getBoolean("Clear_Icicle", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 2:
                    if (sharedPref.getBoolean("White_Glaze", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 3:
                    if (sharedPref.getBoolean("Blue_Ice", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 4:
                    if (sharedPref.getBoolean("Glacial_Mass", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 5:
                    if (sharedPref.getBoolean("Enriched_Clear_Icicle", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 6:
                    if (sharedPref.getBoolean("Pristine_White_Glaze", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 7:
                    if (sharedPref.getBoolean("Thick_Blue_Ice", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 8:
                    if (sharedPref.getBoolean("Smooth_Glacial_Mass", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 9:
                    if (sharedPref.getBoolean("Glare_Crust", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 10:
                    if (sharedPref.getBoolean("Dark_Glitter", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 11:
                    if (sharedPref.getBoolean("Gelidus", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 12:
                    if (sharedPref.getBoolean("Krystallos", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
            }
        }

        boolean t = true;
        while (t) {
            t = false;
            for (int k = 1; k < i - 1; k++) {
                if (sharedPref.getFloat(sharedPref.getString("BS", "Compressed Sell") + "I" + String.valueOf(Sort[k]), (float) 0.00) > sharedPref.getFloat(sharedPref.getString("BS", "Compressed Sell") + "I" + String.valueOf(Sort[k + 1]), (float) 0.00)) {
                    int r = Sort[k];
                    Sort[k] = Sort[k + 1];
                    Sort[k + 1] = r;
                    t = true;
                }
            }
        }
        int[] SortR = new int[i];
        for (int KKK = 1; KKK < i; KKK++) {
            SortR[KKK] = Sort[i - KKK];
        }
        System.arraycopy(SortR, 1, Sort, 1, i - 1);

        mAdapter.notifyDataSetChanged();
    }

    void writeStringstofloats() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();
        for (int count = 1; count != 13; count++) {
            editor.putFloat("CustomI" + count, Float.parseFloat(sharedPref.getString("CustomSI" + count, "0.00")));
        }
        editor.commit();
    }

    public class GreenAdapter extends RecyclerView.Adapter<GreenAdapter.NumberViewHolder> {

        private final String TAG = GreenAdapter.class.getSimpleName();
        final SharedPreferences sharedPref;

        GreenAdapter() {
            sharedPref = PreferenceManager.getDefaultSharedPreferences(IcePrices.this);
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
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(IcePrices.this);
            if (sharedPref.getBoolean("Clear_Icicle", true)) {
                i++;
            }
            if (sharedPref.getBoolean("White_Glaze", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Blue_Ice", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Glacial_Mass", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Enriched_Clear_Icicle", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Pristine_White_Glaze", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Thick_Blue_Ice", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Smooth_Glacial_Mass", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Glare_Crust", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Dark_Glitter", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Gelidus", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Krystallos", true)) {
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
            final String[] Name = getResources().getStringArray(R.array.NamesI);

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
                        listItemNumberView2.append(sharedPref.getString("BS", "Compressed Sell") + " prices");
                        break;
                    default:
                        listItemNumberView.setText(Name[Sort[listIndex]]);
                        PPI.setText(String.format("%.2f", Float.parseFloat(sharedPref.getString("Perc", "100"))/100*sharedPref.getFloat(sharedPref.getString("BS", "Compressed Sell")+"I"+String.valueOf(Sort[listIndex]), (float) 0.00)));
                        PPV.setText(String.format("%.2f", Float.parseFloat(sharedPref.getString("Perc", "100"))/100*sharedPref.getFloat(sharedPref.getString("BS", "Compressed Sell")+"I"+String.valueOf(Sort[listIndex]), (float) 0.00)/Float.parseFloat("1000")));
                        PPH.setText(String.format("%.2f", Float.parseFloat(sharedPref.getString("Perc", "100"))/100*3600*Float.parseFloat(sharedPref.getString("MinIce", "1"))*sharedPref.getFloat(sharedPref.getString("BS", "Compressed Sell")+"I"+String.valueOf(Sort[listIndex]), (float) 0.00)/1000000/Float.parseFloat(sharedPref.getString("TimeIce", "300")))+"M");
                }
            }
        }
    }
}