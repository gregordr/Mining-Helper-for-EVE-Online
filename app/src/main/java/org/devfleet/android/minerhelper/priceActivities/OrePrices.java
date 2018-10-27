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
import org.devfleet.android.minerhelper.selectionSettings.OreSelection;
import org.devfleet.android.minerhelper.systemSettings.OreSystemSelection;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class OrePrices extends BasePrices {

    private final int[] Sort = new int[49];
    private GreenAdapter mAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.buttons_ore, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        base = "https://market.fuzzwork.co.uk/aggregates/?types=22,1223,1225,1232,1229,21,1231,1226,20,11396,1227,18,1224,1228,19,1230,17425,17428,17432,17436,17865,17440,17444,17448,17452,17869,17867,17455,17459,17463,17466,17470,17426,17429,17433,17437,17866,17441,17445,17449,17453,17870,17868,17456,17460,17464,17467,17471,28367,28388,28391,28394,28397,28401,28403,28406,28410,28413,28416,28422,28424,28429,28420,28432,28385,28389,28392,28395,28398,28400,28404,28407,28409,28412,28415,28421,28425,28427,28418,28430,28387,28390,28393,28396,28399,28402,28405,28408,28411,28414,28417,28423,28426,28428,28419,28431";

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
        Intent launchSystemC = new Intent(this, OreSystemSelection.class);
        startActivity(launchSystemC);
    }

    void launchSelectionSettings() {
        Intent launchOreC = new Intent(this, OreSelection.class);
        startActivity(launchOreC);
    }

    void sortBy() {
        AlertDialog.Builder builder = new AlertDialog.Builder(OrePrices.this);
        builder.setTitle(R.string.dialog_message);
        builder.setItems(R.array.sortOptions, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(OrePrices.this);
                SharedPreferences.Editor editor = sharedPref.edit();
                switch (which) {
                    case 0:
                        editor.putString("Sort","PPV");
                        break;
                    case 1:
                        editor.putString("Sort","PPU");
                        break;
                    case 2:
                        editor.putString("Sort","PPN");
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

        String[] nums = getResources().getStringArray(R.array.nums);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();

        for (int i = 1; i <= nums.length / 2; i++) {
            try {
                editor.putFloat("Uncompressed Sell" + i, Float.parseFloat(String.valueOf(obj.getJSONObject(nums[i-1]).getJSONObject("sell").get("min"))));
            } catch (JSONException ignored) {
            }
        }
        for (int i = 1; i <= nums.length / 2; i++) {
            try {
                editor.putFloat("Uncompressed Buy" + i, Float.parseFloat(String.valueOf(obj.getJSONObject(nums[i-1]).getJSONObject("buy").get("max"))));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        for (int i = 1; i <= nums.length / 2; i++) {
            try {
                editor.putFloat("Compressed Sell" + i, Float.parseFloat(String.valueOf(obj.getJSONObject(nums[i+48-1]).getJSONObject("sell").get("min")))/100);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        for (int i = 1; i <= nums.length / 2; i++) {
            try {
                editor.putFloat("Compressed Buy" + i, Float.parseFloat(String.valueOf(obj.getJSONObject(nums[i+48-1]).getJSONObject("buy").get("max")))/100);
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


        for (int i=1;i!=49;i++) {
            if (key.equals("CustomS" + String.valueOf(i)) || key.equals("Perc") || key.equals("MPC") || key.equals("Time") || key.equals("Min")) {
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
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(OrePrices.this);
        int i=1;
        for(int c=1; !(c==49) ;c++) {
            switch (c) {
                case 1: if(sharedPref.getBoolean("Arkonor", true)) {Sort[i]=c; i++;} break;
                case 2: if(sharedPref.getBoolean("Bistot", true)) {Sort[i]=c; i++;} break;
                case 3: if(sharedPref.getBoolean("Crokite", true)) {Sort[i]=c; i++;} break;
                case 4: if(sharedPref.getBoolean("Dark_Ochre", true)) {Sort[i]=c; i++;} break;
                case 5: if(sharedPref.getBoolean("Gneiss", true)) {Sort[i]=c; i++;} break;
                case 6: if(sharedPref.getBoolean("Hedbergite", true)) {Sort[i]=c; i++;} break;
                case 7: if(sharedPref.getBoolean("Hemorphite", true)) {Sort[i]=c; i++;} break;
                case 8: if(sharedPref.getBoolean("Jaspet", true)) {Sort[i]=c; i++;} break;
                case 9: if(sharedPref.getBoolean("Kernite", true)) {Sort[i]=c; i++;} break;
                case 10: if(sharedPref.getBoolean("Mercoxit", false)) {Sort[i]=c; i++;} break;
                case 11: if(sharedPref.getBoolean("Omber", true)) {Sort[i]=c; i++;} break;
                case 12: if(sharedPref.getBoolean("Plagioclase", true)) {Sort[i]=c; i++;} break;
                case 13: if(sharedPref.getBoolean("Pyroxeres", true)) {Sort[i]=c; i++;} break;
                case 14: if(sharedPref.getBoolean("Scordite", true)) {Sort[i]=c; i++;} break;
                case 15: if(sharedPref.getBoolean("Spodumain", true)) {Sort[i]=c; i++;} break;
                case 16: if(sharedPref.getBoolean("Veldspar", true)) {Sort[i]=c; i++;} break;
                case 1+16: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Arkonor", true)) {Sort[i]=c; i++;}} break;
                case 2+16: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Bistot", true)) {Sort[i]=c; i++;}} break;
                case 3+16: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Crokite", true)) {Sort[i]=c; i++;}} break;
                case 4+16: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Dark_Ochre", true)) {Sort[i]=c; i++;}} break;
                case 5+16: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Gneiss", true)) {Sort[i]=c; i++;}} break;
                case 6+16: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Hedbergite", true)) {Sort[i]=c; i++;}} break;
                case 7+16: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Hemorphite", true)) {Sort[i]=c; i++;}} break;
                case 8+16: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Jaspet", true)) {Sort[i]=c; i++;}} break;
                case 9+16: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Kernite", true)) {Sort[i]=c; i++;}} break;
                case 10+16: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Mercoxit", false)) {Sort[i]=c; i++;}} break;
                case 11+16: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Omber", true)) {Sort[i]=c; i++;}} break;
                case 12+16: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Plagioclase", true)) {Sort[i]=c; i++;}} break;
                case 13+16: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Pyroxeres", true)) {Sort[i]=c; i++;}} break;
                case 14+16: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Scordite", true)) {Sort[i]=c; i++;}} break;
                case 15+16: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Spodumain", true)) {Sort[i]=c; i++;}} break;
                case 16+16: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Veldspar", true)) {Sort[i]=c; i++;}} break;
                case 1+32: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Arkonor", true)) {Sort[i]=c; i++;}} break;
                case 2+32: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Bistot", true)) {Sort[i]=c; i++;}} break;
                case 3+32: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Crokite", true)) {Sort[i]=c; i++;}} break;
                case 4+32: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Dark_Ochre", true)) {Sort[i]=c; i++;}} break;
                case 5+32: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Gneiss", true)) {Sort[i]=c; i++;}} break;
                case 6+32: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Hedbergite", true)) {Sort[i]=c; i++;}} break;
                case 7+32: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Hemorphite", true)) {Sort[i]=c; i++;}} break;
                case 8+32: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Jaspet", true)) {Sort[i]=c; i++;}} break;
                case 9+32: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Kernite", true)) {Sort[i]=c; i++;}} break;
                case 10+32: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Mercoxit", false)) {Sort[i]=c; i++;}} break;
                case 11+32: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Omber", true)) {Sort[i]=c; i++;}} break;
                case 12+32: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Plagioclase", true)) {Sort[i]=c; i++;}} break;
                case 13+32: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Pyroxeres", true)) {Sort[i]=c; i++;}} break;
                case 14+32: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Scordite", true)) {Sort[i]=c; i++;}} break;
                case 15+32: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Spodumain", true)) {Sort[i]=c; i++;}} break;
                case 16+32: if(sharedPref.getBoolean("Variants", false)) { if(sharedPref.getBoolean("Veldspar", true)) {Sort[i]=c; i++;}} break;
            }
        }

        switch (sharedPref.getString("Sort","PPV")) {
            case "PPV":
                String Vol[]=getResources().getStringArray(R.array.Vol);
                boolean tT=true;
                while (tT) {
                    tT = false;
                    for (int k = 1; k < i - 1; k++) {
                        if (sharedPref.getFloat(sharedPref.getString("BS", "Compressed Sell") + String.valueOf(Sort[k]), (float) 0.00)/Float.parseFloat(Vol[Sort[k]]) > sharedPref.getFloat(sharedPref.getString("BS", "Compressed Sell") + String.valueOf(Sort[k + 1]), (float) 0.00)/Float.parseFloat(Vol[Sort[k+1]])) {
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
            case "PPU":
                boolean t=true;
                while (t) {
                    t = false;
                    for (int k = 1; k < i - 1; k++) {
                        if (sharedPref.getFloat(sharedPref.getString("BS", "Compressed Sell") + String.valueOf(Sort[k]), (float) 0.00) > sharedPref.getFloat(sharedPref.getString("BS", "Compressed Sell") + String.valueOf(Sort[k + 1]), (float) 0.00)) {
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
            default: break;
        }


        mAdapter.notifyDataSetChanged();
    }

    void writeStringstofloats() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();
        for (int count=1;count!=49; count++) {
            editor.putFloat("Custom"+count, Float.parseFloat(sharedPref.getString("CustomS"+count,"0.00")));
        }
        editor.commit();
    }

    public class GreenAdapter extends RecyclerView.Adapter<GreenAdapter.NumberViewHolder> {

        private final String TAG = GreenAdapter.class.getSimpleName();
        final SharedPreferences sharedPref;

        GreenAdapter() {
            sharedPref = PreferenceManager.getDefaultSharedPreferences(OrePrices.this);
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
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(OrePrices.this);
            if (sharedPref.getBoolean("Veldspar", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Scordite", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Pyroxeres", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Plagioclase", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Omber", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Kernite", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Jaspet", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Hemorphite", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Hedbergite", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Gneiss", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Dark_Ochre", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Spodumain", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Crokite", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Arkonor", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Bistot", true)) {
                i++;
            }
            if (sharedPref.getBoolean("Mercoxit", false)) {
                i++;
            }
            if (sharedPref.getBoolean("Variants", false)) {
                i = i * 3;
            }
            i++;

            return i;
        }

        class NumberViewHolder extends RecyclerView.ViewHolder {


            final TextView listItemNumberView;
            final TextView PPI;
            final TextView PPV;
            final TextView PPH;
            final String[] Name = getResources().getStringArray(R.array.Names);

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
                        PPI.setText(String.format("%.2f", Float.parseFloat(sharedPref.getString("Perc", "100")) / 100 * sharedPref.getFloat(sharedPref.getString("BS", "Compressed Sell") + String.valueOf(Sort[listIndex]), (float) 0.00)));
                        String Vol[] = getResources().getStringArray(R.array.Vol);
                        PPV.setText(String.format("%.2f", Float.parseFloat(sharedPref.getString("Perc", "100")) / 100 * sharedPref.getFloat(sharedPref.getString("BS", "Compressed Sell") + String.valueOf(Sort[listIndex]), (float) 0.00) / Float.parseFloat(Vol[Sort[listIndex]])));
                        PPH.setText(String.format("%.2f", Float.parseFloat(sharedPref.getString("Perc", "100")) / 100 * 3600 * Float.parseFloat(sharedPref.getString("MPC", "1500.00")) * Float.parseFloat(sharedPref.getString("Min", "1")) * sharedPref.getFloat(sharedPref.getString("BS", "Compressed Sell") + String.valueOf(Sort[listIndex]), (float) 0.00) / Float.parseFloat(Vol[Sort[listIndex]]) / 1000000 / Float.parseFloat(sharedPref.getString("Time", "120"))) + "M");
                }
            }
        }
    }
}