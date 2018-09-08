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
import org.devfleet.android.minerhelper.selectionSettings.GasSelection;
import org.devfleet.android.minerhelper.systemSettings.GasSystemSelection;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class GasPrices extends BasePrices {

    private final int[] Sort = new int[26];
    private GreenAdapter mAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.buttons_gas, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        base = "https://market.fuzzwork.co.uk/aggregates/?types=30375,30376,30370,30371,30372,30373,30374,30377,30378,25268,25279,25275,25273,25277,25276,25278,25274,28694,28695,28696,28697,28698,28699,28700,28701";

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
        Intent launchSystemC = new Intent(this, GasSystemSelection.class);
        startActivity(launchSystemC);
    }

    void launchSelectionSettings() {
        Intent launchOreC = new Intent(this, GasSelection.class);
        startActivity(launchOreC);
    }

    void sortBy() {
        AlertDialog.Builder builder = new AlertDialog.Builder(GasPrices.this);
        builder.setTitle(R.string.dialog_message);
        builder.setItems(R.array.sortOptions, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(GasPrices.this);
                SharedPreferences.Editor editor = sharedPref.edit();
                switch (which) {
                    case 0:
                        editor.putString("SortG","PPV");
                        break;
                    case 1:
                        editor.putString("SortG","PPU");
                        break;
                    case 2:
                        editor.putString("SortG","PPN");
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

        String[] nums = getResources().getStringArray(R.array.numsG);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();

        for (int i = 1; i <= nums.length; i++) {
            try {
                editor.putFloat("Uncompressed SellG" + i, Float.parseFloat(String.valueOf(obj.getJSONObject(nums[i-1]).getJSONObject("sell").get("min"))));
            } catch (JSONException ignored) {
            }
        }
        for (int i = 1; i <= nums.length; i++) {
            try {
                editor.putFloat("Uncompressed BuyG" + i, Float.parseFloat(String.valueOf(obj.getJSONObject(nums[i-1]).getJSONObject("buy").get("max"))));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        for (int i = 1; i <= nums.length; i++) {
            try {
                editor.putFloat("Compressed SellG" + i, Float.parseFloat(String.valueOf(obj.getJSONObject(nums[i-1]).getJSONObject("sell").get("min"))));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        for (int i = 1; i <= nums.length; i++) {
            try {
                editor.putFloat("Compressed BuyG" + i, Float.parseFloat(String.valueOf(obj.getJSONObject(nums[i-1]).getJSONObject("buy").get("max"))));
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


        for (int i = 1; i != 26; i++) {
            if (key.equals("CustomSG" + String.valueOf(i)) || key.equals("Perc") || key.equals("MPC") || key.equals("Time") || key.equals("Min")) {
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
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(GasPrices.this);
        int i = 1;
        for (int c = 1; !(c == 26); c++) {
            switch (c) {
                case 1:
                    if (sharedPref.getBoolean("C28", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 2:
                    if (sharedPref.getBoolean("C32", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 3:
                    if (sharedPref.getBoolean("C50", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 4:
                    if (sharedPref.getBoolean("C60", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 5:
                    if (sharedPref.getBoolean("C70", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 6:
                    if (sharedPref.getBoolean("C72", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 7:
                    if (sharedPref.getBoolean("C84", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 8:
                    if (sharedPref.getBoolean("C320", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 9:
                    if (sharedPref.getBoolean("C540", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 10:
                    if (sharedPref.getBoolean("Amber_Cytoserocin", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 11:
                    if (sharedPref.getBoolean("Azure_Cytoserocin", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 12:
                    if (sharedPref.getBoolean("Celadon_Cytoserocin", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 13:
                    if (sharedPref.getBoolean("Golden_Cytoserocin", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 14:
                    if (sharedPref.getBoolean("Lime_Cytoserocin", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 15:
                    if (sharedPref.getBoolean("Malachite_Cytoserocin", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 16:
                    if (sharedPref.getBoolean("Vermillion_Cytoserocin", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 17:
                    if (sharedPref.getBoolean("Viridian_Cytoserocin", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 18:
                    if (sharedPref.getBoolean("Amber_Mykoserocin", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 19:
                    if (sharedPref.getBoolean("Azure_Mykoserocin", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 20:
                    if (sharedPref.getBoolean("Celadon_Mykoserocin", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 21:
                    if (sharedPref.getBoolean("Golden_Mykoserocin", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 22:
                    if (sharedPref.getBoolean("Lime_Mykoserocin", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 23:
                    if (sharedPref.getBoolean("Malachite_Mykoserocin", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 24:
                    if (sharedPref.getBoolean("Vermillion_Mykoserocin", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;
                case 25:
                    if (sharedPref.getBoolean("Viridian_Mykoserocin", true)) {
                        Sort[i] = c;
                        i++;
                    }
                    break;

            }
        }

        switch (sharedPref.getString("SortG", "PPV")) {
            case "PPV":
                String VolG[] = getResources().getStringArray(R.array.VolG);
                boolean tT = true;
                while (tT) {
                    tT = false;
                    for (int k = 1; k < i - 1; k++) {
                        if (sharedPref.getFloat(sharedPref.getString("BS", "Compressed Sell") + "G" + String.valueOf(Sort[k]), (float) 0.00) / Float.parseFloat(VolG[Sort[k]]) > sharedPref.getFloat(sharedPref.getString("BS", "Compressed Sell") + "G" + String.valueOf(Sort[k + 1]), (float) 0.00) / Float.parseFloat(VolG[Sort[k + 1]])) {
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
                boolean t = true;
                while (t) {
                    t = false;
                    for (int k = 1; k < i - 1; k++) {
                        if (sharedPref.getFloat(sharedPref.getString("BS", "Compressed Sell") + "G" + String.valueOf(Sort[k]), (float) 0.00) > sharedPref.getFloat(sharedPref.getString("BS", "Compressed Sell") + "G" + String.valueOf(Sort[k + 1]), (float) 0.00)) {
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
        for (int count = 1; count != 26; count++) {
            editor.putFloat("CustomG" + count, Float.parseFloat(sharedPref.getString("CustomSG" + count, "0.00")));
        }
        editor.commit();
    }

    public class GreenAdapter extends RecyclerView.Adapter<GreenAdapter.NumberViewHolder> {

        private final String TAG = GasPrices.GreenAdapter.class.getSimpleName();
        final SharedPreferences sharedPref;

        GreenAdapter() {
            sharedPref = PreferenceManager.getDefaultSharedPreferences(GasPrices.this);
        }

        @Override
        public GasPrices.GreenAdapter.NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
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
            GasPrices.GreenAdapter.NumberViewHolder viewHolder = new GasPrices.GreenAdapter.NumberViewHolder(view);
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
        public void onBindViewHolder(GasPrices.GreenAdapter.NumberViewHolder holder, int position) {

            holder.bind(position);
        }


        @Override
        public int getItemCount() {
            int i = 0;
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(GasPrices.this);

            if (sharedPref.getBoolean("C28", true)) {
                i++;
            }

            if (sharedPref.getBoolean("C32", true)) {
                i++;
            }

            if (sharedPref.getBoolean("C50", true)) {
                i++;
            }

            if (sharedPref.getBoolean("C60", true)) {
                i++;
            }

            if (sharedPref.getBoolean("C70", true)) {
                i++;
            }

            if (sharedPref.getBoolean("C72", true)) {
                i++;
            }

            if (sharedPref.getBoolean("C84", true)) {
                i++;
            }

            if (sharedPref.getBoolean("C320", true)) {
                i++;
            }

            if (sharedPref.getBoolean("C540", true)) {
                i++;
            }

            if (sharedPref.getBoolean("Amber_Cytoserocin", true)) {
                i++;
            }

            if (sharedPref.getBoolean("Azure_Cytoserocin", true)) {
                i++;
            }

            if (sharedPref.getBoolean("Celadon_Cytoserocin", true)) {
                i++;
            }

            if (sharedPref.getBoolean("Golden_Cytoserocin", true)) {
                i++;
            }

            if (sharedPref.getBoolean("Lime_Cytoserocin", true)) {
                i++;
            }

            if (sharedPref.getBoolean("Malachite_Cytoserocin", true)) {
                i++;
            }

            if (sharedPref.getBoolean("Vermillion_Cytoserocin", true)) {
                i++;
            }

            if (sharedPref.getBoolean("Viridian_Cytoserocin", true)) {
                i++;
            }


            if (sharedPref.getBoolean("Amber_Mykoserocin", true)) {
                i++;
            }

            if (sharedPref.getBoolean("Azure_Mykoserocin", true)) {
                i++;
            }

            if (sharedPref.getBoolean("Celadon_Mykoserocin", true)) {
                i++;
            }

            if (sharedPref.getBoolean("Golden_Mykoserocin", true)) {
                i++;
            }

            if (sharedPref.getBoolean("Lime_Mykoserocin", true)) {
                i++;
            }

            if (sharedPref.getBoolean("Malachite_Mykoserocin", true)) {
                i++;
            }

            if (sharedPref.getBoolean("Vermillion_Mykoserocin", true)) {
                i++;
            }

            if (sharedPref.getBoolean("Viridian_Mykoserocin", true)) {
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
            final String[] Name = getResources().getStringArray(R.array.NamesG);

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
                        switch (sharedPref.getString("BS", "Compressed Sell")){
                            case "Custom": BSt="Custom"; break;
                            case "Compressed Buy": BSt="Buy"; break;
                            case "Compressed Sell": BSt="Sell"; break;
                            case "Uncompressed Sell": BSt="Sell"; break;
                            case "Uncompressed Buy": BSt="Buy"; break;
                        }

                        listItemNumberView2.append(BSt + " prices");
                        break;
                    default:
                        listItemNumberView.setText(Name[Sort[listIndex]]);
                        PPI.setText(String.format("%.2f", Float.parseFloat(sharedPref.getString("Perc", "100"))/100*sharedPref.getFloat(sharedPref.getString("BS", "Compressed Sell")+"G"+String.valueOf(Sort[listIndex]), (float) 0.00)));
                        String VolG[]=getResources().getStringArray(R.array.VolG);
                        PPV.setText(String.format("%.2f", Float.parseFloat(sharedPref.getString("Perc", "100"))/100*sharedPref.getFloat(sharedPref.getString("BS", "Compressed Sell")+"G"+String.valueOf(Sort[listIndex]), (float) 0.00)/Float.parseFloat(VolG[Sort[listIndex]])));
                        PPH.setText(String.format("%.2f", Float.parseFloat(sharedPref.getString("Perc", "100"))/100*3600*Float.parseFloat(sharedPref.getString("AmountGas", "10"))*Float.parseFloat(sharedPref.getString("MinG", "1"))*sharedPref.getFloat(sharedPref.getString("BS", "Compressed Sell")+"G"+String.valueOf(Sort[listIndex]), (float) 0.00)/Float.parseFloat(VolG[Sort[listIndex]])/1000000/Float.parseFloat(sharedPref.getString("TimeGas", "30")))+"M");
                }
            }
        }
    }
}