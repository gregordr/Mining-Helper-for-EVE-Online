package org.devfleet.android.minerhelper.priceActivities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import org.devfleet.android.minerhelper.About;
import org.devfleet.android.minerhelper.R;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Scanner;

public abstract class BasePrices extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private final Handler repeatedTVUpdater = new Handler();
    private final Handler repeatedUpdater = new Handler();
    String base;
    private final Runnable repeatedUpdate = new Runnable() {
        @Override
        public void run() {
            refresh();
            repeatedTVUpdater.postDelayed(repeatedUpdate, 5 * 60000);
        }
    };
    String Status;
    long timeMS;
    private final Runnable repeatedTVUpdate = new Runnable() {
        @Override
        public void run() {
            updateTV();
            repeatedTVUpdater.postDelayed(repeatedTVUpdate, 60000);
        }
    };
    boolean setStatusError = false;

    private static String getResponse(URL url) throws IOException {
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_settings:
                launchSystemSettings();
                return true;
            case R.id.action_select:
                launchSelectionSettings();
                return true;

            case R.id.action_ore:
                launchOre();
                return true;
            case R.id.action_gas:
                launchGas();
                return true;
            case R.id.action_ice:
                launchIce();
                return true;
            case R.id.action_mineral:
                launchMinerals();
                return true;
            case R.id.action_about:
                launchAbout();
                return true;
            case R.id.action_refresh:
                refresh();
                return true;
            case R.id.action_sort:
                sortBy();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    abstract void launchSelectionSettings();

    abstract void launchSystemSettings();

    abstract void sortBy();

    private void launchOre() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("launchName", "Ore");
        editor.commit();

        Intent changeIntent = new Intent(this, OrePrices.class);
        finish();
        startActivity(changeIntent);
        this.overridePendingTransition(0, 0);
    }

    private void launchGas() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("launchName", "Gas");
        editor.commit();

        Intent changeIntent = new Intent(this, GasPrices.class);
        finish();
        startActivity(changeIntent);
        this.overridePendingTransition(0, 0);
    }

    private void launchIce() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("launchName", "Ice");
        editor.commit();

        Intent changeIntent = new Intent(this, IcePrices.class);
        finish();
        startActivity(changeIntent);
        this.overridePendingTransition(0, 0);
    }

    private void launchMinerals() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("launchName", "Mineral");
        editor.commit();

        Intent changeIntent = new Intent(this, MineralPrices.class);
        finish();
        startActivity(changeIntent);
        this.overridePendingTransition(0, 0);
    }

    private void launchAbout() {
        Intent launchAbout = new Intent(this, About.class);
        startActivity(launchAbout);
    }

    abstract void writeStringstofloats();

    abstract void resort();

    void updateTV() {
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
    }

    void refresh() {
        Uri ApiUri = makeURI();
        URL url = null;
        try {
            url = new URL(ApiUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        new fetch().execute(url);
    }

    private Uri makeURI() {
        String SystemNum;
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String SystemNames = sharedPref.getString("SystemNames", "Jita");
        switch (SystemNames) {
            case "Jita":
                SystemNum = "60003760";
                break;
            case "Amarr":
                SystemNum = "60008494";
                break;
            case "Rens":
                SystemNum = "60004588";
                break;
            case "Dodixie":
                SystemNum = "60011866";
                break;
            case "Hek":
                SystemNum = "60005686";
                break;
            default:
                SystemNum = "0";
                break;
        }
        return Uri.parse(base).buildUpon()
                .appendQueryParameter("station", SystemNum)
                .build();
    }

    abstract void getInfo(String s);

    class fetch extends AsyncTask<URL, Void, String> {
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
                } catch (Throwable ignored) {
                }

                if (obj != null) {
                    getInfo(s);
                } else {
                    setStatusError = true;
                }
            } else {
                setStatusError = true;
            }
            resort();
        }
    }

    class DividerItemDecoration extends RecyclerView.ItemDecoration {

        private final int[] ATTRS = new int[]{android.R.attr.listDivider};

        private final Drawable divider;

        DividerItemDecoration(Context context) {
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
}
