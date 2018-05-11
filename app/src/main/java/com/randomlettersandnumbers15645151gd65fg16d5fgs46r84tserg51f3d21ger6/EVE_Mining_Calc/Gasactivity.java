package com.randomlettersandnumbers15645151gd65fg16d5fgs46r84tserg51f3d21ger6.EVE_Mining_Calc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Scanner;


public class Gasactivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    Gasactivity.GreenAdapter mAdapter;
    RecyclerView mNumbersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mNumbersList = (RecyclerView) findViewById(R.id.RecV);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mNumbersList.setLayoutManager(layoutManager);
        mNumbersList.setHasFixedSize(true);
        mAdapter = new Gasactivity.GreenAdapter();
        mNumbersList.addItemDecoration(new Gasactivity.DividerItemDecoration(this));
        mNumbersList.setAdapter(mAdapter);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPref.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onStart() {
        writeStringstofloats();
        resort();
        super.onStart();
        repeatedTVUpdate.run();
        repeatedUpdate.run();
    }

    @Override
    protected void onStop() {
        super.onStop();
        repeatedTVUpdater.removeCallbacks(repeatedTVUpdate);
        repeatedUpdater.removeCallbacks(repeatedUpdate);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.buttonsgas, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settingsIce:
                launchSystemC();
                return true;
            case R.id.action_refresh:
                refresh();
                return true;
            case R.id.action_sort:
                sortBy();
                return true;
            case R.id.action_iceChoice:
                launchOreC();
                return true;
            case R.id.action_backtoOre:
                launchGas();
                return true;
            case R.id.action_ice:
                launchice();
                return true;
            case R.id.action_about:
                launchAbout();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void launchGas() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(Gasactivity.this);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putBoolean("Gas",false);
        editor.commit();

        Intent iceintent = new Intent (this, MainActivity.class);
        finish();
        startActivity(iceintent);
        this.overridePendingTransition(0, 0);
    }

    public void launchice() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(Gasactivity.this);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putBoolean("Gas",false);
        editor.putBoolean("Ice",true);
        editor.commit();

        Intent iceintent = new Intent (this, MainActivity.class);
        finish();
        startActivity(iceintent);
        this.overridePendingTransition(0, 0);
    }

    public void launchSystemC() {
        Intent launchSystemC = new Intent(this, SystemCgas.class);
        startActivity(launchSystemC);
    }

    public void launchOreC() {
        Intent launchOreC = new Intent(this, OreCgas.class);
        startActivity(launchOreC);
    }

    public void launchAbout() {
        Intent launchAbout = new Intent(this, About.class);
        startActivity(launchAbout);
    }

    public void refresh() {
        Uri ApiUri = makeURI();
        URL url = null;
        try {
            url = new URL(ApiUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        new Gasactivity.fetch().execute(url);

    }

    public void sortBy() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Gasactivity.this);
        builder.setTitle(R.string.dialog_message);
        builder.setItems(R.array.sortOptions, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(Gasactivity.this);
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

    public Uri makeURI() {
        String SystemNum;
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String SystemNames = sharedPref.getString("SystemNames","Jita");
        switch (SystemNames) {
            case "Jita": SystemNum="60003760"; break;
            case "Amarr": SystemNum="60008494"; break;
            case "Rens": SystemNum="60004588"; break;
            case "Dodixie": SystemNum="60011866"; break;
            case "Hek": SystemNum="60005686"; break;
            default: SystemNum="0"; break;
        }

        String base = "https://market.fuzzwork.co.uk/aggregates/?types=30375,30376,30370,30371,30372,30373,30374,30377,30378,25268,25279,25275,25273,25277,25276,25278,25274,28694,28695,28696,28697,28698,28699,28700,28701";

        Uri ApiUri = Uri.parse(base).buildUpon()

                .appendQueryParameter("station", SystemNum)
                .build();

        return ApiUri;
    }

    public static String getResponse(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }

    }

    public class fetch extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... urls) {
            URL search = urls[0];
            String results = null;
            try {
                results = getResponse(search);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return results;
        }

        @Override
        protected void onPostExecute(String s) {
            if (s != null && !s.equals("")) {
                setStatusError = false;

                JSONObject obj = null;
                try {
                    obj = new JSONObject(s);
                } catch (Throwable t) {                }

                if(obj!=null) {
                    getInfo(s); } else {
                    setStatusError = true;
                }
            } else {
                setStatusError = true;
            }
            resort();
        }
    }

    public void getInfo(String s) {
        JSONObject obj = null;
        try {

            obj = new JSONObject(s);


        } catch (Throwable t) {

        }

        String[] nums = getResources().getStringArray(R.array .numsG);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();

        for (int i = 1; i!=26; i++) {
            try {
                editor.putFloat("Uncompressed SellG" + i, Float.parseFloat(String.valueOf(obj.getJSONObject(nums[i-1]).getJSONObject("sell").get("min"))));
            } catch (JSONException e) {
            }
        }
        for (int i = 1; i!=26; i++) {
            try {
                editor.putFloat("Uncompressed BuyG" + i, Float.parseFloat(String.valueOf(obj.getJSONObject(nums[i-1]).getJSONObject("buy").get("max"))));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        for (int i = 1; i!=26; i++) {
            try {
                editor.putFloat("Compressed SellG" + i, Float.parseFloat(String.valueOf(obj.getJSONObject(nums[i-1]).getJSONObject("sell").get("min"))));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        for (int i = 1; i!=26; i++) {
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
    }

    String Status;
    public long timeMS;
    boolean setStatusError = false;

    Handler repeatedTVUpdater = new Handler();
    Runnable repeatedTVUpdate = new Runnable() {
        @Override
        public void run() {
            updateTV();
            repeatedTVUpdater.postDelayed(repeatedTVUpdate, 60000);
        }
    };

    Handler repeatedUpdater = new Handler();
    Runnable repeatedUpdate = new Runnable() {
        @Override
        public void run() {
            refresh();
            repeatedTVUpdater.postDelayed(repeatedUpdate, 5*60000);
        }
    };

    public void updateTV() {
        Calendar c = Calendar.getInstance();
        long time = c.getTimeInMillis();
        long rightNow = time - timeMS;
        rightNow = rightNow / 60000;
        if (rightNow < 15 && rightNow >= 0) {
            Status = "Up to date";
        }
        if (rightNow >= 15) {
            Status = "Updated " + rightNow + " minutes ago";
        }
        if (rightNow < 0) {
            Status = "Time travel error";
        }
        if (timeMS == 0) {
            Status = "Updating...";
        }
        mAdapter.notifyItemChanged(0);
    }

    public class GreenAdapter extends RecyclerView.Adapter<Gasactivity.GreenAdapter.NumberViewHolder> {

        private final String TAG = Gasactivity.GreenAdapter.class.getSimpleName();
        SharedPreferences sharedPref;

        GreenAdapter() {
            sharedPref = PreferenceManager.getDefaultSharedPreferences(Gasactivity.this);
        }

        @Override
        public Gasactivity.GreenAdapter.NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
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
            Gasactivity.GreenAdapter.NumberViewHolder viewHolder = new Gasactivity.GreenAdapter.NumberViewHolder(view);
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
        public void onBindViewHolder(Gasactivity.GreenAdapter.NumberViewHolder holder, int position) {

            holder.bind(position);
        }



        @Override
        public int getItemCount() {
            int i = 0;
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(Gasactivity.this);

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


            TextView listItemNumberView;
            TextView PPI;
            TextView PPV;
            TextView PPH;


            public NumberViewHolder(View itemView) {
                super(itemView);

                listItemNumberView = (TextView) itemView.findViewById(R.id.TV1);
                PPI = (TextView) itemView.findViewById(R.id.PPI);
                PPV = (TextView) itemView.findViewById(R.id.PPV);
                PPH = (TextView) itemView.findViewById(R.id.PPH);
            }

            String Name[]=getResources().getStringArray(R.array.NamesG);

            void bind(int listIndex) {
                switch (listIndex) {
                    case 0:
                        listItemNumberView.setText(Status);
                        if (setStatusError) {
                            listItemNumberView.append(" Can't connect");
                        }
                        TextView listItemNumberView2 = (TextView) itemView.findViewById(R.id.TV2);
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


    public class DividerItemDecoration extends RecyclerView.ItemDecoration {

        private final int[] ATTRS = new int[]{android.R.attr.listDivider};

        private Drawable divider;

        public DividerItemDecoration(Context context) {
            final TypedArray styledAttributes = context.obtainStyledAttributes(ATTRS);
            divider = styledAttributes.getDrawable(0);
            styledAttributes.recycle();
        }


        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + divider.getIntrinsicHeight();

                divider.setBounds(left, top, right, bottom);
                divider.draw(c);
            }
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();


        for (int i=1;i!=26;i++) {
            if(key.equals("CustomSG"+String.valueOf(i))||key.equals("Perc")||key.equals("MPC")||key.equals("Time")||key.equals("Min")){
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



    int Sort[]= new int[26];

    public void resort() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(Gasactivity.this);
        int i=1;
        for(int c=1; !(c==26) ;c++) {
            switch (c) {
                case 1: if(sharedPref.getBoolean("C28", true)) {Sort[i]=c; i++;} break;
                case 2: if(sharedPref.getBoolean("C32", true)) {Sort[i]=c; i++;} break;
                case 3: if(sharedPref.getBoolean("C50", true)) {Sort[i]=c; i++;} break;
                case 4: if(sharedPref.getBoolean("C60", true)) {Sort[i]=c; i++;} break;
                case 5: if(sharedPref.getBoolean("C70", true)) {Sort[i]=c; i++;} break;
                case 6: if(sharedPref.getBoolean("C72", true)) {Sort[i]=c; i++;} break;
                case 7: if(sharedPref.getBoolean("C84", true)) {Sort[i]=c; i++;} break;
                case 8: if(sharedPref.getBoolean("C320", true)) {Sort[i]=c; i++;} break;
                case 9: if(sharedPref.getBoolean("C540", true)) {Sort[i]=c; i++;} break;
                case 10: if(sharedPref.getBoolean("Amber_Cytoserocin", true)) {Sort[i]=c; i++;} break;
                case 11: if(sharedPref.getBoolean("Azure_Cytoserocin", true)) {Sort[i]=c; i++;} break;
                case 12: if(sharedPref.getBoolean("Celadon_Cytoserocin", true)) {Sort[i]=c; i++;} break;
                case 13: if(sharedPref.getBoolean("Golden_Cytoserocin", true)) {Sort[i]=c; i++;} break;
                case 14: if(sharedPref.getBoolean("Lime_Cytoserocin", true)) {Sort[i]=c; i++;} break;
                case 15: if(sharedPref.getBoolean("Malachite_Cytoserocin", true)) {Sort[i]=c; i++;} break;
                case 16: if(sharedPref.getBoolean("Vermillion_Cytoserocin", true)) {Sort[i]=c; i++;} break;
                case 17: if(sharedPref.getBoolean("Viridian_Cytoserocin", true)) {Sort[i]=c; i++;} break;
                case 18: if(sharedPref.getBoolean("Amber_Mykoserocin", true)) {Sort[i]=c; i++;} break;
                case 19: if(sharedPref.getBoolean("Azure_Mykoserocin", true)) {Sort[i]=c; i++;} break;
                case 20: if(sharedPref.getBoolean("Celadon_Mykoserocin", true)) {Sort[i]=c; i++;} break;
                case 21: if(sharedPref.getBoolean("Golden_Mykoserocin", true)) {Sort[i]=c; i++;} break;
                case 22: if(sharedPref.getBoolean("Lime_Mykoserocin", true)) {Sort[i]=c; i++;} break;
                case 23: if(sharedPref.getBoolean("Malachite_Mykoserocin", true)) {Sort[i]=c; i++;} break;
                case 24: if(sharedPref.getBoolean("Vermillion_Mykoserocin", true)) {Sort[i]=c; i++;} break;
                case 25: if(sharedPref.getBoolean("Viridian_Mykoserocin", true)) {Sort[i]=c; i++;} break;

            }
        }

        switch (sharedPref.getString("SortG","PPV")) {
            case "PPV":
                String VolG[]=getResources().getStringArray(R.array.VolG);
                boolean tT = true;
                while (tT) {
                    tT = false;
                    for (int k = 1; k < i - 1; k++) {
                        if (sharedPref.getFloat(sharedPref.getString("BS", "Compressed Sell") + "G" + String.valueOf(Sort[k]), (float) 0.00)/Float.parseFloat(VolG[Sort[k]])> sharedPref.getFloat(sharedPref.getString("BS", "Compressed Sell") + "G" + String.valueOf(Sort[k + 1]), (float) 0.00)/Float.parseFloat(VolG[Sort[k+1]])) {
                            int r = Sort[k];
                            Sort[k] = Sort[k + 1];
                            Sort[k + 1] = r;
                            tT = true;
                        }
                    }
                }
                int[] SortRR = new int[i];
                for (int KKK = 1; KKK < i; KKK++) {
                    SortRR[KKK] = Sort[i - KKK];
                }
                for (int KKK = 1; KKK < i; KKK++) {
                    Sort[KKK] = SortRR[KKK];
                }
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
                int[] SortR = new int[i];
                for (int KKK = 1; KKK < i; KKK++) {
                    SortR[KKK] = Sort[i - KKK];
                }
                for (int KKK = 1; KKK < i; KKK++) {
                    Sort[KKK] = SortR[KKK];
                }
                break;
            default: break;
        }


        mAdapter.notifyDataSetChanged();
    }

    public void writeStringstofloats() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();
        for (int count=1;count!=26; count++) {
            editor.putFloat("CustomG"+count, Float.parseFloat(sharedPref.getString("CustomSG"+count,"0.00")));
        }
        editor.commit();
    }
}